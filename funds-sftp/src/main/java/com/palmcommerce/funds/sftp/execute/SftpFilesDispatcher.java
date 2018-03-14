/**
 * 
 */
package com.palmcommerce.funds.sftp.execute;

import com.palmcommerce.funds.sftp.interceptor.SftpFileReceivedHandler;





/**
 * @author sparrow
 *
 */
public class SftpFilesDispatcher {

	

	/**
	 * 
	 */
	public static void main(String[] args) {
		SpringHelper.init(new String[]{
				//"classpath:META-INF/spring/applicationContext-funds-sftp-for-proxy-sending-file.xml",
				"classpath:META-INF/spring/applicationContext-funds-sftp-for-proxy-sending-*.xml"
		    			
		    	});
		//PollableChannel localFileChannel = SpringHelper.getBean("receiveChannel");
		SftpFileReceivedHandler  sftpFileReceivedHandler=SpringHelper.getBean("handler");
	
	}

}
