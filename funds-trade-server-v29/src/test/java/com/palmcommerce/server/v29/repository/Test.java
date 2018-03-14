package com.palmcommerce.server.v29.repository;

import java.util.List;

import com.palmcommerce.funds.service.ProtocolStorageException;
import com.palmcommerce.funds.util.SpringHelper;
import com.palmcommerce.server.v29.db.UserAccount;
import com.palmcommerce.server.v29.service.FundPaymentPlatformService;
import com.palmcommerce.server.v29.service.UserAccountService;

public class Test {

	public Test() {
		// TODO Auto-generated constructor stub
	}

	private static void log(String msg){
		System.out.println(msg);
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SpringHelper.init(new String[]{
    			
    			"classpath:META-INF/spring/applicationContext-funds-id.xml",
    			"classpath:META-INF/spring/applicationContext-funds-packet.xml",
    			"classpath:META-INF/spring/applicationContext-funds-ice-metrics.xml",
    			"classpath:META-INF/spring/applicationContext-funds-bank.xml",
    			"classpath:META-INF/spring/applicationContext.xml",
    			"classpath:META-INF/spring/applicationContext-funds-trade-connection.xml",
    			"classpath:META-INF/spring/applicationContext-funds-trade-ice-server.xml",
    			
    	});
		
		UserAccountService userAccountService=SpringHelper.getBean("userAccountService");
		
		
		List<UserAccount> userAccounts=userAccountService.findUserAccountByUserId("131225114632100003");
	
		for(UserAccount userAccount:userAccounts){
			log(userAccount.toString());
		}
		
		FundPaymentPlatformService fundPaymentPlatformService=SpringHelper.getBean("fundPaymentPlatformService");
		double payMoney=0.23;
		String userName="SomeBody";
		String userId="131225114632100003";
		String bankDate="20131230";
		String bankCode="B0000001";
		String transactionTime="2013-12-30 12:29:30";
		String paymentTerminalType="03";
		String orderId="44131230122932-000000";
		try {
			fundPaymentPlatformService.chargeByBank(bankCode, bankDate, transactionTime, paymentTerminalType, orderId, userId, userName, payMoney);
		} catch (ProtocolStorageException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}

}
