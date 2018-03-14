/**
 * @company Palm Lottery Information&Technology Co.,Ltd.
 *
 * @author  sparrow
 *
 * @date    Oct 15, 2013
 *
 */
package com.palmcommerce.funds.configuration.v2;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SignatureException;
import java.security.cert.CertificateException;
import java.security.interfaces.RSAPrivateKey;
import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.j256.simplejmx.common.JmxOperation;
import com.j256.simplejmx.common.JmxResource;
import com.palmcommerce.funds.ca.client.CaRestClient;
import com.palmcommerce.funds.ca.client.Cacrts;
import com.palmcommerce.funds.ca.client.Cakeys;

/**
 * @author sparrow
 * 
 */
@JmxResource(domainName = "com.palmcommerce.funds.configuration.v2", beanName = "ConfigurationManager", description = "ConfigurationManager JMX bean")
public class ConfigurationManager implements IConfigurationManagerJMX {

	
	
	
	private static final Log logger = LogFactory
			.getLog(ConfigurationManager.class);
	
	/**
	 * 
	 *  Adding the object pool
	 * 
	 */
	
	
	
	
	String config;

	CaRestClient caRestClient;

	/**
	 * @return the caRestClient
	 */
	public CaRestClient getCaRestClient() {
		return caRestClient;
	}

	/**
	 * @param caRestClient
	 *            the caRestClient to set
	 */
	public void setCaRestClient(CaRestClient caRestClient) {
		this.caRestClient = caRestClient;
	}

	/**
	 * @return the config
	 */
	public String getConfig() {
		return config;
	}

	/**
	 * @param config
	 *            the config to set
	 */
	public void setConfig(String config) {
		this.config = config;
	}

	ServerRules serverRules;
	RouteRuleResult routeRuleResult;
	HashMap<String, OpenSslCryptor> mapOfCryptor = new HashMap<String, OpenSslCryptor>();

	/**
	 * 
	 */
	public ConfigurationManager() {
		// TODO Auto-generated constructor stub
		

	}
	
	
	public OpenSslCryptorEntry getCryptorEntry(String code){
		OpenSslCryptorEntry entry=null;
		if(isLocal()){
			 entry=OpenSslCryptorEntryFactory.factory.getOpenSslCryptorEntry(LOCAL_CRYPTOR_KEY);
		}else{
			 entry=OpenSslCryptorEntryFactory.factory.getOpenSslCryptorEntry(code);
		}
		
		return entry;
		
	}
	
	public void returnCryptorEntry(OpenSslCryptorEntry entry){
		OpenSslCryptorEntryFactory.factory.returnOpenSslCryptorEntry(entry);
	}

	public OpenSslCryptor getCryptor(String code) { 
		OpenSslCryptor cryptor=null;
		if(isLocal()){
			 cryptor= this.mapOfCryptor.get(LOCAL_CRYPTOR_KEY);
		}else{
			cryptor=this.mapOfCryptor.get(code);
		}
		return cryptor;
		
	}
	
	private String isNull(String s){
		if(s==null)
			s="";
		return s;
	}
	/**
	 * 
	 * 00000001 : quick lottery
	 * 00000002 : hot line
	 * 00000003 : 
	 * @param userid
	 * @return
	 */
	public String getTradeCodeByUserId(String userid){
		String nodeCode=null;
		for(RouteRuleEntry entry:routeRuleResult.getRouteRuleEntries()){
			if(entry.checkUserId(userid)){
				nodeCode=entry.getToCode();
				break;
			}
		}
		
		logger.info("[userid:"+userid+" <----> nodecode:"+isNull(nodeCode));
		
		return nodeCode;
		
	}
	
	
	

	public RouteRuleResult getRouteRuleResult() {
		return this.routeRuleResult;
	}

	public ServerRules getServerRules() {
		return this.serverRules;
	}
	
	RulesParser parser ;
	public void init(){
		local=false;
		parser= new RulesParser();
		
		File f = new File(ConfigurationManager.class.getResource(config).getFile());
		serverRules = parser.getServerRules(f);
		// should update Route Rules
		
		updateRouteRules();
		updateServerRules();
		// should update Crts
		
	}

