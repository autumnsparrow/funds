/**
 * 
 */
package com.palmcommerce.funds.protocol.trade;

import com.palmcommerce.funds.protocol.covertor.exception.ProtocolConvertorException;
import com.palmcommerce.funds.protocol.validator.exception.ProtocolValidtorException;
import com.palmcommerce.funds.service.ProtocolStorageException;



/**
 * @author sparrow
 *
 */
public interface IStatus {
	
	
	public static final String STATE_OK="0000";
	
	// user not exist
		public static final ProtocolStorageException userNotExistException=new ProtocolStorageException("0002","用户不存在!");
		public static final ProtocolStorageException paymentSerialRepeated=new ProtocolStorageException("0003","交易流水号重复!");
		public static final ProtocolStorageException paymentAmountFormatWrongRepeated=new ProtocolStorageException("0004","金额只能是分!");
		public static final ProtocolStorageException transactionTimeNotEqualsAccountTimeException=new ProtocolStorageException("0005","交易日期与账户日期不一致!");
		
	// for 240002
	// charge serial repeated
		public static final ProtocolStorageException userAlreadBindingException=new ProtocolStorageException("2001","用户已绑定!");	
		public static final ProtocolStorageException bindingFailedBindingException=new ProtocolStorageException("2002","用户绑定失败!");	
		public static final ProtocolStorageException bindingFailedBindingByInformationMisMatchException=new ProtocolStorageException("2003","用户绑定失败,身份信息不匹配!");	

		public static final ProtocolStorageException canotUnbindWithoutBindingException=new ProtocolStorageException("2004","用户解绑定失败,用户未绑定!");
		public static final ProtocolStorageException canotbindWithBindingException=new ProtocolStorageException("2005","用户绑定失败,用户已绑定!");
		public static final ProtocolStorageException canotUnbindWithunBindingException=new ProtocolStorageException("2006","用户解绑定失败,用户已解绑定!");
		public static final ProtocolStorageException unbindAccountMismatchException=new ProtocolStorageException("2007","用户解绑定失败,用户解绑账户不匹配!");
		

		
	
	// for the 240003
	// charge serial repeated
	public static final ProtocolStorageException chargeSerialRepeatedException=new ProtocolStorageException("3001","充值失败,充值交易流水号重复");	
	public static final ProtocolStorageException chargeFailedByInformationMisMatchException=new ProtocolStorageException("3002","充值失败,身份信息不匹配!");	

	// charge failed
	public static final ProtocolStorageException chargeFailedException=new ProtocolStorageException("3003", "充值失败");
	
	
	//for 240004
	public static final ProtocolStorageException cancelChargeFailedException=new ProtocolStorageException("4003", "冲正失败!");
	public static final ProtocolStorageException cancelChargeAmountNotEqualException=new ProtocolStorageException("4004", "冲正金额不相同!");
	public static final ProtocolStorageException cancelChargeAlreadyDonedException=new ProtocolStorageException("4005", "冲正失败,已冲正!");
	public static final ProtocolStorageException cancelChargeFailedByInformationMisMatchException=new ProtocolStorageException("4006","冲正失败,身份信息不匹配!");	
	public static final ProtocolStorageException cancelChargeFailedCancelSerialNumberNotExistException=new ProtocolStorageException("4007","冲正失败,冲正交易流水号不存在!");	
	public static final ProtocolStorageException cancelChargeAccountTimeNotEqualException=new ProtocolStorageException("4008", "冲正失败,冲正账户日期不相同!");
	public static final ProtocolStorageException cancelChargeAccountIdNotEqualException=new ProtocolStorageException("4009", "冲正失败,冲正流水号与用户id不相符!");
	
	//for 240005
	public static final ProtocolStorageException insertuserFailedException=new ProtocolStorageException("5003", "创建用户失败!");
		
	public static final ProtocolConvertorException CONVER_EXCEPTION_PROTOCOL_IS_NULL= new ProtocolConvertorException("8000", "协议对象为空!");
	
	public static final ProtocolConvertorException CONVER_EXCEPTION_RESPONSE_PACTET_FAIL=new ProtocolConvertorException("9011", "响应数据包失败!");
	public static final ProtocolConvertorException CONVER_EXCEPTION_REQUEST_PACTET_FAIL=new ProtocolConvertorException("9010", "请求数据包失败!");
	public static final ProtocolConvertorException CONVER_EXCEPTION_REQEUST_PACTET_IS_NULL=new ProtocolConvertorException("9012", "请求数据包为空!");
	
	public static final ProtocolConvertorException CONVER_EXCEPTION_RESPONSE_PACTET_IS_NULL=new ProtocolConvertorException("9013", "响应数据包为空!");
	//for Validate
	public static final ProtocolValidtorException  Validate_ProtocolMeta_Not_Exist_Exception=new ProtocolValidtorException("9000","协议元数据不存在!");
	public static final ProtocolValidtorException  Validate_ProtocolMeta_And_Entities_Not_Match=new ProtocolValidtorException("9001","协议数据项不匹配!");
	public static final ProtocolValidtorException  Validate_ProtocolMeta_Missing_Required_Element=new ProtocolValidtorException("9002","协议缺少数据项!");
	public static final ProtocolValidtorException  Validate_ProtocolMeta_Convert_String_UnSupported_Charset=new ProtocolValidtorException("9003","协议字符转码错误!");
	public static final ProtocolValidtorException  Validate_ProtocolMeta_Out_Range_Of_Size=new ProtocolValidtorException("9004","数据项长度不满足!!");
	public static final ProtocolValidtorException  Validate_ProtocolMeta_Date_Format_Error=new ProtocolValidtorException("9005","数据项日期格式不正确!");
	public static final ProtocolValidtorException  Validate_ProtocolMeta_Bad_EnumSet_Value=new ProtocolValidtorException("9006","Bad EnumSet value");
	public static final ProtocolValidtorException  Validate_ProtocolMeta_Invalidate_EnumType=new ProtocolValidtorException("9007","Invalidated EnumType");

	public static final ProtocolValidtorException  Validate_ProtocolMeta_Destination_Cannot_Reach=new ProtocolValidtorException("9008","目的服务器无法到达!");

		
	
}
