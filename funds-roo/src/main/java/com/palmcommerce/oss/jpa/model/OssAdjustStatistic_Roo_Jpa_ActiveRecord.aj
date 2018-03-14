// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.palmcommerce.oss.jpa.model;

import com.palmcommerce.oss.jpa.model.OssAdjustStatistic;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;

privileged aspect OssAdjustStatistic_Roo_Jpa_ActiveRecord {
    
    @PersistenceContext
    transient EntityManager OssAdjustStatistic.entityManager;
    
    public static final EntityManager OssAdjustStatistic.entityManager() {
        EntityManager em = new OssAdjustStatistic().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }
    
    public static long OssAdjustStatistic.countOssAdjustStatistics() {
        return entityManager().createQuery("SELECT COUNT(o) FROM OssAdjustStatistic o", Long.class).getSingleResult();
    }
    
    public static List<OssAdjustStatistic> OssAdjustStatistic.findAllOssAdjustStatistics() {
        return entityManager().createQuery("SELECT o FROM OssAdjustStatistic o", OssAdjustStatistic.class).getResultList();
    }
    
    public static OssAdjustStatistic OssAdjustStatistic.findOssAdjustStatistic(BigDecimal id) {
        if (id == null) return null;
        return entityManager().find(OssAdjustStatistic.class, id);
    }
    
    public static List<OssAdjustStatistic> OssAdjustStatistic.findOssAdjustStatisticEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM OssAdjustStatistic o", OssAdjustStatistic.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    @Transactional
    public void OssAdjustStatistic.persist() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.persist(this);
    }
    
    @Transactional
    public void OssAdjustStatistic.remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            OssAdjustStatistic attached = OssAdjustStatistic.findOssAdjustStatistic(this.id);
            this.entityManager.remove(attached);
        }
    }
    
    @Transactional
    public void OssAdjustStatistic.flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }
    
    @Transactional
    public void OssAdjustStatistic.clear() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.clear();
    }
    
    @Transactional
    public OssAdjustStatistic OssAdjustStatistic.merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        OssAdjustStatistic merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
    
}
