/**
 * @company Palm Lottery Information&Technology Co.,Ltd.
 *
 * @author  sparrow
 *
 * @date    Oct 28, 2013
 *
 */
package com.palmcommerce.funds.id.impl;

import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;

import Ice.StringHolder;

import com.palmcommerce.funds.configuration.v2.ConfigurationManager;
import com.palmcommerce.funds.configuration.v2.RouteRuleEntry;
import com.palmcommerce.funds.connection.mina.protocol.AbstractProtocolFilter;
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
import com.palmcommerce.funds.protocol.impl.t2p.T260004;
import com.palmcommerce.funds.protocol.trade.IBankProtocolHandler;
import com.palmcommerce.funds.protocol.trade.IStatus;
import com.palmcommerce.funds.protocol.trade.ITradeProtocolHandler;
import com.palmcommerce.funds.service.ProtocolProcessException;
import com.palmcommerce.funds.service.ProtocolProcessorPrx;
import com.palmcommerce.funds.service.ProtocolStorageException;

/**
 * @author sparrow
 * 
 */
public class IdGeneratorProtocolFilterHandler extends AbstractProtocolFilter
		implements ITradeProtocolHandler, IBankProtocolHandler, IStatus {

	@Autowired
	ConfigurationManager configurationManager;

	IIdGenerator generator;

	/**
	 * @return the generator
	 */
	public IIdGenerator getGenerator() {
		return generator;
	}

	/**
	 * @param generator
	 *            the generator to set
	 */
	public void setGenerator(IIdGenerator generator) {
		this.generator = generator;
	}

	/**
	 * 
	 */
	public IdGeneratorProtocolFilterHandler() {
		// TODO Auto-generated constructor stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.palmcommerce.funds.trade.IBankProtocolHandler#t250001(com.palmcommerce
	 * .funds.protocol.impl.t2p.T250001)
	 */
	public boolean t250001(T250001 t) throws ProtocolStorageException {
		// TODO Auto-generated method stub

		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.palmcommerce.funds.trade.IBankProtocolHandler#t250002(com.palmcommerce
	 * .funds.protocol.impl.t2p.T250002)
	 */
	public boolean t250002(T250002 t) throws ProtocolStorageException {
		// TODO Auto-generated method stub
		boolean ret = true;
		if (t.request != null) {
			String serial = t.request.getCenterNumber();
			t.request.setCenterNumber(generator
					.getLocalSerialForRemoteSerial(serial));
			t.setGlobalSerial(t.request.getCenterNumber());
		} else if (t.response != null) {
			String serial = t.response.getCenterNumber();
			t.setGlobalSerial(t.response.getCenterNumber());
			t.response.setCenterNumber(generator
					.getRemoteSerialByLocalSerial(serial));
		}
		// get the node code by userid
		String toCode = t.getHeader().to;
		RouteRuleEntry routeRuleEntry = this.configurationManager
				.getRouteRuleResult().getRouteRule(toCode);
		if (routeRuleEntry == null) {
			ret = false;
			t.mashallException(
					Validate_ProtocolMeta_Destination_Cannot_Reach.getState(),
					Validate_ProtocolMeta_Destination_Cannot_Reach.getMessage());
			// t.mashallException(Validate_ProtocolMeta_Destination_Cannot_Reach.,
			// Validate_ProtocolMeta_Destination_Cannot_Reach.);
		}

		return ret;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.palmcommerce.funds.trade.IBankProtocolHandler#t250004(com.palmcommerce
	 * .funds.protocol.impl.t2p.T250004)
	 */
	public boolean t250004(T250004 t) throws ProtocolStorageException {
		// TODO Auto-generated method stub
		boolean ret = true;
		if (t.request != null) {
			String serial = t.request.getCenterNumber();

			t.request.setCenterNumber(generator
					.getLocalSerialForRemoteSerial(serial));
			t.setGlobalSerial(t.request.getCenterNumber());
		} else if (t.response != null) {
			String serial = t.response.getCenterNumber();
			t.setGlobalSerial(t.response.getCenterNumber());
			t.response.setCenterNumber(generator
					.getRemoteSerialByLocalSerial(serial));
		}
		
		ProtocolProcessorPrx proxy = this.getMinaContext().getIceProxyManager().getProcessorPrx();
		String toCode = t.getHeader().to;
		//if(toCode==null||toCode.trim().equals("")){
			String cardNo = t.request.getAccount();
			//根据银行卡号获取银行名称
			StringHolder response = new StringHolder();
			T260004 t26004=(T260004) ProtocolDriverManager.instance("260004", "", "");
			try {
				
				t26004.request.setCardNumber(cardNo);
				
				proxy.doProcess(String.valueOf(t26004.getHeader().transcode), "","", t26004.getRequestPacket(), response);
			} catch (ProtocolProcessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				t.mashallException(
						Validate_ProtocolMeta_Destination_Cannot_Reach.getState(),
						Validate_ProtocolMeta_Destination_Cannot_Reach.getMessage());
				ret=false;
			}
		if(ret){
			t26004.setResponsePacket(response.value);
			try {
				t26004.unmashall(false);
			} catch (ProtocolConvertorException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				t.mashallException(
						Validate_ProtocolMeta_Destination_Cannot_Reach.getState(),
						Validate_ProtocolMeta_Destination_Cannot_Reach.getMessage());
				ret=false;
			}
			
			
		}	
			
		//}
		if(ret){
		t.getHeader().to=t26004.response.getBankNodeCode();
		RouteRuleEntry routeRuleEntry = this.configurationManager
				.getRouteRuleResult().getRouteRule(toCode);
		if (routeRuleEntry == null) {
			ret = false;
			t.mashallException(
					Validate_ProtocolMeta_Destination_Cannot_Reach.getState(),
					Validate_ProtocolMeta_Destination_Cannot_Reach.getMessage());
			// t.mashallException(Validate_ProtocolMeta_Destination_Cannot_Reach.,
			// Validate_ProtocolMeta_Destination_Cannot_Reach.);
		}
		}

		return ret;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.palmcommerce.funds.trade.IBankProtocolHandler#t250005(com.palmcommerce
	 * .funds.protocol.impl.t2p.T250005)
	 */
	public boolean t250005(T250005 t) throws ProtocolStorageException {
		// TODO Auto-generated method stub
		boolean ret = true;
		if (t.request != null) {
			String serial = t.request.getCenterNumber();
			t.request.setCenterNumber(generator
					.getLocalSerialForRemoteSerial(serial));
			t.setGlobalSerial(t.request.getCenterNumber());
		} else if (t.response != null) {
			String serial = t.response.getCenterNumber();
			t.setGlobalSerial(t.response.getCenterNumber());
			t.response.setCenterNumber(generator
					.getRemoteSerialByLocalSerial(serial));
		}

		String toCode = t.getHeader().to;
		RouteRuleEntry routeRuleEntry = this.configurationManager
				.getRouteRuleResult().getRouteRule(toCode);
		if (routeRuleEntry == null) {
			ret = false;
			t.mashallException(
					Validate_ProtocolMeta_Destination_Cannot_Reach.getState(),
					Validate_ProtocolMeta_Destination_Cannot_Reach.getMessage());
			// t.mashallException(Validate_ProtocolMeta_Destination_Cannot_Reach.,
			// Validate_ProtocolMeta_Destination_Cannot_Reach.);
		}
		return ret;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.palmcommerce.funds.trade.IBankProtocolHandler#t250006(com.palmcommerce
	 * .funds.protocol.impl.t2p.T250006)
	 */
	public boolean t250006(T250006 t) throws ProtocolStorageException {
		// TODO Auto-generated method stub
		boolean ret = true;
		if (t.request != null) {
			String serial = t.request.getCenterNumber();
			t.request.setCenterNumber(generator
					.getLocalSerialForRemoteSerial(serial));
			t.setGlobalSerial(t.request.getCenterNumber());
		} else if (t.response != null) {
			String serial = t.response.getCenterNumber();
			t.setGlobalSerial(t.response.getCenterNumber());
			t.response.setCenterNumber(generator
					.getRemoteSerialByLocalSerial(serial));
		}

		String toCode = t.getHeader().to;
		RouteRuleEntry routeRuleEntry = this.configurationManager
				.getRouteRuleResult().getRouteRule(toCode);
		if (routeRuleEntry == null) {
			ret = false;
			t.mashallException(
					Validate_ProtocolMeta_Destination_Cannot_Reach.getState(),
					Validate_ProtocolMeta_Destination_Cannot_Reach.getMessage());
			// t.mashallException(Validate_ProtocolMeta_Destination_Cannot_Reach.,
			// Validate_ProtocolMeta_Destination_Cannot_Reach.);
		}
		return ret;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.palmcommerce.funds.trade.IBankProtocolHandler#t250007(com.palmcommerce
	 * .funds.protocol.impl.t2p.T250007)
	 */
	public boolean t250007(T250007 t) throws ProtocolStorageException {
		// TODO Auto-generated method stub
		boolean ret = true;
		if (t.request != null) {
			String serial = t.request.getCenterNumber();
			t.request.setCenterNumber(generator
					.getLocalSerialForRemoteSerial(serial));
			t.setGlobalSerial(t.request.getCenterNumber());
		} else if (t.response != null) {
			String serial = t.response.getCenterNumber();
			t.setGlobalSerial(t.response.getCenterNumber());
			t.response.setCenterNumber(generator
					.getRemoteSerialByLocalSerial(serial));
		}
		String toCode = t.getHeader().to;
		RouteRuleEntry routeRuleEntry = this.configurationManager
				.getRouteRuleResult().getRouteRule(toCode);
		if (routeRuleEntry == null) {
			ret = false;
			t.mashallException(
					Validate_ProtocolMeta_Destination_Cannot_Reach.getState(),
					Validate_ProtocolMeta_Destination_Cannot_Reach.getMessage());
			// t.mashallException(Validate_ProtocolMeta_Destination_Cannot_Reach.,
			// Validate_ProtocolMeta_Destination_Cannot_Reach.);
		}
		return ret;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.palmcommerce.funds.trade.IBankProtocolHandler#t250008(com.palmcommerce
	 * .funds.protocol.impl.t2p.T250008)
	 */
	public boolean t250008(T250008 t) throws ProtocolStorageException {
		// TODO Auto-generated method stub
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.palmcommerce.funds.trade.IBankProtocolHandler#t250009(com.palmcommerce
	 * .funds.protocol.impl.t2p.T250009)
	 */
	public boolean t250009(T250009 t) throws ProtocolStorageException {
		// TODO Auto-generated method stub
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.palmcommerce.funds.trade.IBankProtocolHandler#t250010(com.palmcommerce
	 * .funds.protocol.impl.t2p.T250010)
	 */
	public boolean t250010(T250010 t) throws ProtocolStorageException {
		// TODO Auto-generated method stub
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.palmcommerce.funds.trade.IBankProtocolHandler#t250011(com.palmcommerce
	 * .funds.protocol.impl.t2p.T250011)
	 */
	public boolean t250011(T250011 t) throws ProtocolStorageException {
		// TODO Auto-generated method stub
		// if(t.request!=null){
		// String serial=t.request.getCenterNumber();
		// t.request.setCenterNumber(generator.getLocalSerialForRemoteSerial(serial));
		// t.setGlobalSerial(t.request.getCenterNumber());
		// }else if(t.response!=null){
		// String serial=t.response.getCenterNumber();
		// t.setGlobalSerial(t.response.getCenterNumber());
		// t.response.setCenterNumber(generator.getRemoteSerialByLocalSerial(serial));
		// }
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.palmcommerce.funds.trade.ITradeProtocolHandler#p240001(com.palmcommerce
	 * .funds.protocol.impl.p2t.P240001)
	 */
	public boolean p240001(P240001 p) throws ProtocolStorageException {
		// TODO Auto-generated method stub
		boolean ret = true;
		if (p.request != null) {
			String bankSerialNumber = p.request.getSerialNumber();
			String localSerialNumber = generator
					.getLocalSerialForRemoteSerial(bankSerialNumber);
			p.request.setSerialNumber(localSerialNumber);
			p.setGlobalSerial(localSerialNumber);

			RouteRuleEntry routeRuleEntry = this.configurationManager
					.getRouteRuleResult().getRouteRule(p.getHeader().to);
			//if (routeRuleEntry == null) 
			{

				String nodeCode = configurationManager
						.getTradeCodeByUserId(p.request.getUserId());
				p.getHeader().to = nodeCode;

			}

		} else if (p.response != null) {
			String localSerialNumber = p.response.getSerialNumber();
			String bankSerialNumber = generator
					.getRemoteSerialByLocalSerial(localSerialNumber);
			p.response.setSerialNumber(bankSerialNumber);
			p.setGlobalSerial(localSerialNumber);
		}

		String toCode = p.getHeader().to;
		RouteRuleEntry routeRuleEntry = this.configurationManager
				.getRouteRuleResult().getRouteRule(toCode);
		if (routeRuleEntry == null) {
			ret = false;
			p.mashallException(
					Validate_ProtocolMeta_Destination_Cannot_Reach.getState(),
					Validate_ProtocolMeta_Destination_Cannot_Reach.getMessage());
			// t.mashallException(Validate_ProtocolMeta_Destination_Cannot_Reach.,
			// Validate_ProtocolMeta_Destination_Cannot_Reach.);
		}

		return ret;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.palmcommerce.funds.trade.ITradeProtocolHandler#p240002(com.palmcommerce
	 * .funds.protocol.impl.p2t.P240002)
	 */
	public boolean p240002(P240002 p) throws ProtocolStorageException {
		// TODO Auto-generated method stub
		boolean ret = true;
		if (p.request != null) {
			String bankSerialNumber = p.request.getSerialNumber();
			String localSerialNumber = generator
					.getLocalSerialForRemoteSerial(bankSerialNumber);
			p.request.setSerialNumber(localSerialNumber);
			p.setGlobalSerial(localSerialNumber);
			RouteRuleEntry routeRuleEntry = this.configurationManager
					.getRouteRuleResult().getRouteRule(p.getHeader().to);
			//if (routeRuleEntry == null)
			{

				String nodeCode = configurationManager
						.getTradeCodeByUserId(p.request.getUserId());
				p.getHeader().to = nodeCode;

			}

		} else if (p.response != null) {
			String localSerialNumber = p.response.getSerialNumber();
			String bankSerialNumber = generator
					.getRemoteSerialByLocalSerial(localSerialNumber);
			p.response.setSerialNumber(bankSerialNumber);
			p.setGlobalSerial(localSerialNumber);
		}

		String toCode = p.getHeader().to;
		RouteRuleEntry routeRuleEntry = this.configurationManager
				.getRouteRuleResult().getRouteRule(toCode);
		if (routeRuleEntry == null) {
			ret = false;
			p.mashallException(
					Validate_ProtocolMeta_Destination_Cannot_Reach.getState(),
					Validate_ProtocolMeta_Destination_Cannot_Reach.getMessage());
			// t.mashallException(Validate_ProtocolMeta_Destination_Cannot_Reach.,
			// Validate_ProtocolMeta_Destination_Cannot_Reach.);
		}

		return ret;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.palmcommerce.funds.trade.ITradeProtocolHandler#p240003(com.palmcommerce
	 * .funds.protocol.impl.p2t.P240003)
	 */
	public boolean p240003(P240003 p) throws ProtocolStorageException {
		// TODO Auto-generated method stub
		boolean ret = true;
		if (p.request != null) {

			String bankSerialNumber = p.request.getSerialNumber();
			String localSerialNumber = generator
					.getLocalSerialForRemoteSerial(bankSerialNumber);
			p.request.setSerialNumber(localSerialNumber);
			p.setGlobalSerial(localSerialNumber);
			RouteRuleEntry routeRuleEntry = this.configurationManager
					.getRouteRuleResult().getRouteRule(p.getHeader().to);
			//if (routeRuleEntry == null) 
			{

				String nodeCode = configurationManager
						.getTradeCodeByUserId(p.request.getUserId());
				p.getHeader().to = nodeCode;

			}

		} else if (p.response != null) {
			String localSerialNumber = p.response.getSerialNumber();
			String bankSerialNumber = generator
					.getRemoteSerialByLocalSerial(localSerialNumber);
			p.response.setSerialNumber(bankSerialNumber);
			p.setGlobalSerial(localSerialNumber);
		}

		String toCode = p.getHeader().to;
		RouteRuleEntry routeRuleEntry = this.configurationManager
				.getRouteRuleResult().getRouteRule(toCode);
		if (routeRuleEntry == null) {
			ret = false;
			p.mashallException(
					Validate_ProtocolMeta_Destination_Cannot_Reach.getState(),
					Validate_ProtocolMeta_Destination_Cannot_Reach.getMessage());
			// t.mashallException(Validate_ProtocolMeta_Destination_Cannot_Reach.,
			// Validate_ProtocolMeta_Destination_Cannot_Reach.);
		}

		return ret;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.palmcommerce.funds.trade.ITradeProtocolHandler#p240004(com.palmcommerce
	 * .funds.protocol.impl.p2t.P240004)
	 */
	public boolean p240004(P240004 p) throws ProtocolStorageException {
		// TODO Auto-generated method stub
		boolean ret = true;
		if (p.request != null) {
			String bankSerialNumber = p.request.getSerialNumber();
			String localSerialNumber = generator
					.getLocalSerialForRemoteSerial(bankSerialNumber);

			String oldBankSerialNumber = p.request.getPaymentSerialNumber();
			String oldLocalSerialNumber = generator
					.getLocalSerialForRemoteSerial(oldBankSerialNumber);
			p.request.setSerialNumber(localSerialNumber);
			p.request.setPaymentSerialNumber(oldLocalSerialNumber);
			p.setGlobalSerial(localSerialNumber);
			RouteRuleEntry routeRuleEntry = this.configurationManager
					.getRouteRuleResult().getRouteRule(p.getHeader().to);
			//if (routeRuleEntry == null) 
			{

				String nodeCode = configurationManager
						.getTradeCodeByUserId(p.request.getUserId());
				p.getHeader().to = nodeCode;

			}
		} else if (p.response != null) {
			String localSerialNumber = p.response.getSerialNumber();
			String bankSerialNumber = generator
					.getRemoteSerialByLocalSerial(localSerialNumber);
			p.response.setSerialNumber(bankSerialNumber);
			String oldLocalSerial = p.response.getSeverseSerialNumber();
			String oldBankSerial = generator
					.getRemoteSerialByLocalSerial(oldLocalSerial);
			p.response.setSeverseSerialNumber(oldBankSerial);
			p.setGlobalSerial(localSerialNumber);
		}

		String toCode = p.getHeader().to;
		RouteRuleEntry routeRuleEntry = this.configurationManager
				.getRouteRuleResult().getRouteRule(toCode);
		if (routeRuleEntry == null) {
			ret = false;
			p.mashallException(
					Validate_ProtocolMeta_Destination_Cannot_Reach.getState(),
					Validate_ProtocolMeta_Destination_Cannot_Reach.getMessage());
			// t.mashallException(Validate_ProtocolMeta_Destination_Cannot_Reach.,
			// Validate_ProtocolMeta_Destination_Cannot_Reach.);
		}

		return ret;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.palmcommerce.funds.trade.ITradeProtocolHandler#p240005(com.palmcommerce
	 * .funds.protocol.impl.p2t.P240005)
	 */
	public boolean p240005(P240005 p) throws ProtocolStorageException {
		// TODO Auto-generated method stub
		boolean ret = true;
		if (p.request != null) {
			String bankSerialNumber = p.request.getSerialNumber();
			String localSerialNumber = generator
					.getLocalSerialForRemoteSerial(bankSerialNumber);
			p.request.setSerialNumber(localSerialNumber);
			p.setGlobalSerial(localSerialNumber);

		} else if (p.response != null) {
			String localSerialNumber = p.response.getSerialNumber();
			String bankSerialNumber = generator
					.getRemoteSerialByLocalSerial(localSerialNumber);
			p.response.setSerialNumber(bankSerialNumber);
			p.setGlobalSerial(localSerialNumber);
		}

		String toCode = p.getHeader().to;
		RouteRuleEntry routeRuleEntry = this.configurationManager
				.getRouteRuleResult().getRouteRule(toCode);
		if (routeRuleEntry == null) {
			ret = false;
			p.mashallException(
					Validate_ProtocolMeta_Destination_Cannot_Reach.getState(),
					Validate_ProtocolMeta_Destination_Cannot_Reach.getMessage());
			// t.mashallException(Validate_ProtocolMeta_Destination_Cannot_Reach.,
			// Validate_ProtocolMeta_Destination_Cannot_Reach.);
		}

		// String
		// nodeCode=configurationManager.getTradeCodeByUserId(p.request.getUserId());
		// p.getHeader().to=nodeCode;
		return ret;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.palmcommerce.funds.trade.ITradeProtocolHandler#p240006(com.palmcommerce
	 * .funds.protocol.impl.p2t.P240006)
	 */
	public boolean p240006(P240006 p) throws ProtocolStorageException {
		// TODO Auto-generated method stub
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.palmcommerce.funds.connection.mina.protocol.AbstractProtocolFilter
	 * #handleRequest(com.palmcommerce.funds.protocol.impl.IProtocol)
	 */
	@Override
	public boolean handleRequest(IProtocol protocol)
			throws ProtocolStorageException {
		// TODO Auto-generated method stub
		this.getMinaContext().getMetrics()
				.mark("IdGeneratorProtocolFilterHandler.handleRequest");
		boolean ret = handle(protocol);
		this.getMinaContext().getMetrics()
				.mark("IdGeneratorProtocolFilterHandler.handleRequest");

		return ret;
	}

	private static final Log logger = LogFactory
			.getLog(IdGeneratorProtocolFilterHandler.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.palmcommerce.funds.connection.mina.protocol.AbstractProtocolFilter
	 * #handleResponse(com.palmcommerce.funds.protocol.impl.IProtocol)
	 */
	@Override
	public boolean handleResponse(IProtocol protocol)
			throws ProtocolStorageException {
		// TODO Auto-generated method stub
		this.getMinaContext().getMetrics()
				.mark("IdGeneratorProtocolFilterHandler.handleResponse");

		boolean ret = handle(protocol);
		this.getMinaContext().getMetrics()
				.mark("IdGeneratorProtocolFilterHandler.handleResponse");
		return ret;
	}

	private boolean handle(IProtocol protocol) throws ProtocolStorageException {
		boolean ret = false;
		switch (protocol.getHeader().transcode) {

		case 240001: {
			P240001 p = (P240001) protocol;

			ret = p240001(p);

		}
			// paymentService.p240001((P240001)header);
			break;

		case 240002: {

			P240002 p = (P240002) protocol;

			ret = p240002(p);

		}
			// paymentService.p240001((P240001)header);
			break;

		case 240003: {

			P240003 p = (P240003) protocol;

			ret = p240003(p);

		}
			// paymentService.p240001((P240001)header);
			break;

		case 240004: {

			P240004 p = (P240004) protocol;

			ret = p240004(p);

		}
			// paymentService.p240001((P240001)header);
			break;

		case 240005: {
			P240005 p = (P240005) protocol;

			ret = p240005(p);

		}
			// paymentService.p240001((P240001)header);
			break;

		case 250001: {
			T250001 p = (T250001) protocol;
			ret = t250001(p);

		}

			break;

		case 250002: {
			T250002 p = (T250002) protocol;
			ret = t250002(p);

		}
			// paymentService.p240001((P240001)header);
			break;

		case 250004: {

			T250004 p = (T250004) protocol;
			ret = t250004(p);

		}
			// paymentService.p240001((P240001)header);
			break;

		case 250005: {

			T250005 p = (T250005) protocol;

			ret = t250005(p);

		}
			// paymentService.p240001((P240001)header);
			break;

		case 250006: {

			T250006 p = (T250006) protocol;

			ret = t250006(p);

		}
			// paymentService.p240001((P240001)header);
			break;

		case 250007: {

			T250007 p = (T250007) protocol;

			ret = t250007(p);

		}
			// paymentService.p240001((P240001)header);
			break;

		case 250008: {

			T250008 p = (T250008) protocol;

			ret = t250008(p);

		}
			// paymentService.p240001((P240001)header);
			break;

		case 250009: {

			T250009 p = (T250009) protocol;

			ret = t250009(p);

		}
			// paymentService.p240001((P240001)header);
			break;

		case 250010: {

			T250010 p = (T250010) protocol;

			ret = t250010(p);

		}
			// paymentService.p240001((P240001)header);
			break;

		case 250011: {

			T250011 p = (T250011) protocol;

			ret = t250011(p);

		}
			// paymentService.p240001((P240001)header);
			break;

		default: {
			// non handler.

		}
			break;
		}

		return ret;
	}

}
