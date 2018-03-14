/**
 * 
 */
package com.palmcommerce.funds.protocol.covertor;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.palmcommerce.funds.protocol.ProtocolMetaNotFoundException;
import com.palmcommerce.funds.protocol.ProtocolDriverManager;
import com.palmcommerce.funds.protocol.ProtocolDriverManager.ProtocolMeta;
import com.palmcommerce.funds.protocol.covertor.exception.ProtocolConvertorException;
import com.palmcommerce.funds.protocol.validator.IProtocolValidator;
import com.palmcommerce.funds.protocol.validator.ProtocolValidatorFactory;
import com.palmcommerce.funds.protocol.validator.exception.ProtocolValidtorException;

/**
 * @author sparrow
 *
 */
public class DefaultProtocolConvertor implements IProtocolConvertor {

	private static final  Log logger=LogFactory.getLog(DefaultProtocolConvertor.class);

	/**
	 * 
	 */
	public DefaultProtocolConvertor() {
		// TODO Auto-generated constructor stub
	}
	
	private String isNull(String s){
		if(s==null)s="";
		return s;
	}

	/* (non-Javadoc)
	 * @see com.palmcommerce.funds.protocol.covertor.IProtocolConvertor#convert(java.lang.String[])
	 */
	public <T> T convert(Class<?> identifier,String[] entities) throws ProtocolConvertorException {
		// TODO Auto-generated method stub
		// validate the protocol
		IProtocolValidator validator=ProtocolValidatorFactory.getFactory().getValidator();
		
		boolean validated=false;
		T t=null;
		try {
			  validated=validator.validate(identifier,entities);
		} catch (ProtocolValidtorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ProtocolConvertorException(e.getState(), e.getMessage());
			
			
		}
		
		// parser
		String protocolIdentifer=identifier.getName();//entities[0];
		if(validated){
			// 
			try {
				ProtocolMeta meta=ProtocolDriverManager.getProtocolMeta(protocolIdentifer);
				
				// convert the 
				
				t=(T) meta.protocolClazz.newInstance();
				
				 
				 if(entities.length!=meta.protocolMetaTypes.length){
					 throw new ProtocolConvertorException("protocol entry not match [entries.number="+entities.length+", meta.number="+meta.protocolMetaTypes.length+" ]");
				 }
				 for(int i=0;i<entities.length;i++){
					 
					 Field field=meta.protocolMetaTypes[i].field;
					 String fieldName=field.getName();
					 PropertyUtils.setSimpleProperty(t, fieldName, isNull(entities[i]));
//					 if(meta.protocolMetaTypes[i].metaType.isStateMessage()){
//						 
//						 PropertyUtils.setSimpleProperty((StateMessage)t, fieldName, entities[i]);
//					 }else{
//						 PropertyUtils.setSimpleProperty(t, fieldName, entities[i]);
//					 }
					 
				 }
				
			} catch (ProtocolMetaNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		return t;
	}

	public <T> String convert( T t)
			throws ProtocolConvertorException {
		// TODO Auto-generated method stub
		if(t==null)
			throw ProtocolConvertorException.CONVER_EXCEPTION_PROTOCOL_IS_NULL;
		
		StringBuffer stringBuffer=new StringBuffer();
		String protocolIdentifer=t.getClass().getName();
		
		try {
			ProtocolMeta meta=ProtocolDriverManager.getProtocolMeta(protocolIdentifer);
			
			 for(int i=0;i<meta.protocolMetaTypes.length;i++){
				 
				 Field field=meta.protocolMetaTypes[i].field;
				 
				 String fieldName=field.getName();
				 String value=(String) PropertyUtils.getProperty(t, fieldName);
				 stringBuffer.append(isNull(value)).append("|");
				 //PropertyUtils.setSimpleProperty(t, fieldName, entities[i]);
//				 if(meta.protocolMetaTypes[i].metaType.isStateMessage()){
//					 
//					 PropertyUtils.setSimpleProperty((StateMessage)t, fieldName, entities[i]);
//				 }else{
//					 PropertyUtils.setSimpleProperty(t, fieldName, entities[i]);
//				 }
				 
			 }
			
			
			
		} catch (ProtocolMetaNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return stringBuffer.toString();
	}

	public <T> T convertException(Class<?> identifier, String state,
			String reason) {
		// TODO Auto-generated method stub
		T t=null;
		String protocolIdentifer=identifier.getName();//entities[0];
		
			// 
			try {
				ProtocolMeta meta=ProtocolDriverManager.getProtocolMeta(protocolIdentifer);
				
				// convert the 
				
				t= (T)meta.protocolClazz.newInstance();
				
				// PropertyUtils.setSimpleProperty(t, , entities[i]);
				String[] entities=new String[meta.protocolMetaTypes.length];
				entities[0]=state;
				entities[1]=reason;
				for(int i=0;i<entities.length;i++){
					String entity=entities[i];
					 Field field=meta.protocolMetaTypes[i].field;
					 String fieldName=field.getName();
					if(null==entity){
						 PropertyUtils.setSimpleProperty(t, fieldName, "");
					}else{
						 PropertyUtils.setSimpleProperty(t, fieldName, entity);
					}
				}
				
				
			} catch (ProtocolMetaNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		
		return t;
	}

}
