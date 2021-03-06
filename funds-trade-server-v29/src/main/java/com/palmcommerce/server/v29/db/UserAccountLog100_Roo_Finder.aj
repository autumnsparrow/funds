// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.palmcommerce.server.v29.db;

import com.palmcommerce.server.v29.db.UserAccountLog100;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

privileged aspect UserAccountLog100_Roo_Finder {
    
    public static TypedQuery<UserAccountLog100> UserAccountLog100.findUserAccountLog100sByForigenIdEquals(String forigenId) {
        if (forigenId == null || forigenId.length() == 0) throw new IllegalArgumentException("The forigenId argument is required");
        EntityManager em = UserAccountLog100.entityManager();
        TypedQuery<UserAccountLog100> q = em.createQuery("SELECT o FROM UserAccountLog100 AS o WHERE o.forigenId = :forigenId", UserAccountLog100.class);
        q.setParameter("forigenId", forigenId);
        return q;
    }
    
}
