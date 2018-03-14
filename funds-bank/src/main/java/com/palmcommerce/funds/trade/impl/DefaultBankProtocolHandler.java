/**
 * 
 */
package com.palmcommerce.funds.trade.impl;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.id.serial.AlphanumericGenerator;
import org.apache.commons.id.serial.PrefixedAlphanumericGenerator;
import org.apache.commons.id.serial.PrefixedLeftPaddedNumericGenerator;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.integration.Message;
import org.springframework.integration.MessageChannel;
import org.springframework.integration.support.MessageBuilder;

import com.palmcommerce.funds.connection.mina.protocol.AbstractServerSideProtocolProcessor;
import com.palmcommerce.funds.protocol.covertor.exception.ProtocolConvertorException;
import com.palmcommerce.funds.protocol.impl.Header;
import com.palmcommerce.funds.protocol.impl.IProtocol;
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
import com.palmcommerce.funds.protocol.trade.IBankProtocolHandler;
import com.palmcommerce.funds.protocol.trade.IStatus;
import com.palmcommerce.funds.protocol.util.SerialGenerator;
import com.palmcommerce.funds.roo.model.TransactionMeta;
import com.palmcommerce.funds.roo.service.TransactionMetaService;

import com.palmcommerce.funds.service.ProtocolStorageException;
import com.palmcommerce.funds.util.DateConvertor;
import com.palmcommerce.funds.util.SpringHelper;

/**
 * @author sparrow
 *
 */
public class DefaultBankProtocolHandler extends AbstractServerSideProtocolProcessor implements IBankProtocolHandler,IStatus {
	private static final Log logger=LogFactory.getLog(DefaultBankProtocolHandler.class);
	
	PrefixedAlphanumericGenerator  generator;
	
	public TransactionMetaService getTransactionMetaService() {
		return transactionMetaService;
	}

	public void setTransactionMetaService(
			TransactionMetaService transactionMetaService) {
		this.transactionMetaService = transactionMetaService;
	}



	TransactionMetaService transactionMetaService;
	
	/**
	 * 
	 */
	public DefaultBankProtocolHandler() {
		// TODO Auto-generated constructor stub
		generator=new PrefixedAlphanumericGenerator(SerialGenerator.getSerialPrefix(), true, 20);
	}
	
	/* 
	 	
	 * 
	 * (non-Javadoc)
	 * @see com.palmcommerce.funds.bank.server.IBankProtocolHandler#t250001(com.palmcommerce.funds.protocol.impl.t2p.T250001)
	 */
	public boolean t250001(T250001 t) throws ProtocolStorageException {
		// TODO Auto-generated method stub
		
		
		return false;
	}

	/* 
	 
	 String transcode="250002";
		String from="B0001";
		String to="T00002";
		//boolean isRequest=false;
		String packet="TS10001|lisi|1|532923197802050038|00045244534|2013-09-04 17:13:35|";
		String response="0000||TS10001|";
		
		用户的证件类型1、身份证；2、军官证；3、护照；4.士兵证、5警官证；6、港澳通行证；7、其他
	 * (non-Javadoc)
	 * @see com.palmcommerce.funds.bank.server.IBankProtocolHandler#t250002(com.palmcommerce.funds.protocol.impl.t2p.T250002)
	 */
	public boolean t250002(T250002 t) throws ProtocolStorageException {
		// TODO Auto-generated method stub
		t.request.getCenterNumber();
		t.request.getIdType();
		t.request.getIdNumber();
		t.request.getAccount();
		t.request.getTransactionTime();
		
		//TODO some stuff.
		
		t.response=new T250002.Response();
		t.response.setCode(STATE_OK);
		t.response.setCenterNumber(t.request.getCenterNumber());
		
		logger.info(t.toString());
		return true;
	}

	/* (non-Javadoc)
	 * @see com.palmcommerce.funds.bank.server.IBankProtocolHandler#t250004(com.palmcommerce.funds.protocol.impl.t2p.T250004)
	 */
	public boolean t250004(T250004 t) throws ProtocolStorageException {
		// TODO Auto-generated method stub
		t.request.getAccount();
		t.request.getAccountTime();
		t.request.getCenterNumber();
		t.request.getPaymentAmount();
		t.request.getPaymentType();
		t.request.getTransactionTime();
		t.request.getUserId();
		t.request.getUserName();
		
		//TODO some stuff
		
		t.response=new T250004.Response();
		t.response.setAccount(t.request.getAccount());
		t.response.setCenterNumber(t.request.getCenterNumber());
		t.response.setCode(STATE_OK);
		t.response.setReason(null);
		t.response.setSerialNumber(generator.nextStringIdentifier());
		t.response.setUserId(t.request.getUserId());
		t.response.setUserName(t.request.getUserName());
		
		
		logger.info(t.toString());
		return true;
	}

