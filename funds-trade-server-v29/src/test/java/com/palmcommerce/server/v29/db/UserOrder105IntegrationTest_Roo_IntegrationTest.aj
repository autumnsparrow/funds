// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.palmcommerce.server.v29.db;

import com.palmcommerce.server.v29.db.UserOrder105;
import com.palmcommerce.server.v29.db.UserOrder105DataOnDemand;
import com.palmcommerce.server.v29.db.UserOrder105IntegrationTest;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

privileged aspect UserOrder105IntegrationTest_Roo_IntegrationTest {
    
    declare @type: UserOrder105IntegrationTest: @RunWith(SpringJUnit4ClassRunner.class);
    
    declare @type: UserOrder105IntegrationTest: @ContextConfiguration(locations = "classpath:/META-INF/spring/applicationContext*.xml");
    
    declare @type: UserOrder105IntegrationTest: @Transactional;
    
    @Autowired
    UserOrder105DataOnDemand UserOrder105IntegrationTest.dod;
    
    @Test
    public void UserOrder105IntegrationTest.testCountUserOrder105s() {
        Assert.assertNotNull("Data on demand for 'UserOrder105' failed to initialize correctly", dod.getRandomUserOrder105());
        long count = UserOrder105.countUserOrder105s();
        Assert.assertTrue("Counter for 'UserOrder105' incorrectly reported there were no entries", count > 0);
    }
    
    @Test
    public void UserOrder105IntegrationTest.testFindUserOrder105() {
        UserOrder105 obj = dod.getRandomUserOrder105();
        Assert.assertNotNull("Data on demand for 'UserOrder105' failed to initialize correctly", obj);
        String id = obj.getOrderId();
        Assert.assertNotNull("Data on demand for 'UserOrder105' failed to provide an identifier", id);
        obj = UserOrder105.findUserOrder105(id);
        Assert.assertNotNull("Find method for 'UserOrder105' illegally returned null for id '" + id + "'", obj);
        Assert.assertEquals("Find method for 'UserOrder105' returned the incorrect identifier", id, obj.getOrderId());
    }
    
    @Test
    public void UserOrder105IntegrationTest.testFindAllUserOrder105s() {
        Assert.assertNotNull("Data on demand for 'UserOrder105' failed to initialize correctly", dod.getRandomUserOrder105());
        long count = UserOrder105.countUserOrder105s();
        Assert.assertTrue("Too expensive to perform a find all test for 'UserOrder105', as there are " + count + " entries; set the findAllMaximum to exceed this value or set findAll=false on the integration test annotation to disable the test", count < 250);
        List<UserOrder105> result = UserOrder105.findAllUserOrder105s();
        Assert.assertNotNull("Find all method for 'UserOrder105' illegally returned null", result);
        Assert.assertTrue("Find all method for 'UserOrder105' failed to return any data", result.size() > 0);
    }
    
    @Test
    public void UserOrder105IntegrationTest.testFindUserOrder105Entries() {
        Assert.assertNotNull("Data on demand for 'UserOrder105' failed to initialize correctly", dod.getRandomUserOrder105());
        long count = UserOrder105.countUserOrder105s();
        if (count > 20) count = 20;
        int firstResult = 0;
        int maxResults = (int) count;
        List<UserOrder105> result = UserOrder105.findUserOrder105Entries(firstResult, maxResults);
        Assert.assertNotNull("Find entries method for 'UserOrder105' illegally returned null", result);
        Assert.assertEquals("Find entries method for 'UserOrder105' returned an incorrect number of entries", count, result.size());
    }
    
    @Test
    public void UserOrder105IntegrationTest.testPersist() {
        Assert.assertNotNull("Data on demand for 'UserOrder105' failed to initialize correctly", dod.getRandomUserOrder105());
        UserOrder105 obj = dod.getNewTransientUserOrder105(Integer.MAX_VALUE);
        Assert.assertNotNull("Data on demand for 'UserOrder105' failed to provide a new transient entity", obj);
        Assert.assertNull("Expected 'UserOrder105' identifier to be null", obj.getOrderId());
        obj.persist();
        obj.flush();
        Assert.assertNotNull("Expected 'UserOrder105' identifier to no longer be null", obj.getOrderId());
    }
    
    @Test
    public void UserOrder105IntegrationTest.testRemove() {
        UserOrder105 obj = dod.getRandomUserOrder105();
        Assert.assertNotNull("Data on demand for 'UserOrder105' failed to initialize correctly", obj);
        String id = obj.getOrderId();
        Assert.assertNotNull("Data on demand for 'UserOrder105' failed to provide an identifier", id);
        obj = UserOrder105.findUserOrder105(id);
        obj.remove();
        obj.flush();
        Assert.assertNull("Failed to remove 'UserOrder105' with identifier '" + id + "'", UserOrder105.findUserOrder105(id));
    }
    
}