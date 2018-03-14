/**
 * @company Palm Lottery Information&Technology Co.,Ltd.
 *
 * @author  sparrow
 *
 * @date    Sep 7, 2013-10:03:23 AM
 *
 */
package com.palmcommerce.funds.connection.mina.storage;

import com.palmcommerce.funds.packet.Packet;

/**
 * @author sparrow
 *
 */
public interface IRemoteStorage {
	
	public boolean save(Packet request,Packet response)throws StorageException;

}
