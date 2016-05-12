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

import eu.comprofits.entities.main.Competence;
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
public class StatementTest {
    
    public StatementTest() {
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
     * Test of Statement constructor, of class Statement.
     */
    @Test
    public void testStatement() {
        System.out.println("Statement");
        Statement instance = new Statement(1,"asd");
        Integer idResult = 1;
        Integer result = instance.getIdstatement();
        assertEquals("error id",idResult, result);
        assertEquals("error text",instance.getStatementText(),"asd");
    }

    /**
     * Test of getIdstatement method, of class Statement.
     */
    @Test
    public void testGetSetIdstatement() {
        System.out.println("getsetIdstatement");
        Statement instance = new Statement();
        Integer expResult = 1;
        instance.setIdstatement(expResult);
        Integer result = instance.getIdstatement();
        assertEquals(expResult, result);
    }

    /**
     * Test of getStatementText method, of class Statement.
     */
    @Test
    public void testGetSetStatementText() {
        System.out.println("getsetStatementText");
        Statement instance = new Statement();
        String expResult = "asd";
        instance.setStatementText("asd");
        String result = instance.getStatementText();
        assertEquals(expResult, result);
    }


    /**
     * Test of getCompetenceId method, of class Statement.
     */
    @Test
    public void testGetCompetenceId() {
        System.out.println("getCompetenceId");
        Statement instance = new Statement();
        Competence expResult = new Competence(1);
        instance.setCompetenceId(expResult);
        Competence result = instance.getCompetenceId();
        assertEquals(expResult, result);
    }


    /**
     * Test of getEmployeeCompetenceAssessmentCollection method, of class Statement.
     */
    @Test
    public void testGetSetEmployeeCompetenceAssessmentCollection() {
        System.out.println("getsetEmployeeCompetenceAssessmentCollection");
        Statement instance = new Statement();
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
     * Test of hashCode method, of class Statement.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        Statement instance = new Statement();
        int expResult = 0;
        int result = instance.hashCode();
        assertEquals(expResult, result);
    }
    @Test
    public void testHashCode1() {
        System.out.println("hashCode1");
        Statement instance = new Statement(1);
        int expResult = 1;
        int result = instance.hashCode();
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Statement.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object object = null;
        Statement instance = new Statement();
        boolean expResult = false;
        boolean result = instance.equals(object);
        assertEquals(expResult, result);
    }
    @Test
    public void testEquals1() {
        System.out.println("equals1");
        Statement object = new Statement(1);
        Statement instance = new Statement();
        boolean expResult = false;
        boolean result = instance.equals(object);
        assertEquals(expResult, result);
    }
    @Test
    public void testEquals2() {
        System.out.println("equals2");
        Statement object = new Statement();
        Statement instance = new Statement(1);
        boolean expResult = false;
        boolean result = instance.equals(object);
        assertEquals(expResult, result);
    }
    @Test
    public void testEquals3() {
        System.out.println("equals3");
        Statement object = new Statement(1);
        Statement instance = new Statement(1);
        boolean expResult = true;
        boolean result = instance.equals(object);
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class Statement.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Statement instance = new Statement();
        String expResult = "asd";
        instance.setStatementText(expResult);
        String result = instance.toString();
        assertEquals(expResult, result);
    }
    
}
