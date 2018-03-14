// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.palmcommerce.server.v29.db;

import com.palmcommerce.server.v29.db.UserChargeDataOnDemand;
import com.palmcommerce.server.v29.db.UserChargeIntegrationTest;
import com.palmcommerce.server.v29.service.UserChargeService;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

privileged aspect UserChargeIntegrationTest_Roo_IntegrationTest {
    
    declare @type: UserChargeIntegrationTest: @RunWith(SpringJUnit4ClassRunner.class);
    
    declare @type: UserChargeIntegrationTest: @ContextConfiguration(locations = "classpath:/META-INF/spring/applicationContext*.xml");
    
    declare @type: UserChargeIntegrationTest: @Transactional;
    
    @Autowired
    UserChargeDataOnDemand UserChargeIntegrationTest.dod;
    
    @Autowired
    UserChargeService UserChargeIntegrationTest.userChargeService;
    
    @Test
    public void UserChargeIntegrationTest.testCountAllUserCharges() {
        Assert.assertNotNull("Data on demand for 'UserCharge' failed to initialize correctly", dod.getRandomUserCharge());
        long count = userChargeService.countAllUserCharges();
        Assert.assertTrue("Counter for 'UserCharge' incorrectly reported there were no entries", count > 0);
    }
    
    @Test
    public void UserChargeIntegrationTest.testFindUserCharge() {
        UserCharge obj = dod.getRandomUserCharge();
        Assert.assertNotNull("Data on demand for 'UserCharge' failed to initialize correctly", obj);
        String id = obj.getChargeId();
        Assert.assertNotNull("Data on demand for 'UserCharge' failed to provide an identifier", id);
        obj = userChargeService.findUserCharge(id);
        Assert.assertNotNull("Find method for 'UserCharge' illegally returned null for id '" + id + "'", obj);
        Assert.assertEquals("Find method for 'UserCharge' returned the incorrect identifier", id, obj.getChargeId());
    }
    
    @Test
    public void UserChargeIntegrationTest.testFindAllUserCharges() {
        Assert.assertNotNull("Data on demand for 'UserCharge' failed to initialize correctly", dod.getRandomUserCharge());
        long count = userChargeService.countAllUserCharges();
        Assert.assertTrue("Too expensive to perform a find all test for 'UserCharge', as there are " + count + " entries; set the findAllMaximum to exceed this value or set findAll=false on the integration test annotation to disable the test", count < 250);
        List<UserCharge> result = userChargeService.findAllUserCharges();
        Assert.assertNotNull("Find all method for 'UserCharge' illegally returned null", result);
        Assert.assertTrue("Find all method for 'UserCharge' failed to return any data", result.size() > 0);
    }
    
    @Test
    public void UserChargeIntegrationTest.testFindUserChargeEntries() {
        Assert.assertNotNull("Data on demand for 'UserCharge' failed to initialize correctly", dod.getRandomUserCharge());
        long count = userChargeService.countAllUserCharges();
        if (count > 20) count = 20;
        int firstResult = 0;
        int maxResults = (int) count;
        List<UserCharge> result = userChargeService.findUserChargeEntries(firstResult, maxResults);
        Assert.assertNotNull("Find entries method for 'UserCharge' illegally returned null", result);
        Assert.assertEquals("Find entries method for 'UserCharge' returned an incorrect number of entries", count, result.size());
    }
    
    @Test
    public void UserChargeIntegrationTest.testSaveUserCharge() {
        Assert.assertNotNull("Data on demand for 'UserCharge' failed to initialize correctly", dod.getRandomUserCharge());
        UserCharge obj = dod.getNewTransientUserCharge(Integer.MAX_VALUE);
        Assert.assertNotNull("Data on demand for 'UserCharge' failed to provide a new transient entity", obj);
        Assert.assertNull("Expected 'UserCharge' identifier to be null", obj.getChargeId());
        userChargeService.saveUserCharge(obj);
        obj.flush();
        Assert.assertNotNull("Expected 'UserCharge' identifier to no longer be null", obj.getChargeId());
    }
    
    @Test
    public void UserChargeIntegrationTest.testDeleteUserCharge() {
        UserCharge obj = dod.getRandomUserCharge();
        Assert.assertNotNull("Data on demand for 'UserCharge' failed to initialize correctly", obj);
        String id = obj.getChargeId();
        Assert.assertNotNull("Data on demand for 'UserCharge' failed to provide an identifier", id);
        obj = userChargeService.findUserCharge(id);
        userChargeService.deleteUserCharge(obj);
        obj.flush();
        Assert.assertNull("Failed to remove 'UserCharge' with identifier '" + id + "'", userChargeService.findUserCharge(id));
    }
    
}