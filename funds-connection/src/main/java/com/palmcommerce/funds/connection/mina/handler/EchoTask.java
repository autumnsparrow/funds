/**
 * @company Palm Lottery Information&Technology Co.,Ltd.
 *
 * @author  sparrow
 *
 * @date    Nov 13, 2013-8:56:24 AM
 *
 */
package com.palmcommerce.funds.connection.mina.handler;

import org.apache.mina.core.session.IoSession;

import com.palmcommerce.funds.packet.Packet;

/**
 * @author sparrow
 *
 */
public class EchoTask implements Runnable {
	IoSession session;
	Packet packet;
	


	public EchoTask(IoSession session, Packet packet) {
		super();
		this.session = session;
		this.packet = packet;
	}

	/**
	 * 
	 */
	public EchoTask() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	public void run() {
		// TODO Auto-generated method stub
		// if can't get the to code 
		// so can't get the crts.
		
		//this.session.write(message);
	}

}