	/**
	 * @return the local
	 */
	public boolean isLocal() {
		return local;
	}
	
	
	@JmxOperation(description = "updateRouteRules")
	public void updateRouteRules() {
		
		RouteRule routeRule = serverRules.getRouteRule();

		try {
			logger.info("---> request route rules from server:"+routeRule.getQuery());
			if(routeRule.getQuery().startsWith("http://")){
				URL routeRuleUrl = new URL(routeRule.getQuery());
				routeRuleResult = parser.getRouteRuleResult(routeRuleUrl);
			}else{
				routeRuleResult =parser.getRouteRuleResultByLocalFile(routeRule.getQuery());
			}
			
			if(serverRules.shouldUsingForwardRoute){
				logger.info("--->enable using forward route :"+serverRules.shouldUsingForwardRoute);
				routeRuleResult.updateForwardFlag();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			routeRuleResult=null;
			logger.error("---> request route rules from server:"+routeRule.getQuery()+" failed!");
		}

	}

	public static class UpdateServerRulesException extends Exception {

		int reason;

		public UpdateServerRulesException() {
			super();
			// TODO Auto-generated constructor stub
		}

		public UpdateServerRulesException(String arg0, Throwable arg1) {
			super(arg0, arg1);
			// TODO Auto-generated constructor stub
		}

		public UpdateServerRulesException(int reason, String arg0) {
			super(arg0);
			// TODO Auto-generated constructor stub
			this.reason = reason;
		}

		public UpdateServerRulesException(Throwable arg0) {
			super(arg0);
			// TODO Auto-generated constructor stub
		}

		/**
		 * @return the reason
		 */
		public int getReason() {
			return reason;
		}

		/**
		 * @param reason
		 *            the reason to set
		 */
		public void setReason(int reason) {
			this.reason = reason;
		}

	}

	@JmxOperation(description = "updateServerRules")
	public void updateServerRules() {
		
		PEMStringLoader loader = new PEMStringLoader();

		// don't update first
		// mapOfCryptor.clear();
		logger.info("---> request crts rules from server");
		
		// query the
		int errorcode = 0;
		HashMap<String, OpenSslCryptor> tempMapOfCryptor = new HashMap<String, OpenSslCryptor>();
		try {
			
			Proxy proxy = serverRules.getProxy();
			if (proxy == null)
				throw new UpdateServerRulesException(
						errorcode++,
						"LocalServer "
								+ this.config
								+ " configuration don't configure the Proxy element!");
			// loading the private key
			if (caRestClient == null)
				throw new UpdateServerRulesException(errorcode++,
						"Ca rest client don't configure !");
			if (proxy.getNodeCode() == null)
				throw new UpdateServerRulesException(
						errorcode++,
						"LocalServer "
								+ this.config
								+ " configuration don't configure the Proxy.NodeCode");

			if (proxy.getRootCaNodecode() == null)
				throw new UpdateServerRulesException(
						errorcode++,
						"LocalServer "
								+ this.config
								+ " configuration don't configure the Proxy.RootCaCode");

			Cakeys localKeys = caRestClient.getCakeyByNodecode(proxy
					.getNodeCode());
			// /localKeys.setVersion(verison)
			if (localKeys == null)
				throw new UpdateServerRulesException(errorcode++, "Ca server "
						+ caRestClient.getBaseUrl()
						+ " configuration don't configure  rsa key for "
						+ proxy.getNodeCode());

			PrivateKey localKey = null;
			try {
				localKey = loader.loadPrivateKeyFromFile(localKeys.getContent(),
						localKeys.getPassphase(), RSAPrivateKey.class);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				throw new UpdateServerRulesException(errorcode++, "Ca server "
						+ caRestClient.getBaseUrl() + " configuration  "
						+ proxy.getNodeCode() + " rsa key error!");
			}

			Cacrts rootCrt = caRestClient.getCacrtByNodecode(proxy
					.getRootCaNodecode());

			if (rootCrt == null)
				throw new UpdateServerRulesException(errorcode++, "Ca server "
						+ caRestClient.getBaseUrl() + " configuration "
						+ proxy.getRootCaNodecode() + " root ca crt error!");

			RouteRule routeRule = serverRules.getRouteRule();

			if (routeRule == null)
				throw new UpdateServerRulesException(errorcode++,
						"LocalServer " + this.config
								+ " configuration don't configure RouteRule");

			if (routeRule.getQuery() == null)
				throw new UpdateServerRulesException(
						errorcode++,
						"LocalServer "
								+ this.config
								+ " configuration don't configure RouteRule.Query");

			RouteRuleResult routeRuleResult = null;
			try {
				URL routeRuleUrl = new URL(routeRule.getQuery());
				routeRuleResult = parser.getRouteRuleResult(routeRuleUrl);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new UpdateServerRulesException(errorcode++,
						"LocalServer " + this.config
								+ " fetching RouteRule.Query="
								+ routeRule.getQuery() + " failed");

			}
			if (routeRuleResult == null) {
				throw new UpdateServerRulesException(errorcode++,
						"LocalServer " + this.config
								+ " fetching RouteRule.Query="
								+ routeRule.getQuery()
								+ " RouteRule table empty!");
			}

			

			for (RouteRuleEntry entry : routeRuleResult.getRouteRuleEntries()) {
				// get the route's key store.
				if (entry.getToCode() == null)
					continue;
				Cacrts crts = caRestClient
						.getCacrtByNodecode(entry.getToCode());
				if (crts == null)
					continue;
				PublicKey remoteKey = null;

				try {
					remoteKey = loader.loadCertificateFromFile(crts.getContent(),
							rootCrt.getContent());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (remoteKey == null)
					continue;
				
				OpenSslCryptorEntryFactory.factory.putOpenSslCryptorEntry(entry.getToCode(), remoteKey, localKey);

//				OpenSslCryptor cryptor = new OpenSslCryptor();
//				cryptor.setLocalKey(localKey);
//				cryptor.setRemoteCrt(remoteKey);
//				tempMapOfCryptor.put(entry.getToCode(), cryptor);

			}
			
		} catch (UpdateServerRulesException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
			logger.error("---> request crts rules from server:"+ex.getMessage()+" errorcode:"+ex.reason);
			 
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			errorcode=-1;
			logger.error("---> request crts rules from server:"+e.getMessage());
		}
		
		if(errorcode==0){
			// try to conver the current security table to the current
//			mapOfCryptor.clear();
//			mapOfCryptor=null;
//			mapOfCryptor=tempMapOfCryptor;
			logger.info("*********LocalServer updated the security files!****");
			for(String cryptor:mapOfCryptor.keySet()){
				logger.info(cryptor);
			}
		}else{
			logger.error("---> request crts rules from server: can't get crts.please check the CA server");
		}
		
		
		
		


	}
	
	
	/*
	 * 
	 * 
	 *  loading the local configuration.
	 *  
	 *  Trade Client can only get the route and keys local.
	 * 
	 * 
	 * 
	 * 
	 */
	private static final String LOCAL_CRYPTOR_KEY="local_cryptor_key";
	
	boolean local;
	
	public void initLocal(){
		logger.info("*********InitLocal Begin****");
		local=true;
		parser= new RulesParser();
		File f = new File(ConfigurationManager.class.getResource(config).getFile());
		serverRules = parser.getServerRules(f);
		// route query using the xml configuration.
		updateRouteRules();
		LocalConfiguration localConfiguration=serverRules.getLocalConfiguration();
		
		PEMFileLoader pemFileLoader=new PEMFileLoader();
		try {
			PublicKey remoteCrt=pemFileLoader.loadCertificateFromFile(localConfiguration.getRemoteCrt(), localConfiguration.getRemoteRootCa());
			PrivateKey localKey=pemFileLoader.loadPrivateKeyFromFile(localConfiguration.getLocalKeyPath(), localConfiguration.getLocalKeypassphase(), RSAPrivateKey.class);
//			mapOfCryptor.clear();
//			
//			OpenSslCryptor cryptor = new OpenSslCryptor();
//			cryptor.setLocalKey(localKey);
//			cryptor.setRemoteCrt(remoteCrt);
//			mapOfCryptor.put(LOCAL_CRYPTOR_KEY, cryptor);
			
			OpenSslCryptorEntryFactory.factory.putOpenSslCryptorEntry(LOCAL_CRYPTOR_KEY, remoteCrt, localKey);
			 //entry=OpenSslCryptorEntryFactory.factory.getOpenSslCryptorEntry(LOCAL_CRYPTOR_KEY);
			
			logger.info("*********InitLocal DONE!!!!****");
			
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			logger.error("-----------> InvalidKeyException: "+e.getMessage());

		} catch (CertificateException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			logger.error("-----------> CertificateException : "+e.getMessage());
		} catch (NoSuchProviderException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			logger.error("-----------> NoSuchProviderException : "+e.getMessage());
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			logger.error("-----------> NoSuchAlgorithmException : "+e.getMessage());
		} catch (SignatureException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			logger.error("-----------> SignatureException : "+e.getMessage());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			logger.error("-----------> IOException : "+e.getMessage());
		}
		
		
	}

}
