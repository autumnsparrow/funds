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
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.ui.velocity.VelocityEngineUtils;

import com.ibm.icu.text.SimpleDateFormat;
import com.ibm.icu.util.Calendar;
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
import com.palmcommerce.funds.roo.util.DateConvertor;
import com.palmcommerce.funds.service.ProtocolProcessException;
import com.palmcommerce.oss.jpa.model.OssAlert;
import com.palmcommerce.oss.jpa.service.AlertService;
import com.palmcommerce.funds.service.ProtocolStorageException;
import com.palmcommerce.oss.jpa.service.OssReconciliationStaticsService;

/**
 * @author sparrow
 * 
 */
public class ScheduledTask implements Runnable {

	private static final Log logger = LogFactory.getLog(ScheduledTask.class);

	@Autowired
	ScheduledTradeClientProtocolHandler scheduledTradeClientProtocolHandler;

	@Autowired
	DefaultProtocolProcessHandler defaultProcotolProcessHandler;

	@Autowired
	ConfigurationManager configurationManager;
	@Autowired
	ScheduledTaskStateService scheduledTaskStateService;

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

	List<String> bankCodes;

	List<String> tradeCodes;

	int activeTaskHour;

	/**
	 * @return the activeTaskHour
	 */
	public int getActiveTaskHour() {
		return activeTaskHour;
	}

	/**
	 * @param activeTaskHour
	 *            the activeTaskHour to set
	 */
	public void setActiveTaskHour(int activeTaskHour) {
		this.activeTaskHour = activeTaskHour;
	}

	/**
	 * @return the taskRangeStart
	 */
	public int getTaskRangeStart() {
		return taskRangeStart;
	}

	/**
	 * @param taskRangeStart
	 *            the taskRangeStart to set
	 */
	public void setTaskRangeStart(int taskRangeStart) {
		this.taskRangeStart = taskRangeStart;
	}

	/**
	 * @return the taskRangeEnd
	 */
	public int getTaskRangeEnd() {
		return taskRangeEnd;
	}

	/**
	 * @param taskRangeEnd
	 *            the taskRangeEnd to set
	 */
	public void setTaskRangeEnd(int taskRangeEnd) {
		this.taskRangeEnd = taskRangeEnd;
	}

	int taskRangeStart;
	int taskRangeEnd;

	int taskRepeatInterval;

	public int getTaskRepeatInterval() {
		return taskRepeatInterval;
	}

	public void setTaskRepeatInterval(int taskRepeatInterval) {
		this.taskRepeatInterval = taskRepeatInterval;
	}

	/**
	 * @return the bankCodes
	 */
	public List<String> getBankCodes() {
		return bankCodes;
	}

	@SuppressWarnings("unused")
	private static final String CRON_TAB = "/funds.cron";

	/**
	 * @param bankCodes
	 *            the bankCodes to set
	 */
	public void setBankCodes(List<String> bankCodes) {
		this.bankCodes = bankCodes;
	}

	public static enum TaskState {
		PUBLISHED, PROCESSING, PROCEED_SUCCESS, PROCEED_FAILED, REPUBLISHED
	};

	// public static enum ScheculedTaskStateEnum {
	//
	// }

	ScheculedTaskStateEnum scheculedTaskStateEnum;

	public ScheduledTask() {
		// TODO Auto-generated constructor stub
		scheculedTaskStateEnum = ScheculedTaskStateEnum.StateProxyCheckingActive;
		isRunning = false;
	}

	public List<OssAlert> alertList;

	public List<OssAlert> getAlertList() {
		return alertList;
	}

	public void setAlertList(List<OssAlert> alertList) {
		this.alertList = alertList;
	}

