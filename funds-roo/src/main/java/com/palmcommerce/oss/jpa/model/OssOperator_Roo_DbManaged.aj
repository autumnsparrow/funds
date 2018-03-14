// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.palmcommerce.oss.jpa.model;

import com.palmcommerce.oss.jpa.model.OssOperator;
import com.palmcommerce.oss.jpa.model.OssTransitionOperatorRole;
import java.util.Date;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.format.annotation.DateTimeFormat;

privileged aspect OssOperator_Roo_DbManaged {
    
    @OneToMany(mappedBy = "vc2LoginName", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private Set<OssTransitionOperatorRole> OssOperator.ossTransitionOperatorRoles;
    
    @Column(name = "VC2_OPERATOR_NAME", length = 32)
    private String OssOperator.vc2OperatorName;
    
    @Column(name = "VC2_OPERATOR_PWD", length = 32)
    private String OssOperator.vc2OperatorPwd;
    
    @Column(name = "VC2_DEPARTMENT", length = 32)
    private String OssOperator.vc2Department;
    
    @Column(name = "VC2_STATUS", length = 4)
    private String OssOperator.vc2Status;
    
    @Column(name = "VC2_COMPANY", length = 32)
    private String OssOperator.vc2Company;
    
    @Column(name = "VC2_PHONE", length = 32)
    private String OssOperator.vc2Phone;
    
    @Column(name = "DAT_CREATE_TIME")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(style = "M-")
    private Date OssOperator.datCreateTime;
    
    @Column(name = "VC2_POSITION", length = 32)
    private String OssOperator.vc2Position;
    
    public Set<OssTransitionOperatorRole> OssOperator.getOssTransitionOperatorRoles() {
        return ossTransitionOperatorRoles;
    }
    
    public void OssOperator.setOssTransitionOperatorRoles(Set<OssTransitionOperatorRole> ossTransitionOperatorRoles) {
        this.ossTransitionOperatorRoles = ossTransitionOperatorRoles;
    }
    
    public String OssOperator.getVc2OperatorName() {
        return vc2OperatorName;
    }
    
    public void OssOperator.setVc2OperatorName(String vc2OperatorName) {
        this.vc2OperatorName = vc2OperatorName;
    }
    
    public String OssOperator.getVc2OperatorPwd() {
        return vc2OperatorPwd;
    }
    
    public void OssOperator.setVc2OperatorPwd(String vc2OperatorPwd) {
        this.vc2OperatorPwd = vc2OperatorPwd;
    }
    
    public String OssOperator.getVc2Department() {
        return vc2Department;
    }
    
    public void OssOperator.setVc2Department(String vc2Department) {
        this.vc2Department = vc2Department;
    }
    
    public String OssOperator.getVc2Status() {
        return vc2Status;
    }
    
    public void OssOperator.setVc2Status(String vc2Status) {
        this.vc2Status = vc2Status;
    }
    
    public String OssOperator.getVc2Company() {
        return vc2Company;
    }
    
    public void OssOperator.setVc2Company(String vc2Company) {
        this.vc2Company = vc2Company;
    }
    
    public String OssOperator.getVc2Phone() {
        return vc2Phone;
    }
    
    public void OssOperator.setVc2Phone(String vc2Phone) {
        this.vc2Phone = vc2Phone;
    }
    
    public Date OssOperator.getDatCreateTime() {
        return datCreateTime;
    }
    
    public void OssOperator.setDatCreateTime(Date datCreateTime) {
        this.datCreateTime = datCreateTime;
    }
    
    public String OssOperator.getVc2Position() {
        return vc2Position;
    }
    
    public void OssOperator.setVc2Position(String vc2Position) {
        this.vc2Position = vc2Position;
    }
    
}
