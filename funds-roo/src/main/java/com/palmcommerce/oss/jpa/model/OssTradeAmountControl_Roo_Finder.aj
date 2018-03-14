// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.palmcommerce.oss.jpa.model;

import com.palmcommerce.oss.jpa.model.OssTradeAmountControl;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

privileged aspect OssTradeAmountControl_Roo_Finder {
    
    public static TypedQuery<OssTradeAmountControl> OssTradeAmountControl.findOssTradeAmountControlsByPlatformIdEqualsOrBankIdEquals(String platformId, String bankId) {
        if (platformId == null || platformId.length() == 0) throw new IllegalArgumentException("The platformId argument is required");
        if (bankId == null || bankId.length() == 0) throw new IllegalArgumentException("The bankId argument is required");
        EntityManager em = OssTradeAmountControl.entityManager();
        TypedQuery<OssTradeAmountControl> q = em.createQuery("SELECT o FROM OssTradeAmountControl AS o WHERE o.platformId = :platformId  OR o.bankId = :bankId", OssTradeAmountControl.class);
        q.setParameter("platformId", platformId);
        q.setParameter("bankId", bankId);
        return q;
    }
    
}
