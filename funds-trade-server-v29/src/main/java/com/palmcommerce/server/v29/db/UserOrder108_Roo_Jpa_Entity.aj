// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.palmcommerce.server.v29.db;

import com.palmcommerce.server.v29.db.UserOrder108;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

privileged aspect UserOrder108_Roo_Jpa_Entity {
    
    declare @type: UserOrder108: @Entity;
    
    declare @type: UserOrder108: @Table(schema = "LOTTERY_V29_SX", name = "USER_ORDER108");
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ORDER_ID", length = 30)
    private String UserOrder108.orderId;
    
    public String UserOrder108.getOrderId() {
        return this.orderId;
    }
    
    public void UserOrder108.setOrderId(String id) {
        this.orderId = id;
    }
    
}