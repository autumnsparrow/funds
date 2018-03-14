package com.palmcommerce.funds.id.service;

import org.springframework.roo.addon.layers.service.RooService;

@RooService(domainTypes = { com.palmcommerce.funds.id.model.LocalAndRemoteSerialRelation.class })
public interface LocalAndRemoteSerialRelationService {
	
	public String findLocalSerialByRemoteSerial(String remotSerial);
	public String findRemoteSerialByLocalSerial(String localSerial);
}
