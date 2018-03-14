package com.palmcommerce.funds.roo.protocol.impl;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.palmcommerce.funds.service.ProtocolStorageException;
import com.palmcommerce.funds.alert.service.IAlertManager;
import com.palmcommerce.funds.protocol.covertor.exception.ProtocolConvertorException;
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
import com.palmcommerce.funds.roo.model.TransactionFile;
import com.palmcommerce.funds.roo.model.TransactionMeta;
import com.palmcommerce.funds.roo.model.TransactionRecord;
import com.palmcommerce.funds.roo.protocol.IProtocolStorageHandler;
import com.palmcommerce.funds.roo.service.TransactionFileService;
import com.palmcommerce.funds.roo.service.TransactionMetaService;
import com.palmcommerce.funds.roo.tasklet.ITaskExecutor;
import com.palmcommerce.funds.roo.tasklet.impl.AlertMessageTask;
import com.palmcommerce.funds.roo.tasklet.TaskException;
import com.palmcommerce.funds.roo.tasklet.Tasklet;
import com.palmcommerce.funds.roo.tasklet.impl.T250011Entry;
import com.palmcommerce.funds.roo.util.DateConvertor;

public class DefaultProtocolHandler extends Tasklet implements IProtocolStorageHandler {

	public DefaultProtocolHandler() {
		// TODO Auto-generated constructor stub
	}
	
	private static final Log logger=LogFactory.getLog(DefaultProtocolHandler.class);
	
	private static final int NTHREADS = 10;
	private static final Executor exec= Executors.newFixedThreadPool(NTHREADS);	

	@Autowired
	TransactionFileService transactionFileService;
	Date bankDatatime;
	String transCode;
	
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

	/**
	 * 
	 * 
	 * @param tradeTime
	 * @return
	 * @throws ProtocolStorageException
	 */
	private Date parserTradeTime(String tradeTime) throws ProtocolStorageException {
		Date d;
		try {
			d=DateUtils.parseDate(tradeTime, "yyyy-MM-dd HH:mm:ss");
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ProtocolStorageException("900001", "date time format incorrect");
		}
		
		return d;
	}
	
	private Date parseBankTime(String tradeTime) throws ProtocolStorageException {
		Date d;
		try {
			d=DateUtils.parseDate(tradeTime, "yyyyMMdd");
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ProtocolStorageException("900001", "date time format incorrect");
		}
		
		return d;
	}
	
	@Autowired
	TransactionMetaService transactionMetaService;
	@Autowired
	ITaskExecutor  taskExecutor;
	
	@Autowired
	IAlertManager alertManager;
	
	
	
	

	
	public ITaskExecutor getTaskExecutor() {
		return taskExecutor;
	}

	public void setTaskExecutor(ITaskExecutor taskExecutor) {
		this.taskExecutor = taskExecutor;
	}

	public boolean p240001(P240001 p) throws ProtocolStorageException {
		// TODO Auto-generated method stub
		boolean ret=false;
		
		TransactionMeta meta=new TransactionMeta();
		TransactionRecord record=new TransactionRecord();
		
		//meta.setGlobalSerial(globalSerial);
		if(p.request!=null){
			
		// request
		meta.setGlobalSerial(p.getGlobalSerial());
		meta.setPaymentSerial(p.response.getSerialNumber());
		meta.setTranscode(String.valueOf(p.getHeader().transcode));
		meta.setFromCode(p.getHeader().from);
		meta.setToCode(p.getHeader().to);
		meta.setTradeDateTime(parserTradeTime(p.request.getTransactionTime()));
		record.setUserId(p.request.getUserId());
		}else{
			throw new ProtocolStorageException("9002", "request body is null");
		}
		// response 
		if(p.response!=null){
			meta.setTransactionState(p.response.getCode());
			meta.setTransactionMessage(p.response.getReason());
			
		/*	if(!StringUtils.equals(p.request.getUserId(), p.response.getUserId())){
				throw new ProtocolStorageException("900003", "request and response userid mismatch");
			}
			*/
			record.setUserName(p.response.getUserName());
			
			record.setIdType(p.response.getIdType());
			record.setIdNumber(p.response.getIdNumber());
			int amount=(int)(Double.parseDouble(p.response.getAccountBalance()));
			record.setAmount(amount);
		}else{
			throw new ProtocolStorageException("9004", "repsonse body is null");
		}
		//record.setTransactionMeta(meta);
		//record.persist();
		//transactionMetaService.saveTransactionRecord(record);
		meta.setTransactionRecord(record);
		record.setTransactionMeta(meta);
		transactionMetaService.saveTransactionMeta(meta);
		//record.persist()globalSerial;
		
		logger.debug(meta.toString());
		return ret;
	}


