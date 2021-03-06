// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.palmcommerce.funds.mock.client.model;

import com.palmcommerce.funds.mock.client.model.BankHeader;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;

privileged aspect BankHeader_Roo_Jpa_ActiveRecord {
    
    @PersistenceContext
    transient EntityManager BankHeader.entityManager;
    
    public static final EntityManager BankHeader.entityManager() {
        EntityManager em = new BankHeader() {
        }.entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }
    
    public static long BankHeader.countBankHeaders() {
        return entityManager().createQuery("SELECT COUNT(o) FROM BankHeader o", Long.class).getSingleResult();
    }
    
    public static List<BankHeader> BankHeader.findAllBankHeaders() {
        return entityManager().createQuery("SELECT o FROM BankHeader o", BankHeader.class).getResultList();
    }
    
    public static BankHeader BankHeader.findBankHeader(Long id) {
        if (id == null) return null;
        return entityManager().find(BankHeader.class, id);
    }
    
    public static List<BankHeader> BankHeader.findBankHeaderEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM BankHeader o", BankHeader.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    @Transactional
    public void BankHeader.persist() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.persist(this);
    }
    
    @Transactional
    public void BankHeader.remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            BankHeader attached = BankHeader.findBankHeader(this.id);
            this.entityManager.remove(attached);
        }
    }
    
    @Transactional
    public void BankHeader.flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }
    
    @Transactional
    public void BankHeader.clear() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.clear();
    }
    
    @Transactional
    public BankHeader BankHeader.merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        BankHeader merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
    
}
