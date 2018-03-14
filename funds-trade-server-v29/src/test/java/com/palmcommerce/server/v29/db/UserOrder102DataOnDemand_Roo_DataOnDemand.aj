// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.palmcommerce.server.v29.db;

import com.palmcommerce.server.v29.db.UserOrder102;
import com.palmcommerce.server.v29.db.UserOrder102DataOnDemand;
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

privileged aspect UserOrder102DataOnDemand_Roo_DataOnDemand {
    
    declare @type: UserOrder102DataOnDemand: @Component;
    
    private Random UserOrder102DataOnDemand.rnd = new SecureRandom();
    
    private List<UserOrder102> UserOrder102DataOnDemand.data;
    
    public UserOrder102 UserOrder102DataOnDemand.getNewTransientUserOrder102(int index) {
        UserOrder102 obj = new UserOrder102();
        setCreateTime(obj, index);
        setFailMoney(obj, index);
        setFailNum(obj, index);
        setGameId(obj, index);
        setIssueNo(obj, index);
        setOrderStatus(obj, index);
        setPartnerId(obj, index);
        setPayType(obj, index);
        setPorderId(obj, index);
        setPrizeMoney(obj, index);
        setPrizeMoneyAfterTax(obj, index);
        setPrizeStatus(obj, index);
        setPrizeType(obj, index);
        setSucMoney(obj, index);
        setSucNum(obj, index);
        setTicketNum(obj, index);
        setTotalMoney(obj, index);
        setUserId(obj, index);
        return obj;
    }
    
    public void UserOrder102DataOnDemand.setCreateTime(UserOrder102 obj, int index) {
        Date createTime = new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH), Calendar.getInstance().get(Calendar.HOUR_OF_DAY), Calendar.getInstance().get(Calendar.MINUTE), Calendar.getInstance().get(Calendar.SECOND) + new Double(Math.random() * 1000).intValue()).getTime();
        obj.setCreateTime(createTime);
    }
    
    public void UserOrder102DataOnDemand.setFailMoney(UserOrder102 obj, int index) {
        BigDecimal failMoney = BigDecimal.valueOf(index);
        if (failMoney.compareTo(new BigDecimal("9999999999999.99")) == 1) {
            failMoney = new BigDecimal("9999999999999.99");
        }
        obj.setFailMoney(failMoney);
    }
    
    public void UserOrder102DataOnDemand.setFailNum(UserOrder102 obj, int index) {
        BigDecimal failNum = BigDecimal.valueOf(index);
        obj.setFailNum(failNum);
    }
    
    public void UserOrder102DataOnDemand.setGameId(UserOrder102 obj, int index) {
        String gameId = "gameId_" + index;
        if (gameId.length() > 10) {
            gameId = gameId.substring(0, 10);
        }
        obj.setGameId(gameId);
    }
    
    public void UserOrder102DataOnDemand.setIssueNo(UserOrder102 obj, int index) {
        String issueNo = "issueNo_" + index;
        if (issueNo.length() > 20) {
            issueNo = issueNo.substring(0, 20);
        }
        obj.setIssueNo(issueNo);
    }
    
    public void UserOrder102DataOnDemand.setOrderStatus(UserOrder102 obj, int index) {
        BigDecimal orderStatus = BigDecimal.valueOf(index);
        obj.setOrderStatus(orderStatus);
    }
    
    public void UserOrder102DataOnDemand.setPartnerId(UserOrder102 obj, int index) {
        String partnerId = "partnerId_" + index;
        if (partnerId.length() > 30) {
            partnerId = new Random().nextInt(10) + partnerId.substring(1, 30);
        }
        obj.setPartnerId(partnerId);
    }
    
    public void UserOrder102DataOnDemand.setPayType(UserOrder102 obj, int index) {
        BigDecimal payType = BigDecimal.valueOf(index);
        obj.setPayType(payType);
    }
    
    public void UserOrder102DataOnDemand.setPorderId(UserOrder102 obj, int index) {
        String porderId = "porderId_" + index;
        if (porderId.length() > 30) {
            porderId = new Random().nextInt(10) + porderId.substring(1, 30);
        }
        obj.setPorderId(porderId);
    }
    
    public void UserOrder102DataOnDemand.setPrizeMoney(UserOrder102 obj, int index) {
        BigDecimal prizeMoney = BigDecimal.valueOf(index);
        if (prizeMoney.compareTo(new BigDecimal("9999999999999.99")) == 1) {
            prizeMoney = new BigDecimal("9999999999999.99");
        }
        obj.setPrizeMoney(prizeMoney);
    }
    
    public void UserOrder102DataOnDemand.setPrizeMoneyAfterTax(UserOrder102 obj, int index) {
        BigDecimal prizeMoneyAfterTax = BigDecimal.valueOf(index);
        if (prizeMoneyAfterTax.compareTo(new BigDecimal("9999999999999.99")) == 1) {
            prizeMoneyAfterTax = new BigDecimal("9999999999999.99");
        }
        obj.setPrizeMoneyAfterTax(prizeMoneyAfterTax);
    }
    
    public void UserOrder102DataOnDemand.setPrizeStatus(UserOrder102 obj, int index) {
        BigDecimal prizeStatus = BigDecimal.valueOf(index);
        obj.setPrizeStatus(prizeStatus);
    }
    
    public void UserOrder102DataOnDemand.setPrizeType(UserOrder102 obj, int index) {
        BigDecimal prizeType = BigDecimal.valueOf(index);
        obj.setPrizeType(prizeType);
    }
    
    public void UserOrder102DataOnDemand.setSucMoney(UserOrder102 obj, int index) {
        BigDecimal sucMoney = BigDecimal.valueOf(index);
        if (sucMoney.compareTo(new BigDecimal("9999999999999.99")) == 1) {
            sucMoney = new BigDecimal("9999999999999.99");
        }
        obj.setSucMoney(sucMoney);
    }
    
    public void UserOrder102DataOnDemand.setSucNum(UserOrder102 obj, int index) {
        BigDecimal sucNum = BigDecimal.valueOf(index);
        obj.setSucNum(sucNum);
    }
    
    public void UserOrder102DataOnDemand.setTicketNum(UserOrder102 obj, int index) {
        BigDecimal ticketNum = BigDecimal.valueOf(index);
        obj.setTicketNum(ticketNum);
    }
    
    public void UserOrder102DataOnDemand.setTotalMoney(UserOrder102 obj, int index) {
        BigDecimal totalMoney = BigDecimal.valueOf(index);
        if (totalMoney.compareTo(new BigDecimal("9999999999999.99")) == 1) {
            totalMoney = new BigDecimal("9999999999999.99");
        }
        obj.setTotalMoney(totalMoney);
    }
    
    public void UserOrder102DataOnDemand.setUserId(UserOrder102 obj, int index) {
        String userId = "userId_" + index;
        if (userId.length() > 30) {
            userId = userId.substring(0, 30);
        }
        obj.setUserId(userId);
    }
    
    public UserOrder102 UserOrder102DataOnDemand.getSpecificUserOrder102(int index) {
        init();
        if (index < 0) {
            index = 0;
        }
        if (index > (data.size() - 1)) {
            index = data.size() - 1;
        }
        UserOrder102 obj = data.get(index);
        String id = obj.getOrderId();
        return UserOrder102.findUserOrder102(id);
    }
    
    public UserOrder102 UserOrder102DataOnDemand.getRandomUserOrder102() {
        init();
        UserOrder102 obj = data.get(rnd.nextInt(data.size()));
        String id = obj.getOrderId();
        return UserOrder102.findUserOrder102(id);
    }
    
    public boolean UserOrder102DataOnDemand.modifyUserOrder102(UserOrder102 obj) {
        return false;
    }
    
    public void UserOrder102DataOnDemand.init() {
        int from = 0;
        int to = 10;
        data = UserOrder102.findUserOrder102Entries(from, to);
        if (data == null) {
            throw new IllegalStateException("Find entries implementation for 'UserOrder102' illegally returned null");
        }
        if (!data.isEmpty()) {
            return;
        }
        
        data = new ArrayList<UserOrder102>();
        for (int i = 0; i < 10; i++) {
            UserOrder102 obj = getNewTransientUserOrder102(i);
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