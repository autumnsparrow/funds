/**
 * 
 */
package com.palmcommerce.funds.protocol.impl.p2t;

import com.palmcommerce.funds.protocol.ProtocolClassMetaType;
import com.palmcommerce.funds.protocol.ProtocolDriverManager;
import com.palmcommerce.funds.protocol.ProtocolElementMetaType;
import com.palmcommerce.funds.protocol.ProtocolElementMetaType.ProtocolElementType;
import com.palmcommerce.funds.protocol.covertor.ProtocolConvertFactory;
import com.palmcommerce.funds.protocol.covertor.exception.ProtocolConvertorException;
import com.palmcommerce.funds.protocol.impl.BasicProtocol;
import com.palmcommerce.funds.protocol.impl.StateMessage;

/**
 * 
 * 240001 fetch the user information.
 * 6.1.2  查询用户信息交易
 * 6.1.2.1 业务描述：
此种情况是用户到柜台办理缴费业务时，
先确认该用户在中心平台是否有账户。
由银行柜台办理的业务，需要银行校验用户姓名、证件类
型和证件号码是否正确。

 * 
 *  
 * @author sparrow
 *
 */
@ProtocolClassMetaType
public class P240001 extends BasicProtocol{

	
	
	public Request request;
	public Response response;
	
	/**
	 * 
银行流水号	字符	最长20位	Y	用于银行标识每一笔请求交易（必须唯一）
用户编号	字符	最少8位，最长15位	Y	用户ID，该值由福彩中心创建且对每个用户是唯一的
交易时间	字符	格式为：YYYY-MM-DD HH:MM:SS	Y	格式为：YYYY-MM-DD HH:MM:SS
	 * 
	 * 
	 * @author sparrow
	 *
	 */
	public static class Request{
		@ProtocolElementMetaType(
				sequenceId=0,
				elementType=ProtocolElementType.STRING,
				maxLength=20
				)
		 String serialNumber;
		
		@ProtocolElementMetaType(sequenceId=1,
				elementType=ProtocolElementType.STRING,
				minLenght=8,
				maxLength=15
				)
		 String userId;
		
		@ProtocolElementMetaType(sequenceId=2,
				elementType=ProtocolElementType.DATETIME
				)
		 String transactionTime;

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

		public String getTransactionTime() {
			return transactionTime;
		}

		public void setTransactionTime(String transactionTime) {
			this.transactionTime = transactionTime;
		}

		@Override
		public String toString() {
			return "Request [serialNumber=" + serialNumber + ", userId="
					+ userId + ", transactionTime=" + transactionTime + "]";
		}
		
		
		
		
		
	}
	
	/**
	 * 
	 * 
状态码	字符	4位	Y	0000 交易成功，见附录B
状态码描述	字符	最长30位	Y	对状态码的文字说明信息
银行流水号	字符	最长20位	Y	用于银行标识每一笔请求交易（必须唯一）
用户编号	字符	最少8位，最长15位	Y	用户ID，该值由福彩中心创建且对每个用户是唯一的
用户姓名	字符	最长10位	Y	用户的姓名
证件类型	字符	1位	Y	用户的证件类型1、身份证；2、军官证；3、护照；4.士兵证、5警官证；6、港澳通行证；7、其他
证件号码	字符	最长20位	Y	用户注册时留在福彩中心系统的证件号码
账户余额	字符	最长20位	N	用户的虚拟资金

	 * @author sparrow
	 *
	 */
	
	public static class Response extends StateMessage{
		
		@ProtocolElementMetaType(sequenceId=2,
				
				elementType=ProtocolElementType.STRING,
				minLenght=10,
				maxLength=20
				)
		 String serialNumber;
		
		@ProtocolElementMetaType(sequenceId=3,
				elementType=ProtocolElementType.STRING,
				minLenght=8,
				maxLength=15
				)
		 String userId;
		
		@ProtocolElementMetaType(sequenceId=4,
				elementType=ProtocolElementType.STRING,
				maxLength=10
				)
		 String userName;
		
		@ProtocolElementMetaType(sequenceId=5,
				elementType=ProtocolElementType.STRING,
				maxLength=1,
				minLenght=1
				)
		 String idType;
		
		@ProtocolElementMetaType(sequenceId=6,
				elementType=ProtocolElementType.STRING,
				maxLength=20
				)
		 String idNumber;
		
		@ProtocolElementMetaType(sequenceId=7,
				elementType=ProtocolElementType.STRING,
				maxLength=20
				)
		 String accountBalance;

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

		public String getIdType() {
			return idType;
		}

		public void setIdType(String idType) {
			this.idType = idType;
		}

		public String getIdNumber() {
			return idNumber;
		}

		public void setIdNumber(String idNumber) {
			this.idNumber = idNumber;
		}

		public String getAccountBalance() {
			return accountBalance;
		}

		public void setAccountBalance(String accountBalance) {
			this.accountBalance = accountBalance;
		}

		@Override
		public String toString() {
			return "Response [serialNumber=" + serialNumber + ", userId="
					+ userId + ", userName=" + userName + ", idType=" + idType
					+ ", idNumber=" + idNumber + ", accountBalance="
					+ accountBalance + "]"+super.toString();
		}
		
		
		
		
	}
	

	

	

	public P240001(String transcode, String from, String to, String packet,
			boolean isRequest) {
		super(transcode, from, to, packet, isRequest);
		// TODO Auto-generated constructor stub
	}
	
	
	
	

	public P240001() {
		super();
		// TODO Auto-generated constructor stub
	}

	public P240001(String transcode, String from, String to, String request,
			String response) {
		super(transcode, from, to, request, response);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "P240001 [request=" + request + ", response=" + response + "]";
	}





	public P240001(String transcode, String from, String to) {
		super(transcode, from, to);
		// TODO Auto-generated constructor stub
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
