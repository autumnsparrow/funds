// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.palmcommerce.server.v29.db;

import com.palmcommerce.server.v29.db.UserGiftCoupon;
import com.palmcommerce.server.v29.db.UserGiftCouponDataOnDemand;
import java.math.BigDecimal;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.springframework.stereotype.Component;

privileged aspect UserGiftCouponDataOnDemand_Roo_DataOnDemand {
    
    declare @type: UserGiftCouponDataOnDemand: @Component;
    
    private Random UserGiftCouponDataOnDemand.rnd = new SecureRandom();
    
    private List<UserGiftCoupon> UserGiftCouponDataOnDemand.data;
    
    public UserGiftCoupon UserGiftCouponDataOnDemand.getNewTransientUserGiftCoupon(int index) {
        UserGiftCoupon obj = new UserGiftCoupon();
        setActivityId(obj, index);
        setCouponUnicode(obj, index);
        setCreateTime(obj, index);
        setExpiredTime(obj, index);
        setGivePhones(obj, index);
        setIsGive(obj, index);
        setMoney(obj, index);
        setPCouponId(obj, index);
        setPartnerId(obj, index);
        setRevCouponId(obj, index);
        setSendType(obj, index);
        setStatus(obj, index);
        setType(obj, index);
        setUseMoney(obj, index);
        setUserId(obj, index);
        return obj;
    }
    
    public void UserGiftCouponDataOnDemand.setActivityId(UserGiftCoupon obj, int index) {
        String activityId = "activityId_" + index;
        if (activityId.length() > 30) {
            activityId = activityId.substring(0, 30);
        }
        obj.setActivityId(activityId);
    }
    
    public void UserGiftCouponDataOnDemand.setCouponUnicode(UserGiftCoupon obj, int index) {
        String couponUnicode = "couponUnicode_" + index;
        if (couponUnicode.length() > 50) {
            couponUnicode = new Random().nextInt(10) + couponUnicode.substring(1, 50);
        }
        obj.setCouponUnicode(couponUnicode);
    }
    
    public void UserGiftCouponDataOnDemand.setCreateTime(UserGiftCoupon obj, int index) {
        Date createTime = new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH), Calendar.getInstance().get(Calendar.HOUR_OF_DAY), Calendar.getInstance().get(Calendar.MINUTE), Calendar.getInstance().get(Calendar.SECOND) + new Double(Math.random() * 1000).intValue()).getTime();
        obj.setCreateTime(createTime);
    }
    
    public void UserGiftCouponDataOnDemand.setExpiredTime(UserGiftCoupon obj, int index) {
        Date expiredTime = new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH), Calendar.getInstance().get(Calendar.HOUR_OF_DAY), Calendar.getInstance().get(Calendar.MINUTE), Calendar.getInstance().get(Calendar.SECOND) + new Double(Math.random() * 1000).intValue()).getTime();
        obj.setExpiredTime(expiredTime);
    }
    
    public void UserGiftCouponDataOnDemand.setGivePhones(UserGiftCoupon obj, int index) {
        String givePhones = "givePhones_" + index;
        if (givePhones.length() > 200) {
            givePhones = givePhones.substring(0, 200);
        }
        obj.setGivePhones(givePhones);
    }
    
    public void UserGiftCouponDataOnDemand.setIsGive(UserGiftCoupon obj, int index) {
        BigDecimal isGive = BigDecimal.valueOf(index);
        obj.setIsGive(isGive);
    }
    
    public void UserGiftCouponDataOnDemand.setMoney(UserGiftCoupon obj, int index) {
        BigDecimal money = BigDecimal.valueOf(index);
        if (money.compareTo(new BigDecimal("9999999999999.99")) == 1) {
            money = new BigDecimal("9999999999999.99");
        }
        obj.setMoney(money);
    }
    
    public void UserGiftCouponDataOnDemand.setPCouponId(UserGiftCoupon obj, int index) {
        String pCouponId = "pCouponId_" + index;
        if (pCouponId.length() > 30) {
            pCouponId = pCouponId.substring(0, 30);
        }
        obj.setPCouponId(pCouponId);
    }
    
    public void UserGiftCouponDataOnDemand.setPartnerId(UserGiftCoupon obj, int index) {
        String partnerId = "partnerId_" + index;
        if (partnerId.length() > 20) {
            partnerId = partnerId.substring(0, 20);
        }
        obj.setPartnerId(partnerId);
    }
    
    public void UserGiftCouponDataOnDemand.setRevCouponId(UserGiftCoupon obj, int index) {
        String revCouponId = "revCouponId_" + index;
        if (revCouponId.length() > 30) {
            revCouponId = revCouponId.substring(0, 30);
        }
        obj.setRevCouponId(revCouponId);
    }
    
    public void UserGiftCouponDataOnDemand.setSendType(UserGiftCoupon obj, int index) {
        BigDecimal sendType = BigDecimal.valueOf(index);
        obj.setSendType(sendType);
    }
    
    public void UserGiftCouponDataOnDemand.setStatus(UserGiftCoupon obj, int index) {
        BigDecimal status = BigDecimal.valueOf(index);
        obj.setStatus(status);
    }
    
    public void UserGiftCouponDataOnDemand.setType(UserGiftCoupon obj, int index) {
        BigDecimal type = BigDecimal.valueOf(index);
        obj.setType(type);
    }
    
    public void UserGiftCouponDataOnDemand.setUseMoney(UserGiftCoupon obj, int index) {
        BigDecimal useMoney = BigDecimal.valueOf(index);
        if (useMoney.compareTo(new BigDecimal("9999999999999.99")) == 1) {
            useMoney = new BigDecimal("9999999999999.99");
        }
        obj.setUseMoney(useMoney);
    }
    
    public void UserGiftCouponDataOnDemand.setUserId(UserGiftCoupon obj, int index) {
        String userId = "userId_" + index;
        if (userId.length() > 30) {
            userId = new Random().nextInt(10) + userId.substring(1, 30);
        }
        obj.setUserId(userId);
    }
    
    public UserGiftCoupon UserGiftCouponDataOnDemand.getSpecificUserGiftCoupon(int index) {
        init();
        if (index < 0) {
            index = 0;
        }
        if (index > (data.size() - 1)) {
            index = data.size() - 1;
        }
        UserGiftCoupon obj = data.get(index);
        String id = obj.getCouponId();
        return UserGiftCoupon.findUserGiftCoupon(id);
    }
    
    public UserGiftCoupon UserGiftCouponDataOnDemand.getRandomUserGiftCoupon() {
        init();
        UserGiftCoupon obj = data.get(rnd.nextInt(data.size()));
        String id = obj.getCouponId();
        return UserGiftCoupon.findUserGiftCoupon(id);
    }
    
    public boolean UserGiftCouponDataOnDemand.modifyUserGiftCoupon(UserGiftCoupon obj) {
        return false;
    }
    
    public void UserGiftCouponDataOnDemand.init() {
        int from = 0;
        int to = 10;
        data = UserGiftCoupon.findUserGiftCouponEntries(from, to);
        if (data == null) {
            throw new IllegalStateException("Find entries implementation for 'UserGiftCoupon' illegally returned null");
        }
        if (!data.isEmpty()) {
            return;
        }
        
        data = new ArrayList<UserGiftCoupon>();
        for (int i = 0; i < 10; i++) {
            UserGiftCoupon obj = getNewTransientUserGiftCoupon(i);
            try {
                obj.persist();
            } catch (ConstraintViolationException e) {
                StringBuilder msg = new StringBuilder();
                for (Iterator<ConstraintViolation<?>> iter = e.getConstraintViolations().iterator(); iter.hasNext();) {
                    ConstraintViolation<?> cv = iter.next();
                    msg.append("[").append(cv.getConstraintDescriptor()).append(":").append(cv.getMessage()).append("=").append(cv.getInvalidValue()).append("]");
                }
                throw new RuntimeException(msg.toString(), e);
            }
            obj.flush();
            data.add(obj);
        }
    }
    
}
