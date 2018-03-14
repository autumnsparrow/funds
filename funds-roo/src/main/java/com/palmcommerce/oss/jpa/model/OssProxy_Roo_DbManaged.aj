// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.palmcommerce.oss.jpa.model;

import com.palmcommerce.oss.jpa.model.OssProxy;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.validation.constraints.NotNull;

privileged aspect OssProxy_Roo_DbManaged {
    
    @Column(name = "NAME", length = 50)
    @NotNull
    private String OssProxy.name;
    
    @Column(name = "IP", length = 100)
    @NotNull
    private String OssProxy.ip;
    
    @Column(name = "PROXYID", length = 20)
    private String OssProxy.proxyid;
    
    @Column(name = "MINPROT")
    private BigDecimal OssProxy.minprot;
    
    @Column(name = "MAXPROT")
    private BigDecimal OssProxy.maxprot;
    
    public String OssProxy.getName() {
        return name;
    }
    
    public void OssProxy.setName(String name) {
        this.name = name;
    }
    
    public String OssProxy.getIp() {
        return ip;
    }
    
    public void OssProxy.setIp(String ip) {
        this.ip = ip;
    }
    
    public String OssProxy.getProxyid() {
        return proxyid;
    }
    
    public void OssProxy.setProxyid(String proxyid) {
        this.proxyid = proxyid;
    }
    
    public BigDecimal OssProxy.getMinprot() {
        return minprot;
    }
    
    public void OssProxy.setMinprot(BigDecimal minprot) {
        this.minprot = minprot;
    }
    
    public BigDecimal OssProxy.getMaxprot() {
        return maxprot;
    }
    
    public void OssProxy.setMaxprot(BigDecimal maxprot) {
        this.maxprot = maxprot;
    }
    
}
