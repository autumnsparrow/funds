/**
 *
 */
package com.palmcommerce.funds.alert.service.impl;

import java.net.URI;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;

import org.apache.commons.lang.StringUtils;

import javax.mail.internet.MimeMessage;

import com.palmcommerce.funds.alert.model.AlertStandardControl;
import com.palmcommerce.funds.alert.model.AlertStandardControlManager;
import com.palmcommerce.funds.alert.model.TradeRecordStatics;
import com.palmcommerce.funds.alert.service.IAlertManager;
import com.palmcommerce.funds.alert.service.sms.SmsCodeSendHelper;
import com.palmcommerce.funds.alert.service.sms.SmsServerConfig;
import com.palmcommerce.funds.roo.model.TransactionMeta;
import com.palmcommerce.funds.roo.model.TransactionRecord;
import com.palmcommerce.funds.roo.service.TransactionMetaService;
import com.palmcommerce.funds.roo.util.DateConvertor;
import com.palmcommerce.oss.jpa.model.OssAlert;
import com.palmcommerce.oss.jpa.service.AlertService;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.ui.velocity.VelocityEngineUtils;

/**
 * @author Administrator
 *
 */
public class DefaultAlertManager implements IAlertManager {

    // 内存 存储交易记录
    private HashMap<String, TradeRecordStatics> statics = new HashMap<String, TradeRecordStatics>();
    @Autowired
    AlertStandardControlManager alertStandardControlManager;
    @Autowired
    TransactionMetaService transactionMetaService;
    @Autowired
    AlertService alertService;
    @Autowired
    EmailProperty emailProperty;