	/* (non-Javadoc)
	 * @see com.palmcommerce.funds.bank.server.IBankProtocolHandler#t250005(com.palmcommerce.funds.protocol.impl.t2p.T250005)
	 */
	public boolean t250005(T250005 t) throws ProtocolStorageException {
		// TODO Auto-generated method stub
		t.request.getAccountTime();
		t.request.getCenterNumber();
		t.request.getPaymentAmount();
		t.request.getRealTimePaymentNumber();
		t.request.getTransactionTime();
		t.request.getUserId();
		t.request.getUserName();
		
		
		//TODO
		
		t.response=new T250005.Response();
		t.response.setCenterNumber(t.request.getCenterNumber());
		t.response.setCenterReverseNumber(t.request.getRealTimePaymentNumber());
		t.response.setCode(STATE_OK);
		t.response.setSerialNumber(generator.nextStringIdentifier());
		t.response.setUserId(t.request.getUserId());
		t.response.setUserName(t.request.getUserName());
		
		logger.info(t.toString());
		return true;
	}

	/* (non-Javadoc)
	 * @see com.palmcommerce.funds.bank.server.IBankProtocolHandler#t250006(com.palmcommerce.funds.protocol.impl.t2p.T250006)
	 */
	public boolean t250006(T250006 t) throws ProtocolStorageException {
		// TODO Auto-generated method stub
		t.request.getAccountTime();
		t.request.getCenterNumber();
		t.request.getTransactionTime();
		t.request.getUserId();
		t.request.getUserName();
		
		//TODO
		
		
		t.response=new T250006.Response();
		t.response.setCenterNumber(t.request.getCenterNumber());
		t.response.setPaymentAmount("500");
		t.response.setPaymentType("01");
		t.response.setCode(STATE_OK);
		t.response.setSerialNumber(generator.nextStringIdentifier());
		t.response.setUserId(t.request.getUserId());
		t.response.setUserName(t.request.getUserName());
		logger.info(t.toString());
		
		return true;
	}

	/* (non-Javadoc)
	 * @see com.palmcommerce.funds.bank.server.IBankProtocolHandler#t250007(com.palmcommerce.funds.protocol.impl.t2p.T250007)
	 */
	public boolean t250007(T250007 t) throws ProtocolStorageException {
		// TODO Auto-generated method stub
		t.request.getAccount();
		t.request.getAccountTime();
		t.request.getCenterNumber();
		t.request.getRemittanceAmount();
		t.request.getTransactionTime();
		t.request.getUserId();
		t.request.getUserName();
		
		//TODO
		
		
		t.response=new T250007.Response();
		t.response.setAccount(t.request.getAccount());
		t.response.setCenterNumber(t.request.getCenterNumber());
		t.response.setCode(STATE_OK);
		t.response.setSerialNumber(generator.nextStringIdentifier());
		t.response.setUserId(t.request.getUserId());
		t.response.setUserName(t.request.getUserName());
		logger.info(t.toString());
		return true;
	}

	/* (non-Javadoc)
	 * @see com.palmcommerce.funds.bank.server.IBankProtocolHandler#t250008(com.palmcommerce.funds.protocol.impl.t2p.T250008)
	 */
	public boolean t250008(T250008 t) throws ProtocolStorageException {
		// TODO Auto-generated method stub
		t.request.getAccountTime();
		t.request.getFileName();
		t.request.getFileSize();
		
		t.response=new T250008.Response();
		t.response.setCheckFileSize(t.request.getFileSize());
		t.response.setCode(STATE_OK);
		t.response.setReason(null);
		logger.info(t.toString());
		return true;
	}

	/* (non-Javadoc)
	 * @see com.palmcommerce.funds.bank.server.IBankProtocolHandler#t250009(com.palmcommerce.funds.protocol.impl.t2p.T250009)
	 */
	public boolean t250009(T250009 t) throws ProtocolStorageException {
		// TODO Auto-generated method stub
		t.request.getAccountTime();
		
		//TODO
		
		t.response=new T250009.Response();
		t.response.setCheckFileSize("802941");
		t.response.setCode(STATE_OK);
		
		logger.info(t.toString());
		return true;
	}

	/* (non-Javadoc)
	 * @see com.palmcommerce.funds.bank.server.IBankProtocolHandler#t250010(com.palmcommerce.funds.protocol.impl.t2p.T250010)
	 */
	public boolean t250010(T250010 t) throws ProtocolStorageException {
		// TODO Auto-generated method stub
		
		t.request.getBindingTime();
		
		
		t.response=new T250010.Response();
		t.response.setCheckFileSize("5092929");
		t.response.setCode(STATE_OK);
		
	
		logger.info(t.toString());
		return true;
	}

