package com.palmcommerce.funds.id.model;

import java.util.Date;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord(finders = { "findLocalAndRemoteSerialRelationsByLocalSerialNumberEquals", "findLocalAndRemoteSerialRelationsByRemoteSerialNumberEquals" })
public class LocalAndRemoteSerialRelation {

    @Size(max = 50)
    private String localSerialNumber;

    @Size(max = 50)
    private String remoteSerialNumber;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date createTime;
}
