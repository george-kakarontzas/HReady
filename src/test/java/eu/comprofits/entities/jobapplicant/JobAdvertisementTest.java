/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.comprofits.entities.jobapplicant;

import eu.comprofits.entities.jobprofile.Job;
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
public class JobAdvertisementTest {
    
    public JobAdvertisementTest() {
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
     * Test of JobAdvertisement constructor, of class JobAdvertisement.
     */
    @Test
    public void testJobAdvertisement() {
        System.out.println("JobAdvertisement");
        Integer idResult = 1;
        JobAdvertisement instance = new JobAdvertisement(idResult,"title","responsibility","description");        
        Integer result = instance.getIdjobAdvertisement();
        assertEquals("error jobTitle","title", instance.getJobTitle());
        assertEquals("error fieldsOfResponsibility","responsibility", instance.getFieldsOfResponsibility());
        assertEquals("error jobDescription","description", instance.getJobDescription());
    }

    /**
     * Test of getIdjobAdvertisement method, of class JobAdvertisement.
     */
    @Test
    public void testGetSetIdjobAdvertisement() {
        System.out.println("getsetIdjobAdvertisement");
        JobAdvertisement instance = new JobAdvertisement();
        Integer expResult = 1;
        instance.setIdjobAdvertisement(expResult);
        Integer result = instance.getIdjobAdvertisement();
        assertEquals(expResult, result);
    }

    /**
     * Test of getJobTitle method, of class JobAdvertisement.
     */
    @Test
    public void testGetSetJobTitle() {
        System.out.println("getsetJobTitle");
        JobAdvertisement instance = new JobAdvertisement();
        String expResult = "title";
        instance.setJobTitle(expResult);
        String result = instance.getJobTitle();
        assertEquals(expResult, result);
    }

    /**
     * Test of getFieldsOfResponsibility method, of class JobAdvertisement.
     */
    @Test
    public void testGetSetFieldsOfResponsibility() {
        System.out.println("getsetFieldsOfResponsibility");
        JobAdvertisement instance = new JobAdvertisement();
        String expResult = "responsibility";
        instance.setFieldsOfResponsibility(expResult);
        String result = instance.getFieldsOfResponsibility();
        assertEquals(expResult, result);
    }

    /**
     * Test of getJobDescription method, of class JobAdvertisement.
     */
    @Test
    public void testGetSetJobDescription() {
        System.out.println("getsetJobDescription");
        JobAdvertisement instance = new JobAdvertisement();
        String expResult = "desc";
        instance.setJobDescription(expResult);
        String result = instance.getJobDescription();
        assertEquals(expResult, result);
    }

    /**
     * Test of getJobIdjob method, of class JobAdvertisement.
     */
    @Test
    public void testGetSetJobIdjob() {
        System.out.println("getJsetobIdjob");
        JobAdvertisement instance = new JobAdvertisement();
        Job expResult = new Job(1);
        instance.setJobIdjob(expResult);
        Job result = instance.getJobIdjob();
        assertEquals(expResult, result);
    }

    /**
     * Test of getJobApplicationCollection method, of class JobAdvertisement.
     */
    @Test
    public void testGetSetJobApplicationCollection() {
        System.out.println("getsetJobApplicationCollection");
        JobAdvertisement instance = new JobAdvertisement();
        JobApplication x1 = new JobApplication(1);
        JobApplication x2 = new JobApplication(2);
        Collection<JobApplication> expResult = new HashSet();
        expResult.add(x1);
        expResult.add(x2);
        instance.setJobApplicationCollection(expResult);
        Collection<JobApplication> result = instance.getJobApplicationCollection();
        assertEquals(expResult, result);
    }

    /**
     * Test of hashCode method, of class JobAdvertisement.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        JobAdvertisement instance = new JobAdvertisement();
        int expResult = 0;
        int result = instance.hashCode();
        assertEquals(expResult, result);
    }
    @Test
    public void testHashCode1() {
        System.out.println("hashCode1");
        JobAdvertisement instance = new JobAdvertisement(1);
        int expResult = 1;
        int result = instance.hashCode();
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class JobAdvertisement.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object object = null;
        JobAdvertisement instance = new JobAdvertisement();
        boolean expResult = false;
        boolean result = instance.equals(object);
        assertEquals(expResult, result);
    }
    @Test
    public void testEquals1() {
        System.out.println("equals1");
        JobAdvertisement object = new JobAdvertisement();
        JobAdvertisement instance = new JobAdvertisement(1);
        boolean expResult = false;
        boolean result = instance.equals(object);
        assertEquals(expResult, result);
    }
    @Test
    public void testEquals2() {
        System.out.println("equals2");
        JobAdvertisement object = new JobAdvertisement(1);
        JobAdvertisement instance = new JobAdvertisement();
        boolean expResult = false;
        boolean result = instance.equals(object);
        assertEquals(expResult, result);
    }
    @Test
    public void testEquals3() {
        System.out.println("equals3");
        JobAdvertisement object = new JobAdvertisement(1);
        JobAdvertisement instance = new JobAdvertisement(1);
        boolean expResult = true;
        boolean result = instance.equals(object);
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class JobAdvertisement.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Integer idResult = 1;
        JobAdvertisement instance = new JobAdvertisement(idResult);
        String expResult = "com.mycompany.mavenproject1.JobAdvertisement[ idjobAdvertisement=" + idResult + " ]";
        String result = instance.toString();
        assertEquals(expResult, result);
    }
    
}
