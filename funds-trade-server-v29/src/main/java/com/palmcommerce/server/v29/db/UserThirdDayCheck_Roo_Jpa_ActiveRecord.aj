// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.palmcommerce.server.v29.db;

import com.palmcommerce.server.v29.db.UserThirdDayCheck;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;

privileged aspect UserThirdDayCheck_Roo_Jpa_ActiveRecord {
    
    @PersistenceContext
    transient EntityManager UserThirdDayCheck.entityManager;
    
    public static final EntityManager UserThirdDayCheck.entityManager() {
        EntityManager em = new UserThirdDayCheck().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }
    
    public static long UserThirdDayCheck.countUserThirdDayChecks() {
        return entityManager().createQuery("SELECT COUNT(o) FROM UserThirdDayCheck o", Long.class).getSingleResult();
    }
    
    public static List<UserThirdDayCheck> UserThirdDayCheck.findAllUserThirdDayChecks() {
        return entityManager().createQuery("SELECT o FROM UserThirdDayCheck o", UserThirdDayCheck.class).getResultList();
    }
    
    public static UserThirdDayCheck UserThirdDayCheck.findUserThirdDayCheck(String id) {
        if (id == null || id.length() == 0) return null;
        return entityManager().find(UserThirdDayCheck.class, id);
    }
    
    public static List<UserThirdDayCheck> UserThirdDayCheck.findUserThirdDayCheckEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM UserThirdDayCheck o", UserThirdDayCheck.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    @Transactional
    public void UserThirdDayCheck.persist() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.persist(this);
    }
    
    @Transactional
    public void UserThirdDayCheck.remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            UserThirdDayCheck attached = UserThirdDayCheck.findUserThirdDayCheck(this.id);
            this.entityManager.remove(attached);
        }
    }
    
    @Transactional
    public void UserThirdDayCheck.flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }
    
    @Transactional
    public void UserThirdDayCheck.clear() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.clear();
    }
    
    @Transactional
    public UserThirdDayCheck UserThirdDayCheck.merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        UserThirdDayCheck merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
    
}