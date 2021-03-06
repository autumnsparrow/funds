// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.palmcommerce.server.v29.db;

import com.palmcommerce.server.v29.db.UserAccountLog108;
import com.palmcommerce.server.v29.db.UserAccountLog108DataOnDemand;
import com.palmcommerce.server.v29.service.UserAccountLogService;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

privileged aspect UserAccountLog108DataOnDemand_Roo_DataOnDemand {
    
    declare @type: UserAccountLog108DataOnDemand: @Component;
    
    private Random UserAccountLog108DataOnDemand.rnd = new SecureRandom();
    
    private List<UserAccountLog108> UserAccountLog108DataOnDemand.data;
    
    @Autowired
    UserAccountLogService UserAccountLog108DataOnDemand.userAccountLogService;
    
    public UserAccountLog108 UserAccountLog108DataOnDemand.getNewTransientUserAccountLog108(int index) {
        UserAccountLog108 obj = new UserAccountLog108();
        setCash(obj, index);
        setCreateTime(obj, index);
        setForigenId(obj, index);
        setFrezzMoney(obj, index);
        setOptCode(obj, index);
        setOptMoney(obj, index);
        setPartnerId(obj, index);
        setUserId(obj, index);
        return obj;
    }
    
    public void UserAccountLog108DataOnDemand.setCash(UserAccountLog108 obj, int index) {
        BigDecimal cash = BigDecimal.valueOf(index);
        if (cash.compareTo(new BigDecimal("9999999999999.99")) == 1) {
            cash = new BigDecimal("9999999999999.99");
        }
        obj.setCash(cash);
    }
    
    public void UserAccountLog108DataOnDemand.setCreateTime(UserAccountLog108 obj, int index) {
        Date createTime = new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH), Calendar.getInstance().get(Calendar.HOUR_OF_DAY), Calendar.getInstance().get(Calendar.MINUTE), Calendar.getInstance().get(Calendar.SECOND) + new Double(Math.random() * 1000).intValue()).getTime();
        obj.setCreateTime(createTime);
    }
    
    public void UserAccountLog108DataOnDemand.setForigenId(UserAccountLog108 obj, int index) {
        String forigenId = "forigenId_" + index;
        if (forigenId.length() > 25) {
            forigenId = forigenId.substring(0, 25);
        }
        obj.setForigenId(forigenId);
    }
    
    public void UserAccountLog108DataOnDemand.setFrezzMoney(UserAccountLog108 obj, int index) {
        BigDecimal frezzMoney = BigDecimal.valueOf(index);
        if (frezzMoney.compareTo(new BigDecimal("9999999999999.99")) == 1) {
            frezzMoney = new BigDecimal("9999999999999.99");
        }
        obj.setFrezzMoney(frezzMoney);
    }
    
    public void UserAccountLog108DataOnDemand.setOptCode(UserAccountLog108 obj, int index) {
        String optCode = "optCode_" + index;
        if (optCode.length() > 30) {
            optCode = optCode.substring(0, 30);
        }
        obj.setOptCode(optCode);
    }
    
    public void UserAccountLog108DataOnDemand.setOptMoney(UserAccountLog108 obj, int index) {
        BigDecimal optMoney = BigDecimal.valueOf(index);
        if (optMoney.compareTo(new BigDecimal("9999999999999.99")) == 1) {
            optMoney = new BigDecimal("9999999999999.99");
        }
        obj.setOptMoney(optMoney);
    }
    
    public void UserAccountLog108DataOnDemand.setPartnerId(UserAccountLog108 obj, int index) {
        String partnerId = "partnerId_" + index;
        if (partnerId.length() > 30) {
            partnerId = partnerId.substring(0, 30);
        }
        obj.setPartnerId(partnerId);
    }
    
    public void UserAccountLog108DataOnDemand.setUserId(UserAccountLog108 obj, int index) {
        String userId = "userId_" + index;
        if (userId.length() > 30) {
            userId = userId.substring(0, 30);
        }
        obj.setUserId(userId);
    }
    
    public UserAccountLog108 UserAccountLog108DataOnDemand.getSpecificUserAccountLog108(int index) {
        init();
        if (index < 0) {
            index = 0;
        }
        if (index > (data.size() - 1)) {
            index = data.size() - 1;
        }
        UserAccountLog108 obj = data.get(index);
        String id = obj.getLogId();
        return userAccountLogService.findUserAccountLog108(id);
    }
    
    public UserAccountLog108 UserAccountLog108DataOnDemand.getRandomUserAccountLog108() {
        init();
        UserAccountLog108 obj = data.get(rnd.nextInt(data.size()));
        String id = obj.getLogId();
        return userAccountLogService.findUserAccountLog108(id);
    }
    
    public boolean UserAccountLog108DataOnDemand.modifyUserAccountLog108(UserAccountLog108 obj) {
        return false;
    }
    
    public void UserAccountLog108DataOnDemand.init() {
        int from = 0;
        int to = 10;
        data = userAccountLogService.findUserAccountLog108Entries(from, to);
        if (data == null) {
            throw new IllegalStateException("Find entries implementation for 'UserAccountLog108' illegally returned null");
        }
        if (!data.isEmpty()) {
            return;
        }
        
        data = new ArrayList<UserAccountLog108>();
        for (int i = 0; i < 10; i++) {
            UserAccountLog108 obj = getNewTransientUserAccountLog108(i);
            try {
                userAccountLogService.saveUserAccountLog108(obj);
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
