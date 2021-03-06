// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.palmcommerce.server.v29.db;

import com.palmcommerce.server.v29.db.UserLimitRecord;
import com.palmcommerce.server.v29.db.UserLimitRecordDataOnDemand;
import com.palmcommerce.server.v29.db.UserLimitRecordIntegrationTest;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

privileged aspect UserLimitRecordIntegrationTest_Roo_IntegrationTest {
    
    declare @type: UserLimitRecordIntegrationTest: @RunWith(SpringJUnit4ClassRunner.class);
    
    declare @type: UserLimitRecordIntegrationTest: @ContextConfiguration(locations = "classpath:/META-INF/spring/applicationContext*.xml");
    
    declare @type: UserLimitRecordIntegrationTest: @Transactional;
    
    @Autowired
    UserLimitRecordDataOnDemand UserLimitRecordIntegrationTest.dod;
    
    @Test
    public void UserLimitRecordIntegrationTest.testCountUserLimitRecords() {
        Assert.assertNotNull("Data on demand for 'UserLimitRecord' failed to initialize correctly", dod.getRandomUserLimitRecord());
        long count = UserLimitRecord.countUserLimitRecords();
        Assert.assertTrue("Counter for 'UserLimitRecord' incorrectly reported there were no entries", count > 0);
    }
    
    @Test
    public void UserLimitRecordIntegrationTest.testFindUserLimitRecord() {
        UserLimitRecord obj = dod.getRandomUserLimitRecord();
        Assert.assertNotNull("Data on demand for 'UserLimitRecord' failed to initialize correctly", obj);
        String id = obj.getLimitId();
        Assert.assertNotNull("Data on demand for 'UserLimitRecord' failed to provide an identifier", id);
        obj = UserLimitRecord.findUserLimitRecord(id);
        Assert.assertNotNull("Find method for 'UserLimitRecord' illegally returned null for id '" + id + "'", obj);
        Assert.assertEquals("Find method for 'UserLimitRecord' returned the incorrect identifier", id, obj.getLimitId());
    }
    
    @Test
    public void UserLimitRecordIntegrationTest.testFindAllUserLimitRecords() {
        Assert.assertNotNull("Data on demand for 'UserLimitRecord' failed to initialize correctly", dod.getRandomUserLimitRecord());
        long count = UserLimitRecord.countUserLimitRecords();
        Assert.assertTrue("Too expensive to perform a find all test for 'UserLimitRecord', as there are " + count + " entries; set the findAllMaximum to exceed this value or set findAll=false on the integration test annotation to disable the test", count < 250);
        List<UserLimitRecord> result = UserLimitRecord.findAllUserLimitRecords();
        Assert.assertNotNull("Find all method for 'UserLimitRecord' illegally returned null", result);
        Assert.assertTrue("Find all method for 'UserLimitRecord' failed to return any data", result.size() > 0);
    }
    
    @Test
    public void UserLimitRecordIntegrationTest.testFindUserLimitRecordEntries() {
        Assert.assertNotNull("Data on demand for 'UserLimitRecord' failed to initialize correctly", dod.getRandomUserLimitRecord());
        long count = UserLimitRecord.countUserLimitRecords();
        if (count > 20) count = 20;
        int firstResult = 0;
        int maxResults = (int) count;
        List<UserLimitRecord> result = UserLimitRecord.findUserLimitRecordEntries(firstResult, maxResults);
        Assert.assertNotNull("Find entries method for 'UserLimitRecord' illegally returned null", result);
        Assert.assertEquals("Find entries method for 'UserLimitRecord' returned an incorrect number of entries", count, result.size());
    }
    
    @Test
    public void UserLimitRecordIntegrationTest.testPersist() {
        Assert.assertNotNull("Data on demand for 'UserLimitRecord' failed to initialize correctly", dod.getRandomUserLimitRecord());
        UserLimitRecord obj = dod.getNewTransientUserLimitRecord(Integer.MAX_VALUE);
        Assert.assertNotNull("Data on demand for 'UserLimitRecord' failed to provide a new transient entity", obj);
        Assert.assertNull("Expected 'UserLimitRecord' identifier to be null", obj.getLimitId());
        obj.persist();
        obj.flush();
        Assert.assertNotNull("Expected 'UserLimitRecord' identifier to no longer be null", obj.getLimitId());
    }
    
    @Test
    public void UserLimitRecordIntegrationTest.testRemove() {
        UserLimitRecord obj = dod.getRandomUserLimitRecord();
        Assert.assertNotNull("Data on demand for 'UserLimitRecord' failed to initialize correctly", obj);
        String id = obj.getLimitId();
        Assert.assertNotNull("Data on demand for 'UserLimitRecord' failed to provide an identifier", id);
        obj = UserLimitRecord.findUserLimitRecord(id);
        obj.remove();
        obj.flush();
        Assert.assertNull("Failed to remove 'UserLimitRecord' with identifier '" + id + "'", UserLimitRecord.findUserLimitRecord(id));
    }
    
}
