// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.palmcommerce.oss.jpa.model;

import com.palmcommerce.oss.jpa.model.OssTradeType;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;

privileged aspect OssTradeType_Roo_Jpa_ActiveRecord {
    
    @PersistenceContext
    transient EntityManager OssTradeType.entityManager;
    
    public static final EntityManager OssTradeType.entityManager() {
        EntityManager em = new OssTradeType().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }
    
    public static long OssTradeType.countOssTradeTypes() {
        return entityManager().createQuery("SELECT COUNT(o) FROM OssTradeType o", Long.class).getSingleResult();
    }
    
    public static List<OssTradeType> OssTradeType.findAllOssTradeTypes() {
        return entityManager().createQuery("SELECT o FROM OssTradeType o", OssTradeType.class).getResultList();
    }
    
    public static OssTradeType OssTradeType.findOssTradeType(BigDecimal typeId) {
        if (typeId == null) return null;
        return entityManager().find(OssTradeType.class, typeId);
    }
    
    public static List<OssTradeType> OssTradeType.findOssTradeTypeEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM OssTradeType o", OssTradeType.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    @Transactional
    public void OssTradeType.persist() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.persist(this);
    }
    
    @Transactional
    public void OssTradeType.remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            OssTradeType attached = OssTradeType.findOssTradeType(this.typeId);
            this.entityManager.remove(attached);
        }
    }
    
    @Transactional
    public void OssTradeType.flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }
    
    @Transactional
    public void OssTradeType.clear() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.clear();
    }
    
    @Transactional
    public OssTradeType OssTradeType.merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        OssTradeType merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
    
}