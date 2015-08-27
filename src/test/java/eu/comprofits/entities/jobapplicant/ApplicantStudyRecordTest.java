/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.comprofits.entities.jobapplicant;

import java.util.Date;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

/**
 *
 * @author Christos
 */
public class ApplicantStudyRecordTest {
    
    public ApplicantStudyRecordTest() {
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
     * Test of getIdstudyRecord method, of class ApplicantStudyRecord.
     */
    @Test
    public void testGetSetIdstudyRecord() {
        System.out.println("getsetIdstudyRecord");
        ApplicantStudyRecord instance = new ApplicantStudyRecord();
        Integer expResult = 1;
        instance.setIdstudyRecord(expResult);
        Integer result = instance.getIdstudyRecord();
        assertEquals(expResult, result);
    }

    /**
     * Test of getTitle method, of class ApplicantStudyRecord.
     */
    @Test
    public void testGetSetTitle() {
        System.out.println("getsetTitle");
        ApplicantStudyRecord instance = new ApplicantStudyRecord();
        String expResult = "tittle";
        instance.setTitle(expResult);
        String result = instance.getTitle();
        assertEquals(expResult, result);
    }

    /**
     * Test of getTitleType method, of class ApplicantStudyRecord.
     */
    @Test
    public void testGetSetTitleType() {
        System.out.println("getsetTitleType");
        ApplicantStudyRecord instance = new ApplicantStudyRecord();
        Integer expResult = 1;
        instance.setTitleType(expResult);
        Integer result = instance.getTitleType();
        assertEquals(expResult, result);
    }


    /**
     * Test of getInstitution method, of class ApplicantStudyRecord.
     */
    @Test
    public void testGetSetInstitution() {
        System.out.println("getsetInstitution");
        ApplicantStudyRecord instance = new ApplicantStudyRecord();
        String expResult = "institution";
        instance.setInstitution(expResult);
        String result = instance.getInstitution();
        assertEquals(expResult, result);
    }

    /**
     * Test of getDateStarted method, of class ApplicantStudyRecord.
     */
    @Test
    public void testGetSetDateStarted() {
        System.out.println("getsetDateStarted");
        ApplicantStudyRecord instance = new ApplicantStudyRecord();
        Date expResult = new Date();
        instance.setDateStarted(expResult);
        Date result = instance.getDateStarted();
        assertEquals(expResult, result);
    }


    /**
     * Test of getDateAcquired method, of class ApplicantStudyRecord.
     */
    @Test
    public void testGetSetDateAcquired() {
        System.out.println("getsetDateAcquired");
        ApplicantStudyRecord instance = new ApplicantStudyRecord();
        Date expResult = new Date();
        instance.setDateAcquired(expResult);
        Date result = instance.getDateAcquired();
        assertEquals(expResult, result);
    }

    /**
     * Test of getJobApplicantIdjobApplicant method, of class ApplicantStudyRecord.
     */
    @Test
    public void testGetSetJobApplicantIdjobApplicant() {
        System.out.println("getsetJobApplicantIdjobApplicant");
        ApplicantStudyRecord instance = new ApplicantStudyRecord();
        JobApplicant expResult = new JobApplicant(1);
        instance.setJobApplicantIdjobApplicant(expResult);
        JobApplicant result = instance.getJobApplicantIdjobApplicant();
        assertEquals(expResult, result);
    }

    /**
     * Test of hashCode method, of class ApplicantStudyRecord.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        ApplicantStudyRecord instance = new ApplicantStudyRecord();
        int expResult = 0;
        int result = instance.hashCode();
        assertEquals(expResult, result);
    }
    @Test
    public void testHashCode1() {
        System.out.println("hashCode1");
        ApplicantStudyRecord instance = new ApplicantStudyRecord(1);
        int expResult = 1;
        int result = instance.hashCode();
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class ApplicantStudyRecord.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object object = null;
        ApplicantStudyRecord instance = new ApplicantStudyRecord();
        boolean expResult = false;
        boolean result = instance.equals(object);
        assertEquals(expResult, result);
    }
    @Test
    public void testEquals1() {
        System.out.println("equals1");
        ApplicantStudyRecord object = new ApplicantStudyRecord();
        ApplicantStudyRecord instance = new ApplicantStudyRecord(1);
        boolean expResult = false;
        boolean result = instance.equals(object);
        assertEquals(expResult, result);
    }
    @Test
    public void testEquals2() {
        System.out.println("equals2");
        ApplicantStudyRecord object = new ApplicantStudyRecord(1);
        ApplicantStudyRecord instance = new ApplicantStudyRecord();
        boolean expResult = false;
        boolean result = instance.equals(object);
        assertEquals(expResult, result);
    }
    @Test
    public void testEquals3() {
        System.out.println("equals3");
        ApplicantStudyRecord object = new ApplicantStudyRecord(1);
        ApplicantStudyRecord instance = new ApplicantStudyRecord(1);
        boolean expResult = true;
        boolean result = instance.equals(object);
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class ApplicantStudyRecord.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Integer idResult = 1;
        ApplicantStudyRecord instance = new ApplicantStudyRecord(idResult);
        String expResult = "com.mycompany.mavenproject1.ApplicantStudyRecord[ idstudyRecord=" + idResult + " ]";
        String result = instance.toString();
        assertEquals(expResult, result);
    }

//    /**
//     * Test of getTitleTypeName method, of class ApplicantStudyRecord.
//     */
//    @Test
//    public void testGetTitleTypeName() {
//        System.out.println("getTitleTypeName");
//        ApplicantStudyRecord instance = new ApplicantStudyRecord();
//        String expResult = "";
//        String result = instance.getTitleTypeName();
//        assertEquals(expResult, result);
//    }
    
}
