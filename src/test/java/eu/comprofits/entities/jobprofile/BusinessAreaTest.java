/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.comprofits.entities.jobprofile;

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
public class BusinessAreaTest {
    
    public BusinessAreaTest() {
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
     * Test of getIdbusinessArea method, of class BusinessArea.
     */
    @Test
    public void testGetSetIdbusinessArea() {
        System.out.println("getsetIdbusinessArea");
        BusinessArea instance = new BusinessArea();
        Integer expResult = 1;
        instance.setIdbusinessArea(expResult);
        Integer result = instance.getIdbusinessArea();
        assertEquals(expResult, result);
    }

    /**
     * Test of getName method, of class BusinessArea.
     */
    @Test
    public void testSetGetName() {
        System.out.println("getsetName");
        BusinessArea instance = new BusinessArea();
        String expResult = "Chris";
        instance.setName(expResult);
        String result = instance.getName();
        assertEquals(expResult, result);
    }

    /**
     * Test of getDescription method, of class BusinessArea.
     */
    @Test
    public void testGetSetDescription() {
        System.out.println("getsetDescription");
        BusinessArea instance = new BusinessArea();
        String expResult = "desc";
        instance.setDescription(expResult);
        String result = instance.getDescription();
        assertEquals(expResult, result);
    }

    /**
     * Test of getDivisionIddivision method, of class BusinessArea.
     */
    @Test
    public void testGetSetDivisionIddivision() {
        System.out.println("getsetDivisionIddivision");
        BusinessArea instance = new BusinessArea();
        Division expResult = new Division(1);
        instance.setDivisionIddivision(expResult);
        Division result = instance.getDivisionIddivision();
        assertEquals(expResult, result);
    }
    /**
     * Test of getJobCollection method, of class BusinessArea.
     */
    @Test
    public void testGetSetJobCollection() {
        System.out.println("getsetJobCollection");
        BusinessArea instance = new BusinessArea();
        Job x1 = new Job(1);
        Job x2 = new Job(2);
        Collection<Job> expResult = new HashSet();
        expResult.add(x1);
        expResult.add(x2);
        instance.setJobCollection(expResult);
        Collection<Job> result = instance.getJobCollection();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of hashCode method, of class BusinessArea.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        BusinessArea instance = new BusinessArea();
        int expResult = 0;
        int result = instance.hashCode();
        assertEquals(expResult, result);
    }
    @Test
    public void testHashCode1() {
        System.out.println("hashCode1");
        BusinessArea instance = new BusinessArea(1);
        int expResult = 1;
        int result = instance.hashCode();
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class BusinessArea.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object object = null;
        BusinessArea instance = new BusinessArea();
        boolean expResult = false;
        boolean result = instance.equals(object);
        assertEquals(expResult, result);
    }
    @Test
    public void testEquals1() {
        System.out.println("equals1");
        BusinessArea object = new BusinessArea();
        BusinessArea instance = new BusinessArea(1);
        boolean expResult = false;
        boolean result = instance.equals(object);
        assertEquals(expResult, result);
    }
    @Test
    public void testEquals2() {
        System.out.println("equals2");
        BusinessArea object = new BusinessArea(1);
        BusinessArea instance = new BusinessArea();
        boolean expResult = false;
        boolean result = instance.equals(object);
        assertEquals(expResult, result);
    }
    @Test
    public void testEquals3() {
        System.out.println("equals3");
        BusinessArea object = new BusinessArea(1);
        BusinessArea instance = new BusinessArea(1);
        boolean expResult = true;
        boolean result = instance.equals(object);
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class BusinessArea.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Integer idResult = 1 ;
        BusinessArea instance = new BusinessArea(idResult);
        String expResult = "com.mycompany.mavenproject1.BusinessArea[ idbusinessArea=" + idResult + " ]";
        String result = instance.toString();
        assertEquals(expResult, result);
    }
    
}
