package com.palmcommerce.funds.trade.ibatis.bean;

import java.util.Arrays;
import java.util.List;


public class CommonError extends Response{
	
	public static String ERROR_DATA="1001";
	public static String ERROR_USER_NOEXIST="1002";
	public static String ERROR_NO_TRANSCODE="1003";
	public static String USERNAME_NOT_EXIST="1004";
	public static String EXIST_SERIESNO="1006";
//	public static String ERROR_EXCEPTION="-1";
	public static String ERROR_FORMAT="1005";
	

	private int secretType=0;
	private String errorCode;
	private String frontSeries;
	private String backSeries=String.valueOf(System.currentTimeMillis());
	private String transcode="999999";
	private String tradecode;
	private String message;
	
	@Override
	public List<String> fieldNames() {
		// TODO Auto-generated method stub
		List<String> fieldNames = super.fieldNames();
		fieldNames.addAll(Arrays.asList("transcode", "secretType","errorCode","frontSeries","backSeries","tradecode","message"));
		return fieldNames;
	}
	
	
	
	public String getTradecode() {
		return tradecode;
	}



	public void setTradecode(String tradecode) {
		this.tradecode = tradecode;
	}



	public int getSecretType() {
		return secretType;
	}
	public void setSecretType(int secretType) {
		this.secretType = secretType;
	}
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public String getFrontSeries() {
		return frontSeries;
	}
	public void setFrontSeries(String frontSeries) {
		this.frontSeries = frontSeries;
	}
	public String getBackSeries() {
		return backSeries;
	}
	public void setBackSeries(String backSeries) {
		this.backSeries = backSeries;
	}
	public String getTranscode() {
		return transcode;
	}
	public void setTranscode(String transcode) {
		this.transcode = transcode;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
}
