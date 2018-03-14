// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.palmcommerce.server.v29.db;

import com.palmcommerce.server.v29.db.UserCharge;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;

privileged aspect UserCharge_Roo_Jpa_ActiveRecord {
    
    @PersistenceContext
    transient EntityManager UserCharge.entityManager;
    
    public static final EntityManager UserCharge.entityManager() {
        EntityManager em = new UserCharge().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }
    
    public static long UserCharge.countUserCharges() {
        return entityManager().createQuery("SELECT COUNT(o) FROM UserCharge o", Long.class).getSingleResult();
    }
    
    public static List<UserCharge> UserCharge.findAllUserCharges() {
        return entityManager().createQuery("SELECT o FROM UserCharge o", UserCharge.class).getResultList();
    }
    
    public static UserCharge UserCharge.findUserCharge(String chargeId) {
        if (chargeId == null || chargeId.length() == 0) return null;
        return entityManager().find(UserCharge.class, chargeId);
    }
    
    public static List<UserCharge> UserCharge.findUserChargeEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM UserCharge o", UserCharge.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    @Transactional
    public void UserCharge.persist() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.persist(this);
    }
    
    @Transactional
    public void UserCharge.remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            UserCharge attached = UserCharge.findUserCharge(this.chargeId);
            this.entityManager.remove(attached);
        }
    }
    
    @Transactional
    public void UserCharge.flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }
    
    @Transactional
    public void UserCharge.clear() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.clear();
    }
    
    @Transactional
    public UserCharge UserCharge.merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        UserCharge merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
    
}