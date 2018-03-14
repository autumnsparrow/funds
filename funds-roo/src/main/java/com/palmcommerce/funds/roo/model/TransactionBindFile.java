package com.palmcommerce.funds.roo.model;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

import javax.validation.constraints.Size;

import java.util.Date;
import java.util.List;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;

import org.springframework.format.annotation.DateTimeFormat;

@RooJavaBean
@RooToString
@RooJpaActiveRecord(finders = {"findTransactionBindFileEntriesByBankDateAndTransCode"})
public class TransactionBindFile {

    /**
     */
    @Size(max = 20)
    private String paymentSerial;

    /**
     */
    @Size(max = 20)
    private String bankCode;

    /**
     */
    @Size(max = 10)
    private String bindType;

    /**
     */
    @Size(max = 20)
    private String userId;

    /**
     */
    @Size(max = 10)
    private String userName;

    /**
     */
    @Size(max = 30)
    private String bankAccount;

    /**
     */
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyyMMdd")
    private Date bankDatetime;
    
    public static List<TransactionBindFile> findTransactionBindFileEntriesByBankDateAndTransCode(Date bankDate, String transCode) {
        TypedQuery<TransactionBindFile> q = TransactionBindFile.entityManager().createQuery("SELECT o FROM TransactionBindFile AS o WHERE o.bankDatetime = :bankDate and (o.fromCode=:transCode or o.toCode=:transCode)", TransactionBindFile.class);
        q.setParameter("bankDate", bankDate);
        q.setParameter("transCode", transCode);
        return q.getResultList();
    }
}
