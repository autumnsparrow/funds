/**
 * @company Palm Lottery Information&Technology Co.,Ltd.
 *
 * @author  sparrow
 *
 * @date    Sep 7, 2013-10:01:08 AM
 *
 */
package com.palmcommerce.funds.connection.mina.handler;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.palmcommerce.funds.connection.mina.ctx.ConnectorSessionContext;
import com.palmcommerce.funds.connection.mina.storage.IRemoteStorage;
import com.palmcommerce.funds.connection.mina.storage.StorageException;
import com.palmcommerce.funds.packet.Packet;
import com.palmcommerce.funds.service.ProtocolStorageException;
import com.palmcommerce.funds.service.ProtocolStoragePrx;
import com.palmcommerce.funds.util.IceProxyManager;

/**
 * @author sparrow
 *
 */
public class RemoteStorageTask implements Runnable ,IRemoteStorage{
	ConnectorSessionContext ctx;
	Packet request=null;
	Packet response=null;
	
	public RemoteStorageTask(ConnectorSessionContext ctx) {
		super();
		this.ctx = ctx;
		Object req=null;
		if(ctx.getAcceptorSession()!=null&&ctx.getAcceptorSession().getAttribute(ConnectorSessionContext.PACKET)!=null)
			req=ctx.getAcceptorSession().getAttribute(ConnectorSessionContext.PACKET);
		Object resp=null;
		if(ctx.getConnectorSession()!=null&&ctx.getConnectorSession().getAttribute(ConnectorSessionContext.PACKET)!=null)
			resp=ctx.getConnectorSession().getAttribute(ConnectorSessionContext.PACKET);
		if(req!=null){
			request=(Packet)req;
		}
		if(resp!=null){
			response=(Packet)resp;
		}
		
	}

	/**
	 * 
	 */
	public RemoteStorageTask() {
		// TODO Auto-generated constructor stub
	

	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	public void run() {
		// TODO Auto-generated method stub
		
		try {
			save(request,response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		

	}
	
	private static final Log logger=LogFactory.getLog(RemoteStorageTask.class);
	
	private String nullString(String s){
		if(s==null)s="";
		return s;
	}

	/**
	 * 
	 * 
	 */
	public boolean save(Packet request,Packet response) throws StorageException {
		// TODO Auto-generated method stub
		boolean ret=false;
		if(ctx.getMinaContext().isEnableDebug()){
			logger.info("RemoteStorageTask.save-[TransactionSerial=" +nullString(request.transactionSerial)+
					",TransCode=" +nullString(request.transCode)+
					",FromCode=" +nullString(request.fromCode)+
					",ToCode=" +nullString(request.toCode)+
					",Reuqest=" +nullString(request.content)+
					",Response=" +(response==null?"":nullString(response.content))+
					"]");
		}
		ProtocolStoragePrx prx=null;
		try {
			IceProxyManager iceProxyManager=this.ctx.getMinaContext().getIceProxyManager();
			if(iceProxyManager!=null)
				prx = iceProxyManager.getProxy();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			//e1.printStackTrace();
			throw new StorageException("9013", e1.getMessage());
		}
		
		try {
			
			
			if(prx!=null&&request!=null&response!=null){
				
				ret=prx.save(request.transactionSerial,request.transCode, request.fromCode, request.toCode, request.content, response.content);
			}
		} catch (ProtocolStorageException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			throw new StorageException(e.state, e.reason);
		}
		
		return ret;
	}

}
