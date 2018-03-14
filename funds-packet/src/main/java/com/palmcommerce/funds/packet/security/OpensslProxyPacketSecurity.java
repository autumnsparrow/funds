/**
 * 
 */
package com.palmcommerce.funds.packet.security;

import java.awt.SystemColor;
import java.nio.ByteBuffer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.palmcommerce.funds.configuration.v2.ConfigurationManager;
import com.palmcommerce.funds.configuration.v2.RouteRuleEntry;
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
	
import com.palmcommerce.funds.util.Metrics;

/**
 * @author sparrow
 * 
 */
public class OpensslProxyPacketSecurity implements IPacketSecurity {
	private static final Log logger = LogFactory
			.getLog(OpensslProxyPacketSecurity.class);

	// private static final PacketSecurityHandler factory=new
	// PacketSecurityHandler();
	//
	// public static PacketSecurityHandler getInstance(){
	// return factory;
	// }

	@Autowired
	ConfigurationManager configurationManager;

	private boolean enableDebug;
	boolean enable;

	public void setEnableDebug(boolean enableDebug) {
		this.enableDebug = enableDebug;
	}

	private void log(String message) {
		if (enableDebug) {
			logger.info(message);
		}
	}

	Metrics metrics;

	/**
	 * @return the metrics
	 */
	public Metrics getMetrics() {
		return metrics;
	}

	/**
	 * @param metrics
	 *            the metrics to set
	 */
	public void setMetrics(Metrics metrics) {
		this.metrics = metrics;
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
	public OpensslProxyPacketSecurity() {
		// TODO Auto-generated constructor stub
		enableDebug = false;
		enable = true;
	}

	

	public boolean decryptPacket(Packet packet) throws DecryptPacketException {
		// TODO Auto-generated method stub
		// 1. must assert the packet packet.length and packet.packet
		boolean ret = false;
		log("decryptPacket:" + packet.fromCode);
		if (packet.isReadReady) {

			packet.decryptBody = decrypt.decrypt(packet.encryptBody,
					packet.fromCode);
			ret = true;
		}

		return ret;
	}

	public boolean encryptPacket(Packet packet) throws EncryptPacketException {
		// TODO Auto-generated method stub
		boolean ret = false;
		log("encryptPacket:" + packet.toCode);
		packet.encryptBody = encrypt.encrypt(packet.decryptBody, packet.toCode);
		log("encrypted body:" + HexDump.dumpHexString(packet.encryptBody));
		ret = true;
		return ret;
	}

	public boolean validateSignature(Packet packet)
			throws ValidatePacketException {
		// TODO Auto-generated method stub
		boolean ret = false;

		if (packet.isReadReady) {
			int offset = packet.decryptBody.length - packet.signatureLength;
			log("validateSinature:" + packet.fromCode);
			log("signature offset:" + offset + " packet length:"
					+ packet.decryptBody.length);
			log("decrypt body:" + HexDump.dumpHexString(packet.decryptBody));
			log("validateSignature:"
					+ HexDump.dumpHexString(packet.decryptBody, offset,
							packet.signatureLength));
			ret = decrypt.validateSignature(packet.decryptBody,
					packet.decryptBody.length - packet.signatureLength,
					packet.signatureLength, packet.fromCode);
			// covert the byte to string
			packet.parseBodyFromBinary();
			log("unpacket:" + packet.toString());
			ret = true;
		}
		return ret;
	}

	public boolean signature(Packet packet) throws SignaturePacketException {
		// TODO Auto-generated method stub
		boolean ret = false;
		packet.parseBodyFromText();
		if (packet.isParseReady) {
			log("Sinature:" + packet.toCode);
			byte[] signature = encrypt.signature(packet.decryptBody,
					packet.toCode);
			log("Signature:" + HexDump.dumpHexString(signature));
			ByteBuffer buffer = ByteBuffer.allocate(packet.decryptBody.length
					+ signature.length);
			buffer.put(packet.decryptBody);
			buffer.put(signature);
			buffer.flip();
			packet.decryptBody = buffer.array();
			log("Body+Signautre:" + HexDump.dumpHexString(packet.decryptBody));
			ret = true;
		}

		return ret;
	}

	public boolean mashall(Packet packet) throws EncryptPacketException,
			SignaturePacketException {
		// TODO Auto-generated method stub
		boolean ret = true;

		metrics.mark("OpensslProxyPacketSecurity.mashall");
		ret = signature(packet);
		if (ret) {
			ret = encryptPacket(packet);
		}
		metrics.mark("OpensslProxyPacketSecurity.mashall");
		return ret;
	}

	public boolean unMashall(Packet packet) throws DecryptPacketException,
			ValidatePacketException {
		// TODO Auto-generated method stub
		boolean ret = true;
		metrics.mark("OpensslProxyPacketSecurity.unMashall");
		if (packet.isReadReady) {

			ret = decryptPacket(packet);
			// must parse the packet here.

			if (ret) {
				ret = validateSignature(packet);
			}
		} else {
			throw new DecryptPacketException("packet read failed");
		}

		metrics.mark("OpensslProxyPacketSecurity.unMashall");
		return ret;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.palmcommerce.funds.packet.security.IPacketSecurity#setEnable(boolean)
	 */
	public void setEnable(boolean enable) {
		// TODO Auto-generated method stub
		this.enable = enable;

	}

}
