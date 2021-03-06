// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.palmcommerce.oss.jpa.model;

import com.palmcommerce.oss.jpa.model.OssAlert;
import java.math.BigDecimal;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

privileged aspect OssAlert_Roo_Finder {
    
    public static TypedQuery<OssAlert> OssAlert.findOssAlertsByAlertTypeId(BigDecimal alertTypeId) {
        if (alertTypeId == null) throw new IllegalArgumentException("The alertTypeId argument is required");
        EntityManager em = OssAlert.entityManager();
        TypedQuery<OssAlert> q = em.createQuery("SELECT o FROM OssAlert AS o WHERE o.alertTypeId = :alertTypeId", OssAlert.class);
        q.setParameter("alertTypeId", alertTypeId);
        return q;
    }
    
}
