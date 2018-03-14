// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.palmcommerce.oss.jpa.model;

import com.palmcommerce.oss.jpa.model.OssReconciliationStatics;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;

privileged aspect OssReconciliationStatics_Roo_Jpa_ActiveRecord {
    
    @PersistenceContext
    transient EntityManager OssReconciliationStatics.entityManager;
    
    public static final EntityManager OssReconciliationStatics.entityManager() {
        EntityManager em = new OssReconciliationStatics().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }
    
    public static long OssReconciliationStatics.countOssReconciliationStaticses() {
        return entityManager().createQuery("SELECT COUNT(o) FROM OssReconciliationStatics o", Long.class).getSingleResult();
    }
    
    public static List<OssReconciliationStatics> OssReconciliationStatics.findAllOssReconciliationStaticses() {
        return entityManager().createQuery("SELECT o FROM OssReconciliationStatics o", OssReconciliationStatics.class).getResultList();
    }
    
    public static OssReconciliationStatics OssReconciliationStatics.findOssReconciliationStatics(BigDecimal id) {
        if (id == null) return null;
        return entityManager().find(OssReconciliationStatics.class, id);
    }
    
    public static List<OssReconciliationStatics> OssReconciliationStatics.findOssReconciliationStaticsEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM OssReconciliationStatics o", OssReconciliationStatics.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    @Transactional
    public void OssReconciliationStatics.persist() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.persist(this);
    }
    
    @Transactional
    public void OssReconciliationStatics.remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            OssReconciliationStatics attached = OssReconciliationStatics.findOssReconciliationStatics(this.id);
            this.entityManager.remove(attached);
        }
    }
    
    @Transactional
    public void OssReconciliationStatics.flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }
    
    @Transactional
    public void OssReconciliationStatics.clear() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.clear();
    }
    
    @Transactional
    public OssReconciliationStatics OssReconciliationStatics.merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        OssReconciliationStatics merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
    
}
