// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.palmcommerce.server.v29.service;

import com.palmcommerce.server.v29.db.UserIdentify;
import com.palmcommerce.server.v29.service.UserIdentifyService;
import java.util.List;

privileged aspect UserIdentifyService_Roo_Service {
    
    public abstract long UserIdentifyService.countAllUserIdentifys();    
    public abstract void UserIdentifyService.deleteUserIdentify(UserIdentify userIdentify);    
    public abstract UserIdentify UserIdentifyService.findUserIdentify(String id);    
    public abstract List<UserIdentify> UserIdentifyService.findAllUserIdentifys();    
    public abstract List<UserIdentify> UserIdentifyService.findUserIdentifyEntries(int firstResult, int maxResults);    
    public abstract void UserIdentifyService.saveUserIdentify(UserIdentify userIdentify);    
    public abstract UserIdentify UserIdentifyService.updateUserIdentify(UserIdentify userIdentify);    
}