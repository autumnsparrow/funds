package com.palmcommerce.oss.jpa.service;
import java.util.List;

import org.springframework.roo.addon.layers.service.RooService;

import com.palmcommerce.oss.jpa.model.OssAlert;

@RooService(domainTypes = { com.palmcommerce.oss.jpa.model.OssAlert.class })
public interface AlertService {
	public List<OssAlert> findOssAlertsByAlertTypeId(int alertTypeId);
}
