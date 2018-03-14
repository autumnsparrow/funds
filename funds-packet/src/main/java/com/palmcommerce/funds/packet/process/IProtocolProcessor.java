/**
 * @company Palm Lottery Information&Technology Co.,Ltd.
 *
 * @author  sparrow
 *
 * @date    Sep 9, 2013-7:32:01 PM
 *
 */
package com.palmcommerce.funds.packet.process;





import com.palmcommerce.funds.service.ProtocolProcessException;

import Ice.StringHolder;

/**
 * 
 * the protocol processor can be used for the acceptor or the connector.
 * 
 * 
 * acceptor/connector model:
 * 
 *         fromCode   																toCode
 * 		protocol				IProtocolProcessor   connector		accecptor    	 IProtocolPrcessor    			protocol         handler
 *<object to content> -content-->  <process>	     				
 * 	  		|	 					|-----encrypt-->|------------>|--decrypt -----------> |								|              |
 * 	 		|	 				 	|				|			  |					  <process>  ---content--<content to object> - ><do logic> -
 * 	  		|	 					|				|			  |						  |					 <object to content> <-    |
 * 	  		|<------	            |<-----decrypt--|<---------   |<---encrypt- response--| <-----------------content --|                               
 *   		|					    |
 *<content to object>   
 *
 * 
 * 
 * 
 * acceptor-connector (proxy model):
 * 
 *
          fromCode   														toCode
 * 		protocol				IProtocolProcessor connector  [proxy]	   accecptor    	 IProtocolPrcessor    					protocol         handler
 *<object to content> -content-->  <process>	     		 	+--+		
 * 	  		|	 					|-----encrypt-->|--------  >|  |------->    |--decrypt -----------> |								|              |
 * 	 		|	 				 	|				|			|  | 			|					  <process>  ---content--<content to object> - ><do logic> -
 * 	  		|	 					|				|			|  |			|						|					 <object to content> <-    |
 * 	  		|<------	            |<-----decrypt--|<--------- |  |<-----      |<---encrypt- response--| <-----------------content --|                               
 *   		|					    |							+--+
 *<content to object>   
 *
 * 
 * 
 *    proxy:
 *    
 *    
 *    
 *    
 *    
 * 
 * @author sparrow
 *
 */
public interface IProtocolProcessor {
	
	
	
	/**
	 * 
	 * 
	 * @param transCode
	 * @param fromCode
	 * @param toCode
	 * @param content
	 * @param response
	 * @return
	 * @throws ProtocolStorageException
	 */
	public boolean doProcess(String transCode,String fromCode, String toCode, String content, StringHolder response) throws ProtocolProcessException ;

	
	
	
}
