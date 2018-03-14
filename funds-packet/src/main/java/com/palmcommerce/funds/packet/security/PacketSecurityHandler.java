/**
 * 
 */
package com.palmcommerce.funds.packet.security;

import java.nio.ByteBuffer;

import com.palmcommerce.funds.packet.Packet;
import com.palmcommerce.funds.packet.security.exception.DecryptPacketException;
import com.palmcommerce.funds.packet.security.exception.EncryptPacketException;
import com.palmcommerce.funds.packet.security.exception.SignaturePacketException;
import com.palmcommerce.funds.packet.security.exception.ValidatePacketException;
import com.palmcommerce.funds.packet.security.openssl.DecryptFactory;
import com.palmcommerce.funds.packet.security.openssl.EncryptFactory;

/**
 * @author sparrow
 *
 */
public class PacketSecurityHandler implements IPacketSecurity{

	
	private static final PacketSecurityHandler factory=new PacketSecurityHandler();
	
	public static PacketSecurityHandler getInstance(){
		return factory;
	}
	/**
	 * 
	 */
	public PacketSecurityHandler() {
		// TODO Auto-generated constructor stub
		enable=true;
	
	}

	
	public boolean decryptPacket(Packet packet) throws DecryptPacketException {
		// TODO Auto-generated method stub
		// 1. must assert the packet packet.length and packet.packet
		boolean ret=false;
		
		if(packet.isReadReady){
			packet.decryptBody=DecryptFactory.getFactory().getDecrypt().decrypt(packet.decryptBody, packet.toCode);
			ret=true;
		}
		
		return ret;
	}

	
	public boolean encryptPacket(Packet packet) throws EncryptPacketException {
		// TODO Auto-generated method stub
		boolean ret=false;
		packet.encryptBody=EncryptFactory.getFactory().getEncrypt().encrypt(packet.encryptBody, packet.fromCode);
		ret=true;
		return ret;
	}

	
	public boolean validateSignature(Packet packet)
			throws ValidatePacketException {
		// TODO Auto-generated method stub
		boolean ret=false;
		
		if(packet.isReadReady){
			ret=DecryptFactory.getFactory().
					getDecrypt().validateSignature(packet.decryptBody,
					packet.packetLen-Packet.PACKET_HEADER_LEN-packet.signatureLength, 
					packet.signatureLength, packet.fromCode);
			// covert the byte to string
			packet.parseBodyFromBinary();
			ret=true;
		}
		return false;
	}

	
	public boolean signature(Packet packet) throws SignaturePacketException {
		// TODO Auto-generated method stub
		boolean ret=false;
		packet.parseBodyFromText();
		if(packet.isParseReady){
			byte[] signature=EncryptFactory.getFactory().getEncrypt().signature(packet.decryptBody, packet.fromCode);
			ByteBuffer buffer=ByteBuffer.allocate(packet.decryptBody.length+signature.length);
			buffer.put(packet.decryptBody);
			buffer.put(signature);
			buffer.flip();
			packet.encryptBody=buffer.array();
		}
		
		
		return ret;
	}

	
	public boolean mashall(Packet packet) throws EncryptPacketException,
			SignaturePacketException {
		// TODO Auto-generated method stub
		boolean ret=true;
		if(enable){
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
		ret=decryptPacket(packet);
		if(ret){
			ret=validateSignature(packet);
		}
		}
		return ret;
	}
	
	boolean enable;
	/* (non-Javadoc)
	 * @see com.palmcommerce.funds.packet.security.IPacketSecurity#setEnable(boolean)
	 */
	public void setEnable(boolean enable) {
		// TODO Auto-generated method stub
		this.enable=enable;
	}

}
