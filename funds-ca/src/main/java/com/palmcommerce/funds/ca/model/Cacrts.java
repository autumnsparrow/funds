package com.palmcommerce.funds.ca.model;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.Lob;
import javax.persistence.Query;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;
import javax.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.json.RooJson;
import org.springframework.roo.addon.tostring.RooToString;
import org.springframework.roo.classpath.operations.jsr303.RooUploadedFile;
import org.springframework.transaction.annotation.Transactional;

@RooJavaBean
@RooToString
@RooJpaActiveRecord(finders = { "findCacrtsesByNodecodeEquals" })
@RooJson
public class Cacrts {

    private String nodecode;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date modifytime;

    private Boolean iscsr;

    @Size(max = 2048)
    private String content;
    
    @Transactional
    public static int deleteCacrtsesByNodecodeEquals(String nodecode) {
        if (nodecode == null || nodecode.length() == 0) throw new IllegalArgumentException("The nodecode argument is required");
        EntityManager em = Cacrts.entityManager();
         Query q = em.createNativeQuery("DELETE  FROM Cacrts  o WHERE o.nodecode = :nodecode");
        q.setParameter("nodecode", nodecode);
        return q.executeUpdate();
    }
}
