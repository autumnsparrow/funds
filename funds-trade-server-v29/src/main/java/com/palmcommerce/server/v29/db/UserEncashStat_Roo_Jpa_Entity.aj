// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.palmcommerce.server.v29.db;

import com.palmcommerce.server.v29.db.UserEncashStat;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

privileged aspect UserEncashStat_Roo_Jpa_Entity {
    
    declare @type: UserEncashStat: @Entity;
    
    declare @type: UserEncashStat: @Table(schema = "LOTTERY_V29_SX", name = "USER_ENCASH_STAT");
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "STAT_ID")
    private BigDecimal UserEncashStat.statId;
    
    public BigDecimal UserEncashStat.getStatId() {
        return this.statId;
    }
    
    public void UserEncashStat.setStatId(BigDecimal id) {
        this.statId = id;
    }
    
}