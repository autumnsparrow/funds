/**
 * @company Palm Lottery Information&Technology Co.,Ltd.
 *
 * @author  sparrow
 *
 * @date    Oct 26, 2013-4:38:06 PM
 *
 */
package com.palmcommerce.funds.packet.process;



import com.palmcommerce.funds.packet.Packet;
import com.palmcommerce.funds.service.ProtocolProcessException;

/**
 * @author sparrow
 *
 */
public interface IProtocolFilter {
	
	public boolean requestFilte(Packet packet) throws ProtocolProcessException ;

	public boolean responsetFilte(Packet packet) throws ProtocolProcessException ;


}
