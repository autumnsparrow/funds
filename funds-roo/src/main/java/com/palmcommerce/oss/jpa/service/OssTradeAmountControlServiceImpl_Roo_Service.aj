// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.palmcommerce.oss.jpa.service;

import com.palmcommerce.oss.jpa.model.OssTradeAmountControl;
import com.palmcommerce.oss.jpa.service.OssTradeAmountControlServiceImpl;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

privileged aspect OssTradeAmountControlServiceImpl_Roo_Service {
    
    declare @type: OssTradeAmountControlServiceImpl: @Service;
    
    declare @type: OssTradeAmountControlServiceImpl: @Transactional;
    
    public long OssTradeAmountControlServiceImpl.countAllOssTradeAmountControls() {
        return OssTradeAmountControl.countOssTradeAmountControls();
    }
    
    public void OssTradeAmountControlServiceImpl.deleteOssTradeAmountControl(OssTradeAmountControl ossTradeAmountControl) {
        ossTradeAmountControl.remove();
    }
    
    public OssTradeAmountControl OssTradeAmountControlServiceImpl.findOssTradeAmountControl(BigDecimal id) {
        return OssTradeAmountControl.findOssTradeAmountControl(id);
    }
    
    public List<OssTradeAmountControl> OssTradeAmountControlServiceImpl.findAllOssTradeAmountControls() {
        return OssTradeAmountControl.findAllOssTradeAmountControls();
    }
    
    public List<OssTradeAmountControl> OssTradeAmountControlServiceImpl.findOssTradeAmountControlEntries(int firstResult, int maxResults) {
        return OssTradeAmountControl.findOssTradeAmountControlEntries(firstResult, maxResults);
    }
    
    public void OssTradeAmountControlServiceImpl.saveOssTradeAmountControl(OssTradeAmountControl ossTradeAmountControl) {
        ossTradeAmountControl.persist();
    }
    
    public OssTradeAmountControl OssTradeAmountControlServiceImpl.updateOssTradeAmountControl(OssTradeAmountControl ossTradeAmountControl) {
        return ossTradeAmountControl.merge();
    }
    
}
