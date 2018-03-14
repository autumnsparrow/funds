package com.palmcommerce.funds.protocol.impl.p2t;

import com.palmcommerce.funds.protocol.ProtocolClassMetaType;
import com.palmcommerce.funds.protocol.ProtocolDriverManager;
import com.palmcommerce.funds.protocol.impl.BasicProtocol;
import com.palmcommerce.funds.protocol.impl.StateMessage;
import com.palmcommerce.funds.protocol.impl.p2t.P240001.Request;
import com.palmcommerce.funds.protocol.impl.p2t.P240001.Response;

/**
 * 6.1.7  银行发起链路检测交易
 * 6.1.7.1 业务描述：
保持银行与中心系统连接的心跳检查。
1.当系统在规定的时间内没有任何交易发生时用于检测线路、设备、系统、应用程序等是否正常。
2.由银行可发起，可随时发起系统检测。
3.60秒后重新发起检测；

 * @author lottery
 *
 */
@ProtocolClassMetaType
public class P240006 extends BasicProtocol {
	
	public Request request;
	public Response response;
	
	
	public static class Request{
		
	};
	public static class Response extends StateMessage{
		
	};
	
	
	public P240006() {
		super();
	}

	public P240006(String transcode, String from, String to, String packet,
			boolean isRequest) {
		super(transcode, from, to, packet, isRequest);
	}
	
	public P240006(String transcode, String from, String to, String request,
			String response) {
		super(transcode, from, to, request, response);
	}

	public P240006(String transcode, String from, String to) {
		super(transcode, from, to);
	}

	@Override
	public Class<?> getRequestClazz() {
		return Request.class;
	}





	@Override
	public Class<?> getResponseClazz() {
		return Response.class;
	}





	@Override
	public void setRequestObject(Object obj) {
		this.request=(Request) obj;
		
	}





	@Override
	public void setResponseObject(Object obj) {
		this.response=(Response)obj;
	}





	@Override
	public Object getRequestObject() {
		return this.request;
	}





	@Override
	public Object getResponseObject() {
		return this.response;
	}


}
