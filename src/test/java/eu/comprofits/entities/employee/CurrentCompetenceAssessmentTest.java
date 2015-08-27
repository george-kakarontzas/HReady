/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.comprofits.entities.employee;

import eu.comprofits.entities.main.Competence;
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
public class CurrentCompetenceAssessmentTest {
    
    public CurrentCompetenceAssessmentTest() {
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
     * Test of CurrentCompetenceAssessment constructor, of class CurrentCompetenceAssessment.
     */
    @Test
    public void testCurrentCompetenceAssessment() {
        System.out.println("currentcompetenceassessment");
        CurrentCompetenceAssessment instance = new CurrentCompetenceAssessment(2,1);
        Integer expResult = 2;
        Integer result = instance.getIdcurrentCompetenceAssessment();
        assertEquals("getIdcurrentCompetenceAssessment",expResult, result);
        assertEquals("error assessment",instance.getAssessment(),1);        
    }

    /**
     * Test of getIdcurrentCompetenceAssessment method, of class CurrentCompetenceAssessment.
     */
    @Test
    public void testGetSetIdcurrentCompetenceAssessment() {
        System.out.println("getsetIdcurrentCompetenceAssessment");
        CurrentCompetenceAssessment instance = new CurrentCompetenceAssessment();
        Integer expResult = 12;
        instance.setIdcurrentCompetenceAssessment(expResult);
        Integer result = instance.getIdcurrentCompetenceAssessment();
        assertEquals(expResult, result);
    }


    /**
     * Test of getAssessment method, of class CurrentCompetenceAssessment.
     */
    @Test
    public void testGetSetAssessment() {
        System.out.println("getsetAssessment");
        CurrentCompetenceAssessment instance = new CurrentCompetenceAssessment();
        int expResult = 0;
        instance.setAssessment(expResult);
        int result = instance.getAssessment();
        assertEquals(expResult, result);
    }

    
    /**
     * Test of getEmployeeIdemployee method, of class CurrentCompetenceAssessment.
     */
    @Test
    public void testGetSetEmployeeIdemployee() {
        System.out.println("getsetEmployeeIdemployee");
        CurrentCompetenceAssessment instance = new CurrentCompetenceAssessment();
        Employee expResult = new Employee(1);
        instance.setEmployeeIdemployee(expResult);
        Employee result = instance.getEmployeeIdemployee();
        assertEquals(expResult, result);
    }


    /**
     * Test of getCompetenceIdcompetence method, of class CurrentCompetenceAssessment.
     */
    @Test
    public void testGetSetCompetenceIdcompetence() {
        System.out.println("getsetCompetenceIdcompetence");
        CurrentCompetenceAssessment instance = new CurrentCompetenceAssessment();
        Competence expResult = new Competence(1);
        instance.setCompetenceIdcompetence(expResult);
        Competence result = instance.getCompetenceIdcompetence();
        assertEquals(expResult, result);
    }


    /**
     * Test of hashCode method, of class CurrentCompetenceAssessment.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        CurrentCompetenceAssessment instance = new CurrentCompetenceAssessment(1);
        int expResult = 1;
        int result = instance.hashCode();
        assertEquals(expResult, result);
    }
    
        @Test
    public void testHashCode1() {
        System.out.println("hashCode1");
        CurrentCompetenceAssessment instance = new CurrentCompetenceAssessment();
        int expResult = 0;
        int result = instance.hashCode();
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class CurrentCompetenceAssessment.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object object = null;
        CurrentCompetenceAssessment instance = new CurrentCompetenceAssessment();
        boolean expResult = false;
        boolean result = instance.equals(object);
        assertEquals(expResult, result);
    }
    @Test
    public void testEquals1() {
        System.out.println("equals1");
        CurrentCompetenceAssessment object = new CurrentCompetenceAssessment(1);
        CurrentCompetenceAssessment instance = new CurrentCompetenceAssessment();
        boolean expResult = false;
        boolean result = instance.equals(object);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testEquals2() {
        System.out.println("equals2");
        CurrentCompetenceAssessment object = new CurrentCompetenceAssessment();
        CurrentCompetenceAssessment instance = new CurrentCompetenceAssessment(1);
        boolean expResult = false;
        boolean result = instance.equals(object);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testEquals3() {
        System.out.println("equals3");
        CurrentCompetenceAssessment object = new CurrentCompetenceAssessment(1);
        CurrentCompetenceAssessment instance = new CurrentCompetenceAssessment(1);
        boolean expResult = true;
        boolean result = instance.equals(object);
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class CurrentCompetenceAssessment.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Integer idResult = 1;
        CurrentCompetenceAssessment instance = new CurrentCompetenceAssessment(idResult);
        String expResult = "com.mycompany.mavenproject1.CurrentCompetenceAssessment[ idcurrentCompetenceAssessment=" + idResult + " ]";
        String result = instance.toString();
        assertEquals(expResult, result);
    }
    
}
