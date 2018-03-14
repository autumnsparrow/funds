package com.palmcommerce.funds.demo;

import java.io.File;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;


import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.future.ConnectFuture;

import org.apache.mina.core.future.ReadFuture;
import org.apache.mina.core.future.WriteFuture;
import org.apache.mina.core.service.IoProcessor;
import org.apache.mina.core.service.SimpleIoProcessorPool;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.transport.socket.nio.NioProcessor;
import org.apache.mina.transport.socket.nio.NioSession;
import org.apache.mina.transport.socket.nio.NioSocketConnector;







/**
 * Hello world!
 *
 */
public class AppByNetwork 
{
   private static final Log logger=LogFactory.getLog(AppByNetwork.class);
	public final static  CharsetDecoder decoder=Charset.forName("utf-8").newDecoder();
	public final static CharsetEncoder encoder=Charset.forName("utf-8").newEncoder();
	
	public static class Packet{
		int length;
		String transcode;
		String fromCode;
		String toCode;
		int siglen;
		byte[]  body;
		
		public IoBuffer write() throws CharacterCodingException{
			IoBuffer buffer=IoBuffer.allocate(length+4);
			buffer.putInt(length);
			buffer.putString(transcode, 6, encoder);
			buffer.putString(fromCode, 8, encoder);
			buffer.putString(toCode, 8, encoder);
			buffer.putInt(siglen);
			buffer.put(body);
			buffer.flip();
			return buffer;
			
		}
		
		
		public void  read(IoBuffer buffer) throws CharacterCodingException{
			
			length=buffer.getInt();
			transcode=buffer.getString(6, decoder);
			fromCode=buffer.getString(8, decoder);
			toCode=buffer.getString(8,decoder);
			siglen=buffer.getInt();
			
			buffer.mark();
			if(buffer.remaining()==(length-26)){
				IoBuffer raw=IoBuffer.allocate(length-26);
				raw.put(buffer);
				raw.flip();
				buffer.reset();
				body=raw.array();
			}
			
			
			
			
		}
		/* (non-Javadoc)
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			return "Packet [length=" + length + ", transcode=" + transcode
					+ ", fromCode=" + fromCode + ", toCode=" + toCode
					+ ", siglen=" + siglen + ", body=" + HexDump.dumpHexString(body)
					+ "]";
		}
		
		
		
	}
	
	
	/**
	 * 
	 * 
	 * Demo for the protocol 2400001
	 * 
	 * 
	 * @param args
	 * @throws Exception
	 */
	
	public static void main( String[] args ) throws Exception
    {
       // System.out.println( "Hello World!" );
    	SpringHelper.init("classpath:META-INF/spring/applicationContext_client.xml");
    	//SecurityConfigurationByBean configProperties=SpringHelper.getBean("clientConfigPropertiesLoader");
    	ICryptor clientCryptor=SpringHelper.getBean("clientCryptor");
    	ICryptor serverCryptor=SpringHelper.getBean("serverCryptor");
    	
    	
    	// client send request 
    	String content=SerialNumberGenerator.generate()+"|10000400|"+SerialNumberGenerator.getTransactionDatetime()+"|";
    	
    	// the client side encrypt the data.
    	// convert the content to byte by encoding gbk ,and signature the content by local private key.
    	byte[] raw=content.getBytes("GBK");
    	byte[] digitalSigned=clientCryptor.signature(raw);
    	
    	// concate the raw+digitalSigned
    	ByteBuffer packetBuffer=ByteBuffer.allocate(raw.length+digitalSigned.length);
    	packetBuffer.put(raw);
    	packetBuffer.put(digitalSigned);
    	packetBuffer.flip();
    	
    	byte[]  packet=packetBuffer.array();
    	
    	// encrypt  the packet 
    	byte[] requestEncryptedPacket=clientCryptor.encrypt(packet);
    	
    	Packet requestPacket=new Packet();
    	requestPacket.transcode="240001";  // 6
    	requestPacket.fromCode="BT000001"; // 8
    	requestPacket.toCode="T0000003";  //  8
    	requestPacket.siglen=128;         //  4   
    	//                            + 26
    	//						-----------
    	requestPacket.length=requestEncryptedPacket.length+26;
    	requestPacket.body=requestEncryptedPacket;
    	
    	IoProcessor<NioSession> processor;
    	NioSocketConnector connector;
    	processor=new SimpleIoProcessorPool<NioSession>(NioProcessor.class,1);
    	connector=new NioSocketConnector(processor);
		connector.getSessionConfig().setUseReadOperation(true);
		connector.setHandler(new ProtocolConnectorHandler());
		IoSession session=null;
		
		ConnectFuture future= connector.connect(new InetSocketAddress("222.177.23.40",2023));
		future.awaitUninterruptibly();	
		session = future.getSession();
		
		IoBuffer writeBuffer=requestPacket.write();
		logger.info("[request packet] - "+requestPacket.toString());
		
		
		
		WriteFuture writeFuture=session.write(writeBuffer);
		writeFuture.awaitUninterruptibly();
	    ReadFuture f=session.read();
	    f.awaitUninterruptibly();
	    IoBuffer buffer=(IoBuffer)f.getMessage();
	    
	    
	    buffer.mark();
	    int length=buffer.getInt();
	    buffer.reset();
	    IoBuffer realBuffer=IoBuffer.allocate(length);
	    realBuffer.put(buffer.array(),0,length);
	    realBuffer.flip();
	    //realBuffer.wrap(buffer.array(), 0, length);	    
	    FileUtils.writeByteArrayToFile(new File("240001_response.binary"), realBuffer.array());
	    
		Packet responsePacket=new Packet();
		responsePacket.read(buffer);
		logger.info("[response packet] - "+responsePacket.toString());
		
		
	
		
		
    	// the server side decrypt the packet.
    	byte[] responseDecryptedPacket=clientCryptor.decrypt(responsePacket.body);
    	
    	// the server side validate the signature.
    	boolean validResponsePacket=clientCryptor.validateSignature(responseDecryptedPacket);
    	
    	if(validResponsePacket){
    		String response=new String(responseDecryptedPacket,0,responseDecryptedPacket.length-responsePacket.siglen,"GBK");
    		logger.info("response:"+response);
    	}
    	
    }
}
