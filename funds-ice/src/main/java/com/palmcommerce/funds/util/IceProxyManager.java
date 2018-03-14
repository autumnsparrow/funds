/**
 * @company Palm Lottery Information&Technology Co.,Ltd.
 *
 * @author  sparrow
 *
 * @date    Sep 7, 2013-6:14:52 PM
 *
 */
package com.palmcommerce.funds.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.palmcommerce.funds.service.ProtocolProcessorPrx;
import com.palmcommerce.funds.service.ProtocolProcessorPrxHelper;
import com.palmcommerce.funds.service.ProtocolStorage;
import com.palmcommerce.funds.service.ProtocolStoragePrx;
import com.palmcommerce.funds.service.ProtocolStoragePrxHelper;

import Ice.InitializationData;
import Ice.ObjectPrx;
import Ice.Util;

/**
 * @author sparrow
 *
 */
public class IceProxyManager {
	
	
	private static final Log logger=LogFactory.getLog(IceProxyManager.class);
	Ice.Communicator communictor;
	
	String iceConfig;
	
	

	public String getIceConfig() {
		return iceConfig;
	}

	public void setIceConfig(String iceConfig) {
		this.iceConfig = iceConfig;
	}
	
	

	/**
	 * 
	 */
	public IceProxyManager() {
		// TODO Auto-generated constructor stub
	}
	
	public void init(){
		InitializationData config=new InitializationData();
		//config.properties.setProperty(key, value)
		String file=IceProxyManager.class.getResource(iceConfig).getFile();
		config.properties=Ice.Util.createProperties();
		config.properties.load(file);
		logger.info("load file:"+file);
		
		//logger.info("loading proxy:"+ProtocolStoragePrx.class.getSimpleName()+ "- "+config.properties.getProperty(ProtocolStoragePrx.class.getSimpleName()));
		loadProxy(ProtocolStoragePrx.class,config.properties);
		loadProxy(ProtocolProcessorPrx.class,config.properties);
		communictor=Ice.Util.initialize(config);
	}
	
	private void loadProxy(Class<?> clazz ,Ice.Properties pros){
		String name=clazz.getSimpleName();
		String value=pros.getProperty(name);
		if(value==null){
			value="<not setting>";
		}
		logger.info("loading proxy:"+name+ "- "+value);
		
	}
	
	
	//private static final String ProtocolStoragePrx="ProtocolStorage.Proxy";
	
	
	
	public synchronized ProtocolStoragePrx getProxy(){
		
		ObjectPrx prx=communictor.propertyToProxy(ProtocolStoragePrx.class.getSimpleName());//communictor.stringToProxy(ProtocolStoragePrx);
		ProtocolStoragePrx protocolStoragePrx=null;
		if(prx!=null){
			try {
				protocolStoragePrx=ProtocolStoragePrxHelper.checkedCast(prx);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return protocolStoragePrx;
		
	}
	

	
	public synchronized  ProtocolProcessorPrx getProcessorPrx(){
		
		ObjectPrx prx=communictor.propertyToProxy(ProtocolProcessorPrx.class.getSimpleName());
		ProtocolProcessorPrx protocolStoragePrx=null;
		if(prx!=null){
			try {
				protocolStoragePrx=ProtocolProcessorPrxHelper.checkedCast(prx);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return protocolStoragePrx;
		
	}
	
	public void cleanup(){
		if(communictor!=null)
			communictor.destroy();
	}
	
	

}
