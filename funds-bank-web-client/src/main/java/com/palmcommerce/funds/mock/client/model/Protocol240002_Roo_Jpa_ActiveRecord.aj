// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.palmcommerce.funds.mock.client.model;

import com.palmcommerce.funds.mock.client.model.Protocol240002;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

privileged aspect Protocol240002_Roo_Jpa_ActiveRecord {
    
    public static long Protocol240002.countProtocol240002s() {
        return entityManager().createQuery("SELECT COUNT(o) FROM Protocol240002 o", Long.class).getSingleResult();
    }
    
    public static List<Protocol240002> Protocol240002.findAllProtocol240002s() {
        return entityManager().createQuery("SELECT o FROM Protocol240002 o", Protocol240002.class).getResultList();
    }
    
    public static Protocol240002 Protocol240002.findProtocol240002(Long id) {
        if (id == null) return null;
        return entityManager().find(Protocol240002.class, id);
    }
    
    public static List<Protocol240002> Protocol240002.findProtocol240002Entries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM Protocol240002 o", Protocol240002.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    @Transactional
    public Protocol240002 Protocol240002.merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        Protocol240002 merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
    
}