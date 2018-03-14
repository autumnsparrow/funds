package com.palmcommerce.funds.trade.ibatis.bean;

import java.util.Arrays;
import java.util.Date;
import java.util.List;


/**
 * UserInfo entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class UserInfo extends Response implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1523067936910582718L;

	// Fields
	
	private String transcode="240701";

	private String userId;
	private String partnerId;
	private String realName;
	private String idNo;
	private Date birthday;
	private String sex;
	private String email;
	private String phoneNo;
	private Long status;
	private Date creatTime;
	private String passwd;
	private String loginName;
	private Integer type;
	private java.util.Date  createTime;
	
	
	public java.util.Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(java.util.Date createTime) {
		this.createTime = createTime;
	}

	private String certify;
	
	private double accountMoney;

	private String bankCode;
	
	private String seriesNo;
	
	private String bankAccount;
	
	
	private Integer idType;
	
	
	// Constructors

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getIdType() {
		return idType;
	}

	public void setIdType(Integer idType) {
		this.idType = idType;
	}

	public String getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}

	

	

	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	public String getSeriesNo() {
		return seriesNo;
	}

	public void setSeriesNo(String seriesNo) {
		this.seriesNo = seriesNo;
	}

	/** default constructor */
	public UserInfo() {
		this.transcode="240701";
		
	}
	
	@Override
	public List<String> fieldNames() {
		// TODO Auto-generated method stub
		List<String> fieldNames = super.fieldNames();
		fieldNames.addAll(Arrays.asList("transcode", "userId","realName","seriesNo","partnerId","type","certify","accountMoney"));
		return fieldNames;
	}

	/** minimal constructor */
	public UserInfo(String userId, String realName, String idNo, Date birthday, String sex, String email,String phoneNo,
			Long status, Date creatTime) {
		this.userId = userId;
		this.realName = realName;
		this.idNo = idNo;
		this.birthday = birthday;
		this.sex = sex;
		this.email = email;
		this.phoneNo = phoneNo;
		this.status = status;
		this.creatTime = creatTime;
	}

	/** full constructor */
	public UserInfo(String userId, String partnerId, String realName,
			String idNo, Date birthday, String sex, String email, String phoneNo, Long status, Date creatTime,
			String passwd, String loginName) {
		this.userId = userId;
		this.partnerId = partnerId;
		this.realName = realName;
		this.idNo = idNo;
		this.birthday = birthday;
		this.sex = sex;
		this.email = email;
		this.phoneNo = phoneNo;
		this.status = status;
		this.creatTime = creatTime;
		this.passwd = passwd;
		this.loginName = loginName;
	}

	
	
	// Property accessors

	public String getTranscode() {
		return transcode;
	}

	public void setTranscode(String transcode) {
		this.transcode = transcode;
	}

	

	public String getCertify() {
		return certify;
	}

	public void setCertify(String certify) {
		this.certify = certify;
	}

	public double getAccountMoney() {
		return accountMoney;
	}

	public void setAccountMoney(double accountMoney) {
		this.accountMoney = accountMoney;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPartnerId() {
		return this.partnerId;
	}

	public void setPartnerId(String partnerId) {
		this.partnerId = partnerId;
	}

	public String getRealName() {
		return this.realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getIdNo() {
		return this.idNo;
	}

	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}

	public String getPhoneNo() {
		return this.phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public Long getStatus() {
		return this.status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

	public Date getCreatTime() {
		return this.creatTime;
	}

	public void setCreatTime(Date creatTime) {
		this.creatTime = creatTime;
	}

	public String getPasswd() {
		return this.passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public String getLoginName() {
		return this.loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
}