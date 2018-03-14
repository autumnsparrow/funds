// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.palmcommerce.server.v29.service;

import com.palmcommerce.server.v29.db.UserIdentify;
import com.palmcommerce.server.v29.service.UserIdentifyServiceImpl;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

privileged aspect UserIdentifyServiceImpl_Roo_Service {
    
    declare @type: UserIdentifyServiceImpl: @Service;
    
    declare @type: UserIdentifyServiceImpl: @Transactional;
    
    public long UserIdentifyServiceImpl.countAllUserIdentifys() {
        return UserIdentify.countUserIdentifys();
    }
    
    public void UserIdentifyServiceImpl.deleteUserIdentify(UserIdentify userIdentify) {
        userIdentify.remove();
    }
    
    public UserIdentify UserIdentifyServiceImpl.findUserIdentify(String id) {
        return UserIdentify.findUserIdentify(id);
    }
    
    public List<UserIdentify> UserIdentifyServiceImpl.findAllUserIdentifys() {
        return UserIdentify.findAllUserIdentifys();
    }
    
    public List<UserIdentify> UserIdentifyServiceImpl.findUserIdentifyEntries(int firstResult, int maxResults) {
        return UserIdentify.findUserIdentifyEntries(firstResult, maxResults);
    }
    
    public void UserIdentifyServiceImpl.saveUserIdentify(UserIdentify userIdentify) {
        userIdentify.persist();
    }
    
    public UserIdentify UserIdentifyServiceImpl.updateUserIdentify(UserIdentify userIdentify) {
        return userIdentify.merge();
    }
    
}
