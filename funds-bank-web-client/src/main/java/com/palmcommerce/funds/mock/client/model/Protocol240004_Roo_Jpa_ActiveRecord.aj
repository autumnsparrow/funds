// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.palmcommerce.funds.mock.client.model;

import com.palmcommerce.funds.mock.client.model.Protocol240004;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

privileged aspect Protocol240004_Roo_Jpa_ActiveRecord {
    
    public static long Protocol240004.countProtocol240004s() {
        return entityManager().createQuery("SELECT COUNT(o) FROM Protocol240004 o", Long.class).getSingleResult();
    }
    
    public static List<Protocol240004> Protocol240004.findAllProtocol240004s() {
        return entityManager().createQuery("SELECT o FROM Protocol240004 o", Protocol240004.class).getResultList();
    }
    
    public static Protocol240004 Protocol240004.findProtocol240004(Long id) {
        if (id == null) return null;
        return entityManager().find(Protocol240004.class, id);
    }
    
    public static List<Protocol240004> Protocol240004.findProtocol240004Entries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM Protocol240004 o", Protocol240004.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    @Transactional
    public Protocol240004 Protocol240004.merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        Protocol240004 merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
    
}