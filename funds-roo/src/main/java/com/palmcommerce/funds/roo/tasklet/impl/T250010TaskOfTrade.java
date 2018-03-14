
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
import com.palmcommerce.funds.protocol.impl.t2p.T250010;
import com.palmcommerce.funds.roo.model.TransactionBindFile;
import com.palmcommerce.funds.roo.service.TransactionBindFileService;
import com.palmcommerce.funds.roo.tasklet.TaskException;
import com.palmcommerce.funds.roo.tasklet.Tasklet;
import com.palmcommerce.funds.roo.util.DateConvertor;
import com.palmcommerce.funds.service.ProtocolStorageException;

/**
 * @author lottery
 *
 */
public class T250010TaskOfTrade extends Tasklet {
	
	T250010 t;
	Date bankDatatime;
	String transCode;
	
	@Autowired
	TransactionBindFileService transactionBindFileService;

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
		this.transCode=t.getHeader().to;
	}
	
	public T250010TaskOfTrade(T250010 t) {
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
	 *  get the entry from database
	 *  从本地数据库查数据
	 * @return
	 */
	private List<T250010Entry> getEntiesFromDataBase(){
		List<T250010Entry> entries=new LinkedList<T250010Entry>();
		List<TransactionBindFile> files=null;
		files = transactionBindFileService.findTransactionBindFileEntriesByBankDateAndTransCode(bankDatatime, transCode);
		//银行流水号1|8位银行代码(00000001)| 绑定类型（1位，0表示绑定，1表示解绑）|用户编号1|用户姓名1|帐户|账务日期(YYYYMMDD )|
		for(TransactionBindFile file:files){
			T250010Entry t250010Entry=new T250010Entry("250010");
			T250010Entry.Request r = new T250010Entry.Request();
			t250010Entry.request=r;
			r.setSerialNumber(file.getPaymentSerial());
			r.setBankCode(file.getBankCode());
			r.setBindType(file.getBindType());
			r.setUserId(file.getUserId());
			r.setUserName(file.getUserName());
			r.setAccount(file.getBankAccount());
			r.setAccountDatetime(DateConvertor.getBankTimeByDate( file.getBankDatetime()));
			entries.add(t250010Entry);
		}
		return entries;
	}
	
	/**
	 * 
	 * 把对象转换成文件
	 * @param filename
	 * @param entries
	 * @throws TaskException
	 */
	public void generateToFile(String filename,List<T250010Entry> entries)throws TaskException{
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
			for(T250010Entry entry:entries){
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
				FileUtils.writeLines(f,lines, "\r\n");
			} catch (IOException e) {	
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new TaskException(TaskException.EXCEPTION_FILE_EMPTY,"file alread exist");
			}
		}
	}
}