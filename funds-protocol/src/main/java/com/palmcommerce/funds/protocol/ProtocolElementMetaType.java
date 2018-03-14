/**
 * 
 */
package com.palmcommerce.funds.protocol;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.*;

/**
 * @author sparrow
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ProtocolElementMetaType {
	public enum ProtocolElementType{STRING,INTEGER,BINARY,DATETIME};
	int sequenceId();
	ProtocolElementType elementType();
	int minLenght()  default 1;
	int maxLength()  default 50;
	boolean required() default true;
	boolean isStateMessage() default false;
	String dateTimeFormat() default "yyyy-MM-dd hh:mm:ss";
	String enumSet() default "";
	
	
}
