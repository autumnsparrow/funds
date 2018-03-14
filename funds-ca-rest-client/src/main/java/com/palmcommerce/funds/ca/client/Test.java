/**
 * @company Palm Lottery Information&Technology Co.,Ltd.
 *
 * @author  sparrow
 *
 * @date    Oct 19, 2013
 *
 */
package com.palmcommerce.funds.ca.client;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


/**
 * @author sparrow
 *
 */
public class Test {
	private static final Log logger=LogFactory.getLog(Test.class);

	/**
	 * 
	 */
	public Test() {
		// TODO Auto-generated constructor stub
	}
	
	public static void main(String args[]){
		ApplicationContext ctx=new ClassPathXmlApplicationContext("classpath:META-INF/spring/applicationContext-funds-ca-client.xml");
		CaRestClient client=(CaRestClient) ctx.getBean("caRestClient");
//		Cacrts cacrts=new Cacrts();
//		cacrts.setFile("-----------");
//		cacrts.setModifytime(new Date());
//		cacrts.setNodecode("Bb002");
//		client.addCacrt( cacrts);
		Cacrts entity=client.getCacrtByNodecode("B01");
//		Cakeys keys=client.getCakeyByNodecode("P01");
		logger.info(entity.toString());
//		logger.info(keys.toString());
	}

}
