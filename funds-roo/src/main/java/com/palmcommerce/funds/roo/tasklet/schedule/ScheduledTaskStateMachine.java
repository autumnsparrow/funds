/**
 * @company Palm Lottery Information&Technology Co.,Ltd.
 *
 * @author  sparrow
 *
 * @date    Oct 29, 2013
 *
 */
package com.palmcommerce.funds.roo.tasklet.schedule;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Calendar;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import javax.mail.internet.MimeMessage;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.impl.client.RoutedRequest;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.ui.velocity.VelocityEngineUtils;

import com.ibm.icu.text.SimpleDateFormat;

import com.j256.simplejmx.common.JmxAttributeMethod;
import com.j256.simplejmx.common.JmxOperation;
import com.j256.simplejmx.common.JmxResource;
import com.palmcommerce.funds.alert.service.IAlertManager;
import com.palmcommerce.funds.alert.service.impl.AlertMessage;
import com.palmcommerce.funds.alert.service.impl.EmailProperty;
import com.palmcommerce.funds.alert.service.sms.SmsCodeSendHelper;
import com.palmcommerce.funds.configuration.v2.ConfigurationManager;
import com.palmcommerce.funds.configuration.v2.RouteRuleEntry;
import com.palmcommerce.funds.protocol.ProtocolDriverManager;
import com.palmcommerce.funds.protocol.impl.IProtocol;
import com.palmcommerce.funds.protocol.impl.t2p.T250011;
import com.palmcommerce.funds.roo.model.ScheduledTaskState;
import com.palmcommerce.funds.roo.protocol.impl.DefaultProtocolProcessHandler;
import com.palmcommerce.funds.roo.protocol.impl.ScheduledTradeClientProtocolHandler;
import com.palmcommerce.funds.roo.service.ScheduledTaskStateService;
import com.palmcommerce.funds.roo.service.TransactionFileService;
import com.palmcommerce.funds.roo.service.TransactionMetaService;
import com.palmcommerce.funds.roo.tasklet.impl.T250011TaskOfProxy;
import com.palmcommerce.funds.roo.tasklet.impl.T250011TaskOfTrade;
import com.palmcommerce.funds.roo.tasklet.schedule.ScheduledTask.TaskState;
import com.palmcommerce.funds.roo.util.DateConvertor;
import com.palmcommerce.funds.service.ProtocolProcessException;
import com.palmcommerce.oss.jpa.model.OssAlert;
import com.palmcommerce.oss.jpa.service.AlertService;
import com.palmcommerce.funds.service.ProtocolStorageException;
import com.palmcommerce.oss.jpa.service.OssReconciliationStaticsService;
import java.util.TimeZone;;

/**
 * 
 * Illustration of ScheduledTaskStateMachine:
 * 
 * 
 * 1. network structure of the reconciliation:
 * 					2, sending t250011 
 * 	+----------+                      +---------------------------+             +----------------+
 *  |bank node |  <--------->         | proxy node                |             |trade node      |
 *  +----------+  --------->          +---------------------------+             +----------------+
 *  		  3.sftp upload [file] 			{Forward Acceptor}   {sftp forward}
 *                                   | 	    ^                     ^
 *                                   |	    |    1. active reconciliation.
 *                                   | 	    |                     |
 *                                   | 	    |	                  | 6
 *              4.sftp received     \ /     |                
 *                                    +------------------------------+
 *                                    |  roo node                    |
 * 									  +------------------------------+	   
 * 										
 * 								5. process the data                     6 sftp upload trade node files
 * 
 * 
 * 
 * 2.state machine of the StateMachine
 * 
 * 				STATE :  active reconciliation   yyyyMMdd.reconciliation.active
 * 				STATE :  receiving files		 yyyyMMdd.reconciliation.receive
 * 				STATE :  processing files	     yyyyMMdd.reconciliation.processing
 * 				STATE :  sending files.          yyyyMMdd.reconciliation.seding
 * 
 * 
 * 
 * 
 * 3. state change diagram.
 * 
 * 
 * 						active              <------ timer trigger  create the file lock
 * 
 * 						
 * 				loading route rule
 * 						 |
 * 						  			
 * 							[ isActivingDone]          <--------{ loop in fixed rate}        < ----  timeout trigger.
 * 						|
 * 						
 *          			receiving
 *          			|
 *          				[isReceivingDone]          <------- {loop in fixed rate }        <----- timeout trigger                
 * 						|
 * 						processing	
 * 						| 
 * 							[isProcessingDone]         <----------{loop in fixed rate}      <---- timeout trigger
 * 
 * 						|
 * 					sending  
 * 
 * 						|  
 * 							[ isSendingDone]           <-------{loop in fixed rate}    <------ timeout trigger
 * 
 * 					finished                                 <-------- timeout of the task
 * 					  |
 * 
 * 					cleanup                             <--------clean up the file lock.
 * 								
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * @author sparrow
 * 
 */
