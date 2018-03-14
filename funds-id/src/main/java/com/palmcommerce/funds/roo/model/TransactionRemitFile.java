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
@RooJpaActiveRecord(finders = {"findTransactionRemitFileEntriesByBankDateAndTransCode"})
public class TransactionRemitFile {

    /**
     */
    @Size(max = 20)
    private String fromCode;

    /**
     */
    @Size(max = 20)
    private String toCode;

    /**
     */
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-DD HH:mm:ss")
    private Date tradeDatetime;

    /**
     */
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyyMMdd")
    private Date bankDatetime;

    /**
     */
    @Size(max = 20)
    private String paymentSerial;

    /**
     */
    @Size(max = 20)
    private String tradeSystemSerial;

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
    private int amount;

    /**
     */
    @Size(max = 10)
    private String transactionType;
    
    public static List<TransactionRemitFile> findTransactionRemitFileEntriesByBankDateAndTransCode(Date bankDate, String transCode) {
        TypedQuery<TransactionRemitFile> q = TransactionRemitFile.entityManager().createQuery("SELECT o FROM TransactionRemitFile AS o WHERE o.bankDatetime = :bankDate and (o.fromCode=:transCode or o.toCode=:transCode)", TransactionRemitFile.class);
        q.setParameter("bankDate", bankDate);
        q.setParameter("transCode", transCode);
        return q.getResultList();
    }
}
