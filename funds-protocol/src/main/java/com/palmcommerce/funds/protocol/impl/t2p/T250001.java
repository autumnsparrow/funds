package com.palmcommerce.funds.protocol.impl.t2p;

import com.palmcommerce.funds.protocol.ProtocolClassMetaType;
import com.palmcommerce.funds.protocol.ProtocolDriverManager;
import com.palmcommerce.funds.protocol.impl.BasicProtocol;


/**
 * 6.2.2  福彩中心发起链路检测交易
 * 6.2.2.1 业务描述：
保持银行与中心系统连接的心跳检查。
1.当系统在规定的时间内没有任何交易发生时用于检测线路、设备、系统、应用程序等是否正常。
2.由福彩中心发起，可随时发起系统检测。
3.60秒后重新发起检测；

 * @author lottery
 *
 */
@ProtocolClassMetaType
public class T250001 extends BasicProtocol{
	


	public T250001() {
		super();
		// TODO Auto-generated constructor stub
	}





	public T250001(String transcode, String from, String to, String packet,
			boolean isRequest) {
		super(transcode, from, to, packet, isRequest);
		// TODO Auto-generated constructor stub
	}





	public T250001(String transcode, String from, String to, String request,
			String response) {
		super(transcode, from, to, request, response);
		// TODO Auto-generated constructor stub
	}





	public T250001(String transcode, String from, String to) {
		super(transcode, from, to);
		// TODO Auto-generated constructor stub
	}





	public static final class Request{
		
	};
	public static final class Response{
		
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

	Request request;
	Response response;



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
