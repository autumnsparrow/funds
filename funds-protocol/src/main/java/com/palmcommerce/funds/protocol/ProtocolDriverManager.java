/**
 * 
 */
package com.palmcommerce.funds.protocol;

import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.beanutils.ConstructorUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.palmcommerce.funds.protocol.ProtocolClassMetaType.TRANSFER_MODE;
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



/**
 * 
 * -- configure  the protocol parse map.
 * 
 * 
 * @author sparrow
 *
 */




public class ProtocolDriverManager {
	
	private static final Log logger = LogFactory.getLog(ProtocolDriverManager.class);
	private static final ConcurrentHashMap<String,ProtocolEntry> sMap=new ConcurrentHashMap<String,ProtocolEntry>();
	private static final ConcurrentHashMap<String, ProtocolMeta> sProtocolMetaMap=new ConcurrentHashMap<String, ProtocolDriverManager.ProtocolMeta>();
	
	
	
	static {
		ProtocolDriverManager.registger(P240001.class,P240001.Request.class,P240001.Response.class);
		ProtocolDriverManager.registger(P240002.class,P240002.Request.class,P240002.Response.class);
		ProtocolDriverManager.registger(P240003.class,P240003.Request.class,P240003.Response.class);
		ProtocolDriverManager.registger(P240004.class,P240004.Request.class,P240004.Response.class);
		ProtocolDriverManager.registger(P240005.class,P240005.Request.class,P240005.Response.class);
		ProtocolDriverManager.registger(P240006.class,P240006.Request.class,P240006.Response.class);
		
		
		ProtocolDriverManager.registger(T250001.class,T250001.Request.class,T250001.Response.class);
		ProtocolDriverManager.registger(T250002.class, T250002.Request.class, T250002.Response.class);
		ProtocolDriverManager.registger(T250004.class,T250004.Request.class,T250004.Response.class);
		ProtocolDriverManager.registger(T250005.class,T250005.Request.class,T250005.Response.class);
		ProtocolDriverManager.registger(T250006.class,T250006.Request.class,T250006.Response.class);
		ProtocolDriverManager.registger(T250007.class,T250007.Request.class,T250007.Response.class);
		ProtocolDriverManager.registger(T250008.class,T250008.Request.class,T250008.Response.class);
		ProtocolDriverManager.registger(T250009.class,T250009.Request.class,T250009.Response.class);
		ProtocolDriverManager.registger(T250010.class,T250010.Request.class,T250010.Response.class);
		ProtocolDriverManager.registger(T250011.class,T250011.Request.class,T250011.Response.class);

		ProtocolDriverManager.registger(T260004.class,T260004.Request.class,T260004.Response.class);
	
	
	}
	
	
	/**
	 * 
	 * map the protocol metaType and protocol clazz
	 * 
	 * @author sparrow
	 *
	 */
	public static class ProtocolMeta{
		public String   protocolIdentifier;
		public Class<?> protocolClazz;
	
		public ProtocolMetaType[] protocolMetaTypes;
		public ProtocolMeta(Class<?> protocolClazz) {
			super();
			this.protocolClazz = protocolClazz;
			this.protocolIdentifier=this.protocolClazz.getName();//.substring(1);
			this.protocolMetaTypes=AnnotationIntrospector.instnace.findFieldsMetaTypeAnnotataion(this.protocolClazz);
			sProtocolMetaMap.put(this.protocolIdentifier, this);
		}
		
	}
	
	public  static void registger(Class<?> parentClazz,Class<?> requestClazz,Class<?> repsonseClazz){
		new ProtocolEntry(parentClazz);
				
		new ProtocolMeta(requestClazz);
		new ProtocolMeta(repsonseClazz);
		logger.info(" -----------Register : "+parentClazz.getSimpleName()+" \n "+requestClazz.getName()+" \n "+repsonseClazz.getName());
		
	}
	/**
	 * 
	 *  static parse map for the protocol and protocol meta
	 * 
	 */
	
	/**
	 *  process of the inject the protocol meta
	 * 
	 */
	/*static{
		// ini
		registger(P240001.Request.class);
		registger(P240001.Response.class);
		registger(P240002.Request.class);
		registger(P240002.Response.class);
		registger(P240003.Request.class);
		registger(P240003.Response.class);
		registger(P240004.Request.class);
		registger(P240004.Response.class);
		registger(P240005.Request.class);
		registger(P240005.Response.class);
		
		
		
		//sProtocolMetaMap.put(P240001.Request.class.getSimpleName(), new Pro)
		
	};
*/
	/**
	 * 
	 */
	public ProtocolDriverManager() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * fetch the protocol meta.
	 * 
	 * @param protocolIdentifer
	 * @return
	 * @throws ProtocolMetaNotFoundException 
	 */
	public static ProtocolMeta getProtocolMeta(String protocolIdentifer) throws ProtocolMetaNotFoundException{
		ProtocolMeta meta=null;
		if(sProtocolMetaMap.containsKey(protocolIdentifer)){
			meta=sProtocolMetaMap.get(protocolIdentifer);
		}else{
			throw new ProtocolMetaNotFoundException();
		}
		return meta;
	}
	
	
	
