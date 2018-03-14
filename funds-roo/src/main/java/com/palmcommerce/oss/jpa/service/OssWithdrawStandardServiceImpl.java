package com.palmcommerce.oss.jpa.service;

import java.util.List;

import com.palmcommerce.oss.jpa.model.OssWithdrawStandard;

public class OssWithdrawStandardServiceImpl implements OssWithdrawStandardService {
	
	@Override
	public List<OssWithdrawStandard> findOssWithdrawStandardsByPlatformIdEquals(
			String platformId) {
		// TODO Auto-generated method stub
		return OssWithdrawStandard.findOssWithdrawStandardsByPlatformIdEquals(platformId).getResultList();
	}
}
