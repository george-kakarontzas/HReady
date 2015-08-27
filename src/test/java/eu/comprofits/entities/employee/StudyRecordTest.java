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
public class StudyRecordTest {
    
    public StudyRecordTest() {
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
     * Test of getIdstudyRecord method, of class StudyRecord.
     */
    @Test
    public void testGetSetIdstudyRecord() {
        System.out.println("getsetIdstudyRecord");
        StudyRecord instance = new StudyRecord();
        Integer expResult = 1;
        instance.setIdstudyRecord(expResult);
        Integer result = instance.getIdstudyRecord();
        assertEquals(expResult, result);
    }

    /**
     * Test of getTitle method, of class StudyRecord.
     */
    @Test
    public void testGetSetTitle() {
        System.out.println("getsetTitle");
        StudyRecord instance = new StudyRecord();
        String expResult = "Title";
        instance.setTitle(expResult);
        String result = instance.getTitle();
        assertEquals(expResult, result);
    }


    /**
     * Test of getTitleType method, of class StudyRecord.
     */
    @Test
    public void testGetSetTitleType() {
        System.out.println("getsetTitleType");
        StudyRecord instance = new StudyRecord();
        Integer expResult = 1;
        instance.setTitleType(expResult);
        Integer result = instance.getTitleType();
        assertEquals(expResult, result);
    }

    /**
     * Test of getInstitution method, of class StudyRecord.
     */
    @Test
    public void testGetSetInstitution() {
        System.out.println("getsetInstitution");
        StudyRecord instance = new StudyRecord();
        String expResult = "tei";
        instance.setInstitution(expResult);
        String result = instance.getInstitution();
        assertEquals(expResult, result);
    }


    /**
     * Test of getDateStarted method, of class StudyRecord.
     */
    @Test
    public void testGetSetDateStarted() {
        System.out.println("getSetDateStarted");
        StudyRecord instance = new StudyRecord();
        Date expResult = new Date();
        instance.setDateStarted(expResult);
        Date result = instance.getDateStarted();
        assertEquals(expResult, result);

    }


    /**
     * Test of getDateAcquired method, of class StudyRecord.
     */
    @Test
    public void testGetSetDateAcquired() {
        System.out.println("getSetDateAcquired");
        StudyRecord instance = new StudyRecord();
        Date expResult = new Date();
        instance.setDateAcquired(expResult);
        Date result = instance.getDateAcquired();
        assertEquals(expResult, result);
    }


    /**
     * Test of getEmployeeIdemployee method, of class StudyRecord.
     */
    @Test
    public void testGetEmployeeIdemployee() {
        System.out.println("getEmployeeIdemployee");
        StudyRecord instance = new StudyRecord();
        Employee expResult = new Employee(1);
        instance.setEmployeeIdemployee(expResult);
        Employee result = instance.getEmployeeIdemployee();
        assertEquals(expResult, result);
    }


    /**
     * Test of hashCode method, of class StudyRecord.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        StudyRecord instance = new StudyRecord();
        int expResult = 0;
        int result = instance.hashCode();
        assertEquals(expResult, result);

    }
    @Test
    public void testHashCode1() {
        System.out.println("hashCode1");
        StudyRecord instance = new StudyRecord(1);
        int expResult = 1;
        int result = instance.hashCode();
        assertEquals(expResult, result);
    }


    /**
     * Test of equals method, of class StudyRecord.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object object = null;
        StudyRecord instance = new StudyRecord();
        boolean expResult = false;
        boolean result = instance.equals(object);
        assertEquals(expResult, result);
    }
    @Test
    public void testEquals1() {
        System.out.println("equals1");
        StudyRecord object = new StudyRecord(1);
        StudyRecord instance = new StudyRecord();
        boolean expResult = false;
        boolean result = instance.equals(object);
        assertEquals(expResult, result);
    }
    @Test
    public void testEquals2() {
        System.out.println("equals2");
        StudyRecord object = new StudyRecord();
        StudyRecord instance = new StudyRecord(2);
        boolean expResult = false;
        boolean result = instance.equals(object);
        assertEquals(expResult, result);
    }
    @Test
    public void testEquals3() {
        System.out.println("equals3");
        StudyRecord object = new StudyRecord(1);
        StudyRecord instance = new StudyRecord(1);
        boolean expResult = true;
        boolean result = instance.equals(object);
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class StudyRecord.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Integer idResult = 1;
        StudyRecord instance = new StudyRecord(idResult);
        String expResult = "com.mycompany.mavenproject1.StudyRecord[ idstudyRecord=" + idResult +" ]";
        String result = instance.toString();
        assertEquals(expResult, result);
    }
    
}
