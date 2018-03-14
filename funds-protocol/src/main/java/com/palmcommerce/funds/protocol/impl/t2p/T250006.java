package com.palmcommerce.funds.protocol.impl.t2p;

import java.util.List;

import com.palmcommerce.funds.protocol.ProtocolClassMetaType;
import com.palmcommerce.funds.protocol.ProtocolDriverManager;
import com.palmcommerce.funds.protocol.ProtocolElementMetaType;
import com.palmcommerce.funds.protocol.ProtocolElementMetaType.ProtocolElementType;
import com.palmcommerce.funds.protocol.covertor.exception.ProtocolConvertorException;
import com.palmcommerce.funds.protocol.impl.BasicProtocol;
import com.palmcommerce.funds.protocol.impl.StateMessage;


/**
 * 6.2.6  缴费订单查询交易
 * 6.2.6.1 业务描述：
查询实时缴费交易订单数据。
 * @author lottery
 *
 */
@ProtocolClassMetaType
public class T250006 extends BasicProtocol {
	

	
	
	public T250006(String transcode, String from, String to) {
		super(transcode, from, to);
		// TODO Auto-generated constructor stub
	}



	public Request request;
	public Response response;
	
	
	/**
	 * 中心流水号	字符	最长20位	Y	用于中心标识每一笔请求交易（必须唯一）
用户编号	字符	最少8位，最长15位	Y	用户ID，该值对每个用户是唯一的
用户姓名	字符	最长10位	Y	用户的姓名
交易时间	字符	时间格式为：YYYY-MM-DD HH:MM:SS	Y	格式为：YYYY-MM-DD HH:MM:SS
账务日期	字符	格式为：YYYYMMDD	N	格式为：YYYYMMDD

	 * @author lottery
	 *
	 */
	public static class Request{
		@ProtocolElementMetaType(sequenceId=0,elementType=ProtocolElementType.STRING,maxLength=20)
		String centerNumber;
		@ProtocolElementMetaType(sequenceId=1,elementType=ProtocolElementType.STRING,maxLength=15,minLenght=8)
		String userId;
		@ProtocolElementMetaType(sequenceId=2,elementType=ProtocolElementType.STRING,maxLength=10)
		String userName;
		@ProtocolElementMetaType(sequenceId=3,elementType=ProtocolElementType.DATETIME)
		String transactionTime;
		@ProtocolElementMetaType(sequenceId=4,elementType=ProtocolElementType.DATETIME,dateTimeFormat="yyyyMMdd")
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
			return "Request [centerNumber=" + centerNumber + ", userId="
					+ userId + ", userName=" + userName + ", transactionTime="
					+ transactionTime + ", accountTime=" + accountTime
					+ ", toString()=" + super.toString() + "]";
		}
		
		
	}
	
	/**
	 * 状态码	字符	4位	Y	0000 交易成功，见附录B
状态码描述	字符	最多30位	Y	对状态码的文字说明信息
中心流水号	字符	最长20位	Y	银行处理后原样返回
银行流水号	字符	最长20位	Y	银行流水号标识每一笔请求交易
用户编号	字符	最少8位，最长15位	Y	用户ID，该值对每个用户是唯一的
用户姓名	字符	最长10位	Y	用户的姓名
缴费类型	字符	2位	Y	银行定义 例如01柜台缴费 02终端缴费  03网银缴费
缴费金额	字符	最长20位	Y	单位为分

	 * @author lottery
	 *
	 */
	public static class Response extends StateMessage{
		@ProtocolElementMetaType(sequenceId=2,elementType=ProtocolElementType.STRING,maxLength=20)
		String centerNumber;
		@ProtocolElementMetaType(sequenceId=3,elementType=ProtocolElementType.STRING,maxLength=20,required=false)
		String serialNumber;
		@ProtocolElementMetaType(sequenceId=4,elementType=ProtocolElementType.STRING,maxLength=15,minLenght=8)
		String userId;
		@ProtocolElementMetaType(sequenceId=5,elementType=ProtocolElementType.STRING,maxLength=10)
		String userName;
		@ProtocolElementMetaType(sequenceId=6,elementType=ProtocolElementType.STRING,maxLength=2,minLenght=2)
		String paymentType; 
		@ProtocolElementMetaType(sequenceId=7,elementType=ProtocolElementType.STRING,maxLength=20)
		String paymentAmount;
		public String getCenterNumber() {
			return centerNumber;
		}
		public void setCenterNumber(String centerNumber) {
			this.centerNumber = centerNumber;
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
		public String getPaymentType() {
			return paymentType;
		}
		public void setPaymentType(String paymentType) {
			this.paymentType = paymentType;
		}
		public String getPaymentAmount() {
			return paymentAmount;
		}
		public void setPaymentAmount(String paymentAmount) {
			this.paymentAmount = paymentAmount;
		}
		@Override
		public String toString() {
			return "Response [centerNumber=" + centerNumber + ", serialNumber="
					+ serialNumber + ", userId=" + userId + ", userName="
					+ userName + ", paymentType=" + paymentType
					+ ", paymentAmount=" + paymentAmount + ", toString()="
					+ super.toString() + "]";
		}
		
	}

	public T250006() {
	}

	public T250006(String transcode, String from, String to, String packet,
			boolean isRequest) {
		super(transcode, from, to, packet, isRequest);
	}



	public T250006(String transcode, String from, String to, String request,
			String response) {
		super(transcode, from, to, request, response);
		// TODO Auto-generated constructor stub
	}

	

	@Override
	public String toString() {
		return "T250006 [request=" + request + ", response="  + "]";
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
