// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.palmcommerce.server.v29.db;

import com.palmcommerce.server.v29.db.UserEncashStat;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;

privileged aspect UserEncashStat_Roo_Jpa_ActiveRecord {
    
    @PersistenceContext
    transient EntityManager UserEncashStat.entityManager;
    
    public static final EntityManager UserEncashStat.entityManager() {
        EntityManager em = new UserEncashStat().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }
    
    public static long UserEncashStat.countUserEncashStats() {
        return entityManager().createQuery("SELECT COUNT(o) FROM UserEncashStat o", Long.class).getSingleResult();
    }
    
    public static List<UserEncashStat> UserEncashStat.findAllUserEncashStats() {
        return entityManager().createQuery("SELECT o FROM UserEncashStat o", UserEncashStat.class).getResultList();
    }
    
    public static UserEncashStat UserEncashStat.findUserEncashStat(BigDecimal statId) {
        if (statId == null) return null;
        return entityManager().find(UserEncashStat.class, statId);
    }
    
    public static List<UserEncashStat> UserEncashStat.findUserEncashStatEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM UserEncashStat o", UserEncashStat.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    @Transactional
    public void UserEncashStat.persist() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.persist(this);
    }
    
    @Transactional
    public void UserEncashStat.remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            UserEncashStat attached = UserEncashStat.findUserEncashStat(this.statId);
            this.entityManager.remove(attached);
        }
    }
    
    @Transactional
    public void UserEncashStat.flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }
    
    @Transactional
    public void UserEncashStat.clear() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.clear();
    }
    
    @Transactional
    public UserEncashStat UserEncashStat.merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        UserEncashStat merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
    
}