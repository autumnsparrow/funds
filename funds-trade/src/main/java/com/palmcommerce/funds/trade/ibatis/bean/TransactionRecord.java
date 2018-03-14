package com.palmcommerce.funds.trade.ibatis.bean;

import java.io.Serializable;
import java.util.Date;
/**
 * 用户交易记录所需
 * 
 * **/
public class TransactionRecord implements Serializable {
	 private static final long serialVersionUID = -8665005144187172294L;
	 private long id;
	 private String tradeSerial;  //中心id
	 private String globalSerial;;  //银行id
	 
	 private String userName;//用户姓名
	 private String bankAccount;//银行卡号
	 
	 private String userId; //用户id
	 private int chargeType; //缴费类型/充值方式
	 private long amount; //金额
	 private long tradeSystemDeposit; //虚拟账户余额
	 private long useTradeSystemDeposit; //可用虚拟账户余额
	 private Date tradeDateTime;   //交易时间

	 private String accountTime;//账务日期
	 private String transactionState;  //状态

	 private String cancelPaymentSerial; //冲正流水号/订单号
	 private String paymentSerial;//银行流水号
	 private String tradeSystemSerial;//中心流水号
	 private int poundage;//手续费
	 private String transcode;//交易类型
	 private String chargeTypestr;//缴费类型/充值方式中文
	 private int successNum;//成功总条数
	 private int loseNum;//失败总条数
	 private Long successMoney;//成功总资金
	 private Long loseMoney;//失败总资金
	 
	 //页面统计需要的时间格式
	 private String showTime;
	//充值累计
	private long rechargeAllup;
	//提现累计
	private long drawAllup;
	//冲正累计
	private long chongzhengAllup;
	//选择状态
	private String radioValue;
	//id
	private String bankid;
	private String terraceid;
	private int state;
	
