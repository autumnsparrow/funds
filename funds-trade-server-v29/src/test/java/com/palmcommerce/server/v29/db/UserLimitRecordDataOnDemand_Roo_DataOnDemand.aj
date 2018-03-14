// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.palmcommerce.server.v29.db;

import com.palmcommerce.server.v29.db.UserLimitRecord;
import com.palmcommerce.server.v29.db.UserLimitRecordDataOnDemand;
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

privileged aspect UserLimitRecordDataOnDemand_Roo_DataOnDemand {
    
    declare @type: UserLimitRecordDataOnDemand: @Component;
    
    private Random UserLimitRecordDataOnDemand.rnd = new SecureRandom();
    
    private List<UserLimitRecord> UserLimitRecordDataOnDemand.data;
    
    public UserLimitRecord UserLimitRecordDataOnDemand.getNewTransientUserLimitRecord(int index) {
        UserLimitRecord obj = new UserLimitRecord();
        setChargeMoney(obj, index);
        setChargeNum(obj, index);
        setCreateTime(obj, index);
        setEncashMoney(obj, index);
        setEncashNum(obj, index);
        setPartnerId(obj, index);
        setType(obj, index);
        setUserId(obj, index);
        setVoteMoney(obj, index);
        return obj;
    }
    
    public void UserLimitRecordDataOnDemand.setChargeMoney(UserLimitRecord obj, int index) {
        BigDecimal chargeMoney = BigDecimal.valueOf(index);
        if (chargeMoney.compareTo(new BigDecimal("9999999999999.99")) == 1) {
            chargeMoney = new BigDecimal("9999999999999.99");
        }
        obj.setChargeMoney(chargeMoney);
    }
    
    public void UserLimitRecordDataOnDemand.setChargeNum(UserLimitRecord obj, int index) {
        BigDecimal chargeNum = BigDecimal.valueOf(index);
        obj.setChargeNum(chargeNum);
    }
    
    public void UserLimitRecordDataOnDemand.setCreateTime(UserLimitRecord obj, int index) {
        Date createTime = new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH), Calendar.getInstance().get(Calendar.HOUR_OF_DAY), Calendar.getInstance().get(Calendar.MINUTE), Calendar.getInstance().get(Calendar.SECOND) + new Double(Math.random() * 1000).intValue()).getTime();
        obj.setCreateTime(createTime);
    }
    
    public void UserLimitRecordDataOnDemand.setEncashMoney(UserLimitRecord obj, int index) {
        BigDecimal encashMoney = BigDecimal.valueOf(index);
        if (encashMoney.compareTo(new BigDecimal("9999999999999.99")) == 1) {
            encashMoney = new BigDecimal("9999999999999.99");
        }
        obj.setEncashMoney(encashMoney);
    }
    
    public void UserLimitRecordDataOnDemand.setEncashNum(UserLimitRecord obj, int index) {
        BigDecimal encashNum = BigDecimal.valueOf(index);
        obj.setEncashNum(encashNum);
    }
    
    public void UserLimitRecordDataOnDemand.setPartnerId(UserLimitRecord obj, int index) {
        String partnerId = "partnerId_" + index;
        if (partnerId.length() > 30) {
            partnerId = new Random().nextInt(10) + partnerId.substring(1, 30);
        }
        obj.setPartnerId(partnerId);
    }
    
    public void UserLimitRecordDataOnDemand.setType(UserLimitRecord obj, int index) {
        BigDecimal type = BigDecimal.valueOf(index);
        obj.setType(type);
    }
    
    public void UserLimitRecordDataOnDemand.setUserId(UserLimitRecord obj, int index) {
        String userId = "userId_" + index;
        if (userId.length() > 30) {
            userId = new Random().nextInt(10) + userId.substring(1, 30);
        }
        obj.setUserId(userId);
    }
    
    public void UserLimitRecordDataOnDemand.setVoteMoney(UserLimitRecord obj, int index) {
        BigDecimal voteMoney = BigDecimal.valueOf(index);
        if (voteMoney.compareTo(new BigDecimal("9999999999999.99")) == 1) {
            voteMoney = new BigDecimal("9999999999999.99");
        }
        obj.setVoteMoney(voteMoney);
    }
    
    public UserLimitRecord UserLimitRecordDataOnDemand.getSpecificUserLimitRecord(int index) {
        init();
        if (index < 0) {
            index = 0;
        }
        if (index > (data.size() - 1)) {
            index = data.size() - 1;
        }
        UserLimitRecord obj = data.get(index);
        String id = obj.getLimitId();
        return UserLimitRecord.findUserLimitRecord(id);
    }
    
    public UserLimitRecord UserLimitRecordDataOnDemand.getRandomUserLimitRecord() {
        init();
        UserLimitRecord obj = data.get(rnd.nextInt(data.size()));
        String id = obj.getLimitId();
        return UserLimitRecord.findUserLimitRecord(id);
    }
    
    public boolean UserLimitRecordDataOnDemand.modifyUserLimitRecord(UserLimitRecord obj) {
        return false;
    }
    
    public void UserLimitRecordDataOnDemand.init() {
        int from = 0;
        int to = 10;
        data = UserLimitRecord.findUserLimitRecordEntries(from, to);
        if (data == null) {
            throw new IllegalStateException("Find entries implementation for 'UserLimitRecord' illegally returned null");
        }
        if (!data.isEmpty()) {
            return;
        }
        
        data = new ArrayList<UserLimitRecord>();
        for (int i = 0; i < 10; i++) {
            UserLimitRecord obj = getNewTransientUserLimitRecord(i);
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