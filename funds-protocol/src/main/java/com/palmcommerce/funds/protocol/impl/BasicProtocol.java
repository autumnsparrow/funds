package com.palmcommerce.funds.protocol.impl;

import com.palmcommerce.funds.protocol.covertor.exception.ProtocolConvertorException;
import com.palmcommerce.funds.protocol.parser.exception.ProtocolParserException;
import com.palmcommerce.funds.protocol.trade.IStatus;
import com.palmcommerce.funds.protocol.validator.exception.ProtocolValidtorException;

public abstract class BasicProtocol implements IProtocol, IStatus {

	protected Header header;

	public BasicProtocol() {
		// TODO Auto-generated constructor stub
		super();
	}

	protected String[] entities;

	protected String requestPacket;
	protected String responsePacket;

	String transcode;
	String from;
	String to;

	boolean isRequest;

	String globalSerial;

	/**
	 * @return the globalSerial
	 */
	public String getGlobalSerial() {
		return globalSerial;
	}

	/**
	 * @param globalSerial
	 *            the globalSerial to set
	 */
	public void setGlobalSerial(String globalSerial) {
		this.globalSerial = globalSerial;
	}

	public BasicProtocol(String transcode, String from, String to) {
		super();
		this.transcode = transcode;
		this.from = from;
		this.to = to;
		updateHeader();
	}

	public BasicProtocol(String transcode, String from, String to,
			String packet, boolean isRequest) {
		super();
		this.transcode = transcode;
		this.from = from;
		this.to = to;
		this.isRequest = isRequest;

		if (this.isRequest)
			this.requestPacket = packet;
		else
			this.responsePacket = packet;

		updateHeader();
	}

	private void updateHeader() {
		if (header == null) {
			header = new Header(transcode, from, to, isRequest);
		} else {
			header.header(transcode, from, to, isRequest);
		}

	}

	public BasicProtocol(String transcode, String from, String to,
			String request, String response) {
		super();
		this.transcode = transcode;
		this.from = from;
		this.to = to;

		updateHeader();

		this.responsePacket = response;
		this.requestPacket = request;
	}

	/**
	 * 
	 * convert the string to object.
	 * 
	 * @param transcode
	 * @param from
	 * @param to
	 * @param packet
	 * @param isRequest
	 * @throws ProtocolConvertorException
	 */
	public Header getHeader() {
		return header;

		// unmashall(isRequest);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.palmcommerce.funds.protocol.impl.IDispatcher#mashall(boolean)
	 */
	public void mashall(boolean isRequest) throws ProtocolConvertorException {
		if (isRequest) {
			this.requestPacket = header.convetor.convert(getRequestObject());
		} else {
			this.responsePacket = header.convetor.convert(getResponseObject());
		}
	}

	/**
	 * 
	 * 
	 * @param ex
	 */
	public void mashallException(String state, String message) {
		Object obj = header.convetor.convertException(getResponseClazz(),
				state, message);
		// setResponseObject(obj);
		try {
			this.responsePacket = header.convetor.convert(obj);
		} catch (ProtocolConvertorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			this.transcode = "99999999";
			this.responsePacket = "9999|Mashall exception failed";
		}
	}

	/*
	 * 
	 * (non-Javadoc)
	 * 
	 * @see com.palmcommerce.funds.protocol.impl.IDispatcher#unmashall(boolean)
	 */
	public void unmashall(boolean isRequest) throws ProtocolConvertorException {
		if (isRequest) {
			if (this.requestPacket == null) {
				throw CONVER_EXCEPTION_REQEUST_PACTET_IS_NULL;
			}
			try {
				entities = header.parser.parse(this.requestPacket);
			} catch (ProtocolParserException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				// mashallException(e.getState(), e.getMessage());
				throw new ProtocolConvertorException(
						CONVER_EXCEPTION_REQUEST_PACTET_FAIL.getMessage());
			}
			Object requestObj = header.convetor.convert(getRequestClazz(),
					entities);
			setRequestObject(requestObj);
		} else {
			if (this.responsePacket == null) {
				throw CONVER_EXCEPTION_RESPONSE_PACTET_IS_NULL;
			}

			 
				try {
					entities = header.parser.parse(this.responsePacket);
				} catch (ProtocolParserException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					// mashallException(e.getState(), e.getMessage());
					throw new ProtocolConvertorException(
							CONVER_EXCEPTION_RESPONSE_PACTET_FAIL.getMessage());
				}
				Object responseObj = header.convetor.convert(
						getResponseClazz(), entities);
				setResponseObject(responseObj);
			}
		

	}

	public abstract Class<?> getRequestClazz();

	public abstract Class<?> getResponseClazz();

	public abstract <T> void setRequestObject(T obj);

	public abstract <T> void setResponseObject(T obj);

	public abstract <T> T getRequestObject();

	public abstract <T> T getResponseObject();
	
	

	public String getRequestPacket() {
		return requestPacket;
	}

	public String getResponsePacket() {
		return responsePacket;
	}

	public void setRequestPacket(String requestPacket) {
		this.requestPacket = requestPacket;
	}

	public void setResponsePacket(String responsePacket) {

		this.responsePacket = responsePacket;
	}

}
