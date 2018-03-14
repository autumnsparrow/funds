/**
 * @company Palm Lottery Information&Technology Co.,Ltd.
 *
 * @author  sparrow
 *
 * @date    Oct 27, 2013
 *
 */
package com.palmcommerce.funds.roo.protocol.impl;

import java.io.File;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.integration.Message;
import org.springframework.integration.MessageChannel;
import org.springframework.integration.support.MessageBuilder;

import com.palmcommerce.funds.protocol.impl.p2t.P240001;
import com.palmcommerce.funds.protocol.impl.p2t.P240002;
import com.palmcommerce.funds.protocol.impl.p2t.P240003;
import com.palmcommerce.funds.protocol.impl.p2t.P240004;
import com.palmcommerce.funds.protocol.impl.p2t.P240005;
import com.palmcommerce.funds.protocol.impl.p2t.P240006;
import com.palmcommerce.funds.protocol.impl.t2p.T250001;
import com.palmcommerce.funds.protocol.impl.t2p.T250002;
import com.palmcommerce.funds.protocol.impl.t2p.T250004;
import com.palmcommerce.funds.protocol.impl.t2p.T250005;
import com.palmcommerce.funds.protocol.impl.t2p.T250006;
import com.palmcommerce.funds.protocol.impl.t2p.T250007;
import com.palmcommerce.funds.protocol.impl.t2p.T250008;
import com.palmcommerce.funds.protocol.impl.t2p.T250009;
import com.palmcommerce.funds.protocol.impl.t2p.T250010;
import com.palmcommerce.funds.protocol.impl.t2p.T250011;
import com.palmcommerce.funds.protocol.impl.t2p.T260004;
import com.palmcommerce.funds.roo.model.TradeReconciliationStatics;
import com.palmcommerce.funds.roo.protocol.IProtocolProcessHandler;
import com.palmcommerce.funds.roo.service.TradeReconciliationStaticsService;
import com.palmcommerce.funds.roo.service.TransactionMetaService;
import com.palmcommerce.funds.roo.tasklet.impl.T250011TaskOfTrade;
import com.palmcommerce.funds.roo.util.DateConvertor;
import com.palmcommerce.funds.service.ProtocolProcessException;

public class DefaultProtocolProcessHandler implements IProtocolProcessHandler {
	
	
	
	@Autowired
	TradeReconciliationStaticsService tradeReconciliationStaticsService;
	
	@Autowired
	T250011TaskOfTrade taskOfTrade;

	@Value("${reconciliation.local.dir.sending}")
	String localSendingDir;
	
	@Autowired
	MessageChannel rooSendingInputChannel;
	
	@Autowired
	TransactionMetaService transactionMetaService;
	
	
	
	
	private boolean sending(File file){
		final Message<File> sending = MessageBuilder.withPayload(file).build();
		boolean ret=true;
		ret=rooSendingInputChannel.send(sending);
		return ret;
	}
	
	
	
	
	


	/**
	 * 
	 */
	public DefaultProtocolProcessHandler() {
	}

	/* (non-Javadoc)
	 * @see com.palmcommerce.funds.roo.protocol.IProtocolProcessHandler#p240001(com.palmcommerce.funds.protocol.impl.p2t.P240001)
	 */
	@Override
	public boolean p240001(P240001 p) throws ProtocolProcessException {
		return false;
	}

	/* (non-Javadoc)
	 * @see com.palmcommerce.funds.roo.protocol.IProtocolProcessHandler#p240002(com.palmcommerce.funds.protocol.impl.p2t.P240002)
	 */
	@Override
	public boolean p240002(P240002 p) throws ProtocolProcessException {
		return false;
	}

	/* (non-Javadoc)
	 * @see com.palmcommerce.funds.roo.protocol.IProtocolProcessHandler#p240003(com.palmcommerce.funds.protocol.impl.p2t.P240003)
	 */
	@Override
	public boolean p240003(P240003 p) throws ProtocolProcessException {
		return false;
	}

	/* (non-Javadoc)
	 * @see com.palmcommerce.funds.roo.protocol.IProtocolProcessHandler#p240004(com.palmcommerce.funds.protocol.impl.p2t.P240004)
	 */
	@Override
	public boolean p240004(P240004 p) throws ProtocolProcessException {
		return false;
	}

	/* (non-Javadoc)
	 * @see com.palmcommerce.funds.roo.protocol.IProtocolProcessHandler#p240005(com.palmcommerce.funds.protocol.impl.p2t.P240005)
	 */
	@Override
	public boolean p240005(P240005 p) throws ProtocolProcessException {
		return false;
	}

	/* (non-Javadoc)
	 * @see com.palmcommerce.funds.roo.protocol.IProtocolProcessHandler#p240006(com.palmcommerce.funds.protocol.impl.p2t.P240006)
	 */
	@Override
	public boolean p240006(P240006 p) throws ProtocolProcessException {
		return false;
	}

