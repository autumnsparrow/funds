// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.palmcommerce.server.v29.db;

import com.palmcommerce.server.v29.db.UserOrder106;
import com.palmcommerce.server.v29.db.UserOrder106DataOnDemand;
import com.palmcommerce.server.v29.db.UserOrder106IntegrationTest;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

privileged aspect UserOrder106IntegrationTest_Roo_IntegrationTest {
    
    declare @type: UserOrder106IntegrationTest: @RunWith(SpringJUnit4ClassRunner.class);
    
    declare @type: UserOrder106IntegrationTest: @ContextConfiguration(locations = "classpath:/META-INF/spring/applicationContext*.xml");
    
    declare @type: UserOrder106IntegrationTest: @Transactional;
    
    @Autowired
    UserOrder106DataOnDemand UserOrder106IntegrationTest.dod;
    
    @Test
    public void UserOrder106IntegrationTest.testCountUserOrder106s() {
        Assert.assertNotNull("Data on demand for 'UserOrder106' failed to initialize correctly", dod.getRandomUserOrder106());
        long count = UserOrder106.countUserOrder106s();
        Assert.assertTrue("Counter for 'UserOrder106' incorrectly reported there were no entries", count > 0);
    }
    
    @Test
    public void UserOrder106IntegrationTest.testFindUserOrder106() {
        UserOrder106 obj = dod.getRandomUserOrder106();
        Assert.assertNotNull("Data on demand for 'UserOrder106' failed to initialize correctly", obj);
        String id = obj.getOrderId();
        Assert.assertNotNull("Data on demand for 'UserOrder106' failed to provide an identifier", id);
        obj = UserOrder106.findUserOrder106(id);
        Assert.assertNotNull("Find method for 'UserOrder106' illegally returned null for id '" + id + "'", obj);
        Assert.assertEquals("Find method for 'UserOrder106' returned the incorrect identifier", id, obj.getOrderId());
    }
    
    @Test
    public void UserOrder106IntegrationTest.testFindAllUserOrder106s() {
        Assert.assertNotNull("Data on demand for 'UserOrder106' failed to initialize correctly", dod.getRandomUserOrder106());
        long count = UserOrder106.countUserOrder106s();
        Assert.assertTrue("Too expensive to perform a find all test for 'UserOrder106', as there are " + count + " entries; set the findAllMaximum to exceed this value or set findAll=false on the integration test annotation to disable the test", count < 250);
        List<UserOrder106> result = UserOrder106.findAllUserOrder106s();
        Assert.assertNotNull("Find all method for 'UserOrder106' illegally returned null", result);
        Assert.assertTrue("Find all method for 'UserOrder106' failed to return any data", result.size() > 0);
    }
    
    @Test
    public void UserOrder106IntegrationTest.testFindUserOrder106Entries() {
        Assert.assertNotNull("Data on demand for 'UserOrder106' failed to initialize correctly", dod.getRandomUserOrder106());
        long count = UserOrder106.countUserOrder106s();
        if (count > 20) count = 20;
        int firstResult = 0;
        int maxResults = (int) count;
        List<UserOrder106> result = UserOrder106.findUserOrder106Entries(firstResult, maxResults);
        Assert.assertNotNull("Find entries method for 'UserOrder106' illegally returned null", result);
        Assert.assertEquals("Find entries method for 'UserOrder106' returned an incorrect number of entries", count, result.size());
    }
    
    @Test
    public void UserOrder106IntegrationTest.testPersist() {
        Assert.assertNotNull("Data on demand for 'UserOrder106' failed to initialize correctly", dod.getRandomUserOrder106());
        UserOrder106 obj = dod.getNewTransientUserOrder106(Integer.MAX_VALUE);
        Assert.assertNotNull("Data on demand for 'UserOrder106' failed to provide a new transient entity", obj);
        Assert.assertNull("Expected 'UserOrder106' identifier to be null", obj.getOrderId());
        obj.persist();
        obj.flush();
        Assert.assertNotNull("Expected 'UserOrder106' identifier to no longer be null", obj.getOrderId());
    }
    
    @Test
    public void UserOrder106IntegrationTest.testRemove() {
        UserOrder106 obj = dod.getRandomUserOrder106();
        Assert.assertNotNull("Data on demand for 'UserOrder106' failed to initialize correctly", obj);
        String id = obj.getOrderId();
        Assert.assertNotNull("Data on demand for 'UserOrder106' failed to provide an identifier", id);
        obj = UserOrder106.findUserOrder106(id);
        obj.remove();
        obj.flush();
        Assert.assertNull("Failed to remove 'UserOrder106' with identifier '" + id + "'", UserOrder106.findUserOrder106(id));
    }
    
}