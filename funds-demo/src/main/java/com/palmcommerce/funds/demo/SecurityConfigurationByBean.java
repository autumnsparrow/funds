/**
 * 
 */
package com.palmcommerce.funds.demo;

/**
 * 
 * 
 * 
 * 
 * @author sparrow
 *
 */
public class SecurityConfigurationByBean {
	
	
	
	private String privateKeyPass;
	private int blockSize;
	private String privateKeyFile;
	private int siglen;
	private int pollerInterval;
	private String remotePublicKeyUrl;
	
	private String signature;
	private String ca;
	
	


	/**
	 * @return the ca
	 */
	public String getCa() {
		return ca;
	}



	/**
	 * @param ca the ca to set
	 */
	public void setCa(String ca) {
		this.ca = ca;
	}



	/**
	 * @return the signature
	 */
	public String getSignature() {
		return signature;
	}



	/**
	 * @param signature the signature to set
	 */
	public void setSignature(String signature) {
		this.signature = signature;
	}



	/**
	 * @return the privateKeyPass
	 */
	public String getPrivateKeyPass() {
		return privateKeyPass;
	}



	/**
	 * @param privateKeyPass the privateKeyPass to set
	 */
	public void setPrivateKeyPass(String privateKeyPass) {
		this.privateKeyPass = privateKeyPass;
	}



	/**
	 * @return the blockSize
	 */
	public int getBlockSize() {
		return blockSize;
	}



	/**
	 * @param blockSize the blockSize to set
	 */
	public void setBlockSize(int blockSize) {
		this.blockSize = blockSize;
	}



	/**
	 * @return the privateKeyFile
	 */
	public String getPrivateKeyFile() {
		return privateKeyFile;
	}



	/**
	 * @param privateKeyFile the privateKeyFile to set
	 */
	public void setPrivateKeyFile(String privateKeyFile) {
		this.privateKeyFile = privateKeyFile;
	}



	/**
	 * @return the siglen
	 */
	public int getSiglen() {
		return siglen;
	}



	/**
	 * @param siglen the siglen to set
	 */
	public void setSiglen(int siglen) {
		this.siglen = siglen;
	}



	/**
	 * @return the pollerInterval
	 */
	public int getPollerInterval() {
		return pollerInterval;
	}



	/**
	 * @param pollerInterval the pollerInterval to set
	 */
	public void setPollerInterval(int pollerInterval) {
		this.pollerInterval = pollerInterval;
	}



	


	/**
	 * @return the remotePublicKeyUrl
	 */
	public String getRemotePublicKeyUrl() {
		return remotePublicKeyUrl;
	}



	/**
	 * @param remotePublicKeyUrl the remotePublicKeyUrl to set
	 */
	public void setRemotePublicKeyUrl(String remotePublicKeyUrl) {
		this.remotePublicKeyUrl = remotePublicKeyUrl;
	}



	/**
	 * 
	 */
	public SecurityConfigurationByBean() {
		// TODO Auto-generated constructor stub
	}



	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SecurityConfigurationByBean [privateKeyPass=" + privateKeyPass
				+ ", blockSize=" + blockSize + ", privateKeyFile="
				+ privateKeyFile + ", siglen=" + siglen + ", pollerInterval="
				+ pollerInterval + ", remotePublicKeyUrl=" + remotePublicKeyUrl
				+ ", signature=" + signature + "]";
	}
	

}
