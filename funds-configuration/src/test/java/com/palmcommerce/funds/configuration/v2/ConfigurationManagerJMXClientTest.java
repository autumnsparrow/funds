/**
 * @company Palm Lottery Information&Technology Co.,Ltd.
 *
 * @author  sparrow
 *
 * @date    Oct 16, 2013
 *
 */
package com.palmcommerce.funds.configuration.v2;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.palmcommerce.funds.ca.client.CaRestClient;

import junit.framework.TestCase;

/**
 * @author sparrow
 *
 */
public class ConfigurationManagerJMXClientTest extends TestCase {

	/**
	 * @param name
	 */
	public ConfigurationManagerJMXClientTest(String name) {
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
	 * Test method for {@link com.palmcommerce.funds.configuration.v2.ConfigurationManagerJMXClient#updateServerRules()}.
	 */
	public void testUpdateServerRules() {
		//fail("Not yet implemented");
		ApplicationContext ctx=new ClassPathXmlApplicationContext("classpath:META-INF/spring/applicationContext-funds-ca-client.xml");
		CaRestClient client=(CaRestClient) ctx.getBean("caRestClient");
		client.getCacrtByNodecode("B01");
//		ConfigurationManagerJMXClient client=new ConfigurationManagerJMXClient("127.0.0.1", 8000);
//		client.updateRouteRules();
	}

	/**
	 * Test method for {@link com.palmcommerce.funds.configuration.v2.ConfigurationManagerJMXClient#updateRouteRules()}.
	 */
	public void testUpdateRouteRules() {
		fail("Not yet implemented");
	}

}
