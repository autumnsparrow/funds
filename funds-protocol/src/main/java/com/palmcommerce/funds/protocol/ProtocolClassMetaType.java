/**
 * @company Palm Lottery Information&Technology Co.,Ltd.
 *
 * @author  sparrow
 *
 * @date    Oct 27, 2013
 *
 */
package com.palmcommerce.funds.protocol;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author sparrow
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface ProtocolClassMetaType {
	public enum TRANSFER_MODE{DERIECT_PROXY,DELAY_PROXY};
	TRANSFER_MODE mode() default TRANSFER_MODE.DERIECT_PROXY;
}
