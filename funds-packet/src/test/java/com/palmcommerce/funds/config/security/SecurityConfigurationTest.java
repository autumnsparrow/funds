package com.palmcommerce.funds.config.security;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.xml.sax.SAXException;

import com.palmcommerce.funds.config.security.SecurityConfiguration.SecurityKey;
import com.palmcommerce.funds.config.security.SecurityConfiguration.SecurityKeyStore;
import com.palmcommerce.funds.packet.util.HexDump;


import junit.framework.TestCase;

public class SecurityConfigurationTest extends TestCase {
	private static final Log log = LogFactory.getFactory().getLog(
			SecurityConfigurationTest.class);

	public SecurityConfigurationTest(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	private static class ProtocolHandler implements Runnable {

		public  void run() {
			// TODO Auto-generated method stub
			 String content = "240001|0000030049|0001|0100000001|李四|身份证号码|532923197802050038|0||20090420|";

			 String code = "TRS_001";
			SecurityKey k =null;// store.getKey(code);
			SecurityKey key=new SecurityKey(k);
			boolean debug = true;
			long t0 = System.currentTimeMillis();
			for (int i = 0; i < 1; i++) {
				try {
					byte[] body = content.getBytes("GB2312");
					byte[] signature = key.signature(body);
					if (debug)
						log.info("signature:" + HexDump.dumpHexString(signature));
					ByteBuffer buffer = ByteBuffer.allocate(body.length
							+ signature.length);
					buffer.put(body);
					buffer.put(signature);
					buffer.flip();
					byte[] decrypted = buffer.array();
					byte[] encrypted = key.encrypt(decrypted);
					if (debug) {
						log.info(HexDump.dumpHexString(decrypted));
						log.info(HexDump.dumpHexString(encrypted));

						log.info("decrypt processing.....");
					}
					decrypted = key.decrypt(encrypted);
					// valid the signature
					boolean validated = key.validateSignature(decrypted);
					if (validated) {
						content = new String(decrypted, 0, decrypted.length - 128,
								"GB2312");
						if (debug) {
							log.info(HexDump.dumpHexString(decrypted));

							log.info(content);
						}
					}
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			log.info("Duration:" + (System.currentTimeMillis() - t0));

		}

	}

	// SecurityConfiguration
	// securityConfig=SecurityConfiguration.getInstance();//new
	// SecurityConfiguration();

	


	public void testSecurityConfiguration() throws Exception {
		// fail("Not yet implemented");

		Executor executor = Executors.newFixedThreadPool(20);

//		for(int i=0;i<1;i++){
//			executor.execute(new ProtocolHandler());
//		}
//		Thread.sleep(1000000);
		assertNotNull(executor);
	}

}
