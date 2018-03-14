package com.palmcommerce.funds.roo.tasklet.impl;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.integration.Message;
import org.springframework.integration.MessageChannel;
import org.springframework.integration.sftp.config.SftpOutboundGatewayParser;
import org.springframework.integration.sftp.gateway.SftpOutboundGateway;
import org.springframework.integration.support.MessageBuilder;

import com.palmcommerce.funds.alert.model.TradeRecordStatics;
import com.palmcommerce.funds.protocol.covertor.exception.ProtocolConvertorException;
import com.palmcommerce.funds.protocol.impl.t2p.T250011;
import com.palmcommerce.funds.roo.model.TradeReconciliationStatics;
import com.palmcommerce.funds.roo.model.TransactionFile;
import com.palmcommerce.funds.roo.service.TradeReconciliationStaticsService;
import com.palmcommerce.funds.roo.service.TransactionFileService;
import com.palmcommerce.funds.roo.tasklet.TaskException;
import com.palmcommerce.funds.roo.tasklet.Tasklet;
import com.palmcommerce.funds.roo.util.DateConvertor;
import com.palmcommerce.funds.service.ProtocolStorageException;
import com.palmcommerce.funds.util.SpringHelper;

/**
 * @author lottery
 */
public class T250011TaskOfTrade  {
	
	T250011 t;
	Date bankDatatime;
	String transCode;

	@Autowired
	TransactionFileService transactionFileService;

	
	
	public T250011TaskOfTrade() {
		super();
		// TODO Auto-generated constructor stub
	}

	public T250011 getT() {
		return t;
	}

	public void setT(T250011 t) {
		this.t = t;
		try {
			this.bankDatatime=DateConvertor.parseBankTime(t.request.getAccountTime());
		} catch (ProtocolStorageException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.transCode=t.getHeader().from;
	}

	public T250011TaskOfTrade(T250011 t) {
		// TODO Auto-generated constructor stub
		this.t=t;
		setT(t);
	}
	

	public boolean reconciliation(String base){
		boolean ret=true;
		List<T250011Entry>  entries=getEntiesFromDataBase();
		
		try {
			generateToFile(base, entries);
		} catch (TaskException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ret=false;
		}
		
		return ret;
	}
	
	

	/**
	 *  get the entry from database
	 *  从本地数据库查数据
	 * @return
	 */
	private List<T250011Entry> getEntiesFromDataBase(){
		List<T250011Entry> entries=new LinkedList<T250011Entry>();
		List<TransactionFile> files=null;
		files = transactionFileService.findTransactionFileEntriesByBankDateAndTransCode(bankDatatime, transCode);
		//资金管理平台流水号1|银行流水号1|用户编号1|用户姓名1|金额1|账户1| 交易时间1(YYYY-MM-DD HH:MM:SS) |8银行代码(00000001)| 账务日期(YYYYMMDD )|类型(缴款)|
		for(TransactionFile file:files){
			T250011Entry t250011Entry=new T250011Entry("250011");
			T250011Entry.Request r = new T250011Entry.Request();
			t250011Entry.request=r;
			//r.setPaymentSerial();
			//r.setPaymentSerial();
			//r.setPaymentSerial(file.get)
			t250011Entry.setGlobalSerial(file.getGlobalserial());
			r.setSerialNumber(file.getPaymentSerial());
			r.setUserId(file.getUserId());
			r.setUserName(file.getUserName());
			r.setAmount(String.valueOf(file.getAmount()));
//			r.setAccount();
			r.setTradeDateTime(DateConvertor.getTradeTimeByDate(file.getTradeDatetime()));
			r.setBankCode(file.getFromCode());
			r.setAccountDatetime(DateConvertor.getBankTimeByDate( file.getBankDatetime()));
			r.setChargeType(String.valueOf(file.getTransactionType()));
			entries.add(t250011Entry);
		}
		return entries;
	}
	
	
	
	private String fileShortName;
	private String fileName;
	
	public String getFileShortName(){
		return fileShortName;
	}
	public String getFileName(){
		return fileName;
	}
	
	private String isNull(String s){
		if(s==null)s="";
		return s;
		
	}
	
	/**
	 * 把对象转换成文件
	 * @param filename
	 * @param entries
	 * @throws TaskException
	 */
	private String generateToFile(String base,List<T250011Entry> entries)throws TaskException{
		fileShortName=String.format("ChargeData.%s.%s", t.request.getAccountTime(),t.getHeader().from);
		fileName=String.format("%s"+File.separator+"%s", base,fileShortName);
		//File file=new File(fileName);
		if(entries==null)
			throw  new TaskException(TaskException.EXCEPTION_ENTRIES_EMPTY,"entries is empty");
		if(fileName==null||"".equals(fileName))
			throw new TaskException(TaskException.EXCEPTION_FILENAME_EMPTY,"file name must be set");
		File f=new File(fileName);
		if(f.exists()){
			f.delete();
			//throw new TaskException(TaskException.EXCEPTION_FILE_EMPTY,"file alread exist");
		}
		if(!f.exists()){
			List<String> lines=new LinkedList<String>();
			//lines.add("--------------------"+DateConvertor.getTradeTime()+"---------------------------");
			for(T250011Entry entry:entries){
					// entry set the request
					try {
						entry.getHeader();
						entry.request.amount=entry.request.amount+"|";
						entry.mashall(true);
						// amount|account|
						
						String line=isNull(entry.getGlobalSerial())+"|"+entry.getRequestPacket();
						lines.add(line);
					} catch (ProtocolConvertorException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						throw new TaskException(TaskException.EXCEPTION_FILE_EMPTY,"file alread exist");
					}
			}
			try {
				FileUtils.writeLines(f,lines, "\r\n");
			} catch (IOException e) {	
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new TaskException(TaskException.EXCEPTION_FILE_EMPTY,"file alread exist");
			}
		}
		
		return fileName;
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
	public TransactionFileService getTransactionFileService() {
		return transactionFileService;
	}
	public void setTransactionFileService(
			TransactionFileService transactionFileService) {
		this.transactionFileService = transactionFileService;
	}
}