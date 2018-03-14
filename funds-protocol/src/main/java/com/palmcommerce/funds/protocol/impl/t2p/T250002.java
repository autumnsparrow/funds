package com.palmcommerce.funds.protocol.impl.t2p;

import com.palmcommerce.funds.protocol.ProtocolClassMetaType;
import com.palmcommerce.funds.protocol.ProtocolDriverManager;
import com.palmcommerce.funds.protocol.ProtocolElementMetaType;
import com.palmcommerce.funds.protocol.ProtocolElementMetaType.ProtocolElementType;
import com.palmcommerce.funds.protocol.covertor.exception.ProtocolConvertorException;
import com.palmcommerce.funds.protocol.impl.BasicProtocol;
import com.palmcommerce.funds.protocol.impl.StateMessage;


/**
 * 6.2.3  三级用户信息验证
 * 6.2.3.1 业务描述：
中心发展的三级用户借助银行完成实名制信息验证。
三级个人用户在激活个人用户的时候，由福彩中心发送身份证信息给银行，银行根据身份证信息进行信息验证并返回给福彩中心对应的结果。

 * @author lottery
 *
 */
@ProtocolClassMetaType
public class T250002 extends BasicProtocol {
	
	

	public T250002(String transcode, String from, String to) {
		super(transcode, from, to);
		// TODO Auto-generated constructor stub
	}



	public Request request;
	public Response response;
	
	/**
中心流水号	字符	最长20位	Y	用于中心标识每一笔请求交易（必须唯一）
用户姓名	字符	最长10位	Y	用户的姓名
证件类型	字符	1位	Y	用户的证件类型1、身份证；2、军官证；3、护照；4.士兵证、5警官证；6、港澳通行证；7、其他
证件号码	字符	最长20位	Y	用户所填写的证件号码
帐户	字符	最长20位	Y	用户在银行的帐户
交易时间	字符	时间格式为：YYYY-MM-DD HH:MM:SS	Y	格式为：YYYY-MM-DD HH:MM:SS

	 * @author lottery
	 *
	 */
	public static class Request{
		@ProtocolElementMetaType(sequenceId=0,elementType=ProtocolElementType.STRING,maxLength=20)
		String centerNumber;
		@ProtocolElementMetaType(sequenceId=1,elementType=ProtocolElementType.STRING,maxLength=10)
		String userName;
		@ProtocolElementMetaType(sequenceId=2,elementType=ProtocolElementType.STRING,maxLength=1 ,minLenght=1,enumSet="1|2|3|4|5|6|7|")
		String idType;
		@ProtocolElementMetaType(sequenceId=3,elementType=ProtocolElementType.STRING,maxLength=20)
		String idNumber;
		@ProtocolElementMetaType(sequenceId=4,elementType=ProtocolElementType.STRING,maxLength=20)
		String account;
		@ProtocolElementMetaType(sequenceId=5,elementType=ProtocolElementType.DATETIME)
		String transactionTime;
		public String getCenterNumber() {
			return centerNumber;
		}
		public void setCenterNumber(String centerNumber) {
			this.centerNumber = centerNumber;
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
		@Override
		public String toString() {
			return "Request [centerNumber=" + centerNumber + ", userName="
					+ userName + ", idType=" + idType + ", idNumber="
					+ idNumber + ", account=" + account + ", transactionTime="
					+ transactionTime + ", toString()=" + super.toString()
					+ "]";
		}
		
		
	}
	
	/**
状态码	字符	4位	Y	0000 交易成功，见附录B
状态码描述	字符	最长30位	Y	对状态码的文字说明信息
中心流水号	字符	最长20位	Y	银行处理后原样返回

	 * @author lottery
	 *
	 */
	public static class Response extends StateMessage{
		@ProtocolElementMetaType(sequenceId=2,elementType=ProtocolElementType.STRING,maxLength=20)
		String centerNumber;

		public String getCenterNumber() {
			return centerNumber;
		}

		public void setCenterNumber(String centerNumber) {
			this.centerNumber = centerNumber;
		}

		@Override
		public String toString() {
			return "Response [centerNumber=" + centerNumber + ", toString()="
					+ super.toString() + "]";
		}
		
		
	}
	
	
	
	public T250002() {
	}



	public T250002(String transcode, String from, String to, String packet,
			boolean isRequest) {
		super(transcode, from, to, packet, isRequest);
	}



	public T250002(String transcode, String from, String to, String request,
			String response) {
		super(transcode, from, to, request, response);
		// TODO Auto-generated constructor stub
	}



	



	@Override
	public String toString() {
		return "T250002 [request=" + request + ", response=" + response + "]";
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
