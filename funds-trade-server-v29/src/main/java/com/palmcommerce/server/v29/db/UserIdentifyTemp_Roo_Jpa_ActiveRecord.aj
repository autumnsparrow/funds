// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.palmcommerce.server.v29.db;

import com.palmcommerce.server.v29.db.UserIdentifyTemp;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;

privileged aspect UserIdentifyTemp_Roo_Jpa_ActiveRecord {
    
    @PersistenceContext
    transient EntityManager UserIdentifyTemp.entityManager;
    
    public static final EntityManager UserIdentifyTemp.entityManager() {
        EntityManager em = new UserIdentifyTemp().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }
    
    public static long UserIdentifyTemp.countUserIdentifyTemps() {
        return entityManager().createQuery("SELECT COUNT(o) FROM UserIdentifyTemp o", Long.class).getSingleResult();
    }
    
    public static List<UserIdentifyTemp> UserIdentifyTemp.findAllUserIdentifyTemps() {
        return entityManager().createQuery("SELECT o FROM UserIdentifyTemp o", UserIdentifyTemp.class).getResultList();
    }
    
    public static UserIdentifyTemp UserIdentifyTemp.findUserIdentifyTemp(String userId) {
        if (userId == null || userId.length() == 0) return null;
        return entityManager().find(UserIdentifyTemp.class, userId);
    }
    
    public static List<UserIdentifyTemp> UserIdentifyTemp.findUserIdentifyTempEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM UserIdentifyTemp o", UserIdentifyTemp.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    @Transactional
    public void UserIdentifyTemp.persist() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.persist(this);
    }
    
    @Transactional
    public void UserIdentifyTemp.remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            UserIdentifyTemp attached = UserIdentifyTemp.findUserIdentifyTemp(this.userId);
            this.entityManager.remove(attached);
        }
    }
    
    @Transactional
    public void UserIdentifyTemp.flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }
    
    @Transactional
    public void UserIdentifyTemp.clear() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.clear();
    }
    
    @Transactional
    public UserIdentifyTemp UserIdentifyTemp.merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        UserIdentifyTemp merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
    
}
