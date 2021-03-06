// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.palmcommerce.oss.jpa.model;

import com.palmcommerce.oss.jpa.model.OssPopedomRelation;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;

privileged aspect OssPopedomRelation_Roo_Jpa_ActiveRecord {
    
    @PersistenceContext
    transient EntityManager OssPopedomRelation.entityManager;
    
    public static final EntityManager OssPopedomRelation.entityManager() {
        EntityManager em = new OssPopedomRelation().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }
    
    public static long OssPopedomRelation.countOssPopedomRelations() {
        return entityManager().createQuery("SELECT COUNT(o) FROM OssPopedomRelation o", Long.class).getSingleResult();
    }
    
    public static List<OssPopedomRelation> OssPopedomRelation.findAllOssPopedomRelations() {
        return entityManager().createQuery("SELECT o FROM OssPopedomRelation o", OssPopedomRelation.class).getResultList();
    }
    
    public static OssPopedomRelation OssPopedomRelation.findOssPopedomRelation(String vc2OperateName) {
        if (vc2OperateName == null || vc2OperateName.length() == 0) return null;
        return entityManager().find(OssPopedomRelation.class, vc2OperateName);
    }
    
    public static List<OssPopedomRelation> OssPopedomRelation.findOssPopedomRelationEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM OssPopedomRelation o", OssPopedomRelation.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    @Transactional
    public void OssPopedomRelation.persist() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.persist(this);
    }
    
    @Transactional
    public void OssPopedomRelation.remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            OssPopedomRelation attached = OssPopedomRelation.findOssPopedomRelation(this.vc2OperateName);
            this.entityManager.remove(attached);
        }
    }
    
    @Transactional
    public void OssPopedomRelation.flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }
    
    @Transactional
    public void OssPopedomRelation.clear() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.clear();
    }
    
    @Transactional
    public OssPopedomRelation OssPopedomRelation.merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        OssPopedomRelation merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
    
}