	private boolean loadRouteRule() {
		boolean ret = true;
		logger.info("---------->loadRouterule ");
		
		this.bankCodes = new LinkedList<String>();
		this.tradeCodes = new LinkedList<String>();
		configurationManager.updateRouteRules();
		if (configurationManager.getRouteRuleResult() != null) {
			for (RouteRuleEntry entry : configurationManager
					.getRouteRuleResult().getRouteRuleEntries()) {
				if (entry.isBank())
					this.bankCodes.add(entry.getToCode());
				else {
					this.tradeCodes.add(entry.getToCode());
				}
			}
		} else {
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

			ret = false;

		}
		return ret;

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

	boolean isFailedEmailSend;

	volatile boolean isRunning;

	private void dispatchTask() {
		if (isRunning)
			return;

		if (scheculedTaskStateEnum == null)
			scheculedTaskStateEnum = ScheculedTaskStateEnum.StateProxyCheckingActive;

		// isRunning=true;
		try {
			do {
				isRunning = true;
				switch (scheculedTaskStateEnum) {
				case StateProxyCheckingActive: {

					int currentHour = getCurrentHour();
					logger.info("---------->StateProxyCheckingActive:"
							+ DateConvertor.getTradeTime());
					if ((currentHour >= taskRangeStart && currentHour <= taskRangeEnd)) {

						scheculedTaskStateEnum = ScheculedTaskStateEnum.StateProxyActive;

					} else {
						scheculedTaskStateEnum = ScheculedTaskStateEnum.StateInactive;

					}

				}
					break;
				case StateProxyActive: {
					logger.info("---------->StateProxyActive:"
							+ DateConvertor.getTradeTime());
					boolean loaded = loadRouteRule();
					
					if (!loaded)
						scheculedTaskStateEnum = ScheculedTaskStateEnum.StateInactive;
					else
						scheculedTaskStateEnum = ScheculedTaskStateEnum.StateProxyPublishing;
				}
					break;
				case StateProxyPublishing: {

					isFailedEmailSend = false;
					logger.info("---------->StateProxyPublishingOrStateProxyActive:"+ DateConvertor.getTradeTime());

					List<ScheduledTaskState> tasks = scheduledTaskStateService
							.findScheduledTaskStatesByBankDateEqualsAndIsBank(
									previousDate(), Boolean.valueOf(true));
					// .findScheduledTaskStatesByBankDateEquals(previouseDate);

					if (tasks == null || (tasks != null && tasks.size() == 0)) {
						scheculedTaskStateEnum = ScheculedTaskStateEnum.StateProxyPublishing;
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
						tasks = scheduledTaskStateService
								.findScheduledTaskStatesByBankDateEqualsAndIsBank(
										previousDate(), Boolean.valueOf(true));
						// .findScheduledTaskStatesByBankDateEquals(previouseDate);
					}

					// query if the task published.
					if (tasks != null && this.bankCodes != null) {
						if (tasks.size() == this.bankCodes.size()) {
							// task has been published.
							logger.info("---------->all bank code has been published!");
							scheculedTaskStateEnum = ScheculedTaskStateEnum.StateProxyPublished;
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
							scheculedTaskStateEnum = ScheculedTaskStateEnum.StateProxyPublished;
						}
					}

				}
					break;

				case StateProxyPublished: {
					logger.info("---------->StateProxyPublished:"+ DateConvertor.getTradeTime());

					
					isFailedEmailSend = false;
					List<ScheduledTaskState> tasks = scheduledTaskStateService
							.findScheduledTaskStatesByBankDateEqualsAndIsBank(
									previousDate(), Boolean.valueOf(true));
					// .findScheduledTaskStatesByBankDateEqualsAndTranscodeEquals(previousDate(),
					// transcode);
					// .findScheduledTaskStatesByBankDateEquals(previousDate());

					// re-enable the failed task to republished state

					// TODO Auto-generated method stub
					for (ScheduledTaskState task : tasks) {
						// Task T250011
						logger.info("Sending the request:"
								+ task.getProcessState() + "["
								+ TaskState.PUBLISHED.ordinal() + "-PUBLISHED|"
								+ TaskState.PROCEED_FAILED.ordinal() + "-PROCESS_FAILED]");

						if (task.getProcessState() == TaskState.PROCESSING
								.ordinal()) {
							logger.info("------>PROCESSING: bankcode="+task.getBankCode());
							long processingTime = task.getProcessDatetime()
									.getTime();
							long currentTime = new Date().getTime();
							if (currentTime - processingTime > taskRepeatInterval * 1000) {
								task.setProcessState(TaskState.PROCEED_FAILED
										.ordinal());
								task.setProcessDatetime(new Date());
								scheduledTaskStateService
										.updateScheduledTaskState(task);
							}

						}

						if (task.getProcessState() == TaskState.PUBLISHED
								.ordinal()
								|| task.getProcessState() == TaskState.PROCEED_FAILED
										.ordinal()) {

							T250011 t = (T250011) ProtocolDriverManager
									.instance("250011", configurationManager
											.getServerRules().getProxy()
											.getNodeCode(), task.getBankCode());
							t.request.setAccountTime(DateConvertor
									.getBankTimeByDate(previousDate()));

							// try {

							logger.info("Sending the request:" + t.toString());
							// Release that par when connection.
							scheduledTradeClientProtocolHandler.send(t);

							/*
							 * try { t.response=new T250011.Response();
							 * t.response
							 * .setReason("ChargeData.20131110.P0000001.txt");
							 * t.
							 * response.setCheckFileSize(String.valueOf(10389));
							 * scheduledTradeClientProtocolHandler.t250011(t);
							 * //task.setProcessState(TaskState.PROCEED_SUCCESS.
							 * ordinal()); } catch (ProtocolStorageException e)
							 * { // TODO Auto-generated catch block
							 * e.printStackTrace(); }
							 */

							task.setProcessState(TaskState.PROCESSING.ordinal());
							task.setRetries(task.getRetries() + 1);
							task.setProcessDatetime(new Date());
							scheduledTaskStateService
									.updateScheduledTaskState(task);
						}

					}

					scheculedTaskStateEnum = ScheculedTaskStateEnum.StateProxyProcessing;
				}
					break;
				case StateProxyProcessing: {
					logger.info("---------->StateProxyProcessing:"+ DateConvertor.getTradeTime());
					isFailedEmailSend = false;
					boolean shouldActiveTradeTask = true;

					List<ScheduledTaskState> tasks = scheduledTaskStateService
							.findScheduledTaskStatesByBankDateEqualsAndIsBank(
									previousDate(), Boolean.valueOf(true));
					// .findScheduledTaskStatesByBankDateEqualsAndTranscodeEquals(previousDate(),
					// transcode);
					// .findScheduledTaskStatesByBankDateEquals(previousDate());
					for (ScheduledTaskState task : tasks) {
						if (task.getProcessState() != TaskState.PROCEED_SUCCESS
								.ordinal()) {
							shouldActiveTradeTask = false;
						}
					}
					if (shouldActiveTradeTask) {
						// scheculedTaskStateEnum=StateProxyProceedFinsihed;
						scheculedTaskStateEnum = ScheculedTaskStateEnum.StateTradeActive;
					} else {
						scheculedTaskStateEnum = ScheculedTaskStateEnum.StateProxyProcessingFailed;
					}

				}
					break;
				case StateProxyProcessingFailed: {
					logger.info("---------->StateProxyProcessingFailed:"+ DateConvertor.getTradeTime());
					// isFailedEmailSend=false;
					// send email.
					/***************************************************************************/
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

					
					scheculedTaskStateEnum = ScheculedTaskStateEnum.StateProxyPublished;
				}
					break;
				case StateProxyProceedFinsihed: {
					logger.info("---------->StateProxyProceedFinsihed:"+ DateConvertor.getTradeTime());
					isFailedEmailSend = false;
					scheculedTaskStateEnum = ScheculedTaskStateEnum.StateTradeActive;
					logger.info("StateProxyProceedFinsihed!");
				}
					break;
				case StateTradeActive:
				case StateTradePublishing: {
					isFailedEmailSend = false;
					logger.info("---------->StateTradeActiveOrStateTradeActive:"+ DateConvertor.getTradeTime());

					List<ScheduledTaskState> tasks = scheduledTaskStateService
							.findScheduledTaskStatesByBankDateEqualsAndIsBank(
									previousDate(), Boolean.valueOf(false));
					// .findScheduledTaskStatesByBankDateEquals(previouseDate);

					if (tasks == null || (tasks != null && tasks.size() == 0)) {
						scheculedTaskStateEnum = ScheculedTaskStateEnum.StateTradePublishing;
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
							scheculedTaskStateEnum = ScheculedTaskStateEnum.StateTradePublished;
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
							scheculedTaskStateEnum = ScheculedTaskStateEnum.StateTradePublished;
						}
					}

				}
					break;

				case StateTradePublished: {
					logger.info("---------->StateTradePublished:"+ DateConvertor.getTradeTime());


					isFailedEmailSend = false;
					List<ScheduledTaskState> tasks = scheduledTaskStateService
							.findScheduledTaskStatesByBankDateEqualsAndIsBank(
									previousDate(), Boolean.valueOf(false));
					// .findScheduledTaskStatesByBankDateEqualsAndTranscodeEquals(previousDate(),
					// transcode);
					// .findScheduledTaskStatesByBankDateEquals(previousDate());

					// re-enable the failed task to republished state

					// TODO Auto-generated method stub
					for (ScheduledTaskState task : tasks) {

						logger.info("Sending the request:"
								+ task.getProcessState() + "["
								+ TaskState.PUBLISHED.ordinal() + "|"
								+ TaskState.PROCEED_FAILED.ordinal() + "]");

						if (task.getProcessState() == TaskState.PROCESSING
								.ordinal()) {
							long processingTime = task.getProcessDatetime()
									.getTime();
							long currentTime = new Date().getTime();
							if (currentTime - processingTime > taskRepeatInterval * 1000) {
								task.setProcessState(TaskState.PROCEED_FAILED
										.ordinal());
								task.setProcessDatetime(new Date());
								scheduledTaskStateService
										.updateScheduledTaskState(task);
							}

						}
						// Task T250011
						if (task.getProcessState() == TaskState.PUBLISHED
								.ordinal()
								|| task.getProcessState() == TaskState.PROCEED_FAILED
										.ordinal()) {

							T250011 t = (T250011) ProtocolDriverManager
									.instance("250011", task.getTradecode(),
											configurationManager
													.getServerRules()
													.getProxy().getNodeCode());
							t.request.setAccountTime(DateConvertor
									.getBankTimeByDate(previousDate()));

							// try {

							logger.info("Sending the request:" + t.toString());
							boolean ret = true;
							// Release that part when connection test.
							//
							try {
								ret = defaultProcotolProcessHandler.t250011(t);
							} catch (ProtocolProcessException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
								ret = false;
							}

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

					scheculedTaskStateEnum = ScheculedTaskStateEnum.StateTradeProcessing;

				}
					break;
				case StateTradeProcessing: {
					logger.info("---------->StateTradeProcessing:"+ DateConvertor.getTradeTime());

					isFailedEmailSend = false;

					boolean shouldActiveTradeTask = true;

					List<ScheduledTaskState> tasks = scheduledTaskStateService
							.findScheduledTaskStatesByBankDateEqualsAndIsBank(
									previousDate(), false);
					// .findScheduledTaskStatesByBankDateEqualsAndTranscodeEquals(previousDate(),
					// transcode);
					// .findScheduledTaskStatesByBankDateEquals(previousDate());
					for (ScheduledTaskState task : tasks) {
						if (task.getProcessState() != TaskState.PROCEED_SUCCESS
								.ordinal()) {
							shouldActiveTradeTask = false;
						}
					}
					if (shouldActiveTradeTask) {
						// scheculedTaskStateEnum=StateProxyProceedFinsihed;
						scheculedTaskStateEnum = ScheculedTaskStateEnum.StateTradeProcessFinished;
					} else {
						scheculedTaskStateEnum = ScheculedTaskStateEnum.StateTradeProcessFailed;
					}

				}
					break;
				case StateTradeProcessFailed: {
					// isFailedEmailSend=false;
					logger.info("---------->StateTradeProcessFailed:"+ DateConvertor.getTradeTime());

					scheculedTaskStateEnum = ScheculedTaskStateEnum.StateTradeActive;
				}
					break;
				case StateTradeProcessFinished: {
					// isFailedEmailSend=false;

					logger.info("---------->StateTradeProcessFinished:"+ DateConvertor.getTradeTime());

					
					scheculedTaskStateEnum = ScheculedTaskStateEnum.StateJobDone;

				}
					break;
				case StateJobDone: {
					int currentHour = getCurrentHour();
					logger.info("StateJobDone:" + DateConvertor.getTradeTime());
					if ((currentHour >= taskRangeStart && currentHour <= taskRangeEnd)) {

						scheculedTaskStateEnum = ScheculedTaskStateEnum.StateJobDone;

					} else {
						scheculedTaskStateEnum = ScheculedTaskStateEnum.StateProxyActive;

					}
				}
					break;

				case StateInactive: {
					logger.info("---------->StateInactive:"+ DateConvertor.getTradeTime());

					int currentHour = getCurrentHour();
					
					if ((currentHour >= taskRangeStart && currentHour <= taskRangeEnd)) {

						scheculedTaskStateEnum = ScheculedTaskStateEnum.StateProxyActive;

					} else {
						scheculedTaskStateEnum = ScheculedTaskStateEnum.StateInactive;

					}

					if (!isFailedEmailSend) {
						logger.info("StateInactive and! isFailedEmailSend"
								+ isFailedEmailSend);

						// check Task of proxy
						boolean taskFinished = true;
						List<ScheduledTaskState> tasks = scheduledTaskStateService
						// .findScheduledTaskStatesByBankDateEqualsAndTranscodeEquals(previousDate(),
						// transcode);
								.findScheduledTaskStatesByBankDateEquals(previousDate());
						for (ScheduledTaskState task : tasks) {
							if (task.getProcessState() != TaskState.PROCEED_SUCCESS
									.ordinal()) {
								taskFinished = false;
							}
						}

						// Task Of Trade

						if (!taskFinished) {

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
						scheculedTaskStateEnum = ScheculedTaskStateEnum.StateProxyCheckingActive;
						isFailedEmailSend = true;

					}
				}
					break;
				default: {
					scheculedTaskStateEnum = ScheculedTaskStateEnum.StateInactive;
				}
					break;

				}

			} while (scheculedTaskStateEnum != ScheculedTaskStateEnum.StateInactive);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		} finally {
			isRunning = false;

		}

	}

	/**
	 * 
	 * 
	 */

	HashMap<String, ScheduledTaskState> mapOfTasks = new HashMap<String, ScheduledTaskState>();

	private static final SimpleDateFormat bankDateFormat = new SimpleDateFormat(
			"yyyyMMdd");

	private Date previousDate() {

		String dateString = bankDateFormat.format(new Date());
		Date myDate = null;
		try {
			myDate = bankDateFormat.parse(dateString);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Use the Calendar class to subtract one day
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(myDate);
		calendar.add(Calendar.DAY_OF_YEAR, -1);

		// Use the date formatter to produce a formatted date string
		Date previousDate = calendar.getTime();
		// String result = bankDateFormat.format(previousDate);

		return previousDate;
	}

	static final SimpleDateFormat format = new SimpleDateFormat("HH");

	private int getCurrentHour() {
		Date d = new Date();

		String hour = format.format(d);
		int currentHour = Integer.parseInt(hour);
		return currentHour;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run() task will execute every 1 hour.
	 * 
	 * the task will execute every 5 minutes.
	 * 
	 * starting execute the jobs from 1:00 am to 8:00 am, retrying the task
	 * every 1 hour , util the jobs finished.
	 * 
	 * doing the jobs like that.
	 * 
	 * 
	 * 1,when the time need to generate the todo task list in a
	 * directory,generate it.
	 * 
	 * 2,in the right time range do jobs until it succeed.,if any job failed to
	 * alarm.
	 */

	public void run() {
		logger.info(" scheduled task routine .........................");
		// checking is bank
		try {
			dispatchTask();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// publishJobs();
		//
		// doJobs();
		//
		//
		// checkJobs();
		//
		// checkJobs();
		//
		// doTradeJobs();
		//
		//
		// shouldSendTaskFailedEmail();
		//

	}

	// /**
	// * publish the job
	// *
	// *
	// */
	// private void publishJobs() {
	//
	// int currentHour = getCurrentHour();
	// if (currentHour == this.activeTaskHour) {
	//
	// // steps,1. fetch the pre-date date time
	// Date previouseDate = previousDate();
	//
	// List<ScheduledTaskState> tasks = scheduledTaskStateService
	// .findScheduledTaskStatesByBankDateEquals(previouseDate);
	//
	// this.bankCodes = new LinkedList<String>();
	// configurationManager.updateRouteRules();
	// if (configurationManager.getRouteRuleResult() != null) {
	// for (RouteRuleEntry entry : configurationManager
	// .getRouteRuleResult().getRouteRuleEntries()) {
	// if (entry.isBank())
	// this.bankCodes.add(entry.getToCode());
	// }
	// } else {
	// // should notify the administrator that the job list is empty
	// // alertManager.sendMail(message, to, from, template);
	// logger.error("Can't get the bankCodes for the scheduled task T250011 ,program returned.");
	// // send email
	//
	// return;
	// }
	//
	//
	//
	// tasks = scheduledTaskStateService
	// .findScheduledTaskStatesByBankDateEquals(previouseDate);
	//
	// // query if the task published.
	// if (tasks != null && this.bankCodes != null) {
	// if (tasks.size() == this.bankCodes.size()) {
	// // task has been published.
	// logger.info("all bank code has been published!");
	//
	// } else {
	// mapOfTasks.clear();
	// // alert some task don't published.
	// for (ScheduledTaskState task : tasks) {
	// mapOfTasks.put(task.getBankCode(), task);
	// }
	//
	// for (String bankCode : bankCodes) {
	// if (!mapOfTasks.containsKey(bankCode)) {
	// logger.error("bank code: " + bankCode
	// + " failed to publish scheduled task!");
	// }
	// }
	//
	// }
	// }
	//
	// }
	//
	// }
	//
	// private boolean checkJobs() {
	//
	// int currentHours = getCurrentHour();
	//
	// // doing the job in the range of time
	// boolean shouldActiveTradeTask = true;
	// if (currentHours >= taskRangeStart && currentHours <= taskRangeEnd) {
	// isFailedEmailSend=false;
	// List<ScheduledTaskState> tasks = scheduledTaskStateService
	// //
	// .findScheduledTaskStatesByBankDateEqualsAndTranscodeEquals(previousDate(),
	// // transcode);
	// .findScheduledTaskStatesByBankDateEquals(previousDate());
	// for (ScheduledTaskState task : tasks) {
	// if (task.getProcessState() != TaskState.PROCEED_SUCCESS) {
	// shouldActiveTradeTask = false;
	// }
	// }
	//
	// }
	// if (shouldActiveTradeTask) {
	//
	// publishTradeJobs();
	// }
	//
	// return shouldActiveTradeTask;
	//
	// }
	//
	// private void doJobs() {
	//
	// int currentHours = getCurrentHour();
	//
	// // doing the job in the range of time
	// if (currentHours >= taskRangeStart && currentHours <= taskRangeEnd) {
	//
	// List<ScheduledTaskState> tasks = scheduledTaskStateService
	// //
	// .findScheduledTaskStatesByBankDateEqualsAndTranscodeEquals(previousDate(),
	// // transcode);
	// .findScheduledTaskStatesByBankDateEquals(previousDate());
	//
	// // re-enable the failed task to republished state
	//
	// // TODO Auto-generated method stub
	// for (ScheduledTaskState task : tasks) {
	// // Task T250011
	// if (task.getProcessState() == TaskState.PUBLISHED.ordinal()
	// || task.getProcessState() == TaskState.PROCEED_FAILED
	// .ordinal()) {
	//
	// T250011 t = (T250011) ProtocolDriverManager.instance(
	// "250011", IProtocol.DEFAULT_FROM_CODE,
	// task.getBankCode());
	// t.request.setAccountTime(DateConvertor
	// .getBankTimeByDate(previousDate()));
	// // try {
	//
	// logger.info("Sending the request:" + t.toString());
	// scheduledTradeClientProtocolHandler.send(t);
	// task.setProcessState(TaskState.PROCESSING.ordinal());
	// task.setRetries(task.getRetries() + 1);
	// task.setProcessDatetime(new Date());
	// scheduledTaskStateService.updateScheduledTaskState(task);
	//
	// // }
	// //
	// // catch (ProtocolProcessException e) {
	// // // TODO Auto-generated catch block
	// // e.printStackTrace();
	// // task.setProcessDatetime(new Date());
	// // task.setProcessState(TaskState.PROCEED_FAILED.ordinal());
	// // scheduledTaskStateService.updateScheduledTaskState(task);
	// // logger.error("ScheduledTask execute exeption:"+e.getMessage());
	// //
	//
	// /*
	// * if(task.getTranscode=="T250011"){ T250011 t = (T250011)
	// * ProtocolDriverManager.instance("250011",
	// * IProtocol.DEFAULT_FROM_CODE, task.getBankCode());
	// * t.request.setAccountTime(DateConvertor.getBankTime());
	// * try { logger.info("Sending the request:"+t.toString());
	// * scheduledTradeClientProtocolHandler.send(t);
	// * task.setProcessState(TaskState.PROCESSING.ordinal());
	// * task.setRetries(task.getRetries()+1);
	// * task.setProcessDatetime(new Date());
	// * scheduledTaskStateService.updateScheduledTaskState(task);
	// *
	// * } catch (ProtocolProcessException e) { // TODO
	// * Auto-generated catch block e.printStackTrace();
	// * task.setProcessDatetime(new Date());
	// * task.setProcessState(TaskState.PROCEED_FAILED.ordinal());
	// * scheduledTaskStateService.updateScheduledTaskState(task);
	// * logger
	// * .error("ScheduledTask execute exeption:"+e.getMessage());
	// * } }else if(task.getTranscode=="T250010"){ T250010 t =
	// * (T250010) ProtocolDriverManager.instance("T250010",
	// * IProtocol.DEFAULT_FROM_CODE, task.getBankCode());
	// * t.request.setBindingTime(DateConvertor.getBankTime());
	// * try { logger.info("Sending the request:"+t.toString());
	// * scheduledTradeClientProtocolHandler.send(t);
	// * task.setProcessState(TaskState.PROCESSING.ordinal());
	// * task.setRetries(task.getRetries()+1);
	// * task.setProcessDatetime(new Date());
	// * scheduledTaskStateService.updateScheduledTaskState(task);
	// *
	// * } catch (ProtocolProcessException e) { // TODO
	// * Auto-generated catch block e.printStackTrace();
	// * task.setProcessDatetime(new Date());
	// * task.setProcessState(TaskState.PROCEED_FAILED.ordinal());
	// * scheduledTaskStateService.updateScheduledTaskState(task);
	// * logger
	// * .error("ScheduledTask execute exeption:"+e.getMessage());
	// * } }else if(task.getTranscode=="T250009"){ T250009 t =
	// * (T250009) ProtocolDriverManager.instance("T250009",
	// * IProtocol.DEFAULT_FROM_CODE, task.getBankCode());
	// * t.request.setAccountTime(DateConvertor.getBankTime());
	// * try { logger.info("Sending the request:"+t.toString());
	// * scheduledTradeClientProtocolHandler.send(t);
	// * task.setProcessState(TaskState.PROCESSING.ordinal());
	// * task.setRetries(task.getRetries()+1);
	// * task.setProcessDatetime(new Date());
	// * scheduledTaskStateService.updateScheduledTaskState(task);
	// *
	// * } catch (ProtocolProcessException e) { // TODO
	// * Auto-generated catch block e.printStackTrace();
	// * task.setProcessDatetime(new Date());
	// * task.setProcessState(TaskState.PROCEED_FAILED.ordinal());
	// * scheduledTaskStateService.updateScheduledTaskState(task);
	// * logger
	// * .error("ScheduledTask execute exeption:"+e.getMessage());
	// * }
	// */
	// // }
	// }
	// }
	// }
	// }
	//
	// void publishTradeJobs() {
	//
	// }
	//
	// void doTradeJobs() {
	// int currentHours = getCurrentHour();
	//
	// // doing the job in the range of time
	// if (currentHours >= taskRangeStart && currentHours <= taskRangeEnd) {
	//
	// }
	//
	// }
	//
	//
	//
	//
	//
	// boolean isFailedEmailSend;
	// void shouldSendTaskFailedEmail(){
	// int currentHours = getCurrentHour();
	// if (!(currentHours >= taskRangeStart && currentHours <= taskRangeEnd)) {
	// if(!isFailedEmailSend){
	// // send email
	// // check Task of proxy
	// boolean taskFinished=true;
	// List<ScheduledTaskState> tasks = scheduledTaskStateService
	// //
	// .findScheduledTaskStatesByBankDateEqualsAndTranscodeEquals(previousDate(),
	// // transcode);
	// .findScheduledTaskStatesByBankDateEquals(previousDate());
	// for (ScheduledTaskState task : tasks) {
	// if (task.getProcessState() != TaskState.PROCEED_SUCCESS) {
	// taskFinished = false;
	// }
	// }
	//
	// // Task Of Trade
	//
	//
	//
	// if(!taskFinished){
	//
	//
	// }
	//
	//
	// isFailedEmailSend=true;
	// }
	// }
	//
	//
	// }

}
