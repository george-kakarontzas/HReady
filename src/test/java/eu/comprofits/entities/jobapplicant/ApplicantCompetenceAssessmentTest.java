/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.comprofits.entities.jobapplicant;

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
public class ApplicantCompetenceAssessmentTest {
    
    public ApplicantCompetenceAssessmentTest() {
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
     * Test of getIdapplicantCompetenceAssessment method, of class ApplicantCompetenceAssessment.
     */
    @Test
    public void testGetSetIdapplicantCompetenceAssessment() {
        System.out.println("getsetIdapplicantCompetenceAssessment");
        ApplicantCompetenceAssessment instance = new ApplicantCompetenceAssessment();
        Integer expResult = 1;
        instance.setIdapplicantCompetenceAssessment(expResult);
        Integer result = instance.getIdapplicantCompetenceAssessment();
        assertEquals(expResult, result);
    }


    /**
     * Test of getValue method, of class ApplicantCompetenceAssessment.
     */
    @Test
    public void testSetGetValue() {
        System.out.println("getsetValue");
        ApplicantCompetenceAssessment instance = new ApplicantCompetenceAssessment();
        Integer expResult = 1;
        instance.setValue(expResult);
        Integer result = instance.getValue();
        assertEquals(expResult, result);
    }


    /**
     * Test of getJobApplicationId method, of class ApplicantCompetenceAssessment.
     */
    @Test
    public void testGetSetJobApplicationId() {
        System.out.println("getsetJobApplicationId");
        ApplicantCompetenceAssessment instance = new ApplicantCompetenceAssessment();
        JobApplication expResult = new JobApplication(1);
        instance.setJobApplicationId(expResult);
        JobApplication result = instance.getJobApplicationId();
        assertEquals(expResult, result);
    }

    /**
     * Test of getCompetenceId method, of class ApplicantCompetenceAssessment.
     */
    @Test
    public void testGetSetCompetenceId() {
        System.out.println("getCompetenceId");
        ApplicantCompetenceAssessment instance = new ApplicantCompetenceAssessment();
        Competence expResult = new Competence(1);
        instance.setCompetenceId(expResult);
        Competence result = instance.getCompetenceId();
        assertEquals(expResult, result);
    }

    /**
     * Test of hashCode method, of class ApplicantCompetenceAssessment.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        ApplicantCompetenceAssessment instance = new ApplicantCompetenceAssessment();
        int expResult = 0;
        int result = instance.hashCode();
        assertEquals(expResult, result);
    }
    @Test
    public void testHashCode1() {
        System.out.println("hashCode1");
        ApplicantCompetenceAssessment instance = new ApplicantCompetenceAssessment(1);
        int expResult = 1;
        int result = instance.hashCode();
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class ApplicantCompetenceAssessment.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object object = null;
        ApplicantCompetenceAssessment instance = new ApplicantCompetenceAssessment();
        boolean expResult = false;
        boolean result = instance.equals(object);
        assertEquals(expResult, result);
    }
    @Test
    public void testEquals1() {
        System.out.println("equals1");
        ApplicantCompetenceAssessment object = new ApplicantCompetenceAssessment();
        ApplicantCompetenceAssessment instance = new ApplicantCompetenceAssessment(1);
        boolean expResult = false;
        boolean result = instance.equals(object);
        assertEquals(expResult, result);
    }
    @Test
    public void testEquals2() {
        System.out.println("equals2");
        ApplicantCompetenceAssessment object = new ApplicantCompetenceAssessment(1);
        ApplicantCompetenceAssessment instance = new ApplicantCompetenceAssessment();
        boolean expResult = false;
        boolean result = instance.equals(object);
        assertEquals(expResult, result);
    }
    @Test
    public void testEquals3() {
        System.out.println("equals3");
        ApplicantCompetenceAssessment object = new ApplicantCompetenceAssessment(1);
        ApplicantCompetenceAssessment instance = new ApplicantCompetenceAssessment(1);
        boolean expResult = true;
        boolean result = instance.equals(object);
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class ApplicantCompetenceAssessment.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Integer idResult = 1;
        ApplicantCompetenceAssessment instance = new ApplicantCompetenceAssessment(idResult);
        String expResult = "com.mycompany.mavenproject1.ApplicantCompetenceAssessment[ idapplicantCompetenceAssessment=" + idResult + " ]";
        String result = instance.toString();
        assertEquals(expResult, result);
    }
    
}
