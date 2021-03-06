// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.palmcommerce.server.v29.db;

import com.palmcommerce.server.v29.db.UserInfo;
import com.palmcommerce.server.v29.db.UserInfoDataOnDemand;
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

privileged aspect UserInfoDataOnDemand_Roo_DataOnDemand {
    
    declare @type: UserInfoDataOnDemand: @Component;
    
    private Random UserInfoDataOnDemand.rnd = new SecureRandom();
    
    private List<UserInfo> UserInfoDataOnDemand.data;
    
    public UserInfo UserInfoDataOnDemand.getNewTransientUserInfo(int index) {
        UserInfo obj = new UserInfo();
        setCreateTime(obj, index);
        setEmail(obj, index);
        setEmailStatus(obj, index);
        setFrezzTime(obj, index);
        setFrezzType(obj, index);
        setIvrPwd(obj, index);
        setLastOptTime(obj, index);
        setLevelType(obj, index);
        setPartnerId(obj, index);
        setPasswd(obj, index);
        setPhone(obj, index);
        setPwdAnswer(obj, index);
        setPwdQuestion(obj, index);
        setRegisterFrom(obj, index);
        setStatus(obj, index);
        setUserProvince(obj, index);
        return obj;
    }
    
    public void UserInfoDataOnDemand.setCreateTime(UserInfo obj, int index) {
        Date createTime = new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH), Calendar.getInstance().get(Calendar.HOUR_OF_DAY), Calendar.getInstance().get(Calendar.MINUTE), Calendar.getInstance().get(Calendar.SECOND) + new Double(Math.random() * 1000).intValue()).getTime();
        obj.setCreateTime(createTime);
    }
    
    public void UserInfoDataOnDemand.setEmail(UserInfo obj, int index) {
        String email = "foo" + index + "@bar.com";
        if (email.length() > 50) {
            email = email.substring(0, 50);
        }
        obj.setEmail(email);
    }
    
    public void UserInfoDataOnDemand.setEmailStatus(UserInfo obj, int index) {
        BigDecimal emailStatus = BigDecimal.valueOf(index);
        obj.setEmailStatus(emailStatus);
    }
    
    public void UserInfoDataOnDemand.setFrezzTime(UserInfo obj, int index) {
        Date frezzTime = new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH), Calendar.getInstance().get(Calendar.HOUR_OF_DAY), Calendar.getInstance().get(Calendar.MINUTE), Calendar.getInstance().get(Calendar.SECOND) + new Double(Math.random() * 1000).intValue()).getTime();
        obj.setFrezzTime(frezzTime);
    }
    
    public void UserInfoDataOnDemand.setFrezzType(UserInfo obj, int index) {
        BigDecimal frezzType = BigDecimal.valueOf(index);
        obj.setFrezzType(frezzType);
    }
    
    public void UserInfoDataOnDemand.setIvrPwd(UserInfo obj, int index) {
        String ivrPwd = "ivrPwd_" + index;
        if (ivrPwd.length() > 20) {
            ivrPwd = ivrPwd.substring(0, 20);
        }
        obj.setIvrPwd(ivrPwd);
    }
    
    public void UserInfoDataOnDemand.setLastOptTime(UserInfo obj, int index) {
        Date lastOptTime = new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH), Calendar.getInstance().get(Calendar.HOUR_OF_DAY), Calendar.getInstance().get(Calendar.MINUTE), Calendar.getInstance().get(Calendar.SECOND) + new Double(Math.random() * 1000).intValue()).getTime();
        obj.setLastOptTime(lastOptTime);
    }
    
    public void UserInfoDataOnDemand.setLevelType(UserInfo obj, int index) {
        BigDecimal levelType = BigDecimal.valueOf(index);
        obj.setLevelType(levelType);
    }
    
    public void UserInfoDataOnDemand.setPartnerId(UserInfo obj, int index) {
        String partnerId = "partnerId_" + index;
        if (partnerId.length() > 20) {
            partnerId = partnerId.substring(0, 20);
        }
        obj.setPartnerId(partnerId);
    }
    
    public void UserInfoDataOnDemand.setPasswd(UserInfo obj, int index) {
        String passwd = "passwd_" + index;
        if (passwd.length() > 50) {
            passwd = passwd.substring(0, 50);
        }
        obj.setPasswd(passwd);
    }
    
    public void UserInfoDataOnDemand.setPhone(UserInfo obj, int index) {
        String phone = "phone_" + index;
        if (phone.length() > 20) {
            phone = phone.substring(0, 20);
        }
        obj.setPhone(phone);
    }
    
    public void UserInfoDataOnDemand.setPwdAnswer(UserInfo obj, int index) {
        String pwdAnswer = "pwdAnswer_" + index;
        if (pwdAnswer.length() > 50) {
            pwdAnswer = pwdAnswer.substring(0, 50);
        }
        obj.setPwdAnswer(pwdAnswer);
    }
    
    public void UserInfoDataOnDemand.setPwdQuestion(UserInfo obj, int index) {
        String pwdQuestion = "pwdQuestion_" + index;
        if (pwdQuestion.length() > 50) {
            pwdQuestion = pwdQuestion.substring(0, 50);
        }
        obj.setPwdQuestion(pwdQuestion);
    }
    
    public void UserInfoDataOnDemand.setRegisterFrom(UserInfo obj, int index) {
        BigDecimal registerFrom = BigDecimal.valueOf(index);
        obj.setRegisterFrom(registerFrom);
    }
    
    public void UserInfoDataOnDemand.setStatus(UserInfo obj, int index) {
        BigDecimal status = BigDecimal.valueOf(index);
        obj.setStatus(status);
    }
    
    public void UserInfoDataOnDemand.setUserProvince(UserInfo obj, int index) {
        String userProvince = "userProv_" + index;
        if (userProvince.length() > 10) {
            userProvince = userProvince.substring(0, 10);
        }
        obj.setUserProvince(userProvince);
    }
    
    public UserInfo UserInfoDataOnDemand.getSpecificUserInfo(int index) {
        init();
        if (index < 0) {
            index = 0;
        }
        if (index > (data.size() - 1)) {
            index = data.size() - 1;
        }
        UserInfo obj = data.get(index);
        String id = obj.getUserId();
        return UserInfo.findUserInfo(id);
    }
    
    public UserInfo UserInfoDataOnDemand.getRandomUserInfo() {
        init();
        UserInfo obj = data.get(rnd.nextInt(data.size()));
        String id = obj.getUserId();
        return UserInfo.findUserInfo(id);
    }
    
    public boolean UserInfoDataOnDemand.modifyUserInfo(UserInfo obj) {
        return false;
    }
    
    public void UserInfoDataOnDemand.init() {
        int from = 0;
        int to = 10;
        data = UserInfo.findUserInfoEntries(from, to);
        if (data == null) {
            throw new IllegalStateException("Find entries implementation for 'UserInfo' illegally returned null");
        }
        if (!data.isEmpty()) {
            return;
        }
        
        data = new ArrayList<UserInfo>();
        for (int i = 0; i < 10; i++) {
            UserInfo obj = getNewTransientUserInfo(i);
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
