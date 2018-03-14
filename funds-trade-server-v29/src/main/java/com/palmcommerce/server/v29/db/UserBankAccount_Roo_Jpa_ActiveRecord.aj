// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.palmcommerce.server.v29.db;

import com.palmcommerce.server.v29.db.UserBankAccount;
import com.palmcommerce.server.v29.db.UserBankAccountPK;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;

privileged aspect UserBankAccount_Roo_Jpa_ActiveRecord {
    
    @PersistenceContext
    transient EntityManager UserBankAccount.entityManager;
    
    public static final EntityManager UserBankAccount.entityManager() {
        EntityManager em = new UserBankAccount().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }
    
    public static long UserBankAccount.countUserBankAccounts() {
        return entityManager().createQuery("SELECT COUNT(o) FROM UserBankAccount o", Long.class).getSingleResult();
    }
    
    public static List<UserBankAccount> UserBankAccount.findAllUserBankAccounts() {
        return entityManager().createQuery("SELECT o FROM UserBankAccount o", UserBankAccount.class).getResultList();
    }
    
    public static UserBankAccount UserBankAccount.findUserBankAccount(UserBankAccountPK id) {
        if (id == null) return null;
        return entityManager().find(UserBankAccount.class, id);
    }
    
    public static List<UserBankAccount> UserBankAccount.findUserBankAccountEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM UserBankAccount o", UserBankAccount.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    @Transactional
    public void UserBankAccount.persist() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.persist(this);
    }
    
    @Transactional
    public void UserBankAccount.remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            UserBankAccount attached = UserBankAccount.findUserBankAccount(this.id);
            this.entityManager.remove(attached);
        }
    }
    
    @Transactional
    public void UserBankAccount.flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }
    
    @Transactional
    public void UserBankAccount.clear() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.clear();
    }
    
    @Transactional
    public UserBankAccount UserBankAccount.merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        UserBankAccount merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
    
}