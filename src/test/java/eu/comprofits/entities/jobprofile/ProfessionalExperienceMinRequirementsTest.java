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
public class ProfessionalExperienceMinRequirementsTest {
    
    public ProfessionalExperienceMinRequirementsTest() {
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
     * Test of ProfessionalExperienceMinRequirements constructor, of class ProfessionalExperienceMinRequirements.
     */
    @Test
    public void testProfessionalExperienceMinRequirements() {
        System.out.println("ProfessionalExperienceMinRequirements");
        Integer idResult = 1;
        ProfessionalExperienceMinRequirements instance = new ProfessionalExperienceMinRequirements(idResult,10,"desc"); 
        Integer result = instance.getIdprofessionalExperienceMinRequirements();
        assertEquals("error idprofessionalExperienceMinRequirements",idResult, result);
        assertEquals("error requiredExperienceYears",instance.getRequiredExperienceYears(),10);
        assertEquals("error requiredProfExperienceDescription",instance.getRequiredProfExperienceDescription(),"desc");

    }

    /**
     * Test of getIdprofessionalExperienceMinRequirements method, of class ProfessionalExperienceMinRequirements.
     */
    @Test
    public void testGetSetIdprofessionalExperienceMinRequirements() {
        System.out.println("getsetIdprofessionalExperienceMinRequirements");
        ProfessionalExperienceMinRequirements instance = new ProfessionalExperienceMinRequirements();
        Integer expResult = 1;
        instance.setIdprofessionalExperienceMinRequirements(expResult);
        Integer result = instance.getIdprofessionalExperienceMinRequirements();
        assertEquals(expResult, result);
    }

    /**
     * Test of getRequiredExperienceYears method, of class ProfessionalExperienceMinRequirements.
     */
    @Test
    public void testGetSetRequiredExperienceYears() {
        System.out.println("getsetRequiredExperienceYears");
        ProfessionalExperienceMinRequirements instance = new ProfessionalExperienceMinRequirements();
        int expResult = 1;
        instance.setRequiredExperienceYears(expResult);
        int result = instance.getRequiredExperienceYears();
        assertEquals(expResult, result);
    }

    /**
     * Test of getRequiredProfExperienceDescription method, of class ProfessionalExperienceMinRequirements.
     */
    @Test
    public void testGetSetRequiredProfExperienceDescription() {
        System.out.println("getsetRequiredProfExperienceDescription");
        ProfessionalExperienceMinRequirements instance = new ProfessionalExperienceMinRequirements();
        String expResult = "desc";
        instance.setRequiredProfExperienceDescription(expResult);
        String result = instance.getRequiredProfExperienceDescription();
        assertEquals(expResult, result);
    }

    /**
     * Test of getJobIdjob method, of class ProfessionalExperienceMinRequirements.
     */
    @Test
    public void testGetSetJobIdjob() {
        System.out.println("getsetJobIdjob");
        ProfessionalExperienceMinRequirements instance = new ProfessionalExperienceMinRequirements();
        Job expResult = new Job(1);
        instance.setJobIdjob(expResult);
        Job result = instance.getJobIdjob();
        assertEquals(expResult, result);
    }

    /**
     * Test of hashCode method, of class ProfessionalExperienceMinRequirements.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        ProfessionalExperienceMinRequirements instance = new ProfessionalExperienceMinRequirements();
        int expResult = 0;
        int result = instance.hashCode();
        assertEquals(expResult, result);
    }
    @Test
    public void testHashCode1() {
        System.out.println("hashCode1");
        ProfessionalExperienceMinRequirements instance = new ProfessionalExperienceMinRequirements(1);
        int expResult = 1;
        int result = instance.hashCode();
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class ProfessionalExperienceMinRequirements.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object object = null;
        ProfessionalExperienceMinRequirements instance = new ProfessionalExperienceMinRequirements();
        boolean expResult = false;
        boolean result = instance.equals(object);
        assertEquals(expResult, result);
    }
    @Test
    public void testEquals1() {
        System.out.println("equals1");
        ProfessionalExperienceMinRequirements object = new ProfessionalExperienceMinRequirements();
        ProfessionalExperienceMinRequirements instance = new ProfessionalExperienceMinRequirements(1);
        boolean expResult = false;
        boolean result = instance.equals(object);
        assertEquals(expResult, result);
    }
    @Test
    public void testEquals2() {
        System.out.println("equals2");
        ProfessionalExperienceMinRequirements object = new ProfessionalExperienceMinRequirements(1);
        ProfessionalExperienceMinRequirements instance = new ProfessionalExperienceMinRequirements();
        boolean expResult = false;
        boolean result = instance.equals(object);
        assertEquals(expResult, result);
    }
    @Test
    public void testEquals3() {
        System.out.println("equals3");
        ProfessionalExperienceMinRequirements object = new ProfessionalExperienceMinRequirements(1);
        ProfessionalExperienceMinRequirements instance = new ProfessionalExperienceMinRequirements(1);
        boolean expResult = true;
        boolean result = instance.equals(object);
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class ProfessionalExperienceMinRequirements.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Integer idResult = 1;
        ProfessionalExperienceMinRequirements instance = new ProfessionalExperienceMinRequirements(idResult);
        String expResult = "com.mycompany.mavenproject1.ProfessionalExperienceMinRequirements[ idprofessionalExperienceMinRequirements=" + idResult + " ]";
        String result = instance.toString();
        assertEquals(expResult, result);
    }
    
}
