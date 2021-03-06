// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.palmcommerce.server.v29.db;

import com.palmcommerce.server.v29.db.UserOrder103;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;

privileged aspect UserOrder103_Roo_Jpa_ActiveRecord {
    
    @PersistenceContext
    transient EntityManager UserOrder103.entityManager;
    
    public static final EntityManager UserOrder103.entityManager() {
        EntityManager em = new UserOrder103().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }
    
    public static long UserOrder103.countUserOrder103s() {
        return entityManager().createQuery("SELECT COUNT(o) FROM UserOrder103 o", Long.class).getSingleResult();
    }
    
    public static List<UserOrder103> UserOrder103.findAllUserOrder103s() {
        return entityManager().createQuery("SELECT o FROM UserOrder103 o", UserOrder103.class).getResultList();
    }
    
    public static UserOrder103 UserOrder103.findUserOrder103(String orderId) {
        if (orderId == null || orderId.length() == 0) return null;
        return entityManager().find(UserOrder103.class, orderId);
    }
    
    public static List<UserOrder103> UserOrder103.findUserOrder103Entries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM UserOrder103 o", UserOrder103.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    @Transactional
    public void UserOrder103.persist() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.persist(this);
    }
    
    @Transactional
    public void UserOrder103.remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            UserOrder103 attached = UserOrder103.findUserOrder103(this.orderId);
            this.entityManager.remove(attached);
        }
    }
    
    @Transactional
    public void UserOrder103.flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }
    
    @Transactional
    public void UserOrder103.clear() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.clear();
    }
    
    @Transactional
    public UserOrder103 UserOrder103.merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        UserOrder103 merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
    
}
