// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.palmcommerce.oss.jpa.model;

import com.palmcommerce.oss.jpa.model.OssTradeTask;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;

privileged aspect OssTradeTask_Roo_Jpa_ActiveRecord {
    
    @PersistenceContext
    transient EntityManager OssTradeTask.entityManager;
    
    public static final EntityManager OssTradeTask.entityManager() {
        EntityManager em = new OssTradeTask().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }
    
    public static long OssTradeTask.countOssTradeTasks() {
        return entityManager().createQuery("SELECT COUNT(o) FROM OssTradeTask o", Long.class).getSingleResult();
    }
    
    public static List<OssTradeTask> OssTradeTask.findAllOssTradeTasks() {
        return entityManager().createQuery("SELECT o FROM OssTradeTask o", OssTradeTask.class).getResultList();
    }
    
    public static OssTradeTask OssTradeTask.findOssTradeTask(BigDecimal id) {
        if (id == null) return null;
        return entityManager().find(OssTradeTask.class, id);
    }
    
    public static List<OssTradeTask> OssTradeTask.findOssTradeTaskEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM OssTradeTask o", OssTradeTask.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    @Transactional
    public void OssTradeTask.persist() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.persist(this);
    }
    
    @Transactional
    public void OssTradeTask.remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            OssTradeTask attached = OssTradeTask.findOssTradeTask(this.id);
            this.entityManager.remove(attached);
        }
    }
    
    @Transactional
    public void OssTradeTask.flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }
    
    @Transactional
    public void OssTradeTask.clear() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.clear();
    }
    
    @Transactional
    public OssTradeTask OssTradeTask.merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        OssTradeTask merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
    
}