    @Autowired
    SmsCodeSendHelper smsSender;
    @Value("${funds.roo.enable.alert.service}")
    boolean enableAlert;
    /**
     * 1
     * init serviceload   accumulation TradeRecord
     */
    @Override
    public void loadDefaultTradeRecord() {
    	if(enableAlert){
    	new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				 DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			        DateFormat f = new SimpleDateFormat("yyyy-MM-dd");
			        Date maxTradeDateTime = new Date();
			        String dateString = f.format(maxTradeDateTime);
			        Date minTradeDateTime = null;
			        try {
			            minTradeDateTime = format.parse(dateString + " 00:00:00");
			        } catch (ParseException e) {
			            e.printStackTrace();
			        }
			        List<TransactionMeta> chargeRecords = transactionMetaService.findTransactionMetasByTradeDateTimeBetweenAndTransCode(minTradeDateTime, maxTradeDateTime, true);
			        List<TransactionMeta> withdrawRecords = transactionMetaService.findTransactionMetasByTradeDateTimeBetweenAndTransCode(minTradeDateTime, maxTradeDateTime, false);
			        for (TransactionMeta meta : chargeRecords) {
			            notifyTradeRecord(meta); //accumulation chargeRecords
			        }
			        for (TransactionMeta meta : withdrawRecords) {
			            notifyTradeRecord(meta); //accumulation withdrawRecord
			        }
				
			}
		}).start();
    	}
    }

    private static final int INVALID_CODE = -1;

    private static final int WITHDRAW_BY_TRADE = 0;

    private static final int CHARGE_BY_BANK = 1;

    private static final int CHARGE_BY_TRADE = 2;

    /**
     * @param meta  当前交易
     * @return 判断当前的交易  充值  or 提现
     */
    private int isCharge(TransactionMeta meta) {
        int ret = INVALID_CODE;
        if (ret==INVALID_CODE) {
        		ret = "250007".equals(meta.getTranscode()) ? WITHDRAW_BY_TRADE : INVALID_CODE;
		}
        if (ret == INVALID_CODE) 
        		ret = "240003".equals(meta.getTranscode()) ? CHARGE_BY_BANK : INVALID_CODE;
        if (ret == INVALID_CODE) 
        		ret = "250004".equals(meta.getTranscode()) ? CHARGE_BY_TRADE : INVALID_CODE;
        System.out.println("250007-->0;   240003-->1;   250004-->2 》》》"+ret);
        return ret;
    }

    /**
     * @param r
     */
    public String getKey(String userId, String bankId, String tradeId) {
        return String.format("%s_%s_%s", userId, tradeId, bankId);
    }

    /**
     * 1.1
     */
    @Override
    public void notifyTradeRecord(TransactionMeta meta) {
    	if(enableAlert){
        int isCharge = isCharge(meta);
        if(meta.getTransactionRecord()==null)
    		return;
        if (meta==null) 
			return;
		
        String key = null;
        if (isCharge == WITHDRAW_BY_TRADE) {
            	key = getKey(meta.getTransactionRecord().getUserId(),meta.getToCode() ,meta.getFromCode() );
            	updateWithdraw(meta.getTransactionRecord(), key);
        } else if (isCharge == CHARGE_BY_BANK || isCharge == CHARGE_BY_TRADE) {
            if (isCharge == CHARGE_BY_BANK) {
            	
                key = getKey(meta.getTransactionRecord().getUserId(),meta.getToCode(), meta.getFromCode());
                System.out.println(key);
            } else {
                key = getKey(meta.getTransactionRecord().getUserId(), meta.getToCode(), meta.getFromCode() );
                System.out.println(key);
            }
            updateRecharge(meta.getTransactionRecord(), key);
        }
    	}
    }

    /**
     * 1.1.1
     * @param r 单条提现交易记录
     * @param key  指定用户-银行-平台之间
     *  提现累加
     */
    private void updateWithdraw(TransactionRecord r, String key) {
        TradeRecordStatics savedTradeRecordStatics = null;
        savedTradeRecordStatics = statics.get(key);
        if (savedTradeRecordStatics != null) {
            if (!savedTradeRecordStatics.validate()) {
                statics.clear();
            }
        } else {
            savedTradeRecordStatics = new TradeRecordStatics(r, key);
            statics.put(key, savedTradeRecordStatics);
        }
        savedTradeRecordStatics.drawNumbers++;
        savedTradeRecordStatics.drawAmounts += r.getAmount();
        AlertStandardControl standard = alertStandardControlManager.getStandardControl(savedTradeRecordStatics);
        withdrawAlarm(r, savedTradeRecordStatics, standard); //提现预警
    }

    /**
     * 1.1.2
     * @param r  
     * @param key  
     *  Recharge
     */
    private void updateRecharge(TransactionRecord r, String key) {
        TradeRecordStatics savedTradeRecordStatics = null;
        savedTradeRecordStatics = statics.get(key);
        if (savedTradeRecordStatics != null) {
            if (!savedTradeRecordStatics.validate()) {
                statics.clear();
            }
        } else {
            savedTradeRecordStatics = new TradeRecordStatics(r, key);
            statics.put(key, savedTradeRecordStatics);
        }
//        System.out.println("tradeCode:"+r.getTransactionMeta().getTranscode()+"金额："+r.getAmount());
        savedTradeRecordStatics.chargeNumbers++;
        savedTradeRecordStatics.chargeAmounts += r.getAmount();
//        System.out.println("充值叠加次数和："+savedTradeRecordStatics.chargeNumbers);
//        System.out.println("充值叠加金额和"+savedTradeRecordStatics.chargeAmounts);
//        System.out.println("更新充值updateRecharge"+key);
        AlertStandardControl standard = alertStandardControlManager.getStandardControl(savedTradeRecordStatics);
        rechargeAlarm(savedTradeRecordStatics, standard); //recharge alarm
    }

    /**
     * 1.1.2.1
     * @param s  
     * @param standard 
     *  Recharge Alarm
     */
    private void rechargeAlarm(TradeRecordStatics s, AlertStandardControl standard) {
        //判断  -->充值的总次数
    	//System.out.println("充值的总次数："+s.getChargeNumbers());
    	if( standard.getAmountControl()==null)
    		return;
        if (s.getChargeNumbers() > standard.getAmountControl().getMaxRechargeTimes().intValue()) {
            typeId = 5; 
            if (typeId == 5) {
                judgeAndTradeCreditTimesSendMail(typeId,s.getChargeNumbers(),standard.getAmountControl().getMaxRechargeTimes().intValue());
            }
        }
        //判断   -->充值的总金额
        //System.out.println("充值的总金额"+s.getChargeAmounts());
        if (s.getChargeAmounts() > standard.getAmountControl().getMaxRechargeAmount().intValue()) {
            typeId = 4; 
            if (typeId == 4) {
                judgeAndTradeCreditAmountSendMail(typeId,s.getChargeAmounts(),standard.getAmountControl().getMaxRechargeAmount().intValue());
            }
        }
    }

    /**
     * 1.1.1.1
     * @param r 单条记录
     * @param s  多条记录综合
     * @param standard 标准
     * 提现预警
     */
    private void withdrawAlarm(TransactionRecord r, TradeRecordStatics s, AlertStandardControl standard) {
    	
    	if(standard.getWithdrawStard()==null)
    		return;
        //判断  提现审核金额
    	//System.out.println("当前提现交易为："+r.getAmount());
    	if (r.getAmount() > standard.getWithdrawStard().getMaxWithdrawAmount().intValue()) {
            typeId = 3;     //
            if (typeId == 3) {
                judgeAndWithdrawSendMail(typeId, context, r, standard);
            }
        }
        //判断    -->提现总金额
      //  System.out.println("交易额控制提现总金额:"+standard.getAmountControl().getMaxWithdrawalsAmount().intValue());
      //  System.out.println("提现总金额:"+s.getDrawAmounts());
        if (s.getDrawAmounts() > standard.getAmountControl().getMaxWithdrawalsAmount().intValue()) {
            typeId = 3; 
            if (typeId == 3) {
				judgeAndTradeCreditAmountSendMail(typeId,s.getDrawAmounts(), standard.getAmountControl().getMaxWithdrawalsAmount().intValue());
			}
		}
		//判断   -->提现总次数
       // System.out.println("交易额控制提现总次数:"+standard.getAmountControl().getMaxWithdrawalsTimes().intValue());
       // System.out.println("提现总次数:"+s.getDrawNumbers());
        if (s.getDrawNumbers()>standard.getAmountControl().getMaxWithdrawalsTimes().intValue()) {
			typeId=2;
			if (typeId==2) {
                judgeAndTradeCreditTimesSendMail(typeId,s.getDrawNumbers(), standard.getAmountControl().getMaxWithdrawalsTimes().intValue());
            }
        }
    }

    private List<OssAlert> alertList;
    int typeId;
    String context = "";

    /*0:'差账',1:'调账',2:'提现异常',3:'大额提现',4:'大额充值',5:'充值异常'*/
    /*通知方式:'短信',1:'邮件',2:'QQ'*/
    /**
 	 * 交易   次数
     */
    private void judgeAndTradeCreditTimesSendMail(int typeId, int rechargeNumbers,int MaxTimes) {
        alertList = alertService.findOssAlertsByAlertTypeId(typeId);
        for (OssAlert alert : alertList) {
            if (alert.getAlertNotityId().intValue() == 0) {
                try {
                    sendNote(alert.getOperatorPhone(), alert.getRemarks());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (alert.getAlertNotityId().intValue() == 1) {
            	
            	AlertMessage alertMessage=new AlertMessage();
            	alertMessage.setNumbers(MaxTimes);//标准次数
            	alertMessage.setTimes(rechargeNumbers);
            	alertMessage.setOperatorName(alert.getOperatorName());
            	alertMessage.setSendAddr(emailProperty.getFrom());
            	alertMessage.setTime(DateConvertor.getTradeTime());
            	alertMessage.setTradeName("times");
            	alertMessage.setContext(alert.getRemarks());
            	alertMessage.setUserName(emailProperty.getUsername());
            	sendMessageFormTimes(alertMessage, alert.getOperatorPhone());
            }
        }
    }
    /**
 	 * 交易  金额
     */
    private void judgeAndTradeCreditAmountSendMail(int typeId,long money, int maxAmount) {
        alertList = alertService.findOssAlertsByAlertTypeId(typeId);
        for (OssAlert alert : alertList) {
            if (alert.getAlertNotityId().intValue() == 0) {
                try {
                    sendNote(alert.getOperatorPhone(), alert.getRemarks());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (alert.getAlertNotityId().intValue() == 1) {
            	
            	AlertMessage alertMessage=new AlertMessage();
            	alertMessage.setAmount(String.valueOf(maxAmount));
            	alertMessage.setMoney(String.valueOf(money));
            	alertMessage.setOperatorName(alert.getOperatorName());
            	alertMessage.setSendAddr(emailProperty.getFrom());
            	alertMessage.setTradeName("operator");
            	alertMessage.setContext(alert.getRemarks());
            	alertMessage.setTime(DateConvertor.getTradeTime());
            	alertMessage.setUserName(emailProperty.getUsername());
            	sendMessageFormAmount(alertMessage, alert.getOperatorPhone());
            }
        }
    }
    /**
     *  提现审核  
     */
    private void judgeAndWithdrawSendMail(int typeId, String context, TransactionRecord r, AlertStandardControl standard) {
        alertList = alertService.findOssAlertsByAlertTypeId(typeId);
        for (OssAlert alert : alertList) {
            if (alert.getAlertNotityId().intValue() == 0) {
                try {
                    //发短信通知
                    sendNote(alert.getOperatorPhone(), alert.getRemarks());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (alert.getAlertNotityId().intValue() == 1) {
            	AlertMessage alertMessage=new AlertMessage();
            	alertMessage.setAmount(String.valueOf(standard.getWithdrawStard().getMaxWithdrawAmount().intValue()));
            	alertMessage.setMoney(String.valueOf(r.getAmount()));
            	alertMessage.setOperatorName(alert.getOperatorName());
            	alertMessage.setSendAddr(emailProperty.getFrom());
            	alertMessage.setTime(DateConvertor.getTradeTime());
            	alertMessage.setContext(alert.getRemarks());
            	alertMessage.setTradeName(r.getUserName());
            	alertMessage.setContext(context);
            	sendMessage(alertMessage, alert.getOperatorPhone());
            }
        }
    }

   
  
	public AlertStandardControlManager getAlertStandardControlManager() {
		return alertStandardControlManager;
	}

	public void setAlertStandardControlManager(
			AlertStandardControlManager alertStandardControlManager) {
		this.alertStandardControlManager = alertStandardControlManager;
	}

	public TransactionMetaService getTransactionMetaService() {
		return transactionMetaService;
	}
	public void setTransactionMetaService(
			TransactionMetaService transactionMetaService) {
		this.transactionMetaService = transactionMetaService;
	}
	public AlertService getAlertService() {
		return alertService;
	}
	public void setAlertService(AlertService alertService) {
		this.alertService = alertService;
	}
	public List<OssAlert> getAlertList() {
        return alertList;
    }
    public void setAlertList(List<OssAlert> alertList) {
        this.alertList = alertList;
    }
    public HashMap<String, TradeRecordStatics> getStatics() {
        return statics;
    }
    public void setStatics(HashMap<String, TradeRecordStatics> statics) {
        this.statics = statics;
    }
    public int getTypeId() {
        return typeId;
    }
    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }
    public String getContext() {
        return context;
    }
    public void setContext(String context) {
        this.context = context;
    }

    //send SMS message  
    private void sendNote(String operatorPhone, String message) {
    	System.out.println("短信通知");
    	smsSender.send(operatorPhone, message);
	}

    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    VelocityEngine velocityEngine;
    
    
    public  void sendMail(final Object message,final String to,final String from,final String template){
    	MimeMessagePreparator preparator = new MimeMessagePreparator() {
            public void prepare(MimeMessage mimeMessage) throws Exception {
               MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
               mimeMessage.setSubject("警报信息");
               message.setTo(to);
               message.setFrom(from); // could be parameterized...
               Map model = new HashMap();
               model.put("message", message);
               String text = VelocityEngineUtils.mergeTemplateIntoString(
                  velocityEngine,template, model);
              // System.out.println("交易次数的警报");
               message.setText(text, true);
               
            }
         };
         this.mailSender.send(preparator);
    }
    
    
    public void sendMessageFormTimes(final AlertMessage alert,final String to){
    	MimeMessagePreparator preparator = new MimeMessagePreparator() {
            public void prepare(MimeMessage mimeMessage) throws Exception {
               MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
               mimeMessage.setSubject("警报信息");
               message.setTo(to);
               message.setFrom(alert.getSendAddr()); // could be parameterized...
               Map model = new HashMap();
               model.put("alert", alert);
               String text = VelocityEngineUtils.mergeTemplateIntoString(
                  velocityEngine, "com/palmcommerce/funds/alert/service/impl/TimesMessage.vm", "utf-8", model);
              // System.out.println("交易次数的警报");
               message.setText(text, true);
               
            }
         };
         try {
			this.mailSender.send(preparator);
		} catch (MailException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    public void sendMessageFormAmount(final AlertMessage alert,final String to){
    	MimeMessagePreparator preparator = new MimeMessagePreparator() {
            public void prepare(MimeMessage mimeMessage) throws Exception {
               MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
               mimeMessage.setSubject("警报信息");
               message.setTo(to);
               message.setFrom(alert.getSendAddr()); // could be parameterized...
               Map model = new HashMap();
               model.put("alert", alert);
               String text = VelocityEngineUtils.mergeTemplateIntoString(
                  velocityEngine, "com/palmcommerce/funds/alert/service/impl/AmountMessage.vm","utf-8",  model);
               message.setText(text, true);
            }
         };
         this.mailSender.send(preparator);
    }
    
    
    public void sendMessage(final AlertMessage alert,final String to) {
        MimeMessagePreparator preparator = new MimeMessagePreparator() {
            public void prepare(MimeMessage mimeMessage) throws Exception {
               MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
               mimeMessage.setSubject("警报信息");
               message.setTo(to);
               message.setFrom(alert.getSendAddr()); // could be parameterized...
               Map model = new HashMap();
               model.put("alert", alert);
               String text = VelocityEngineUtils.mergeTemplateIntoString(
                  velocityEngine, "com/palmcommerce/funds/alert/service/impl/AlertMessage.vm","utf-8",  model);
               message.setText(text, true);
            }
         };
         try {
			this.mailSender.send(preparator);
		} catch (MailException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
