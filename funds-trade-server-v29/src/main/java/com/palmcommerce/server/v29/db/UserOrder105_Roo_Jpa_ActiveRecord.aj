// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.palmcommerce.server.v29.db;

import com.palmcommerce.server.v29.db.UserOrder105;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;

privileged aspect UserOrder105_Roo_Jpa_ActiveRecord {
    
    @PersistenceContext
    transient EntityManager UserOrder105.entityManager;
    
    public static final EntityManager UserOrder105.entityManager() {
        EntityManager em = new UserOrder105().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }
    
    public static long UserOrder105.countUserOrder105s() {
        return entityManager().createQuery("SELECT COUNT(o) FROM UserOrder105 o", Long.class).getSingleResult();
    }
    
    public static List<UserOrder105> UserOrder105.findAllUserOrder105s() {
        return entityManager().createQuery("SELECT o FROM UserOrder105 o", UserOrder105.class).getResultList();
    }
    
    public static UserOrder105 UserOrder105.findUserOrder105(String orderId) {
        if (orderId == null || orderId.length() == 0) return null;
        return entityManager().find(UserOrder105.class, orderId);
    }
    
    public static List<UserOrder105> UserOrder105.findUserOrder105Entries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM UserOrder105 o", UserOrder105.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    @Transactional
    public void UserOrder105.persist() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.persist(this);
    }
    
    @Transactional
    public void UserOrder105.remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            UserOrder105 attached = UserOrder105.findUserOrder105(this.orderId);
            this.entityManager.remove(attached);
        }
    }
    
    @Transactional
    public void UserOrder105.flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }
    
    @Transactional
    public void UserOrder105.clear() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.clear();
    }
    
    @Transactional
    public UserOrder105 UserOrder105.merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        UserOrder105 merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
    
}