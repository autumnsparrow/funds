// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.palmcommerce.server.v29.db;

import com.palmcommerce.server.v29.db.UserOrder108;
import com.palmcommerce.server.v29.db.UserOrder108DataOnDemand;
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

privileged aspect UserOrder108DataOnDemand_Roo_DataOnDemand {
    
    declare @type: UserOrder108DataOnDemand: @Component;
    
    private Random UserOrder108DataOnDemand.rnd = new SecureRandom();
    
    private List<UserOrder108> UserOrder108DataOnDemand.data;
    
    public UserOrder108 UserOrder108DataOnDemand.getNewTransientUserOrder108(int index) {
        UserOrder108 obj = new UserOrder108();
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
    
    public void UserOrder108DataOnDemand.setCreateTime(UserOrder108 obj, int index) {
        Date createTime = new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH), Calendar.getInstance().get(Calendar.HOUR_OF_DAY), Calendar.getInstance().get(Calendar.MINUTE), Calendar.getInstance().get(Calendar.SECOND) + new Double(Math.random() * 1000).intValue()).getTime();
        obj.setCreateTime(createTime);
    }
    
    public void UserOrder108DataOnDemand.setFailMoney(UserOrder108 obj, int index) {
        BigDecimal failMoney = BigDecimal.valueOf(index);
        if (failMoney.compareTo(new BigDecimal("9999999999999.99")) == 1) {
            failMoney = new BigDecimal("9999999999999.99");
        }
        obj.setFailMoney(failMoney);
    }
    
    public void UserOrder108DataOnDemand.setFailNum(UserOrder108 obj, int index) {
        BigDecimal failNum = BigDecimal.valueOf(index);
        obj.setFailNum(failNum);
    }
    
    public void UserOrder108DataOnDemand.setGameId(UserOrder108 obj, int index) {
        String gameId = "gameId_" + index;
        if (gameId.length() > 10) {
            gameId = gameId.substring(0, 10);
        }
        obj.setGameId(gameId);
    }
    
    public void UserOrder108DataOnDemand.setIssueNo(UserOrder108 obj, int index) {
        String issueNo = "issueNo_" + index;
        if (issueNo.length() > 20) {
            issueNo = issueNo.substring(0, 20);
        }
        obj.setIssueNo(issueNo);
    }
    
    public void UserOrder108DataOnDemand.setOrderStatus(UserOrder108 obj, int index) {
        BigDecimal orderStatus = BigDecimal.valueOf(index);
        obj.setOrderStatus(orderStatus);
    }
    
    public void UserOrder108DataOnDemand.setPartnerId(UserOrder108 obj, int index) {
        String partnerId = "partnerId_" + index;
        if (partnerId.length() > 30) {
            partnerId = new Random().nextInt(10) + partnerId.substring(1, 30);
        }
        obj.setPartnerId(partnerId);
    }
    
    public void UserOrder108DataOnDemand.setPayType(UserOrder108 obj, int index) {
        BigDecimal payType = BigDecimal.valueOf(index);
        obj.setPayType(payType);
    }
    
    public void UserOrder108DataOnDemand.setPorderId(UserOrder108 obj, int index) {
        String porderId = "porderId_" + index;
        if (porderId.length() > 30) {
            porderId = new Random().nextInt(10) + porderId.substring(1, 30);
        }
        obj.setPorderId(porderId);
    }
    
    public void UserOrder108DataOnDemand.setPrizeMoney(UserOrder108 obj, int index) {
        BigDecimal prizeMoney = BigDecimal.valueOf(index);
        if (prizeMoney.compareTo(new BigDecimal("9999999999999.99")) == 1) {
            prizeMoney = new BigDecimal("9999999999999.99");
        }
        obj.setPrizeMoney(prizeMoney);
    }
    
    public void UserOrder108DataOnDemand.setPrizeMoneyAfterTax(UserOrder108 obj, int index) {
        BigDecimal prizeMoneyAfterTax = BigDecimal.valueOf(index);
        if (prizeMoneyAfterTax.compareTo(new BigDecimal("9999999999999.99")) == 1) {
            prizeMoneyAfterTax = new BigDecimal("9999999999999.99");
        }
        obj.setPrizeMoneyAfterTax(prizeMoneyAfterTax);
    }
    
    public void UserOrder108DataOnDemand.setPrizeStatus(UserOrder108 obj, int index) {
        BigDecimal prizeStatus = BigDecimal.valueOf(index);
        obj.setPrizeStatus(prizeStatus);
    }
    
    public void UserOrder108DataOnDemand.setPrizeType(UserOrder108 obj, int index) {
        BigDecimal prizeType = BigDecimal.valueOf(index);
        obj.setPrizeType(prizeType);
    }
    
    public void UserOrder108DataOnDemand.setSucMoney(UserOrder108 obj, int index) {
        BigDecimal sucMoney = BigDecimal.valueOf(index);
        if (sucMoney.compareTo(new BigDecimal("9999999999999.99")) == 1) {
            sucMoney = new BigDecimal("9999999999999.99");
        }
        obj.setSucMoney(sucMoney);
    }
    
    public void UserOrder108DataOnDemand.setSucNum(UserOrder108 obj, int index) {
        BigDecimal sucNum = BigDecimal.valueOf(index);
        obj.setSucNum(sucNum);
    }
    
    public void UserOrder108DataOnDemand.setTicketNum(UserOrder108 obj, int index) {
        BigDecimal ticketNum = BigDecimal.valueOf(index);
        obj.setTicketNum(ticketNum);
    }
    
    public void UserOrder108DataOnDemand.setTotalMoney(UserOrder108 obj, int index) {
        BigDecimal totalMoney = BigDecimal.valueOf(index);
        if (totalMoney.compareTo(new BigDecimal("9999999999999.99")) == 1) {
            totalMoney = new BigDecimal("9999999999999.99");
        }
        obj.setTotalMoney(totalMoney);
    }
    
    public void UserOrder108DataOnDemand.setUserId(UserOrder108 obj, int index) {
        String userId = "userId_" + index;
        if (userId.length() > 30) {
            userId = userId.substring(0, 30);
        }
        obj.setUserId(userId);
    }
    
    public UserOrder108 UserOrder108DataOnDemand.getSpecificUserOrder108(int index) {
        init();
        if (index < 0) {
            index = 0;
        }
        if (index > (data.size() - 1)) {
            index = data.size() - 1;
        }
        UserOrder108 obj = data.get(index);
        String id = obj.getOrderId();
        return UserOrder108.findUserOrder108(id);
    }
    
    public UserOrder108 UserOrder108DataOnDemand.getRandomUserOrder108() {
        init();
        UserOrder108 obj = data.get(rnd.nextInt(data.size()));
        String id = obj.getOrderId();
        return UserOrder108.findUserOrder108(id);
    }
    
    public boolean UserOrder108DataOnDemand.modifyUserOrder108(UserOrder108 obj) {
        return false;
    }
    
    public void UserOrder108DataOnDemand.init() {
        int from = 0;
        int to = 10;
        data = UserOrder108.findUserOrder108Entries(from, to);
        if (data == null) {
            throw new IllegalStateException("Find entries implementation for 'UserOrder108' illegally returned null");
        }
        if (!data.isEmpty()) {
            return;
        }
        
        data = new ArrayList<UserOrder108>();
        for (int i = 0; i < 10; i++) {
            UserOrder108 obj = getNewTransientUserOrder108(i);
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
