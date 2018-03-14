/**
 * 
 */
package com.palmcommerce.funds.packet;



/**
 * @author sparrow
 *
 */
public class PacketFactory {

	/**
	 * 
	 */
	
	public PacketFactory() {
		// TODO Auto-generated constructor stub
	
	}
	
	
	
	private static final PacketFactory factory=new PacketFactory();
	public static PacketFactory getFactory(){
		return factory;
	}
	
	public Packet getPacket(){
		Packet p=new Packet();
		
		//p.transactionSerial=genrator.nextStringIdentifier();
		return p;
	}

}
