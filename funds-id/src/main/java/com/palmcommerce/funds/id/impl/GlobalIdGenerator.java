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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.scheduling.TaskScheduler;

import com.palmcommerce.funds.configuration.v2.FundsScheduledExecutorService;

/**
 * 
 * 
 * Pxxdddddd-fsssss 3 10 1 6
 * 
 * s-99999 every minute generate
 * 
 * Algorithm of Global Id Generator:
 * 
 * xx
 * 
 * xx : express the front communicator server sequence :00~99 len=2 dddddddddd:
 * express the date time to minutes : yyMMddHHmm len=10 - :express that the
 * IdGenerator prefix and suffix seperator len=1 f : express the IdGenerate
 * f: failed how many times with in a minutes : 0~9 len=1
 * 
 * sssss: express the sequence of : 000000~999999 len:6
 * 
 * a minute the communicator can process 1000000 100w packet.
 * 
 * 
 * @author sparrow
 * 
 */
public class GlobalIdGenerator {
	private static Log logger = LogFactory.getLog(GlobalIdGenerator.class);
	private static final SimpleDateFormat format = new SimpleDateFormat(
			"yyMMddHHmm");

	// should be <100.
	int nodeCode;

	int delayTimeSeconds;
	int loopTimeSeconds;
	
	
	
	/**
	 * @return the delayTimeSeconds
	 */
	public int getDelayTimeSeconds() {
		return delayTimeSeconds;
	}

	/**
	 * @param delayTimeSeconds the delayTimeSeconds to set
	 */
	public void setDelayTimeSeconds(int delayTimeSeconds) {
		this.delayTimeSeconds = delayTimeSeconds;
	}

	/**
	 * @return the loopTimeSeconds
	 */
	public int getLoopTimeSeconds() {
		return loopTimeSeconds;
	}

	/**
	 * @param loopTimeSeconds the loopTimeSeconds to set
	 */
	public void setLoopTimeSeconds(int loopTimeSeconds) {
		this.loopTimeSeconds = loopTimeSeconds;
	}

	private static final String SEQ_FILE = "/global.ser";
	private static final int MIN_SEQ = 0;
	private static final int MAX_SEQ = 	99999;
	private static final ConcurrentLinkedQueue<String> seqQueue = new ConcurrentLinkedQueue<String>();

	// private static final List<String> newQueue=new LinkedList<String>();
	private static String getBase(int nodeCode) {
		String timestamp = format.format(new Date());
		return String.format("%02d%s", nodeCode, timestamp);
	}

	static volatile boolean isNew;

	private static final Object lock = new Object();

	public String nextSerialId() {

		return seqQueue.poll();
	}

	// write the base.

	private static class SequenceUpdater implements Runnable {

		int nodeCode;
		File file;
		String prefix;
		int retries;

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.TimerTask#run()
		 */
		public SequenceUpdater(int nodeCode) throws Exception {
			super();
			// TODO Auto-generated constructor stub
			file = new File(GlobalIdGenerator.class.getResource(SEQ_FILE)
					.getFile());
			if (!file.exists()) {
				file.createNewFile();
			}
			this.nodeCode = nodeCode;
			// prefix=getBase(nodeCode);
			retries=0;

			// update();

		}

		private void updateSer() throws Exception {
			readFromSer();
			String base = getBase(nodeCode);
			if(prefix==null)
				prefix=base;
			else{
				if(prefix.equalsIgnoreCase(base)){
					retries++;
				}else{
					prefix=base;
					retries=0;
				}
				
			}
			
			writeToSer();
		}

		private void writeToSer() throws Exception {
			if (retries > 9)
				throw new Exception("Retry ser file too many times." + retries);
			String ser = String.format("%s-%d", prefix, retries);
			FileUtils.writeStringToFile(file, ser);
			//FileUtils.writeAsString(file, ser);
			logger.info("ser  ["+ser+ "] to file:"+file.getAbsolutePath());
		}

		private void readFromSer() throws IOException {

			String ser = FileUtils.readFileToString(file);
			logger.info("ser   ["+ser+ "]  from file:"+file.getAbsolutePath());
			String[] entries = ser.split("-");
			if (entries.length == 2) {
				if (entries[0] != null)
					prefix = entries[0];
				if (entries[1] != null)
					retries = Integer.parseInt(entries[1]);
			}

		}

		private void update() {

		}

		public void run() {
			// TODO Auto-generated method stub

			long time = System.currentTimeMillis();
			//
			try {
				updateSer();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// should read the ser from file
			// current is new

			// seqQueue.clear();
			seqQueue.clear();
			for (int i = MIN_SEQ; i < MAX_SEQ; i++) {
				String seq = String.format("%s-%d%05d", prefix,retries, i);
				seqQueue.add(seq);
			}
			logger.info("generate:" + MAX_SEQ + " uses:"
					+ (System.currentTimeMillis() - time) + " ,prefix =  "
					+ prefix + ", retries =" + retries + " seqQueue.size "
					+ seqQueue.size());

		}

	}

	/**
	 * 
	 */
	ScheduledExecutorService scheduler;
	FundsScheduledExecutorService fundsScheduledExecutorService;
	public GlobalIdGenerator(int nodeCode,FundsScheduledExecutorService scheduler) {

		this.nodeCode = nodeCode;
		this.fundsScheduledExecutorService=scheduler;
		this.scheduler=this.fundsScheduledExecutorService.getScheduledExecutorService();
		// TODO Auto-generated constructor stub
		// idGeneratorService=Executors.newScheduledThreadPool(1);

	}

	public void start() {
		SequenceUpdater seq;
		try {
			//Timer timer = new Timer();
			seq = new SequenceUpdater(nodeCode);
			//timer.scheduleAtFixedRate(seq, 1000, 6000);

			 scheduler.scheduleAtFixedRate(seq, this.delayTimeSeconds, this.loopTimeSeconds, TimeUnit.SECONDS);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.info(e.getMessage());
		}
	}

}
