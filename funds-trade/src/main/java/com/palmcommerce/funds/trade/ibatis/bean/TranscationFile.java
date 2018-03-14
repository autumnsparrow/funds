package com.palmcommerce.funds.trade.ibatis.bean;
	
public class TranscationFile implements java.io.Serializable {

	// Fields

	private Integer id;  
	private Integer amount;//金额
	private String bankAccount;  // 账户
	private String bankDatetime;//	账务时间 
	private String fromcode;  //发送方代码
	private Integer paymentserial;  // 银行流水号
	private Integer tocode;  //接收方代码	
	private String tradedatatetime;  //时间
	private String tradesystemserial;  //交易平台流水号
	private String transactiontype;   //
	private String userId;  //用户id
	private String userName;  //用户名
	private String version;

	/** default constructor */
	public TranscationFile() {
	}

	public TranscationFile(Integer id, Integer amount, String bankAccount,
			String bankDatetime, String fromcode, Integer paymentserial,
			Integer tocode, String tradedatatetime, String tradesystemserial,
			String transactiontype, String userId, String userName,
			String version) {
		super();
		this.id = id;
		this.amount = amount;
		this.bankAccount = bankAccount;
		this.bankDatetime = bankDatetime;
		this.fromcode = fromcode;
		this.paymentserial = paymentserial;
		this.tocode = tocode;
		this.tradedatatetime = tradedatatetime;
		this.tradesystemserial = tradesystemserial;
		this.transactiontype = transactiontype;
		this.userId = userId;
		this.userName = userName;
		this.version = version;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public String getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}

	public String getBankDatetime() {
		return bankDatetime;
	}

	public void setBankDatetime(String bankDatetime) {
		this.bankDatetime = bankDatetime;
	}

	public String getFromcode() {
		return fromcode;
	}

	public void setFromcode(String fromcode) {
		this.fromcode = fromcode;
	}

	public Integer getPaymentserial() {
		return paymentserial;
	}

	public void setPaymentserial(Integer paymentserial) {
		this.paymentserial = paymentserial;
	}

	public Integer getTocode() {
		return tocode;
	}

	public void setTocode(Integer tocode) {
		this.tocode = tocode;
	}

	public String getTradedatatetime() {
		return tradedatatetime;
	}

	public void setTradedatatetime(String tradedatatetime) {
		this.tradedatatetime = tradedatatetime;
	}

	public String getTradesystemserial() {
		return tradesystemserial;
	}

	public void setTradesystemserial(String tradesystemserial) {
		this.tradesystemserial = tradesystemserial;
	}

	public String getTransactiontype() {
		return transactiontype;
	}

	public void setTransactiontype(String transactiontype) {
		this.transactiontype = transactiontype;
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

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}
}