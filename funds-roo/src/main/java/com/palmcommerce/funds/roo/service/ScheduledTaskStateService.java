package com.palmcommerce.funds.roo.service;

import java.util.Date;
import java.util.List;

import org.springframework.roo.addon.layers.service.RooService;

import com.palmcommerce.funds.roo.model.ScheduledTaskState;

@RooService(domainTypes = { com.palmcommerce.funds.roo.model.ScheduledTaskState.class })
public interface ScheduledTaskStateService {
	
	ScheduledTaskState findScheduledTaskStatesByBankDateEqualsAndBankCodeEquals(Date bankDate,String bankCode);

	List<ScheduledTaskState> findScheduledTaskStatesByBankDateEquals(Date bankDate);
	
	List<ScheduledTaskState> findScheduledTaskStatesByBankDateEqualsAndTranscodeEquals(Date bankDate,String transcode);
	
	List<ScheduledTaskState> findScheduledTaskStatesByBankDateEqualsAndIsBank(Date bankDate, Boolean isBank) ;

	ScheduledTaskState findScheduledTaskStatesByBankDateEqualsAndTradecodeEquals(Date bankDate, String tradecode);
}
