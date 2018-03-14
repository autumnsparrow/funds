/**
 * 
 */
package com.palmcommerce.funds.roo.tasklet.impl;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.palmcommerce.funds.protocol.covertor.exception.ProtocolConvertorException;
import com.palmcommerce.funds.protocol.impl.t2p.T250009;
import com.palmcommerce.funds.roo.model.TransactionMeta;
import com.palmcommerce.funds.roo.model.TransactionRemitFile;
import com.palmcommerce.funds.roo.service.TransactionMetaService;
import com.palmcommerce.funds.roo.service.TransactionRemitFileService;
import com.palmcommerce.funds.roo.tasklet.Tasklet;
import com.palmcommerce.funds.roo.util.DateConvertor;

import com.palmcommerce.funds.service.ProtocolStorageException;

import com.palmcommerce.oss.jpa.model.OssReconciliationRemitStatics;

import com.palmcommerce.oss.jpa.service.OssReconciliationRemitStaticsService;

/**
 * @author lottery
 *
 */
public class T250009TaskOfProxy extends Tasklet {
	
	T250009 t;
	Date bankDatatime;
	String bankCode;

	@Autowired
	TransactionRemitFileService transactionRemitFileService;
	@Autowired
	TransactionMetaService transactionMetaService;
//	@Autowired
//	OssBankService ossBankService;
	@Autowired
	OssReconciliationRemitStaticsService ossReconciliationRemitStaticsService;
	
	
	public T250009 getT() {
		return t;
	}

