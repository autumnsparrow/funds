package com.palmcommerce.funds.roo.model;

import java.util.Date;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord(finders = { "findScheduledTaskStatesByBankDateEqualsAndBankCodeEquals", "findScheduledTaskStatesByBankDateEquals", "findScheduledTaskStatesByBankDateEqualsAndIsBankNot", "findScheduledTaskStatesByBankDateEqualsAndTradecodeEquals", "findScheduledTaskStatesByBankDateEqualsAndTranscodeEquals", "findScheduledTaskStatesByBankDateEqualsAndIsBank" })
public class ScheduledTaskState {

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyyMMdd")
    private Date bankDate;

    private String bankCode;

    private int processState;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date processDatetime;

    private int retries;

    private String transcode;

    private String tradecode;

    private int isBank;

    private String fileName;

    private long fileSize;

    private Boolean isFileValidate;
}
