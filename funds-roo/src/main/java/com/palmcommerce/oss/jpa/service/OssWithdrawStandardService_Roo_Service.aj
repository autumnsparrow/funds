// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.palmcommerce.oss.jpa.service;

import com.palmcommerce.oss.jpa.model.OssWithdrawStandard;
import com.palmcommerce.oss.jpa.service.OssWithdrawStandardService;
import java.math.BigDecimal;
import java.util.List;

privileged aspect OssWithdrawStandardService_Roo_Service {
    
    public abstract long OssWithdrawStandardService.countAllOssWithdrawStandards();    
    public abstract void OssWithdrawStandardService.deleteOssWithdrawStandard(OssWithdrawStandard ossWithdrawStandard);    
    public abstract OssWithdrawStandard OssWithdrawStandardService.findOssWithdrawStandard(BigDecimal id);    
    public abstract List<OssWithdrawStandard> OssWithdrawStandardService.findAllOssWithdrawStandards();    
    public abstract List<OssWithdrawStandard> OssWithdrawStandardService.findOssWithdrawStandardEntries(int firstResult, int maxResults);    
    public abstract void OssWithdrawStandardService.saveOssWithdrawStandard(OssWithdrawStandard ossWithdrawStandard);    
    public abstract OssWithdrawStandard OssWithdrawStandardService.updateOssWithdrawStandard(OssWithdrawStandard ossWithdrawStandard);    
}
