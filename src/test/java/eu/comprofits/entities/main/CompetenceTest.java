/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.comprofits.entities.main;

import eu.comprofits.entities.assessment.EmployeeCompetenceAssessment;
import eu.comprofits.entities.assessment.Statement;
import eu.comprofits.entities.edr.CompetenceGoal;
import eu.comprofits.entities.employee.CurrentCompetenceAssessment;
import eu.comprofits.entities.jobapplicant.ApplicantCompetenceAssessment;
import eu.comprofits.entities.jobprofile.CompetencesRequirement;
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
public class CompetenceTest {
    
    public CompetenceTest() {
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
     * Test of getIdcompetence method, of class Competence.
     */
    @Test
    public void testGetSetIdcompetence() {
        System.out.println("getsetIdcompetence");
        Competence instance = new Competence();
        Integer expResult = 1;
        instance.setIdcompetence(expResult);
        Integer result = instance.getIdcompetence();
        assertEquals(expResult, result);
    }

    /**
     * Test of getCompetenceName method, of class Competence.
     */
    @Test
    public void testGetSetCompetenceName() {
        System.out.println("getsetCompetenceName");
        Competence instance = new Competence();
        String expResult = "comp";
        instance.setCompetenceName(expResult);
        String result = instance.getCompetenceName();
        assertEquals(expResult, result);
    }

    /**
     * Test of getCurrentCompetenceAssessmentCollection method, of class Competence.
     */
    @Test
    public void testGetSetCurrentCompetenceAssessmentCollection() {
        System.out.println("getsetCurrentCompetenceAssessmentCollection");
        Competence instance = new Competence();
        CurrentCompetenceAssessment x1 = new CurrentCompetenceAssessment(1);
        CurrentCompetenceAssessment x2 = new CurrentCompetenceAssessment(2);
        Collection<CurrentCompetenceAssessment> expResult = new HashSet();
        expResult.add(x1);
        expResult.add(x2);
        instance.setCurrentCompetenceAssessmentCollection(expResult);
        Collection<CurrentCompetenceAssessment> result = instance.getCurrentCompetenceAssessmentCollection();
        assertEquals(expResult, result);
    }

    /**
     * Test of getCompetenceCollection method, of class Competence.
     */
    @Test
    public void testGetSetCompetenceCollection() {
        System.out.println("getsetCompetenceCollection");
        Competence instance = new Competence();
        Competence x1 = new Competence(1);
        Competence x2 = new Competence(2);
        Collection<Competence> expResult = new HashSet();
        expResult.add(x1);
        expResult.add(x2);
        instance.setCompetenceCollection(expResult);
        Collection<Competence> result = instance.getCompetenceCollection();
        assertEquals(expResult, result);
    }

    /**
     * Test of getParentId method, of class Competence.
     */
    @Test
    public void testGetSetParentId() {
        System.out.println("getsetParentId");
        Competence instance = new Competence();
        Competence expResult = new Competence(1);
        instance.setParentId(expResult);
        Competence result = instance.getParentId();
        assertEquals(expResult, result);
    }

    /**
     * Test of getLevel method, of class Competence.
     */
    @Test
    public void testGetLevel() {
        System.out.println("getLevel");
        Competence instance = new Competence(1);
        int expResult = 1;
        int result = instance.getLevel();
        assertEquals(expResult, result);
    }
    @Test
    public void testGetLevel1() {
        System.out.println("getLevel1");
        Competence instance = new Competence(1);
        Competence instance1 = new Competence(2);
        instance.setParentId(instance1);
        int expResult = 2;
        int result = instance.getLevel();
        assertEquals(expResult, result);
    }

    /**
     * Test of getLeveledLabel method, of class Competence.
     
    @Test
    public void testGetLeveledLabel() {
        System.out.println("getLeveledLabel");
        Competence instance = new Competence(1);
        instance.setCompetenceName("comp");
        String expResult = "-comp";
        String result = instance.getLeveledLabel();
        assertEquals(expResult, result);
    }
    @Test
    public void testGetLeveledLabel1() {
        System.out.println("getLeveledLabel1");
        Competence instance = new Competence(1);
        instance.setCompetenceName("comp");
        Competence instance1 = new Competence(2);
        instance1.setCompetenceName("comp1");
        instance.setParentId(instance1);
        String expResult = "--comp";
        String result = instance.getLeveledLabel();
        assertEquals(expResult, result);
    }*/

    /**
     * Test of getEmployeeCompetenceAssessmentCollection method, of class Competence.
     */
    @Test
    public void testGetSetEmployeeCompetenceAssessmentCollection() {
        System.out.println("getSetEmployeeCompetenceAssessmentCollection");
        Competence instance = new Competence();
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
     * Test of getCompetenceGoalCollection method, of class Competence.
     */
    @Test
    public void testGetSetCompetenceGoalCollection() {
        System.out.println("getsetCompetenceGoalCollection");
        Competence instance = new Competence();
        CompetenceGoal x1 = new CompetenceGoal(1);
        CompetenceGoal x2 = new CompetenceGoal(2);
        Collection<CompetenceGoal> expResult = new HashSet();
        expResult.add(x1);
        expResult.add(x2);
        instance.setCompetenceGoalCollection(expResult);
        Collection<CompetenceGoal> result = instance.getCompetenceGoalCollection();
        assertEquals(expResult, result);
    }


    /**
     * Test of getApplicantCompetenceAssessmentCollection method, of class Competence.
     */
    @Test
    public void testGetSetApplicantCompetenceAssessmentCollection() {
        System.out.println("getsetApplicantCompetenceAssessmentCollection");
        Competence instance = new Competence();
        ApplicantCompetenceAssessment x1 = new ApplicantCompetenceAssessment(1);
        ApplicantCompetenceAssessment x2 = new ApplicantCompetenceAssessment(2);
        Collection<ApplicantCompetenceAssessment> expResult = new HashSet();
        expResult.add(x1);
        expResult.add(x2);
        instance.setApplicantCompetenceAssessmentCollection(expResult);
        Collection<ApplicantCompetenceAssessment> result = instance.getApplicantCompetenceAssessmentCollection();
        assertEquals(expResult, result);

    }

    /**
     * Test of getCompetencesRequirementCollection method, of class Competence.
     */
    @Test
    public void testGetSetCompetencesRequirementCollection() {
        System.out.println("getsetCompetencesRequirementCollection");
        Competence instance = new Competence();
        CompetencesRequirement x1 = new CompetencesRequirement(1);
        CompetencesRequirement x2 = new CompetencesRequirement(2);
        Collection<CompetencesRequirement> expResult = new HashSet();
        expResult.add(x1);
        expResult.add(x2);
        instance.setCompetencesRequirementCollection(expResult);
        Collection<CompetencesRequirement> result = instance.getCompetencesRequirementCollection();
        assertEquals(expResult, result);
    }

    /**
     * Test of hashCode method, of class Competence.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        Competence instance = new Competence();
        int expResult = 0;
        int result = instance.hashCode();
        assertEquals(expResult, result);
    }
    public void testHashCode1() {
        System.out.println("hashCode1");
        Competence instance = new Competence(1);
        int expResult = 1;
        int result = instance.hashCode();
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Competence.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object object = null;
        Competence instance = new Competence();
        boolean expResult = false;
        boolean result = instance.equals(object);
        assertEquals(expResult, result);
    }
    @Test
    public void testEquals1() {
        System.out.println("equals1");
        Competence object = new Competence(1);
        Competence instance = new Competence();
        boolean expResult = false;
        boolean result = instance.equals(object);
        assertEquals(expResult, result);
    }
    @Test
    public void testEquals2() {
        System.out.println("equals2");
        Competence object = new Competence();
        Competence instance = new Competence(1);
        boolean expResult = false;
        boolean result = instance.equals(object);
        assertEquals(expResult, result);
    }
    @Test
    public void testEquals3() {
        System.out.println("equals3");
        Competence object = new Competence(1);
        Competence instance = new Competence(1);
        boolean expResult = true;
        boolean result = instance.equals(object);
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class Competence.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Competence instance = new Competence();
        String expResult = "name";
        instance.setCompetenceName(expResult);
        String result = instance.toString();
        assertEquals(expResult, result);
    }

    /**
     * Test of getStatementCollection method, of class Competence.
     */
    @Test
    public void testGetSetStatementCollection() {
        System.out.println("getsetStatementCollection");
        Competence instance = new Competence();
        Statement x1 = new Statement(1);
        Statement x2 = new Statement(2);
        Collection<Statement> expResult = new HashSet();
        expResult.add(x1);
        expResult.add(x2);
        instance.setStatementCollection(expResult);
        Collection<Statement> result = instance.getStatementCollection();
        assertEquals(expResult, result);
    }
    
}
