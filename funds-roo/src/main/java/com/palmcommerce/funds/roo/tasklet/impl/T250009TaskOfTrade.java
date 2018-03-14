/**
 * 
 */
package com.palmcommerce.funds.roo.tasklet.impl;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.palmcommerce.funds.protocol.covertor.exception.ProtocolConvertorException;
import com.palmcommerce.funds.protocol.impl.t2p.T250009;
import com.palmcommerce.funds.roo.model.TransactionRemitFile;
import com.palmcommerce.funds.roo.service.TransactionRemitFileService;
import com.palmcommerce.funds.roo.tasklet.TaskException;
import com.palmcommerce.funds.roo.tasklet.Tasklet;
import com.palmcommerce.funds.roo.util.DateConvertor;
import com.palmcommerce.funds.service.ProtocolStorageException;

/**
 * @author lottery
 *
 */
public class T250009TaskOfTrade extends Tasklet {
	
	T250009 t;
	Date bankDatatime;
	String transCode;

	@Autowired
	TransactionRemitFileService transactionRemitFileService;


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
		this.transCode=t.getHeader().to;
	}

	public T250009TaskOfTrade(T250009 t) {
		// TODO Auto-generated constructor stub
		this.t=t;
	}
	
	/* (non-Javadoc)
	 * @see com.palmcommerce.funds.roo.tasklet.Tasklet#run()
	 */
	@Override
	public void run() {
		// TODO Auto-generated method stub
		getEntiesFromDataBase();
	}
	

	/**
	 *  get the entry from database
	 *  从数据库查询结果
	 * @return
	 */
	private List<T250009Entry> getEntiesFromDataBase(){
		List<T250009Entry> entries=new LinkedList<T250009Entry>();
		List<TransactionRemitFile> files=null;
		files = transactionRemitFileService.findTransactionRemitFileEntriesByBankDateAndTransCode(bankDatatime, transCode);
		//银行流水号1|中心流水号1|用户编号1|用户姓名1|银行卡号|金额1| 交易时间1(YYYY-MM-DD HH:MM:SS) |8位银行代码(00000001)| 账务日期(YYYYMMDD ) |平台代号|类型(汇付)
		for(TransactionRemitFile file:files){
			T250009Entry t250009Entry=new T250009Entry("250009");
			T250009Entry.Request r = new T250009Entry.Request();
			t250009Entry.request=r;
			r.setSerialNumber(file.getPaymentSerial());
			r.setTradeSerial(file.getTradeSystemSerial());
			r.setUserId(file.getUserId());
			r.setUserName(file.getUserName());
			r.setAccount(file.getBankAccount());
			r.setAmount(String.valueOf(file.getAmount()));
			r.setTradeDateTime(DateConvertor.getTradeTimeByDate(file.getTradeDatetime()));
			r.setBankCode(file.getFromCode());
			r.setAccountDatetime(DateConvertor.getBankTimeByDate( file.getBankDatetime()));
			r.setTransCode(file.getToCode());
			r.setChargeType(String.valueOf(file.getTransactionType()));
			entries.add(t250009Entry);
		}
		return entries;
	}
	
	/**
	 * 把对象转换成文件
	 * @param filename
	 * @param entries
	 * @throws TaskException
	 */
	public void generateToFile(String filename,List<T250009Entry> entries)throws TaskException{
		if(entries==null)
			throw  new TaskException(TaskException.EXCEPTION_ENTRIES_EMPTY,"entries is empty");
		if(filename==null||"".equals(filename))
			throw new TaskException(TaskException.EXCEPTION_FILENAME_EMPTY,"file name must be set");
		File f=new File(getFileBase()+File.separator+filename);
		if(f.exists()){
			throw new TaskException(TaskException.EXCEPTION_FILE_EMPTY,"file alread exist");
		}
		if(!f.exists()){
			List<String> lines=new LinkedList<String>();
			for(T250009Entry entry:entries){
					// entry set the request
					try {
						entry.getHeader();
						entry.mashall(true);
						String line=entry.getRequestPacket();
						lines.add(line);
					} catch (ProtocolConvertorException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						throw new TaskException(TaskException.EXCEPTION_FILE_EMPTY,"file alread exist");
					}
			}
			try {
				FileUtils.writeLines(f, lines, "\r\n");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new TaskException(TaskException.EXCEPTION_FILE_EMPTY,"file alread exist");
			}
		}
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

	public TransactionRemitFileService getTransactionRemitFileService() {
		return transactionRemitFileService;
	}

	public void setTransactionRemitFileService(
			TransactionRemitFileService transactionRemitFileService) {
		this.transactionRemitFileService = transactionRemitFileService;
	}
}
