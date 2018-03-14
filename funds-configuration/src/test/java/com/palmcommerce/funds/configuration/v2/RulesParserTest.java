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

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.palmcommerce.funds.ca.client.CaRestClient;
import com.palmcommerce.funds.ca.client.Cacrts;
import com.palmcommerce.funds.ca.client.Cakeys;


import junit.framework.TestCase;

/**
 * @author sparrow
 *
 */
public class RulesParserTest extends TestCase {

	/**
	 * @param name
	 */
	public RulesParserTest(String name) {
		super(name);
	}

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		super.setUp();
	}

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#tearDown()
	 */
	protected void tearDown() throws Exception {
		super.tearDown();
	}

	/**
	 * Test method for {@link com.palmcommerce.funds.configuration.v2.RulesParser#getServerRules(java.io.File)}.
	 */
	public void testGetServerRules() {
		
		ApplicationContext ctx=new ClassPathXmlApplicationContext("classpath:META-INF/spring/applicationContext-funds-ca-client.xml");
		CaRestClient caRestClient=(CaRestClient) ctx.getBean("caRestClient");
		//fail("Not yet implemented");
		ConfigurationManager manager =new ConfigurationManager();
		manager.setConfig(RulesParser.class.getResource("/listeners.xml").getFile());
		manager.setCaRestClient(caRestClient);
		manager.updateServerRules();
		
		
//		boolean loaded=true;
//		RulesParser parser=new RulesParser();
//		File f=new File(RulesParser.class.getResource("/listeners.xml").getFile());
//		ServerRules serverRules=parser.getServerRules(f);
//		
//		
//		// query the 
//		
//		Proxy proxy=serverRules.getProxy();
//		
//		
//		// loading the private key
//		
//		PEMStringLoader loader=new PEMStringLoader();
//		
//		Cakeys localKeys=client.getCakeyByNodecode(proxy.getNodeCode());
//		///localKeys.setVersion(verison)
//		
//		PrivateKey localKey=null;
//		try {
//			localKey = loader.loadPrivateKeyFromFile(localKeys.getFile(), localKeys.getPassphase(), RSAPrivateKey.class);
//		} catch (IOException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//		Cacrts rootCrt=client.getCacrtByNodecode(proxy.getRootCaNodecode());
//		
//		
//		
//		RouteRule routeRule=serverRules.getRouteRule();
//		RouteRuleResult routeRuleResult=null;
//		try {
//			URL routeRuleUrl=new URL(routeRule.getQuery());
//			routeRuleResult=parser.getRouteRuleResult(routeRuleUrl);
//		} catch (MalformedURLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		HashMap<String,OpenSslCryptor> mapOfCryptor=new HashMap<String,OpenSslCryptor>();
//		
//		if(routeRuleResult!=null){
//			
//			for(RouteRuleEntry entry:routeRuleResult.getRouteRuleEntries()){
//				// get the route's key store.
//				if(entry.getToCode()==null)
//					continue;
//				Cacrts crts=client.getCacrtByNodecode(entry.getToCode());
//				PublicKey remoteKey=null;
//				if(crts!=null){
//				
//				try {
//					remoteKey = loader.loadCertificateFromFile(crts.getFile(), rootCrt.getFile());
//				} catch (InvalidKeyException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				} catch (CertificateException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				} catch (NoSuchProviderException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				} catch (NoSuchAlgorithmException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				} catch (SignatureException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				}
//				if(remoteKey!=null&&localKey!=null){
//					OpenSslCryptor cryptor=new OpenSslCryptor();
//					cryptor.setLocalKey(localKey);
//					cryptor.setRemoteCrt(remoteKey);
//					mapOfCryptor.put(entry.getToCode(),cryptor );
//				}
//				
//			}
//			
//		}
		
		
		
		
		
		
	}

}
