/**
 * 
 */
package com.palmcommerce.funds.protocol;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.palmcommerce.funds.protocol.impl.StateMessage;

/**
 * @author sparrow
 *
 */
public class AnnotationIntrospector {
	
	public static final AnnotationIntrospector  instnace=new AnnotationIntrospector();

    private static final Log logger=LogFactory.getLog(AnnotationIntrospector.class);

	public AnnotationIntrospector() {
        super();
    }

    public Annotation[] findClassAnnotation(Class<?> clazz) {
        return clazz.getAnnotations();
    }

    public ProtocolClassMetaType findClassMetaAnnotation(Class<?> clazz){
    	ProtocolClassMetaType metaType=clazz.getAnnotation(ProtocolClassMetaType.class);
    	return metaType;
    }
    public Annotation[] findMethodAnnotation(Class<?> clazz, String methodName) {

        Annotation[] annotations = null;
        try {
            Class<?>[] params = null;
            Method method = clazz.getDeclaredMethod(methodName, params);
            if (method != null) {
                annotations = method.getAnnotations();
            }
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return annotations;
    }

    public Annotation[] findFieldAnnotation(Class<?> clazz, String fieldName) {
        Annotation[] annotations = null;
        try {
            Field field = clazz.getDeclaredField(fieldName);
            if (field != null) {
                annotations = field.getAnnotations();
            }
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        return annotations;
    }
    
    
    public <T extends Annotation> List<T> findFieldsSpecialAnnotataion(Class<?> clazz,Class<T> type ){
    	Field[] fileds=clazz.getDeclaredFields();
    	List<T> result=new ArrayList<T>(fileds.length);
    	for(Field field:fileds){
    		if(field!=null){
    			T  annotations=field.getAnnotation(type);
    			
    			result.add(annotations);    			
    		}
    	}
    	return result;
    }
    
   
    
    /**
     * 
     * Core the the project ,current using .
     * 
     * @param clazz
     * @return
     */
    public  ProtocolMetaType[] findFieldsMetaTypeAnnotataion(Class<?> clazz ){
    	
    	Field[] fileds=clazz.getDeclaredFields();
    	Field[] parentFields=null;
    	Class<?> parentClass=clazz.getSuperclass();
    	if(parentClass == StateMessage.class){
    		parentFields=clazz.getSuperclass().getDeclaredFields();
    		
    	}
    	
    	
    	int len=fileds.length+(parentFields==null?0:parentFields.length);
    	
    	ProtocolMetaType[]  types=new ProtocolMetaType[len];
    	if(parentFields!=null){
    		for(Field field:parentFields){
        		if(field!=null){
        			ProtocolElementMetaType  annotations=field.getAnnotation(ProtocolElementMetaType.class);
        			types[annotations.sequenceId()]=new ProtocolMetaType(annotations, clazz, field);
        			//result.add(new ProtocolMetaType(annotations,clazz, field));    			
        		}
        	}
    	}
    	
    	for(Field field:fileds){
    		if(field!=null){
    			ProtocolElementMetaType  annotations=field.getAnnotation(ProtocolElementMetaType.class);
    			types[annotations.sequenceId()]=new ProtocolMetaType(annotations, clazz, field);
    			//result.add(new ProtocolMetaType(annotations,clazz, field));    			
    		}
    	}
    	
    
    	return types;
    }
    
    public static void showAnnotations(Annotation[] ann) {
        if (ann == null)
            return;
        for (Annotation a : ann) {
           logger.info(a.toString());
        }
    }


}
