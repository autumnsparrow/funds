package com.palmcommerce.funds.trade.impl;

import java.util.LinkedList;
import java.util.List;

import Ice.StringHolder;



import com.palmcommerce.funds.connection.mina.protocol.AbstractClientSideProcessor;
import com.palmcommerce.funds.packet.process.IProtocolProcessor;
import com.palmcommerce.funds.protocol.covertor.exception.ProtocolConvertorException;
import com.palmcommerce.funds.protocol.impl.Header;
import com.palmcommerce.funds.protocol.impl.IProtocol;
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
import com.palmcommerce.funds.service.ProtocolProcessException;
import com.palmcommerce.funds.service.ProtocolStorageException;


public class DefaultTradeClientProtocolHandler extends AbstractClientSideProcessor implements IBankProtocolHandler {
	
	
	
	public DefaultTradeClientProtocolHandler(){
		
	}
	
	

	public boolean t250001(T250001 t) throws ProtocolStorageException {
		boolean ret=false;
		
		return ret;
	}

	public boolean t250002(T250002 t) throws ProtocolStorageException {
		boolean ret=false;
		
		return ret;
	}

	public boolean t250004(T250004 t) throws ProtocolStorageException {
		boolean ret=false;
		
		return ret;
	}

	public boolean t250005(T250005 t) throws ProtocolStorageException {
		boolean ret=false;
		
		return ret;
	}

	public boolean t250006(T250006 t) throws ProtocolStorageException {
		boolean ret=false;
		String response=t.getResponsePacket();
		String[] responses=response.split("\r\n");
		List<T250006.Response> collectionOfResponse=new LinkedList<T250006.Response>();
		for(String r:responses)
		{
			t.setResponsePacket(r);
			try {
				t.unmashall(false);
			} catch (ProtocolConvertorException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			collectionOfResponse.add(t.response);
			
		}	
		return ret;
	}

	public boolean t250007(T250007 t) throws ProtocolStorageException {
		boolean ret=false;
		
		return ret;
	}

	public boolean t250008(T250008 t) throws ProtocolStorageException {
		boolean ret=false;
		
		return ret;
	}

	public boolean t250009(T250009 t) throws ProtocolStorageException {
		boolean ret=false;
		
		return ret;
	}

	public boolean t250010(T250010 t) throws ProtocolStorageException {
		boolean ret=false;
		
		return ret;
	}

	public boolean t250011(T250011 t) throws ProtocolStorageException {
		boolean ret=false;
		
		return ret;
	}

	/* (non-Javadoc)
	 * @see com.palmcommerce.funds.connection.mina.protocol.AbstractClientSideProcessor#handle(com.palmcommerce.funds.protocol.impl.IProtocol)
	 */
	@Override
	public void handle(IProtocol protocol) throws ProtocolProcessException {
		// TODO Auto-generated method stub
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

}
