/**
 * 
 */
package com.palmcommerce.funds.protocol.impl.p2t;

import com.palmcommerce.funds.protocol.ProtocolClassMetaType;
import com.palmcommerce.funds.protocol.ProtocolDriverManager;
import com.palmcommerce.funds.protocol.ProtocolElementMetaType;
import com.palmcommerce.funds.protocol.ProtocolElementMetaType.ProtocolElementType;

import com.palmcommerce.funds.protocol.impl.BasicProtocol;
import com.palmcommerce.funds.protocol.impl.StateMessage;





/**
 * 
 * 
 6.1.3  用户ID和账户绑定交易(240002)
 6.1.3.1 业务描述：
在银行柜面从帐户转帐给福彩帐户的，需要到银行进行用户ID和帐户的绑定。
银行向资金管理平台发起账户绑定请求。资金管理平台请求进行处理并返回响应数据。
绑定成功后，用户即可以进行缴费充值。

 * @author sparrow
 *
 */
@ProtocolClassMetaType
public class P240002 extends BasicProtocol {
	
	public P240002(String transcode, String from, String to) {
		super(transcode, from, to);
		// TODO Auto-generated constructor stub
	}





	static {
		}
	
	public Request request;
	public Response response;
	
	
	
	/**
	 * 
	 * 
银行流水号	字符	最长20位	Y	用于银行标识每一笔请求交易（必须唯一）
用户编号	字符	最少8位， 最长15位	Y	用户ID，该值由福彩中心创建且对每个用户是唯一的
用户姓名	字符	最长10位	Y	用户的姓名
证件类型	字符	1位	Y	用户的证件类型1、身份证；2、军官证；3、护照；4.士兵证、5警官证；6、港澳通行证；7、其他
证件号码	字符	最大20位	Y	用户注册时留在福彩中心系统的证件号码
绑定类型	字符	1位	Y	0表示绑定，1表示解绑
帐户	字符	允许为空	N	渠道在银行绑定的帐户
交易时间	字符	时间格式: YYYY-MM-DD HH:MM:SS	Y	格式为：YYYY-MM-DD HH:MM:SS
账务时间	字符	时间格式: YYYYMMDD	Y	格式为：YYYYMMDD

	 * @author sparrow
	 *
	 */
	public static class Request {
		@ProtocolElementMetaType(sequenceId=0,elementType=ProtocolElementType.STRING,maxLength=20)
		String serialNumber;
		@ProtocolElementMetaType(sequenceId=1,elementType=ProtocolElementType.STRING,maxLength=15,minLenght=8)
		String userId;
		@ProtocolElementMetaType(sequenceId=2,elementType=ProtocolElementType.STRING,maxLength=10)
		String userName;
		@ProtocolElementMetaType(sequenceId=3,elementType=ProtocolElementType.STRING,maxLength=1 
				,minLenght=1,enumSet="1|2|3|4|5|6|7|")
		String idType;
		@ProtocolElementMetaType(sequenceId=4,elementType=ProtocolElementType.STRING,maxLength=20)
		String idNumber;
		@ProtocolElementMetaType(sequenceId=5,elementType=ProtocolElementType.STRING,maxLength=1,minLenght=1
				,enumSet="0|1|")
		String bindType;
		@ProtocolElementMetaType(sequenceId=6,elementType=ProtocolElementType.STRING,required=false)
		String account;
		@ProtocolElementMetaType(sequenceId=7,elementType=ProtocolElementType.DATETIME)
		String transactionTime;
		@ProtocolElementMetaType(sequenceId=8,elementType=ProtocolElementType.DATETIME,dateTimeFormat="yyyyMMdd")
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
		public String getBindType() {
			return bindType;
		}
		public void setBindType(String bindType) {
			this.bindType = bindType;
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
			return "Request [serialNumber=" + serialNumber + ", userId="
					+ userId + ", userName=" + userName + ", idType=" + idType
					+ ", idNumber=" + idNumber + ", bindType=" + bindType
					+ ", account=" + account + ", transactionTime="
					+ transactionTime + ", accountTime=" + accountTime + "]";
		}
		
		
		
	};
	
	
	
	@Override
	public String toString() {
		return "P240002 [request=" + request + ", response=" + response + "]";
	}

	/**
	 * 
	 * 
状态码	字符	4位	Y	0000 交易成功，见附录B
状态码描述	字符	最长30位	Y	对状态码的文字说明信息
银行流水号	字符	最长20位	Y	资金归集管理平台处理后原样返回

	 * @author sparrow
	 *
	 */
	public static class Response extends StateMessage{
		
		@ProtocolElementMetaType(sequenceId=2,elementType=ProtocolElementType.STRING,maxLength=20)
		String serialNumber;

		public String getSerialNumber() {
			return serialNumber;
		}

		public void setSerialNumber(String serialNumber) {
			this.serialNumber = serialNumber;
		}

		@Override
		public String toString() {
			return "Response [serialNumber=" + serialNumber + ", toString()="
					+ super.toString() + "]";
		}
		
		
	}

	/**
	 * 
	 */
	public P240002() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param transcode
	 * @param from
	 * @param to
	 * @param packet
	 * @param isRequest
	 */
	public P240002(String transcode, String from, String to, String packet,
			boolean isRequest) {
		super(transcode, from, to, packet, isRequest);
		// TODO Auto-generated constructor stub
	}

	public P240002(String transcode, String from, String to, String request,
			String response) {
		super(transcode, from, to, request, response);
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
