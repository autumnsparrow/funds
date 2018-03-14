// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.palmcommerce.oss.jpa.model;

import com.palmcommerce.oss.jpa.model.OssAlert;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

privileged aspect OssAlert_Roo_DbManaged {
    
    @Column(name = "ALERT_TYPE_ID")
    @NotNull
    private BigDecimal OssAlert.alertTypeId;
    
    @Column(name = "ALERT_NOTITY_ID")
    @NotNull
    private BigDecimal OssAlert.alertNotityId;
    
    @Column(name = "REMARKS", length = 200)
    private String OssAlert.remarks;
    
    @Column(name = "NOTICE_TIME")
    @NotNull
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(style = "M-")
    private Date OssAlert.noticeTime;
    
    @Column(name = "OPERATOR_NAME", length = 20)
    @NotNull
    private String OssAlert.operatorName;
    
    @Column(name = "OPERATOR_PHONE", length = 200)
    private String OssAlert.operatorPhone;
    
    public BigDecimal OssAlert.getAlertTypeId() {
        return alertTypeId;
    }
    
    public void OssAlert.setAlertTypeId(BigDecimal alertTypeId) {
        this.alertTypeId = alertTypeId;
    }
    
    public BigDecimal OssAlert.getAlertNotityId() {
        return alertNotityId;
    }
    
    public void OssAlert.setAlertNotityId(BigDecimal alertNotityId) {
        this.alertNotityId = alertNotityId;
    }
    
    public String OssAlert.getRemarks() {
        return remarks;
    }
    
    public void OssAlert.setRemarks(String remarks) {
        this.remarks = remarks;
    }
    
    public Date OssAlert.getNoticeTime() {
        return noticeTime;
    }
    
    public void OssAlert.setNoticeTime(Date noticeTime) {
        this.noticeTime = noticeTime;
    }
    
    public String OssAlert.getOperatorName() {
        return operatorName;
    }
    
    public void OssAlert.setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }
    
    public String OssAlert.getOperatorPhone() {
        return operatorPhone;
    }
    
    public void OssAlert.setOperatorPhone(String operatorPhone) {
        this.operatorPhone = operatorPhone;
    }
    
}