// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.palmcommerce.server.v29.db;

import com.palmcommerce.server.v29.db.UserEncashStat;
import com.palmcommerce.server.v29.db.UserEncashStatDataOnDemand;
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

privileged aspect UserEncashStatDataOnDemand_Roo_DataOnDemand {
    
    declare @type: UserEncashStatDataOnDemand: @Component;
    
    private Random UserEncashStatDataOnDemand.rnd = new SecureRandom();
    
    private List<UserEncashStat> UserEncashStatDataOnDemand.data;
    
    public UserEncashStat UserEncashStatDataOnDemand.getNewTransientUserEncashStat(int index) {
        UserEncashStat obj = new UserEncashStat();
        setCreateTime(obj, index);
        setEncashTime(obj, index);
        setPartnerId(obj, index);
        setTotalFailCount(obj, index);
        setTotalFailMoney(obj, index);
        setTotalSucCount(obj, index);
        setTotalSucMoney(obj, index);
        return obj;
    }
    
    public void UserEncashStatDataOnDemand.setCreateTime(UserEncashStat obj, int index) {
        Date createTime = new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH), Calendar.getInstance().get(Calendar.HOUR_OF_DAY), Calendar.getInstance().get(Calendar.MINUTE), Calendar.getInstance().get(Calendar.SECOND) + new Double(Math.random() * 1000).intValue()).getTime();
        obj.setCreateTime(createTime);
    }
    
    public void UserEncashStatDataOnDemand.setEncashTime(UserEncashStat obj, int index) {
        Date encashTime = new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH), Calendar.getInstance().get(Calendar.HOUR_OF_DAY), Calendar.getInstance().get(Calendar.MINUTE), Calendar.getInstance().get(Calendar.SECOND) + new Double(Math.random() * 1000).intValue()).getTime();
        obj.setEncashTime(encashTime);
    }
    
    public void UserEncashStatDataOnDemand.setPartnerId(UserEncashStat obj, int index) {
        String partnerId = "partnerId_" + index;
        if (partnerId.length() > 20) {
            partnerId = partnerId.substring(0, 20);
        }
        obj.setPartnerId(partnerId);
    }
    
    public void UserEncashStatDataOnDemand.setTotalFailCount(UserEncashStat obj, int index) {
        BigDecimal totalFailCount = BigDecimal.valueOf(index);
        obj.setTotalFailCount(totalFailCount);
    }
    
    public void UserEncashStatDataOnDemand.setTotalFailMoney(UserEncashStat obj, int index) {
        BigDecimal totalFailMoney = BigDecimal.valueOf(index);
        if (totalFailMoney.compareTo(new BigDecimal("999999999999999999.99")) == 1) {
            totalFailMoney = new BigDecimal("999999999999999999.99");
        }
        obj.setTotalFailMoney(totalFailMoney);
    }
    
    public void UserEncashStatDataOnDemand.setTotalSucCount(UserEncashStat obj, int index) {
        BigDecimal totalSucCount = BigDecimal.valueOf(index);
        obj.setTotalSucCount(totalSucCount);
    }
    
    public void UserEncashStatDataOnDemand.setTotalSucMoney(UserEncashStat obj, int index) {
        BigDecimal totalSucMoney = BigDecimal.valueOf(index);
        if (totalSucMoney.compareTo(new BigDecimal("999999999999999999.99")) == 1) {
            totalSucMoney = new BigDecimal("999999999999999999.99");
        }
        obj.setTotalSucMoney(totalSucMoney);
    }
    
    public UserEncashStat UserEncashStatDataOnDemand.getSpecificUserEncashStat(int index) {
        init();
        if (index < 0) {
            index = 0;
        }
        if (index > (data.size() - 1)) {
            index = data.size() - 1;
        }
        UserEncashStat obj = data.get(index);
        BigDecimal id = obj.getStatId();
        return UserEncashStat.findUserEncashStat(id);
    }
    
    public UserEncashStat UserEncashStatDataOnDemand.getRandomUserEncashStat() {
        init();
        UserEncashStat obj = data.get(rnd.nextInt(data.size()));
        BigDecimal id = obj.getStatId();
        return UserEncashStat.findUserEncashStat(id);
    }
    
    public boolean UserEncashStatDataOnDemand.modifyUserEncashStat(UserEncashStat obj) {
        return false;
    }
    
    public void UserEncashStatDataOnDemand.init() {
        int from = 0;
        int to = 10;
        data = UserEncashStat.findUserEncashStatEntries(from, to);
        if (data == null) {
            throw new IllegalStateException("Find entries implementation for 'UserEncashStat' illegally returned null");
        }
        if (!data.isEmpty()) {
            return;
        }
        
        data = new ArrayList<UserEncashStat>();
        for (int i = 0; i < 10; i++) {
            UserEncashStat obj = getNewTransientUserEncashStat(i);
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
