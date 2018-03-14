package com.palmcommerce.funds.ice.trade.client;

import org.springframework.aop.framework.adapter.GlobalAdvisorAdapterRegistry;

import Ice.StringHolder;

import com.palmcommerce.funds.id.impl.GlobalIdGenerator;
import com.palmcommerce.funds.protocol.ProtocolDriverManager;
import com.palmcommerce.funds.protocol.covertor.exception.ProtocolConvertorException;
import com.palmcommerce.funds.protocol.impl.StateMessage;
import com.palmcommerce.funds.protocol.impl.t2p.T250004;
import com.palmcommerce.funds.service.ProtocolProcessException;
import com.palmcommerce.funds.service.ProtocolProcessorPrx;
import com.palmcommerce.funds.util.DateConvertor;
import com.palmcommerce.funds.util.IceProxyManager;
import com.palmcommerce.funds.util.SpringHelper;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	SpringHelper.init(new String[]{
    			
    			"classpath:META-INF/spring/applicationContext-funds-trade-client.xml",
    			 "classpath:META-INF/spring/applicationContext-funds-ice-proxy.xml",
    			 "classpath:META-INF/spring/applicationContext-funds-id.xml",
    			
    	});
    	GlobalIdGenerator globalIdGenerator=SpringHelper.getBean("globalIdGenerator");
    	
    	globalIdGenerator.start();
    	
    	IceProxyManager proxyManager=SpringHelper.getBean("iceProxyManager");
    	ProtocolProcessorPrx prx= proxyManager.getProcessorPrx();
    	StringHolder response=new StringHolder();
    	T250004 t=(T250004) ProtocolDriverManager.instance("250004", "T0000004", "B0000001");
    	t.request.setAccount("399292992");
    	t.request.setAccountTime(DateConvertor.getBankTime());
    	//t.request.setCenterNumber(globalIdGenerator.nextSerialId());
    	t.request.setPaymentType("01");
    	t.request.setTransactionTime(DateConvertor.getTradeTime());
    	
    	t.request.setPaymentAmount("100");// fen
    	t.request.setUserId("40000001");
    	t.request.setUserName("测试");
    	try {
			t.mashall(true);
			prx.doProcess(String.valueOf(t.getHeader().transcode), t.getHeader().from, t.getHeader().to, t.getRequestPacket(), response);
			t.setResponsePacket(response.value);
			System.out.println(response.value);
		//	StateMessage  state=(StateMessage)t.response;
		
			t.unmashall(false);
			
		} catch (ProtocolProcessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ProtocolConvertorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
