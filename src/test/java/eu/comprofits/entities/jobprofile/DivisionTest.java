/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.comprofits.entities.jobprofile;

import eu.comprofits.entities.employee.Employee;
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
public class DivisionTest {
    
    public DivisionTest() {
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
     * Test of getIddivision method, of class Division.
     */
    @Test
    public void testSetGetIddivision() {
        System.out.println("getsetIddivision");
        Division instance = new Division();
        Integer expResult = 1;
        instance.setIddivision(expResult);
        Integer result = instance.getIddivision();
        assertEquals(expResult, result);
    }

    /**
     * Test of getName method, of class Division.
     */
    @Test
    public void testGetSetName() {
        System.out.println("getsetName");
        Division instance = new Division();
        String expResult = "Chris";
        instance.setName(expResult);
        String result = instance.getName();
        assertEquals(expResult, result);
    }

    /**
     * Test of getDescription method, of class Division.
     */
    @Test
    public void testGetSetDescription() {
        System.out.println("getsetDescription");
        Division instance = new Division();
        String expResult = "desc";
        instance.setDescription(expResult);
        String result = instance.getDescription();
        assertEquals(expResult, result);
    }

    /**
     * Test of getHeadOfDivisionEmployee method, of class Division.
     */
    @Test
    public void testGetSetHeadOfDivisionEmployee() {
        System.out.println("getsetHeadOfDivisionEmployee");
        Division instance = new Division();
        Employee expResult = new Employee(1);
        instance.setHeadOfDivisionEmployee(expResult);
        Employee result = instance.getHeadOfDivisionEmployee();
        assertEquals(expResult, result);
    }

    /**
     * Test of getBusinessAreaCollection method, of class Division.
     */
    @Test
    public void testGetSetBusinessAreaCollection() {
        System.out.println("getsetBusinessAreaCollection");
        Division instance = new Division();
        BusinessArea x1 = new BusinessArea(1);
        BusinessArea x2 = new BusinessArea(2);
        Collection<BusinessArea> expResult = new HashSet();
        expResult.add(x1);
        expResult.add(x2);
        instance.setBusinessAreaCollection(expResult);
        Collection<BusinessArea> result = instance.getBusinessAreaCollection();
        assertEquals(expResult, result);

    }

    /**
     * Test of hashCode method, of class Division.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        Division instance = new Division();
        int expResult = 0;
        int result = instance.hashCode();
        assertEquals(expResult, result);
    }
    @Test
    public void testHashCode1() {
        System.out.println("hashCode1");
        Division instance = new Division(1);
        int expResult = 1;
        int result = instance.hashCode();
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Division.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object object = null;
        Division instance = new Division();
        boolean expResult = false;
        boolean result = instance.equals(object);
        assertEquals(expResult, result);
    }
    @Test
    public void testEquals1() {
        System.out.println("equals1");
        Division object = new Division(1);
        Division instance = new Division();
        boolean expResult = false;
        boolean result = instance.equals(object);
        assertEquals(expResult, result);
    }
    @Test
    public void testEquals2() {
        System.out.println("equals2");
        Division object = new Division();
        Division instance = new Division(1);
        boolean expResult = false;
        boolean result = instance.equals(object);
        assertEquals(expResult, result);
    }
    @Test
    public void testEquals3() {
        System.out.println("equals3");
        Division object = new Division(1);
        Division instance = new Division(1);
        boolean expResult = true;
        boolean result = instance.equals(object);
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class Division.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Integer idResult = 1;
        Division instance = new Division(idResult);
        String expResult = "com.mycompany.mavenproject1.Division[ iddivision=" + idResult + " ]";
        String result = instance.toString();
        assertEquals(expResult, result);
    }
    
}
