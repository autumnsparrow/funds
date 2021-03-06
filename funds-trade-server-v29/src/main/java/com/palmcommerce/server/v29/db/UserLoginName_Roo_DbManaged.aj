// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.palmcommerce.server.v29.db;

import com.palmcommerce.server.v29.db.UserLoginName;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

privileged aspect UserLoginName_Roo_DbManaged {
    
    @Column(name = "USER_ID", length = 30)
    private String UserLoginName.userId;
    
    @Column(name = "STATUS")
    @NotNull
    private BigDecimal UserLoginName.status;
    
    @Column(name = "CREATE_TIME")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(style = "M-")
    private Date UserLoginName.createTime;
    
    public String UserLoginName.getUserId() {
        return userId;
    }
    
    public void UserLoginName.setUserId(String userId) {
        this.userId = userId;
    }
    
    public BigDecimal UserLoginName.getStatus() {
        return status;
    }
    
    public void UserLoginName.setStatus(BigDecimal status) {
        this.status = status;
    }
    
    public Date UserLoginName.getCreateTime() {
        return createTime;
    }
    
    public void UserLoginName.setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    
}
