/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.comprofits.entities.employee;

import eu.comprofits.entities.jobprofile.Job;
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
public class InCompanyEmploymentTest {
    
    public InCompanyEmploymentTest() {
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
     * Test of getIdinCompanyEmployment method, of class InCompanyEmployment.
     */
    @Test
    public void testGetSetIdinCompanyEmployment() {
        System.out.println("getsetIdinCompanyEmployment");
        InCompanyEmployment instance = new InCompanyEmployment();
        Integer expResult = 1;
        instance.setIdinCompanyEmployment(expResult);
        Integer result = instance.getIdinCompanyEmployment();
        assertEquals(expResult, result);
    }

    /**
     * Test of getStartDate method, of class InCompanyEmployment.
     */
    @Test
    public void testGetSetStartDate() {
        System.out.println("getsetStartDate");
        InCompanyEmployment instance = new InCompanyEmployment();
        Date expResult = new Date();
        instance.setStartDate(expResult);
        Date result = instance.getStartDate();
        assertEquals(expResult, result);
    }


    /**
     * Test of getEndDate method, of class InCompanyEmployment.
     */
    @Test
    public void testGetSetEndDate() {
        System.out.println("getsetEndDate");
        InCompanyEmployment instance = new InCompanyEmployment();
        Date expResult = new Date();
        instance.setEndDate(expResult);
        Date result = instance.getEndDate();
        assertEquals(expResult, result);

    }


    /**
     * Test of getEmployeeCollection method, of class InCompanyEmployment.
     */
    @Test
    public void testGetSetEmployeeCollection() {
        System.out.println("getsetEmployeeCollection");
        InCompanyEmployment instance = new InCompanyEmployment();
        Employee x1 = new Employee(1);
        Employee x2 = new Employee(1);
        Collection<Employee> expResult = new HashSet();
        expResult.add(x1);
        expResult.add(x2);
        instance.setEmployeeCollection(expResult);
        Collection<Employee> result = instance.getEmployeeCollection();
        assertEquals(expResult, result);
    }


    /**
     * Test of getJobIdjob method, of class InCompanyEmployment.
     */
    @Test
    public void testGetSetJobIdjob() {
        System.out.println("getsetJobIdjob");
        InCompanyEmployment instance = new InCompanyEmployment();
        Job expResult = new Job(1);
        instance.setJobIdjob(expResult);
        Job result = instance.getJobIdjob();
        assertEquals(expResult, result);
    }

    /**
     * Test of getEmployeeIdemployee method, of class InCompanyEmployment.
     */
    @Test
    public void testGetSetEmployeeIdemployee() {
        System.out.println("getsetEmployeeIdemployee");
        InCompanyEmployment instance = new InCompanyEmployment();
        Employee expResult = new Employee(1);
        instance.setEmployeeIdemployee(expResult);
        Employee result = instance.getEmployeeIdemployee();
        assertEquals(expResult, result);
    }


    /**
     * Test of hashCode method, of class InCompanyEmployment.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        InCompanyEmployment instance = new InCompanyEmployment();
        int expResult = 0;
        int result = instance.hashCode();
        assertEquals(expResult, result);
    }
    @Test
    public void testHashCode1() {
        System.out.println("hashCode1");
        InCompanyEmployment instance = new InCompanyEmployment(1);
        int expResult = 1;
        int result = instance.hashCode();
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class InCompanyEmployment.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object object = null;
        InCompanyEmployment instance = new InCompanyEmployment();
        boolean expResult = false;
        boolean result = instance.equals(object);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testEquals1() {
        System.out.println("equals1");
        InCompanyEmployment object = new InCompanyEmployment(1);
        InCompanyEmployment instance = new InCompanyEmployment();
        boolean expResult = false;
        boolean result = instance.equals(object);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testEquals2() {
        System.out.println("equals2");
        InCompanyEmployment object = new InCompanyEmployment();
        InCompanyEmployment instance = new InCompanyEmployment(1);
        boolean expResult = false;
        boolean result = instance.equals(object);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testEquals3() {
        System.out.println("equals3");
        InCompanyEmployment object = new InCompanyEmployment(1);
        InCompanyEmployment instance = new InCompanyEmployment(1);
        boolean expResult = true;
        boolean result = instance.equals(object);
        assertEquals(expResult, result);

    }

    /**
     * Test of getDepartmentName method, of class InCompanyEmployment.
     */
    @Test
    public void testGetSetDepartmentName() {
        System.out.println("getsetDepartmentName");
        InCompanyEmployment instance = new InCompanyEmployment();
        String expResult = "A";
        instance.setDepartmentName(expResult);
        String result = instance.getDepartmentName();
        assertEquals(expResult, result);
    }


    /**
     * Test of toString method, of class InCompanyEmployment.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Integer idResult = 1;
        InCompanyEmployment instance = new InCompanyEmployment(idResult);
        String expResult = "com.mycompany.mavenproject1.InCompanyEmployment[ idinCompanyEmployment=" + idResult + " ]";
        String result = instance.toString();
        assertEquals(expResult, result);
    }
    
}
