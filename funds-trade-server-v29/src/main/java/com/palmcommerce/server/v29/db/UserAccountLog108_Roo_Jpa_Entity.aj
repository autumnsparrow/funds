// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.palmcommerce.server.v29.db;

import com.palmcommerce.server.v29.db.UserAccountLog108;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

privileged aspect UserAccountLog108_Roo_Jpa_Entity {
    
    declare @type: UserAccountLog108: @Entity;
    
    declare @type: UserAccountLog108: @Table(schema = "LOTTERY_V29_SX", name = "USER_ACCOUNT_LOG108");
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "LOG_ID", length = 30)
    private String UserAccountLog108.logId;
    
    public String UserAccountLog108.getLogId() {
        return this.logId;
    }
    
    public void UserAccountLog108.setLogId(String id) {
        this.logId = id;
    }
    
}
