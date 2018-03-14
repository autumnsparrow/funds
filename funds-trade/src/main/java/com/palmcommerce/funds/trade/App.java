package com.palmcommerce.funds.trade;

import com.palmcommerce.funds.connection.mina.ctx.MinaContext;
import com.palmcommerce.funds.id.impl.GlobalIdGenerator;
import com.palmcommerce.funds.packet.process.IProtocolProcessor;
import com.palmcommerce.funds.util.SpringHelper;

public class App {

	public static void main(String[] args) {
		SpringHelper.init(new String[]{
				"classpath:META-INF/spring/applicationContext-funds-trade-ibatis.xml"
		});
		
		final GlobalIdGenerator generator = SpringHelper.getBean("globalIdGenerator");
		IProtocolProcessor tradeServerProtocolHandler = SpringHelper
				.getBean("oldTradeClientProtocolHandler");
		MinaContext minaContext = SpringHelper.getBean("minaContext");
		minaContext.setServerSideProtocolProcessor(tradeServerProtocolHandler);
		minaContext.startWithAcceptorOrConnectorMode();

	}

}
