package com.palmcommerce.funds.trade.ibatis.impl;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;

import com.j256.simplejmx.common.JmxAttributeField;
import com.j256.simplejmx.common.JmxAttributeMethod;
import com.j256.simplejmx.common.JmxOperation;
import com.j256.simplejmx.common.JmxResource;
import com.palmcommerce.funds.configuration.v2.ConfigurationManager;
import com.palmcommerce.funds.connection.mina.protocol.AbstractClientSideProcessor;
import com.palmcommerce.funds.id.impl.GlobalIdGenerator;
import com.palmcommerce.funds.protocol.ProtocolDriverManager;
import com.palmcommerce.funds.protocol.covertor.exception.ProtocolConvertorException;
import com.palmcommerce.funds.protocol.impl.Header;
import com.palmcommerce.funds.protocol.impl.IProtocol;
import com.palmcommerce.funds.protocol.impl.p2t.P240003;
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
import com.palmcommerce.funds.protocol.trade.IBankProtocolHandler;
import com.palmcommerce.funds.service.ProtocolProcessException;
import com.palmcommerce.funds.service.ProtocolStorageException;
import com.palmcommerce.funds.trade.ibatis.bean.AccountBankBean;
import com.palmcommerce.funds.trade.ibatis.bean.BankBean;
import com.palmcommerce.funds.trade.ibatis.bean.PayMoneyBean;
import com.palmcommerce.funds.trade.ibatis.bean.UserInfo;
import com.palmcommerce.funds.trade.ibatis.services.PaymentService;
import com.palmcommerce.funds.trade.ibatis.services.UserInfoService;
import com.palmcommerce.funds.util.DateConvertor;
@JmxResource(domainName="com.palmcommerce.funds.trade.ibatis.impl",beanName="OldTradeClientProtocolHandler")
public class OldTradeClientProtocolHandler extends AbstractClientSideProcessor
		implements IBankProtocolHandler,IReconciliationJmx {
	
	private static final Log logger=LogFactory.getLog(OldTradeClientProtocolHandler.class);
	
	private DecimalFormat decimalFormat = new DecimalFormat("#.##");
	
	static {

		ProtocolDriverManager.registger(T250011Entry.class, T250011Entry.Request.class, T250011Entry.Response.class);
	}

	
	@Autowired
	OldTradeProtocolHandler tradeProtocolHandler;
	@Autowired
	ConfigurationManager configurationManager;
	
	
	/**
	 * @return the paymentService
	 */
	public PaymentService getPaymentService() {
		return paymentService;
	}

	/**
	 * @param paymentService the paymentService to set
	 */
	public void setPaymentService(PaymentService paymentService) {
		this.paymentService = paymentService;
	}
	@Autowired
	PaymentService paymentService;
	
	@Autowired
	UserInfoService userInfoService;

	@Value("${reconcilation.local.dir}")
	String localDir;

	public OldTradeClientProtocolHandler() {

	}

	
	@JmxOperation(description="start reconciliation")
	@Scheduled(cron="${reconciliation.cron.active}")
	public void startReconciliation(){
		String bankDate=DateConvertor.getBankTimeByDate(previousDate());
		String fileName = "ChargeData."+bankDate+".T0000003";
		String path = String.format("%s" + File.separator + "%s",
				localDir, fileName);
		File f = new File(path);
		if(f.exists()&&f.length()>0){
			reconciliation(fileName, bankDate);
		}
		
	}
	
	@JmxAttributeMethod(description="current state")
	public String getCurrentState(){
		return State.getState().toString();
	}
	
	
	@JmxOperation(description="stop reconciliation")
	@Scheduled(cron="${reconciliation.cron.finished}")
	public void stopReconcilition(){
		if(State.getState()!=State.FINISHED){
		}
		State.clearState();
	}
	
	@JmxOperation(description="cleanStates")
	public void cleanStates(){
		State.clearState();
	}
	@JmxOperation(description="reconciliation",parameterNames={"date"})
	public void reconciliation(String date){
		try {
			sendRequest(DateConvertor.parseBankTime(date));
		} catch (ProtocolStorageException e) {
			e.printStackTrace();
		}
	}
	@JmxAttributeMethod(description="get reconciliation date")
	public String getReconciliationDate(){
		 String previousDate=DateConvertor.getBankTimeByDate(previousDate());
		 return previousDate;
	}
	
	@JmxOperation(description="reconciliation",parameterNames={"date"})
	public void changeReconciliationDate(String d){
		this.prevDate=Integer.parseInt(d);
	}
	@JmxOperation(description="reconciliationByDate",parameterNames={"filename","bankdate"})
	public void reconciliationByDate(String filename,String bankdate){
		
			reconciliation(filename,bankdate);
		
	}
	
	int prevDate=-1;
	
	private void sendRequest(Date date){
		try {
			T250011 t=(T250011) ProtocolDriverManager.instance("250011", this.configurationManager.getServerRules().getProxy().getNodeCode(), "P0000003");
			String previousDate=DateConvertor.getBankTimeByDate(date);
			t.request.setAccountTime(previousDate);
			this.send(t);
		} catch (Exception e) {
			e.printStackTrace();
			State.putState(State.ERROR);
		}
		
		State.putState(State.RECONCING);
		
	}
	
	
	
	
	private static final SimpleDateFormat bankDateFormat = new SimpleDateFormat(
			"yyyyMMdd");

	private Date previousDate() {

		String dateString = bankDateFormat.format(new Date());
		Date myDate = null;
		try {
			myDate = bankDateFormat.parse(dateString);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		// Use the Calendar class to subtract one day
		Calendar calendar = Calendar.getInstance();
		
		String name=calendar.getTimeZone().getDisplayName();
		logger.info("Current Time Zone:" + name );
	    TimeZone tz = TimeZone.getTimeZone("GMT+08");
		logger.info("GMT+08 Time Zone:" + name );
		calendar.setTimeZone(tz);
		//calendar.setTime(myDate);
		calendar.add(Calendar.DAY_OF_YEAR, prevDate);

		// Use the date formatter to produce a formatted date string
		Date previousDate = calendar.getTime();
		// String result = bankDateFormat.format(previousDate);

		return previousDate;
	}
	
	

	
	
	public boolean t250001(T250001 t) throws ProtocolStorageException {
		boolean ret = false;

		return ret;
	}

	public boolean t250002(T250002 t) throws ProtocolStorageException {
		boolean ret = false;

		return ret;
	}

	public boolean t250004(T250004 t) throws ProtocolStorageException {
		boolean ret = false;

		return ret;
	}

	public boolean t250005(T250005 t) throws ProtocolStorageException {
		boolean ret = false;

		return ret;
	}

	public boolean t250006(T250006 t) throws ProtocolStorageException {
		boolean ret = false;

		return ret;
	}

	public boolean t250007(T250007 t) throws ProtocolStorageException {
		boolean ret = false;

		return ret;
	}

	public boolean t250008(T250008 t) throws ProtocolStorageException {
		boolean ret = false;

		return ret;
	}

	public boolean t250009(T250009 t) throws ProtocolStorageException {
		boolean ret = false;

		return ret;
	}

	public boolean t250010(T250010 t) throws ProtocolStorageException {
		boolean ret = false;

		return ret;
	}

	private String isNull(String s) {
		if (s == null)
			s = "";
		return s;
	}
	
	private boolean comparePayMoney(PayMoneyBean bank,PayMoneyBean local){
		boolean ret=false;
		ret=(bank.getMoney()==local.getMoney())&&
				compareString(bank.getUserId(),local.getUserId());
//				&&
//				compareString(bank.getSeriesNo(),(local.getSeriesNo()))&&
//				compareString(bank.getBankCode(),(local.getBankCode()))&&
//				compareString(bank.getAccountTime(),(local.getAccountTime()));
		return ret;
	}
	
	@SuppressWarnings("unchecked")
	private synchronized void reconciliation(String filename,String bankdate){
		String path = String.format("%s" + File.separator + "%s",
				localDir, filename);
		// check the file size
		
		File f = new File(path);
		logger.info("----Reconciliation-->:filename=" +
				"" +path+
				",size=" +f.length()+
				",bankdate=" +bankdate+
				"]");
		if(f.length()==0){
			logger.info("--Reconciliation file is empty don't do reconciliation!");
			return;
		}
		try {
			List<String> lines = FileUtils.readLines(f, "utf-8");
			List<T250011Entry> entries = new LinkedList<T250011Entry>();
			for (String line : lines) {
				logger.info("["+line+"]");
				T250011Entry entry=new T250011Entry("250011");
				entry.setRequestObject(entry.request);
				entry.setRequestPacket(line);
				entry.unmashall(true);
				entries.add(entry);
			}
			
			
			//SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
			HashMap<String,AccountBankBean> mapOfAccountBankBean=new HashMap<String,AccountBankBean>();
			List<BankBean> banks=userInfoService.getBankAll();
			
			for(BankBean  bank:banks){
				String bankcode=bank.getBankId();
				if(!mapOfAccountBankBean.containsKey(bankcode)){
					AccountBankBean accountBankBean=new AccountBankBean();
					accountBankBean.setBankCode(bankcode);
					accountBankBean.setCreateTime(new Date());
					accountBankBean.setTradeTime(bankdate);
					accountBankBean.setOperatorTime(new Date());
					accountBankBean.setStatus(1);
					mapOfAccountBankBean.put(bankcode,accountBankBean);
				}
			}
			//paymentService.deleteAccountBankBean(bankdate, bankCode);
			
			//
			
			/**
			 * Detail process of reconciliation:
			 * 
			 * Bank record as baseline:
			 * 
			 * localPayMoneyBean is null, state less,need insert 
			 * localPayMoneyBean exists,
			 * 					
			 * 							compared  :  corrected
			 * 							mismatched : wrong
			 * 
			 * 
			 * 
			 * 
			 * 
			 */
			
			for(T250011Entry entry:entries){
			
				PayMoneyBean payMoney=wrapperT250011Entry(entry);
				payMoney.setMoney(Double.valueOf(decimalFormat.format(payMoney.getMoney())));
				PayMoneyBean localPayMoneyBean=paymentService.getPayMoneyBySeriesNo(payMoney.getSeriesNo(), payMoney.getBankCode(), payMoney.getAccountTime());
				// compare what's?
				// 1. checking the local database account type [reconciliation state]
				AccountBankBean accountBankBean=mapOfAccountBankBean.get(payMoney.getBankCode());
				
				// 
				// state 1. [compared with bank] less state add.
				if(localPayMoneyBean==null){
					UserInfo info=userInfoService.getUser(payMoney.getUserId());
					String partnerId=info.getPartnerId();
					payMoney.setSeriesNo(payMoney.getSeriesNo());
					payMoney.setCreateTime(new Date());
					payMoney.setPartnerId(partnerId);
					payMoney.setAccountType(UserBankAccountState.LESS.getState());
					if(payMoney.getSeriesNo()!=null&&!"".equals(payMoney.getSeriesNo())){
						payMoney.setBalanceMoney(payMoney.getMoney());
						logger.info("R+"+payMoney.getMoney()+">|LESS|"+payMoney.toString());
						paymentService.insertPayMoneyBean(payMoney);
					}else{
						logger.info("R<>"+payMoney.getMoney()+">|LESS(nopaymentserial)|"+payMoney.toString());
					}
					
					
					if(accountBankBean!=null){
						accountBankBean.setLackNum(accountBankBean.getLackNum()+1);
						accountBankBean.setLackMoney(accountBankBean.getLackMoney()+payMoney.getMoney());
					}
				}else{
					// state 2. when the database has the item that reconciliation file contains.
					boolean compared=comparePayMoney(payMoney, localPayMoneyBean);
					if(compared){
						// state should be corrected
						if(localPayMoneyBean.getAccountType()==UserBankAccountState.CALLBACK.getState()){
							// should add
							localPayMoneyBean.setAccountType(UserBankAccountState.CORRECTED.getState());
							localPayMoneyBean.setBalanceMoney(payMoney.getMoney());
							logger.info("R+"+payMoney.getMoney()+"|CANCEL2CORRECT|"+localPayMoneyBean.toString());
							paymentService.updatePayMoneyBean(localPayMoneyBean,false);
						}
						// updat state
						if(localPayMoneyBean.getAccountType()!=UserBankAccountState.CORRECTED.getState()){
							localPayMoneyBean.setAccountType(UserBankAccountState.CORRECTED.getState());
							localPayMoneyBean.setBalanceMoney(0.0);
							paymentService.updatePayMoneyBean(localPayMoneyBean);
						}
						
						logger.info("=|CORRECTED|"+localPayMoneyBean.toString());
						
						// for statics only.
						if(accountBankBean!=null){
							accountBankBean.setCorrectNum(accountBankBean.getCorrectNum()+1);
							accountBankBean.setCorrectMoney(accountBankBean.getCorrectMoney()+payMoney.getMoney());
						}
						
					}else{
						if(localPayMoneyBean.getAccountType()!=UserBankAccountState.WRONG.getState()){
							localPayMoneyBean.setAccountType(UserBankAccountState.WRONG.getState());
							localPayMoneyBean.setBalanceMoney(0.0);
							paymentService.updatePayMoneyBean(localPayMoneyBean);
						}
						logger.info("R0|"+payMoney.toString()+"|WRONG|"+localPayMoneyBean.toString());
						
						
						// for statics only.
						if(accountBankBean!=null){
							accountBankBean.setErrorNum(accountBankBean.getErrorNum()+1);
							accountBankBean.setErrorMoney(accountBankBean.getErrorMoney()+payMoney.getMoney());
						}
						
					}
					
					
				}
			}
			
			
			// special deal with the more state.
			// get the
			// other state must be unbalance or more
			String unbalaceState=String.valueOf(UserBankAccountState.UNBALANCE.getState());
			for(String bankcode:mapOfAccountBankBean.keySet()){
				List<PayMoneyBean> localPayMoneyBeans=paymentService.getPayMoneyList(bankdate, bankcode, unbalaceState);
				
				for(PayMoneyBean payMoney:localPayMoneyBeans){
					payMoney.setMoney(Double.valueOf(decimalFormat.format(payMoney.getMoney())));
					payMoney.setAccountType(UserBankAccountState.MORE.getState());
					payMoney.setBalanceMoney(-payMoney.getMoney());
					paymentService.updatePayMoneyBean(payMoney,true);
					logger.info("R-"+payMoney.getMoney()+"<|MORE|"+payMoney.toString());
				}
			}
			
			String moreState=String.valueOf(UserBankAccountState.MORE.getState());
			for(String bankcode:mapOfAccountBankBean.keySet()){
				List<PayMoneyBean> localPayMoneyBeans=paymentService.getPayMoneyList(bankdate, bankcode, moreState);
				AccountBankBean accountBankBean=mapOfAccountBankBean.get(bankcode);
				for(PayMoneyBean payMoney:localPayMoneyBeans){
					payMoney.setMoney(Double.valueOf(decimalFormat.format(payMoney.getMoney())));
				if(accountBankBean!=null){
					accountBankBean.setOverNum(accountBankBean.getOverNum()+1);
					//(accountBankBean.getErrorNum()+1);
					accountBankBean.setOverMoney(accountBankBean.getOverMoney()+payMoney.getMoney());
					//(accountBankBean.getErrorMoney()+payMoney.getMoney());
				}
				}
			}
			
			
			for(String bankcode:mapOfAccountBankBean.keySet()){
				AccountBankBean accountBankBean=mapOfAccountBankBean.get(bankcode);
				if(accountBankBean!=null){
					paymentService.deleteAccountBankBean(bankdate, bankcode);
					accountBankBean.setTotalMoney(accountBankBean.getCorrectMoney()+accountBankBean.getLackMoney());
					logger.info(accountBankBean.toString());
					paymentService.insertAccountBankBean(accountBankBean);
				}
			}
			
			
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ProtocolConvertorException e) {
			e.printStackTrace();
		}
		
	}
	

	public boolean t250011(T250011 t) throws ProtocolStorageException {
		boolean ret = false;
		State.putState(State.ERROR);
		// this is client .here to handle the response data.
		if ("0000".equals(t.response.getCode())) {
			// checking the size of the file
			String fileName = t.response.getReason();
			String fileSize = t.response.getCheckFileSize();
			if (fileName != null && localDir != null && fileSize != null) {
				String path = String.format("%s" + File.separator + "%s",
						localDir, fileName);
				// check the file size
				
				File f = new File(path);
				logger.info("----Reconciliation-->:filename=" +
						"" +path+
						",size=" +f.length()+
						",bankdate=" +t.request.getAccountTime()+
						"]");
				if(f.length()==Integer.parseInt(fileSize))
					reconciliation(fileName,t.request.getAccountTime());
			} else {
				throw new ProtocolStorageException("2511", "one fileName="
						+ isNull(fileName) + ",fileSize:" + isNull(fileSize)
						+ ",localDir:" + isNull(localDir));
			}
			State.putState(State.FINISHED);
		}else{
			throw new ProtocolStorageException("2511",t.response.getReason() );
		}

		return ret;
	}
	
	
	public PayMoneyBean wrapperT250011Entry(T250011Entry entry){
		PayMoneyBean payMoneyBean=new PayMoneyBean();
		payMoneyBean.setAccountTime(entry.request.getAccountDatetime());
		String amt = entry.request.getAmount();
		if(amt!=null&&!amt.trim().equals("")){
			payMoneyBean.setMoney((getNumber(entry.request.getAmount()))*0.01);
		}else{
			payMoneyBean.setMoney(0.00);
		}
		payMoneyBean.setBankCode(entry.request.getBankCode());
		payMoneyBean.setSeriesNo(entry.request.getGlobalSerial());
		payMoneyBean.setUserId(entry.request.getUserId());
		payMoneyBean.setUserName(entry.request.getUserName());
		
//		entry.request.account;
//		entry.request.chargeType;
//		entry.request.serialNumber;
//		entry.request.tradeDateTime
		
		//payMoneyBean.setAccountType(UserBankAccountState.BALANCED.ordinal());
		return payMoneyBean;
	}
	
	private boolean compareString(String o,String d){
		return isNull(o).equalsIgnoreCase(isNull(d));
	}
	
	
	
	private long getNumber(String amt){
		Pattern pattern=Pattern .compile("[^0-9]");
		if(amt!=null&&!amt.trim().equals("")){
			Matcher matcher=pattern.matcher(amt);
			amt = matcher.replaceAll("").trim();
			if(amt!=null&&!amt.equals("")){
				return Long.valueOf(decimalFormat.format(Long.valueOf(amt)));
			}
		}
		return 0;
	}
	

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.palmcommerce.funds.connection.mina.protocol.AbstractClientSideProcessor
	 * #handle(com.palmcommerce.funds.protocol.impl.IProtocol)
	 */
	@Override
	public void handle(IProtocol protocol) throws ProtocolProcessException {
		try {
			Header header = protocol.getHeader();

			// header.getHeader().transcode;
			switch (header.transcode) {

			case 250001: {
				T250001 p = (T250001) protocol;

				t250001(p);

			}

				break;

			case 250002: {
				T250002 p = (T250002) protocol;

				t250002(p);

			}
				// paymentService.p240001((P240001)header);
				break;

			case 250004: {

				T250004 p = (T250004) protocol;

				t250004(p);

			}
				// paymentService.p240001((P240001)header);
				break;

			case 250005: {

				T250005 p = (T250005) protocol;

				t250005(p);

			}
				// paymentService.p240001((P240001)header);
				break;

			case 250006: {

				T250006 p = (T250006) protocol;

				t250006(p);

			}
				// paymentService.p240001((P240001)header);
				break;

			case 250007: {

				T250007 p = (T250007) protocol;

				t250007(p);

			}
				// paymentService.p240001((P240001)header);
				break;

			case 250008: {

				T250008 p = (T250008) protocol;

				t250008(p);

			}
				// paymentService.p240001((P240001)header);
				break;

			case 250009: {

				T250009 p = (T250009) protocol;

				t250009(p);

			}
				// paymentService.p240001((P240001)header);
				break;

			case 250010: {

				T250010 p = (T250010) protocol;

				t250010(p);

			}
				// paymentService.p240001((P240001)header);
				break;

			case 250011: {

				T250011 p = (T250011) protocol;

				t250011(p);

			}
				// paymentService.p240001((P240001)header);
				break;

			default:
				break;
			}
		} catch (ProtocolStorageException e) {
			e.printStackTrace();
			throw new ProtocolProcessException(e.state, e.reason);
		}

	}
	

	
	/**
	 * Define Enum for Schedule
	 * State{UNDOING(-1),CHECKING,REQUESTING,RECONCING,FINISHED}
	 */
	
	
	private static final int REQUESTING=3;
	private static final int ERROR=6;
	
	private enum State{
		
		UNDOING(1,"UNDOING"),
		CHECKING(2,"CHECKING"),
		REQUESTING(3,"REQUESTING"),
		RECONCING(4,"RECONCING"),
		FINISHED(5,"FINISHED"),
		ERROR(6,"ERROR");
		
		private final int value;
		private final String name;
		
		/**
		 * Construction
		 * @param value
		 * @param name
		 */
		private State(int value,String name){
			this.value = value;
			this.name = name;
		}
		
		public String toString(){
			return "[state="+value
					+ ",name="+name
					+ "]";
		}
		
		public String getName(){
			return this.name;
		}
		
		
		public static void clearState(){
			try{
				File f = getLock();
				if(f.exists()){
					f.delete();
				}
			}catch(Exception e){}
			
		}
		/**
		 * Record Schedule Lock
		 */
		private static final String CRON_TAB = "funds.trade.cron";
		private static File getLock() throws IOException{
			String file = OldTradeClientProtocolHandler.class.getResource("/").getFile();
			File f=new File(file+File.separator+CRON_TAB+File.separator+DateConvertor.getBankTime()+".lock");
			if(!f.exists()){
				f.createNewFile();
				FileUtils.writeStringToFile(f, String.valueOf(State.UNDOING.value));
			}
			return f;
		}
		
		/**
		 * Save Current State
		 * @param state
		 */
		public static void putState(State state){
			try{
				File f=getLock();
				FileUtils.writeStringToFile(f, String.valueOf(state.value));
			}catch(Exception e){}
		}
		
		/**
		 * Get Current State
		 * @return
		 */
		public static State getState(){
			try{
				File f = getLock();
				String content=FileUtils.readFileToString(f);
				if(content!=null && !content.trim().equals("")){
					int value = Integer.valueOf(content);
					for(State state : State.values()){
						if(state.value==value){
							return state;
						}
					}
				}
				return State.UNDOING;
			}catch(Exception e){
				return State.UNDOING;
			}
			
		}
		
	}
	

	public static void main(String args[]){
		OldTradeClientProtocolHandler older=new OldTradeClientProtocolHandler();
		T250011Entry entry=new T250011Entry("240003");
		entry.request=new T250011Entry.Request();
		entry.request.amount="80019";
		older.wrapperT250011Entry(entry);
	}
	

}
