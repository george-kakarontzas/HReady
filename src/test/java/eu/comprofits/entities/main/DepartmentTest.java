/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.comprofits.entities.main;

import eu.comprofits.entities.employee.Employee;
import eu.comprofits.entities.jobprofile.Division;
import java.util.Collection;
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
public class DepartmentTest {
    
    public DepartmentTest() {
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
     * Test of getIddepartment method, of class Department.
     */
    @Test
    public void testGetSetIddepartment() {
        System.out.println("getsetIddepartment");
        Department instance = new Department();
        Integer expResult = 1;
        instance.setIddepartment(expResult);
        Integer result = instance.getIddepartment();
        assertEquals(expResult, result);
    }


    /**
     * Test of getDepartmentName method, of class Department.
     */
    @Test
    public void testGetSetDepartmentName() {
        System.out.println("getsetDepartmentName");
        Department instance = new Department();
        String expResult = "name";
        instance.setDepartmentName(expResult);
        String result = instance.getDepartmentName();
        assertEquals(expResult, result);
    }


    /**
     * Test of getHeadOfDepartmentIdemployee method, of class Department.
     */
    @Test
    public void testGetSetHeadOfDepartmentIdemployee() {
        System.out.println("getsetHeadOfDepartmentIdemployee");
        Department instance = new Department();
        Employee expResult = new Employee(1);
        instance.setHeadOfDepartmentIdemployee(expResult);
        Employee result = instance.getHeadOfDepartmentIdemployee();
        assertEquals(expResult, result);
    }

    /**
     * Test of getCompanyIdcompany method, of class Department.
     */
    @Test
    public void testGetSetDivisionIddivision() {
        System.out.println("getsetDivisionIddivision");
        Department instance = new Department();
        Division expResult = new Division(1);
        instance.setDivisionIddivision(expResult);
        Division result = instance.getDivisionIddivision();
        assertEquals(expResult, result);
    }


    /**
     * Test of getEmployeeCollection method, of class Department.
     */
    @Test
    public void testGetEmployeeCollection() {
        System.out.println("getEmployeeCollection");
        Department instance = new Department();
        Employee x1 = new Employee(1);
        Employee x2 = new Employee(2);
        Collection<Employee> expResult = new HashSet();
        expResult.add(x1);
        expResult.add(x2);
        instance.setEmployeeCollection(expResult);
        Collection<Employee> result = instance.getEmployeeCollection();
        assertEquals(expResult, result);

    }

    /**
     * Test of hashCode method, of class Department.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        Department instance = new Department();
        int expResult = 0;
        int result = instance.hashCode();
        assertEquals(expResult, result);
    }
    @Test
    public void testHashCode1() {
        System.out.println("hashCode1");
        Department instance = new Department(1);
        int expResult = 1;
        int result = instance.hashCode();
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Department.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object object = null;
        Department instance = new Department();
        boolean expResult = false;
        boolean result = instance.equals(object);
        assertEquals(expResult, result);
    }
    @Test
    public void testEquals1() {
        System.out.println("equals1");
        Department object = new Department();
        Department instance = new Department(1);
        boolean expResult = false;
        boolean result = instance.equals(object);
        assertEquals(expResult, result);
    }
    @Test
    public void testEquals2() {
        System.out.println("equals2");
        Department object = new Department(1);
        Department instance = new Department();
        boolean expResult = false;
        boolean result = instance.equals(object);
        assertEquals(expResult, result);
    }
    @Test
    public void testEquals3() {
        System.out.println("equals3");
        Department object = new Department(1);
        Department instance = new Department(1);
        boolean expResult = true;
        boolean result = instance.equals(object);
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class Department.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Department instance = new Department(1);
        String expResult = "name";
        instance.setDepartmentName(expResult);
        String result = instance.toString();
        assertEquals(expResult, result);
    }
    
}
