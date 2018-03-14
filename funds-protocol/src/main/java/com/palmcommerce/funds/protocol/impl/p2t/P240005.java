package com.palmcommerce.funds.protocol.impl.p2t;

import com.palmcommerce.funds.protocol.ProtocolClassMetaType;
import com.palmcommerce.funds.protocol.ProtocolDriverManager;
import com.palmcommerce.funds.protocol.ProtocolElementMetaType;
import com.palmcommerce.funds.protocol.ProtocolElementMetaType.ProtocolElementType;

import com.palmcommerce.funds.protocol.impl.BasicProtocol;
import com.palmcommerce.funds.protocol.impl.StateMessage;


/**
 * 6.1.6  用户柜台开户交易
 * 6.1.6.1 业务描述：
支持用户在银行柜台进行开户办理彩票业务，用户可在银行柜台完成账户的开户，
需要同步的信息有：真实姓名、证件类型、身份证号，手机号，银行卡号,
同时需要银行对用户真实姓名、证件类型、身份证号正确性进行校验
 * @author lottery
 *
 */
@ProtocolClassMetaType
public class P240005 extends BasicProtocol {

	public Request request;
	public Response response;
	
	/**
银行流水号	字符	最长20位	Y	用于银行标识每一笔请求交易（必须唯一）
真实姓名	字符	最长10位	Y	真实姓名
证件类型	字符	1位	Y	1、身份证 ；2、军官证；3、护照。
证件号码	字符	最长20位	Y	证件号码
手机号	字符	11位	Y	手机号
账户	    字符	最长20位	N	银行卡号
交易时间	字符	时间格式为：YYYY-MM-DD HH:MM:SS	Y	格式为：YYYY-MM-DD HH:MM:SS
账务日期	字符	格式为：YYYYMMDD	Y	格式为：YYYYMMDD

	 * @author lottery
	 *
	 */
	public static class Request{
		@ProtocolElementMetaType(sequenceId=0,elementType=ProtocolElementType.STRING,maxLength=20)
		String serialNumber;
		@ProtocolElementMetaType(sequenceId=1,elementType=ProtocolElementType.STRING,maxLength=10)
		String userName;
		@ProtocolElementMetaType(sequenceId=2,elementType=ProtocolElementType.STRING,maxLength=1 ,minLenght=1,enumSet="1|2|3|")
		String idType;
		@ProtocolElementMetaType(sequenceId=3,elementType=ProtocolElementType.STRING,maxLength=20)
		String idNumber;
		@ProtocolElementMetaType(sequenceId=4,elementType=ProtocolElementType.STRING,maxLength=11,minLenght=11)
		String phone;
		@ProtocolElementMetaType(sequenceId=5,elementType=ProtocolElementType.STRING,maxLength=20,required=false)
		String account;
		@ProtocolElementMetaType(sequenceId=6,elementType=ProtocolElementType.DATETIME)
		String transactionTime;
		@ProtocolElementMetaType(sequenceId=7,elementType=ProtocolElementType.DATETIME,dateTimeFormat="yyyyMMdd")
		String accountTime;
		public String getSerialNumber() {
			return serialNumber;
		}
		public void setSerialNumber(String serialNumber) {
			this.serialNumber = serialNumber;
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
		public String getPhone() {
			return phone;
		}
		public void setPhone(String phone) {
			this.phone = phone;
		}
		public String getAccount() {
			return account;
		}
		public void setAccount(String account) {
			this.account = account;
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
			return "Request [serialNumber=" + serialNumber + ", userName="
					+ userName + ", idType=" + idType + ", idNumber="
					+ idNumber + ", phone=" + phone + ", account=" + account
					+ ", transactionTime=" + transactionTime + ", accountTime="
					+ accountTime + ", toString()=" + super.toString() + "]";
		}
		
		
	}
	
	/**
状态码	字符	4位	Y	0000 交易成功，见附录B
状态码描述	字符	最长30位	Y	对状态码的文字说明信息
银行流水号	字符	最长20位	Y	资金归集管理平台处理后原样返回
用户编号	字符	最少8位，最长15位	Y	对应缴款的银行流水号
用户姓名	字符	最长10位	Y	用户的姓名

	 * @author lottery
	 *
	 */
	public static class Response extends StateMessage{
		@ProtocolElementMetaType(sequenceId=2,elementType=ProtocolElementType.STRING,maxLength=20)
		String serialNumber;
		@ProtocolElementMetaType(sequenceId=3,elementType=ProtocolElementType.STRING,maxLength=15,minLenght=8)
		String userId;
		@ProtocolElementMetaType(sequenceId=4,elementType=ProtocolElementType.STRING,maxLength=10)
		String userName;
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
			return "Response [serialNumber=" + serialNumber + ", userId="
					+ userId + ", userName=" + userName + ", toString()="
					+ super.toString() + "]";
		}
		
	}

	public P240005() {
		super();
	}
	
	public P240005(String transcode, String from, String to, String packet,
			boolean isRequest) {
		super(transcode, from, to, packet, isRequest);
	}

	public P240005(String transcode, String from, String to, String request,
			String response) {
		super(transcode, from, to, request, response);
		// TODO Auto-generated constructor stub
	}

	
	@Override
	public String toString() {
		return "P240005 [request=" + request + ", response=" + response + "]";
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

	public P240005(String transcode, String from, String to) {
		super(transcode, from, to);
		// TODO Auto-generated constructor stub
	}


}