	public static ProtocolMeta getProtocolMeta(Class<?> t) throws ProtocolMetaNotFoundException{
		
		ProtocolMeta meta=null;
		String id=t.getName();
		if(sProtocolMetaMap.containsKey(id)){
			meta=sProtocolMetaMap.get(id);
		}else{
			throw new ProtocolMetaNotFoundException();
		}
		return meta;
	}
	
	
	private static class ProtocolEntry{
		String transcode;
		Class<?> clazz;
		ProtocolClassMetaType metaType;
		public ProtocolEntry(Class<?> clazz) {
			super();
			transcode=clazz.getSimpleName().substring(1);
			this.clazz = clazz;
			this.metaType=AnnotationIntrospector.instnace.findClassMetaAnnotation(this.clazz);
			sMap.put(transcode, this);
		}
		
		public boolean isDirect(){
			return this.metaType.mode()==TRANSFER_MODE.DERIECT_PROXY;
		}
		
		
		
	}
	
	public static boolean isTransferModeDirect(String transcode){
		ProtocolEntry entry=sMap.get(transcode);
		boolean ret=false;
		if(entry!=null){
			ret=entry.isDirect();
		}
		return ret;	
	}
	
//	private static void registerProtocol(Class<?> clazz){
//		new ProtocolEntry(clazz);
//	}

//	static {
//		registerProtocol(P240001.class);
//		registerProtocol(P240002.class);
//		registerProtocol(P240003.class);
//		registerProtocol(P240004.class);
//		registerProtocol(P240005.class);
//		registerProtocol(P240006.class);
//		registerProtocol(T250001.class);
//		registerProtocol(T250002.class);
//		registerProtocol(T250004.class);
//		registerProtocol(T250006.class);
//		registerProtocol(T250007.class);
//		registerProtocol(T250008.class);
//		registerProtocol(T250009.class);
//		registerProtocol(T250010.class);
//		registerProtocol(T250011.class);
//	};
	
	private static final Object lock =new Object();
	
	
	/**
	 * 
	 * 
	 * create protocol object by request and response packet.
	 * @param transcode
	 * @param fromcode
	 * @param tocode
	 * @param request
	 * @param response
	 * @return
	 */
	public static IProtocol instance(String transcode, String fromcode, String tocode,
			String request, String response){
	
		IProtocol t=null;
		ProtocolEntry entry=sMap.get(transcode);
		try {
			synchronized (lock) {
				t=(IProtocol)ConstructorUtils.invokeConstructor(entry.clazz, 
						new Object[]{transcode,fromcode,tocode,request,response},new Class[]{String.class,String.class,String.class,String.class,String.class});
				
			}
				} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return t;
	}
	
	
	/**
	 * create the protocol object by the request or response packet.
	 * @param transcode
	 * @param fromcode
	 * @param tocode
	 * @param packet
	 * @param isRequest
	 * @return
	 */
	public static IProtocol instance(String transcode, String fromcode, String tocode,
			String packet, boolean isRequest){
	
		IProtocol t=null;
		ProtocolEntry entry=sMap.get(transcode);
		try {
			t=(IProtocol)ConstructorUtils.invokeConstructor(entry.clazz, 
					new Object[]{transcode,fromcode,tocode,packet,Boolean.valueOf(isRequest)},new Class[]{String.class,String.class,String.class,String.class,Boolean.class});
			
			
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return t;
	}
		
	/**
	 * 
	 * 
	 * Create the protocol and protocol request object.
	 * @param transcode
	 * @param fromcode
	 * @param tocode
	 * @return
	 */
	public static IProtocol instance(String transcode, String fromcode, String tocode
			){
	
		IProtocol t=null;
		ProtocolEntry entry=sMap.get(transcode);
		try {
			t=(IProtocol)ConstructorUtils.invokeConstructor(entry.clazz, 
					new Object[]{transcode,fromcode,tocode},new Class[]{String.class,String.class,String.class});
			Object request=t.getRequestClazz().newInstance();
			t.setRequestObject(request);
			
			
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return t;
	}
		

}
