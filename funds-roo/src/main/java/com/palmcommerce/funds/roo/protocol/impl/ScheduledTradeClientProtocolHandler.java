package com.palmcommerce.funds.roo.protocol.impl;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.SchedulingConfigurer;

import com.palmcommerce.funds.connection.mina.protocol.AbstractClientSideProcessor;
import com.palmcommerce.funds.protocol.impl.Header;
import com.palmcommerce.funds.protocol.impl.IProtocol;
import com.palmcommerce.funds.protocol.impl.p2t.P240001;
import com.palmcommerce.funds.protocol.impl.p2t.P240002;
import com.palmcommerce.funds.protocol.impl.p2t.P240003;
import com.palmcommerce.funds.protocol.impl.p2t.P240004;
import com.palmcommerce.funds.protocol.impl.p2t.P240005;
import com.palmcommerce.funds.protocol.impl.p2t.P240006;
import com.palmcommerce.funds.protocol.impl.t2p.T250001;
import com.palmcommerce.funds.protocol.impl.t2p.T250002;
import com.palmcommerce.funds.protocol.impl.t2p.T250004;
import com.palmcommerce.funds.protocol.impl.t2p.T250005;
import com.palmcommerce.funds.protocol.impl.t2p.T250006;
import com.palmcommerce.funds.protocol.impl.t2p.T250007;
import com.palmcommerce.funds.protocol.impl.t2p.T250008;
import com.palmcommerce.funds.protocol.impl.t2p.T250009;
import com.palmcommerce.funds.protocol.impl.t2p.T250010;
import com.palmcommerce.funds.protocol.impl.t2p.T250011;
import com.palmcommerce.funds.protocol.trade.IBankProtocolHandler;
import com.palmcommerce.funds.protocol.trade.ITradeProtocolHandler;
import com.palmcommerce.funds.roo.model.ScheduledTaskState;
import com.palmcommerce.funds.roo.service.ScheduledTaskStateService;
import com.palmcommerce.funds.roo.service.TransactionFileService;
import com.palmcommerce.funds.roo.service.TransactionMetaService;
import com.palmcommerce.funds.roo.tasklet.ITaskExecutor;
import com.palmcommerce.funds.roo.tasklet.impl.T250011TaskOfProxy;
import com.palmcommerce.funds.roo.tasklet.schedule.ScheduledTask.TaskState;
import com.palmcommerce.funds.roo.util.DateConvertor;
import com.palmcommerce.funds.service.ProtocolProcessException;
import com.palmcommerce.funds.service.ProtocolStorageException;
import com.palmcommerce.oss.jpa.service.OssReconciliationStaticsService;

/**
 * @company Palm Lottery Information&Technology Co.,Ltd.
 *
 * @author  sparrow
 *
 * @date    Oct 29, 2013
 *
 */

/**
 * @author sparrow
 *
 */
