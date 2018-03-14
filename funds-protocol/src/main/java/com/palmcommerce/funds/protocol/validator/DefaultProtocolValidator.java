/**
 * 
 */
package com.palmcommerce.funds.protocol.validator;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.palmcommerce.funds.protocol.ProtocolDriverManager;
import com.palmcommerce.funds.protocol.ProtocolDriverManager.ProtocolMeta;
import com.palmcommerce.funds.protocol.ProtocolElementMetaType;
import com.palmcommerce.funds.protocol.ProtocolElementMetaType.ProtocolElementType;
import com.palmcommerce.funds.protocol.ProtocolMetaNotFoundException;
import com.palmcommerce.funds.protocol.parser.ProtocolParserFactory;
import com.palmcommerce.funds.protocol.parser.exception.ProtocolParserException;
import com.palmcommerce.funds.protocol.trade.IStatus;
import com.palmcommerce.funds.protocol.validator.exception.ProtocolValidtorException;

/**
 * @author sparrow
 *
 */
public class DefaultProtocolValidator implements IProtocolValidator,IStatus {
	
	private static final Log logger=LogFactory.getLog(DefaultProtocolValidator.class);
	/**
	 * 
	 */
	public DefaultProtocolValidator() {
		// TODO Auto-generated constructor stub
	}

	public boolean validate(Class<?> identifier,String[] entities) throws ProtocolValidtorException {
		// TODO Auto-generated method stub
		
		String protocolIdentifer=identifier.getName();//entities[0];
		
			// 
		ProtocolMeta meta=null;
		try {
			meta=ProtocolDriverManager.getProtocolMeta(protocolIdentifer);
			
		} catch (ProtocolMetaNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//throw Validate_ProtocolMeta_Not_Exist_Exception;
			throw new ProtocolValidtorException(Validate_ProtocolMeta_Not_Exist_Exception.getState(), Validate_ProtocolMeta_Not_Exist_Exception.getMessage()+" identified:"+protocolIdentifer);
			//return false;
		}
		
		if(meta!=null){
			
			if(meta.protocolMetaTypes.length!=entities.length){
				
				
				throw  new ProtocolValidtorException(Validate_ProtocolMeta_And_Entities_Not_Match.getState(),Validate_ProtocolMeta_And_Entities_Not_Match.getMessage()+" protocolMeata.length:"+meta.protocolMetaTypes.length+" entries.length:"+entities.length);
			}
			
			ProtocolElementMetaType metaType;
			String value=null;
			for(int i=0;i<entities.length;i++){
				String field=meta.protocolMetaTypes[i].field.getName();
				metaType=meta.protocolMetaTypes[i].metaType;
				
				value=entities[i];
				// valid the data type.
				// valid the data type
				// all the data type is chars.
				// validate the size range of the bytes.
				
				if(metaType.required()){
					if(null==value||"".equals(value)){
						throw new ProtocolValidtorException(Validate_ProtocolMeta_Missing_Required_Element.getState(), Validate_ProtocolMeta_Missing_Required_Element.getMessage()+" - "+field);
					}
					int minLength=metaType.minLenght();
					int maxLength=metaType.maxLength();
					int length=0;
					//try {
						length=value.length();
						//length = value.getBytes(Packet.ENCODING_CHARSET).length;
					
					
					if(length<minLength){
						throw new ProtocolValidtorException(Validate_ProtocolMeta_Out_Range_Of_Size.getState(), Validate_ProtocolMeta_Out_Range_Of_Size.getMessage()+" current:"+length+" less  than"+minLength+" - "+field);
					}
					if(length>maxLength){
						throw new ProtocolValidtorException(Validate_ProtocolMeta_Out_Range_Of_Size.getState(), Validate_ProtocolMeta_Out_Range_Of_Size.getMessage()+" current:"+length+" larger than "+maxLength+" - "+field);
					}
				}
				
			
				
				
				// validate the data type.
				if(metaType.elementType()==ProtocolElementType.DATETIME){
					String pattern=metaType.dateTimeFormat();
					DateFormat formatter = new SimpleDateFormat(pattern);
					try {
						formatter.parse(value);
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						throw new ProtocolValidtorException(Validate_ProtocolMeta_Date_Format_Error.getState(), Validate_ProtocolMeta_Date_Format_Error.getMessage()+" corrected:"+pattern+" - "+field);
						
					}
				}
				
				if(!"".equals(metaType.enumSet())){
					// parse the enum data type.
					String[] enumTypes=null;
					try {
						enumTypes=ProtocolParserFactory.getFactory().getParser().parse(metaType.enumSet());
					} catch (ProtocolParserException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						throw new ProtocolValidtorException(Validate_ProtocolMeta_Bad_EnumSet_Value.getState(), Validate_ProtocolMeta_Bad_EnumSet_Value.getMessage()+" current:"+metaType.enumSet()+" - "+field);
						
					}
					if(enumTypes!=null){
						boolean validateEnumType=false;
					for(String enumType:enumTypes){
						if(enumType.equals(value))
						{
							validateEnumType=true;
							break;
						}
					}
					if(!validateEnumType){
						throw new ProtocolValidtorException(Validate_ProtocolMeta_Invalidate_EnumType.getState(), Validate_ProtocolMeta_Invalidate_EnumType.getMessage()+" value: "+value+" -"+field);
					}
					}
				}
				 
				
				 
				 
			}
			
		}
			
		
		
		
		return true;
	}

	

}
