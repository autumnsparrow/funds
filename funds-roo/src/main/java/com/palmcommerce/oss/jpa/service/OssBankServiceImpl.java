package com.palmcommerce.oss.jpa.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.palmcommerce.oss.jpa.model.OssBank;


public class OssBankServiceImpl implements OssBankService {
	
	 public  List<OssBank> findOssBanksByBankIdEquals(String bankId) {
	        if (bankId == null || bankId.length() == 0) throw new IllegalArgumentException("The bankId argument is required");
	        EntityManager em = OssBank.entityManager();
	        TypedQuery<OssBank> q = em.createQuery("SELECT o FROM OssBank AS o WHERE o.bankId = :bankId", OssBank.class);
	        q.setParameter("bankId", bankId);
	        return q.getResultList();
	    }
}
