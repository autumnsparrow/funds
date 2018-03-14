// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.palmcommerce.server.v29.db;

import com.palmcommerce.server.v29.db.UserThirdDayCheck;
import com.palmcommerce.server.v29.db.UserThirdDayCheckDataOnDemand;
import com.palmcommerce.server.v29.db.UserThirdDayCheckIntegrationTest;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

privileged aspect UserThirdDayCheckIntegrationTest_Roo_IntegrationTest {
    
    declare @type: UserThirdDayCheckIntegrationTest: @RunWith(SpringJUnit4ClassRunner.class);
    
    declare @type: UserThirdDayCheckIntegrationTest: @ContextConfiguration(locations = "classpath:/META-INF/spring/applicationContext*.xml");
    
    declare @type: UserThirdDayCheckIntegrationTest: @Transactional;
    
    @Autowired
    UserThirdDayCheckDataOnDemand UserThirdDayCheckIntegrationTest.dod;
    
    @Test
    public void UserThirdDayCheckIntegrationTest.testCountUserThirdDayChecks() {
        Assert.assertNotNull("Data on demand for 'UserThirdDayCheck' failed to initialize correctly", dod.getRandomUserThirdDayCheck());
        long count = UserThirdDayCheck.countUserThirdDayChecks();
        Assert.assertTrue("Counter for 'UserThirdDayCheck' incorrectly reported there were no entries", count > 0);
    }
    
    @Test
    public void UserThirdDayCheckIntegrationTest.testFindUserThirdDayCheck() {
        UserThirdDayCheck obj = dod.getRandomUserThirdDayCheck();
        Assert.assertNotNull("Data on demand for 'UserThirdDayCheck' failed to initialize correctly", obj);
        String id = obj.getId();
        Assert.assertNotNull("Data on demand for 'UserThirdDayCheck' failed to provide an identifier", id);
        obj = UserThirdDayCheck.findUserThirdDayCheck(id);
        Assert.assertNotNull("Find method for 'UserThirdDayCheck' illegally returned null for id '" + id + "'", obj);
        Assert.assertEquals("Find method for 'UserThirdDayCheck' returned the incorrect identifier", id, obj.getId());
    }
    
    @Test
    public void UserThirdDayCheckIntegrationTest.testFindAllUserThirdDayChecks() {
        Assert.assertNotNull("Data on demand for 'UserThirdDayCheck' failed to initialize correctly", dod.getRandomUserThirdDayCheck());
        long count = UserThirdDayCheck.countUserThirdDayChecks();
        Assert.assertTrue("Too expensive to perform a find all test for 'UserThirdDayCheck', as there are " + count + " entries; set the findAllMaximum to exceed this value or set findAll=false on the integration test annotation to disable the test", count < 250);
        List<UserThirdDayCheck> result = UserThirdDayCheck.findAllUserThirdDayChecks();
        Assert.assertNotNull("Find all method for 'UserThirdDayCheck' illegally returned null", result);
        Assert.assertTrue("Find all method for 'UserThirdDayCheck' failed to return any data", result.size() > 0);
    }
    
    @Test
    public void UserThirdDayCheckIntegrationTest.testFindUserThirdDayCheckEntries() {
        Assert.assertNotNull("Data on demand for 'UserThirdDayCheck' failed to initialize correctly", dod.getRandomUserThirdDayCheck());
        long count = UserThirdDayCheck.countUserThirdDayChecks();
        if (count > 20) count = 20;
        int firstResult = 0;
        int maxResults = (int) count;
        List<UserThirdDayCheck> result = UserThirdDayCheck.findUserThirdDayCheckEntries(firstResult, maxResults);
        Assert.assertNotNull("Find entries method for 'UserThirdDayCheck' illegally returned null", result);
        Assert.assertEquals("Find entries method for 'UserThirdDayCheck' returned an incorrect number of entries", count, result.size());
    }
    
    @Test
    public void UserThirdDayCheckIntegrationTest.testPersist() {
        Assert.assertNotNull("Data on demand for 'UserThirdDayCheck' failed to initialize correctly", dod.getRandomUserThirdDayCheck());
        UserThirdDayCheck obj = dod.getNewTransientUserThirdDayCheck(Integer.MAX_VALUE);
        Assert.assertNotNull("Data on demand for 'UserThirdDayCheck' failed to provide a new transient entity", obj);
        Assert.assertNull("Expected 'UserThirdDayCheck' identifier to be null", obj.getId());
        obj.persist();
        obj.flush();
        Assert.assertNotNull("Expected 'UserThirdDayCheck' identifier to no longer be null", obj.getId());
    }
    
    @Test
    public void UserThirdDayCheckIntegrationTest.testRemove() {
        UserThirdDayCheck obj = dod.getRandomUserThirdDayCheck();
        Assert.assertNotNull("Data on demand for 'UserThirdDayCheck' failed to initialize correctly", obj);
        String id = obj.getId();
        Assert.assertNotNull("Data on demand for 'UserThirdDayCheck' failed to provide an identifier", id);
        obj = UserThirdDayCheck.findUserThirdDayCheck(id);
        obj.remove();
        obj.flush();
        Assert.assertNull("Failed to remove 'UserThirdDayCheck' with identifier '" + id + "'", UserThirdDayCheck.findUserThirdDayCheck(id));
    }
    
}