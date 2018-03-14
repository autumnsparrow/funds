/**
 * 
 */
package com.palmcommerce.funds.protocol;

import java.lang.reflect.Field;

/**
 * @author sparrow
 *
 */
public class ProtocolMetaType {

	public ProtocolElementMetaType metaType;

	public Class<?> clazz;
	public Field  field;
	public ProtocolMetaType(ProtocolElementMetaType metaType, Class<?> clazz,
			Field field) {
		super();
		
		this.metaType = metaType;
		this.clazz = clazz;
		this.field = field;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ProtocolMetaType [metaType=" + metaType  + ", clazz=" + clazz + ", field=" + field + "]";
	}

	
}
