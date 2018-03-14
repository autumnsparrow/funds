/**
 * @company Palm Lottery Information&Technology Co.,Ltd.
 *
 * @author  sparrow
 *
 * @date    Sep 9, 2013-9:18:00 AM
 *
 */
package com.palmcommerce.funds.util;



import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;



import Ice.InitializationData;
import Ice.ObjectAdapter;

/**
 * @author sparrow
 *
 */
public class IceServerManager {
	
	private static final Log logger=LogFactory.getLog(IceServerManager.class);
	Ice.Communicator communictor;
	
	String iceConfig;
	
	

	public String getIceConfig() {
		return iceConfig;
	}

	public void setIceConfig(String iceConfig) {
		this.iceConfig = iceConfig;
	}
	
	
	
	String objectAdapter;
	
	

	/**
	 * @return the objectAdapter
	 */
	public String getObjectAdapter() {
		return objectAdapter;
	}

	/**
	 * @param objectAdapter the objectAdapter to set
	 */
	public void setObjectAdapter(String objectAdapter) {
		this.objectAdapter = objectAdapter;
	}
	
	List<Ice.Object> servants;
	

	
	/**
	 * @return the servants
	 */
	public List<Ice.Object> getServants() {
		return servants;
	}

	/**
	 * @param servants the servants to set
	 */
	public void setServants(List<Ice.Object> servants) {
		this.servants = servants;
	}

	/**
	 * 
	 */
	public IceServerManager() {
		// TODO Auto-generated constructor stub
	}
	
	public void init(){
		InitializationData config=new InitializationData();
		//config.properties.setProperty(key, value)
		String file=IceServerManager.class.getResource(iceConfig).getFile();
		logger.info("IceServerManager loading configuration file:"+file);
		config.properties=Ice.Util.createProperties();
		
		config.properties.load(file);
		
		//logger.info(config.properties.toString());
		communictor=Ice.Util.initialize(config);
	}
	
	public void cleanup(){
		if(communictor!=null)
			communictor.destroy();
	}
	
	

	
	public void start(){
		init();
		ObjectAdapter adapter=communictor.createObjectAdapter(this.objectAdapter);
		
		for(Ice.Object servant:servants){
			String identity=servant.getClass().getSimpleName();
			adapter.add(servant, communictor.stringToIdentity(identity));
			logger.info("IceServerManager add servant:"+identity+"  object.adpater:"+this.objectAdapter);
		}
		//adapter.add(getServant(), communictor.stringToIdentity(servant.getClass().getSimpleName()));
		
		adapter.activate();
		logger.info("IceServerManager Started!");
		communictor.waitForShutdown();
		
		cleanup();
		
		
	}
	
	
	

}
