package com.palmcommerce.funds.roo.model;

import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;
import javax.validation.constraints.Size;
import org.eclipse.persistence.annotations.QueryRedirectors;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord(finders = { "findTransactionFileEntriesByBankDateAndTransCode", "findTransactionFilesByBankDatetimeEquals" })
public class TransactionFile {

    @Size(max = 20)
    private String fromCode;

    @Size(max = 20)
    private String toCode;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-DD HH:mm:ss")
    private Date tradeDatetime;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyyMMdd")
    private Date bankDatetime;

    @Size(max = 20)
    private String paymentSerial;

    @Size(max = 20)
    private String tradeSystemSerial;

    @Size(max = 20)
    private String userId;

    @Size(max = 10)
    private String userName;

    @Size(max = 30)
    private String bankAccount;

    private int amount;

    @Size(max = 10)
    private String transactionType;

    private String globalserial;

    private int flag;

    public static List<com.palmcommerce.funds.roo.model.TransactionFile> findTransactionFileEntriesByBankDateAndTransCode(Date bankDate, String transCode) {
        TypedQuery<TransactionFile> q = TransactionFile.entityManager().createQuery("SELECT o FROM TransactionFile AS o WHERE o.bankDatetime = :bankDate and (o.fromCode=:transCode or o.toCode=:transCode)", TransactionFile.class);
        q.setParameter("bankDate", bankDate);
        q.setParameter("transCode", transCode);
        return q.getResultList();
    }
    
    public static int deleteTransactionFilesByBankDatetimeEquals(Date bankDatetime) {
        if (bankDatetime == null) throw new IllegalArgumentException("The bankDatetime argument is required");
        EntityManager em = TransactionFile.entityManager();
        TypedQuery<TransactionFile> q = em.createQuery("delete FROM TransactionFile AS o WHERE o.bankDatetime = :bankDatetime", TransactionFile.class);
        q.setParameter("bankDatetime", bankDatetime);
        
        int i=q.executeUpdate();
        
        return i;
        
    }
}
