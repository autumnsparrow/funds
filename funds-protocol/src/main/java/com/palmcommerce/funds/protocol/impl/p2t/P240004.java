package com.palmcommerce.funds.protocol.impl.p2t;

import com.palmcommerce.funds.protocol.ProtocolClassMetaType;
import com.palmcommerce.funds.protocol.ProtocolDriverManager;
import com.palmcommerce.funds.protocol.ProtocolElementMetaType;
import com.palmcommerce.funds.protocol.ProtocolElementMetaType.ProtocolElementType;

import com.palmcommerce.funds.protocol.impl.BasicProtocol;
import com.palmcommerce.funds.protocol.impl.StateMessage;


/**
 * 6.1.5  冲正交易
 * 6.1.5.1 业务描述：
当用户缴费过程中，银行端出现异常情况时，需要给福彩中心发起相应的冲正请求。
 * @author lottery
 *
 */
@ProtocolClassMetaType
public class P240004 extends BasicProtocol {
	
	
	
	public P240004(String transcode, String from, String to) {
		super(transcode, from, to);
		// TODO Auto-generated constructor stub
	}





	public Request request;
	public Response response;
	
	/**
银行流水号	字符	最长20位	Y	用于银行标识每一笔请求交易（必须唯一）
缴款银行流水号	字符	最长20位	Y	冲正这笔缴款的流水号
用户编号	字符	最少8位，最长15位	Y	用户id
用户姓名	字符	最长10位	Y	用户的姓名
冲正金额	字符	最长20位	Y	单位为分
交易时间	字符	时间格式为：YYYY-MM-DD HH:MM:SS	Y	格式为：YYYY-MM-DD HH:MM:SS
账务日期	字符	时间格式为：YYYYMMDD	Y	格式为：YYYYMMDD
	 * @author lottery
	 *
	 */
	public static class Request{
		@ProtocolElementMetaType(sequenceId=0,elementType=ProtocolElementType.STRING,maxLength=20)
		String serialNumber;
		@ProtocolElementMetaType(sequenceId=1,elementType=ProtocolElementType.STRING,maxLength=20)
		String paymentSerialNumber;
		@ProtocolElementMetaType(sequenceId=2,elementType=ProtocolElementType.STRING,maxLength=15,minLenght=8)
		String userId;
		@ProtocolElementMetaType(sequenceId=3,elementType=ProtocolElementType.STRING,maxLength=10)
		String userName;
		@ProtocolElementMetaType(sequenceId=4,elementType=ProtocolElementType.STRING,maxLength=20)
		String reverseAmount;
		@ProtocolElementMetaType(sequenceId=5,elementType=ProtocolElementType.DATETIME)
		String transactionTime;
		@ProtocolElementMetaType(sequenceId=6,elementType=ProtocolElementType.DATETIME,dateTimeFormat="yyyyMMdd")
		String accountTime;
		public String getSerialNumber() {
			return serialNumber;
		}
		public void setSerialNumber(String serialNumber) {
			this.serialNumber = serialNumber;
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
		public String getPaymentSerialNumber() {
			return paymentSerialNumber;
		}
		public void setPaymentSerialNumber(String paymentSerialNumber) {
			this.paymentSerialNumber = paymentSerialNumber;
		}
		public String getReverseAmount() {
			return reverseAmount;
		}
		public void setReverseAmount(String reverseAmount) {
			this.reverseAmount = reverseAmount;
		}
		public String getTransactionTime() {
			return transactionTime;
		}
		public void setTransactionTime(String transactionTime) {
			this.transactionTime = transactionTime;
		}
		public String getAccountTime() {
			return accountTime;
		}
		public void setAccountTime(String accountTime) {
			this.accountTime = accountTime;
		}
		@Override
		public String toString() {
			return "Request [serialNumber=" + serialNumber
					+ ", paymentSerialNumber=" + paymentSerialNumber
					+ ", userId=" + userId + ", userName=" + userName
					+ ", reverseAmount=" + reverseAmount + ", transactionTime="
					+ transactionTime + ", accountTime=" + accountTime
					+ ", toString()=" + super.toString() + "]";
		}
		
		
	}
	/**
状态码	字符	4位	Y	0000 交易成功，见附录B
状态码描述	字符	最长30位	Y	对状态码的文字说明信息
银行流水号	字符	最长20位	Y	资金归集管理平台处理后原样返回
冲正银行流水号	字符	最长20位	Y	对应缴款的银行流水号
	 * @author lottery
	 *
	 */
	public static class Response extends StateMessage{
		@ProtocolElementMetaType(sequenceId=2,elementType=ProtocolElementType.STRING,maxLength=20)
		String serialNumber;
		@ProtocolElementMetaType(sequenceId=3,elementType=ProtocolElementType.STRING,maxLength=20)
		String severseSerialNumber;
		public String getSerialNumber() {
			return serialNumber;
		}
		public void setSerialNumber(String serialNumber) {
			this.serialNumber = serialNumber;
		}
		public String getSeverseSerialNumber() {
			return severseSerialNumber;
		}
		public void setSeverseSerialNumber(String severseSerialNumber) {
			this.severseSerialNumber = severseSerialNumber;
		}
		@Override
		public String toString() {
			return "Response [serialNumber=" + serialNumber
					+ ", severseSerialNumber=" + severseSerialNumber
					+ ", toString()=" + super.toString() + "]";
		}
		
		
	}
	
	public P240004() {
		
	}

	public P240004(String transcode, String from, String to, String packet,
			boolean isRequest) {
		super(transcode, from, to, packet, isRequest);
	}



	public P240004(String transcode, String from, String to, String request,
			String response) {
		super(transcode, from, to, request, response);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "P240004 [request=" + request + ", response=" + response + "]";
	}
	
	

	@Override
	public Class<?> getRequestClazz() {
		// TODO Auto-generated method stub
		return Request.class;
	}





	@Override
	public Class<?> getResponseClazz() {
		// TODO Auto-generated method stub
		return Response.class;
	}





	@Override
	public void setRequestObject(Object obj) {
		// TODO Auto-generated method stub
		this.request=(Request) obj;
		
	}





	@Override
	public void setResponseObject(Object obj) {
		// TODO Auto-generated method stub
		this.response=(Response)obj;
	}





	@Override
	public Object getRequestObject() {
		// TODO Auto-generated method stub
		return this.request;
	}





	@Override
	public Object getResponseObject() {
		// TODO Auto-generated method stub
		return this.response;
	}

	

}
