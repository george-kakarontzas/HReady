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
package eu.comprofits.entities.jobapplicant;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
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
public class JobApplicationTest {
    
    public JobApplicationTest() {
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
     * Test of getIdjobApplication method, of class JobApplication.
     */
    @Test
    public void testGetSetIdjobApplication() {
        System.out.println("getsetIdjobApplication");
        JobApplication instance = new JobApplication();
        Integer expResult = 1;
        instance.setIdjobApplication(expResult);
        Integer result = instance.getIdjobApplication();
        assertEquals(expResult, result);
    }


    /**
     * Test of getDate method, of class JobApplication.
     */
    @Test
    public void testGetSetDate() {
        System.out.println("getsetDate");
        JobApplication instance = new JobApplication();
        Date expResult = new Date();
        instance.setDate(expResult);
        Date result = instance.getDate();
        assertEquals(expResult, result);
    }


    /**
     * Test of getJobApplicantIdjobApplicant method, of class JobApplication.
     */
    @Test
    public void testGetSetJobApplicantIdjobApplicant() {
        System.out.println("getsetobApplicantIdjobApplicant");
        JobApplication instance = new JobApplication();
        JobApplicant expResult = new JobApplicant(1);
        instance.setJobApplicantIdjobApplicant(expResult);
        JobApplicant result = instance.getJobApplicantIdjobApplicant();
        assertEquals(expResult, result);
    }

    /**
     * Test of getJobAdvertisementIdjobAdvertisement method, of class JobApplication.
     */
    @Test
    public void testGetSetJobAdvertisementIdjobAdvertisement() {
        System.out.println("getsetJobAdvertisementIdjobAdvertisement");
        JobApplication instance = new JobApplication();
        JobAdvertisement expResult = new JobAdvertisement(1);
        instance.setJobAdvertisementIdjobAdvertisement(expResult);
        JobAdvertisement result = instance.getJobAdvertisementIdjobAdvertisement();
        assertEquals(expResult, result);
    }


    /**
     * Test of getApplicantCompetenceAssessmentCollection method, of class JobApplication.
     */
    @Test
    public void testGetSetApplicantCompetenceAssessmentCollection() {
        System.out.println("getsetApplicantCompetenceAssessmentCollection");
        JobApplication instance = new JobApplication();
        ApplicantCompetenceAssessment x1 = new ApplicantCompetenceAssessment(1);
        ApplicantCompetenceAssessment x2 = new ApplicantCompetenceAssessment(2);
        Collection<ApplicantCompetenceAssessment> expResult = new HashSet();
        expResult.add(x1);
        expResult.add(x2);
        instance.setApplicantCompetenceAssessmentCollection(expResult);
        Collection<ApplicantCompetenceAssessment> result = instance.getApplicantCompetenceAssessmentCollection();
        assertEquals(expResult, result);
    }



    /**
     * Test of hashCode method, of class JobApplication.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        JobApplication instance = new JobApplication();
        int expResult = 0;
        int result = instance.hashCode();
        assertEquals(expResult, result);
    }
    @Test
    public void testHashCode1() {
        System.out.println("hashCode1");
        JobApplication instance = new JobApplication(1);
        int expResult = 1;
        int result = instance.hashCode();
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class JobApplication.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object object = null;
        JobApplication instance = new JobApplication();
        boolean expResult = false;
        boolean result = instance.equals(object);
        assertEquals(expResult, result);
    }
    @Test
    public void testEquals1() {
        System.out.println("equals1");
        JobApplication object = new JobApplication();
        JobApplication instance = new JobApplication(1);
        boolean expResult = false;
        boolean result = instance.equals(object);
        assertEquals(expResult, result);
    }
    @Test
    public void testEquals2() {
        System.out.println("equals2");
        JobApplication object = new JobApplication(1);
        JobApplication instance = new JobApplication();
        boolean expResult = false;
        boolean result = instance.equals(object);
        assertEquals(expResult, result);
    }
    @Test
    public void testEquals3() {
        System.out.println("equals3");
        JobApplication object = new JobApplication(1);
        JobApplication instance = new JobApplication(1);
        boolean expResult = true;
        boolean result = instance.equals(object);
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class JobApplication.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Integer idResult = 1;
        JobApplication instance = new JobApplication(idResult);
        String expResult = "com.mycompany.mavenproject1.JobApplication[ idjobApplication=" + idResult + " ]";
        String result = instance.toString();
        assertEquals(expResult, result);
    }

}
