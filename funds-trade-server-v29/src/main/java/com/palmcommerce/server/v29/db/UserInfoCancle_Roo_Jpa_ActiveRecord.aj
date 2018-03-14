// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.palmcommerce.server.v29.db;

import com.palmcommerce.server.v29.db.UserInfoCancle;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;

privileged aspect UserInfoCancle_Roo_Jpa_ActiveRecord {
    
    @PersistenceContext
    transient EntityManager UserInfoCancle.entityManager;
    
    public static final EntityManager UserInfoCancle.entityManager() {
        EntityManager em = new UserInfoCancle().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }
    
    public static long UserInfoCancle.countUserInfoCancles() {
        return entityManager().createQuery("SELECT COUNT(o) FROM UserInfoCancle o", Long.class).getSingleResult();
    }
    
    public static List<UserInfoCancle> UserInfoCancle.findAllUserInfoCancles() {
        return entityManager().createQuery("SELECT o FROM UserInfoCancle o", UserInfoCancle.class).getResultList();
    }
    
    public static UserInfoCancle UserInfoCancle.findUserInfoCancle(String userId) {
        if (userId == null || userId.length() == 0) return null;
        return entityManager().find(UserInfoCancle.class, userId);
    }
    
    public static List<UserInfoCancle> UserInfoCancle.findUserInfoCancleEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM UserInfoCancle o", UserInfoCancle.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    @Transactional
    public void UserInfoCancle.persist() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.persist(this);
    }
    
    @Transactional
    public void UserInfoCancle.remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            UserInfoCancle attached = UserInfoCancle.findUserInfoCancle(this.userId);
            this.entityManager.remove(attached);
        }
    }
    
    @Transactional
    public void UserInfoCancle.flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }
    
    @Transactional
    public void UserInfoCancle.clear() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.clear();
    }
    
    @Transactional
    public UserInfoCancle UserInfoCancle.merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        UserInfoCancle merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
    
}
