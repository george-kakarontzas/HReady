/* 
 * Copyright 2016 ComProFITS Project.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package eu.comprofits.entities.assessment;

import eu.comprofits.entities.employee.Employee;
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
public class EmployeeCompetenceAssessmentTest {
    
    public EmployeeCompetenceAssessmentTest() {
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
     * Test of getIdemployeeCompetenceAssessment method, of class EmployeeCompetenceAssessment.
     */
    @Test
    public void testGetSetIdemployeeCompetenceAssessment() {
        System.out.println("getsetIdemployeeCompetenceAssessment");
        EmployeeCompetenceAssessment instance = new EmployeeCompetenceAssessment();
        Integer expResult = 1;
        instance.setIdemployeeCompetenceAssessment(expResult);
        Integer result = instance.getIdemployeeCompetenceAssessment();
        assertEquals(expResult, result);
    }


    /**
     * Test of getAssessment method, of class EmployeeCompetenceAssessment.
     */
    @Test
    public void testGetSetAssessment() {
        System.out.println("getsetAssessment");
        EmployeeCompetenceAssessment instance = new EmployeeCompetenceAssessment();
        Integer expResult = 1;
        instance.setAssessment(expResult);
        Integer result = instance.getAssessment();
        assertEquals(expResult, result);
    }


    /**
     * Test of getAssessorIdemployee method, of class EmployeeCompetenceAssessment.
     */
    @Test
    public void testGetSetAssessorIdemployee() {
        System.out.println("getsetAssessorIdemployee");
        EmployeeCompetenceAssessment instance = new EmployeeCompetenceAssessment();
        Employee expResult = new Employee(1);
        instance.setAssessorIdemployee(expResult);
        Employee result = instance.getAssessorIdemployee();
        assertEquals(expResult, result);
    }

    /**
     * Test of getCompetenceIdcompetence method, of class EmployeeCompetenceAssessment.
     */
    @Test
    public void testGetSetCompetenceIdcompetence() {
        System.out.println("getsetCompetenceIdcompetence");
        EmployeeCompetenceAssessment instance = new EmployeeCompetenceAssessment();
        Competence expResult = new Competence(1);
        instance.setCompetenceIdcompetence(expResult);
        Competence result = instance.getCompetenceIdcompetence();
        assertEquals(expResult, result);
    }


    /**
     * Test of getAssessmentIdassessment method, of class EmployeeCompetenceAssessment.
     */
    @Test
    public void testGetSetAssessmentIdassessment() {
        System.out.println("getsetAssessmentIdassessment");
        EmployeeCompetenceAssessment instance = new EmployeeCompetenceAssessment();
        Assessment expResult = new Assessment(1);
        instance.setAssessmentIdassessment(expResult);
        Assessment result = instance.getAssessmentIdassessment();
        assertEquals(expResult, result);
    }


    /**
     * Test of hashCode method, of class EmployeeCompetenceAssessment.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        EmployeeCompetenceAssessment instance = new EmployeeCompetenceAssessment();
        int expResult = 0;
        int result = instance.hashCode();
        assertEquals(expResult, result);
    }
    @Test
    public void testHashCode1() {
        System.out.println("hashCode1");
        EmployeeCompetenceAssessment instance = new EmployeeCompetenceAssessment(1);
        int expResult = 1;
        int result = instance.hashCode();
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class EmployeeCompetenceAssessment.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object object = null;
        EmployeeCompetenceAssessment instance = new EmployeeCompetenceAssessment();
        boolean expResult = false;
        boolean result = instance.equals(object);
        assertEquals(expResult, result);
    }
    @Test
    public void testEquals1() {
        System.out.println("equals1");
        EmployeeCompetenceAssessment object = new EmployeeCompetenceAssessment(1);
        EmployeeCompetenceAssessment instance = new EmployeeCompetenceAssessment();
        boolean expResult = false;
        boolean result = instance.equals(object);
        assertEquals(expResult, result);
    }
    @Test
    public void testEquals2() {
        System.out.println("equals2");
        EmployeeCompetenceAssessment object = new EmployeeCompetenceAssessment();
        EmployeeCompetenceAssessment instance = new EmployeeCompetenceAssessment(1);
        boolean expResult = false;
        boolean result = instance.equals(object);
        assertEquals(expResult, result);
    }
    @Test
    public void testEquals3() {
        System.out.println("equals3");
        EmployeeCompetenceAssessment object = new EmployeeCompetenceAssessment(1);
        EmployeeCompetenceAssessment instance = new EmployeeCompetenceAssessment(1);
        boolean expResult = true;
        boolean result = instance.equals(object);
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class EmployeeCompetenceAssessment.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Integer idResult = 1;
        EmployeeCompetenceAssessment instance = new EmployeeCompetenceAssessment(idResult);
        String expResult = "com.mycompany.mavenproject1.EmployeeCompetenceAssessment[ idemployeeCompetenceAssessment=" + idResult + " ]";
        String result = instance.toString();
        assertEquals(expResult, result);
    }

    /**
     * Test of getStatementIdstatement method, of class EmployeeCompetenceAssessment.
     */
    @Test
    public void testGetSetStatementIdstatement() {
        System.out.println("getsetStatementIdstatement");
        EmployeeCompetenceAssessment instance = new EmployeeCompetenceAssessment();
        Statement expResult = new Statement(1);
        instance.setStatementIdstatement(expResult);
        Statement result = instance.getStatementIdstatement();
        assertEquals(expResult, result);
    }

}