	public String getShowTime() {
		return showTime;
	}
	public void setShowTime(String showTime) {
		this.showTime = showTime;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public TransactionRecord(){
		
	}
	public TransactionRecord(String tradeSerial,String transactionState){
		this.setTradeSerial(tradeSerial);
		this.setTransactionState(transactionState);
	}
	
	public TransactionRecord(String tradeSerial,int state){
		this.setTradeSerial(tradeSerial);
		this.setState(state);
	}
	
	//返回平台对账文件所需
	private Date terracetime;
	private String terracefilename;
	private String filesuccess;
	
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getAccountTime() {
		return accountTime;
	}
	public void setAccountTime(String accountTime) {
		this.accountTime = accountTime;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getBankAccount() {
		return bankAccount;
	}
	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}
	public String getTradeSystemSerial() {
		return tradeSystemSerial;
	}
	public void setTradeSystemSerial(String tradeSystemSerial) {
		this.tradeSystemSerial = tradeSystemSerial;
	}
	public Date getTerracetime() {
		return terracetime;
	}
	public void setTerracetime(Date terracetime) {
		this.terracetime = terracetime;
	}
	public String getTerracefilename() {
		return terracefilename;
	}
	public void setTerracefilename(String terracefilename) {
		this.terracefilename = terracefilename;
	}
	public String getFilesuccess() {
		return filesuccess;
	}
	public void setFilesuccess(String filesuccess) {
		this.filesuccess = filesuccess;
	}
	public String getBankid() {
		return bankid;
	}
	public void setBankid(String bankid) {
		this.bankid = bankid;
	}
	public String getTerraceid() {
		return terraceid;
	}
	public void setTerraceid(String terraceid) {
		this.terraceid = terraceid;
	}
	public String getRadioValue() {
		return radioValue;
	}
	public void setRadioValue(String radioValue) {
		this.radioValue = radioValue;
	}
	public String getTranscode() {
		return transcode;
	}
	public void setTranscode(String transcode) {
		this.transcode = transcode;
	}
	public long getChongzhengAllup() {
		return chongzhengAllup;
	}
	public void setChongzhengAllup(long chongzhengAllup) {
		this.chongzhengAllup = chongzhengAllup;
	}
	public String getPaymentSerial() {
		return paymentSerial;
	}
	public void setPaymentSerial(String paymentSerial) {
		this.paymentSerial = paymentSerial;
	}

	public long getRechargeAllup() {
		return rechargeAllup;
	}
	public void setRechargeAllup(long rechargeAllup) {
		this.rechargeAllup = rechargeAllup;
	}
	public long getDrawAllup() {
		return drawAllup;
	}
	public void setDrawAllup(long drawAllup) {
		this.drawAllup = drawAllup;
	}
	public long getUseTradeSystemDeposit() {
		return useTradeSystemDeposit;
	}
	public void setUseTradeSystemDeposit(long useTradeSystemDeposit) {
		this.useTradeSystemDeposit = useTradeSystemDeposit;
	}
	public int getSuccessNum() {
		return successNum;
	}
	public void setSuccessNum(int successNum) {
		this.successNum = successNum;
	}
	public int getLoseNum() {
		return loseNum;
	}
	public void setLoseNum(int loseNum) {
		this.loseNum = loseNum;
	}
	public Long getSuccessMoney() {
		return successMoney;
	}
	public void setSuccessMoney(Long successMoney) {
		this.successMoney = successMoney;
	}
	public Long getLoseMoney() {
		return loseMoney;
	}
	public void setLoseMoney(Long loseMoney) {
		this.loseMoney = loseMoney;
	}
	public String getChargeTypestr() {
		return chargeTypestr;
	}
	public void setChargeTypestr(String chargeTypestr) {
		this.chargeTypestr = chargeTypestr;
	}
	public int getPoundage() {
		return poundage;
	}
	public void setPoundage(int poundage) {
		this.poundage = poundage;
	}
	public String getTradeSerial() {
		return tradeSerial;
	}
	public void setTradeSerial(String tradeSerial) {
		this.tradeSerial = tradeSerial;
	}
	public String getGlobalSerial() {
		return globalSerial;
	}
	public void setGlobalSerial(String globalSerial) {
		this.globalSerial = globalSerial;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public int getChargeType() {
		return chargeType;
	}
	public void setChargeType(int chargeType) {
		this.chargeType = chargeType;
	}
	public long getAmount() {
		return amount;
	}
	public void setAmount(long amount) {
		this.amount = amount;
	}
	public long getTradeSystemDeposit() {
		return tradeSystemDeposit;
	}
	public void setTradeSystemDeposit(long tradeSystemDeposit) {
		this.tradeSystemDeposit = tradeSystemDeposit;
	}
	public Date getTradeDateTime() {
		return tradeDateTime;
	}
	public void setTradeDateTime(Date tradeDateTime) {
		this.tradeDateTime = tradeDateTime;
	}
	public String getTransactionState() {
		return transactionState;
	}
	public void setTransactionState(String transactionState) {
		this.transactionState = transactionState;
	}
	public String getCancelPaymentSerial() {
		return cancelPaymentSerial;
	}
	public void setCancelPaymentSerial(String cancelPaymentSerial) {
		this.cancelPaymentSerial = cancelPaymentSerial;
	}
	
//	@Override
//	public boolean equals(Object obj){
//		TransactionRecord tr=(TransactionRecord)obj;
//		return compareString(tr.tradeSerial,tradeSerial) && tr.transactionState.equals(transactionState);
//	}
//	private boolean compareString(String src,String dest){
//		if (null!=src && null!=dest) {
//			return src.equals(dest);
//		} else {
//			return false;
//		}
//	}
//	
	@Override
	public boolean equals(Object obj) {
		TransactionRecord tr=(TransactionRecord) obj;
		if (!tr.getPaymentSerial().equals(this.getPaymentSerial())) {
			return false;
		}
		if(!tr.getTradeSerial().equals(this.getTradeSerial())){
			return false;
		}
		if(!tr.getUserId().equals(this.getUserId())){
			return false;
		}
		if(!tr.getUserName().equals(this.getUserName())){
			return false;
		}
		if(!tr.getBankAccount().equals(this.getBankAccount())){
			return false;
		}
		if(tr.getAmount()!=(this.getAmount())){
			return false;
		}
		if(!tr.getTradeDateTime().equals(this.getTradeDateTime())){
			return false;
		}
		if(!tr.getBankid().equals(this.getBankid())){
			return false;
		}
		if(!tr.getAccountTime().equals(this.getAccountTime())){
			return false;
		}
		if(!tr.getChargeTypestr().equals(this.getChargeTypestr())){
			return false;
		}
		return true;
	}
	@Override
	public String toString() {
		return "TransactionRecord [id=" + id + ", tradeSerial=" + tradeSerial
				+ ", globalSerial=" + globalSerial + ", userName=" + userName
				+ ", bankAccount=" + bankAccount + ", userId=" + userId
				+ ", chargeType=" + chargeType + ", amount=" + amount
				+ ", tradeSystemDeposit=" + tradeSystemDeposit
				+ ", useTradeSystemDeposit=" + useTradeSystemDeposit
				+ ", tradeDateTime=" + tradeDateTime + ", accountTime="
				+ accountTime + ", transactionState=" + transactionState
				+ ", cancelPaymentSerial=" + cancelPaymentSerial
				+ ", paymentSerial=" + paymentSerial + ", tradeSystemSerial="
				+ tradeSystemSerial + ", poundage=" + poundage + ", transcode="
				+ transcode + ", chargeTypestr=" + chargeTypestr
				+ ", successNum=" + successNum + ", loseNum=" + loseNum
				+ ", successMoney=" + successMoney + ", loseMoney=" + loseMoney
				+ ", rechargeAllup=" + rechargeAllup + ", drawAllup="
				+ drawAllup + ", chongzhengAllup=" + chongzhengAllup
				+ ", radioValue=" + radioValue + ", bankid=" + bankid
				+ ", terraceid=" + terraceid + ", terracetime=" + terracetime
				+ ", terracefilename=" + terracefilename + ", filesuccess="
				+ filesuccess + "]";
	}
	
	private int correctSum; //正确笔数
	private int correctAmount;  //正确金额
	private int wrongSum;  //错误笔数
	private int wrongAmount; //错误金额
	private int overSum;  //多帐笔数
	private int overAmount;  //多帐金额
	private int lessSum;  //少帐笔数
	private int lessAmount;  //少帐金额
	private String tradeTime;  //交易日期
	private String financeName;  //金融商名称

	public int getCorrectSum() {
		return correctSum;
	}
	public void setCorrectSum(int correctSum) {
		this.correctSum = correctSum;
	}
	public int getCorrectAmount() {
		return correctAmount;
	}
	public void setCorrectAmount(int correctAmount) {
		this.correctAmount = correctAmount;
	}
	public int getWrongSum() {
		return wrongSum;
	}
	public void setWrongSum(int wrongSum) {
		this.wrongSum = wrongSum;
	}
	public int getWrongAmount() {
		return wrongAmount;
	}
	public void setWrongAmount(int wrongAmount) {
		this.wrongAmount = wrongAmount;
	}
	public int getOverSum() {
		return overSum;
	}
	public void setOverSum(int overSum) {
		this.overSum = overSum;
	}
	public int getOverAmount() {
		return overAmount;
	}
	public void setOverAmount(int overAmount) {
		this.overAmount = overAmount;
	}
	public int getLessSum() {
		return lessSum;
	}
	public void setLessSum(int lessSum) {
		this.lessSum = lessSum;
	}
	public int getLessAmount() {
		return lessAmount;
	}
	public void setLessAmount(int lessAmount) {
		this.lessAmount = lessAmount;
	}
	public String getTradeTime() {
		return tradeTime;
	}
	public void setTradeTime(String tradeTime) {
		this.tradeTime = tradeTime;
	}
	public String getFinanceName() {
		return financeName;
	}
	public void setFinanceName(String financeName) {
		this.financeName = financeName;
	}
	
}
     