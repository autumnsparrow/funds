// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.palmcommerce.server.v29.db;

import com.palmcommerce.server.v29.db.UserAccountLog101;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

privileged aspect UserAccountLog101_Roo_Jpa_Entity {
    
    declare @type: UserAccountLog101: @Entity;
    
    declare @type: UserAccountLog101: @Table(schema = "LOTTERY_V29_SX", name = "USER_ACCOUNT_LOG101");
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "LOG_ID", length = 30)
    private String UserAccountLog101.logId;
    
    public String UserAccountLog101.getLogId() {
        return this.logId;
    }
    
    public void UserAccountLog101.setLogId(String id) {
        this.logId = id;
    }
    
}
