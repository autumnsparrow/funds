// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.palmcommerce.server.v29.db;

import com.palmcommerce.server.v29.db.UserIdentify;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

privileged aspect UserIdentify_Roo_Jpa_Entity {
    
    declare @type: UserIdentify: @Entity;
    
    declare @type: UserIdentify: @Table(schema = "LOTTERY_V29_SX", name = "USER_IDENTIFY");
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "USER_ID", length = 30)
    private String UserIdentify.userId;
    
    public String UserIdentify.getUserId() {
        return this.userId;
    }
    
    public void UserIdentify.setUserId(String id) {
        this.userId = id;
    }
    
}