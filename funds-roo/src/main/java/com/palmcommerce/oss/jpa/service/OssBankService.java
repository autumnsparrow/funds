package com.palmcommerce.oss.jpa.service;

import java.util.List;

import org.springframework.roo.addon.layers.service.RooService;

import com.palmcommerce.oss.jpa.model.OssBank;

@RooService(domainTypes = { com.palmcommerce.oss.jpa.model.OssBank.class })
public interface OssBankService {
	public  List<OssBank> findOssBanksByBankIdEquals(String bankId) ;
}
