/**
 * 
 */
package com.palmcommerce.funds.sftp.interceptor;

import java.io.File;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.Message;
import org.springframework.integration.MessageChannel;
import org.springframework.integration.MessagingException;
import org.springframework.integration.core.MessageHandler;
import org.springframework.integration.support.MessageBuilder;


/**
 * @author sparrow
 *
 */
public class SftpFileReceivedHandler {
	private static final Log logger=LogFactory.getLog(SftpFileReceivedHandler.class);
	
	Map<String,MessageChannel> messageChannels;
	
	
	
	/**
	 * @return the messageChannels
	 */
	public Map<String, MessageChannel> getMessageChannels() {
		return messageChannels;
	}

	/**
	 * @param messageChannels the messageChannels to set
	 */
	public void setMessageChannels(Map<String, MessageChannel> messageChannels) {
		this.messageChannels = messageChannels;
	}

	/**
	 * 
	 */
	public SftpFileReceivedHandler() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see org.springframework.integration.core.MessageHandler#handleMessage(org.springframework.integration.Message)
	 */
	public void handleMessage(Message<?> message) throws MessagingException {
		// TODO Auto-generated method stub
		System.out.println("------------handleMessage:"+message.toString());
		
//		String csr=getCsrFilePrefix(message.getPayload().toString());
//		if(csr!=null){
//			//csrSignedManager.execute(csr);se
//		}
//		String txt=getTxtFilePrefix(message.getPayload().toString());
//		for(String key:messageChannels.keySet()){
//			if(txt.endsWith(key)){
//				put(txt, messageChannels.get(key));
//				break;
//			}
//		}
//		

	}
	
	public File handleFile(File f){
		//String txt=getTxtFilePrefix(f.getAbsolutePath());
		String path=f.getAbsolutePath();
		logger.info("------------handleMessage:"+path);
		for(String key:messageChannels.keySet()){
			if(path.contains(key)){
				put(f, messageChannels.get(key));
				break;
			}
		}
		return f;
	}
	
	/**
	 * 
	 * 
	 * /home/cqtest/crypto.csr/P0000001.csr
	 * @param file
	 * @return
	 */
	private String getTxtFilePrefix(String file){
		String csr=null;
		if(file!=null&&file.contains(File.separator)){
			String [] entries=file.split(File.separator);
			if(entries!=null){
				String  filename=entries[entries.length-1];
				if(filename.startsWith("ChargeData")){
					String[] names=filename.split("\\.");
					csr=names[2];
					
				}
			}
			
		}
		
		return csr;
	}
	
	public void put(File file,MessageChannel inputChannel){
		//File file=new File(fileName);
		if(file.exists()){
			final Message<File> sending = MessageBuilder.withPayload(file).build();
			boolean ret=inputChannel.send(sending);
			if(ret){
				logger.info(file.getAbsoluteFile()+" already sent!");
				
			}else{
				logger.info(file.getAbsoluteFile()+" do not sent");
			}
		}
	}

}
