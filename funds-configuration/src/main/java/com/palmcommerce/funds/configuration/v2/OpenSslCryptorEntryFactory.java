/**
 * @company Palm Lottery Information&Technology Co.,Ltd.
 *
 * @author  sparrow
 *
 * @date    Nov 7, 2013
 *
 */
package com.palmcommerce.funds.configuration.v2;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;



/**
 * @author sparrow
 *
 */
public class OpenSslCryptorEntryFactory {
	private static final Log logger=LogFactory.getLog(OpenSslCryptorEntryFactory.class);
 	private static  final ConcurrentHashMap<String, ConcurrentLinkedQueue<OpenSslCryptorEntry>> caches=new ConcurrentHashMap<String, ConcurrentLinkedQueue<OpenSslCryptorEntry>>();
	
 	
 	public static final OpenSslCryptorEntryFactory factory=new OpenSslCryptorEntryFactory();
 	
	int min=5;
	int max=20;
	
	
	/**
	 * @return the min
	 */
	public int getMin() {
		return min;
	}


	/**
	 * @param min the min to set
	 */
	public void setMin(int min) {
		this.min = min;
	}


	/**
	 * @return the max
	 */
	public int getMax() {
		return max;
	}


	/**
	 * @param max the max to set
	 */
	public void setMax(int max) {
		this.max = max;
	}


	/**
	 * 
	 */
	public OpenSslCryptorEntryFactory() {
		// TODO Auto-generated constructor stub
	}
	
	
	public void putOpenSslCryptorEntry(String key,PublicKey remoteCrt,PrivateKey localKey){
		
		ConcurrentLinkedQueue<OpenSslCryptorEntry> queue=null;
		if(caches.containsKey(key)){
			queue=new ConcurrentLinkedQueue<OpenSslCryptorEntry>();
			queue.clear();
		}
		if(!caches.containsKey(key)){
			queue=new ConcurrentLinkedQueue<OpenSslCryptorEntry>();
		}
		for(int i=0;i<max;i++){
			queue.add(new OpenSslCryptorEntry(key,localKey, remoteCrt));
			
		}
		caches.put(key, queue);
	}
	
	
	public OpenSslCryptorEntry getOpenSslCryptorEntry(String key){
		
		OpenSslCryptorEntry entry=null;
		if(caches.containsKey(key)){
			ConcurrentLinkedQueue<OpenSslCryptorEntry> queue=caches.get(key);
			if(queue.size()<min){
				logger.warn("code :"+key+" has less OpenSslEntry "+min);
				entry=queue.peek();
				for(int i=0;i<max;i++){
					queue.add(new OpenSslCryptorEntry(key,entry.getLocalKey(), entry.getRemoteCrt()));
					
				}
				
			}else if(queue.size()>max){
				logger.warn("code :"+key+" has large OpenSSlEntry "+max);
				for(int i=0;i<queue.size()-max;i++){
					queue.poll();
				}
			}
			entry=queue.poll();
			
		}
		
		return entry;
		
	}
	
	public void returnOpenSslCryptorEntry(OpenSslCryptorEntry entry){
		if(caches.contains(entry.code)){
			ConcurrentLinkedQueue<OpenSslCryptorEntry> queue=caches.get(entry.code);
			queue.add(entry);
		}
	}
	
	
	

}
