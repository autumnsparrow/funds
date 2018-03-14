// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.palmcommerce.oss.jpa.model;

import com.palmcommerce.oss.jpa.model.OssOperatePopedom;
import com.palmcommerce.oss.jpa.model.OssOperatePopedomPK;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;

privileged aspect OssOperatePopedom_Roo_Jpa_ActiveRecord {
    
    @PersistenceContext
    transient EntityManager OssOperatePopedom.entityManager;
    
    public static final EntityManager OssOperatePopedom.entityManager() {
        EntityManager em = new OssOperatePopedom().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }
    
    public static long OssOperatePopedom.countOssOperatePopedoms() {
        return entityManager().createQuery("SELECT COUNT(o) FROM OssOperatePopedom o", Long.class).getSingleResult();
    }
    
    public static List<OssOperatePopedom> OssOperatePopedom.findAllOssOperatePopedoms() {
        return entityManager().createQuery("SELECT o FROM OssOperatePopedom o", OssOperatePopedom.class).getResultList();
    }
    
    public static OssOperatePopedom OssOperatePopedom.findOssOperatePopedom(OssOperatePopedomPK id) {
        if (id == null) return null;
        return entityManager().find(OssOperatePopedom.class, id);
    }
    
    public static List<OssOperatePopedom> OssOperatePopedom.findOssOperatePopedomEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM OssOperatePopedom o", OssOperatePopedom.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    @Transactional
    public void OssOperatePopedom.persist() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.persist(this);
    }
    
    @Transactional
    public void OssOperatePopedom.remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            OssOperatePopedom attached = OssOperatePopedom.findOssOperatePopedom(this.id);
            this.entityManager.remove(attached);
        }
    }
    
    @Transactional
    public void OssOperatePopedom.flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }
    
    @Transactional
    public void OssOperatePopedom.clear() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.clear();
    }
    
    @Transactional
    public OssOperatePopedom OssOperatePopedom.merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        OssOperatePopedom merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
    
}
