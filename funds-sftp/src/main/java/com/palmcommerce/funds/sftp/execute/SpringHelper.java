package com.palmcommerce.funds.sftp.execute;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;







public class SpringHelper {
	
	private static Logger logger = Logger.getLogger(SpringHelper.class);
    
	private static ApplicationContext cx = null;
	
    public static <T> T getBean(String beanId){
       return (T)cx.getBean(beanId);
	}
    
    public synchronized static void init(){
    	if(cx == null){
      	  cx = new ClassPathXmlApplicationContext("classpath:META-INF/spring/applicationContext.xml");
      	  logger.info("Spring config success!,ApplicationContext set a object");
      	}
    }
    
    public synchronized static void init(String path){
    	
    	if(cx == null){
    	  cx = new ClassPathXmlApplicationContext(path);
    	  logger.info("Spring config success!,ApplicationContext set a object");
    	}
    }
    
  public synchronized static void init(String[] path){
    	
    	if(cx == null){
    	  cx = new ClassPathXmlApplicationContext(path);
    	  logger.info("Spring config success!,ApplicationContext set a object");
    	}
    }
    
    
    public static void main(String[] args){
    	
    	init();
    	
    //	meta.findAllTransactionMetas();
    }
    
  
}
