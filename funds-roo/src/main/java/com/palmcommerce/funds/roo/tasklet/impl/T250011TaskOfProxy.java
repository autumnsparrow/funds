/**
 * @company Palm Lottery Information&Technology Co.,Ltd.
 *
 * @author  sparrow
 *
 * @date    Nov 4, 2013
 *
 */
package com.palmcommerce.funds.roo.tasklet.impl;



import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.beans.factory.annotation.Autowired;

import com.palmcommerce.funds.configuration.v2.ConfigurationManager;
import com.palmcommerce.funds.id.jmx.IdGeneratorJmxClient;
import com.palmcommerce.funds.protocol.ProtocolDriverManager;
import com.palmcommerce.funds.protocol.covertor.exception.ProtocolConvertorException;
import com.palmcommerce.funds.protocol.impl.p2t.P240003;
import com.palmcommerce.funds.protocol.impl.p2t.P240004;
import com.palmcommerce.funds.protocol.impl.p2t.P240005;
import com.palmcommerce.funds.protocol.impl.t2p.T250005;
import com.palmcommerce.funds.protocol.impl.t2p.T250011;
import com.palmcommerce.funds.protocol.trade.IStatus;
import com.palmcommerce.oss.jpa.model.OssReconciliationStatics;
import com.palmcommerce.funds.roo.model.TransactionFile;
import com.palmcommerce.funds.roo.model.TransactionMeta;
import com.palmcommerce.funds.roo.model.TransactionRecord;
import com.palmcommerce.funds.roo.protocol.impl.DefaultProtocolHandler;
import com.palmcommerce.funds.roo.protocol.impl.ScheduledTradeClientProtocolHandler;
import com.palmcommerce.oss.jpa.service.OssReconciliationStaticsService;
import com.palmcommerce.funds.roo.service.TransactionFileService;
import com.palmcommerce.funds.roo.service.TransactionMetaService;
import com.palmcommerce.funds.roo.tasklet.Tasklet;
import com.palmcommerce.funds.roo.tasklet.impl.T250011Entry.T250011EntryStateEnum;
import com.palmcommerce.funds.roo.tasklet.schedule.ScheduledTaskStateMachine.TaskState;
import com.palmcommerce.funds.roo.util.DateConvertor;
import com.palmcommerce.funds.service.ProtocolStorageException;

/**
 * @author lottery
 * 
 * the new algorithm of the compare proxy and bank:
 * 
 * 
 *  bank :
 *  	state 0 : charged
 *  	state 1:  canceled
 *  local:
 *  	state 0: charged.
 *  
 *  
 *  
 *  
 *  the result should be :
 *  
 *   entry state should be:
 *   state1 :	entry : in bank state 0 ,not in the local 
 *   state2:	entry : in bank state 0 ,in local state 0 , compared match
 *   state3:	entry : in bank state 0 ,in local state 0,  compared mismatch.
 *   state4:	entry : in bank state 1 ,in local state 0,  compared match
 *   state5:	entry : in bank state 1 , not in the local
 *   state6:	entry : not in bank state 0,1 ,in local state  0
 *   
 *   state2 --- >correct , saved
 *   state3 ---->wrong   , saved
 *   state1 ---> less    ,saved
 *   state4 ---> more    ,not saved
 *   state5 ---> more    , not saved.
 *   state6 ---> more    , not saved.
 *   
 *  	
 *  
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 *
 */
public class T250011TaskOfProxy {
	
	private static final Log logger=LogFactory.getLog(T250011TaskOfProxy.class);
	T250011 t;
	Date bankDatatime;
	String bankCode;
	
	
	Map<String,String> fileCharsets;

	@Autowired
	TransactionFileService transactionFileService;
	@Autowired
	TransactionMetaService transactionMetaService;
	@Autowired
	OssReconciliationStaticsService ossReconciliationStaticsService;
	
	@Autowired
	ScheduledTradeClientProtocolHandler scheduledTradeClientProtocolHandler;
	@Autowired 
	DefaultProtocolHandler defaultProtocolHandler;
	
	@Autowired
	IdGeneratorJmxClient idGeneratorJmxClient;
	

