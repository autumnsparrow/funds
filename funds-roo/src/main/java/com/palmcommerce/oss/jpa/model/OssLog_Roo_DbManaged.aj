// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.palmcommerce.oss.jpa.model;

import com.palmcommerce.oss.jpa.model.OssLog;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

privileged aspect OssLog_Roo_DbManaged {
    
    @Column(name = "OPRTATOR_NAME", length = 20)
    @NotNull
    private String OssLog.oprtatorName;
    
    @Column(name = "CONTENT", length = 200)
    @NotNull
    private String OssLog.content;
    
    @Column(name = "OPERATOR_TIME")
    @NotNull
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(style = "M-")
    private Date OssLog.operatorTime;
    
    @Column(name = "OPERATOR_TYPE")
    private BigDecimal OssLog.operatorType;
    
    public String OssLog.getOprtatorName() {
        return oprtatorName;
    }
    
    public void OssLog.setOprtatorName(String oprtatorName) {
        this.oprtatorName = oprtatorName;
    }
    
    public String OssLog.getContent() {
        return content;
    }
    
    public void OssLog.setContent(String content) {
        this.content = content;
    }
    
    public Date OssLog.getOperatorTime() {
        return operatorTime;
    }
    
    public void OssLog.setOperatorTime(Date operatorTime) {
        this.operatorTime = operatorTime;
    }
    
    public BigDecimal OssLog.getOperatorType() {
        return operatorType;
    }
    
    public void OssLog.setOperatorType(BigDecimal operatorType) {
        this.operatorType = operatorType;
    }
    
}
