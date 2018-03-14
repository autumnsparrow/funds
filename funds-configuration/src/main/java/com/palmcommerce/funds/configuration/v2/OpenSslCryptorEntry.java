/**
 * @company Palm Lottery Information&Technology Co.,Ltd.
 *
 * @author  sparrow
 *
 * @date    Oct 15, 2013
 *
 */
package com.palmcommerce.funds.configuration.v2;

import java.nio.ByteBuffer;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;

/**
 * @author sparrow
 *
 */
public class OpenSslCryptorEntry {
	
	private int blockSize;

	private int siglen;
	
	private PrivateKey localKey;
	// private Public
	private PublicKey remoteCrt;
	
	private Signature sigKey;
	private Cipher cipherKey;
	
	private Signature sigCrt;
	private Cipher cipherCrt;
	
	
	

	/**
	 * @return the localKey
	 */
	public PrivateKey getLocalKey() {
		return localKey;
	}

	/**
	 * @param localKey the localKey to set
	 */
	public void setLocalKey(PrivateKey localKey) {
		this.localKey = localKey;
	}

	/**
	 * @return the remoteCrt
	 */
	public PublicKey getRemoteCrt() {
		return remoteCrt;
	}

	/**
	 * @param remoteCrt the remoteCrt to set
	 */
	public void setRemoteCrt(PublicKey remoteCrt) {
		this.remoteCrt = remoteCrt;
	}
	
	
	String code;

	public OpenSslCryptorEntry(String code,PrivateKey localKey, PublicKey remoteCrt) {
		super();
		this.code=code;
		this.localKey = localKey;
		this.remoteCrt = remoteCrt;

		try {
			sigKey = Signature.getInstance("SHA256withRSA","BC");
			cipherKey = Cipher.getInstance("RSA/ECB/PKCS1Padding","BC");
			sigCrt = Signature.getInstance("SHA256withRSA","BC");
			cipherCrt = Cipher.getInstance("RSA/ECB/PKCS1Padding","BC");
			blockSize=100;
			siglen=128;
			
			sigKey.initSign(localKey);
			sigCrt.initVerify(remoteCrt);
			
			cipherKey.init(Cipher.DECRYPT_MODE, localKey);
			cipherCrt.init(Cipher.ENCRYPT_MODE, remoteCrt);
			
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchProviderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	
	
	public  byte[] signature(byte[] content) throws Exception {

		

		sigKey.update(content);
		byte[] signed = sigKey.sign();
		return signed;

	}

	public  boolean validateSignature(byte[] content)
			throws Exception {
		
		sigCrt.update(content,0, content.length-siglen);
		boolean ret = sigCrt.verify(content, content.length-siglen, siglen);
		return ret;
	}

	public  byte[] decrypt(byte[] content) throws Exception {
		
		ByteBuffer buffer=ByteBuffer.allocate(1024);
		int blocksize=siglen;//cipher.getBlockSize();
		
		for(int i=0;i<content.length;i+=blocksize){
			
			if((content.length-i)>blocksize){
				byte[]  bytes=cipherKey.doFinal(content,i,blocksize);
				buffer.put(bytes);
			}
				
			else{
				byte[]  bytes=cipherKey.doFinal(content,i,content.length-i);
				
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
		
		return realBuffer.array();

	}

	public  byte[]  encrypt(byte[] content) throws Exception {

		
		int blocksize=blockSize;//cipher.getBlockSize();
		int bufLen=(content.length/blockSize+(content.length%blockSize>0?1:0))*siglen;
		ByteBuffer buffer=ByteBuffer.allocate(bufLen);
		
		
		for(int i=0;i<content.length;i+=blocksize){
			
			if((content.length-i)>blocksize){
				byte[]  bytes=cipherCrt.doFinal(content,i,blocksize);
				
				buffer.put(bytes);
			}
				
			else{
				byte[]  bytes=cipherCrt.doFinal(content,i,content.length-i);
				
				buffer.put(bytes);
			}
				
		}
		//byte[] encryptedData = cipher.doFinal();
		//buffer.put(cipher.doFinal());
		buffer.flip();
		
		return buffer.array();
	}

	

	
	

}
