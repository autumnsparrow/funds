// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.palmcommerce.oss.jpa.model;

import com.palmcommerce.oss.jpa.model.OssTradeAmountControl;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;

privileged aspect OssTradeAmountControl_Roo_Jpa_ActiveRecord {
    
    @PersistenceContext
    transient EntityManager OssTradeAmountControl.entityManager;
    
    public static final EntityManager OssTradeAmountControl.entityManager() {
        EntityManager em = new OssTradeAmountControl().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }
    
    public static long OssTradeAmountControl.countOssTradeAmountControls() {
        return entityManager().createQuery("SELECT COUNT(o) FROM OssTradeAmountControl o", Long.class).getSingleResult();
    }
    
    public static List<OssTradeAmountControl> OssTradeAmountControl.findAllOssTradeAmountControls() {
        return entityManager().createQuery("SELECT o FROM OssTradeAmountControl o", OssTradeAmountControl.class).getResultList();
    }
    
    public static OssTradeAmountControl OssTradeAmountControl.findOssTradeAmountControl(BigDecimal id) {
        if (id == null) return null;
        return entityManager().find(OssTradeAmountControl.class, id);
    }
    
    public static List<OssTradeAmountControl> OssTradeAmountControl.findOssTradeAmountControlEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM OssTradeAmountControl o", OssTradeAmountControl.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    @Transactional
    public void OssTradeAmountControl.persist() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.persist(this);
    }
    
    @Transactional
    public void OssTradeAmountControl.remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            OssTradeAmountControl attached = OssTradeAmountControl.findOssTradeAmountControl(this.id);
            this.entityManager.remove(attached);
        }
    }
    
    @Transactional
    public void OssTradeAmountControl.flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }
    
    @Transactional
    public void OssTradeAmountControl.clear() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.clear();
    }
    
    @Transactional
    public OssTradeAmountControl OssTradeAmountControl.merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        OssTradeAmountControl merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
    
}
