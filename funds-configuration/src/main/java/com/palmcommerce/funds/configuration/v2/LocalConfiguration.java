/**
 * @company Palm Lottery Information&Technology Co.,Ltd.
 *
 * @author  sparrow
 *
 * @date    Oct 31, 2013
 *
 */
package com.palmcommerce.funds.configuration.v2;

import org.apache.commons.digester3.annotations.rules.BeanPropertySetter;
import org.apache.commons.digester3.annotations.rules.ObjectCreate;

/**
 * @author sparrow
 *
 */
@ObjectCreate(pattern="ServerRules/LocalConfiguration")
public class LocalConfiguration {
	
	@BeanPropertySetter(pattern="ServerRules/LocalConfiguration/LocalKeyPath")
	String localKeyPath;
	@BeanPropertySetter(pattern="ServerRules/LocalConfiguration/LocalKeypassphase")
	String localKeypassphase;
	@BeanPropertySetter(pattern="ServerRules/LocalConfiguration/RemoteRootCa")
	String remoteRootCa;
	@BeanPropertySetter(pattern="ServerRules/LocalConfiguration/RemoteCrtPath")
	String remoteCrt;

	/**
	 * 
	 */
	public LocalConfiguration() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the localKeyPath
	 */
	public String getLocalKeyPath() {
		return localKeyPath;
	}

	/**
	 * @param localKeyPath the localKeyPath to set
	 */
	public void setLocalKeyPath(String localKeyPath) {
		this.localKeyPath = localKeyPath;
	}

	/**
	 * @return the localKeypassphase
	 */
	public String getLocalKeypassphase() {
		return localKeypassphase;
	}

	/**
	 * @param localKeypassphase the localKeypassphase to set
	 */
	public void setLocalKeypassphase(String localKeypassphase) {
		this.localKeypassphase = localKeypassphase;
	}

	/**
	 * @return the remoteRootCa
	 */
	public String getRemoteRootCa() {
		return remoteRootCa;
	}

	/**
	 * @param remoteRootCa the remoteRootCa to set
	 */
	public void setRemoteRootCa(String remoteRootCa) {
		this.remoteRootCa = remoteRootCa;
	}

	/**
	 * @return the remoteCrt
	 */
	public String getRemoteCrt() {
		return remoteCrt;
	}

	/**
	 * @param remoteCrt the remoteCrt to set
	 */
	public void setRemoteCrt(String remoteCrt) {
		this.remoteCrt = remoteCrt;
	}

	

}
