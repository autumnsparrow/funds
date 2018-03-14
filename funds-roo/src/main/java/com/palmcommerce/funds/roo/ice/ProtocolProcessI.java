/**
 * @company Palm Lottery Information&Technology Co.,Ltd.
 *
 * @author  sparrow
 *
 * @date    Oct 27, 2013
 *
 */
package com.palmcommerce.funds.roo.ice;

import Ice.Current;
import Ice.StringHolder;

import com.palmcommerce.funds.protocol.ProtocolDriverManager;
import com.palmcommerce.funds.protocol.covertor.exception.ProtocolConvertorException;
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
import com.palmcommerce.funds.roo.protocol.IProtocolProcessHandler;
import com.palmcommerce.funds.service.ProtocolProcessException;
import com.palmcommerce.funds.service.ProtocolStorageException;
import com.palmcommerce.funds.service._ProtocolProcessorDisp;

/**
 * @author sparrow
 * 
 */
public class ProtocolProcessI extends _ProtocolProcessorDisp {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	IProtocolProcessHandler protocolProcessHandler;

	/**
	 * @return the protocolProcessHandler
	 */
	public IProtocolProcessHandler getProtocolProcessHandler() {
		return protocolProcessHandler;
	}

	/**
	 * @param protocolProcessHandler
	 *            the protocolProcessHandler to set
	 */
	public void setProtocolProcessHandler(
			IProtocolProcessHandler protocolProcessHandler) {
		this.protocolProcessHandler = protocolProcessHandler;
	}

	/**
	 * 
	 */
	public ProtocolProcessI() {
		// TODO Auto-generated constructor stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.palmcommerce.funds.service._ProtocolProcessorOperations#doProcess
	 * (java.lang.String, java.lang.String, java.lang.String, java.lang.String,
	 * Ice.StringHolder, Ice.Current)
	 */
	@Override
	public boolean doProcess(String transcode, String fromcode, String tocode,
			String request, StringHolder response, Current __current)
			throws ProtocolProcessException {
		// TODO Auto-generated method stub
		IProtocol protocol = ProtocolDriverManager.instance(transcode,
				fromcode, tocode, request, true);

		boolean ret = true;
		try {
			protocol.unmashall(true);
			handle(protocol);
			protocol.mashall(false);
			response.value = protocol.getResponsePacket();

		} catch (ProtocolConvertorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			protocol.mashallException(e.getState(), e.getMessage());
			response.value = protocol.getResponsePacket();
			ret = false;
		}

		return ret;
	}

	private void handle(IProtocol protocol) throws ProtocolProcessException {
		// header.getHeader().transcode;
		switch (protocol.getHeader().transcode) {

		case 240001: {
			// transactionMetaService.p240001((P240001)header);
			protocolProcessHandler.p240001((P240001) protocol);

		}
			break;
		case 240002: {
			protocolProcessHandler.p240002((P240002) protocol);

		}
			break;
		case 240003: {
			protocolProcessHandler.p240003((P240003) protocol);

		}
			break;
		case 240004: {
			protocolProcessHandler.p240004((P240004) protocol);

		}
			break;
		case 240005: {
			protocolProcessHandler.p240005((P240005) protocol);

		}
			break;
		case 240006: {
			protocolProcessHandler.p240006((P240006) protocol);

		}
			break;
		case 250001: {
			protocolProcessHandler.t250001((T250001) protocol);

		}
			break;

		case 250002: {
			protocolProcessHandler.t250002((T250002) protocol);

		}
			break;
		case 250004: {
			protocolProcessHandler.t250004((T250004) protocol);

		}
			break;
		case 250005: {
			protocolProcessHandler.t250005((T250005) protocol);

		}
			break;
		case 250006: {
			protocolProcessHandler.t250006((T250006) protocol);

		}
			break;
		case 250007: {
			protocolProcessHandler.t250007((T250007) protocol);

		}
			break;
		case 250008: {
			protocolProcessHandler.t250008((T250008) protocol);

		}
			break;
		case 250009: {
			protocolProcessHandler.t250009((T250009) protocol);

		}
			break;
		case 250010: {
			protocolProcessHandler.t250010((T250010) protocol);

		}
			break;
		case 250011: {
			protocolProcessHandler.t250011((T250011) protocol);

		}
			break;
		case 260004:{
			
			
		}
		
		break;

		default:
			break;
		}

	}

}
