package com.palmcommerce.funds.roo.model;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.EntityManager;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;
import org.springframework.transaction.annotation.Transactional;

@RooJavaBean
@RooToString
@RooJpaActiveRecord(finders = { "findTransactionMetasByGlobalSerialEquals", "findTransactionMetaEntriesByBankDateAndBankCode", "findTransactionMetasByTradeDateTimeBetween" })
public class TransactionMeta {

    private String globalSerial;

    @Size(max = 6)
    private String transcode;

    @Size(max = 20)
    private String fromCode;

    @Size(max = 20)
    private String toCode;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date tradeDateTime; //交易时间

    @Size(max = 20)
    private String paymentSerial; //银行流水号

    @Size(max = 20)
    private String tradeSerial; //中心流水号

    @Size(max = 4)
    private String transactionState; //状态

    @Size(max = 30)
    private String transactionMessage; //信息

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "transactionMeta")
    private TransactionRecord transactionRecord;
    
  
  
  
    
    
    

    public static List<TransactionMeta> findTransactionMetaEntriesByBankDateAndBankCode(Date bankDate, String bankCode) {
        TypedQuery<TransactionMeta> q = TransactionMeta.entityManager().createQuery("SELECT o FROM TransactionMeta AS o WHERE (o.transcode='240003' or o.transcode='250004') and (o.transactionRecord.bankDatetime = :bankDate) and (o.fromCode=:bankCode or o.toCode=:bankCode)", TransactionMeta.class);
        q.setParameter("bankDate", bankDate);
        q.setParameter("bankCode", bankCode);
        return q.getResultList();
    }
    
    
    public static List<TransactionMeta> findTransactionMetasByTradeDateTimeBetweenAndTransCode(Date minTradeDateTime, Date maxTradeDateTime,boolean isCharge) {
        if (minTradeDateTime == null) throw new IllegalArgumentException("The minTradeDateTime argument is required");
        if (maxTradeDateTime == null) throw new IllegalArgumentException("The maxTradeDateTime argument is required");
        
        EntityManager em = TransactionMeta.entityManager();
        
        TypedQuery<TransactionMeta> q=null;
        if(!isCharge){
        	q= em.createQuery("SELECT o FROM TransactionMeta AS o WHERE o.tradeDateTime BETWEEN :minTradeDateTime AND :maxTradeDateTime and o.transcode=250007", TransactionMeta.class);
        }else{
         	q= em.createQuery("SELECT o FROM TransactionMeta AS o WHERE o.tradeDateTime BETWEEN :minTradeDateTime AND :maxTradeDateTime and (o.transcode=250004 or o.transcode=240003)", TransactionMeta.class);
            	
        }
        q.setParameter("minTradeDateTime", minTradeDateTime);
        q.setParameter("maxTradeDateTime", maxTradeDateTime);
        
        return q.getResultList();
    }
    
    
}
