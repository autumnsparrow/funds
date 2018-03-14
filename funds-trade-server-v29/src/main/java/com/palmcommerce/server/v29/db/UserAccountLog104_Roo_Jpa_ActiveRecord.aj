// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.palmcommerce.server.v29.db;

import com.palmcommerce.server.v29.db.UserAccountLog104;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;

privileged aspect UserAccountLog104_Roo_Jpa_ActiveRecord {
    
    @PersistenceContext
    transient EntityManager UserAccountLog104.entityManager;
    
    public static final EntityManager UserAccountLog104.entityManager() {
        EntityManager em = new UserAccountLog104().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }
    
    public static long UserAccountLog104.countUserAccountLog104s() {
        return entityManager().createQuery("SELECT COUNT(o) FROM UserAccountLog104 o", Long.class).getSingleResult();
    }
    
    public static List<UserAccountLog104> UserAccountLog104.findAllUserAccountLog104s() {
        return entityManager().createQuery("SELECT o FROM UserAccountLog104 o", UserAccountLog104.class).getResultList();
    }
    
    public static UserAccountLog104 UserAccountLog104.findUserAccountLog104(String logId) {
        if (logId == null || logId.length() == 0) return null;
        return entityManager().find(UserAccountLog104.class, logId);
    }
    
    public static List<UserAccountLog104> UserAccountLog104.findUserAccountLog104Entries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM UserAccountLog104 o", UserAccountLog104.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    @Transactional
    public void UserAccountLog104.persist() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.persist(this);
    }
    
    @Transactional
    public void UserAccountLog104.remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            UserAccountLog104 attached = UserAccountLog104.findUserAccountLog104(this.logId);
            this.entityManager.remove(attached);
        }
    }
    
    @Transactional
    public void UserAccountLog104.flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }
    
    @Transactional
    public void UserAccountLog104.clear() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.clear();
    }
    
    @Transactional
    public UserAccountLog104 UserAccountLog104.merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        UserAccountLog104 merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
    
}