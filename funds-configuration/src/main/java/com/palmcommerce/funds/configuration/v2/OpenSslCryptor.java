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
public class OpenSslCryptor {
	
	private int blockSize;

	private int siglen;
	
	private PrivateKey localKey;
	// private Public
	private PublicKey remoteCrt;
	
	private Signature sig;
	private Cipher cipher;
	
	
	
	

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

	/**
	 * 
	 */
	public OpenSslCryptor() {
		// TODO Auto-generated constructor stub
		
		try {
			sig = Signature.getInstance("SHA256withRSA","BC");
			cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding","BC");
			blockSize=100;
			siglen=128;
			
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchProviderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public  byte[] signature(byte[] content) throws Exception {

		sig.initSign(localKey);

		sig.update(content);
		byte[] signed = sig.sign();
		return signed;

	}

	public  boolean validateSignature(byte[] content)
			throws Exception {
		sig.initVerify(remoteCrt);
		sig.update(content,0, content.length-siglen);
		boolean ret = sig.verify(content, content.length-siglen, siglen);
		return ret;
	}

	public  byte[] decrypt(byte[] content) throws Exception {
		cipher.init(Cipher.DECRYPT_MODE, localKey);
		ByteBuffer buffer=ByteBuffer.allocate(1024);
		int blocksize=siglen;//cipher.getBlockSize();
		
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
		
		return realBuffer.array();

	}

	public  byte[]  encrypt(byte[] content) throws Exception {

		cipher.init(Cipher.ENCRYPT_MODE, remoteCrt);
		int blocksize=blockSize;//cipher.getBlockSize();
		int bufLen=(content.length/blockSize+(content.length%blockSize>0?1:0))*siglen;
		ByteBuffer buffer=ByteBuffer.allocate(bufLen);
		
		
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
		
		return buffer.array();
	}

	

	
	

}
