/**
 * 
 */
package com.palmcommerce.funds.roo.protocol;

import com.palmcommerce.funds.service.ProtocolProcessException;
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

/**
 * @author sparrow
 *
 */
public interface IProtocolProcessHandler {

	/**
	 * 
	 * @param p
	 * @return
	 * @throws ProtocolProcessException
	 */
	public boolean p240001(P240001 p) throws ProtocolProcessException;
	
	/**
	 * 
	 * @param p
	 * @return
	 * @throws ProtocolProcessException
	 */
	public boolean p240002(P240002 p) throws ProtocolProcessException;
	
	/**
	 * @param p
	 * @return
	 * @throws ProtocolProcessException
	 */
	public boolean p240003(P240003 p) throws ProtocolProcessException;
	
	/**
	 * @param p
	 * @return
	 * @throws ProtocolProcessException
	 */
	public boolean p240004(P240004 p) throws ProtocolProcessException;
	
	/**
	 * @param p
	 * @return
	 * @throws ProtocolProcessException
	 */
	public boolean p240005(P240005 p) throws ProtocolProcessException;
	
	/**
	 * @param p
	 * @return
	 * @throws ProtocolProcessException
	 */
	public boolean p240006(P240006 p) throws ProtocolProcessException;
	
	
	
	/**
	 * 
	 * @param p
	 * @return
	 * @throws ProtocolProcessException
	 */
	public boolean t250001(T250001 t) throws ProtocolProcessException;
	
	/**
	 * @param t
	 * @return
	 * @throws ProtocolProcessException
	 */
	public boolean t250002(T250002 t) throws ProtocolProcessException;
	
	/**
	 * @param t
	 * @return
	 * @throws ProtocolProcessException
	 */
	public boolean t250004(T250004 t) throws ProtocolProcessException;
	
	/**
	 * @param t
	 * @return
	 * @throws ProtocolProcessException
	 */
	public boolean t250005(T250005 t) throws ProtocolProcessException;
	
	/**
	 * @param t
	 * @return
	 * @throws ProtocolProcessException
	 */
	public boolean t250006(T250006 t) throws ProtocolProcessException;
	
	/**
	 * @param t
	 * @return
	 * @throws ProtocolProcessException
	 */
	public boolean t250007(T250007 t) throws ProtocolProcessException;
	
	/**
	 * @param t
	 * @return
	 * @throws ProtocolProcessException
	 */
	public boolean t250008(T250008 t) throws ProtocolProcessException;
	
	/**
	 * @param t
	 * @return
	 * @throws ProtocolProcessException
	 */
	public boolean t250009(T250009 t) throws ProtocolProcessException;
	
	/**
	 * @param t
	 * @return
	 * @throws ProtocolProcessException
	 */
	public boolean t250010(T250010 t) throws ProtocolProcessException;
	
	/**
	 * @param t
	 * @return
	 * @throws ProtocolProcessException
	 */
	public boolean t250011(T250011 t) throws ProtocolProcessException;
	
	/**
	 * 
	 * @param t
	 * @return
	 * @throws ProtocolProcessException
	 */
	public boolean t260004(T260004 t)throws ProtocolProcessException;
}
