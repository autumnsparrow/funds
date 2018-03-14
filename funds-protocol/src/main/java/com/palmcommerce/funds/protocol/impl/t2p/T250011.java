package com.palmcommerce.funds.protocol.impl.t2p;

import com.palmcommerce.funds.protocol.ProtocolClassMetaType;
import com.palmcommerce.funds.protocol.ProtocolDriverManager;
import com.palmcommerce.funds.protocol.ProtocolElementMetaType;
import com.palmcommerce.funds.protocol.ProtocolClassMetaType.TRANSFER_MODE;
import com.palmcommerce.funds.protocol.ProtocolElementMetaType.ProtocolElementType;
import com.palmcommerce.funds.protocol.impl.BasicProtocol;
import com.palmcommerce.funds.protocol.impl.StateMessage;


/**
 * 6.2.11  缴费对账交易
 * 6.2.11.1 业务描述：
由福彩中心发起同步交易或异步交易，在日终后1小时进行对账。福彩中心发送对帐请求，
银行方触发生成对帐文件的函数，生成对帐文件之后，银行通过sftp方式将对帐文件
上传到福彩中心的接口前置机，银行校验上传的对帐文件是否完整，校验完整后银行
方返回“成功”或“失败”应答信息；对帐文件的格式与缴费的格式完全一样。
 * @author lottery
 *
 */
@ProtocolClassMetaType(mode=TRANSFER_MODE.DELAY_PROXY)
public class T250011 extends BasicProtocol {
	

	
	public T250011(String transcode, String from, String to) {
		super(transcode, from, to);
		// TODO Auto-generated constructor stub
	}



	public Request request;
	public Response response;
	
	/**
	 * 账务日期	字符		Y	格式为：YYYYMMDD
	 * @author lottery
	 *
	 */
	public static class Request{
		@ProtocolElementMetaType(sequenceId=0,elementType=ProtocolElementType.DATETIME,dateTimeFormat="yyyyMMdd")
		String accountTime;

		public String getAccountTime() {
			return accountTime;
		}

		public void setAccountTime(String accountTime) {
			this.accountTime = accountTime;
		}

		@Override
		public String toString() {
			return "Request [accountTime=" + accountTime + ", toString()="
					+ super.toString() + "]";
		}
		
	}
	
	/**
	 * 状态码	字符	4位	Y	0000 ：成功
对状态码的文字说明信息	字符	最长30位	Y	对状态码的文字说明信息
校验文件大小	字符	最长10位	Y	字节数
	 * @author lottery
	 *
	 */
	public static class Response extends StateMessage{
		@ProtocolElementMetaType(sequenceId=2,elementType=ProtocolElementType.STRING,maxLength=10,required=false)
		String checkFileSize;

		public String getCheckFileSize() {
			return checkFileSize;
		}

		public void setCheckFileSize(String checkFileSize) {
			this.checkFileSize = checkFileSize;
		}

		@Override
		public String toString() {
			return "Response [checkFileSize=" + checkFileSize + ", toString()="
					+ super.toString() + "]";
		}
		
	}
	
	public T250011() {
	}

	public T250011(String transcode, String from, String to, String packet,
			boolean isRequest) {
		super(transcode, from, to, packet, isRequest);
	}

	public T250011(String transcode, String from, String to, String request,
			String response) {
		super(transcode, from, to, request, response);
		// TODO Auto-generated constructor stub
	}

	
	@Override
	public String toString() {
		return "T250011 [request=" + request + ", response=" + response + "]";
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
