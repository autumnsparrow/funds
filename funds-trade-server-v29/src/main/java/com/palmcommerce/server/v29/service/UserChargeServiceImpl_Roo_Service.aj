// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.palmcommerce.server.v29.service;

import com.palmcommerce.server.v29.db.UserCharge;
import com.palmcommerce.server.v29.service.UserChargeServiceImpl;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

privileged aspect UserChargeServiceImpl_Roo_Service {
    
    declare @type: UserChargeServiceImpl: @Service;
    
    declare @type: UserChargeServiceImpl: @Transactional;
    
    public long UserChargeServiceImpl.countAllUserCharges() {
        return UserCharge.countUserCharges();
    }
    
    public void UserChargeServiceImpl.deleteUserCharge(UserCharge userCharge) {
        userCharge.remove();
    }
    
    public UserCharge UserChargeServiceImpl.findUserCharge(String id) {
        return UserCharge.findUserCharge(id);
    }
    
    public List<UserCharge> UserChargeServiceImpl.findAllUserCharges() {
        return UserCharge.findAllUserCharges();
    }
    
    public List<UserCharge> UserChargeServiceImpl.findUserChargeEntries(int firstResult, int maxResults) {
        return UserCharge.findUserChargeEntries(firstResult, maxResults);
    }
    
    public void UserChargeServiceImpl.saveUserCharge(UserCharge userCharge) {
        userCharge.persist();
    }
    
    public UserCharge UserChargeServiceImpl.updateUserCharge(UserCharge userCharge) {
        return userCharge.merge();
    }
    
}
