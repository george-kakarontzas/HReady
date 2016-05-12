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
public class ApplicantProfessionalExperienceRecordTest {
    
    public ApplicantProfessionalExperienceRecordTest() {
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
     * Test of ApplicantProfessionalExperienceRecord constructor, of class ApplicantProfessionalExperienceRecord.
     */
    @Test
    public void testApplicantProfessionalExperienceRecord() {
        System.out.println("ApplicantProfessionalExperienceRecord");
        Integer idResult = 1;
        Date date = new Date();
        ApplicantProfessionalExperienceRecord instance = new ApplicantProfessionalExperienceRecord(idResult,date,date,"comp","role");
        instance.setIdapplicantProfessionalExperienceRecord(idResult);
        Integer result = instance.getIdapplicantProfessionalExperienceRecord();
        assertEquals("error idapplicantProfessionalExperienceRecord",idResult, result);
        assertEquals("error start date",date,instance.getDateStarted());
        assertEquals("error finish date",date,instance.getDateFinished());
        assertEquals("error company name","comp",instance.getCompany());
        assertEquals("error role","role",instance.getRole());
    }

    /**
     * Test of getIdapplicantProfessionalExperienceRecord method, of class ApplicantProfessionalExperienceRecord.
     */
    @Test
    public void testGetSetIdapplicantProfessionalExperienceRecord() {
        System.out.println("getsetIdapplicantProfessionalExperienceRecord");
        ApplicantProfessionalExperienceRecord instance = new ApplicantProfessionalExperienceRecord();
        Integer expResult = 1;
        instance.setIdapplicantProfessionalExperienceRecord(expResult);
        Integer result = instance.getIdapplicantProfessionalExperienceRecord();
        assertEquals(expResult, result);
    }


    /**
     * Test of getDateStarted method, of class ApplicantProfessionalExperienceRecord.
     */
    @Test
    public void testGetSetDateStarted() {
        System.out.println("getsetDateStarted");
        ApplicantProfessionalExperienceRecord instance = new ApplicantProfessionalExperienceRecord();
        Date expResult = new Date();
        instance.setDateStarted(expResult);
        Date result = instance.getDateStarted();
        assertEquals(expResult, result);
    }

    /**
     * Test of getDateFinished method, of class ApplicantProfessionalExperienceRecord.
     */
    @Test
    public void testGetSetDateFinished() {
        System.out.println("getDateFinished");
        ApplicantProfessionalExperienceRecord instance = new ApplicantProfessionalExperienceRecord();
        Date expResult = new Date();
        instance.setDateFinished(expResult);
        Date result = instance.getDateFinished();
        assertEquals(expResult, result);
    }


    /**
     * Test of getCompany method, of class ApplicantProfessionalExperienceRecord.
     */
    @Test
    public void testGetSetCompany() {
        System.out.println("getsetCompany");
        ApplicantProfessionalExperienceRecord instance = new ApplicantProfessionalExperienceRecord();
        String expResult = "comp";
        instance.setCompany(expResult);
        String result = instance.getCompany();
        assertEquals(expResult, result);
    }


    /**
     * Test of getRole method, of class ApplicantProfessionalExperienceRecord.
     */
    @Test
    public void testGetSetRole() {
        System.out.println("getsetRole");
        ApplicantProfessionalExperienceRecord instance = new ApplicantProfessionalExperienceRecord();
        String expResult = "role";
        instance.setRole(expResult);
        String result = instance.getRole();
        assertEquals(expResult, result);
    }


    /**
     * Test of getJobTitle method, of class ApplicantProfessionalExperienceRecord.
     */
    @Test
    public void testGetSetJobTitle() {
        System.out.println("getsetJobTitle");
        ApplicantProfessionalExperienceRecord instance = new ApplicantProfessionalExperienceRecord();
        String expResult = "title";
        instance.setJobTitle(expResult);
        String result = instance.getJobTitle();
        assertEquals(expResult, result);
    }


    /**
     * Test of getFieldOfWork method, of class ApplicantProfessionalExperienceRecord.
     */
    @Test
    public void testGetSetFieldOfWork() {
        System.out.println("getsetFieldOfWork");
        ApplicantProfessionalExperienceRecord instance = new ApplicantProfessionalExperienceRecord();
        String expResult = "field";
        instance.setFieldOfWork(expResult);
        String result = instance.getFieldOfWork();
        assertEquals(expResult, result);
    }

    /**
     * Test of getPlaceOfEmployment method, of class ApplicantProfessionalExperienceRecord.
     */
    @Test
    public void testGetSetPlaceOfEmployment() {
        System.out.println("getsetPlaceOfEmployment");
        ApplicantProfessionalExperienceRecord instance = new ApplicantProfessionalExperienceRecord();
        String expResult = "here";
        instance.setPlaceOfEmployment(expResult);
        String result = instance.getPlaceOfEmployment();
        assertEquals(expResult, result);
    }

    /**
     * Test of getImmediateManager method, of class ApplicantProfessionalExperienceRecord.
     */
    @Test
    public void testGetSetImmediateManager() {
        System.out.println("getsetImmediateManager");
        ApplicantProfessionalExperienceRecord instance = new ApplicantProfessionalExperienceRecord();
        String expResult = "manager";
        instance.setImmediateManager(expResult);
        String result = instance.getImmediateManager();
        assertEquals(expResult, result);
    }

    /**
     * Test of getBusinessArea method, of class ApplicantProfessionalExperienceRecord.
     */
    @Test
    public void testGetSetBusinessArea() {
        System.out.println("getsetBusinessArea");
        ApplicantProfessionalExperienceRecord instance = new ApplicantProfessionalExperienceRecord();
        String expResult = "area";
        instance.setBusinessArea(expResult);
        String result = instance.getBusinessArea();
        assertEquals(expResult, result);
    }

    /**
     * Test of getDivision method, of class ApplicantProfessionalExperienceRecord.
     */
    @Test
    public void testGetSetDivision() {
        System.out.println("getsetDivision");
        ApplicantProfessionalExperienceRecord instance = new ApplicantProfessionalExperienceRecord();
        String expResult = "division";
        instance.setDivision(expResult);
        String result = instance.getDivision();
        assertEquals(expResult, result);
    }

    /**
     * Test of getJobApplicantIdjobApplicant method, of class ApplicantProfessionalExperienceRecord.
     */
    @Test
    public void testGetSetJobApplicantIdjobApplicant() {
        System.out.println("getsetJobApplicantIdjobApplicant");
        ApplicantProfessionalExperienceRecord instance = new ApplicantProfessionalExperienceRecord();
        JobApplicant expResult = new JobApplicant(1);
        instance.setJobApplicantIdjobApplicant(expResult);
        JobApplicant result = instance.getJobApplicantIdjobApplicant();
        assertEquals(expResult, result);
    }

    /**
     * Test of getJobApplicantCollection method, of class ApplicantProfessionalExperienceRecord.
     */
    @Test
    public void testGetSetJobApplicantCollection() {
        System.out.println("getsetJobApplicantCollection");
        ApplicantProfessionalExperienceRecord instance = new ApplicantProfessionalExperienceRecord();
        JobApplicant x1 = new JobApplicant(1);
        JobApplicant x2 = new JobApplicant(2);
        Collection<JobApplicant> expResult = new HashSet();
        expResult.add(x1);
        expResult.add(x2);
        instance.setJobApplicantCollection(expResult);
        Collection<JobApplicant> result = instance.getJobApplicantCollection();
        assertEquals(expResult, result);
    }

    /**
     * Test of hashCode method, of class ApplicantProfessionalExperienceRecord.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        ApplicantProfessionalExperienceRecord instance = new ApplicantProfessionalExperienceRecord();
        int expResult = 0;
        int result = instance.hashCode();
        assertEquals(expResult, result);
    }
    @Test
    public void testHashCode1() {
        System.out.println("hashCode1");
        ApplicantProfessionalExperienceRecord instance = new ApplicantProfessionalExperienceRecord(1);
        int expResult = 1;
        int result = instance.hashCode();
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class ApplicantProfessionalExperienceRecord.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object object = null;
        ApplicantProfessionalExperienceRecord instance = new ApplicantProfessionalExperienceRecord();
        boolean expResult = false;
        boolean result = instance.equals(object);
    }
    @Test
    public void testEquals1() {
        System.out.println("equals1");
        ApplicantProfessionalExperienceRecord object = new ApplicantProfessionalExperienceRecord();
        ApplicantProfessionalExperienceRecord instance = new ApplicantProfessionalExperienceRecord(1);
        boolean expResult = false;
        boolean result = instance.equals(object);
    }
    @Test
    public void testEquals2() {
        System.out.println("equals2");
        ApplicantProfessionalExperienceRecord object = new ApplicantProfessionalExperienceRecord(1);
        ApplicantProfessionalExperienceRecord instance = new ApplicantProfessionalExperienceRecord();
        boolean expResult = false;
        boolean result = instance.equals(object);
    }
    @Test
    public void testEquals3() {
        System.out.println("equals3");
        ApplicantProfessionalExperienceRecord object = new ApplicantProfessionalExperienceRecord(1);
        ApplicantProfessionalExperienceRecord instance = new ApplicantProfessionalExperienceRecord(1);
        boolean expResult = true;
        boolean result = instance.equals(object);
    }

    /**
     * Test of toString method, of class ApplicantProfessionalExperienceRecord.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Integer idResult = 1;
        ApplicantProfessionalExperienceRecord instance = new ApplicantProfessionalExperienceRecord(idResult);
        String expResult = "com.mycompany.mavenproject1.ApplicantProfessionalExperienceRecord[ idapplicantProfessionalExperienceRecord=" + idResult + " ]";
        String result = instance.toString();
        assertEquals(expResult, result);
    }
    
}
