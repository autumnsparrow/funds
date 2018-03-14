/**
 * @company Palm Lottery Information&Technology Co.,Ltd.
 *
 * @author  sparrow
 *
 * @date    Sep 29, 2013
 *
 */
package com.palmcommerce.funds.demo;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Security;
import java.security.Signature;
import java.security.SignatureException;
import java.security.cert.CertificateException;
import java.security.interfaces.RSAPrivateKey;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author sparrow
 *
 */
public class SecurityOpensslCryptor implements ICryptor{
	
	
	

	
	private static Log logger=LogFactory.getLog(SecurityOpensslCryptor.class);
	private PrivateKey localKey;
	// private Public
	private PublicKey remoteCrt;
	
	
	
	private Signature sig;
	private Cipher cipher;
	SecurityConfigurationByBean configuration;
	CryptoFileLoader fileLoader;
	
	/**
	 * @return the configuration
	 */
	public SecurityConfigurationByBean getConfiguration() {
		return configuration;
	}



	/**
	 * @param configuration the configuration to set
	 */
	public void setConfiguration(SecurityConfigurationByBean configuration) {
		this.configuration = configuration;
	}



	/**
	 * 
	 */
	public SecurityOpensslCryptor() {
		// TODO Auto-generated constructor stub
		super();
	}



	public SecurityOpensslCryptor(SecurityConfigurationByBean configuration,CryptoFileLoader fileLoader) {
		super();
		this.configuration = configuration;
		this.fileLoader=fileLoader;
		
	}
	
	
	public void initialize() throws CertificateException, NoSuchProviderException, IOException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, SignatureException{
			logger.info("[loading configuration - ] "+this.configuration.toString());
			this.sig = Signature.getInstance(this.configuration.getSignature());
			this.cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
		
		
		// load the CA
		
			
			this.localKey=this.fileLoader.loadPrivateKey(this.configuration.getPrivateKeyFile(), this.configuration.getPrivateKeyPass(), RSAPrivateKey.class);
			this.remoteCrt=this.fileLoader.loadCertificate(this.configuration.getRemotePublicKeyUrl(), this.configuration.getCa());
		
	}
	
	
	/**
	 * 
	 * 
	 *  ICryptor interface 
	 * 
	 * 
	 * 
	 */

	public synchronized byte[] signature(byte[] content) throws Exception {

		this.sig.initSign(localKey);
		sig.update(content);
		byte[] signed = sig.sign();
		logger.info("[ signature content - ] "+HexDump.dumpHexString(content));
		logger.info("[ signature - ] "+HexDump.dumpHexString(signed));
		return signed;

	}

	public synchronized boolean validateSignature(byte[] content)
			throws Exception {
		sig.initVerify(remoteCrt);
		sig.update(content,0, content.length-this.configuration.getSiglen());
		boolean ret = sig.verify(content, content.length-this.configuration.getSiglen(), this.configuration.getSiglen());
		
		logger.info("[ validate signature -] "+HexDump.dumpHexString(content,0, content.length-this.configuration.getSiglen()));
		logger.info(" ret -"+ret);
		logger.info("[ raw data -]"+new String(content,0,content.length-this.configuration.getSiglen(),"GBK"));
		return ret;
	}

	public synchronized byte[] decrypt(byte[] content) throws Exception {
		cipher.init(Cipher.DECRYPT_MODE, localKey);
		ByteBuffer buffer=ByteBuffer.allocate(1024);
		int blocksize=this.configuration.getSiglen();//cipher.getBlockSize();
		
		for(int i=0;i<content.length;i+=blocksize){
			
			if((content.length-i)>blocksize){
				byte[]  bytes=cipher.doFinal(content,i,blocksize);
				
				buffer.put(bytes);
			}
				
			else{
				byte[]  bytes=cipher.doFinal(content,i,content.length-i);
				
				buffer.put(bytes);
			}
				
		}
		//byte[] encryptedData = cipher.doFinal();
		//buffer.put(cipher.doFinal());
		buffer.flip();
		int len=buffer.limit();
		//byte[]  encryptedData=buffer.array();
		ByteBuffer realBuffer=ByteBuffer.allocate(len);
		realBuffer.put(buffer);
		realBuffer.flip();
		byte[]  bytes=realBuffer.array();
		logger.info("[decrypt -] "+HexDump.dumpHexString(content));
		logger.info("[packet -] "+HexDump.dumpHexString(bytes));
		
		return bytes;

	}

	public synchronized byte[]  encrypt(byte[] content) throws Exception {

		cipher.init(Cipher.ENCRYPT_MODE, remoteCrt);
		int blockSize=this.configuration.getBlockSize();;//cipher.getBlockSize();
		int siglen=this.configuration.getSiglen();
		int bufLen=(content.length/blockSize+(content.length%blockSize>0?1:0))*siglen;
		ByteBuffer buffer=ByteBuffer.allocate(bufLen);
		
		
		for(int i=0;i<content.length;i+=blockSize){
			
			if((content.length-i)>blockSize){
				byte[]  bytes=cipher.doFinal(content,i,blockSize);
				
				buffer.put(bytes);
			}
				
			else{
				byte[]  bytes=cipher.doFinal(content,i,content.length-i);
				
				buffer.put(bytes);
			}
				
		}
		//byte[] encryptedData = cipher.doFinal();
		//buffer.put(cipher.doFinal());
		buffer.flip();
		
		byte[]  bytes=buffer.array();
		logger.info("[packet -] "+HexDump.dumpHexString(content));
		logger.info("[encrypt -] "+HexDump.dumpHexString(bytes));
		return bytes;
	}
	
	
	

	
	
	
	
}
