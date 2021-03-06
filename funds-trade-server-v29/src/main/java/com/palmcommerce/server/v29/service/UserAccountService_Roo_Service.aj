// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.palmcommerce.server.v29.service;

import com.palmcommerce.server.v29.db.UserAccount;
import com.palmcommerce.server.v29.db.UserAccountCanclePK;
import com.palmcommerce.server.v29.service.UserAccountService;
import java.util.List;

privileged aspect UserAccountService_Roo_Service {
    
    public abstract long UserAccountService.countAllUserAccounts();    
    public abstract void UserAccountService.deleteUserAccount(UserAccount userAccount);    
    public abstract UserAccount UserAccountService.findUserAccount(UserAccountCanclePK id);    
    public abstract List<UserAccount> UserAccountService.findAllUserAccounts();    
    public abstract List<UserAccount> UserAccountService.findUserAccountEntries(int firstResult, int maxResults);    
    public abstract void UserAccountService.saveUserAccount(UserAccount userAccount);    
    public abstract UserAccount UserAccountService.updateUserAccount(UserAccount userAccount);    
}
