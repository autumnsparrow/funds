// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.palmcommerce.server.v29.db;

import com.palmcommerce.server.v29.db.UserChargeCard;
import com.palmcommerce.server.v29.db.UserChargeCardDataOnDemand;
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

privileged aspect UserChargeCardDataOnDemand_Roo_DataOnDemand {
    
    declare @type: UserChargeCardDataOnDemand: @Component;
    
    private Random UserChargeCardDataOnDemand.rnd = new SecureRandom();
    
    private List<UserChargeCard> UserChargeCardDataOnDemand.data;
    
    public UserChargeCard UserChargeCardDataOnDemand.getNewTransientUserChargeCard(int index) {
        UserChargeCard obj = new UserChargeCard();
        setCreateTime(obj, index);
        setCurrency(obj, index);
        setEncPinblock(obj, index);
        setEncTracks(obj, index);
        setIdcard(obj, index);
        setIdcardType(obj, index);
        setName(obj, index);
        setSn(obj, index);
        return obj;
    }
    
    public void UserChargeCardDataOnDemand.setCreateTime(UserChargeCard obj, int index) {
        Date createTime = new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH), Calendar.getInstance().get(Calendar.HOUR_OF_DAY), Calendar.getInstance().get(Calendar.MINUTE), Calendar.getInstance().get(Calendar.SECOND) + new Double(Math.random() * 1000).intValue()).getTime();
        obj.setCreateTime(createTime);
    }
    
    public void UserChargeCardDataOnDemand.setCurrency(UserChargeCard obj, int index) {
        String currency = "currency_" + index;
        if (currency.length() > 10) {
            currency = currency.substring(0, 10);
        }
        obj.setCurrency(currency);
    }
    
    public void UserChargeCardDataOnDemand.setEncPinblock(UserChargeCard obj, int index) {
        String encPinblock = "encPinblock_" + index;
        if (encPinblock.length() > 1000) {
            encPinblock = encPinblock.substring(0, 1000);
        }
        obj.setEncPinblock(encPinblock);
    }
    
    public void UserChargeCardDataOnDemand.setEncTracks(UserChargeCard obj, int index) {
        String encTracks = "encTracks_" + index;
        if (encTracks.length() > 1000) {
            encTracks = encTracks.substring(0, 1000);
        }
        obj.setEncTracks(encTracks);
    }
    
    public void UserChargeCardDataOnDemand.setIdcard(UserChargeCard obj, int index) {
        String idcard = "idcard_" + index;
        if (idcard.length() > 30) {
            idcard = idcard.substring(0, 30);
        }
        obj.setIdcard(idcard);
    }
    
    public void UserChargeCardDataOnDemand.setIdcardType(UserChargeCard obj, int index) {
        BigDecimal idcardType = BigDecimal.valueOf(index);
        obj.setIdcardType(idcardType);
    }
    
    public void UserChargeCardDataOnDemand.setName(UserChargeCard obj, int index) {
        String name = "name_" + index;
        if (name.length() > 50) {
            name = name.substring(0, 50);
        }
        obj.setName(name);
    }
    
    public void UserChargeCardDataOnDemand.setSn(UserChargeCard obj, int index) {
        String sn = "sn_" + index;
        if (sn.length() > 50) {
            sn = sn.substring(0, 50);
        }
        obj.setSn(sn);
    }
    
    public UserChargeCard UserChargeCardDataOnDemand.getSpecificUserChargeCard(int index) {
        init();
        if (index < 0) {
            index = 0;
        }
        if (index > (data.size() - 1)) {
            index = data.size() - 1;
        }
        UserChargeCard obj = data.get(index);
        String id = obj.getChargeId();
        return UserChargeCard.findUserChargeCard(id);
    }
    
    public UserChargeCard UserChargeCardDataOnDemand.getRandomUserChargeCard() {
        init();
        UserChargeCard obj = data.get(rnd.nextInt(data.size()));
        String id = obj.getChargeId();
        return UserChargeCard.findUserChargeCard(id);
    }
    
    public boolean UserChargeCardDataOnDemand.modifyUserChargeCard(UserChargeCard obj) {
        return false;
    }
    
    public void UserChargeCardDataOnDemand.init() {
        int from = 0;
        int to = 10;
        data = UserChargeCard.findUserChargeCardEntries(from, to);
        if (data == null) {
            throw new IllegalStateException("Find entries implementation for 'UserChargeCard' illegally returned null");
        }
        if (!data.isEmpty()) {
            return;
        }
        
        data = new ArrayList<UserChargeCard>();
        for (int i = 0; i < 10; i++) {
            UserChargeCard obj = getNewTransientUserChargeCard(i);
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