	/*
	 * 
	 * (non-Javadoc)
	 * @see com.palmcommerce.funds.roo.protocol.IProtocolStorageHandler#p240002(com.palmcommerce.funds.protocol.impl.p2t.P240002)
	 */
	@Override
	public boolean p240002(P240002 p) throws ProtocolStorageException {
		boolean ret=false;
		
		TransactionMeta meta=new TransactionMeta();
		TransactionRecord record=new TransactionRecord();
		//meta.setTransactionRecord(record);
		if(p.request!=null){
			meta.setGlobalSerial(p.getGlobalSerial());
			meta.setPaymentSerial(p.response.getSerialNumber());
			meta.setTranscode(String.valueOf(p.getHeader().transcode));
			meta.setFromCode(p.getHeader().from);
			meta.setToCode(p.getHeader().to);
			meta.setTradeDateTime(parserTradeTime(p.request.getTransactionTime()));
			record.setUserId(p.request.getUserId());
			record.setUserName(p.request.getUserName());
			record.setIdType(p.request.getIdType());
			record.setIdNumber(p.request.getIdNumber());
			record.setBindingType(Integer.parseInt(p.request.getBindType()));
			record.setBankAccount(p.request.getAccount());
			record.setBankDatetime(parseBankTime(p.request.getAccountTime()));
		}else{
			throw new ProtocolStorageException("9002", "request body is null");
		}
		if(p.response!=null){
			meta.setTransactionState(p.response.getCode());
			meta.setTransactionMessage(p.response.getReason());
			
		}else{
			throw new ProtocolStorageException("9004", "repsonse body is null");
		}
		meta.setTransactionRecord(record);
		record.setTransactionMeta(meta);
		transactionMetaService.saveTransactionMeta(meta);
		logger.debug(meta.toString());
		
		taskExecutor.execute(new AlertMessageTask(meta,alertManager));
		
		return ret;
	}



	@Override
	public boolean p240003(P240003 p) throws ProtocolStorageException {
		boolean ret=false;
		
		TransactionMeta meta=new TransactionMeta();
		TransactionRecord record=new TransactionRecord();
		
		if(p.request!=null){
			
			meta.setTranscode(String.valueOf(p.getHeader().transcode));
			meta.setFromCode(p.getHeader().from);
			meta.setToCode(p.getHeader().to);
			
			meta.setGlobalSerial(p.getGlobalSerial());
			meta.setPaymentSerial(p.response.getSerialNumber());
			
			meta.setTradeDateTime(parserTradeTime(p.request.getTransactionTime()));
			record.setBankDatetime(parseBankTime(p.request.getAccountTime()));
			
			record.setUserId(p.request.getUserId());
			record.setUserName(p.request.getUserName());
			record.setChargeType(Integer.parseInt(p.request.getPaymentType()));
			record.setAmount(Long.parseLong(p.request.getPaymentAmount()));
			
		}else{
			throw new ProtocolStorageException("9002", "request body is null");
		}
		if(p.response!=null){
			meta.setTransactionState(p.response.getCode());
			meta.setTransactionMessage(p.response.getReason());
			
		}else{
			throw new ProtocolStorageException("9004", "repsonse body is null");
		}
		meta.setTransactionRecord(record);
		record.setTransactionMeta(meta);
		transactionMetaService.saveTransactionMeta(meta);
		logger.debug(meta.toString());
		return ret;
	}



