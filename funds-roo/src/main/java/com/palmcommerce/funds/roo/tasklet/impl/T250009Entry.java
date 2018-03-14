/**
 * 
 */
package com.palmcommerce.funds.roo.tasklet.impl;

import com.palmcommerce.funds.protocol.ProtocolDriverManager;
import com.palmcommerce.funds.protocol.ProtocolElementMetaType;
import com.palmcommerce.funds.protocol.ProtocolElementMetaType.ProtocolElementType;
import com.palmcommerce.funds.protocol.impl.BasicProtocol;

/**
 * @author lottery
 *
 */
public class T250009Entry extends BasicProtocol {
	
	static {
		
		ProtocolDriverManager.registger(T250009Entry.class, T250009Entry.Request.class, T250009Entry.Response.class);
	}
	
	public static enum Entry_State{
		BANK,
		PROXY,
		BOTH
	}
	
	public Entry_State state;
	
	/**
	 * 银行流水号1|中心流水号1|用户编号1|用户姓名1|银行卡号|金额1| 交易时间1(YYYY-MM-DD HH:MM:SS) |8位银行代码(00000001)| 账务日期(YYYYMMDD ) |平台代号|类型(汇付)
	 * @author lottery
	 */
	public static class Request{
		@ProtocolElementMetaType(sequenceId=0,elementType=ProtocolElementType.STRING,maxLength=20)
		String serialNumber;
		@ProtocolElementMetaType(sequenceId=1,elementType=ProtocolElementType.STRING,maxLength=20)
		String tradeSerial;
		@ProtocolElementMetaType(sequenceId=2,elementType=ProtocolElementType.STRING,maxLength=20)
		String userId;
		@ProtocolElementMetaType(sequenceId=3,elementType=ProtocolElementType.STRING,maxLength=20)
		String userName;
		@ProtocolElementMetaType(sequenceId=4,elementType=ProtocolElementType.STRING,maxLength=20)
		String account;
		@ProtocolElementMetaType(sequenceId=5,elementType=ProtocolElementType.STRING,maxLength=20)
		String amount;
		@ProtocolElementMetaType(sequenceId=6,elementType=ProtocolElementType.DATETIME)
		String tradeDateTime;
		@ProtocolElementMetaType(sequenceId=7,elementType=ProtocolElementType.STRING,maxLength=10)
		String bankCode;
		@ProtocolElementMetaType(sequenceId=8,elementType=ProtocolElementType.DATETIME,dateTimeFormat="yyyyMMdd")
		String accountDatetime;
		@ProtocolElementMetaType(sequenceId=9,elementType=ProtocolElementType.STRING,maxLength=10)
		String transCode;
		@ProtocolElementMetaType(sequenceId=10,elementType=ProtocolElementType.INTEGER,maxLength=1 ,minLenght=1,enumSet="0|")
		String chargeType;


		public String getSerialNumber() {
			return serialNumber;
		}
		public void setSerialNumber(String serialNumber) {
			this.serialNumber = serialNumber;
		}
		public String getTradeSerial() {
			return tradeSerial;
		}
		public void setTradeSerial(String tradeSerial) {
			this.tradeSerial = tradeSerial;
		}
		public String getUserId() {
			return userId;
		}
		public void setUserId(String userId) {
			this.userId = userId;
		}
		public String getUserName() {
			return userName;
		}
		public void setUserName(String userName) {
			this.userName = userName;
		}
		public String getAccount() {
			return account;
		}
		public void setAccount(String account) {
			this.account = account;
		}
		public String getAmount() {
			return amount;
		}
		public void setAmount(String amount) {
			this.amount = amount;
		}
		public String getTradeDateTime() {
			return tradeDateTime;
		}
		public void setTradeDateTime(String tradeDateTime) {
			this.tradeDateTime = tradeDateTime;
		}
		public String getBankCode() {
			return bankCode;
		}
		public void setBankCode(String bankCode) {
			this.bankCode = bankCode;
		}
		public String getAccountDatetime() {
			return accountDatetime;
		}
		public void setAccountDatetime(String accountDatetime) {
			this.accountDatetime = accountDatetime;
		}
		public String getTransCode() {
			return transCode;
		}
		public void setTransCode(String transCode) {
			this.transCode = transCode;
		}
		public String getChargeType() {
			return chargeType;
		}
		public void setChargeType(String chargeType) {
			this.chargeType = chargeType;
		}
		
		
		public boolean compare(Request r){
			return this.serialNumber.equals(r.serialNumber)&&
					this.tradeSerial.equals(r.tradeSerial)&&
					this.userId.equals(r.userId)&&
					this.userName.equals(r.userName)&&	
					this.account.equals(r.account)&&
					this.amount.equals(r.amount)&&	
					this.tradeDateTime.equals(r.tradeDateTime)&&
					this.bankCode.equals(r.bankCode)&&
					this.accountDatetime.equals(r.accountDatetime)&&
					this.transCode.equals(r.transCode)&&
					this.chargeType.equals(r.chargeType);		
		}
	};
	
	public static class Response{
		
	};

	public Request request;
	public Response response;
	/**
	 * 
	 */
	public T250009Entry(String transcode) {
		// TODO Auto-generated constructor stub
		super(transcode,null,null,null,true);
	}

	/**
	 * @param transcode
	 * @param from
	 * @param to
	 * @param packet
	 * @param isRequest
	 */
	public T250009Entry(String transcode, String from, String to,
			String packet, boolean isRequest) {
		super(transcode, from, to, packet, isRequest);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param transcode
	 * @param from
	 * @param to
	 * @param request
	 * @param response
	 */
	public T250009Entry(String transcode, String from, String to,
			String request, String response) {
		super(transcode, from, to, request, response);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see com.palmcommerce.funds.protocol.impl.BasicProtocol#getRequestClazz()
	 */
	@Override
	public Class<?> getRequestClazz() {
		// TODO Auto-generated method stub
		return Request.class;
	}

	/* (non-Javadoc)
	 * @see com.palmcommerce.funds.protocol.impl.BasicProtocol#getResponseClazz()
	 */
	@Override
	public Class<?> getResponseClazz() {
		// TODO Auto-generated method stub
		return Response.class;
	}

	/* (non-Javadoc)
	 * @see com.palmcommerce.funds.protocol.impl.BasicProtocol#setRequestObject(java.lang.Object)
	 */
	@Override
	public <T> void setRequestObject(T obj) {
		// TODO Auto-generated method stub
		this.request=(Request)obj;
	}

	/* (non-Javadoc)
	 * @see com.palmcommerce.funds.protocol.impl.BasicProtocol#setResponseObject(java.lang.Object)
	 */
	@Override
	public <T> void setResponseObject(T obj) {
		// TODO Auto-generated method stub
		this.response=(Response)obj;
	}

	/* (non-Javadoc)
	 * @see com.palmcommerce.funds.protocol.impl.BasicProtocol#getRequestObject()
	 */
	@Override
	public <T> T getRequestObject() {
		// TODO Auto-generated method stub
		return (T)this.request;
	}

	/* (non-Javadoc)
	 * @see com.palmcommerce.funds.protocol.impl.BasicProtocol#getResponseObject()
	 */
	@Override
	public <T> T getResponseObject() {
		// TODO Auto-generated method stub
		return (T)this.response;
	}
}
