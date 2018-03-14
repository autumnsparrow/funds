/**
 * 
 */
package com.palmcommerce.funds.protocol.impl.t2p;

import com.palmcommerce.funds.protocol.ProtocolElementMetaType;
import com.palmcommerce.funds.protocol.ProtocolElementMetaType.ProtocolElementType;
import com.palmcommerce.funds.protocol.impl.BasicProtocol;
import com.palmcommerce.funds.protocol.impl.StateMessage;

/**
 * @author ParmCommerce
 *
 */
public class T260004 extends BasicProtocol {
	
	public static class Request{
		@ProtocolElementMetaType(sequenceId=0,elementType=ProtocolElementType.STRING,maxLength=20)
		String cardNumber;

		public String getCardNumber() {
			return cardNumber;
		}

		public void setCardNumber(String cardNumber) {
			this.cardNumber = cardNumber;
		}
		
		
	}
	
	
	public static class Response extends StateMessage{
		@ProtocolElementMetaType(sequenceId=2,elementType=ProtocolElementType.STRING,maxLength=20)
		String bankNodeCode;

		public String getBankNodeCode() {
			return bankNodeCode;
		}

		public void setBankNodeCode(String bankNodeCode) {
			this.bankNodeCode = bankNodeCode;
		}
		
	}

	
	public Request request;
	public Response response;
	/**
	 * 
	 */
	public T260004() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param transcode
	 * @param from
	 * @param to
	 */
	public T260004(String transcode, String from, String to) {
		super(transcode, from, to);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param transcode
	 * @param from
	 * @param to
	 * @param packet
	 * @param isRequest
	 */
	public T260004(String transcode, String from, String to, String packet,
			boolean isRequest) {
		super(transcode, from, to, packet, isRequest);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param transcode
	 * @param from
	 * @param to
	 * @param request
	 * @param response
	 */
	public T260004(String transcode, String from, String to, String request,
			String response) {
		super(transcode, from, to, request, response);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see com.palmcommerce.funds.protocol.impl.BasicProtocol#getRequestClazz()
	 */
	@Override
	public Class<?> getRequestClazz() {
		// TODO Auto-generated method stub
		return Request.class;
	}

	/* (non-Javadoc)
	 * @see com.palmcommerce.funds.protocol.impl.BasicProtocol#getResponseClazz()
	 */
	@Override
	public Class<?> getResponseClazz() {
		// TODO Auto-generated method stub
		return Response.class;
	}

	/* (non-Javadoc)
	 * @see com.palmcommerce.funds.protocol.impl.BasicProtocol#setRequestObject(java.lang.Object)
	 */
	@Override
	public <T> void setRequestObject(T obj) {
		// TODO Auto-generated method stub
			this.request=(Request)obj;
	}

	/* (non-Javadoc)
	 * @see com.palmcommerce.funds.protocol.impl.BasicProtocol#setResponseObject(java.lang.Object)
	 */
	@Override
	public <T> void setResponseObject(T obj) {
		// TODO Auto-generated method stub
		this.response=(Response) obj;
	}

	/* (non-Javadoc)
	 * @see com.palmcommerce.funds.protocol.impl.BasicProtocol#getRequestObject()
	 */
	@Override
	public <T> T getRequestObject() {
		// TODO Auto-generated method stub
		return (T)this.request;
	}

	/* (non-Javadoc)
	 * @see com.palmcommerce.funds.protocol.impl.BasicProtocol#getResponseObject()
	 */
	@Override
	public <T> T getResponseObject() {
		// TODO Auto-generated method stub
		return (T)this.response;
	}

}