	@Override
	public boolean p240004(P240004 p) throws ProtocolStorageException {
		boolean ret=false;
		
		TransactionMeta meta=new TransactionMeta();
		TransactionRecord record=new TransactionRecord();
		
		if(p.request!=null){
			meta.setGlobalSerial(p.getGlobalSerial());
			meta.setPaymentSerial(p.response.getSerialNumber());
			meta.setTranscode(String.valueOf(p.getHeader().transcode));
			meta.setFromCode(p.getHeader().from);
			meta.setToCode(p.getHeader().to);
			meta.setPaymentSerial(p.request.getPaymentSerialNumber());
			meta.setTradeDateTime(parserTradeTime(p.request.getTransactionTime()));
			record.setUserId(p.request.getUserId());
			record.setUserName(p.request.getUserName());
			record.setAmount(Long.parseLong(p.request.getReverseAmount()));
			record.setBankDatetime(parseBankTime(p.request.getAccountTime()));
		}else{
			throw new ProtocolStorageException("9002", "request body is null");
		}
		if(p.response!=null){
			meta.setTransactionState(p.response.getCode());
			meta.setTransactionMessage(p.response.getReason());
			record.setCancelPaymentSerial(p.response.getSeverseSerialNumber());
		}else{
			throw new ProtocolStorageException("9004", "repsonse body is null");
		}
		meta.setTransactionRecord(record);
		record.setTransactionMeta(meta);
		transactionMetaService.saveTransactionMeta(meta);
		logger.debug(meta.toString());
		return ret;
	}



	@Override
	public boolean p240005(P240005 p) throws ProtocolStorageException {
		boolean ret=false;
		
		TransactionMeta meta=new TransactionMeta();
		TransactionRecord record=new TransactionRecord();
		
		
		if(p.request!=null){
			meta.setGlobalSerial(p.getGlobalSerial());
			meta.setPaymentSerial(p.response.getSerialNumber());
			meta.setTranscode(String.valueOf(p.getHeader().transcode));
			meta.setFromCode(p.getHeader().from);
			meta.setToCode(p.getHeader().to);
			meta.setTradeDateTime(parserTradeTime(p.request.getTransactionTime()));
			record.setUserName(p.request.getUserName());
			record.setIdType(p.request.getIdType());
			record.setIdNumber(p.request.getIdNumber());
			record.setPhoneNumber(p.request.getPhone());
			record.setBankAccount(p.request.getAccount());
			record.setBankDatetime(parseBankTime(p.request.getAccountTime()));
		}else{
			throw new ProtocolStorageException("9002", "request body is null");
		}
		if(p.response!=null){
			meta.setTransactionState(p.response.getCode());
			meta.setTransactionMessage(p.response.getReason());
			record.setUserId(p.response.getUserId());
			record.setUserName(p.response.getUserName());
		}else{
			throw new ProtocolStorageException("9004", "repsonse body is null");
		}
		meta.setTransactionRecord(record);
		record.setTransactionMeta(meta);
		transactionMetaService.saveTransactionMeta(meta);
		logger.debug(meta.toString());
		return ret;
	}



	@Override
	public boolean p240006(P240006 p) throws ProtocolStorageException {
		// TODO Auto-generated method stub
		return false;
	}



	@Override
	public boolean t250001(T250001 t) throws ProtocolStorageException {
		// TODO Auto-generated method stub
		return false;
	}



	@Override
	public boolean t250002(T250002 t) throws ProtocolStorageException {
		boolean ret=false;
		
		TransactionMeta meta=new TransactionMeta();
		TransactionRecord record=new TransactionRecord();
		
		if(t.request!=null){
		// request
			meta.setGlobalSerial(t.getGlobalSerial());
			
		meta.setTradeSerial(t.request.getCenterNumber());
		meta.setTranscode(String.valueOf(t.getHeader().transcode));
		meta.setFromCode(t.getHeader().from);
		meta.setToCode(t.getHeader().to);
		meta.setTradeDateTime(parserTradeTime(t.request.getTransactionTime()));
		record.setUserName(t.request.getUserName());
		record.setIdType(t.request.getIdType());
		record.setIdNumber(t.request.getIdNumber());
		record.setBankAccount(t.request.getAccount());
		}else{
			throw new ProtocolStorageException("9002", "request body is null");
		}
		// response 
		if(t.response!=null){
			meta.setTransactionState(t.response.getCode());
			meta.setTransactionMessage(t.response.getReason());
			
		}else{
			throw new ProtocolStorageException("9004", "repsonse body is null");
		}
		
		meta.setTransactionRecord(record);
		record.setTransactionMeta(meta);
		transactionMetaService.saveTransactionMeta(meta);
		logger.debug(meta.toString());
		return ret;
	}



