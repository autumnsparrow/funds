// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.palmcommerce.funds.roo.service;

import com.palmcommerce.funds.roo.model.TradeReconciliationStatics;
import com.palmcommerce.funds.roo.service.TradeReconciliationStaticsServiceImpl;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

privileged aspect TradeReconciliationStaticsServiceImpl_Roo_Service {
    
    declare @type: TradeReconciliationStaticsServiceImpl: @Service;
    
    declare @type: TradeReconciliationStaticsServiceImpl: @Transactional;
    
    public long TradeReconciliationStaticsServiceImpl.countAllTradeReconciliationStaticses() {
        return TradeReconciliationStatics.countTradeReconciliationStaticses();
    }
    
    public void TradeReconciliationStaticsServiceImpl.deleteTradeReconciliationStatics(TradeReconciliationStatics tradeReconciliationStatics) {
        tradeReconciliationStatics.remove();
    }
    
    public TradeReconciliationStatics TradeReconciliationStaticsServiceImpl.findTradeReconciliationStatics(Long id) {
        return TradeReconciliationStatics.findTradeReconciliationStatics(id);
    }
    
    public List<TradeReconciliationStatics> TradeReconciliationStaticsServiceImpl.findAllTradeReconciliationStaticses() {
        return TradeReconciliationStatics.findAllTradeReconciliationStaticses();
    }
    
    public List<TradeReconciliationStatics> TradeReconciliationStaticsServiceImpl.findTradeReconciliationStaticsEntries(int firstResult, int maxResults) {
        return TradeReconciliationStatics.findTradeReconciliationStaticsEntries(firstResult, maxResults);
    }
    
    public void TradeReconciliationStaticsServiceImpl.saveTradeReconciliationStatics(TradeReconciliationStatics tradeReconciliationStatics) {
        tradeReconciliationStatics.persist();
    }
    
    public TradeReconciliationStatics TradeReconciliationStaticsServiceImpl.updateTradeReconciliationStatics(TradeReconciliationStatics tradeReconciliationStatics) {
        return tradeReconciliationStatics.merge();
    }
    
}