// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.palmcommerce.funds.roo.model;

import com.palmcommerce.funds.roo.model.TransactionBindFile;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;

privileged aspect TransactionBindFile_Roo_Jpa_ActiveRecord {
    
    @PersistenceContext
    transient EntityManager TransactionBindFile.entityManager;
    
    public static final EntityManager TransactionBindFile.entityManager() {
        EntityManager em = new TransactionBindFile().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }
    
    public static long TransactionBindFile.countTransactionBindFiles() {
        return entityManager().createQuery("SELECT COUNT(o) FROM TransactionBindFile o", Long.class).getSingleResult();
    }
    
    public static List<TransactionBindFile> TransactionBindFile.findAllTransactionBindFiles() {
        return entityManager().createQuery("SELECT o FROM TransactionBindFile o", TransactionBindFile.class).getResultList();
    }
    
    public static TransactionBindFile TransactionBindFile.findTransactionBindFile(Long id) {
        if (id == null) return null;
        return entityManager().find(TransactionBindFile.class, id);
    }
    
    public static List<TransactionBindFile> TransactionBindFile.findTransactionBindFileEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM TransactionBindFile o", TransactionBindFile.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    @Transactional
    public void TransactionBindFile.persist() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.persist(this);
    }
    
    @Transactional
    public void TransactionBindFile.remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            TransactionBindFile attached = TransactionBindFile.findTransactionBindFile(this.id);
            this.entityManager.remove(attached);
        }
    }
    
    @Transactional
    public void TransactionBindFile.flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }
    
    @Transactional
    public void TransactionBindFile.clear() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.clear();
    }
    
    @Transactional
    public TransactionBindFile TransactionBindFile.merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        TransactionBindFile merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
    
}