	@Override
	public boolean t250004(T250004 t) throws ProtocolStorageException {
		boolean ret=false;
		
		TransactionMeta meta=new TransactionMeta();
		TransactionRecord record=new TransactionRecord();
		
		if(t.request!=null){
		// request
		meta.setGlobalSerial(t.getGlobalSerial());
		meta.setTradeSerial(t.request.getCenterNumber());
		meta.setTranscode(String.valueOf(t.getHeader().transcode));
		meta.setFromCode(t.getHeader().from);
		meta.setToCode(t.getHeader().to);
		meta.setTradeDateTime(parserTradeTime(t.request.getTransactionTime()));
		record.setUserId(t.request.getUserId());
		record.setUserName(t.request.getUserName());
		record.setChargeType(Integer.parseInt(t.request.getPaymentType()));
		record.setAmount(Long.parseLong(t.request.getPaymentAmount()));
		record.setBankAccount(t.request.getAccount());
		record.setBankDatetime(parseBankTime(t.request.getAccountTime()));
		}else{
			throw new ProtocolStorageException("9002", "request body is null");
		}
		// response 
		if(t.response!=null){
			meta.setTransactionState(t.response.getCode());
			meta.setTransactionMessage(t.response.getReason());
			
			meta.setTradeSerial(t.response.getCenterNumber());
			meta.setPaymentSerial(t.response.getSerialNumber());
			record.setUserId(t.response.getUserId());
			record.setUserName(t.response.getUserName());
			record.setBankAccount(t.response.getAccount());
		}else{
			throw new ProtocolStorageException("9004", "repsonse body is null");
		}
		
		meta.setTransactionRecord(record);
		record.setTransactionMeta(meta);
		transactionMetaService.saveTransactionMeta(meta);
		logger.debug(meta.toString());
		
		taskExecutor.execute(new AlertMessageTask(meta,alertManager));
		return ret;
	}



	@Override
	public boolean t250005(T250005 t) throws ProtocolStorageException {
		boolean ret=false;
		
		TransactionMeta meta=new TransactionMeta();
		TransactionRecord record=new TransactionRecord();
		
		if(t.request!=null){
		// request
			meta.setGlobalSerial(t.getGlobalSerial());
		meta.setTradeSerial(t.request.getCenterNumber());
		meta.setTranscode(String.valueOf(t.getHeader().transcode));
		meta.setFromCode(t.getHeader().from);
		meta.setToCode(t.getHeader().to);
		
		meta.setTradeDateTime(parserTradeTime(t.request.getTransactionTime()));
		record.setCancelPaymentSerial(t.request.getRealTimePaymentNumber());
		record.setUserId(t.request.getUserId());
		record.setUserName(t.request.getUserName());
		record.setAmount(Long.parseLong(t.request.getPaymentAmount()));
		record.setBankDatetime(parseBankTime(t.request.getAccountTime()));
		}else{
			throw new ProtocolStorageException("9002", "request body is null");
		}
		// response 
		if(t.response!=null){
			meta.setTransactionState(t.response.getCode());
			meta.setTransactionMessage(t.response.getReason());
			
			meta.setTradeSerial(t.response.getCenterNumber());
			meta.setPaymentSerial(t.response.getSerialNumber());
			record.setCancelPaymentSerial(t.request.getRealTimePaymentNumber());	
			record.setUserId(t.response.getUserId());
			record.setUserName(t.response.getUserName());
		}else{
			throw new ProtocolStorageException("9004", "repsonse body is null");
		}
		meta.setTransactionRecord(record);
		record.setTransactionMeta(meta);
		transactionMetaService.saveTransactionMeta(meta);
		logger.debug(meta.toString());

		return ret;
	}



