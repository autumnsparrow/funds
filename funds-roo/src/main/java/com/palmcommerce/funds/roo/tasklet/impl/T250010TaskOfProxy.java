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
import com.palmcommerce.funds.protocol.impl.t2p.T250010;
import com.palmcommerce.funds.roo.model.TransactionBindFile;
import com.palmcommerce.funds.roo.model.TransactionMeta;
import com.palmcommerce.funds.roo.service.TransactionBindFileService;
import com.palmcommerce.funds.roo.service.TransactionMetaService;
import com.palmcommerce.funds.roo.tasklet.Tasklet;
import com.palmcommerce.funds.roo.util.DateConvertor;
import com.palmcommerce.funds.service.ProtocolStorageException;

/**
 * @author lottery
 *
 */
public class T250010TaskOfProxy extends Tasklet {
	
	T250010 t;
	Date bankDatatime;
	String bankCode;
	
	
	@Autowired
	TransactionBindFileService transactionBindFileService;
	@Autowired
	TransactionMetaService transactionMetaService;
	
	public T250010 getT() {
		return t;
	}

	public void setT(T250010 t) {
		this.t = t;
		try {
			this.bankDatatime=DateConvertor.parseBankTime(t.request.getBindingTime());
		} catch (ProtocolStorageException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.bankCode=t.getHeader().to;
	}

	public T250010TaskOfProxy() {
		super();
	}

	public T250010TaskOfProxy(T250010 t) {
		// TODO Auto-generated constructor stub
		this.t=t;
	}

	/* (non-Javadoc)
	 * @see com.palmcommerce.funds.roo.tasklet.Tasklet#run()
	 */
	@Override
	public void run() {
		// TODO Auto-generated method stub
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
		List<T250010Entry> entriesOfBankFile=getEntriesFromFile(this.getFileBase());
		// 2. read the database entries into memory.
		List<T250010Entry> entriesOfLocalDatabase=getEntiesFromDataBase();
		
		// bank don't have but the local has
		List<T250010Entry> moreEntries=new LinkedList<T250010Entry>();
		// bank has but the local don't
		List<T250010Entry> lessEntries=new LinkedList<T250010Entry>();
		// the bank and the local the same
		List<T250010Entry>  correctEntries=new LinkedList<T250010Entry>();
		// the bank the the local don't match
		List<T250010Entry>  wrongEntries=new LinkedList<T250010Entry>();
				
		// containers for the 
		HashMap<String,T250010Entry> comparedMap=new LinkedHashMap<String, T250010Entry>();
		
		for(T250010Entry entry:entriesOfBankFile){
			entry.state=T250010Entry.Entry_State.BANK;
			comparedMap.put(entry.request.getSerialNumber(), entry);
		}
				
		for(T250010Entry entry:entriesOfLocalDatabase){
			String key=entry.request.getSerialNumber();
			if(comparedMap.containsKey(key)){
				T250010Entry bankEntry=comparedMap.get(key);
				bankEntry.state=T250010Entry.Entry_State.BOTH;
				// compare the correct or wrong.
				if(bankEntry.request.compare(entry.request)){
					// correct
					correctEntries.add(entry);
				}else{
					wrongEntries.add(entry);
				}
			}else{
				
				entry.state=T250010Entry.Entry_State.PROXY;
				comparedMap.put(key, entry);
			}
		}
				
		for(T250010Entry entry:comparedMap.values()){
			if(entry.state==T250010Entry.Entry_State.BANK){
				lessEntries.add(entry);
			}
			else if (entry.state==T250010Entry.Entry_State.PROXY){
				moreEntries.add(entry);
			}
		}
	}
	
	/**
	 * 
	 *  get the entry from database
	 *  从本地数据库读取entry
	 * @return
	 */
	private List<T250010Entry> getEntiesFromDataBase(){
		List<T250010Entry> entries=new LinkedList<T250010Entry>();
		List<TransactionMeta> metas =null;
		metas = transactionMetaService.findChargedTransactionMetaEntriesByBankDateAndBankCode(bankDatatime, bankCode);
		//银行流水号1|8位银行代码(00000001)| 绑定类型（1位，0表示绑定，1表示解绑）|用户编号1|用户姓名1|帐户|账务日期(YYYYMMDD )|
		for(TransactionMeta meta:metas){
			T250010Entry t250010Entry=new T250010Entry("250010");
			T250010Entry.Request r = new T250010Entry.Request();
			t250010Entry.request=r;
			r.setSerialNumber(meta.getPaymentSerial());
			if("240002".equals(meta.getTranscode())){
				r.setBankCode(meta.getFromCode());
			}
			r.setBindType(String.valueOf(meta.getTransactionRecord().getBindingType()));
			r.setUserId(meta.getTransactionRecord().getUserId());
			r.setUserName(meta.getTransactionRecord().getUserName());
			r.setAccount(meta.getTransactionRecord().getBankAccount());
			r.setAccountDatetime(DateConvertor.getBankTimeByDate( meta.getTransactionRecord().getBankDatetime()));
			entries.add(t250010Entry);
		}
		return entries;
	}
	
	
	/**
	 * 
	 * conver the file content into list of entry object.
	 * 把银行传过来的文件转换成对象
	 * @return
	 */
	public List<T250010Entry> getEntriesFromFile(String filepath) throws ProtocolStorageException{
		
		List<T250010Entry> bankBindFile=new LinkedList<T250010Entry>();
		String fileName=String.format("%s"+File.separator+"AccountBind.%s.%s", getFileBase(),t.request.getBindingTime(),t.getHeader().to);
		File file=new File(fileName);
		try {
			if(file.exists()){
				// 1. read into the file
				List<String> lines=FileUtils.readLines(file, "utf-8");
				for(String line:lines){
					// 2. parse the line data into object.
					T250010Entry entry=new T250010Entry("250010","","",line,true);
					entry.getHeader();
					entry.unmashall(true);
					bankBindFile.add(entry);
			    }
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ProtocolConvertorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bankBindFile;
	}
	
	
	/**
	 * 
	 * 把转换好的对象存入数据库
	 * 银行流水号1|8位银行代码(00000001)| 绑定类型（1位，0表示绑定，1表示解绑）|用户编号1|用户姓名1|帐户|账务日期(YYYYMMDD )|
	 * @param entry
	 */
	public void saveEntry(T250010Entry entry){
		//3, set the jpa object 
		TransactionBindFile bindFile=new TransactionBindFile();
		bindFile.setPaymentSerial(entry.request.getSerialNumber());
		bindFile.setBankCode(entry.request.getBankCode());
		bindFile.setBindType(entry.request.getBindType());
		bindFile.setUserId(entry.request.getUserId());
		bindFile.setUserName(entry.request.getUserName());
		bindFile.setBankAccount(entry.request.getAccount());
		try {
			bindFile.setBankDatetime(DateConvertor.parseBankTime(entry.request.getAccountDatetime()));
		} catch (ProtocolStorageException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//4. save the jpa into the database.
		transactionBindFileService.saveTransactionBindFile(bindFile);
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

	public TransactionBindFileService getTransactionBindFileService() {
		return transactionBindFileService;
	}

	public void setTransactionBindFileService(
			TransactionBindFileService transactionBindFileService) {
		this.transactionBindFileService = transactionBindFileService;
	}

	public TransactionMetaService getTransactionMetaService() {
		return transactionMetaService;
	}

	public void setTransactionMetaService(
			TransactionMetaService transactionMetaService) {
		this.transactionMetaService = transactionMetaService;
	}
}
