package com.palmcommerce.oss.jpa.service;

import java.math.BigDecimal;
import java.util.List;

import com.palmcommerce.oss.jpa.model.OssAlert;

public class AlertServiceImpl implements AlertService {

	@Override
	public List<OssAlert> findOssAlertsByAlertTypeId(int alertTypeId) {
		return OssAlert.findOssAlertsByAlertTypeId(BigDecimal.valueOf(alertTypeId)).getResultList();
	}
}
