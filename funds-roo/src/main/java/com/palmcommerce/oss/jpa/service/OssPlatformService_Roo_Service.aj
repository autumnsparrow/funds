// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.palmcommerce.oss.jpa.service;

import com.palmcommerce.oss.jpa.model.OssPlatform;
import com.palmcommerce.oss.jpa.service.OssPlatformService;
import java.math.BigDecimal;
import java.util.List;

privileged aspect OssPlatformService_Roo_Service {
    
    public abstract long OssPlatformService.countAllOssPlatforms();    
    public abstract void OssPlatformService.deleteOssPlatform(OssPlatform ossPlatform);    
    public abstract OssPlatform OssPlatformService.findOssPlatform(BigDecimal id);    
    public abstract List<OssPlatform> OssPlatformService.findAllOssPlatforms();    
    public abstract List<OssPlatform> OssPlatformService.findOssPlatformEntries(int firstResult, int maxResults);    
    public abstract void OssPlatformService.saveOssPlatform(OssPlatform ossPlatform);    
    public abstract OssPlatform OssPlatformService.updateOssPlatform(OssPlatform ossPlatform);    
}
