/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.comprofits.entities.edr;

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
public class EdrTest {
    
    public EdrTest() {
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
     * Test of getIdedr method, of class Edr.
     */
    @Test
    public void testGetSetIdedr() {
        System.out.println("getsetIdedr");
        Edr instance = new Edr();
        Integer expResult = 1;
        instance.setIdedr(expResult);
        Integer result = instance.getIdedr();
        assertEquals(expResult, result);
    }

    /**
     * Test of getYear method, of class Edr.
     */
    @Test
    public void testGetSetYear() {
        System.out.println("getsetYear");
        Edr instance = new Edr();
        String expResult = "1993";
        instance.setYear(expResult);
        String result = instance.getYear();
        assertEquals(expResult, result);
    }

    /**
     * Test of getStatus method, of class Edr.
     */
    @Test
    public void testGetSetStatus() {
        System.out.println("getsetStatus");
        Edr instance = new Edr();
        Integer expResult = 1;
        instance.setStatus(expResult);
        Integer result = instance.getStatus();
        assertEquals(expResult, result);
    }

    /**
     * Test of getVerdict method, of class Edr.
     */
    @Test
    public void testGetSetVerdict() {
        System.out.println("getsetVerdict");
        Edr instance = new Edr();
        String expResult = "ver";
        instance.setVerdict(expResult);
        String result = instance.getVerdict();
        assertEquals(expResult, result);
    }

    /**
     * Test of getQuestionAnswerCollection method, of class Edr.
     */
    @Test
    public void testGetSetQuestionAnswerCollection() {
        System.out.println("getsetQuestionAnswerCollection");
        Edr instance = new Edr();
        QuestionAnswer x1 = new QuestionAnswer(1);
        QuestionAnswer x2 = new QuestionAnswer(2);
        Collection<QuestionAnswer> expResult = new HashSet();
        expResult.add(x1);
        expResult.add(x2);
        instance.setQuestionAnswerCollection(expResult);
        Collection<QuestionAnswer> result = instance.getQuestionAnswerCollection();
        assertEquals(expResult, result);
    }

    /**
     * Test of getImmediateManagerIdemployee method, of class Edr.
     */
    @Test
    public void testGetSetImmediateManagerIdemployee() {
        System.out.println("getsetImmediateManagerIdemployee");
        Edr instance = new Edr();
        Employee expResult = new Employee(1);
        instance.setImmediateManagerIdemployee(expResult);
        Employee result = instance.getImmediateManagerIdemployee();
        assertEquals(expResult, result);
    }

    /**
     * Test of getReviewedEmployeeIdemployee method, of class Edr.
     */
    @Test
    public void testGetSetReviewedEmployeeIdemployee() {
        System.out.println("getsetReviewedEmployeeIdemployee");
        Edr instance = new Edr();
        Employee expResult = new Employee(1);
        instance.setReviewedEmployeeIdemployee(expResult);
        Employee result = instance.getReviewedEmployeeIdemployee();
        assertEquals(expResult, result);
    }

    /**
     * Test of getEdrCollection method, of class Edr.
     */
    @Test
    public void testGetSetEdrCollection() {
        System.out.println("getsetEdrCollection");
        Edr instance = new Edr();
        Edr x1 = new Edr(1);
        Edr x2 = new Edr(2);
        Collection<Edr> expResult = new HashSet();
        expResult.add(x1);
        expResult.add(x2);
        instance.setEdrCollection(expResult);
        Collection<Edr> result = instance.getEdrCollection();
        assertEquals(expResult, result);
    }


    /**
     * Test of getPreviousEdrIdedr method, of class Edr.
     */
    @Test
    public void testGetSetPreviousEdrIdedr() {
        System.out.println("getsetPreviousEdrIdedr");
        Edr instance = new Edr(1);
        Edr expResult = new Edr(1);
        instance.setPreviousEdrIdedr(instance);
        Edr result = instance.getPreviousEdrIdedr();
        assertEquals(expResult, result);
    }


    /**
     * Test of hashCode method, of class Edr.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        Edr instance = new Edr();
        int expResult = 0;
        int result = instance.hashCode();
        assertEquals(expResult, result);
    }
    @Test
    public void testHashCode1() {
        System.out.println("hashCode1");
        Edr instance = new Edr(1);
        int expResult = 1;
        int result = instance.hashCode();
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Edr.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object object = null;
        Edr instance = new Edr();
        boolean expResult = false;
        boolean result = instance.equals(object);
        assertEquals(expResult, result);
    }
    @Test
    public void testEquals1() {
        System.out.println("equals1");
        Edr object = new Edr(1);
        Edr instance = new Edr();
        boolean expResult = false;
        boolean result = instance.equals(object);
        assertEquals(expResult, result);
    }
    @Test
    public void testEquals2() {
        System.out.println("equals2");
        Edr object = new Edr(1);
        Edr instance = new Edr(1);
        boolean expResult = true;
        boolean result = instance.equals(object);
        assertEquals(expResult, result);
    }
    @Test
    public void testEquals3() {
        System.out.println("equals3");
        Edr object = new Edr();
        Edr instance = new Edr(1);
        boolean expResult = false;
        boolean result = instance.equals(object);
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class Edr.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Integer Result = 1;
        Edr instance = new Edr(Result);
        String expResult = "com.mycompany.mavenproject1.Edr[ idedr=" + Result + " ]";
        String result = instance.toString();
        assertEquals(expResult, result);
    }

    /**
     * Test of followUpOnLatestEdr method, of class Edr.
     */
    @Test
    public void testFollowUpOnLatestEdr() {
        System.out.println("followUpOnLatestEdr");
        Edr edr = null;
        Employee employee = new Employee(1);
        Edr instance = new Edr(1);
        Edr expResult = null;
        Edr result = instance.followUpOnLatestEdr(edr, employee);
        assertEquals(expResult, result);
    }

    /**
     * Test of closeEdrStatusByEmployee method, of class Edr.
     */
    @Test
    public void testCloseEdrStatusByEmployee() {
        System.out.println("closeEdrStatusByEmployee");
        Edr edr = new Edr(1);
        Employee employee = new Employee(1);
        Edr instance = new Edr();
        instance.setReviewedEmployeeIdemployee(employee);
        boolean expResult = true;
        boolean result = instance.closeEdrStatusByEmployee(edr, employee);
        assertEquals(expResult, result);
    }
    @Test
    public void testCloseEdrStatusByEmployee1() {
        System.out.println("closeEdrStatusByEmployee1");
        Edr edr = new Edr(1);
        Employee employee = new Employee(1);
        Employee employee2 = new Employee(2);
        Edr instance = new Edr();
        instance.setReviewedEmployeeIdemployee(employee2);
        boolean expResult = false;
        boolean result = instance.closeEdrStatusByEmployee(edr, employee);
        assertEquals(expResult, result);
    }
}
