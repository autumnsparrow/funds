/**
 * @company Palm Lottery Information&Technology Co.,Ltd.
 *
 * @author  sparrow
 *
 * @date    Sep 5, 2013-11:46:30 AM
 *
 */
package com.palmcommerce.funds.connection.mina.storage;

import com.palmcommerce.funds.packet.Packet;

/**
 * @author sparrow
 *
 */
public interface ILocalStorage {
	/**
	 * 
	 * @param packet
	 * @return
	 * @throws StorageException
	 */
	public boolean  request(Packet packet) throws StorageException;
	
	/**
	 * 
	 * @param packet
	 * @return
	 * @throws StorageException
	 */
	public boolean response(Packet packet) throws StorageException;

}
