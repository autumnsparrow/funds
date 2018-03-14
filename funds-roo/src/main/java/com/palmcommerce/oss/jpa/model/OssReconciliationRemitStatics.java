package com.palmcommerce.oss.jpa.model;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;
import javax.validation.constraints.Size;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class OssReconciliationRemitStatics {

    /**
     */
    private long correctOrderSum;

    /**
     */
    private long correctOrderAmout;

    /**
     */
    private long wrongOrderSum;

    /**
     */
    private long wrongOrderAmout;

    /**
     */
    private long overOrderSum;

    /**
     */
    private long overOrderAmout;

    /**
     */
    private long lessOrderSum;

    /**
     */
    private long lessOrderAmout;

    /**
     */
    @Size(max = 20)
    private String tradeTime;

    /**
     */
    @Size(max = 20)
    private String financeName;
}
