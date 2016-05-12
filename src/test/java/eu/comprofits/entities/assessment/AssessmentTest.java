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
public class AssessmentTest {
    
    public AssessmentTest() {
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
     * Test of getIdassessment method, of class Assessment.
     */
    @Test
    public void testGetSetIdassessment() {
        System.out.println("getsetIdassessment");
        Assessment instance = new Assessment();
        Integer expResult = 1;
        instance.setIdassessment(expResult);
        Integer result = instance.getIdassessment();
        assertEquals(expResult, result);
    }


    /**
     * Test of getDateCreated method, of class Assessment.
     */
    @Test
    public void testGetSetDateCreated() {
        System.out.println("getsetDateCreated");
        Assessment instance = new Assessment();
        Date expResult = new Date();
        instance.setDateCreated(expResult);
        Date result = instance.getDateCreated();
        assertEquals(expResult, result);
    }


    /**
     * Test of getDeadline method, of class Assessment.
     */
    @Test
    public void testGetSetDeadline() {
        System.out.println("getsetDeadline");
        Assessment instance = new Assessment();
        Date expResult = new Date();
        instance.setDeadline(expResult);
        Date result = instance.getDeadline();
        assertEquals(expResult, result);
    }


    /**
     * Test of isCompleted method, of class Assessment.
     */
    @Test
    public void testSetIsCompleted() {
        System.out.println("setisCompleted");
        Assessment instance = new Assessment();
        boolean expResult = false;
        instance.setCompleted(expResult);
        boolean result = instance.isCompleted();
        assertEquals(expResult, result);
    }


    /**
     * Test of getConclusion method, of class Assessment.
     */
    @Test
    public void testGetSetConclusion() {
        System.out.println("getsetConclusion");
        Assessment instance = new Assessment();
        String expResult = "wow";
        instance.setConclusion(expResult);
        String result = instance.getConclusion();
        assertEquals(expResult, result);
    }


    /**
     * Test of getEmployeeCompetenceAssessmentCollection method, of class Assessment.
     */
    @Test
    public void testGetSetEmployeeCompetenceAssessmentCollection() {
        System.out.println("getsetEmployeeCompetenceAssessmentCollection");
        Assessment instance = new Assessment();
        EmployeeCompetenceAssessment x1 = new EmployeeCompetenceAssessment(1);
        EmployeeCompetenceAssessment x2 = new EmployeeCompetenceAssessment(2);
        Collection<EmployeeCompetenceAssessment> expResult = new HashSet();
        expResult.add(x1);
        expResult.add(x2);
        instance.setEmployeeCompetenceAssessmentCollection(expResult);
        Collection<EmployeeCompetenceAssessment> result = instance.getEmployeeCompetenceAssessmentCollection();
        assertEquals(expResult, result);
    }


    /**
     * Test of getImmediateManagerIdemployee method, of class Assessment.
     */
    @Test
    public void testGetSetImmediateManagerIdemployee() {
        System.out.println("getsetImmediateManagerIdemployee");
        Assessment instance = new Assessment();
        Employee expResult = new Employee(1);
        instance.setImmediateManagerIdemployee(expResult);
        Employee result = instance.getImmediateManagerIdemployee();
        assertEquals(expResult, result);
    }


    /**
     * Test of getColleague3Idemployee method, of class Assessment.
     */
    @Test
    public void testGetSetColleague3Idemployee() {
        System.out.println("getsetColleague3Idemployee");
        Assessment instance = new Assessment();
        Employee expResult = new Employee(1);
        instance.setColleague3Idemployee(expResult);
        Employee result = instance.getColleague3Idemployee();
        assertEquals(expResult, result);
    }


    /**
     * Test of getColleague2Idemployee method, of class Assessment.
     */
    @Test
    public void testGetSetColleague2Idemployee() {
        System.out.println("getSetColleague2Idemployee");
        Assessment instance = new Assessment();
        Employee expResult = new Employee(1);
        instance.setColleague2Idemployee(expResult);
        Employee result = instance.getColleague2Idemployee();
        assertEquals(expResult, result);
    }


    /**
     * Test of getColleague1Idemployee method, of class Assessment.
     */
    @Test
    public void testGetSetColleague1Idemployee() {
        System.out.println("getSetColleague1Idemployee");
        Assessment instance = new Assessment();
        Employee expResult = new Employee(1);
        instance.setColleague1Idemployee(expResult);
        Employee result = instance.getColleague1Idemployee();
        assertEquals(expResult, result);

    }


    /**
     * Test of getAssesseeIdemployee method, of class Assessment.
     */
    @Test
    public void testGetSetAssesseeIdemployee() {
        System.out.println("getsetAssesseeIdemployee");
        Assessment instance = new Assessment();
        Employee expResult = new Employee(1);
        instance.setAssesseeIdemployee(expResult);
        Employee result = instance.getAssesseeIdemployee();
        assertEquals(expResult, result);
    }

    /**
     * Test of getIssueDate method, of class Assessment.
     */
    @Test
    public void testGetSetIssueDate() {
        System.out.println("getsetIssueDate");
        Assessment instance = new Assessment();
        Date expResult = new Date();
        instance.setIssueDate(expResult);
        Date result = instance.getIssueDate();
        assertEquals(expResult, result);
    }


    /**
     * Test of getShortDate method, of class Assessment.
     */
    @Test
    public void testGetShortDate() {
        System.out.println("getShortDate");
        Assessment instance = new Assessment();
        Date date = new Date(93,10,11);
        instance.setDateCreated(date);
        String expResult = "11/11/1993";
        String result = instance.getShortDate();
        assertEquals(expResult, result);
    }

    /**
     * Test of getShortIssueDate method, of class Assessment.
     */
    @Test
    public void testGetShortIssueDate() {
        System.out.println("getShortIssueDate");
        Assessment instance = new Assessment();
        Date date = new Date(93,10,11);
        instance.setIssueDate(date);
        String expResult = "11/11/1993";
        String result = instance.getShortIssueDate();
        assertEquals(expResult, result);
    }

    /**
     * Test of getShortDeadlineDate method, of class Assessment.
     */
    @Test
    public void testGetShortDeadlineDate() {
        System.out.println("getShortDeadlineDate");
        Assessment instance = new Assessment();
        Date date = new Date(93,10,11);
        instance.setDeadline(date);
        String expResult = "11/11/1993";
        String result = instance.getShortDeadlineDate();
        assertEquals(expResult, result);
    }

    /**
     * Test of hashCode method , of class Assessment.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        Assessment instance = new Assessment();
        int expResult = 0;
        int result = instance.hashCode();
        assertEquals(expResult, result);
    }
    @Test
    public void testHashCode1() {
        System.out.println("hashCode1");
        Assessment instance = new Assessment(1);
        int expResult = 1;
        int result = instance.hashCode();
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Assessment.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object object = null;
        Assessment instance = new Assessment();
        boolean expResult = false;
        boolean result = instance.equals(object);
        assertEquals(expResult, result);
    }
    @Test
    public void testEquals1() {
        System.out.println("equals1");
        Assessment object = new Assessment(1);
        Assessment instance = new Assessment();
        boolean expResult = false;
        boolean result = instance.equals(object);
        assertEquals(expResult, result);
    }
    @Test
    public void testEquals2() {
        System.out.println("equals2");
        Assessment object = new Assessment();
        Assessment instance = new Assessment(1);
        boolean expResult = false;
        boolean result = instance.equals(object);
        assertEquals(expResult, result);
    }
    @Test
    public void testEquals3() {
        System.out.println("equals3");
        Assessment object = new Assessment(1);
        Assessment instance = new Assessment(1);
        boolean expResult = true;
        boolean result = instance.equals(object);
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class Assessment.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Assessment instance = new Assessment();
        Date date = new Date(93,10,11);
        instance.setDateCreated(date);
        Employee x1 = new Employee();
        x1.setLastName("Papantonis");
        x1.setFirstName("Chris");
        instance.setAssesseeIdemployee(x1);
        String expResult = "11/11/1993 Papantonis Chris";
        String result = instance.toString();
        assertEquals(expResult, result);
    }
    
}
