package com.palmcommerce.oss.jpa.service;

import java.util.List;

import com.palmcommerce.oss.jpa.model.OssTradeAmountControl;

public class OssTradeAmountControlServiceImpl implements OssTradeAmountControlService {

	@Override
	public List<OssTradeAmountControl> findOssTradeAmountControlsByPlatformIdEqualsOrBankIdEquals(
			String platformId, String bankId) {
		// TODO Auto-generated method stub
		return OssTradeAmountControl.findOssTradeAmountControlsByPlatformIdEqualsOrBankIdEquals(platformId,bankId).getResultList();
	}
}
