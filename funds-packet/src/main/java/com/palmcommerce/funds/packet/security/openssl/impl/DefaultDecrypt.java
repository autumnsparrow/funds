/**
 * 
 */
package com.palmcommerce.funds.packet.security.openssl.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


import com.palmcommerce.funds.config.security.SecurityConfiguration;
import com.palmcommerce.funds.config.security.SecurityConfiguration.SecurityKey;
import com.palmcommerce.funds.config.security.SecurityConfiguration.SecurityKeyStore;
import com.palmcommerce.funds.configuration.v2.ConfigurationManager;
import com.palmcommerce.funds.configuration.v2.OpenSslCryptor;
import com.palmcommerce.funds.configuration.v2.OpenSslCryptorEntry;
import com.palmcommerce.funds.packet.security.OpensslPacketSecurity;
import com.palmcommerce.funds.packet.security.openssl.IDecrypt;

/**
 * @author sparrow
 *
 */
public class DefaultDecrypt implements IDecrypt {
	
private static final Log logger=LogFactory.getLog(DefaultDecrypt.class);
	
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
	public DefaultDecrypt() {
		// TODO Auto-generated constructor stub
	}

	

	public boolean validateSignature(byte[] signature, int offset, int len,
			String fromCode) {
		
		//SecurityKeyStore keyStore= SecurityConfiguration.getInstance().getSecurityKeyStore();
//		SecurityKey key=securityConfiguration.getSecurityKeyStore().getKey(fromCode);
//		log("validateSignature:"+key.toString());
		//OpenSslCryptor key=this.configurationManager.getCryptor(fromCode);
		OpenSslCryptorEntry entry=this.configurationManager.getCryptorEntry(fromCode);
		//log("validateSignature:"+entry.toString());
		boolean ret=false;
		try {
			ret=entry.validateSignature(signature);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			this.configurationManager.returnCryptorEntry(entry);
		}
		// TODO Auto-generated method stub
		return ret;
	}


	public byte[] decrypt(byte[] packet, String toCode) {
		// TODO Auto-generated method stub
		//SecurityKeyStore keyStore= SecurityConfiguration.getInstance().getSecurityKeyStore();
		//OpenSslCryptor key=this.configurationManager.getCryptor(toCode);
		OpenSslCryptorEntry entry=this.configurationManager.getCryptorEntry(toCode);
		
		log("decrypt:"+entry.toString());
		
		byte[] decrypted=null;
		try {
			decrypted=entry.decrypt(packet);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			this.configurationManager.returnCryptorEntry(entry);
		}
		return decrypted;
	}

}
