// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.palmcommerce.server.v29.db;

import com.palmcommerce.server.v29.db.UserAccountLog109;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

privileged aspect UserAccountLog109_Roo_Jpa_Entity {
    
    declare @type: UserAccountLog109: @Entity;
    
    declare @type: UserAccountLog109: @Table(schema = "LOTTERY_V29_SX", name = "USER_ACCOUNT_LOG109");
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "LOG_ID", length = 30)
    private String UserAccountLog109.logId;
    
    public String UserAccountLog109.getLogId() {
        return this.logId;
    }
    
    public void UserAccountLog109.setLogId(String id) {
        this.logId = id;
    }
    
}
