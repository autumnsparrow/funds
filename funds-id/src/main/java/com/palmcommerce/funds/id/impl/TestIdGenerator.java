/**
 * @company Palm Lottery Information&Technology Co.,Ltd.
 *
 * @author  sparrow
 *
 * @date    Oct 26, 2013
 *
 */
package com.palmcommerce.funds.id.impl;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;

import com.palmcommerce.funds.configuration.v2.FundsScheduledExecutorService;


/**
 * @author sparrow
 *
 */
public class TestIdGenerator {

	/**
	 * 
	 */
	public TestIdGenerator() {
		// TODO Auto-generated constructor stub
	}

	
	private static class IdFetch implements Runnable{
		GlobalIdGenerator idGen;
		
		List<String> ids=new LinkedList<String>();
		public IdFetch(GlobalIdGenerator idGen) {
			super();
			this.idGen = idGen;
		}
		
		public int getIds(){
			return ids.size();
		}


		/* (non-Javadoc)
		 * @see java.lang.Runnable#run()
		 */
		public void run() {
			ids.clear();
			// TODO Auto-generated method stub
			long t=System.currentTimeMillis();
			
			for(int i=0;i<1000;i++){
				ids.add(idGen.nextSerialId());
			}
			
			
			System.out.println("fetch 10000 uses:"+(System.currentTimeMillis()-t)+" total:"+ids.size());
			try {
				FileUtils.writeLines(new File(Thread.currentThread().getId()+"_"+String.valueOf(System.currentTimeMillis())+".seres"), ids);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//ScheduledExecutorService shedule=Executors.newScheduledThreadPool(1);
		//ScheduledExecutorService shedule2=Executors.newScheduledThreadPool(1);
		FundsScheduledExecutorService fundsScheduledExecutorService=new FundsScheduledExecutorService();
		GlobalIdGenerator generator=new GlobalIdGenerator(1,fundsScheduledExecutorService);
		
		final List<IdFetch> fetches=new LinkedList<IdFetch>();
		for(int i=0;i<100;i++){
			fetches.add(new IdFetch(generator));
		}
		generator.start();
		
		final ExecutorService  executorService=Executors.newFixedThreadPool(10);
		
		fundsScheduledExecutorService.getScheduledExecutorService().scheduleAtFixedRate(new Runnable() {
			
			public void run() {
				// TODO Auto-generated method stub
				for(IdFetch fetch:fetches){
					executorService.execute(fetch);
				}
				
			}
		},10, 120, TimeUnit.SECONDS);
		
		
		
		
		try {
			Thread.sleep(2000000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