	/* (non-Javadoc)
	 * @see com.palmcommerce.funds.bank.server.IBankProtocolHandler#t250011(com.palmcommerce.funds.protocol.impl.t2p.T250011)
	 */
	public boolean t250011(T250011 t) throws ProtocolStorageException {
		// TODO Auto-generated method stub
		t.request.getAccountTime();
		
		
		/***
		 * 测试对账   时间段  8-10 频率 20 秒
		 *   1.获取数据  (T250011TaskOfTrade -->getEntiesFromDataBase方法)
		 *   2.把获取的数据集合  转换成文件(   -->generateToFile(String base,List<T250011Entry> entries)方法)
		 * **/
		
		
		
		
		//TODO
		t.response=new T250011.Response();
		t.response=new T250011.Response();
		
		String from=t.getHeader().from;
	//	String base=getGeneratedFileBase(t);
		//String fileName;
		try {
			List<T250011Entry> entries= getEntiesFromDataBase(t);
			 generateToFile(t, entries);
			boolean ret= put();
			if(ret){
				t.response.setCode(STATE_OK);
				t.response.setReason(fileShortName);	
				t.response.setCheckFileSize(String.valueOf(new File(this.fileName).length()));
			}else{
				t.response.setCode("0001");
				t.response.setReason("");
				t.response.setCheckFileSize("0");
				
			}
//			t.response.setCode("0000");
//			t.response.setReason("ChargeData."+com.palmcommerce.funds.util.DateConvertor.getBankTime()+"."+t.getHeader().to);
//			t.response.setCheckFileSize(String.valueOf(10389));
			/*t.response.setCheckFileSize("89292");
			t.response.setCode(STATE_OK);*/
			logger.info(t.toString());
			
//			sendFile(fileName);//从银行   往  平台 发送对账 文件
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}

	
	
	String fileShortName;
	String fileName;
	T250011 t;
	Date bankDatatime;
	String transCode;
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	private String isNull(String s){
		if(s==null)s="";
		return s;
	}

	/**
	 *  get the entry from database
	 *  从本地数据库查数据
	 * @return
	 */
	public List<T250011Entry> getEntiesFromDataBase(T250011 t){
		List<T250011Entry> entries=new LinkedList<T250011Entry>();
		//List<TranscationFile> files=null;
		List<TransactionMeta> metas=null;
		try {
			metas=transactionMetaService.findTransactionMetaEntriesByBankDateAndBankCode(DateConvertor.parseBankTime(t.request.getAccountTime()),t.getHeader().to);
		} catch (ProtocolStorageException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	//	files = transactionMetaService//findTransactionFileEntriesByBankDateAndTransCode(t.request.getAccountTime(), t.getHeader().to);
	//	资金管理平台流水号1|银行流水号1|用户编号1|用户姓名1|金额1|账户1| 交易时间1(YYYY-MM-DD HH:MM:SS) |8银行代码(00000001)| 账务日期(YYYYMMDD )|类型(缴款)|
		for(TransactionMeta meta:metas){
			T250011Entry t250011Entry=new T250011Entry("250011");
			T250011Entry.Request r = new T250011Entry.Request();
			t250011Entry.request=r;
			
			/*t250011Entry.setGlobalSerial(file.getGlobalserial());
			r.setSerialNumber(file.getPaymentSerial());
			r.setUserId(file.getUserId());
			r.setUserName(file.getUserName());
			r.setAmount(String.valueOf(file.getAmount()));
			r.setTradeDateTime(DateConvertor.getTradeTimeByDate(file.getTradeDatetime()));
			r.setBankCode(file.getFromCode());
			r.setAccountDatetime(DateConvertor.getBankTimeByDate( file.getBankDatetime()));
			r.setChargeType(String.valueOf(file.getTransactionType()));*/
			//t.setGlobalSerial(meta.getGlobalserial());
			t250011Entry.setGlobalSerial(meta.getGlobalSerial());
			r.setSerialNumber(meta.getPaymentSerial());
			r.setUserId(meta.getTransactionRecord().getUserId());
			String username=meta.getTransactionRecord().getUserName();
			r.setUserName(username);
			r.setAmount(String.valueOf(meta.getTransactionRecord().getAmount()));
			//r.setAccount(isNull(meta.getTransactionRecord().getBankAccount()));
			r.setTradeDateTime(DateConvertor.getTradeTimeByDate(meta.getTradeDateTime()));
			r.setBankCode(t.getHeader().to);
			r.setAccountDatetime(DateConvertor.getBankTimeByDate(meta.getTransactionRecord().getBankDatetime()));
			r.setChargeType("0");
			entries.add(t250011Entry);
		}
		return entries;
	}
	/**
	 * 把对象转换成文件
	 * @param filename
	 * @param entries
	 * @throws Exception 
	 * @throws TaskException
	 */
	public String generateToFile(T250011 t,List<T250011Entry> entries) throws Exception{
		fileShortName=String.format("ChargeData.%s.%s", t.request.getAccountTime(),t.getHeader().to);
		fileName=String.format("%s"+File.separator+"%s", localDir,fileShortName);
		//File file=new File(fileName);
		if(entries==null)
			throw  new Exception("entries is empty");
		if(fileName==null||"".equals(fileName))
			throw  new Exception("entries is empty");
		File f=new File(fileName);
		if(f.exists()){
			f.delete();
			//throw new TaskException(TaskException.EXCEPTION_FILE_EMPTY,"file alread exist");
		}
		if(!f.exists()){
			List<String> lines=new LinkedList<String>();
			for(T250011Entry entry:entries){
					// entry set the request
					try {
						entry.getHeader();
						entry.mashall(true);
						//String line=entry.getGlobalSerial()+"|"+entry.getRequestPacket();
						String line=entry.getRequestPacket();
						
						lines.add(line);
					} catch (ProtocolConvertorException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}
			try {
				FileUtils.writeLines(f,lines, "\r\n");
			} catch (IOException e) {	
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return fileName;
	}
	@Value("${local.directory}")
	private String localDir;
	
	
	public MessageChannel getProxySendingFirstOutputChannel() {
		return proxySendingFirstOutputChannel;
	}

	public void setProxySendingFirstOutputChannel(
			MessageChannel proxySendingFirstOutputChannel) {
		this.proxySendingFirstOutputChannel = proxySendingFirstOutputChannel;
	}



	MessageChannel proxySendingFirstOutputChannel;
	
	
	
	public boolean put(){
		File file=new File(fileName);
		boolean  ret=false;
		if(file.exists()){
			//String inputChannel=null;
			final Message<File> sending = MessageBuilder.withPayload(file).build();
			ret=proxySendingFirstOutputChannel.send(sending);
			
		}
		return ret;
	}
	
	
	
	
	
	public String getFileShortName() {
		return fileShortName;
	}
	public void setFileShortName(String fileShortName) {
		this.fileShortName = fileShortName;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public T250011 getT() {
		return t;
	}
	public void setT(T250011 t) {
		this.t = t;
	}
	public Date getBankDatatime() {
		return bankDatatime;
	}
	public void setBankDatatime(Date bankDatatime) {
		this.bankDatatime = bankDatatime;
	}
	public String getTransCode() {
		return transCode;
	}
	public void setTransCode(String transCode) {
		this.transCode = transCode;
	}
	
	
	
	/* (non-Javadoc)
	 * @see com.palmcommerce.funds.trade.impl.AbstractServerSideProtocolProcessor#handle(com.palmcommerce.funds.protocol.impl.IProtocol)
	 */
	@Override
	public void handle(IProtocol protocol) throws ProtocolStorageException {
		// TODO Auto-generated method stub
		Header header = protocol.getHeader();

		// header.getHeader().transcode;
		switch (header.transcode) {

		case 250001: {
			T250001 p = (T250001) protocol;

			t250001(p);

		}

			break;

		case 250002: {
			T250002 p = (T250002) protocol;

			t250002(p);

		}
			// paymentService.p240001((P240001)header);
			break;

		case 250004: {

			T250004 p = (T250004) protocol;

			t250004(p);

		}
			// paymentService.p240001((P240001)header);
			break;

		case 250005: {

			T250005 p = (T250005) protocol;

			t250005(p);

		}
			// paymentService.p240001((P240001)header);
			break;

		case 250006: {

			T250006 p = (T250006) protocol;

			t250006(p);

		}
			// paymentService.p240001((P240001)header);
			break;

		case 250007: {

			T250007 p = (T250007) protocol;

			t250007(p);

		}
			// paymentService.p240001((P240001)header);
			break;

		case 250008: {

			T250008 p = (T250008) protocol;

			t250008(p);

		}
			// paymentService.p240001((P240001)header);
			break;

		case 250009: {

			T250009 p = (T250009) protocol;

			t250009(p);

		}
			// paymentService.p240001((P240001)header);
			break;

		case 250010: {

			T250010 p = (T250010) protocol;

			t250010(p);

		}
			// paymentService.p240001((P240001)header);
			break;

		case 250011: {

			T250011 p = (T250011) protocol;

			t250011(p);

		}
			// paymentService.p240001((P240001)header);
			break;

		default:
			break;
		}
		
	}

}
