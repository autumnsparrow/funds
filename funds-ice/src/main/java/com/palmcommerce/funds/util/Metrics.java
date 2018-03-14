/**
 * @company Palm Lottery Information&Technology Co.,Ltd.
 *
 * @author  sparrow
 *
 * @date    Nov 6, 2013
 *
 */
package com.palmcommerce.funds.util;


import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author sparrow
 *
 */
public class Metrics {
	private static final Log logger=LogFactory.getLog(Metrics.class);
	
	public static class MetricsEntry{
		String tag;
		boolean state;
		boolean debug;
		long t;
		long e;
		public MetricsEntry(String tag,boolean debug) {
			super();
			this.tag = tag;
			this.state=false;
			this.debug=debug;
			mark();
		}

		public void mark(){
			if(!state){
				t=System.currentTimeMillis();
				state=true;
			}else{
				e=System.currentTimeMillis();
				state=false;
				if(debug){
					logger.info(this.toString());
				}
				
			}
			
		}
		
		/* (non-Javadoc)
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			return "MetricsEntry [tag=" + tag + ", t=" + (e-t) + "]";
		}

	}
	
	
	/**
	 * 
	 */
	public Metrics() {
		// TODO Auto-generated constructor stub
	}
	
	
	boolean debug;
	int dumpSize;
	
	
	/**
	 * @return the debug
	 */
	public boolean isDebug() {
		return debug;
	}


	/**
	 * @param debug the debug to set
	 */
	public void setDebug(boolean debug) {
		this.debug = debug;
	}


	/**
	 * @return the dumpSize
	 */
	public int getDumpSize() {
		return dumpSize;
	}


	/**
	 * @param dumpSize the dumpSize to set
	 */
	public void setDumpSize(int dumpSize) {
		this.dumpSize = dumpSize;
	}


	private static ConcurrentHashMap<String, MetricsEntry> TimeMetrics=new ConcurrentHashMap<String,  MetricsEntry>();

	public  void mark(String threadTag){
		long id=Thread.currentThread().getId();
		String tag=id+"-"+threadTag;
		if(debug){
			if(TimeMetrics.containsKey(tag)){
				TimeMetrics.get(tag).mark();
			}else{
				TimeMetrics.put(tag, new MetricsEntry(tag, debug));
			}
		
		}
	}
	
	

}
