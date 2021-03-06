// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.palmcommerce.server.v29.db;

import com.palmcommerce.server.v29.db.UserStakeAccount;
import com.palmcommerce.server.v29.db.UserStakeAccountPK;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

privileged aspect UserStakeAccount_Roo_Jpa_Entity {
    
    declare @type: UserStakeAccount: @Entity;
    
    declare @type: UserStakeAccount: @Table(schema = "LOTTERY_V29_SX", name = "USER_STAKE_ACCOUNT");
    
    @EmbeddedId
    private UserStakeAccountPK UserStakeAccount.id;
    
    public UserStakeAccountPK UserStakeAccount.getId() {
        return this.id;
    }
    
    public void UserStakeAccount.setId(UserStakeAccountPK id) {
        this.id = id;
    }
    
}
