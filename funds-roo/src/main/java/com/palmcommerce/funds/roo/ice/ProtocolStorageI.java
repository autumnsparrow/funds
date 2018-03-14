/**
 * 
 */
package com.palmcommerce.funds.roo.ice;

import Ice.Current;

import com.palmcommerce.funds.service.ProtocolStorageException;
import com.palmcommerce.funds.service._ProtocolStorageDisp;
import com.palmcommerce.funds.protocol.ProtocolDriverManager;
import com.palmcommerce.funds.protocol.covertor.exception.ProtocolConvertorException;
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
import com.palmcommerce.funds.roo.protocol.IProtocolStorageHandler;
import com.palmcommerce.funds.roo.tasklet.ITaskExecutor;


/**
 * @author sparrow
 *
 */
public class ProtocolStorageI extends _ProtocolStorageDisp  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	IProtocolStorageHandler protocolStorageHandler;
	
	ITaskExecutor taskExecutor;
	

	public ITaskExecutor getTaskExecutor() {
		return taskExecutor;
	}



	public void setTaskExecutor(ITaskExecutor taskExecutor) {
		this.taskExecutor = taskExecutor;
	}



	public IProtocolStorageHandler getProtocolStorageHandler() {
		return protocolStorageHandler;
	}



	public void setProtocolStorageHandler(
			IProtocolStorageHandler protocolStorageHandler) {
		this.protocolStorageHandler = protocolStorageHandler;
	}



	/**
	 * 
	 */
	public ProtocolStorageI() {
		// TODO Auto-generated constructor stub
	}

	

	@Override
	public boolean save( String global,String transcode, String fromcode,
			String tocode, String request, String response, Current __current)
			throws ProtocolStorageException {
		// TODO Auto-generated method stub
		IProtocol protocol=ProtocolDriverManager.instance(transcode, fromcode, tocode, request, response);
		
		Header header=protocol.getHeader();
		protocol.setGlobalSerial(global);
		
		try {
			protocol.unmashall(true);
			protocol.unmashall(false);
		} catch (ProtocolConvertorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ProtocolStorageException(e.getState(), e.getMessage());
			
		}
		
		//header.getHeader().transcode;
		switch (header.transcode) {
		
		
		case 240001:{
			//transactionMetaService.p240001((P240001)header);
			protocolStorageHandler.p240001((P240001)protocol) ;
			
			
		}
		break;
		case 240002:{
			protocolStorageHandler.p240002((P240002)protocol) ;
			
			
		}
		break;
		case 240003:{
			protocolStorageHandler.p240003((P240003)protocol) ;
			
			
		}
		break;
		case 240004:{
			protocolStorageHandler.p240004((P240004)protocol) ;
			
		}
		break;
		case 240005:{
			protocolStorageHandler.p240005((P240005)protocol) ;
			
		}
		break;
		case 240006:{
			protocolStorageHandler.p240006((P240006)protocol) ;
			
		}
		break;
		case 250001:{
			protocolStorageHandler.t250001((T250001)protocol) ;
			
		}
		break;
		
		case 250002:{
			protocolStorageHandler.t250002((T250002)protocol) ;
			
			
		}
		break;
		case 250004:{
			protocolStorageHandler.t250004((T250004)protocol) ;
			
		}
		break;
		case 250005:{
			protocolStorageHandler.t250005((T250005)protocol) ;
			
		}
		break;
		case 250006:{
			protocolStorageHandler.t250006((T250006)protocol) ;
			
			
		}
		break;
		case 250007:{
			protocolStorageHandler.t250007((T250007)protocol) ;
			
			
		}
		break;
		case 250008:{
			protocolStorageHandler.t250008((T250008)protocol) ;
			
			
		}
		break;
		case 250009:{
			protocolStorageHandler.t250009((T250009)protocol) ;
			
			
		}
		break;
		case 250010:{
			protocolStorageHandler.t250010((T250010)protocol) ;
			
			
		}
		break;
		case 250011:{
			protocolStorageHandler.t250011((T250011)protocol) ;
			
			
		}
			break;

		default:
			break;
		}
		
		return true;
	}
	
	
	
	

}
