// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.palmcommerce.server.v29.db;

import com.palmcommerce.server.v29.db.UserOrderCancle;
import com.palmcommerce.server.v29.db.UserOrderCancleDataOnDemand;
import com.palmcommerce.server.v29.db.UserOrderCancleIntegrationTest;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

privileged aspect UserOrderCancleIntegrationTest_Roo_IntegrationTest {
    
    declare @type: UserOrderCancleIntegrationTest: @RunWith(SpringJUnit4ClassRunner.class);
    
    declare @type: UserOrderCancleIntegrationTest: @ContextConfiguration(locations = "classpath:/META-INF/spring/applicationContext*.xml");
    
    declare @type: UserOrderCancleIntegrationTest: @Transactional;
    
    @Autowired
    UserOrderCancleDataOnDemand UserOrderCancleIntegrationTest.dod;
    
    @Test
    public void UserOrderCancleIntegrationTest.testCountUserOrderCancles() {
        Assert.assertNotNull("Data on demand for 'UserOrderCancle' failed to initialize correctly", dod.getRandomUserOrderCancle());
        long count = UserOrderCancle.countUserOrderCancles();
        Assert.assertTrue("Counter for 'UserOrderCancle' incorrectly reported there were no entries", count > 0);
    }
    
    @Test
    public void UserOrderCancleIntegrationTest.testFindUserOrderCancle() {
        UserOrderCancle obj = dod.getRandomUserOrderCancle();
        Assert.assertNotNull("Data on demand for 'UserOrderCancle' failed to initialize correctly", obj);
        String id = obj.getOrderId();
        Assert.assertNotNull("Data on demand for 'UserOrderCancle' failed to provide an identifier", id);
        obj = UserOrderCancle.findUserOrderCancle(id);
        Assert.assertNotNull("Find method for 'UserOrderCancle' illegally returned null for id '" + id + "'", obj);
        Assert.assertEquals("Find method for 'UserOrderCancle' returned the incorrect identifier", id, obj.getOrderId());
    }
    
    @Test
    public void UserOrderCancleIntegrationTest.testFindAllUserOrderCancles() {
        Assert.assertNotNull("Data on demand for 'UserOrderCancle' failed to initialize correctly", dod.getRandomUserOrderCancle());
        long count = UserOrderCancle.countUserOrderCancles();
        Assert.assertTrue("Too expensive to perform a find all test for 'UserOrderCancle', as there are " + count + " entries; set the findAllMaximum to exceed this value or set findAll=false on the integration test annotation to disable the test", count < 250);
        List<UserOrderCancle> result = UserOrderCancle.findAllUserOrderCancles();
        Assert.assertNotNull("Find all method for 'UserOrderCancle' illegally returned null", result);
        Assert.assertTrue("Find all method for 'UserOrderCancle' failed to return any data", result.size() > 0);
    }
    
    @Test
    public void UserOrderCancleIntegrationTest.testFindUserOrderCancleEntries() {
        Assert.assertNotNull("Data on demand for 'UserOrderCancle' failed to initialize correctly", dod.getRandomUserOrderCancle());
        long count = UserOrderCancle.countUserOrderCancles();
        if (count > 20) count = 20;
        int firstResult = 0;
        int maxResults = (int) count;
        List<UserOrderCancle> result = UserOrderCancle.findUserOrderCancleEntries(firstResult, maxResults);
        Assert.assertNotNull("Find entries method for 'UserOrderCancle' illegally returned null", result);
        Assert.assertEquals("Find entries method for 'UserOrderCancle' returned an incorrect number of entries", count, result.size());
    }
    
    @Test
    public void UserOrderCancleIntegrationTest.testPersist() {
        Assert.assertNotNull("Data on demand for 'UserOrderCancle' failed to initialize correctly", dod.getRandomUserOrderCancle());
        UserOrderCancle obj = dod.getNewTransientUserOrderCancle(Integer.MAX_VALUE);
        Assert.assertNotNull("Data on demand for 'UserOrderCancle' failed to provide a new transient entity", obj);
        Assert.assertNull("Expected 'UserOrderCancle' identifier to be null", obj.getOrderId());
        obj.persist();
        obj.flush();
        Assert.assertNotNull("Expected 'UserOrderCancle' identifier to no longer be null", obj.getOrderId());
    }
    
    @Test
    public void UserOrderCancleIntegrationTest.testRemove() {
        UserOrderCancle obj = dod.getRandomUserOrderCancle();
        Assert.assertNotNull("Data on demand for 'UserOrderCancle' failed to initialize correctly", obj);
        String id = obj.getOrderId();
        Assert.assertNotNull("Data on demand for 'UserOrderCancle' failed to provide an identifier", id);
        obj = UserOrderCancle.findUserOrderCancle(id);
        obj.remove();
        obj.flush();
        Assert.assertNull("Failed to remove 'UserOrderCancle' with identifier '" + id + "'", UserOrderCancle.findUserOrderCancle(id));
    }
    
}