/**
 * 
 */
package com.palmcommerce.funds.packet;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.future.WriteFuture;
import org.apache.mina.core.session.IoSession;
import org.w3c.dom.CharacterData;



/**
 * @author sparrow
 *
 */
public class Packet {
	
	@Override
	public String toString() {
		return "Packet [packetLen=" + packetLen + ", transCode=" + transCode
				+ ", fromCode=" + fromCode + ", toCode=" + toCode
				+ ", isReadReady=" + isReadReady + ", isParseReady="
				+ isParseReady + "]";
	}

	public static final int PACKET_HEADER_LEN=26;
	private static final int PACKET_SIGNATURE_LEN=128;
	
	public static final String ENCODING_CHARSET="GB2312";
	
	public final static  CharsetDecoder decoder=Charset.forName("utf-8").newDecoder();
	public final static CharsetEncoder encoder=Charset.forName("utf-8").newEncoder();
	
	// packet format of the transport.
	// encrypted packet
	
	public String transactionSerial;
	// network transfer format.
	public int packetLen;
	
	
	// protocol format
	public String transCode; // 6
	public String fromCode;  // 8
	public String toCode;   // 8
	public int signatureLength; //4
	// encrypt body
	public byte[] encryptBody;
	
	public boolean isReadReady;
	
	// decrypt  body
	public byte[] decryptBody;
	
	// string format
	public String content;
	
	public boolean isParseReady;
	
	
	private void reset(){
		isReadReady=false;
		isParseReady=false;
		packetLen=0;
		transCode="-1";
		fromCode="-1";
		toCode="-1";
		signatureLength=0;
		encryptBody=null;
		decryptBody=null;
		content=null;
	}
	
	/**
	 * 
	 * 
	 * @param buffer
	 */
	public void readPacket(IoSession session,IoBuffer buffer){
		//boolean ret=true;
		reset();
	//	session.resumeRead();
		packetLen=buffer.getInt();
		try {
			
			transCode=buffer.getString(6, decoder);
			fromCode=buffer.getString(8, decoder);
			toCode=buffer.getString(8,decoder);
			signatureLength=buffer.getInt();
			
			buffer.mark();
			if(buffer.remaining()==(packetLen-26)){
			IoBuffer body=IoBuffer.allocate(packetLen-26);
			body.put(buffer);
			body.flip();
			buffer.reset();
			encryptBody=body.array();
			isReadReady=true;
			}else{
				isReadReady=false;
					//ret=false;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			isReadReady=false;
		}finally{
			
			//session.suspendRead();
		}
		
		
		
		
	}
	
	
	public void parseBodyFromBinary(){
		
		
		try {
			content=new String(decryptBody,0,decryptBody.length-signatureLength,ENCODING_CHARSET);
			
			isParseReady=true;
		} catch (UnsupportedEncodingException e) {
		
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void parseBodyFromText(){
		//reset();
		try {
			byte[] bytes=content.getBytes(ENCODING_CHARSET);
			
			ByteBuffer buffer=ByteBuffer.allocate(bytes.length);
			buffer.put(bytes);
			buffer.flip();
			decryptBody=buffer.array();
			isParseReady=true;
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		
	}
	public WriteFuture writePacket(IoSession session){
		packetLen=PACKET_HEADER_LEN+encryptBody.length;
		IoBuffer buffer=IoBuffer.allocate(packetLen+4);
		buffer.putInt(packetLen);
		WriteFuture future=null;
		try {
			buffer.putString(transCode, 6, encoder);
			buffer.putString(fromCode, 8,encoder);
			buffer.putString(toCode,8,encoder);
			buffer.putInt(signatureLength);
			buffer.put(encryptBody);
			buffer.flip();
			//session.resumeWrite();
			if(session.isConnected())
				future=session.write(buffer);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			//session.suspendWrite();
			
		}
		
		return future;
	}
	
	/**
	 * 
	 */
	public Packet() {
		// TODO Auto-generated constructor stub
		super();
		signatureLength=PACKET_SIGNATURE_LEN;
		
		
	}
	
	
	
}
