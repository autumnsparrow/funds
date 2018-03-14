package com.palmcommerce.funds.protocol.impl.t2p;

import com.palmcommerce.funds.protocol.ProtocolClassMetaType;
import com.palmcommerce.funds.protocol.ProtocolDriverManager;
import com.palmcommerce.funds.protocol.ProtocolElementMetaType;
import com.palmcommerce.funds.protocol.ProtocolClassMetaType.TRANSFER_MODE;
import com.palmcommerce.funds.protocol.ProtocolElementMetaType.ProtocolElementType;
import com.palmcommerce.funds.protocol.covertor.exception.ProtocolConvertorException;
import com.palmcommerce.funds.protocol.impl.BasicProtocol;
import com.palmcommerce.funds.protocol.impl.StateMessage;


/**
 * 6.2.5  实时缴费冲正交易
 * 6.2.5.1 业务描述：
资金归集管理平台对实时缴费失败的订单向银行发起冲正请求，银行需要验证身份和帐户信息
 * @author lottery
 *
 */
@ProtocolClassMetaType(mode=TRANSFER_MODE.DELAY_PROXY)
public class T250005 extends BasicProtocol {
	
	
	

	
	public T250005(String transcode, String from, String to) {
		super(transcode, from, to);
		// TODO Auto-generated constructor stub
	}



	public Request request;
	public Response response;
	
	/**
	 * 中心流水号	字符	最长20位	Y	用于中心标识每一笔请求交易（必须唯一）
实时缴款流水号	字符	最长20位	Y	上一笔未成功缴款交易的流水号
用户编号	字符	最少8位，最长15位	Y	用户ID，该值对每个用户是唯一的
用户姓名	字符	最长10位	Y	用户的姓名
缴费金额	字符	最长20位	Y	单位为分
交易时间	字符	时间格式为：YYYY-MM-DD HH:MM:SS	Y	格式为：YYYY-MM-DD HH:MM:SS
账务日期	字符	格式为：YYYYMMDD	Y	格式为：YYYYMMDD

	 * @author lottery
	 *
	 */
	public static class Request{
		@ProtocolElementMetaType(sequenceId=0,elementType=ProtocolElementType.STRING,maxLength=20)
		String centerNumber;
		@ProtocolElementMetaType(sequenceId=1,elementType=ProtocolElementType.STRING,maxLength=20)
		String realTimePaymentNumber;
		@ProtocolElementMetaType(sequenceId=2,elementType=ProtocolElementType.STRING,maxLength=15,minLenght=8)
		String userId;
		@ProtocolElementMetaType(sequenceId=3,elementType=ProtocolElementType.STRING,maxLength=10)
		String userName;
		@ProtocolElementMetaType(sequenceId=4,elementType=ProtocolElementType.STRING,maxLength=20)
		String paymentAmount;
		@ProtocolElementMetaType(sequenceId=5,elementType=ProtocolElementType.DATETIME)
		String transactionTime;
		@ProtocolElementMetaType(sequenceId=6,elementType=ProtocolElementType.DATETIME,dateTimeFormat="yyyyMMdd")
		String accountTime;
		public String getCenterNumber() {
			return centerNumber;
		}
		public void setCenterNumber(String centerNumber) {
			this.centerNumber = centerNumber;
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
		public String getRealTimePaymentNumber() {
			return realTimePaymentNumber;
		}
		public void setRealTimePaymentNumber(String realTimePaymentNumber) {
			this.realTimePaymentNumber = realTimePaymentNumber;
		}
		public String getPaymentAmount() {
			return paymentAmount;
		}
		public void setPaymentAmount(String paymentAmount) {
			this.paymentAmount = paymentAmount;
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
			return "Request [centerNumber=" + centerNumber
					+ ", realTimePaymentNumber=" + realTimePaymentNumber
					+ ", userId=" + userId + ", userName=" + userName
					+ ", paymentAmount=" + paymentAmount + ", transactionTime="
					+ transactionTime + ", accountTime=" + accountTime
					+ ", toString()=" + super.toString() + "]";
		}
		
		
	}

	/**
	 * 状态码	字符	4位	Y	0000 交易成功，见附录B
状态码描述	字符	最长30位	Y	对状态码的文字说明信息
中心流水号	字符	最长20位	Y	银行处理后原样返回
中心冲正流水号	字符	最长20位	Y	上一笔未成功缴款交易的流水号
银行流水号	字符	最长20位	Y	银行流水号标识每一笔请求交易
用户编号	字符	最少8位，最长15位	Y	用户ID，该值对每个用户是唯一的
用户姓名	字符	最长10位	Y	用户的姓名

	 * @author lottery
	 *
	 */
	public static class Response extends StateMessage{
		@ProtocolElementMetaType(sequenceId=2,elementType=ProtocolElementType.STRING,maxLength=20)
		String centerNumber;
		@ProtocolElementMetaType(sequenceId=3,elementType=ProtocolElementType.STRING,maxLength=20)
		String centerReverseNumber;
		@ProtocolElementMetaType(sequenceId=4,elementType=ProtocolElementType.STRING,maxLength=20)
		String serialNumber;
		@ProtocolElementMetaType(sequenceId=5,elementType=ProtocolElementType.STRING,maxLength=15,minLenght=8)
		String userId;
		@ProtocolElementMetaType(sequenceId=6,elementType=ProtocolElementType.STRING,maxLength=10)
		String userName;
		public String getCenterNumber() {
			return centerNumber;
		}
		public void setCenterNumber(String centerNumber) {
			this.centerNumber = centerNumber;
		}
		public String getCenterReverseNumber() {
			return centerReverseNumber;
		}
		public void setCenterReverseNumber(String centerReverseNumber) {
			this.centerReverseNumber = centerReverseNumber;
		}
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
		@Override
		public String toString() {
			return "Response [centerNumber=" + centerNumber
					+ ", centerReverseNumber=" + centerReverseNumber
					+ ", serialNumber=" + serialNumber + ", userId=" + userId
					+ ", userName=" + userName + ", toString()="
					+ super.toString() + "]";
		}
		
		
	}
	
	public T250005() {
	}

	public T250005(String transcode, String from, String to, String packet,
			boolean isRequest) {
		super(transcode, from, to, packet, isRequest);
	}

	public T250005(String transcode, String from, String to, String request,
			String response) {
		super(transcode, from, to, request, response);
		// TODO Auto-generated constructor stub
	}

	

	@Override
	public String toString() {
		return "T250005 [request=" + request + ", response=" + response + "]";
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


}
