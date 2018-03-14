/**
 * 
 */
package com.palmcommerce.funds.packet.security;

import java.nio.ByteBuffer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;

import com.palmcommerce.funds.packet.Packet;
import com.palmcommerce.funds.packet.security.exception.DecryptPacketException;
import com.palmcommerce.funds.packet.security.exception.EncryptPacketException;
import com.palmcommerce.funds.packet.security.exception.SignaturePacketException;
import com.palmcommerce.funds.packet.security.exception.ValidatePacketException;
import com.palmcommerce.funds.packet.security.openssl.DecryptFactory;
import com.palmcommerce.funds.packet.security.openssl.EncryptFactory;
import com.palmcommerce.funds.packet.security.openssl.IDecrypt;
import com.palmcommerce.funds.packet.security.openssl.IEncrypt;
import com.palmcommerce.funds.packet.util.HexDump;

/**
 * @author sparrow
 *
 */
public class OpensslPacketSecurity implements IPacketSecurity{
	private static final Log logger=LogFactory.getLog(OpensslPacketSecurity.class);
	
//	private static final PacketSecurityHandler factory=new PacketSecurityHandler();
//	
//	public static PacketSecurityHandler getInstance(){
//		return factory;
//	}
	
	private boolean enableDebug;
	
	public void setEnableDebug(boolean enableDebug) {
		this.enableDebug = enableDebug;
	}


	private void log(String message){
		if(enableDebug){
			logger.info(message);
		}
	}
	
	IDecrypt decrypt;
	IEncrypt encrypt;
	
	public void setDecrypt(IDecrypt decrypt) {
		this.decrypt = decrypt;
		
	}


	public void setEncrypt(IEncrypt encrypt) {
		this.encrypt = encrypt;
	}


	/**
	 * 
	 */
	public OpensslPacketSecurity() {
		// TODO Auto-generated constructor stub
		enableDebug=false;
		enable=true;
	}

	
	public boolean decryptPacket(Packet packet) throws DecryptPacketException {
		// TODO Auto-generated method stub
		// 1. must assert the packet packet.length and packet.packet
		boolean ret=false;
		
		if(packet.isReadReady){
			
			packet.decryptBody=decrypt.decrypt(packet.encryptBody, packet.toCode);
			ret=true;
		}
		
		return ret;
	}

	
	public boolean encryptPacket(Packet packet) throws EncryptPacketException {
		// TODO Auto-generated method stub
		boolean ret=false;
		packet.encryptBody=encrypt.encrypt(packet.decryptBody, packet.fromCode);
		log("encrypted body:"+HexDump.dumpHexString(packet.encryptBody));
		ret=true;
		return ret;
	}

	
	public boolean validateSignature(Packet packet)
			throws ValidatePacketException {
		// TODO Auto-generated method stub
		boolean ret=false;
		
		if(packet.isReadReady){
			int offset=packet.decryptBody.length-packet.signatureLength;
			
			log("signature offset:"+offset+" packet length:"+packet.decryptBody.length);
			log("decrypt body:"+HexDump.dumpHexString(packet.decryptBody));
			log("validateSignature:"+HexDump.dumpHexString(packet.decryptBody,offset, 
					packet.signatureLength));
			ret=decrypt.validateSignature(packet.decryptBody,
					packet.packetLen-Packet.PACKET_HEADER_LEN-packet.signatureLength, 
					packet.signatureLength, packet.fromCode);
			// covert the byte to string
			packet.parseBodyFromBinary();
			log("unpacket:"+packet.toString());
			ret=true;
		}
		return ret;
	}

	
	public boolean signature(Packet packet) throws SignaturePacketException {
		// TODO Auto-generated method stub
		boolean ret=false;
		packet.parseBodyFromText();
		if(packet.isParseReady){
			byte[] signature=encrypt.signature(packet.decryptBody, packet.fromCode);
			log("Signature:"+HexDump.dumpHexString(signature));
			ByteBuffer buffer=ByteBuffer.allocate(packet.decryptBody.length+signature.length);
			buffer.put(packet.decryptBody);
			buffer.put(signature);
			buffer.flip();
			packet.decryptBody=buffer.array();
			log("Body+Signautre:"+HexDump.dumpHexString(packet.decryptBody));
			ret=true;
		}
		
		
		return ret;
	}

	
	public boolean mashall(Packet packet) throws EncryptPacketException,
			SignaturePacketException {
		// TODO Auto-generated method stub
		boolean ret=true;
		if(enable){
		log("mashalling ...."+packet.toString());
		ret=signature(packet);
		if(ret){
			ret=encryptPacket(packet);
		}
		}
		return ret;
	}

	
	public boolean unMashall(Packet packet) throws DecryptPacketException,
			ValidatePacketException {
		// TODO Auto-generated method stub
		boolean ret=true;
		if(enable){
		if(packet.isReadReady) {
		log("unmashalling..."+HexDump.dumpHexString(packet.encryptBody));
		ret=decryptPacket(packet);
		if(ret){
			ret=validateSignature(packet);
		}
		}else{
			throw new DecryptPacketException("packet read failed");
		}
		}
		return ret;
	}

	
	private boolean enable;

	/* (non-Javadoc)
	 * @see com.palmcommerce.funds.packet.security.IPacketSecurity#setEnable(boolean)
	 */
	public void setEnable(boolean enable) {
		// TODO Auto-generated method stub
		this.enable=enable;
		
	}

}
