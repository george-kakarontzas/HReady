/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.comprofits.entities.edr;

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
public class CompetenceGoalTest {
    
    public CompetenceGoalTest() {
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
     * Test of CompetenceGoal constructor, of class CompetenceGoal.
     */
    @Test
    public void testCompetenceGoal() {
        System.out.println("CompetenceGoal");
        Integer idResult = 1;
        Edr edr = new Edr(1);
        CompetenceGoal instance = new CompetenceGoal(idResult,edr);        
        instance.setIdcompetenceGoal(idResult);
        Integer result = instance.getIdcompetenceGoal();
        assertEquals("error id competence goal",idResult, result);
        assertEquals("error edrid",edr, instance.getEdrIdedr());
    }
    
    /**
     * Test of getIdcompetenceGoal method, of class CompetenceGoal.
     */
    @Test
    public void testGetSetIdcompetenceGoal() {
        System.out.println("getsetIdcompetenceGoal");
        CompetenceGoal instance = new CompetenceGoal();
        Integer expResult = 1;
        instance.setIdcompetenceGoal(expResult);
        Integer result = instance.getIdcompetenceGoal();
        assertEquals(expResult, result);
    }

    /**
     * Test of getNextYearGoalValue method, of class CompetenceGoal.
     */
    @Test
    public void testGetSetNextYearGoalValue() {
        System.out.println("getsetNextYearGoalValue");
        CompetenceGoal instance = new CompetenceGoal();
        int expResult = 3;
        instance.setNextYearGoalValue(expResult);
        int result = instance.getNextYearGoalValue();
        assertEquals(expResult, result);
    }


    /**
     * Test of getComments method, of class CompetenceGoal.
     */
    @Test
    public void testGetSetComments() {
        System.out.println("getsetComments");
        CompetenceGoal instance = new CompetenceGoal();
        String expResult = "comment";
        instance.setComments(expResult);
        String result = instance.getComments();
        assertEquals(expResult, result);
    }

    /**
     * Test of getCompetenceIdcompetence method, of class CompetenceGoal.
     */
    @Test
    public void testGetSetCompetenceIdcompetence() {
        System.out.println("getsetCompetenceIdcompetence");
        CompetenceGoal instance = new CompetenceGoal();
        Competence expResult = new Competence(1);
        instance.setCompetenceIdcompetence(expResult);
        Competence result = instance.getCompetenceIdcompetence();
        assertEquals(expResult, result);
    }

    /**
     * Test of hashCode method, of class CompetenceGoal.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        CompetenceGoal instance = new CompetenceGoal();
        int expResult = 0;
        int result = instance.hashCode();
        assertEquals(expResult, result);
    }
    @Test
    public void testHashCode1() {
        System.out.println("hashCode1");
        CompetenceGoal instance = new CompetenceGoal(1);
        int expResult = 1;
        int result = instance.hashCode();
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class CompetenceGoal.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object object = null;
        CompetenceGoal instance = new CompetenceGoal();
        boolean expResult = false;
        boolean result = instance.equals(object);
        assertEquals(expResult, result);
    }
    @Test
    public void testEquals1() {
        System.out.println("equals1");
        CompetenceGoal object = new CompetenceGoal();
        CompetenceGoal instance = new CompetenceGoal(1);
        boolean expResult = false;
        boolean result = instance.equals(object);
        assertEquals(expResult, result);
    }
    @Test
    public void testEquals2() {
        System.out.println("equals2");
        CompetenceGoal object = new CompetenceGoal(1);
        CompetenceGoal instance = new CompetenceGoal();
        boolean expResult = false;
        boolean result = instance.equals(object);
        assertEquals(expResult, result);
    }
    @Test
    public void testEquals3() {
        System.out.println("equals3");
        CompetenceGoal object = new CompetenceGoal(1);
        CompetenceGoal instance = new CompetenceGoal(1);
        boolean expResult = true;
        boolean result = instance.equals(object);
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class CompetenceGoal.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Integer Result = 1;
        CompetenceGoal instance = new CompetenceGoal(Result);
        String expResult = "com.mycompany.mavenproject1.CompetenceGoal[ idcompetenceGoal=" + Result + " ]";
        String result = instance.toString();
        assertEquals(expResult, result);
    }

    /**
     * Test of getEdrIdedr method, of class CompetenceGoal.
     */
    @Test
    public void testGetSetEdrIdedr() {
        System.out.println("getsetEdrIdedr");
        CompetenceGoal instance = new CompetenceGoal();
        Edr expResult = new Edr(1);
        instance.setEdrIdedr(expResult);
        Edr result = instance.getEdrIdedr();
        assertEquals(expResult, result);
    }

    /**
     * Test of getCompetenceName method, of class CompetenceGoal.
     */
    @Test
    public void testGetCompetenceName() {
        System.out.println("getCompetenceName");
        CompetenceGoal instance = new CompetenceGoal();
        Competence x1 = new Competence(1);
        String expResult = "comp";
        x1.setCompetenceName(expResult);        
        instance.setCompetenceIdcompetence(x1);
        String result = instance.getCompetenceName();
        assertEquals(expResult, result);
    }
    
}
