// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.palmcommerce.server.v29.db;

import com.palmcommerce.server.v29.db.UserAccount;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

privileged aspect UserAccount_Roo_Finder {
    
    public static TypedQuery<UserAccount> UserAccount.findUserAccountsByUserIdEquals(String userId) {
        if (userId == null || userId.length() == 0) throw new IllegalArgumentException("The userId argument is required");
        EntityManager em = UserAccount.entityManager();
        TypedQuery<UserAccount> q = em.createQuery("SELECT o FROM UserAccount AS o WHERE o.userId = :userId", UserAccount.class);
        q.setParameter("userId", userId);
        return q;
    }
    
}