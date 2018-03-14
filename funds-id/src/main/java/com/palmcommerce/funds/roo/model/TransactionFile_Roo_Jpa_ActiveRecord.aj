// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.palmcommerce.funds.roo.model;

import com.palmcommerce.funds.roo.model.TransactionFile;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;

privileged aspect TransactionFile_Roo_Jpa_ActiveRecord {
    
    @PersistenceContext
    transient EntityManager TransactionFile.entityManager;
    
    public static final EntityManager TransactionFile.entityManager() {
        EntityManager em = new TransactionFile().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }
    
    public static long TransactionFile.countTransactionFiles() {
        return entityManager().createQuery("SELECT COUNT(o) FROM TransactionFile o", Long.class).getSingleResult();
    }
    
    public static List<TransactionFile> TransactionFile.findAllTransactionFiles() {
        return entityManager().createQuery("SELECT o FROM TransactionFile o", TransactionFile.class).getResultList();
    }
    
    public static TransactionFile TransactionFile.findTransactionFile(Long id) {
        if (id == null) return null;
        return entityManager().find(TransactionFile.class, id);
    }
    
    public static List<TransactionFile> TransactionFile.findTransactionFileEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM TransactionFile o", TransactionFile.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    @Transactional
    public void TransactionFile.persist() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.persist(this);
    }
    
    @Transactional
    public void TransactionFile.remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            TransactionFile attached = TransactionFile.findTransactionFile(this.id);
            this.entityManager.remove(attached);
        }
    }
    
    @Transactional
    public void TransactionFile.flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }
    
    @Transactional
    public void TransactionFile.clear() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.clear();
    }
    
    @Transactional
    public TransactionFile TransactionFile.merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        TransactionFile merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
    
}
