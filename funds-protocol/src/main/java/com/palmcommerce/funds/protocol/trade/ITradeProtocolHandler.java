/**
 * 
 */
package com.palmcommerce.funds.protocol.trade;

import com.palmcommerce.funds.protocol.impl.p2t.P240001;
import com.palmcommerce.funds.protocol.impl.p2t.P240002;
import com.palmcommerce.funds.protocol.impl.p2t.P240003;
import com.palmcommerce.funds.protocol.impl.p2t.P240004;
import com.palmcommerce.funds.protocol.impl.p2t.P240005;
import com.palmcommerce.funds.protocol.impl.p2t.P240006;
import com.palmcommerce.funds.service.ProtocolStorageException;




/**
 * @author sparrow
 *
 */
public interface ITradeProtocolHandler {

	/**
	 * 
	 * @param p
	 * @return
	 * @throws ProtocolStorageException
	 */
	public boolean p240001(P240001 p) throws ProtocolStorageException;
	
	/**
	 * 
	 * @param p
	 * @return
	 * @throws ProtocolStorageException
	 */
	public boolean p240002(P240002 p) throws ProtocolStorageException;
	
	/**
	 * @param p
	 * @return
	 * @throws ProtocolStorageException
	 */
	public boolean p240003(P240003 p) throws ProtocolStorageException;
	
	/**
	 * @param p
	 * @return
	 * @throws ProtocolStorageException
	 */
	public boolean p240004(P240004 p) throws ProtocolStorageException;
	
	/**
	 * @param p
	 * @return
	 * @throws ProtocolStorageException
	 */
	public boolean p240005(P240005 p) throws ProtocolStorageException;
	
	/**
	 * @param p
	 * @return
	 * @throws ProtocolStorageException
	 */
	public boolean p240006(P240006 p) throws ProtocolStorageException;
	
	

}
