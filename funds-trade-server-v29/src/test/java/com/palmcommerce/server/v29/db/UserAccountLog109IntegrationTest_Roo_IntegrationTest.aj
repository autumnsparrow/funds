// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.palmcommerce.server.v29.db;

import com.palmcommerce.server.v29.db.UserAccountLog109DataOnDemand;
import com.palmcommerce.server.v29.db.UserAccountLog109IntegrationTest;
import com.palmcommerce.server.v29.service.UserAccountLogService;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

privileged aspect UserAccountLog109IntegrationTest_Roo_IntegrationTest {
    
    declare @type: UserAccountLog109IntegrationTest: @RunWith(SpringJUnit4ClassRunner.class);
    
    declare @type: UserAccountLog109IntegrationTest: @ContextConfiguration(locations = "classpath:/META-INF/spring/applicationContext*.xml");
    
    declare @type: UserAccountLog109IntegrationTest: @Transactional;
    
    @Autowired
    UserAccountLog109DataOnDemand UserAccountLog109IntegrationTest.dod;
    
    @Autowired
    UserAccountLogService UserAccountLog109IntegrationTest.userAccountLogService;
    
    @Test
    public void UserAccountLog109IntegrationTest.testCountAllUserAccountLog109s() {
        Assert.assertNotNull("Data on demand for 'UserAccountLog109' failed to initialize correctly", dod.getRandomUserAccountLog109());
        long count = userAccountLogService.countAllUserAccountLog109s();
        Assert.assertTrue("Counter for 'UserAccountLog109' incorrectly reported there were no entries", count > 0);
    }
    
    @Test
    public void UserAccountLog109IntegrationTest.testFindUserAccountLog109() {
        UserAccountLog109 obj = dod.getRandomUserAccountLog109();
        Assert.assertNotNull("Data on demand for 'UserAccountLog109' failed to initialize correctly", obj);
        String id = obj.getLogId();
        Assert.assertNotNull("Data on demand for 'UserAccountLog109' failed to provide an identifier", id);
        obj = userAccountLogService.findUserAccountLog109(id);
        Assert.assertNotNull("Find method for 'UserAccountLog109' illegally returned null for id '" + id + "'", obj);
        Assert.assertEquals("Find method for 'UserAccountLog109' returned the incorrect identifier", id, obj.getLogId());
    }
    
    @Test
    public void UserAccountLog109IntegrationTest.testFindAllUserAccountLog109s() {
        Assert.assertNotNull("Data on demand for 'UserAccountLog109' failed to initialize correctly", dod.getRandomUserAccountLog109());
        long count = userAccountLogService.countAllUserAccountLog109s();
        Assert.assertTrue("Too expensive to perform a find all test for 'UserAccountLog109', as there are " + count + " entries; set the findAllMaximum to exceed this value or set findAll=false on the integration test annotation to disable the test", count < 250);
        List<UserAccountLog109> result = userAccountLogService.findAllUserAccountLog109s();
        Assert.assertNotNull("Find all method for 'UserAccountLog109' illegally returned null", result);
        Assert.assertTrue("Find all method for 'UserAccountLog109' failed to return any data", result.size() > 0);
    }
    
    @Test
    public void UserAccountLog109IntegrationTest.testFindUserAccountLog109Entries() {
        Assert.assertNotNull("Data on demand for 'UserAccountLog109' failed to initialize correctly", dod.getRandomUserAccountLog109());
        long count = userAccountLogService.countAllUserAccountLog109s();
        if (count > 20) count = 20;
        int firstResult = 0;
        int maxResults = (int) count;
        List<UserAccountLog109> result = userAccountLogService.findUserAccountLog109Entries(firstResult, maxResults);
        Assert.assertNotNull("Find entries method for 'UserAccountLog109' illegally returned null", result);
        Assert.assertEquals("Find entries method for 'UserAccountLog109' returned an incorrect number of entries", count, result.size());
    }
    
    @Test
    public void UserAccountLog109IntegrationTest.testSaveUserAccountLog109() {
        Assert.assertNotNull("Data on demand for 'UserAccountLog109' failed to initialize correctly", dod.getRandomUserAccountLog109());
        UserAccountLog109 obj = dod.getNewTransientUserAccountLog109(Integer.MAX_VALUE);
        Assert.assertNotNull("Data on demand for 'UserAccountLog109' failed to provide a new transient entity", obj);
        Assert.assertNull("Expected 'UserAccountLog109' identifier to be null", obj.getLogId());
        userAccountLogService.saveUserAccountLog109(obj);
        obj.flush();
        Assert.assertNotNull("Expected 'UserAccountLog109' identifier to no longer be null", obj.getLogId());
    }
    
    @Test
    public void UserAccountLog109IntegrationTest.testDeleteUserAccountLog109() {
        UserAccountLog109 obj = dod.getRandomUserAccountLog109();
        Assert.assertNotNull("Data on demand for 'UserAccountLog109' failed to initialize correctly", obj);
        String id = obj.getLogId();
        Assert.assertNotNull("Data on demand for 'UserAccountLog109' failed to provide an identifier", id);
        obj = userAccountLogService.findUserAccountLog109(id);
        userAccountLogService.deleteUserAccountLog109(obj);
        obj.flush();
        Assert.assertNull("Failed to remove 'UserAccountLog109' with identifier '" + id + "'", userAccountLogService.findUserAccountLog109(id));
    }
    
}
