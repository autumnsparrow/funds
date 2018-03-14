/**
 * 
 */
package com.palmcommerce.funds.packet.security;

import com.palmcommerce.funds.packet.Packet;
import com.palmcommerce.funds.packet.security.exception.DecryptPacketException;
import com.palmcommerce.funds.packet.security.exception.EncryptPacketException;
import com.palmcommerce.funds.packet.security.exception.SignaturePacketException;
import com.palmcommerce.funds.packet.security.exception.ValidatePacketException;

/**
 * @author sparrow
 *
 */
public interface IPacketSecurity {
	
	public void setEnable(boolean enable);
	
	/**
	 * de crypt the packet content after the signature validated.
	 * @param packet
	 * @return
	 * @throws DecryptPacketException
	 */
	public boolean decryptPacket(Packet packet) throws DecryptPacketException; 
	
	/**
	 * 
	 * encrypt the plain text before signature.
	 * @param packet
	 * @return
	 * @throws EncryptPacketException
	 */
	public boolean encryptPacket(Packet packet) throws EncryptPacketException;
	
	
	/**
	 * validate the signature of the packet.
	 * 
	 * @param packet
	 * @return
	 * @throws ValidatePacketException
	 */
	public boolean validateSignature(Packet packet)throws ValidatePacketException;
	
	/**
	 * signature the packet after the encrypt
	 * @param packet
	 * @return
	 * @throws SignaturePacketException
	 */
	public boolean signature(Packet packet) throws SignaturePacketException;
	
	/**
	 * parse the plain text into the encrypted packet.
	 * 
	 * @param packet
	 * @return
	 * @throws MashallPacketSecurityException
	 */
	public boolean mashall(Packet packet) throws EncryptPacketException,SignaturePacketException;
	
	
	/**
	 * parse the encrypted packet into plain text.
	 * 
	 * @param packet
	 * @return
	 * @throws DecryptPacketException
	 * @throws ValidatePacketException
	 */
	public boolean unMashall(Packet packet) throws DecryptPacketException,ValidatePacketException;

}
