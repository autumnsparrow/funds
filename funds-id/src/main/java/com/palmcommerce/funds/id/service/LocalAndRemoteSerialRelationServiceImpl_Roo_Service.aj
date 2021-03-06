// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.palmcommerce.funds.id.service;

import com.palmcommerce.funds.id.model.LocalAndRemoteSerialRelation;
import com.palmcommerce.funds.id.service.LocalAndRemoteSerialRelationServiceImpl;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

privileged aspect LocalAndRemoteSerialRelationServiceImpl_Roo_Service {
    
    declare @type: LocalAndRemoteSerialRelationServiceImpl: @Service;
    
    declare @type: LocalAndRemoteSerialRelationServiceImpl: @Transactional;
    
    public long LocalAndRemoteSerialRelationServiceImpl.countAllLocalAndRemoteSerialRelations() {
        return LocalAndRemoteSerialRelation.countLocalAndRemoteSerialRelations();
    }
    
    public void LocalAndRemoteSerialRelationServiceImpl.deleteLocalAndRemoteSerialRelation(LocalAndRemoteSerialRelation localAndRemoteSerialRelation) {
        localAndRemoteSerialRelation.remove();
    }
    
    public LocalAndRemoteSerialRelation LocalAndRemoteSerialRelationServiceImpl.findLocalAndRemoteSerialRelation(Long id) {
        return LocalAndRemoteSerialRelation.findLocalAndRemoteSerialRelation(id);
    }
    
    public List<LocalAndRemoteSerialRelation> LocalAndRemoteSerialRelationServiceImpl.findAllLocalAndRemoteSerialRelations() {
        return LocalAndRemoteSerialRelation.findAllLocalAndRemoteSerialRelations();
    }
    
    public List<LocalAndRemoteSerialRelation> LocalAndRemoteSerialRelationServiceImpl.findLocalAndRemoteSerialRelationEntries(int firstResult, int maxResults) {
        return LocalAndRemoteSerialRelation.findLocalAndRemoteSerialRelationEntries(firstResult, maxResults);
    }
    
    public void LocalAndRemoteSerialRelationServiceImpl.saveLocalAndRemoteSerialRelation(LocalAndRemoteSerialRelation localAndRemoteSerialRelation) {
        localAndRemoteSerialRelation.persist();
    }
    
    public LocalAndRemoteSerialRelation LocalAndRemoteSerialRelationServiceImpl.updateLocalAndRemoteSerialRelation(LocalAndRemoteSerialRelation localAndRemoteSerialRelation) {
        return localAndRemoteSerialRelation.merge();
    }
    
}
