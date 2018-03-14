package com.palmcommerce.funds.protocol.impl.t2p;

import com.palmcommerce.funds.protocol.ProtocolClassMetaType;
import com.palmcommerce.funds.protocol.ProtocolDriverManager;
import com.palmcommerce.funds.protocol.ProtocolElementMetaType;
import com.palmcommerce.funds.protocol.ProtocolElementMetaType.ProtocolElementType;
import com.palmcommerce.funds.protocol.covertor.exception.ProtocolConvertorException;
import com.palmcommerce.funds.protocol.impl.BasicProtocol;
import com.palmcommerce.funds.protocol.impl.StateMessage;


/**
 * 6.2.7  实时汇付接口
 * 6.2.7.1 业务描述：
由福彩中心向银行发起汇付的请求
 * @author lottery
 *
 */
@ProtocolClassMetaType
public class T250007 extends BasicProtocol {
	

	
	public T250007(String transcode, String from, String to) {
		super(transcode, from, to);
		// TODO Auto-generated constructor stub
	}



	public Request request;
	public Response response;
	
	/**
	 * 中心流水号	字符	最长20位	Y	中心流水号用于标识每一笔汇付退款交易（必须唯一）
用户编号	字符	最少8位，最长15位	Y	用户ID，该值对每个用户是唯一的
用户姓名	字符	最长10位	Y	用户的姓名
汇付金额	字符	最长20位	Y	单位为分
账户	字符	最长20位	Y	银行卡号
交易时间	字符	时间格式为：YYYY-MM-DD HH:MM:SS	Y	格式为：YYYY-MM-DD HH:MM:SS
账务日期	字符	时间格式为：YYYYMMDD	Y	格式为：YYYYMMDD

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
		@ProtocolElementMetaType(sequenceId=3,elementType=ProtocolElementType.STRING,maxLength=20)
		String remittanceAmount; 
		@ProtocolElementMetaType(sequenceId=4,elementType=ProtocolElementType.STRING,maxLength=20)
		String account;
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
		public String getRemittanceAmount() {
			return remittanceAmount;
		}
		public void setRemittanceAmount(String remittanceAmount) {
			this.remittanceAmount = remittanceAmount;
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
			return "Request [centerNumber=" + centerNumber + ", userId="
					+ userId + ", userName=" + userName + ", remittanceAmount="
					+ remittanceAmount + ", account=" + account
					+ ", transactionTime=" + transactionTime + ", accountTime="
					+ accountTime + ", toString()=" + super.toString() + "]";
		}
		
	}
	
	/**
	 * 状态码	字符	4位	Y	0000 交易成功，见附录B
状态码描述	字符	最多30位	Y	对状态码的文字说明信息
中心流水号	字符	最长20位	Y	银行处理后原样返回
银行流水号	字符	最长20位	Y	银行生成的唯一流水号
用户编号	字符	最少8位，最长15位	Y	用户ID，该值对每个用户是唯一的
用户姓名	字符	最长10位	Y	用户的姓名
账户	字符	最长20位	Y	银行卡
状态	字符	1	Y	汇付处理结果 0表示处理中；1表示汇付成功；2表示汇付失败
	 * @author lottery
	 *
	 */
	public static class Response extends StateMessage{
		@ProtocolElementMetaType(sequenceId=2,elementType=ProtocolElementType.STRING,maxLength=20)
		String centerNumber;
		@ProtocolElementMetaType(sequenceId=3,elementType=ProtocolElementType.STRING,maxLength=20)
		String serialNumber;
		@ProtocolElementMetaType(sequenceId=4,elementType=ProtocolElementType.STRING,maxLength=15,minLenght=8)
		String userId;
		@ProtocolElementMetaType(sequenceId=5,elementType=ProtocolElementType.STRING,maxLength=10)
		String userName;
		@ProtocolElementMetaType(sequenceId=6,elementType=ProtocolElementType.STRING,maxLength=20)
		String account;
		@ProtocolElementMetaType(sequenceId=7,elementType=ProtocolElementType.STRING,maxLength=1,minLenght=1)
		String state;
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
		public String getAccount() {
			return account;
		}
		public void setAccount(String account) {
			this.account = account;
		}
		public String getState() {
			return state;
		}
		public void setState(String state) {
			this.state = state;
		}
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
		@Override
		public String toString() {
			return "Response [centerNumber=" + centerNumber + ", serialNumber="
					+ serialNumber + ", userId=" + userId + ", userName="
					+ userName + ", account=" + account + ", state=" + state
					+ ", toString()=" + super.toString() + "]";
		}
		
		
	}
	public T250007() {
	}

	public T250007(String transcode, String from, String to, String packet,
			boolean isRequest) {
		super(transcode, from, to, packet, isRequest);
	}



	public T250007(String transcode, String from, String to, String request,
			String response) {
		super(transcode, from, to, request, response);
		// TODO Auto-generated constructor stub
	}

	
	@Override
	public String toString() {
		return "T250007 [request=" + request + ", response=" + response + "]";
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