public class ScheduledTradeClientProtocolHandler extends
		AbstractClientSideProcessor implements IBankProtocolHandler {

	/**
	 * 
	 */
	public ScheduledTradeClientProtocolHandler() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	
	
	@Autowired
	ScheduledTaskStateService scheduledTaskStateService;
	



	/* (non-Javadoc)
	 * @see com.palmcommerce.funds.connection.mina.protocol.AbstractClientSideProcessor#handle(com.palmcommerce.funds.protocol.impl.IProtocol)
	 */
	@Override
	public void handle(IProtocol protocol) throws ProtocolProcessException {
		// TODO Auto-generated method stub
		try {
			Header header = protocol.getHeader();

			// header.getHeader().transcode;
			switch (header.transcode) {

			case 250001: {
				T250001 p = (T250001) protocol;

				t250001(p);

			}

				break;

			case 250002: {
				T250002 p = (T250002) protocol;

				t250002(p);

			}
				// paymentService.p240001((P240001)header);
				break;

			case 250004: {

				T250004 p = (T250004) protocol;

				t250004(p);

			}
				// paymentService.p240001((P240001)header);
				break;

			case 250005: {

				T250005 p = (T250005) protocol;

				t250005(p);

			}
				// paymentService.p240001((P240001)header);
				break;

			case 250006: {

				T250006 p = (T250006) protocol;

				t250006(p);

			}
				// paymentService.p240001((P240001)header);
				break;

			case 250007: {

				T250007 p = (T250007) protocol;

				t250007(p);

			}
				// paymentService.p240001((P240001)header);
				break;

			case 250008: {

				T250008 p = (T250008) protocol;

				t250008(p);

			}
				// paymentService.p240001((P240001)header);
				break;

			case 250009: {

				T250009 p = (T250009) protocol;

				t250009(p);

			}
				// paymentService.p240001((P240001)header);
				break;

			case 250010: {

				T250010 p = (T250010) protocol;

				t250010(p);

			}
				// paymentService.p240001((P240001)header);
				break;

			case 250011: {

				T250011 p = (T250011) protocol;

				t250011(p);

			}
				// paymentService.p240001((P240001)header);
				break;

			default:
				break;
			}
		} catch (ProtocolStorageException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ProtocolProcessException(e.state, e.reason);
		}
	}



	/* (non-Javadoc)
	 * @see com.palmcommerce.funds.protocol.trade.IBankProtocolHandler#t250001(com.palmcommerce.funds.protocol.impl.t2p.T250001)
	 */
	@Override
	public boolean t250001(T250001 t) throws ProtocolStorageException {
		// TODO Auto-generated method stub
		return false;
	}



	/* (non-Javadoc)
	 * @see com.palmcommerce.funds.protocol.trade.IBankProtocolHandler#t250002(com.palmcommerce.funds.protocol.impl.t2p.T250002)
	 */
	@Override
	public boolean t250002(T250002 t) throws ProtocolStorageException {
		// TODO Auto-generated method stub
		return false;
	}



	/* (non-Javadoc)
	 * @see com.palmcommerce.funds.protocol.trade.IBankProtocolHandler#t250004(com.palmcommerce.funds.protocol.impl.t2p.T250004)
	 */
	@Override
	public boolean t250004(T250004 t) throws ProtocolStorageException {
		// TODO Auto-generated method stub
		return false;
	}



	/* (non-Javadoc)
	 * @see com.palmcommerce.funds.protocol.trade.IBankProtocolHandler#t250005(com.palmcommerce.funds.protocol.impl.t2p.T250005)
	 */
	@Override
	public boolean t250005(T250005 t) throws ProtocolStorageException {
		// TODO Auto-generated method stub
		return false;
	}



	/* (non-Javadoc)
	 * @see com.palmcommerce.funds.protocol.trade.IBankProtocolHandler#t250006(com.palmcommerce.funds.protocol.impl.t2p.T250006)
	 */
	@Override
	public boolean t250006(T250006 t) throws ProtocolStorageException {
		// TODO Auto-generated method stub
		return false;
	}



	/* (non-Javadoc)
	 * @see com.palmcommerce.funds.protocol.trade.IBankProtocolHandler#t250007(com.palmcommerce.funds.protocol.impl.t2p.T250007)
	 */
	@Override
	public boolean t250007(T250007 t) throws ProtocolStorageException {
		// TODO Auto-generated method stub
		return false;
	}



	/* (non-Javadoc)
	 * @see com.palmcommerce.funds.protocol.trade.IBankProtocolHandler#t250008(com.palmcommerce.funds.protocol.impl.t2p.T250008)
	 */
	@Override
	public boolean t250008(T250008 t) throws ProtocolStorageException {
		// TODO Auto-generated method stub
		return false;
	}



	/* (non-Javadoc)
	 * @see com.palmcommerce.funds.protocol.trade.IBankProtocolHandler#t250009(com.palmcommerce.funds.protocol.impl.t2p.T250009)
	 */
	@Override
	public boolean t250009(T250009 t) throws ProtocolStorageException {
		// TODO Auto-generated method stub
//		T250009TaskOfProxy t250009Task=new T250009TaskOfProxy(t);
//		
//		taskExecutor.execute(t250009Task);
//		
//		return true;
		
		return false;
	}



	/* (non-Javadoc)
	 * @see com.palmcommerce.funds.protocol.trade.IBankProtocolHandler#t250010(com.palmcommerce.funds.protocol.impl.t2p.T250010)
	 */
	@Override
	public boolean t250010(T250010 t) throws ProtocolStorageException {
		// TODO Auto-generated method stub
//		T250010TaskOfProxy t250010Task=new T250010TaskOfProxy(t);
//		
//		taskExecutor.execute(t250010Task);
//		
//		return true;
		
		return false;
	}

	private String isNull(String s){
		if(s==null)
			s="0";
		return s;
	}

	/* (non-Javadoc)
	 * @see com.palmcommerce.funds.protocol.trade.IBankProtocolHandler#t250011(com.palmcommerce.funds.protocol.impl.t2p.T250011)
	 */
	@Override
	public boolean t250011(T250011 t) throws ProtocolStorageException {
		// TODO Auto-generated method stub
		
		
		String state=t.response.getCode();
		String message=t.response.getReason();
		//
		boolean ret=true;
		
				Date previouseDate=DateConvertor.parseBankTime(t.request.getAccountTime());
				ScheduledTaskState taskState=null;
				try {
					taskState = scheduledTaskStateService.findScheduledTaskStatesByBankDateEqualsAndBankCodeEquals(previouseDate, t.getHeader().to);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					ret=false;
				}
				boolean shouldSaved=false;
				if(taskState==null){
					taskState=new ScheduledTaskState();
					shouldSaved=true;
				}
				taskState.setBankCode(t.getHeader().to);
				taskState.setBankDate(previouseDate);
				taskState.setProcessDatetime(new Date());
				taskState.setIsBank(1);
				if("0000".equals(state)){
				taskState.setProcessState(TaskState.PROCEED_SUCCESS.ordinal());
				taskState.setFileSize(Long.parseLong(isNull(t.response.getCheckFileSize())));
				}else{
					taskState.setProcessState(TaskState.PROCEED_FAILED.ordinal());
				}
				taskState.setFileName(message);
				if(shouldSaved){
					scheduledTaskStateService.saveScheduledTaskState(taskState)	;
				}else{
					scheduledTaskStateService.updateScheduledTaskState(taskState);
				}
				
	
		return ret;
	
	}

}
