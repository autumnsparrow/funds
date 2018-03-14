package com.palmcommerce.funds.roo.model;
import java.util.Date;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

/**
 * @author Administrator
 *  单条交易信息
 */
@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class TransactionRecord {

    @Size(max = 20)
    private String userId; //用户编号

    @Size(max = 10)
    private String userName; //用户姓名

    @Size(max = 2)
    private String idType; //证件类型

    @Size(max = 20)
    private String idNumber; //证件号码

    @Size(max = 20)
    private String phoneNumber; //手机

    private long tradeSystemDeposit; //虚拟账户余额

    @Size(max = 30)
    private String bankAccount; //银行账户

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyyMMdd")
    private Date bankDatetime; //账务时间

    private int bindingType; //绑定类型

    private int chargeType; //缴费类型

    private long amount; //金额

    @Size(max = 20)
    private String cancelPaymentSerial; //冲正流水号

    @OneToOne
    @JoinColumn
    private TransactionMeta transactionMeta;

    /**
     */
    @Size(max = 50)
    private String fileName;

    /**
     */
    private int fileSize;
}
