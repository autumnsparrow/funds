package com.palmcommerce.oss.jpa.model;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import org.springframework.roo.addon.dbre.RooDbManaged;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooDbManaged(automaticallyDelete = true)
@RooJpaActiveRecord(versionField = "", table = "OSS_WITHDRAW_STANDARD", schema = "CQFC")
public class OssWithdrawStandard {

    public static TypedQuery<com.palmcommerce.oss.jpa.model.OssWithdrawStandard> findOssWithdrawStandardsByPlatformIdEquals(String platformId) {
        if (platformId == null || platformId.length() == 0) throw new IllegalArgumentException("The platformId argument is required");
        EntityManager em = OssWithdrawStandard.entityManager();
        TypedQuery<OssWithdrawStandard> q = em.createQuery("SELECT o FROM OssWithdrawStandard AS o WHERE o.platformId = :platformId", OssWithdrawStandard.class);
        q.setParameter("platformId", platformId);
        return q;
    }
}
