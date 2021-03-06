// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.palmcommerce.funds.ca.model;

import com.palmcommerce.funds.ca.model.Cakeys;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

privileged aspect Cakeys_Roo_Finder {
    
    public static TypedQuery<Cakeys> Cakeys.findCakeysesByNodecodeEquals(String nodecode) {
        if (nodecode == null || nodecode.length() == 0) throw new IllegalArgumentException("The nodecode argument is required");
        EntityManager em = Cakeys.entityManager();
        TypedQuery<Cakeys> q = em.createQuery("SELECT o FROM Cakeys AS o WHERE o.nodecode = :nodecode", Cakeys.class);
        q.setParameter("nodecode", nodecode);
        return q;
    }
    
}
