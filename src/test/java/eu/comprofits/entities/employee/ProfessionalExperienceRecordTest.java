/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.comprofits.entities.employee;

import java.util.Date;
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
public class ProfessionalExperienceRecordTest {
    
    public ProfessionalExperienceRecordTest() {
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
     * Test of ProfessionalExperienceRecord constructor, of class ProfessionalExperienceRecord.
     */
    @Test
    public void testProfessionalExperienceRecord() {
        System.out.println("professionalexperienceRecord");
        Date date = new Date();
        ProfessionalExperienceRecord instance = new ProfessionalExperienceRecord(1,date,date,"ae","emp");
        Integer expResult = 1;
        instance.setIdprofessionalExperienceRecord(expResult);
        Integer result = instance.getIdprofessionalExperienceRecord();
        assertEquals("error IdprofessionalExperienceRecord",expResult, result);
        assertEquals("error startdate",instance.getDateStarted(),date);
        assertEquals("error finishdate",instance.getDateFinished(),date);
        assertEquals("error company",instance.getCompany(),"ae");
        assertEquals("error role",instance.getRole(),"emp");
    }
    
    /**
     * Test of getIdprofessionalExperienceRecord method, of class ProfessionalExperienceRecord.
     */
    @Test
    public void testGetSetIdprofessionalExperienceRecord() {
        System.out.println("getsetIdprofessionalExperienceRecord");
        ProfessionalExperienceRecord instance = new ProfessionalExperienceRecord();
        Integer expResult = 1;
        instance.setIdprofessionalExperienceRecord(expResult);
        Integer result = instance.getIdprofessionalExperienceRecord();
        assertEquals(expResult, result);
    }


    /**
     * Test of getDateStarted method, of class ProfessionalExperienceRecord.
     */
    @Test
    public void testGetSetDateStarted() {
        System.out.println("getsetDateStarted");
        ProfessionalExperienceRecord instance = new ProfessionalExperienceRecord();
        Date expResult = new Date();
        instance.setDateStarted(expResult);
        Date result = instance.getDateStarted();
        assertEquals(expResult, result);
    }


    /**
     * Test of getDateFinished method, of class ProfessionalExperienceRecord.
     */
    @Test
    public void testGetSetDateFinished() {
        System.out.println("getsetDateFinished");
        ProfessionalExperienceRecord instance = new ProfessionalExperienceRecord();
        Date expResult = new Date();
        instance.setDateFinished(expResult);
        Date result = instance.getDateFinished();
        assertEquals(expResult, result);
    }


    /**
     * Test of getCompany method, of class ProfessionalExperienceRecord.
     */
    @Test
    public void testGetSetCompany() {
        System.out.println("getsetCompany");
        ProfessionalExperienceRecord instance = new ProfessionalExperienceRecord();
        String expResult = "pi";
        instance.setCompany(expResult);
        String result = instance.getCompany();
        assertEquals(expResult, result);
    }


    /**
     * Test of getRole method, of class ProfessionalExperienceRecord.
     */
    @Test
    public void testGetSetRole() {
        System.out.println("getsetRole");
        ProfessionalExperienceRecord instance = new ProfessionalExperienceRecord();
        String expResult = "emp";
        instance.setRole(expResult);
        String result = instance.getRole();
        assertEquals(expResult, result);
    }

    /**
     * Test of getJobTitle method, of class ProfessionalExperienceRecord.
     */
    @Test
    public void testGetSetJobTitle() {
        System.out.println("getsetJobTitle");
        ProfessionalExperienceRecord instance = new ProfessionalExperienceRecord();
        String expResult = "emp";
        instance.setJobTitle(expResult);
        String result = instance.getJobTitle();
        assertEquals(expResult, result);
    }

    /**
     * Test of getFieldOfWork method, of class ProfessionalExperienceRecord.
     */
    @Test
    public void testGetSetFieldOfWork() {
        System.out.println("getsetFieldOfWork");
        ProfessionalExperienceRecord instance = new ProfessionalExperienceRecord();
        String expResult = "tech";
        instance.setFieldOfWork(expResult);
        String result = instance.getFieldOfWork();
        assertEquals(expResult, result);
    }


    /**
     * Test of getPlaceOfEmployment method, of class ProfessionalExperienceRecord.
     */
    @Test
    public void testGetSetPlaceOfEmployment() {
        System.out.println("getsetPlaceOfEmployment");
        ProfessionalExperienceRecord instance = new ProfessionalExperienceRecord();
        String expResult = "Greece";
        instance.setPlaceOfEmployment(expResult);
        String result = instance.getPlaceOfEmployment();
        assertEquals(expResult, result);
    }

    /**
     * Test of getImmediateManager method, of class ProfessionalExperienceRecord.
     */
    @Test
    public void testGetImmediateManager() {
        System.out.println("getImmediateManager");
        ProfessionalExperienceRecord instance = new ProfessionalExperienceRecord();
        String expResult = "emp";
        instance.setImmediateManager(expResult);
        String result = instance.getImmediateManager();
        assertEquals(expResult, result);
    }

    /**
     * Test of getBusinessArea method, of class ProfessionalExperienceRecord.
     */
    @Test
    public void testGetSetBusinessArea() {
        System.out.println("getBusinessArea");
        ProfessionalExperienceRecord instance = new ProfessionalExperienceRecord();
        String expResult = "emp";
        instance.setBusinessArea(expResult);
        String result = instance.getBusinessArea();
        assertEquals(expResult, result);
    }

    /**
     * Test of getDivision method, of class ProfessionalExperienceRecord.
     */
    @Test
    public void testGetSetDivision() {
        System.out.println("getDivision");
        ProfessionalExperienceRecord instance = new ProfessionalExperienceRecord();
        String expResult = "div";
        instance.setDivision(expResult);
        String result = instance.getDivision();
        assertEquals(expResult, result);
    }

    
    /**
     * Test of getEmployeeIdemployee method, of class ProfessionalExperienceRecord.
     */
    @Test
    public void testGetEmployeeIdemployee() {
        System.out.println("getEmployeeIdemployee");
        ProfessionalExperienceRecord instance = new ProfessionalExperienceRecord();
        Employee expResult = new Employee(1);
        instance.setEmployeeIdemployee(expResult);
        Employee result = instance.getEmployeeIdemployee();
        assertEquals(expResult, result);
    }


    /**
     * Test of hashCode method, of class ProfessionalExperienceRecord.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        ProfessionalExperienceRecord instance = new ProfessionalExperienceRecord();
        int expResult = 0;
        int result = instance.hashCode();
        assertEquals(expResult, result);
    }
    @Test
    public void testHashCode1() {
        System.out.println("hashCode1");
        ProfessionalExperienceRecord instance = new ProfessionalExperienceRecord(1);
        int expResult = 1;
        int result = instance.hashCode();
        assertEquals(expResult, result);

    }

    /**
     * Test of equals method, of class ProfessionalExperienceRecord.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object object = null;
        ProfessionalExperienceRecord instance = new ProfessionalExperienceRecord();
        boolean expResult = false;
        boolean result = instance.equals(object);
        assertEquals(expResult, result);
    }
    @Test
    public void testEquals1() {
        System.out.println("equals1");
        ProfessionalExperienceRecord object = new ProfessionalExperienceRecord(1);
        ProfessionalExperienceRecord instance = new ProfessionalExperienceRecord();
        boolean expResult = false;
        boolean result = instance.equals(object);
        assertEquals(expResult, result);
    }
    @Test
    public void testEquals2() {
        System.out.println("equals3");
        ProfessionalExperienceRecord object = new ProfessionalExperienceRecord();
        ProfessionalExperienceRecord instance = new ProfessionalExperienceRecord(1);
        boolean expResult = false;
        boolean result = instance.equals(object);
        assertEquals(expResult, result);
    }
    @Test
    public void testEquals3() {
        System.out.println("equals3");
        ProfessionalExperienceRecord object = new ProfessionalExperienceRecord(1);
        ProfessionalExperienceRecord instance = new ProfessionalExperienceRecord(1);
        boolean expResult = true;
        boolean result = instance.equals(object);
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class ProfessionalExperienceRecord.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Integer idResult = 1;
        ProfessionalExperienceRecord instance = new ProfessionalExperienceRecord(idResult);
        String expResult = "com.mycompany.mavenproject1.ProfessionalExperienceRecord[ idprofessionalExperienceRecord=" + idResult + " ]";
        String result = instance.toString();
        assertEquals(expResult, result);
    }
    
}
