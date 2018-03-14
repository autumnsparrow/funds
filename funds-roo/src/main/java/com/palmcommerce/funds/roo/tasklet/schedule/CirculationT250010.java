package com.palmcommerce.funds.roo.tasklet.schedule;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import com.j256.simplejmx.common.JmxAttributeMethod;
import com.j256.simplejmx.common.JmxOperation;
import com.j256.simplejmx.common.JmxResource;
@JmxResource(domainName="com.palmcommerce.funds.roo.tasklet.schedule",
	beanName="CirculationT250010")
public class CirculationT250010 {

	// 保存当前的状态
	private volatile ThreadLocal<Integer> state = new ThreadLocal<Integer>();
	//记录错误次数
	private volatile ThreadLocal<Map<String,Integer>> errorTimes = new ThreadLocal<Map<String,Integer>>();

	// 程序的全部状态
	private final int WAITING = 0;
	private final int REQUEST_FILE = 1;
	private final int REQUEST_ERROR = 2;
	private final int PROCESSING = 3;
	private final int PROCESS_ERROR = 4;
	private final int SENDING_FILE = 5;
	private final int SEND_ERROR = 6;
	private final int FINISHED = 7;
	private final int TIME_OUT = 8;
	private final int UNKNOWN_ERROR = 9;
	//最大错误次数，超过则执行超时
	private final int maxErrorTime = 5;
	
	@Autowired
	OperationT250010 operation;
	
	@JmxOperation(description="Active Reconciliation")
	public void active(){
		requestFile();
	}
	@JmxAttributeMethod(description="View Current State")
	public String viewState(){
		int state = getState();
		if(state==0){
			return "WAITING:--->wait for request from trade client.";
		}
		if(state==1){
			return "REQUEST_FILE:--->request file from bank server.";
		}
		if(state==2){
			return "REQUEST_ERROR:-->error occured when request for a file from bank server.";
		}
		if(state==3){
			return "PROCESSING:--->process file and compare data to finish reconciliation.";
		}
		if(state==4){
			return "PROCESS_ERROR:--->process or reconciliation inner error occured.";
		}
		if(state==5){
			return "SENDING_FILE:--->sending file to trade client.";
		}
		if(state==6){
			return "SEND_ERROR:--->send file error occured.";
		}
		if(state==7){
			return "FINISHED:--->congratulations!";
		}
		if(state==8){
			return "TIME_OUT:--->error occured and time out,please check your program.";
		}
		if(state==9){
			return "UNKNOWN_ERROR:-->unknown error occured.";
		}
		return "NO_STATE:--->unknown state.";
	}
	@JmxOperation(description="Change Current State",
			parameterNames={"state"},
			parameterDescriptions="Please Input a Number 0:WAITING,1:REQUEST_FILE,2:REQUEST_ERROR,3:PROCESSING,4:PROCESS_ERROR,5:SENDING_FILE,6:SEND_ERROR,7:FINISHED,8:TIME_OUT,9:UNKNOWN_ERROR]")
	public void changeState(int state){
		putState(state);
	}

	/**
	 * 按照时间循环监听状态，状态改变执行相应的操作
	 */
	@Scheduled(fixedRate = 30 * 1000L)
	private void listeningState() {
		int currentState = getState();
		switch (currentState) {
			case WAITING: {
				break;
			}
			case REQUEST_FILE:{
				requestFile();
				break;
			}
			case REQUEST_ERROR:{
				requestFile();
				if(getErrorTime("REQUEST_ERROR")>maxErrorTime){
					putState(TIME_OUT);
				}
				break;
			}
			case PROCESSING:{
				processFile();
				break;
			}
			case PROCESS_ERROR:{
				processFile();
				if(getErrorTime("PROCESS_ERROR")>maxErrorTime){
					putState(TIME_OUT);
				}
				break;
			}
			case SENDING_FILE:{
				sendFile();
				break;
			}
			case SEND_ERROR:{
				sendFile();
				if(getErrorTime("SEND_ERROR")>maxErrorTime){
					putState(TIME_OUT);
				}
				break;
			}
			case FINISHED:{
				putState(WAITING);
				clear();
				break;
			}
			case UNKNOWN_ERROR:{
				putState(UNKNOWN_ERROR);
				if(getErrorTime("UNKNOWN_ERROR")>maxErrorTime){
					putState(TIME_OUT);
				}
				clear();
				break;
			}
			case TIME_OUT:{
				clear();
				break;
			}
			default:{
				break;
			}
		}

	}
	
	
	
	
	private synchronized void requestFile(){
		try{
			putState(REQUEST_FILE);
			operation.sendRequest();
			putState(PROCESSING);
		}catch(Exception e){
			putState(REQUEST_ERROR);
			countErrorTime("REQUEST_ERROR");
		}
	}
	
	private synchronized void processFile(){
		try{
			putState(PROCESSING);
			//TODO 文件处理
			putState(SENDING_FILE);
		}catch(Exception e){
			putState(PROCESS_ERROR);
			countErrorTime("PROCESS_ERROR");
		}
	}
	
	private synchronized void clear(){
		errorTimes = null;
	}
	
	private synchronized void sendFile(){
		try{
			putState(SENDING_FILE);
			//TODO 发送文件
			putState(FINISHED);
		}catch(Exception e){
			putState(SEND_ERROR);
			countErrorTime("SEND_ERROR");
		}
	}
	
	
	
	/**
	 * 错误发生时，错误次数加一
	 * 
	 * @param errorType
	 */
	private void countErrorTime(String errorType){
		Map<String,Integer> m = errorTimes.get();
		if(m==null){
			m=new HashMap<String,Integer>();
		}
		Integer times = m.get(errorType);
		if(times==null){
			times=1;
		}else{
			times +=1;
		}
		m.put(errorType, times);
		errorTimes.set(m);
	}
	
	/**
	 * 获取错误次数
	 * 
	 * @param errorType
	 * @return
	 */
	private int getErrorTime(String errorType){
		Map<String,Integer> m = errorTimes.get();
		if(m==null){
			return 0;
		}else{
			Integer time = m.get(errorType);
			if(time==null){
				return 0;
			}else{
				return time;
			}
		}
		
	}
	

	/**
	 * 获取当前的状态
	 * 
	 * @return
	 */
	private int getState() {
		Integer currentState = state.get();
		if (currentState == null) {
			return WAITING;
		} else {
			return currentState;
		}
	}

	/**
	 * 设置当前的状态
	 * 
	 * @param currentState
	 */
	private void putState(Integer currentState) {
		state.set(currentState);
	}

}
