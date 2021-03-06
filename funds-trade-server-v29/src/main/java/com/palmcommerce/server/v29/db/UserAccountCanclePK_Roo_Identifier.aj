// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.palmcommerce.server.v29.db;

import com.palmcommerce.server.v29.db.UserAccountCanclePK;
import javax.persistence.Column;
import javax.persistence.Embeddable;

privileged aspect UserAccountCanclePK_Roo_Identifier {
    
    declare @type: UserAccountCanclePK: @Embeddable;
    
    @Column(name = "USER_ID", nullable = false, length = 30)
    private String UserAccountCanclePK.userId;
    
    @Column(name = "PARTNER_ID", nullable = false, length = 20)
    private String UserAccountCanclePK.partnerId;
    
    public UserAccountCanclePK.new(String userId, String partnerId) {
        super();
        this.userId = userId;
        this.partnerId = partnerId;
    }

    private UserAccountCanclePK.new() {
        super();
    }

    public String UserAccountCanclePK.getUserId() {
        return userId;
    }
    
    public String UserAccountCanclePK.getPartnerId() {
        return partnerId;
    }
    
}
