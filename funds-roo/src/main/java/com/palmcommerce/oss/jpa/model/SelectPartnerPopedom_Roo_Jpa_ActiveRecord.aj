// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.palmcommerce.oss.jpa.model;

import com.palmcommerce.oss.jpa.model.SelectPartnerPopedom;
import com.palmcommerce.oss.jpa.model.SelectPartnerPopedomPK;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;

privileged aspect SelectPartnerPopedom_Roo_Jpa_ActiveRecord {
    
    @PersistenceContext
    transient EntityManager SelectPartnerPopedom.entityManager;
    
    public static final EntityManager SelectPartnerPopedom.entityManager() {
        EntityManager em = new SelectPartnerPopedom().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }
    
    public static long SelectPartnerPopedom.countSelectPartnerPopedoms() {
        return entityManager().createQuery("SELECT COUNT(o) FROM SelectPartnerPopedom o", Long.class).getSingleResult();
    }
    
    public static List<SelectPartnerPopedom> SelectPartnerPopedom.findAllSelectPartnerPopedoms() {
        return entityManager().createQuery("SELECT o FROM SelectPartnerPopedom o", SelectPartnerPopedom.class).getResultList();
    }
    
    public static SelectPartnerPopedom SelectPartnerPopedom.findSelectPartnerPopedom(SelectPartnerPopedomPK id) {
        if (id == null) return null;
        return entityManager().find(SelectPartnerPopedom.class, id);
    }
    
    public static List<SelectPartnerPopedom> SelectPartnerPopedom.findSelectPartnerPopedomEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM SelectPartnerPopedom o", SelectPartnerPopedom.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    @Transactional
    public void SelectPartnerPopedom.persist() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.persist(this);
    }
    
    @Transactional
    public void SelectPartnerPopedom.remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            SelectPartnerPopedom attached = SelectPartnerPopedom.findSelectPartnerPopedom(this.id);
            this.entityManager.remove(attached);
        }
    }
    
    @Transactional
    public void SelectPartnerPopedom.flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }
    
    @Transactional
    public void SelectPartnerPopedom.clear() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.clear();
    }
    
    @Transactional
    public SelectPartnerPopedom SelectPartnerPopedom.merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        SelectPartnerPopedom merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
    
}
