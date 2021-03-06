// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.palmcommerce.funds.mock.client.model;

import com.palmcommerce.funds.mock.client.model.FromCodeEnum;
import com.palmcommerce.funds.mock.client.model.Protocol240001;
import com.palmcommerce.funds.mock.client.model.Protocol240001DataOnDemand;
import com.palmcommerce.funds.mock.client.model.ToCodeEnum;
import com.palmcommerce.funds.mock.client.model.TranscodeEnum;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.springframework.stereotype.Component;

privileged aspect Protocol240001DataOnDemand_Roo_DataOnDemand {
    
    declare @type: Protocol240001DataOnDemand: @Component;
    
    private Random Protocol240001DataOnDemand.rnd = new SecureRandom();
    
    private List<Protocol240001> Protocol240001DataOnDemand.data;
    
    public Protocol240001 Protocol240001DataOnDemand.getNewTransientProtocol240001(int index) {
        Protocol240001 obj = new Protocol240001();
        setFromcode(obj, index);
        setResponse(obj, index);
        setSerialNumber(obj, index);
        setSiglen(obj, index);
        setTocode(obj, index);
        setTransactionDatetime(obj, index);
        setTranscode(obj, index);
        setUserId(obj, index);
        return obj;
    }
    
    public void Protocol240001DataOnDemand.setFromcode(Protocol240001 obj, int index) {
        FromCodeEnum fromcode = FromCodeEnum.class.getEnumConstants()[0];
        obj.setFromcode(fromcode);
    }
    
    public void Protocol240001DataOnDemand.setResponse(Protocol240001 obj, int index) {
        String response = "response_" + index;
        obj.setResponse(response);
    }
    
    public void Protocol240001DataOnDemand.setSerialNumber(Protocol240001 obj, int index) {
        String serialNumber = "serialNumber_" + index;
        obj.setSerialNumber(serialNumber);
    }
    
    public void Protocol240001DataOnDemand.setSiglen(Protocol240001 obj, int index) {
        int siglen = index;
        obj.setSiglen(siglen);
    }
    
    public void Protocol240001DataOnDemand.setTocode(Protocol240001 obj, int index) {
        ToCodeEnum tocode = ToCodeEnum.class.getEnumConstants()[0];
        obj.setTocode(tocode);
    }
    
    public void Protocol240001DataOnDemand.setTransactionDatetime(Protocol240001 obj, int index) {
        String transactionDatetime = "transactionDatetime_" + index;
        obj.setTransactionDatetime(transactionDatetime);
    }
    
    public void Protocol240001DataOnDemand.setTranscode(Protocol240001 obj, int index) {
        TranscodeEnum transcode = TranscodeEnum.class.getEnumConstants()[0];
        obj.setTranscode(transcode);
    }
    
    public void Protocol240001DataOnDemand.setUserId(Protocol240001 obj, int index) {
        String userId = "userId_" + index;
        obj.setUserId(userId);
    }
    
    public Protocol240001 Protocol240001DataOnDemand.getSpecificProtocol240001(int index) {
        init();
        if (index < 0) {
            index = 0;
        }
        if (index > (data.size() - 1)) {
            index = data.size() - 1;
        }
        Protocol240001 obj = data.get(index);
        Long id = obj.getId();
        return Protocol240001.findProtocol240001(id);
    }
    
    public Protocol240001 Protocol240001DataOnDemand.getRandomProtocol240001() {
        init();
        Protocol240001 obj = data.get(rnd.nextInt(data.size()));
        Long id = obj.getId();
        return Protocol240001.findProtocol240001(id);
    }
    
    public boolean Protocol240001DataOnDemand.modifyProtocol240001(Protocol240001 obj) {
        return false;
    }
    
    public void Protocol240001DataOnDemand.init() {
        int from = 0;
        int to = 10;
        data = Protocol240001.findProtocol240001Entries(from, to);
        if (data == null) {
            throw new IllegalStateException("Find entries implementation for 'Protocol240001' illegally returned null");
        }
        if (!data.isEmpty()) {
            return;
        }
        
        data = new ArrayList<Protocol240001>();
        for (int i = 0; i < 10; i++) {
            Protocol240001 obj = getNewTransientProtocol240001(i);
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
