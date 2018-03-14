package com.palmcommerce.funds.roo.service;

import java.util.Date;
import java.util.List;

import com.palmcommerce.funds.roo.model.ScheduledTaskState;


public class ScheduledTaskStateServiceImpl implements ScheduledTaskStateService {

	/* (non-Javadoc)
	 * @see com.palmcommerce.funds.roo.service.ScheduledTaskStateService#findScheduledTaskStatesByBankDateEqualsAndTranscodeEquals(java.util.Date, java.lang.String)
	 */
	@Override
	public List<ScheduledTaskState> findScheduledTaskStatesByBankDateEqualsAndTranscodeEquals(
			Date bankDate, String transcode) {
		// TODO Auto-generated method stub
		return ScheduledTaskState.findScheduledTaskStatesByBankDateEqualsAndTranscodeEquals(bankDate,transcode).getResultList();
	}

	/* (non-Javadoc)
	 * @see com.palmcommerce.funds.roo.service.ScheduledTaskStateService#findScheduledTaskStatesByBankDateEqualsAndIsBankNot(java.util.Date, java.lang.Boolean)
	 */
	@Override
	public List<ScheduledTaskState> findScheduledTaskStatesByBankDateEqualsAndIsBank(
			Date bankDate, Boolean isBank) {
		// TODO Auto-generated method stub
		return ScheduledTaskState.findScheduledTaskStatesByBankDateEqualsAndIsBank(bankDate,isBank?1:0).getResultList();
	}

	/* (non-Javadoc)
	 * @see com.palmcommerce.funds.roo.service.ScheduledTaskStateService#findScheduledTaskStatesByBankDateEqualsAndTradecodeEquals(java.util.Date, java.lang.String)
	 */
	@Override
	public ScheduledTaskState findScheduledTaskStatesByBankDateEqualsAndTradecodeEquals(
			Date bankDate, String tradecode) {
		// TODO Auto-generated method stub
		return ScheduledTaskState.findScheduledTaskStatesByBankDateEqualsAndTradecodeEquals(bankDate,tradecode).getSingleResult();
	}

	/* (non-Javadoc)
	 * @see com.palmcommerce.funds.roo.service.ScheduledTaskStateService#findScheduledTaskStatesByBankDateEqualsAndBankCodeEquals(java.util.Date, java.lang.String)
	 */
	@Override
	public ScheduledTaskState findScheduledTaskStatesByBankDateEqualsAndBankCodeEquals(
			Date bankDate, String bankCode) {
		// TODO Auto-generated method stub
		return ScheduledTaskState.findScheduledTaskStatesByBankDateEqualsAndBankCodeEquals(bankDate, bankCode).getSingleResult();
	}

	/* (non-Javadoc)
	 * @see com.palmcommerce.funds.roo.service.ScheduledTaskStateService#findScheduledTaskStatesByBankDateEquals(java.util.Date)
	 */
	@Override
	public List<ScheduledTaskState> findScheduledTaskStatesByBankDateEquals(
			Date bankDate) {
		// TODO Auto-generated method stub
		return ScheduledTaskState.findScheduledTaskStatesByBankDateEquals(bankDate).getResultList();
	}

	
	
	
	

}