	public void setT(T250009 t) {
		this.t = t;
		try {
			this.bankDatatime=DateConvertor.parseBankTime(t.request.getAccountTime());
		} catch (ProtocolStorageException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.bankCode=t.getHeader().to;
	}
	
	public T250009TaskOfProxy() {
		super();
	}

	public T250009TaskOfProxy(T250009 t) {
		// TODO Auto-generated constructor stub
		this.t=t;
	}
	
	/* (non-Javadoc)
	 * @see com.palmcommerce.funds.roo.tasklet.Tasklet#run()
	 */
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			compileLists();
		} catch (ProtocolStorageException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 
	 * 比较两个list，生成对账结果集 
	 * @throws ProtocolStorageException 
	 * 
	 * 
	 */
	public void compileLists() throws ProtocolStorageException{
		
		long correctSum=0;
		long correctAmount=0;
		long wrongSum=0;
		long wrongAmount=0;
		long lessSum=0;
		long lessAmount=0;
		long moreSum=0;
		long moreAmount=0;
		
		
		// 1. read the file into the memory.
		List<T250009Entry> entriesOfBankFile=getEntriesFromFile(this.getFileBase());
		// 2. read the database entries into memory.
		List<T250009Entry> entriesOfLocalDatabase=getEntiesFromDataBase();
		
		// bank don't have but the local has
		List<T250009Entry> moreEntries=new LinkedList<T250009Entry>();
		// bank has but the local don't
		List<T250009Entry> lessEntries=new LinkedList<T250009Entry>();
		// the bank and the local the same
		List<T250009Entry>  correctEntries=new LinkedList<T250009Entry>();
		// the bank the the local don't match
		List<T250009Entry>  wrongEntries=new LinkedList<T250009Entry>();
		
		// containers for the 
		HashMap<String,T250009Entry> comparedMap=new LinkedHashMap<String, T250009Entry>();
		
		for(T250009Entry entry:entriesOfBankFile){
			entry.state=T250009Entry.Entry_State.BANK;
			comparedMap.put(entry.request.getSerialNumber(), entry);
		}
		
		for(T250009Entry entry:entriesOfLocalDatabase){
			String key=entry.request.getSerialNumber();
			if(comparedMap.containsKey(key)){
				T250009Entry bankEntry=comparedMap.get(key);
				bankEntry.state=T250009Entry.Entry_State.BOTH;
				// compare the correct or wrong.
				if(bankEntry.request.compare(entry.request)){
					// correct
					correctEntries.add(entry);
				}else{
					wrongEntries.add(entry);
				}
			
			}else{
				entry.state=T250009Entry.Entry_State.PROXY;
				comparedMap.put(key, entry);
			}
		}
		
		for(T250009Entry entry:comparedMap.values()){
			if(entry.state==T250009Entry.Entry_State.BANK){
				lessEntries.add(entry);
			}
			else if (entry.state==T250009Entry.Entry_State.PROXY){
				moreEntries.add(entry);
			}
		}
		
		// statics.
		correctSum=correctEntries.size();
		for(T250009Entry entry:correctEntries){
			correctAmount+=Long.parseLong(entry.request.getAmount());
		}
				
		wrongSum=wrongEntries.size();
		for(T250009Entry entry:wrongEntries){
			wrongAmount+=Long.parseLong(entry.request.getAmount());
		}
		
		lessSum=lessEntries.size();
		for(T250009Entry entry:lessEntries){
			lessAmount+=Long.parseLong(entry.request.getAmount());
		}
				
		moreSum=moreEntries.size();
		for(T250009Entry entry:moreEntries){
			moreAmount+=Long.parseLong(entry.request.getAmount());
		}	
		
		
		OssReconciliationRemitStatics o=new OssReconciliationRemitStatics();
		o.setCorrectOrderAmout(correctAmount/100000);
		o.setCorrectOrderSum(correctSum);
		o.setLessOrderAmout(lessAmount/100000);
		o.setLessOrderSum(lessSum);
		o.setOverOrderAmout(moreAmount/100000);
		o.setOverOrderSum(moreSum);
		o.setWrongOrderAmout(wrongAmount);
		o.setWrongOrderSum(wrongSum);
//		OssBank ossBank=ListEntryConvertor.getOneEntry(ossBankService.findOssBanksByBankIdEquals(bankCode));
		o.setFinanceName(bankCode);
		o.setTradeTime(t.request.getAccountTime());
		ossReconciliationRemitStaticsService.saveOssReconciliationRemitStatics(o);
	}
	

	/**
	 * 
	 *  get the entry from database
	 *  从本地数据库读取entry
	 * @return
	 */
	private List<T250009Entry> getEntiesFromDataBase(){
		List<T250009Entry> entries=new LinkedList<T250009Entry>();
		List<TransactionMeta> metas =null;
		metas = transactionMetaService.findChargedTransactionMetaEntriesByBankDateAndBankCode(bankDatatime, bankCode);
		//银行流水号1|中心流水号1|用户编号1|用户姓名1|银行卡号|金额1| 交易时间1(YYYY-MM-DD HH:MM:SS) |8位银行代码(00000001)| 账务日期(YYYYMMDD ) |平台代号|类型(汇付)
		for(TransactionMeta meta:metas){
			T250009Entry t250009Entry=new T250009Entry("250009");
			T250009Entry.Request r = new T250009Entry.Request();
			t250009Entry.request=r;
			r.setSerialNumber(meta.getPaymentSerial());
			r.setTradeSerial(meta.getTradeSerial());
			r.setUserId(meta.getTransactionRecord().getUserId());
			r.setUserName(meta.getTransactionRecord().getUserName());
			r.setAccount(meta.getTransactionRecord().getBankAccount());
			r.setAmount(String.valueOf(meta.getTransactionRecord().getAmount()));
			r.setTradeDateTime(DateConvertor.getTradeTimeByDate(meta.getTradeDateTime()));
			if("250007".equals(meta.getTranscode())){
				r.setBankCode(meta.getToCode());
			}else if("250008".equals(meta.getTranscode())){
				r.setBankCode(meta.getToCode());
			}
			r.setAccountDatetime(DateConvertor.getBankTimeByDate( meta.getTransactionRecord().getBankDatetime()));
			r.setChargeType(String.valueOf(meta.getTransactionRecord().getChargeType()));
			entries.add(t250009Entry);
		}
		
		return entries;
	}

	/**
	 * 
	 * conver the file content into list of entry object.
	 * 把银行传过来的文件转换成对象
	 * @return
	 */
	public List<T250009Entry> getEntriesFromFile(String filepath) throws ProtocolStorageException{
		
		List<T250009Entry> bankRemitFileFile=new LinkedList<T250009Entry>();
		String fileName=String.format("%s"+File.separator+"EncashAccountData.%s.%s", getFileBase(),t.request.getAccountTime(),t.getHeader().to);
		File file=new File(fileName);
		
		try {
			if(file.exists()){
				// 1. read into the file
				List<String> lines=FileUtils.readLines(file, "utf-8");
				for(String line:lines){
					
					// 2. parse the line data into object.
					T250009Entry entry=new T250009Entry("250009","","",line,true);
					entry.getHeader();
					entry.unmashall(true);
					bankRemitFileFile.add(entry);
			    }
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ProtocolConvertorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bankRemitFileFile;
	}
	

	/**
	 * 把转换好的对象存入数据库
	 * 银行流水号1|中心流水号1|用户编号1|用户姓名1|银行卡号|金额1| 交易时间1(YYYY-MM-DD HH:MM:SS) |8位银行代码(00000001)| 账务日期(YYYYMMDD ) |平台代号|类型(汇付)
	 * @param entry
	 */
	public void saveEntry(T250009Entry entry){
		//3, set the jpa object 
		TransactionRemitFile remitFile=new TransactionRemitFile();
		remitFile.setPaymentSerial(entry.request.getSerialNumber());
		remitFile.setTradeSystemSerial(entry.request.getTradeSerial());
		remitFile.setUserId(entry.request.getUserId());
		remitFile.setUserName(entry.request.getUserName());
		remitFile.setBankAccount(entry.request.getAccount());
		remitFile.setAmount(Integer.parseInt(entry.request.getAmount()));
		try {
			remitFile.setTradeDatetime(DateConvertor.parserTradeTime(entry.request.getTradeDateTime()));
		} catch (ProtocolStorageException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		remitFile.setFromCode(entry.request.getBankCode());
		try {
			remitFile.setBankDatetime(DateConvertor.parseBankTime(entry.request.getAccountDatetime()));
		} catch (ProtocolStorageException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		remitFile.setToCode(entry.request.getTransCode());
		remitFile.setTransactionType(entry.request.getChargeType());

		//4. save the jpa into the database.
		transactionRemitFileService.saveTransactionRemitFile(remitFile);
	}
	
	public Date getBankDatatime() {
		return bankDatatime;
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
	
	public TransactionRemitFileService getTransactionRemitFileService() {
		return transactionRemitFileService;
	}

	public void setTransactionRemitFileService(
			TransactionRemitFileService transactionRemitFileService) {
		this.transactionRemitFileService = transactionRemitFileService;
	}

	public TransactionMetaService getTransactionMetaService() {
		return transactionMetaService;
	}

	public void setTransactionMetaService(
			TransactionMetaService transactionMetaService) {
		this.transactionMetaService = transactionMetaService;
	}

	
	public OssReconciliationRemitStaticsService getOssReconciliationRemitStaticsService() {
		return ossReconciliationRemitStaticsService;
	}

	public void setOssReconciliationRemitStaticsService(
			OssReconciliationRemitStaticsService ossReconciliationRemitStaticsService) {
		this.ossReconciliationRemitStaticsService = ossReconciliationRemitStaticsService;
	}
	
}