	@Override
	public boolean t250006(T250006 t) throws ProtocolStorageException {
		boolean ret=false;
		
		TransactionMeta meta=new TransactionMeta();
		TransactionRecord record=new TransactionRecord();
		
		if(t.request!=null){
		// request
			meta.setGlobalSerial(t.getGlobalSerial());
		meta.setTradeSerial(t.request.getCenterNumber());
		meta.setTranscode(String.valueOf(t.getHeader().transcode));
		meta.setFromCode(t.getHeader().from);
		meta.setToCode(t.getHeader().to);
		
		meta.setTradeDateTime(parserTradeTime(t.request.getTransactionTime()));
		record.setUserId(t.request.getUserId());
		record.setUserName(t.request.getUserName());
		record.setBankDatetime(parseBankTime(t.request.getAccountTime()));
		}else{
			throw new ProtocolStorageException("9002", "request body is null");
		}
		// response 
		if(t.response!=null){
			meta.setTransactionState(t.response.getCode());
			meta.setTransactionMessage(t.response.getReason());
			
			meta.setTradeSerial(t.response.getCenterNumber());
			meta.setPaymentSerial(t.response.getSerialNumber());	
			record.setUserId(t.response.getUserId());
			record.setUserName(t.response.getUserName());
			record.setChargeType(Integer.parseInt(t.response.getPaymentType()));
			record.setAmount(Long.parseLong(t.response.getPaymentAmount()));
		}else{
			throw new ProtocolStorageException("9004", "repsonse body is null");
		}
		
		meta.setTransactionRecord(record);
		record.setTransactionMeta(meta);
		transactionMetaService.saveTransactionMeta(meta);
		logger.debug(meta.toString());
		return ret;
	}



	@Override
	public boolean t250007(T250007 t) throws ProtocolStorageException {
		boolean ret=false;
		
		TransactionMeta meta=new TransactionMeta();
		TransactionRecord record=new TransactionRecord();
		
		if(t.request!=null){
		// request
			
			meta.setGlobalSerial(t.getGlobalSerial());
		meta.setTradeSerial(t.request.getCenterNumber());
		meta.setTranscode(String.valueOf(t.getHeader().transcode));
		meta.setFromCode(t.getHeader().from);
		meta.setToCode(t.getHeader().to);
		
		meta.setTradeDateTime(parserTradeTime(t.request.getTransactionTime()));
		record.setUserId(t.request.getUserId());
		record.setUserName(t.request.getUserName());
		record.setBankAccount(t.request.getAccount());
		record.setAmount(Long.parseLong(t.request.getRemittanceAmount()));
		record.setBankDatetime(parseBankTime(t.request.getAccountTime()));
		}else{
			throw new ProtocolStorageException("9002", "request body is null");
		}
		// response 
		if(t.response!=null){
			meta.setTransactionState(t.response.getCode());
			meta.setTransactionMessage(t.response.getReason());
			meta.setTransactionState(t.response.getState());
			meta.setTradeSerial(t.response.getCenterNumber());
			meta.setPaymentSerial(t.response.getSerialNumber());
			record.setUserId(t.response.getUserId());
			record.setUserName(t.response.getUserName());
			record.setBankAccount(t.response.getAccount());
			
		}else{
			throw new ProtocolStorageException("9004", "repsonse body is null");
		}
		
		meta.setTransactionRecord(record);
		record.setTransactionMeta(meta);
		transactionMetaService.saveTransactionMeta(meta);
		logger.debug(meta.toString());
		taskExecutor.execute(new AlertMessageTask(meta,alertManager));
		return ret;
	}



	@Override
	public boolean t250008(T250008 t) throws ProtocolStorageException {
		boolean ret=false;
		
		TransactionMeta meta=new TransactionMeta();
		TransactionRecord record=new TransactionRecord();
		
		if(t.request!=null){
		// request
			meta.setGlobalSerial(t.getGlobalSerial());
		meta.setTranscode(String.valueOf(t.getHeader().transcode));
		meta.setFromCode(t.getHeader().from);
		meta.setToCode(t.getHeader().to);
		
		record.setFileName(t.request.getFileName());
		record.setFileSize(Integer.parseInt(t.request.getFileSize()));
		record.setBankDatetime(parseBankTime(t.request.getAccountTime()));
		}else{
			throw new ProtocolStorageException("9002", "request body is null");
		}
		// response 
		if(t.response!=null){
			meta.setTransactionState(t.response.getCode());
			meta.setTransactionMessage(t.response.getReason());
			
			record.setFileSize(Integer.parseInt(t.response.getCheckFileSize()));
		}else{
			throw new ProtocolStorageException("9004", "repsonse body is null");
		}
		
		meta.setTransactionRecord(record);
		record.setTransactionMeta(meta);
		transactionMetaService.saveTransactionMeta(meta);
		logger.debug(meta.toString());
		return ret;
	}