@JmxResource(domainName="com.palmcommerce.funds.roo.tasklet.schedule" ,beanName="ScheduledTaskStateMachine",description="Scheduled Task state machine")
public class ScheduledTaskStateMachine {

	private static final Log logger = LogFactory.getLog(ScheduledTaskStateMachine.class);

	@Autowired
	ScheduledTradeClientProtocolHandler scheduledTradeClientProtocolHandler;
	@Autowired
	DefaultProtocolProcessHandler defaultProcotolProcessHandler;
	@Autowired
	ConfigurationManager configurationManager;
	@Autowired
	ScheduledTaskStateService scheduledTaskStateService;
	@Autowired
	T250011TaskOfProxy taskOfProxy;
	@Autowired
	AlertService alertService;	
	@Autowired
	private JavaMailSender mailSender;
	@Autowired
	VelocityEngine velocityEngine;
	@Autowired
	EmailProperty emailProperty;
	@Autowired
	SmsCodeSendHelper smsSender;
	
	
	
	
	
	
	@Value("${reconciliation.local.dir.receiving}")
	String localReceivingDir;
	
	boolean  loaded;
	
	int prevDays;
	
	@JmxOperation(description="send reconciliation request",parameterNames={"bankcode","bankdate"},parameterDescriptions={"bankcode and bank date"})
	public synchronized void sendReconciliationRequest(String bankcode,String bankdate){
		try {
			sendBankReconciliationRequest(bankcode, DateConvertor.parseBankTime(bankdate));
		} catch (ProtocolStorageException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	@JmxOperation(description="reconciliation files",parameterNames={"bankcode","bankfile","bankdate"},parameterDescriptions={"filename and bank date"})
	public void reconciliationBank(String bankcode,String bankfile,String bankdate){
		try {
			reconciliationBank(bankcode,bankfile, DateConvertor.parseBankTime(bankdate));
		} catch (ProtocolStorageException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@JmxOperation(description="reconciliation trade files",parameterNames={"tradecode","bankdate"},parameterDescriptions={"trade code and bank date"})

	public  void reconciliationTrade(String tradecode,String bankdate){
		try {
			sendingReconciliationTrade(tradecode, DateConvertor.parseBankTime(bankdate));
		} catch (ProtocolStorageException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@JmxAttributeMethod(description="account state.")
	public synchronized String getAccountDate(){
		String date=DateConvertor.getBankTimeByDate(previousDate());
		return date;
		
	}
	
	@JmxOperation(description="chage previos date",parameterNames={"days"},parameterDescriptions={"current - days"})
	public synchronized void changePreviousDays(int days){
		this.prevDays=days;
	}
	
	
	
	@JmxOperation(description="reconciliation the date",parameterNames={"accountDate"},parameterDescriptions={"accountDate like 20140209"})
	public synchronized void reconciliation(String accountDate){
		//this.onCleanup();
		
		String dateString = bankDateFormat.format(new Date());
		Date myDate = null;
		try {
			myDate = bankDateFormat.parse(dateString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Date accountDatetime=null;
		try {
			accountDatetime = DateConvertor.parseBankTime(accountDate);
		} catch (ProtocolStorageException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		long times=accountDatetime.getTime()-myDate.getTime();
		if(myDate==null||accountDatetime==null)
			this.prevDays=-1;
		else
			this.prevDays=(int)times/24*60*60*60;
		
		this.onCleanup();
		this.changeState(0);
	}
	
	
	@JmxOperation(description="chage state of the state machine",parameterNames={"state"},parameterDescriptions={"INVALID=-1;ACTIVING=0;RECEIVING=1;PROCESSING=2;SENDING=3;TIMEOUT=4;FINISHED=5;ACTIVE_WAITING=6;"})
	public synchronized void changeState(int state){
	switch (state) {
			case ACTIVING:
			{
				// is all finished.
				// re acting
				//activing();
				//publishTask();
				
				ScheduledState.pushState(ScheduledState.STATE_ACTIVING);
			}
				break;
			case ACTIVE_WAITING:{
			ScheduledState.pushState(ScheduledState.STATE_ACTIVE_WAITING);
				//isActivingDone();
			}
				break;

				
			case RECEIVING:{
				ScheduledState.pushState(ScheduledState.STATE_RECEIVING);
			}
				break;
			case PROCESSING:{
				
				ScheduledState.pushState(ScheduledState.STATE_PROCESSING);
			}
				break;
			case SENDING:{
				ScheduledState.pushState(ScheduledState.STATE_SENDING);
				
			}
				break;
			case INVALID:{
				ScheduledState.pushState(ScheduledState.STATE_INVALID);
			}
			default:
				break;
			}
	
	}
	
	@JmxOperation(description="reconciliationActive")
	@Scheduled(cron="${reconciliation.cron.active}")
	public synchronized void activing(){
		log("xxxxxxxx---reconciliation.cron.active -->"+DateConvertor.getTradeTime());
		// 1.loading the route rule.
		loaded=loadRouteRule();
		if(loaded){
			ScheduledState.pushState(ScheduledState.STATE_ACTIVING);
			
			
		}else{
			ScheduledState.pushState(ScheduledState.STATE_INVALID);
			onUnloaded();
			
		}
	
		
	}
	
	private static final int FIXED_RATE=60*1000;
	
	@JmxAttributeMethod(description="scheduled state.")
	public synchronized String getScheduledState(){
		ScheduledState state=ScheduledState.pullState();
		return state.toString();
	}
	
	
	ScheduledState  currentState;
	
	/**
	 * checking every 15 minutes.
	 * 
	 */
	@Scheduled(fixedRate=FIXED_RATE)
	public synchronized void schedule(){
		try {
			if(!loaded)
				loadRouteRule();
			
			ScheduledState state=ScheduledState.pullState();
			log("xxxxxxxxxxx---- schedule ---->"+DateConvertor.getTradeTime()+" , "+state.toString());
			switch (state.getState()) {
			case ACTIVING:
			{
				// is all finished.
				// re acting
				//activing();
				publishTask();
				
			}
				break;
			case ACTIVE_WAITING:{
				isActivingDone();
			}
				break;

				
			case RECEIVING:{
				isReceivingDone();
			}
				break;
			case PROCESSING:{
				isProcessingDone();
				
			}
				break;
			case SENDING:{
				isSendingDone();
				
			}
				break;
			case INVALID:{
				
			}
			default:
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@JmxOperation(description="reconciliationActiveTimeout")
	@Scheduled(cron="${reconciliation.cron.active.timeout}")
	public synchronized void onActivingTimeout(){
		log("xxxxxxxx---reconciliation.cron.active.timeout -->"+DateConvertor.getTradeTime());
		try {
			ScheduledState scheduledState=ScheduledState.pullState();
			
			if(scheduledState.getState()==ACTIVING){
				scheduledState=ScheduledState.STATE_TIMEOUT;
				ScheduledState.pushState(scheduledState);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@JmxOperation(description="reconciliationReceivingTimeout")
	@Scheduled(cron="${reconciliation.cron.receiving.timeout}")
	public synchronized void onReceivingTimeout(){
		log("xxxxxxxx---reconciliation.cron.receiving.timeout -->"+DateConvertor.getTradeTime());
		try {
			ScheduledState scheduledState=ScheduledState.pullState();
			if(scheduledState.getState()==RECEIVING){
				scheduledState=ScheduledState.STATE_TIMEOUT;
				ScheduledState.pushState(scheduledState);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@JmxOperation(description="reconciliationProcessingTimeout")
	@Scheduled(cron="${reconciliation.cron.processing.timeout}")
	public synchronized void onProcessingTimeout(){
		log("xxxxxxxx---reconciliation.cron.processing.timeout -->"+DateConvertor.getTradeTime());
		try {
			ScheduledState scheduledState=ScheduledState.pullState();
			if(scheduledState.getState()==PROCESSING){
				scheduledState=ScheduledState.STATE_TIMEOUT;
				ScheduledState.pushState(scheduledState);
				//onReceivingTimeout();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@JmxOperation(description="reconciliationSendingTimeout")
	@Scheduled(cron="${reconciliation.cron.sending.timeout}")
	public synchronized void onSendingTimeout(){
		log("xxxxxxxx---reconciliation.cron.sending.timeout -->"+DateConvertor.getTradeTime());
		try {
			ScheduledState scheduledState=ScheduledState.pullState();
			if(scheduledState.getState()==SENDING){
				scheduledState=ScheduledState.STATE_TIMEOUT;
				ScheduledState.pushState(scheduledState);
				//onSendingTimeout();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@JmxOperation(description="reconciliationFinished")
	@Scheduled(cron="${reconciliation.cron.finished}")
	public synchronized void onFinished(){
		log("xxxxxxxx---reconciliation.cron.finished -->"+DateConvertor.getTradeTime());
		try {
			ScheduledState scheduledState=ScheduledState.pullState();
			if(scheduledState.getState()!=FINISHED){
				// Sending the failed email.
				onUnfinished();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.prevDays=-1;
		
	}
	
	@JmxOperation(description="reconciliationCleanup")
	@Scheduled(cron="${reconciliation.cron.cleanup}")
	public synchronized void onCleanup(){
		log("xxxxxxxx---reconciliation.cron.cleanup -->"+DateConvertor.getTradeTime());
		try {
			ScheduledState.clearState();
			List<ScheduledTaskState> states=scheduledTaskStateService.findScheduledTaskStatesByBankDateEquals(previousDate());
			for(ScheduledTaskState state:states){
				state.remove();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	private void log(String message){
		logger.warn("=========Reconciliation======>:"+message);
	}
	
	/**
	 * 
	 * Loading the route rule from the oss server.
	 * 
	 * 
	 * @return
	 */
	@JmxOperation(description="loadRouteRule")
	public synchronized boolean loadRouteRule() {
		boolean ret = true;
		
		
		this.bankCodes = new LinkedList<String>();
		this.tradeCodes = new LinkedList<String>();
		configurationManager.updateRouteRules();
		if (configurationManager.getRouteRuleResult() != null) {
			for (RouteRuleEntry entry : configurationManager
					.getRouteRuleResult().getRouteRuleEntries()) {
				if (entry.isBank()){
					this.bankCodes.add(entry.getToCode());
					log("xxxxxxxxxxx----bank code---->"+entry.toString());
				}
				else {
					if(entry!=null&&entry.getToCode()!=null&&!entry.getToCode().startsWith("P"))
					this.tradeCodes.add(entry.getToCode());
					log("xxxxxxxxxxx----trade code---->"+entry.toString());
				}
			}
		} else {
			

			ret = false;

		}
	
		
		return ret;

	}
	
	private synchronized boolean sendBankReconciliationRequest(String bankCode,Date date){
		
		boolean ret=false;
		T250011 t = (T250011) ProtocolDriverManager
				.instance("250011", configurationManager
						.getServerRules().getProxy()
						.getNodeCode(), bankCode);
		t.request.setAccountTime(DateConvertor
				.getBankTimeByDate(date));
		log("xxxxxxxx---publishTask -->"+t.toString());
		// try {
		
		//logger.info("Sending the request:" + t.toString());
		// Release that par when connection.
		try {
			//TODO see me ?
			scheduledTradeClientProtocolHandler.send(t);
			//TODO 00
			ret=true;
		} catch (Exception e) {
			e.printStackTrace();
			ret=false;
			//task.setProcessState(TaskState.PROCEED_FAILED.ordinal());
		}
		return ret;
	}
	
	/**
	 * checking is the activing command send succeed util time out trigger.
	 * 
	 * @return
	 */
	public boolean isActivingDone(){
		boolean isFinished=true;
		List<ScheduledTaskState> tasks = scheduledTaskStateService
				.findScheduledTaskStatesByBankDateEqualsAndIsBank(
						previousDate(), Boolean.valueOf(true));
		// .findScheduledTaskStatesByBankDateEqualsAndTranscodeEquals(previousDate(),
		// transcode);
		// .findScheduledTaskStatesByBankDateEquals(previousDate());
		for (ScheduledTaskState task : tasks) {
			log("xxxxxxxx---isActivingDone -->"+task.toString());
			if (task.getProcessState() != TaskState.PROCEED_SUCCESS
					.ordinal()) {
				isFinished = false;
				break;
			}
		}
		
		if(isFinished){
			ScheduledState.pushState(ScheduledState.STATE_RECEIVING);
		}else{
			ScheduledState.pushState(ScheduledState.STATE_ACTIVING);
		}
		return isFinished;
	}

	
	synchronized boolean isReceivingDone(){
		boolean isFinished=true;
		List<ScheduledTaskState> tasks = scheduledTaskStateService
				.findScheduledTaskStatesByBankDateEqualsAndIsBank(
						previousDate(), Boolean.valueOf(true));
		// .findScheduledTaskStatesByBankDateEqualsAndTranscodeEquals(previousDate(),
		// transcode);
		// .findScheduledTaskStatesByBankDateEquals(previousDate());
		for (ScheduledTaskState task : tasks) {
			
			log("xxxxxxxx---isReceivingDone -->"+task.toString());
			if (task.getProcessState() == TaskState.PROCEED_SUCCESS
					.ordinal()) {
				File f=new File(localReceivingDir+File.separator+task.getFileName());
				if(!f.exists()){
					log(f.getAbsoluteFile()+" not exist!");
					isFinished=false;
				}else{
					if(f.length()!=task.getFileSize()){
						log(f.getAbsolutePath()+" size [local: "+f.length()+" != server: "+task.getFileSize()+"]");
						isFinished=false;
					}
				}
				//break;
			}
		}
		
		
		if(isFinished){
			ScheduledState.pushState(ScheduledState.STATE_PROCESSING);
		}
		return isFinished;
	}
	
	
	private synchronized boolean reconciliationBank(String bankcode,String fileName,Date date){
		log("xxxxxxxx---reconciliationBank --");
		File f=new File(localReceivingDir+File.separator+fileName);
		boolean isFinished=false;
		T250011 t = (T250011) ProtocolDriverManager
				.instance("250011", configurationManager
						.getServerRules().getProxy()
						.getNodeCode(), bankcode);
		t.request.setAccountTime(DateConvertor
				.getBankTimeByDate(date));
		t.response=new T250011.Response();
		t.response.setCheckFileSize(String.valueOf(f.length()));
		t.response.setReason(f.getAbsolutePath());
		taskOfProxy.setT(t);
		try {
			isFinished=taskOfProxy.reconciliation();
		} catch (ProtocolStorageException e) {
			e.printStackTrace();
		}
		return isFinished;
	}
	
	
	private synchronized boolean isProcessingDone(){
		
		boolean isFinished=true;
		List<ScheduledTaskState> tasks = scheduledTaskStateService
				.findScheduledTaskStatesByBankDateEqualsAndIsBank(
						previousDate(), Boolean.valueOf(true));
		// .findScheduledTaskStatesByBankDateEqualsAndTranscodeEquals(previousDate(),
		// transcode);
		// .findScheduledTaskStatesByBankDateEquals(previousDate());
		for (ScheduledTaskState task : tasks) {
			if (task.getProcessState() == TaskState.PROCEED_SUCCESS
					.ordinal()) {
				log("xxxxxxxx---isProcessingDone -->"+task.toString());
				isFinished=reconciliationBank(task.getBankCode(),task.getFileName(), previousDate());
				
				
				//scheduledTradeClientProtocolHandler.processReconciliation(t);
				//break;
			}
		}
		
		
		if(isFinished){
			ScheduledState.pushState(ScheduledState.STATE_SENDING);
		}
		return isFinished;
	}
	
	/**
	 * 
	 * 
	 * 
	 * 
	 * 
	 */
	private synchronized void publishTask(){
		//TODO see me?
		
		
		List<ScheduledTaskState> tasks = scheduledTaskStateService
				.findScheduledTaskStatesByBankDateEqualsAndIsBank(
						previousDate(), Boolean.valueOf(true));
		// .findScheduledTaskStatesByBankDateEquals(previouseDate);

		if (tasks == null || (tasks != null && tasks.size() == 0)) {
		
			for (String bankCode : bankCodes) {
				ScheduledTaskState task = new ScheduledTaskState();
				task.setBankCode(bankCode);
				task.setBankDate(previousDate());
				task.setProcessDatetime(new Date());
				task.setProcessState(TaskState.PUBLISHED.ordinal());
				task.setIsBank(1);
			
				scheduledTaskStateService
						.saveScheduledTaskState(task);
				logger.info("all bank code publishing now!");

			}
			
			// .findScheduledTaskStatesByBankDateEquals(previouseDate);
		}
		
		tasks = scheduledTaskStateService
				.findScheduledTaskStatesByBankDateEqualsAndIsBank(
						previousDate(), Boolean.valueOf(true));

		// query if the task published.
		if (tasks != null && this.bankCodes != null) {
			if (tasks.size() == this.bankCodes.size()) {
				// task has been published.
				logger.info("---------->all bank code has been published!");
				
			} else {
				mapOfTasks.clear();
				// alert some task don't published.
				for (ScheduledTaskState task : tasks) {
					mapOfTasks.put(task.getBankCode(), task);
				}

				for (String bankCode : bankCodes) {
					if (!mapOfTasks.containsKey(bankCode)) {
						ScheduledTaskState task = new ScheduledTaskState();
						task.setBankCode(bankCode);
						task.setBankDate(previousDate());
						task.setProcessDatetime(new Date());
						task.setIsBank(1);
						task.setProcessState(TaskState.PUBLISHED
								.ordinal());
						scheduledTaskStateService
								.saveScheduledTaskState(task);

						logger.error("bank code: "
								+ bankCode
								+ " failed re- publish scheduled task!");
					}
				}
				
			}
		}
		
		
		//
		// the task will be re-set failed state.
		for (ScheduledTaskState task : tasks) {
			// Task T250011
			//log("xxxxxxxx---publishTask -->"+task.toString());

			if (task.getProcessState() == TaskState.PROCESSING
					.ordinal()) {
				logger.info("------>PROCESSING: bankcode="+task.getBankCode());
				long processingTime = task.getProcessDatetime()
						.getTime();
				long currentTime = new Date().getTime();
				if (currentTime - processingTime > 2*60 * 1000) {
					task.setProcessState(TaskState.PROCEED_FAILED
							.ordinal());
					task.setProcessDatetime(new Date());
					scheduledTaskStateService
					.updateScheduledTaskState(task);
					
				}

			}
		}
		
		for (ScheduledTaskState task : tasks) {
			
			log("xxxxxxxx---publishTask -->"+task.toString());
			

			if (task.getProcessState() == TaskState.PUBLISHED
					.ordinal()
					|| task.getProcessState() == TaskState.PROCEED_FAILED
							.ordinal()) {

				
				task.setProcessState(TaskState.PROCESSING.ordinal());
				task.setRetries(task.getRetries() + 1);
				task.setProcessDatetime(new Date());
				scheduledTaskStateService
				.updateScheduledTaskState(task);
				boolean ret=sendBankReconciliationRequest(task.getBankCode(),previousDate());
				if(!ret){
					task.setProcessState(TaskState.PROCEED_FAILED.ordinal());
					scheduledTaskStateService
					.updateScheduledTaskState(task);
				}
				
//				T250011 t = (T250011) ProtocolDriverManager
//						.instance("250011", configurationManager
//								.getServerRules().getProxy()
//								.getNodeCode(), task.getBankCode());
//				t.request.setAccountTime(DateConvertor
//						.getBankTimeByDate(previousDate()));
//
//				// try {
//				
//				//logger.info("Sending the request:" + t.toString());
//				// Release that par when connection.
//				try {
//					//TODO see me ?
//					scheduledTradeClientProtocolHandler.send(t);
//					//TODO 00
//				} catch (Exception e) {
//					e.printStackTrace();
//					task.setProcessState(TaskState.PROCEED_FAILED.ordinal());
//				}
//				
//				scheduledTaskStateService
//						.updateScheduledTaskState(task);
			}

		}
		
		ScheduledState.pushState(ScheduledState.STATE_ACTIVE_WAITING);

	}
	
	
	
	private synchronized boolean sendingReconciliationTrade(String tradecode,Date bankdate){
		boolean ret=true;
		T250011 t = (T250011) ProtocolDriverManager
				.instance("250011", tradecode,
						configurationManager
								.getServerRules()
								.getProxy().getNodeCode());
		t.request.setAccountTime(DateConvertor.getBankTimeByDate(bankdate));

		// try {

		logger.info("Sending the request:" + t.toString());
		// Release that part when connection test.
		//
		
		
		
		try {
			ret = defaultProcotolProcessHandler.t250011(t);
		} catch (ProtocolProcessException e) {
			e.printStackTrace();
			ret = false;
		}
		return ret;
	}
	
	
	
	private synchronized boolean isSendingDone(){
		boolean ret=true;
		List<ScheduledTaskState> tasks = scheduledTaskStateService
				.findScheduledTaskStatesByBankDateEqualsAndIsBank(
						previousDate(), Boolean.valueOf(false));
		// .findScheduledTaskStatesByBankDateEquals(previouseDate);

		if (tasks == null || (tasks != null && tasks.size() == 0)&&tradeCodes!=null) {
			
			for (String tradeCode : tradeCodes) {
				ScheduledTaskState task = new ScheduledTaskState();
				task.setTradecode(tradeCode);
				task.setBankDate(previousDate());
				task.setProcessDatetime(new Date());
				task.setProcessState(TaskState.PUBLISHED.ordinal());
				task.setIsBank(0);
				scheduledTaskStateService
						.saveScheduledTaskState(task);
				logger.info("all trade code publishing now!");

			}
			tasks = scheduledTaskStateService
					.findScheduledTaskStatesByBankDateEqualsAndIsBank(
							previousDate(), Boolean.valueOf(false));
			// .findScheduledTaskStatesByBankDateEquals(previouseDate);
		}

		// query if the task published.
		if (tasks != null && this.tradeCodes != null) {
			if (tasks.size() == this.tradeCodes.size()) {
				// task has been published.
				logger.info("all tradeCodes code has been published!");
				
			} else {
				// mapOfTasks.clear();
				// alert some task don't published.
				for (ScheduledTaskState task : tasks) {
					mapOfTasks.put(task.getTradecode(), task);
				}

				for (String tradecode : tradeCodes) {
					if (!mapOfTasks.containsKey(tradecode)) {
						ScheduledTaskState task = new ScheduledTaskState();
						task.setTradecode(tradecode);
						task.setBankDate(previousDate());
						task.setProcessDatetime(new Date());
						task.setIsBank(0);
						task.setProcessState(TaskState.PUBLISHED
								.ordinal());
						scheduledTaskStateService
								.saveScheduledTaskState(task);

						logger.error("trade code: "
								+ tradecode
								+ " failed re- publish scheduled task!");
					}
				}
				
			}
		}
		
		for (ScheduledTaskState task : tasks) {

			logger.info("Sending the request:"
					+ task.getProcessState() + "["
					+ TaskState.PUBLISHED.ordinal() + "|"
					+ TaskState.PROCEED_FAILED.ordinal() + "]");

		
			// Task T250011
			if (task.getProcessState() == TaskState.PUBLISHED
					.ordinal()
					|| task.getProcessState() == TaskState.PROCEED_FAILED
							.ordinal()) {

//				T250011 t = (T250011) ProtocolDriverManager
//						.instance("250011", task.getTradecode(),
//								configurationManager
//										.getServerRules()
//										.getProxy().getNodeCode());
//				t.request.setAccountTime(DateConvertor
//						.getBankTimeByDate(previousDate()));
//
//				// try {
//
//				logger.info("Sending the request:" + t.toString());
//				// Release that part when connection test.
//				//
//				
//				
//				
//				try {
//					ret = defaultProcotolProcessHandler.t250011(t);
//				} catch (ProtocolProcessException e) {
//					e.printStackTrace();
//					ret = false;
//				}

				ret=sendingReconciliationTrade(task.getTradecode(),previousDate());
				if (ret)
					task.setProcessState(TaskState.PROCEED_SUCCESS
							.ordinal());
				else
					task.setProcessState(TaskState.PROCEED_FAILED
							.ordinal());
				task.setRetries(task.getRetries() + 1);
				task.setProcessDatetime(new Date());
				scheduledTaskStateService
						.updateScheduledTaskState(task);
			}

		}

		if(ret){
			ScheduledState.pushState(ScheduledState.STATE_FINISHED);
		}
		
			return ret;
	}
	
	
	
	private synchronized void onReceivingFailed(){
		logger.info("send email ......................StateProxyProcessingFailed....");
		int typeId = 7;
		alertList = alertService.findOssAlertsByAlertTypeId(typeId);
		for (OssAlert alert : alertList) {
			if (alert.getAlertNotityId().intValue() == 0) {
				try {
					sendNote(alert.getOperatorPhone(),
							alert.getRemarks());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (alert.getAlertNotityId().intValue() == 1) {

				AlertMessage alertMessage = new AlertMessage();
				alertMessage.setOperatorName(alert
						.getOperatorName());
				alertMessage.setSendAddr(emailProperty.getFrom());
				alertMessage.setTradeName("operator");
				alertMessage.setState("fail");
				alertMessage.setContext(alert.getRemarks());
				alertMessage.setTime(DateConvertor.getTradeTime());
				alertMessage.setUserName(emailProperty
						.getUsername());
				taskSendMail(alertMessage, alert.getOperatorPhone());
			}
		}

		
	}
	
	
	private synchronized void onUnfinished(){
		// send email
		/******************************************************************************/
		logger.info("send email ..................!taskFinished.............");
		int typeId = 7;
		alertList = alertService
				.findOssAlertsByAlertTypeId(typeId);
		for (OssAlert alert : alertList) {
			if (alert.getAlertNotityId().intValue() == 0) {
				try {
					sendNote(alert.getOperatorPhone(),
							alert.getRemarks());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (alert.getAlertNotityId().intValue() == 1) {

				AlertMessage alertMessage = new AlertMessage();
				alertMessage.setOperatorName(alert
						.getOperatorName());
				alertMessage.setSendAddr(emailProperty
						.getFrom());
				alertMessage.setTradeName("operator");
				alertMessage.setState("fail");
				alertMessage.setContext(alert.getRemarks());
				alertMessage.setTime(DateConvertor
						.getTradeTime());
				alertMessage.setUserName(emailProperty
						.getUsername());
				taskSendMail(alertMessage,
						alert.getOperatorPhone());
			}
		}

	}
	
	private synchronized void onUnloaded(){
		// should notify the administrator that the job list is empty
					// alertManager.sendMail(message, to, from, template);
					logger.error("Can't get the bankCodes/tradeCodes for the scheduled task T250011 ,program returned.");
					// send email ()
					/******************************************************************/
					logger.info("send email ..........loadRouteRule..............");
					int typeId = 6; // get roule type 6
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

							AlertMessage alertMessage = new AlertMessage();
							alertMessage.setOperatorName(alert.getOperatorName());
							alertMessage.setSendAddr(emailProperty.getFrom());
							alertMessage.setTradeName("operator");
							alertMessage.setContext(alert.getRemarks());
							alertMessage.setTime(DateConvertor.getTradeTime());
							alertMessage.setUserName(emailProperty.getUsername());
							noRulesSendMail(alertMessage, alert.getOperatorPhone());
						}
					}
	}
	
	

	List<String> bankCodes;

	List<String> tradeCodes;

	

	

	/**
	 * @param bankCodes
	 *            the bankCodes to set
	 */
	public void setBankCodes(List<String> bankCodes) {
		this.bankCodes = bankCodes;
	}


	// public static enum ScheculedTaskStateEnum {
	//
	// }

	

	public ScheduledTaskStateMachine() {
		prevDays=-1;
	}

	public List<OssAlert> alertList;

	public List<OssAlert> getAlertList() {
		return alertList;
	}

	public void setAlertList(List<OssAlert> alertList) {
		this.alertList = alertList;
	}

	
	// send SMS message
	@SuppressWarnings("unused")
	private void sendNote(String operatorPhone, String message) {
		System.out.println("短信通知");
		smsSender.send(operatorPhone, message);
	}

	/**
	 * 工作列表 --->空
	 * 
	 * @return send mail
	 */
	public void noRulesSendMail(final AlertMessage alert, final String to) {
		MimeMessagePreparator preparator = new MimeMessagePreparator() {

			@SuppressWarnings("unchecked")
			public void prepare(MimeMessage mimeMessage) throws Exception {

               MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
               mimeMessage.setSubject("警报信息");
               message.setTo(to);
               message.setFrom(alert.getSendAddr()); // could be parameterized...
               
               @SuppressWarnings("rawtypes")
               Map model = new HashMap();
               model.put("alert", alert);
               @SuppressWarnings("deprecation")
			String text = 
               VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "com/palmcommerce/funds/alert/service/impl/RouleMessage.vm", "utf-8", model);
               message.setText(text, true);
            }
         };
         this.mailSender.send(preparator);

	}

	/***
	 * 任务发布 失败
	 * 
	 * @return send mail
	 * ***/
	public void taskSendMail(final AlertMessage alert, final String to) {

		MimeMessagePreparator preparator = new MimeMessagePreparator() {

			@SuppressWarnings("unchecked")
			public void prepare(MimeMessage mimeMessage) throws Exception {
				MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
				mimeMessage.setSubject("警报信息");
				message.setTo(to);
				message.setFrom(emailProperty.getFrom()); // could be
															// parameterized...

				Map model = new HashMap();
				model.put("alert", alert);

				String text = VelocityEngineUtils
						.mergeTemplateIntoString(
								velocityEngine,
								"com/palmcommerce/funds/alert/service/impl/TaskMessage.vm",
								"utf-8", model);

				message.setText(text, true);
			}
		};
		this.mailSender.send(preparator);
	}

	
	/**
	 * 
	 * 
	 */

	HashMap<String, ScheduledTaskState> mapOfTasks = new HashMap<String, ScheduledTaskState>();

	private static final SimpleDateFormat bankDateFormat = new SimpleDateFormat(
			"yyyyMMdd");

	public  Date previousDate() {

		
		
		

		// Use the Calendar class to subtract one day
		Calendar calendar = Calendar.getInstance();
		String name=calendar.getTimeZone().getDisplayName();
	
	   TimeZone timezone = TimeZone.getTimeZone("GMT+08");
	   name=calendar.getTimeZone().getDisplayName();
	   logger.info("GMT+08 Time Zone:" + name );
	   calendar.setTimeZone(timezone);
	   
		//calendar.setTime(myDate);
		calendar.add(Calendar.DAY_OF_YEAR,prevDays);

		// Use the date formatter to produce a formatted date string
		Date previousDate = calendar.getTime();
		// String result = bankDateFormat.format(previousDate);
		String accountTime=DateConvertor.getBankTimeByDate(previousDate);
		try {
			previousDate=DateConvertor.parseBankTime(accountTime);
		} catch (ProtocolStorageException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 logger.info("AccountTime:" + accountTime );
		return previousDate;
	}

	static final SimpleDateFormat format = new SimpleDateFormat("HH");

	private int getCurrentHour() {
		Date d = new Date();

		String hour = format.format(d);
		int currentHour = Integer.parseInt(hour);
		return currentHour;
	}

	

	public static enum TaskState {
		PUBLISHED, PROCESSING, PROCEED_SUCCESS, PROCEED_FAILED, REPUBLISHED
	};
	public static final int INVALID=-1;
	public static final int ACTIVING=0;
	public static final int RECEIVING=1;
	public static final int PROCESSING=2;
	public static final int SENDING=3;
	public static final int TIMEOUT=4;
	public static final int FINISHED=5;
	public static final int ACTIVE_WAITING=6;
	
	
	public enum ScheduledState {
		
		
		STATE_INVALID(INVALID,"Invalid"),
		STATE_ACTIVING(ACTIVING,"Activing"),
		STATE_RECEIVING(RECEIVING,"Receiving"),
		STATE_PROCESSING(PROCESSING,"Processing"),
		STATE_SENDING(SENDING,"Sending"),
		STATE_TIMEOUT(TIMEOUT,"Timeout"),
		STATE_FINISHED(FINISHED,"Finished"),
		STATE_ACTIVE_WAITING(ACTIVE_WAITING,"active_waiting")
		;
		
		
		
		private final int state;
		private final String message;
		
		private ScheduledState(int state,String message) {
			this.message = message;
			this.state = state;
		}
		
		public boolean isState(ScheduledState state){
				return this.state==state.state;
		}
		
		public String toString(){
			return "ScheduledState [state=" +state+
					",message=" +message+
					"]";
		}

		/**
		 * @return the message
		 */
		public String getMessage() {
			return message;
		}

		/**
		 * @return the state
		 */
		public int getState() {
			return state;
		}
		public static void clearState(){
			try {
				File f=getLock();
				if(f.exists()){
					f.delete();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		@SuppressWarnings("unused")
		private static final String CRON_TAB = "funds.roo.cron";
		private static File getLock() throws IOException{
			String file=ScheduledTaskStateMachine.class.getResource("/").getFile();
			File f=new File(file+File.separator+CRON_TAB+File.separator+DateConvertor.getBankTime()+".lock");
			if(!f.exists()){
				f.createNewFile();
				//FileUtils.writes
				FileUtils.writeStringToFile(f, String.valueOf(ScheduledState.STATE_INVALID.getState()));
			}
			return f;
		}
		
		public static synchronized void pushState(ScheduledState state){
			try {
				File f=getLock();
				FileUtils.writeStringToFile(f, String.valueOf(state.state));
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		
		
		
		public static synchronized ScheduledState pullState(){
			ScheduledState  scheduledState=ScheduledState.STATE_INVALID;
			try {
				File f=getLock();
				String content=FileUtils.readFileToString(f);
				int state=-1;
				if(content==null||"".equals(content))
					content=String.valueOf(ScheduledState.STATE_INVALID.getState());
				if(content!=null){
					state=Integer.parseInt(content);
				}
				
				for(ScheduledState   v:ScheduledState.values()){
					if(v.state==state){
						scheduledState=v;
						break;
					}
				}
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return scheduledState;
		}
		

	}
	
	


}
