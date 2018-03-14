// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.palmcommerce.oss.jpa.model;

import com.palmcommerce.oss.jpa.model.OssAlert;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;

privileged aspect OssAlert_Roo_Jpa_ActiveRecord {
    
    @PersistenceContext
    transient EntityManager OssAlert.entityManager;
    
    public static final EntityManager OssAlert.entityManager() {
        EntityManager em = new OssAlert().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }
    
    public static long OssAlert.countOssAlerts() {
        return entityManager().createQuery("SELECT COUNT(o) FROM OssAlert o", Long.class).getSingleResult();
    }
    
    public static List<OssAlert> OssAlert.findAllOssAlerts() {
        return entityManager().createQuery("SELECT o FROM OssAlert o", OssAlert.class).getResultList();
    }
    
    public static OssAlert OssAlert.findOssAlert(BigDecimal id) {
        if (id == null) return null;
        return entityManager().find(OssAlert.class, id);
    }
    
    public static List<OssAlert> OssAlert.findOssAlertEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM OssAlert o", OssAlert.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    @Transactional
    public void OssAlert.persist() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.persist(this);
    }
    
    @Transactional
    public void OssAlert.remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            OssAlert attached = OssAlert.findOssAlert(this.id);
            this.entityManager.remove(attached);
        }
    }
    
    @Transactional
    public void OssAlert.flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }
    
    @Transactional
    public void OssAlert.clear() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.clear();
    }
    
    @Transactional
    public OssAlert OssAlert.merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        OssAlert merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
    
}
