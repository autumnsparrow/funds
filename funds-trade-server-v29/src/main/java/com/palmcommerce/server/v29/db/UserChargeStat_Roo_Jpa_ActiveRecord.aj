// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.palmcommerce.server.v29.db;

import com.palmcommerce.server.v29.db.UserChargeStat;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;

privileged aspect UserChargeStat_Roo_Jpa_ActiveRecord {
    
    @PersistenceContext
    transient EntityManager UserChargeStat.entityManager;
    
    public static final EntityManager UserChargeStat.entityManager() {
        EntityManager em = new UserChargeStat().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }
    
    public static long UserChargeStat.countUserChargeStats() {
        return entityManager().createQuery("SELECT COUNT(o) FROM UserChargeStat o", Long.class).getSingleResult();
    }
    
    public static List<UserChargeStat> UserChargeStat.findAllUserChargeStats() {
        return entityManager().createQuery("SELECT o FROM UserChargeStat o", UserChargeStat.class).getResultList();
    }
    
    public static UserChargeStat UserChargeStat.findUserChargeStat(BigDecimal statId) {
        if (statId == null) return null;
        return entityManager().find(UserChargeStat.class, statId);
    }
    
    public static List<UserChargeStat> UserChargeStat.findUserChargeStatEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM UserChargeStat o", UserChargeStat.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    @Transactional
    public void UserChargeStat.persist() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.persist(this);
    }
    
    @Transactional
    public void UserChargeStat.remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            UserChargeStat attached = UserChargeStat.findUserChargeStat(this.statId);
            this.entityManager.remove(attached);
        }
    }
    
    @Transactional
    public void UserChargeStat.flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }
    
    @Transactional
    public void UserChargeStat.clear() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.clear();
    }
    
    @Transactional
    public UserChargeStat UserChargeStat.merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        UserChargeStat merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
    
}
