// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.palmcommerce.server.v29.db;

import com.palmcommerce.server.v29.db.UserAccountCancle;
import com.palmcommerce.server.v29.db.UserAccountCanclePK;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

privileged aspect UserAccountCancle_Roo_Jpa_Entity {
    
    declare @type: UserAccountCancle: @Entity;
    
    declare @type: UserAccountCancle: @Table(schema = "LOTTERY_V29_SX", name = "USER_ACCOUNT_CANCLE");
    
    @EmbeddedId
    private UserAccountCanclePK UserAccountCancle.id;
    
    public UserAccountCanclePK UserAccountCancle.getId() {
        return this.id;
    }
    
    public void UserAccountCancle.setId(UserAccountCanclePK id) {
        this.id = id;
    }
    
}
