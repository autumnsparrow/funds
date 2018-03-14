// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.palmcommerce.funds.ca.model;

import com.palmcommerce.funds.ca.model.Cacrts;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

privileged aspect Cacrts_Roo_Finder {
    
    public static TypedQuery<Cacrts> Cacrts.findCacrtsesByNodecodeEquals(String nodecode) {
        if (nodecode == null || nodecode.length() == 0) throw new IllegalArgumentException("The nodecode argument is required");
        EntityManager em = Cacrts.entityManager();
        TypedQuery<Cacrts> q = em.createQuery("SELECT o FROM Cacrts AS o WHERE o.nodecode = :nodecode", Cacrts.class);
        q.setParameter("nodecode", nodecode);
        return q;
    }
    
}