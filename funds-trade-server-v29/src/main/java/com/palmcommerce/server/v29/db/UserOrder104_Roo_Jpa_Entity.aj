// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.palmcommerce.server.v29.db;

import com.palmcommerce.server.v29.db.UserOrder104;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

privileged aspect UserOrder104_Roo_Jpa_Entity {
    
    declare @type: UserOrder104: @Entity;
    
    declare @type: UserOrder104: @Table(schema = "LOTTERY_V29_SX", name = "USER_ORDER104");
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ORDER_ID", length = 30)
    private String UserOrder104.orderId;
    
    public String UserOrder104.getOrderId() {
        return this.orderId;
    }
    
    public void UserOrder104.setOrderId(String id) {
        this.orderId = id;
    }
    
}
