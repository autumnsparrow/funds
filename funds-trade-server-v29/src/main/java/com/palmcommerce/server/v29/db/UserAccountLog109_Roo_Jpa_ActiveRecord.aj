// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.palmcommerce.server.v29.db;

import com.palmcommerce.server.v29.db.UserAccountLog109;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;

privileged aspect UserAccountLog109_Roo_Jpa_ActiveRecord {
    
    @PersistenceContext
    transient EntityManager UserAccountLog109.entityManager;
    
    public static final EntityManager UserAccountLog109.entityManager() {
        EntityManager em = new UserAccountLog109().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }
    
    public static long UserAccountLog109.countUserAccountLog109s() {
        return entityManager().createQuery("SELECT COUNT(o) FROM UserAccountLog109 o", Long.class).getSingleResult();
    }
    
    public static List<UserAccountLog109> UserAccountLog109.findAllUserAccountLog109s() {
        return entityManager().createQuery("SELECT o FROM UserAccountLog109 o", UserAccountLog109.class).getResultList();
    }
    
    public static UserAccountLog109 UserAccountLog109.findUserAccountLog109(String logId) {
        if (logId == null || logId.length() == 0) return null;
        return entityManager().find(UserAccountLog109.class, logId);
    }
    
    public static List<UserAccountLog109> UserAccountLog109.findUserAccountLog109Entries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM UserAccountLog109 o", UserAccountLog109.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    @Transactional
    public void UserAccountLog109.persist() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.persist(this);
    }
    
    @Transactional
    public void UserAccountLog109.remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            UserAccountLog109 attached = UserAccountLog109.findUserAccountLog109(this.logId);
            this.entityManager.remove(attached);
        }
    }
    
    @Transactional
    public void UserAccountLog109.flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }
    
    @Transactional
    public void UserAccountLog109.clear() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.clear();
    }
    
    @Transactional
    public UserAccountLog109 UserAccountLog109.merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        UserAccountLog109 merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
    
}
