package com.palmcommerce.oss.jpa.service;
import java.util.List;

import org.springframework.roo.addon.layers.service.RooService;

import com.palmcommerce.oss.jpa.model.OssWithdrawStandard;

@RooService(domainTypes = { com.palmcommerce.oss.jpa.model.OssWithdrawStandard.class })
public interface OssWithdrawStandardService {
	public List<OssWithdrawStandard> findOssWithdrawStandardsByPlatformIdEquals(
			String platformId);
}
