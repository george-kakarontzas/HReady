/* 
 * Copyright 2016 ComProFITS Project.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package eu.comprofits.entities.jobprofile;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Christos
 */
public class JobStudyMinRequirementsTest {
    
    public JobStudyMinRequirementsTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getSetIdjobStudyMinRequirements method, of class JobStudyMinRequirements.
     */
    @Test
    public void testGetIdjobStudyMinRequirements() {
        System.out.println("getsetIdjobStudyMinRequirements");
        JobStudyMinRequirements instance = new JobStudyMinRequirements();
        Integer expResult = 1;
        instance.setIdjobStudyMinRequirements(expResult);
        Integer result = instance.getIdjobStudyMinRequirements();
        assertEquals(expResult, result);
    }

    /**
     * Test of getRequiredTitleType method, of class JobStudyMinRequirements.
     */
    @Test
    public void testGetSetRequiredTitleType() {
        System.out.println("getsetRequiredTitleType");
        JobStudyMinRequirements instance = new JobStudyMinRequirements();
        Integer expResult = 1;
        instance.setRequiredTitleType(expResult);
        Integer result = instance.getRequiredTitleType();
        assertEquals(expResult, result);
    }

    /**
     * Test of getJobIdjob method, of class JobStudyMinRequirements.
     */
    @Test
    public void testGetSetJobIdjob() {
        System.out.println("getsetJobIdjob");
        JobStudyMinRequirements instance = new JobStudyMinRequirements();
        Job expResult = new Job(1);
        instance.setJobIdjob(expResult);
        Job result = instance.getJobIdjob();
        assertEquals(expResult, result);
    }

    /**
     * Test of hashCode method, of class JobStudyMinRequirements.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        JobStudyMinRequirements instance = new JobStudyMinRequirements();
        int expResult = 0;
        int result = instance.hashCode();
        assertEquals(expResult, result);
    }
    @Test
    public void testHashCode1() {
        System.out.println("hashCode1");
        JobStudyMinRequirements instance = new JobStudyMinRequirements(1);
        int expResult = 1;
        int result = instance.hashCode();
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class JobStudyMinRequirements.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object object = null;
        JobStudyMinRequirements instance = new JobStudyMinRequirements();
        boolean expResult = false;
        boolean result = instance.equals(object);
        assertEquals(expResult, result);
    }
    @Test
    public void testEquals1() {
        System.out.println("equals1");
        JobStudyMinRequirements object = new JobStudyMinRequirements();
        JobStudyMinRequirements instance = new JobStudyMinRequirements(1);
        boolean expResult = false;
        boolean result = instance.equals(object);
        assertEquals(expResult, result);
    }
    @Test
    public void testEquals2() {
        System.out.println("equals2");
        JobStudyMinRequirements object = new JobStudyMinRequirements(1);
        JobStudyMinRequirements instance = new JobStudyMinRequirements();
        boolean expResult = false;
        boolean result = instance.equals(object);
        assertEquals(expResult, result);
    }
    @Test
    public void testEquals3() {
        System.out.println("equals3");
        JobStudyMinRequirements object = new JobStudyMinRequirements(1);
        JobStudyMinRequirements instance = new JobStudyMinRequirements(1);
        boolean expResult = true;
        boolean result = instance.equals(object);
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class JobStudyMinRequirements.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Integer idResult = 1;
        JobStudyMinRequirements instance = new JobStudyMinRequirements(idResult);
        String expResult = "com.mycompany.mavenproject1.JobStudyMinRequirements[ idjobStudyMinRequirements=" + idResult + " ]";
        String result = instance.toString();
        assertEquals(expResult, result);
    }
    
}
