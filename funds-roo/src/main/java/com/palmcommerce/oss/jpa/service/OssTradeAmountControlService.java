package com.palmcommerce.oss.jpa.service;
import java.util.List;


import org.springframework.roo.addon.layers.service.RooService;

import com.palmcommerce.oss.jpa.model.OssTradeAmountControl;

@RooService(domainTypes = { com.palmcommerce.oss.jpa.model.OssTradeAmountControl.class })
public interface OssTradeAmountControlService {
	public List<OssTradeAmountControl> findOssTradeAmountControlsByPlatformIdEqualsOrBankIdEquals(String platformId, String bankId);
}
