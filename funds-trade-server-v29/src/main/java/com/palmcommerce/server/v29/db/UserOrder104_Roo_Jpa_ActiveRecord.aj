// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.palmcommerce.server.v29.db;

import com.palmcommerce.server.v29.db.UserOrder104;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;

privileged aspect UserOrder104_Roo_Jpa_ActiveRecord {
    
    @PersistenceContext
    transient EntityManager UserOrder104.entityManager;
    
    public static final EntityManager UserOrder104.entityManager() {
        EntityManager em = new UserOrder104().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }
    
    public static long UserOrder104.countUserOrder104s() {
        return entityManager().createQuery("SELECT COUNT(o) FROM UserOrder104 o", Long.class).getSingleResult();
    }
    
    public static List<UserOrder104> UserOrder104.findAllUserOrder104s() {
        return entityManager().createQuery("SELECT o FROM UserOrder104 o", UserOrder104.class).getResultList();
    }
    
    public static UserOrder104 UserOrder104.findUserOrder104(String orderId) {
        if (orderId == null || orderId.length() == 0) return null;
        return entityManager().find(UserOrder104.class, orderId);
    }
    
    public static List<UserOrder104> UserOrder104.findUserOrder104Entries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM UserOrder104 o", UserOrder104.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    @Transactional
    public void UserOrder104.persist() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.persist(this);
    }
    
    @Transactional
    public void UserOrder104.remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            UserOrder104 attached = UserOrder104.findUserOrder104(this.orderId);
            this.entityManager.remove(attached);
        }
    }
    
    @Transactional
    public void UserOrder104.flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }
    
    @Transactional
    public void UserOrder104.clear() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.clear();
    }
    
    @Transactional
    public UserOrder104 UserOrder104.merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        UserOrder104 merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
    
}
