// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.palmcommerce.server.v29.db;

import com.palmcommerce.server.v29.db.UserOrder107;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

privileged aspect UserOrder107_Roo_Jpa_Entity {
    
    declare @type: UserOrder107: @Entity;
    
    declare @type: UserOrder107: @Table(schema = "LOTTERY_V29_SX", name = "USER_ORDER107");
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ORDER_ID", length = 30)
    private String UserOrder107.orderId;
    
    public String UserOrder107.getOrderId() {
        return this.orderId;
    }
    
    public void UserOrder107.setOrderId(String id) {
        this.orderId = id;
    }
    
}