	@Override
	public boolean t250009(T250009 t) throws ProtocolStorageException {
		boolean ret=false;
		
		TransactionMeta meta=new TransactionMeta();
		TransactionRecord record=new TransactionRecord();
		
		if(t.request!=null){
		// request
			meta.setGlobalSerial(t.getGlobalSerial());
		meta.setTranscode(String.valueOf(t.getHeader().transcode));
		meta.setFromCode(t.getHeader().from);
		meta.setToCode(t.getHeader().to);
		
		
		record.setBankDatetime(parseBankTime(t.request.getAccountTime()));
		}else{
			throw new ProtocolStorageException("9002", "request body is null");
		}
		// response 
		if(t.response!=null){
			meta.setTransactionState(t.response.getCode());
			meta.setTransactionMessage(t.response.getReason());
			
			record.setFileSize(Integer.parseInt(t.response.getCheckFileSize()));
		}else{
			throw new ProtocolStorageException("9004", "repsonse body is null");
		}
		
		meta.setTransactionRecord(record);
		record.setTransactionMeta(meta);
		transactionMetaService.saveTransactionMeta(meta);
		logger.debug(meta.toString());
		return ret;
	}



	@Override
	public boolean t250010(T250010 t) throws ProtocolStorageException {
		boolean ret=false;
		
		TransactionMeta meta=new TransactionMeta();
		TransactionRecord record=new TransactionRecord();
	
		if(t.request!=null){
		// request
			meta.setGlobalSerial(t.getGlobalSerial());
		meta.setTranscode(String.valueOf(t.getHeader().transcode));
		meta.setFromCode(t.getHeader().from);
		meta.setToCode(t.getHeader().to);
		
		record.setBankDatetime(parseBankTime(t.request.getBindingTime()));
		}else{
			throw new ProtocolStorageException("9002", "request body is null");
		}
		// response 
		if(t.response!=null){
			meta.setTransactionState(t.response.getCode());
			meta.setTransactionMessage(t.response.getReason());
			record.setFileSize(Integer.parseInt(t.response.getCheckFileSize()));
			
		}else{
			throw new ProtocolStorageException("9004", "repsonse body is null");
		}
		
		meta.setTransactionRecord(record);
		record.setTransactionMeta(meta);
		transactionMetaService.saveTransactionMeta(meta);
		logger.debug(meta.toString());
		return ret;
	}



	@Override
	public boolean t250011(T250011 t) throws ProtocolStorageException {
		boolean ret=false;
		
		TransactionMeta meta=new TransactionMeta();
		TransactionRecord record=new TransactionRecord();
		
		if(t.request!=null){
		// request
			meta.setGlobalSerial(t.getGlobalSerial());
		meta.setTranscode(String.valueOf(t.getHeader().transcode));
		meta.setFromCode(t.getHeader().from);
		meta.setToCode(t.getHeader().to);
		
		record.setBankDatetime(parseBankTime(t.request.getAccountTime()));
		}else{
			throw new ProtocolStorageException("9002", "request body is null");
		}
		// response 
		if(t.response!=null){
			meta.setTransactionState(t.response.getCode());
			meta.setTransactionMessage(t.response.getReason());
			
			record.setFileSize(Integer.parseInt(t.response.getCheckFileSize()));
		}else{
			throw new ProtocolStorageException("9004", "repsonse body is null");
		}
		
		meta.setTransactionRecord(record);
		record.setTransactionMeta(meta);
		
		transactionMetaService.saveTransactionMeta(meta);
		logger.debug(meta.toString());
		// doing task
		//taskExecutor.execute(new T250011Task(t));
		
		
		return ret;
	}
	
	

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
}