	/* (non-Javadoc)
	 * @see com.palmcommerce.funds.roo.protocol.IProtocolProcessHandler#t250001(com.palmcommerce.funds.protocol.impl.t2p.T250001)
	 */
	@Override
	public boolean t250001(T250001 t) throws ProtocolProcessException {
		return false;
	}

	/* (non-Javadoc)
	 * @see com.palmcommerce.funds.roo.protocol.IProtocolProcessHandler#t250002(com.palmcommerce.funds.protocol.impl.t2p.T250002)
	 */
	@Override
	public boolean t250002(T250002 t) throws ProtocolProcessException {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.palmcommerce.funds.roo.protocol.IProtocolProcessHandler#t250004(com.palmcommerce.funds.protocol.impl.t2p.T250004)
	 */
	@Override
	public boolean t250004(T250004 t) throws ProtocolProcessException {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.palmcommerce.funds.roo.protocol.IProtocolProcessHandler#t250005(com.palmcommerce.funds.protocol.impl.t2p.T250005)
	 */
	@Override
	public boolean t250005(T250005 t) throws ProtocolProcessException {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.palmcommerce.funds.roo.protocol.IProtocolProcessHandler#t250006(com.palmcommerce.funds.protocol.impl.t2p.T250006)
	 */
	@Override
	public boolean t250006(T250006 t) throws ProtocolProcessException {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.palmcommerce.funds.roo.protocol.IProtocolProcessHandler#t250007(com.palmcommerce.funds.protocol.impl.t2p.T250007)
	 */
	@Override
	public boolean t250007(T250007 t) throws ProtocolProcessException {
		return false;
	}

	/* (non-Javadoc)
	 * @see com.palmcommerce.funds.roo.protocol.IProtocolProcessHandler#t250008(com.palmcommerce.funds.protocol.impl.t2p.T250008)
	 */
	@Override
	public boolean t250008(T250008 t) throws ProtocolProcessException {
		return false;
	}

	/* (non-Javadoc)
	 * @see com.palmcommerce.funds.roo.protocol.IProtocolProcessHandler#t250009(com.palmcommerce.funds.protocol.impl.t2p.T250009)
	 */
	@Override
	public boolean t250009(T250009 t) throws ProtocolProcessException {
		return false;
	}

	/* (non-Javadoc)
	 * @see com.palmcommerce.funds.roo.protocol.IProtocolProcessHandler#t250010(com.palmcommerce.funds.protocol.impl.t2p.T250010)
	 */
	@Override
	public boolean t250010(T250010 t) throws ProtocolProcessException {
		// TODO Auto-generated method stub
		return false;
	}

	
	/* (non-Javadoc)
	 * @see com.palmcommerce.funds.roo.protocol.IProtocolProcessHandler#t250011(com.palmcommerce.funds.protocol.impl.t2p.T250011)
	 */
	@Override
	public boolean t250011(T250011 t) throws ProtocolProcessException {
		boolean ret=false;
		taskOfTrade.setT(t);
		ret=taskOfTrade.reconciliation(localSendingDir);

		
		
		if(ret){
			
			File f=new File(taskOfTrade.getFileName());
			ret=sending(f);
			t.response=new T250011.Response();
			if(ret){
				
				t.response.setCheckFileSize(String.valueOf(f.length()));
				t.response.setCode("0000");
				t.response.setReason(taskOfTrade.getFileShortName());
			}
			else{
				t.response.setCode("5001");
				t.response.setReason("reconciliation file not generated!");
				t.response.setCheckFileSize("0");
			}
			
			/* *
			 * create table save the (fromCode,filename,datatime,ret)
			 * trade 对账 文件 
			 * */
			
		}else{
			ret=false;
			t.response.setCode("5001");
			t.response.setReason("reconciliation file not generated!");
			t.response.setCheckFileSize("0");
		}
		
		
		
		
		TradeReconciliationStatics trs=new TradeReconciliationStatics();
		trs.setTradeCode(t.getHeader().from);
		trs.setFileName(taskOfTrade.getFileShortName());
		trs.setUpdateDatetime(new Date());
		trs.setIsUpdated(ret);
		try {
			trs.setBankDatetime(DateConvertor.parseBankTime(t.request.getAccountTime()));
		
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			tradeReconciliationStaticsService.deleteTradeReconciliationByBankDatetimeEqualsAndTradecodeEqauls(trs.getBankDatetime(), trs.getTradeCode());
			//(trs.getBankDatetime());
			//tradeReconciliationStatics.remove();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		
		try {
			tradeReconciliationStaticsService.saveTradeReconciliationStatics(trs);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//tradeReconciliationStaticsService.deleteTradeReconciliationStatics(trs);
		//存入数据库
		
	
		
		//taskExecutor.execute(task);
		return ret;
	}
	
	@Override
	public boolean t260004(T260004 t) throws ProtocolProcessException {
		try{
			String bankNumber = transactionMetaService.findBankNumberByCardNumber(t.request.getCardNumber());
			t.response.setBankNodeCode(bankNumber);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}

	}









}
