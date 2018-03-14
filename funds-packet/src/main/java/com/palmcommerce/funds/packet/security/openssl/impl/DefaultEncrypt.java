/**
 * 
 */
package com.palmcommerce.funds.packet.security.openssl.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.palmcommerce.funds.config.security.SecurityConfiguration;
import com.palmcommerce.funds.config.security.SecurityConfiguration.SecurityKey;
import com.palmcommerce.funds.configuration.v2.ConfigurationManager;
import com.palmcommerce.funds.configuration.v2.OpenSslCryptor;
import com.palmcommerce.funds.configuration.v2.OpenSslCryptorEntry;

import com.palmcommerce.funds.packet.security.openssl.IEncrypt;

/**
 * @author sparrow
 *
 */
public class DefaultEncrypt implements IEncrypt {
	
private static final Log logger=LogFactory.getLog(DefaultEncrypt.class);
	
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
	
ConfigurationManager configurationManager;
	
	
	
	/**
	 * @return the configurationManager
	 */
	public ConfigurationManager getConfigurationManager() {
		return configurationManager;
	}


	/**
	 * @param configurationManager the configurationManager to set
	 */
	public void setConfigurationManager(ConfigurationManager configurationManager) {
		this.configurationManager = configurationManager;
	}

	/**
	 * 
	 */
	public DefaultEncrypt() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see com.palmcommerce.funds.packet.security.openssl.IEncrypt#encrypt(byte[], java.lang.String)
	 */
	public byte[] encrypt(byte[] content, String toCode) {
		// TODO Auto-generated method stub
		//SecurityKeyStore keyStore= SecurityConfiguration.getInstance().getSecurityKeyStore();
		//OpenSslCryptor key=this.configurationManager.getCryptor(toCode);
		OpenSslCryptorEntry key=this.configurationManager.getCryptorEntry(toCode);
		
		log("encrypt:"+key.toString());
		byte[]  encrypted=null;
		try {
			encrypted=key.encrypt(content);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			this.configurationManager.returnCryptorEntry(key);
		}
		
		return encrypted;
	}

	/* (non-Javadoc)
	 * @see com.palmcommerce.funds.packet.security.openssl.IEncrypt#signature(byte[], java.lang.String)
	 */
	public byte[] signature(byte[] content, String fromCode) {
		// TODO Auto-generated method stub
		//SecurityKeyStore keyStore= SecurityConfiguration.getInstance().getSecurityKeyStore();
		//OpenSslCryptor key=this.configurationManager.getCryptor(fromCode);
		OpenSslCryptorEntry key=this.configurationManager.getCryptorEntry(fromCode);
		
		log("signature"+key.toString());
		byte[] sigature=null;
		try {
			sigature=key.signature(content);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			this.configurationManager.returnCryptorEntry(key);
		}
		
		return sigature;
	}

}