	@Autowired
	ConfigurationManager configurationManager;
	
	
//	@Autowired
//	OssBankService ossBankService;
	
	
	public T250011 getT() {
		return t;
	}

	public Map<String, String> getFileCharsets() {
		return fileCharsets;
	}

	public void setFileCharsets(Map<String, String> fileCharsets) {
		this.fileCharsets = fileCharsets;
	}

	public void setT(T250011 t) {
		this.t = t;
		try {
			this.bankDatatime=DateConvertor.parseBankTime(t.request.getAccountTime());
		} catch (ProtocolStorageException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.bankCode=t.getHeader().to;
	}

	public T250011TaskOfProxy() {
		super();
		// TODO Auto-generated constructor stub
	}




	
	
	private boolean isCharged(T250011Entry bankEntry){
		boolean isCharge=false;
		if("0".equals(bankEntry.request.getChargeType())){
			isCharge=true;
		}else if("1".equals(bankEntry.request.getChargeType())){
			isCharge=false;
		}
		return isCharge;
	}
	
	public void callBankReconciliation(T250011Entry entry){
		P240004 t=(P240004) ProtocolDriverManager.instance("240004", configurationManager
				.getServerRules().getProxy()
				.getNodeCode(), getBankCode());
		String serial=String.format("RC%s", entry.request.getSerialNumber().substring(2));
	
		t.request.setSerialNumber(serial);
		t.request.setPaymentSerialNumber(entry.request.getSerialNumber());
		t.request.setReverseAmount(entry.request.getAmount());
	
		t.request.setAccountTime(DateConvertor.getBankTime());
		
		t.request.setTransactionTime(DateConvertor.getTradeTime());
		t.request.setUserId(entry.request.getUserId());
		t.request.setUserName(entry.request.getUserName());
		
		//t.request.setCenterNumber(centerNumber)
		t.response=new P240004.Response();
		
		t.response.setCode(IStatus.STATE_OK);
		
		t.response.setReason("");
		t.response.setSerialNumber(t.request.getSerialNumber());
		t.response.setSeverseSerialNumber(t.request.getPaymentSerialNumber());
		try {
			defaultProtocolHandler.p240004(t);
		} catch (ProtocolStorageException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//t.response.setUserId(userId)
		
		
	}
	
	/**
	 * 
	 * 比较两个list，生成对账结果集 
	 * 
	 * 
	 */
	public boolean reconciliation() throws ProtocolStorageException{
		boolean ret=true;
		//
		//logger.info("clear the transaction file table in:"+t.request.getAccountTime());
		transactionFileService.deleteTransactionFilesByBankDatetimeEquals(DateConvertor.parseBankTime(t.request.getAccountTime()),bankCode);
		
		long correctSum=0;
		long correctAmount=0;
		long wrongSum=0;
		long wrongAmount=0;
		long lessSum=0;
		long lessAmount=0;
		long moreSum=0;
		long moreAmount=0;
		
		// 1. read the file into the memory.
		List<T250011Entry> entriesOfBankFile=getEntriesFromFile(t.response.getReason());
	//	List<T250011Entry> cancelEntriesOfBankFile=new LinkedList<T250011Entry>();
		// 2. read the database entries into memory.
		List<T250011Entry> entriesOfLocalDatabase=getEntiesFromDataBase();
		
		// bank don't have but the local has
		List<T250011Entry> moreEntries=new LinkedList<T250011Entry>();
		// bank has but the local don't
		List<T250011Entry> lessEntries=new LinkedList<T250011Entry>();
		// the bank and the local the same
		List<T250011Entry>  correctEntries=new LinkedList<T250011Entry>();
		// the bank the the local don't match
		List<T250011Entry>  wrongEntries=new LinkedList<T250011Entry>();
		
		// containers for the 
		// contains bank key
		HashMap<String,T250011Entry> comparedMap=new LinkedHashMap<String, T250011Entry>();
		
		// trim the same serial id in the bank files.
		for(T250011Entry entry:entriesOfBankFile){
			
			entry.state=T250011Entry.Entry_State.BANK;
			
				comparedMap.put(entry.request.getSerialNumber(), entry);
		
		
		
		}
		// checking the bank if contains the two entry both charged and canceled.
		
		
		for(T250011Entry entry:entriesOfLocalDatabase){
			String key=entry.request.getSerialNumber();
			if(comparedMap.containsKey(key)){
				T250011Entry bankEntry=comparedMap.get(key);
				bankEntry.state=T250011Entry.Entry_State.BOTH;
				bankEntry.getHeader().to=entry.getHeader().to;
				bankEntry.setGlobalSerial(entry.getGlobalSerial());
				// compare the correct or wrong.
				
				if(bankEntry.request.compare(entry.request)){
					correctEntries.add(entry);
					entry.entryState=T250011EntryStateEnum.StateCorrect;
					saveEntry(entry);
				}else{
					wrongEntries.add(entry);
					bankEntry.entryState=T250011EntryStateEnum.StateWrongBank;
					saveEntry(bankEntry);
				}
				comparedMap.remove(bankEntry);

			}
			/* if the bank don't contains it ,so not contains it .*/
				else{
					//state 6
					entry.state=T250011Entry.Entry_State.PROXY;
					entry.entryState=T250011EntryStateEnum.StateMore;
					moreEntries.add(entry);
				
				}
			}
		
			for(T250011Entry entry:comparedMap.values()){
				if(entry.state==T250011Entry.Entry_State.BANK){
					entry.getHeader().to=configurationManager.getTradeCodeByUserId(entry.request.getUserId());
				
					//state1
					lessEntries.add(entry);
					entry.entryState=T250011EntryStateEnum.StateLess;
					saveEntry(entry);
					
					// need add those record into the database.
					String bankSerial=entry.request.getSerialNumber();
					String proxySerial=idGeneratorJmxClient.findLocalSerialByRemoteSerial(bankSerial);
					// save as 24003.
					String tocode=configurationManager.getTradeCodeByUserId(entry.request.getUserId());
					P240003 p=(P240003)ProtocolDriverManager.instance("240003", entry.request.getBankCode(), tocode);
					p.response=new P240003.Response();
					
					
					p.response.setSerialNumber(bankSerial);
					p.setGlobalSerial(proxySerial);
					
					p.response.setCode("0000");
					p.response.setReason("SUCCEED");
					
					//p.request.setSerialNumber(bankSerial);
				
					//p.getHeader().from=entry.request.getBankCode();
					//p.getHeader().to=
				
					p.request.setAccountTime(entry.request.getAccountDatetime());
					p.request.setTransactionTime(entry.request.getTradeDateTime());
					
					// system reconciliation add
					p.request.setPaymentType("05");
					p.request.setPaymentAmount(entry.request.getAmount());
					p.request.setUserId(entry.request.userId);
					p.request.setUserName(entry.request.getUserName());
					
					try {
						defaultProtocolHandler.p240003(p);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					
					
				}
				
		}	

		
		// statics.
		correctSum=correctEntries.size();
		for(T250011Entry entry:correctEntries){
			correctAmount+=Long.parseLong(entry.request.getAmount());
			// correct 
			
		}
		
		wrongSum=wrongEntries.size();
		for(T250011Entry entry:wrongEntries){
			wrongAmount+=Long.parseLong(entry.request.getAmount());
		}
		
		lessSum=lessEntries.size();
		for(T250011Entry entry:lessEntries){
			lessAmount+=Long.parseLong(entry.request.getAmount());
		}
		
		moreSum=moreEntries.size();
		for(T250011Entry entry:moreEntries){
			moreAmount+=Long.parseLong(entry.request.getAmount());
		}
		
		
		OssReconciliationStatics o=new OssReconciliationStatics();
		o.setCorrectOrderAmount(BigDecimal.valueOf(correctAmount/100));
		o.setCorrectOrderSum(BigDecimal.valueOf(correctSum));
		o.setLessOrderAmount(BigDecimal.valueOf(lessAmount/100));
		o.setLessOrderSum(BigDecimal.valueOf(lessSum));
		o.setOverOrderAmount(BigDecimal.valueOf(moreAmount/100));
		o.setOverOrderSum(BigDecimal.valueOf(moreSum));
		o.setWrongOrderAmount(BigDecimal.valueOf(wrongAmount/100));
		o.setWrongOrderSum(BigDecimal.valueOf(wrongSum));
//		OssBank ossBank=ListEntryConvertor.getOneEntry(ossBankService.findOssBanksByBankIdEquals(bankCode));
		o.setFinanceName(bankCode);
		//o.setBankCode(bankCode);
		o.setTradeTime(t.request.getAccountTime());
		ossReconciliationStaticsService.deleteOssReconciliationStaticsByBankIdAndTradeTime(o.getFinanceName(), o.getTradeTime());
		ossReconciliationStaticsService.saveOssReconciliationStatics(o);
		return ret;

	};
	
	
	/**
	 * get the entry from database
	 * 从本地数据库读取entry
	 * @return
	 */
	private List<T250011Entry> getEntiesFromDataBase(){
		List<T250011Entry> entries=new LinkedList<T250011Entry>();
		List<TransactionMeta> metas =null;
		List<TransactionMeta> canceledMetas=null;
		HashMap<String,TransactionMeta> canceledMetasMap=new HashMap<String, TransactionMeta>();
		
//		int count=transactionMetaService.countTransactionMetaEntriesByBankDateAndBankCode(bankDatatime, bankCode);
//		int pageSize=100;
//		int page=0;
		metas = transactionMetaService.findChargedTransactionMetaEntriesByBankDateAndBankCode(bankDatatime, bankCode);
		canceledMetas=transactionMetaService.findCanceledTransactionMetaEntriesByBankDateAndBankCode(bankDatatime, bankCode);
		for(TransactionMeta meta:canceledMetas){
			if(meta.getTransactionRecord()!=null&&meta.getTransactionRecord().getCancelPaymentSerial()!=null)
				canceledMetasMap.put(meta.getTransactionRecord().getCancelPaymentSerial(),meta);
		}
		
		
		
		
		//银行流水号1|用户编号1|用户姓名1|金额1|账户1| 交易时间1(YYYY-MM-DD HH:MM:SS) |8银行代码(00000001)| 账务日期(YYYYMMDD )|类型(缴款)|
		for(TransactionMeta meta:metas){
			if(canceledMetasMap.containsKey(meta.getPaymentSerial()))
				continue;
			T250011Entry entry=new T250011Entry("250011");
			T250011Entry.Request r = new T250011Entry.Request();
			
			entry.request=r;
			
			//entry.setGlobalSerial(meta.getGlobalSerial());
			//(meta.getGlobalSerial());
		//	r.setSerialNumber();
			try {
				entry.getHeader().transcode=Integer.parseInt(meta.getTranscode());
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			entry.setGlobalSerial(meta.getGlobalSerial());
			r.setSerialNumber(meta.getPaymentSerial());
			
			if(meta.getTransactionRecord()==null){
				continue;
//				TransactionRecord record=new TransactionRecord();
//				record.setAmount(0);
//				record.setUserId("");
//				record.setUserName("");
//				record.setBankAccount("");
//				record.setTransactionMeta(meta);
//				record.setBankDatetime(meta.getTradeDateTime());
//				
//				record.setChargeType(-1);
//				
//				meta.setTransactionRecord(record);
				
			}
			//entry.meta=meta;
			r.setUserId(meta.getTransactionRecord().getUserId());
			r.setUserName(meta.getTransactionRecord().getUserName());
			r.setAmount(String.valueOf(meta.getTransactionRecord().getAmount()));
			//r.setAccount(meta.getTransactionRecord().getBankAccount());
			r.setTradeDateTime(DateConvertor.getTradeTimeByDate(meta.getTradeDateTime()));
			if("240003".equals(meta.getTranscode())){
				r.setBankCode(meta.getFromCode());
				entry.getHeader().to=meta.getToCode();
			} else if ("250004".equals(meta.getTranscode())){
				r.setBankCode(meta.getToCode());
				entry.getHeader().to=meta.getFromCode();
				
			}
			r.setAccountDatetime(DateConvertor.getBankTimeByDate(meta.getTransactionRecord().getBankDatetime()));
			/*
			else if("250004".equals(meta.getTranscode())||"250005".equals(meta.getTranscode())){
				r.setBankCode(meta.getToCode());
				entry.getHeader().to=meta.getFromCode();
			}*/
			
			if(meta.getTransactionRecord().getBankDatetime()!=null)
				r.setAccountDatetime(DateConvertor.getBankTimeByDate( meta.getTransactionRecord().getBankDatetime()));
			r.setChargeType(String.valueOf(meta.getTransactionRecord().getChargeType()));
			entries.add(entry);
		}
		return entries;
	}
	
	/**
	 * 
	 * conver the file content into list of entry object.
	 * 把银行传过来的文件转换成对象
	 * @return
	 */
	private List<T250011Entry> getEntriesFromFile(String filepath) throws ProtocolStorageException{
		List<T250011Entry> bankPayFile=new LinkedList<T250011Entry>();
		String fileName=filepath;//String.format("%s"+File.separator+Tasklet.receive_dir+File.separator+"ChargeData.%s.%s", getFileBase(),t.request.getAccountTime(),t.getHeader().to);
		File file=new File(fileName);
		try {
			if(file.exists()){
				// 1. read into the file
				String charset="utf-8";
				if(fileCharsets!=null&&fileCharsets.containsKey(bankCode)){
					charset=fileCharsets.get(bankCode);
				}
				logger.info("Reconciliation ------ >Reading [bankfile=" +filepath+
						",bankcode=," +bankCode+
						"charset="+charset+"]"
						);
				List<String> lines=FileUtils.readLines(file, charset);
				for(String line:lines){
					// 2. parse the line data into object.
					logger.info("---->\r\n"+line);
					T250011Entry entry=new T250011Entry("250011","","",line,true);
					entry.getHeader();
					entry.unmashall(true);
					bankPayFile.add(entry);
			    }
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ProtocolConvertorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bankPayFile;
	}
	
	/**
	 * 银行流水号1|用户编号1|用户姓名1|金额1|账户1| 交易时间1(YYYY-MM-DD HH:MM:SS) |8银行代码(00000001)| 账务日期(YYYYMMDD )|类型(缴款)
	 * 把转换好的对象存入数据库
	 * @param entry
	 */
	private void saveEntry(T250011Entry entry){
		//3, set the jpa object 
		TransactionFile payFile=new TransactionFile();
		if(entry.getGlobalSerial()!=null)
			payFile.setGlobalserial(entry.getGlobalSerial());
		
		payFile.setFlag(entry.getEntryState().ordinal());
		payFile.setPaymentSerial(entry.request.getSerialNumber());
		payFile.setUserId(entry.request.getUserId());
		payFile.setUserName(entry.request.getUserName());
		payFile.setAmount(Long.parseLong(entry.request.getAmount()));
		payFile.setToCode(entry.getHeader().to);
//		payFile.setAccount(entry.request.getAccount());
		try {
			payFile.setTradeDatetime(DateConvertor.parserTradeTime(entry.request.getTradeDateTime()));
		} catch (ProtocolStorageException e) {
			// TODO Auto-generated catch block 
			e.printStackTrace();	
		}
		payFile.setFromCode(entry.request.getBankCode());
		try {
			if(entry.request.getAccountDatetime()!=null)
				payFile.setBankDatetime(DateConvertor.parseBankTime(entry.request.getAccountDatetime()));
		} catch (ProtocolStorageException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		payFile.setTransactionType("0");
		
		
		//4. save the jpa into the database.
		transactionFileService.saveTransactionFile(payFile);
	}
	
	public void setBankDatatime(Date bankDatatime) {
		this.bankDatatime = bankDatatime;
	}

	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}
	
	public TransactionFileService getTransactionFileService() {
		return transactionFileService;
	}

	public void setTransactionFileService(
			TransactionFileService transactionFileService) {
		this.transactionFileService = transactionFileService;
	}

	public TransactionMetaService getTransactionMetaService() {
		return transactionMetaService;
	}

	public void setTransactionMetaService(
			TransactionMetaService transactionMetaService) {
		this.transactionMetaService = transactionMetaService;
	}

	public OssReconciliationStaticsService getOssReconciliationStaticsService() {
		return ossReconciliationStaticsService;
	}

	public void setOssReconciliationStaticsService(
			OssReconciliationStaticsService ossReconciliationStaticsService) {
		this.ossReconciliationStaticsService = ossReconciliationStaticsService;
	}
}
