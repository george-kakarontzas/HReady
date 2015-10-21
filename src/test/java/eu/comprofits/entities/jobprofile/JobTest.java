/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.comprofits.entities.jobprofile;

import eu.comprofits.entities.employee.Employee;
import eu.comprofits.entities.employee.InCompanyEmployment;
import eu.comprofits.entities.jobapplicant.JobAdvertisement;
import eu.comprofits.entities.main.Department;
import eu.comprofits.entities.main.OrganisationalPosition;
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
public class JobTest {
    
    public JobTest() {
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
     * Test of Job constructor, of class Job.
     */
    @Test
    public void testJob() {
        System.out.println("Job");
        Integer idResult = 1;
        Job instance = new Job(idResult,"title","desc");        
        Integer result = instance.getIdjob();
        assertEquals("error idjob",idResult, result);
        assertEquals("error jobTitle",instance.getJobTitle(),"title");
        assertEquals("error jobDescription",instance.getJobDescription(),"desc");
    }

    /**
     * Test of getStatus method, of class Job.
     */
    @Test
    public void testSetGetStatus() {
        System.out.println("getsetStatus");
        Job instance = new Job();
        Boolean expResult = true;
        instance.setStatus(expResult);
        Boolean result = instance.getStatus();
        assertEquals(expResult, result);
    }

    /**
     * Test of getIdjob method, of class Job.
     */
    @Test
    public void testGetSetIdjob() {
        System.out.println("getsetIdjob");
        Job instance = new Job();
        Integer expResult = 1;
        instance.setIdjob(expResult);
        Integer result = instance.getIdjob();
        assertEquals(expResult, result);
    }

    /**
     * Test of getJobTitle method, of class Job.
     */
    @Test
    public void testGetSetJobTitle() {
        System.out.println("getsetJobTitle");
        Job instance = new Job();
        String expResult = "title";
        instance.setJobTitle(expResult);
        String result = instance.getJobTitle();
        assertEquals(expResult, result);
    }

    /**
     * Test of getJobDescription method, of class Job.
     */
    @Test
    public void testGetJobDescription() {
        System.out.println("getJobDescription");
        Job instance = new Job();
        String expResult = "desc";
        instance.setJobDescription(expResult);
        String result = instance.getJobDescription();
        assertEquals(expResult, result);
    }

    /**
     * Test of getProfessionalExperienceMinRequirementsCollection method, of class Job.
     */
    @Test
    public void testGetProfessionalExperienceMinRequirementsCollection() {
        System.out.println("getProfessionalExperienceMinRequirementsCollection");
        Job instance = new Job();
        ProfessionalExperienceMinRequirements x1 = new ProfessionalExperienceMinRequirements(1);
        ProfessionalExperienceMinRequirements x2 = new ProfessionalExperienceMinRequirements(2);
        Collection<ProfessionalExperienceMinRequirements> expResult = new HashSet();
        expResult.add(x1);
        expResult.add(x2);
        instance.setProfessionalExperienceMinRequirementsCollection(expResult);
        Collection<ProfessionalExperienceMinRequirements> result = instance.getProfessionalExperienceMinRequirementsCollection();
        assertEquals(expResult, result);
    }

    /**
     * Test of getJobAdvertisementCollection method, of class Job.
     */
    @Test
    public void testGetSetJobAdvertisementCollection() {
        System.out.println("getsetJobAdvertisementCollection");
        Job instance = new Job();
        JobAdvertisement x1 = new JobAdvertisement(1);
        JobAdvertisement x2 = new JobAdvertisement(2);
        Collection<JobAdvertisement> expResult = new HashSet();
        expResult.add(x1);
        expResult.add(x2);
        instance.setJobAdvertisementCollection(expResult);
        Collection<JobAdvertisement> result = instance.getJobAdvertisementCollection();
        assertEquals(expResult, result);
    }

    /**
     * Test of getJobStudyMinRequirementsCollection method, of class Job.
     */
    @Test
    public void testGetSetJobStudyMinRequirementsCollection() {
        System.out.println("getsetJobStudyMinRequirementsCollection");
        Job instance = new Job();
        JobStudyMinRequirements x1 = new JobStudyMinRequirements(1);
        JobStudyMinRequirements x2 = new JobStudyMinRequirements(2);
        Collection<JobStudyMinRequirements> expResult = new HashSet();
        expResult.add(x1);
        expResult.add(x2);
        instance.setJobStudyMinRequirementsCollection(expResult);
        Collection<JobStudyMinRequirements> result = instance.getJobStudyMinRequirementsCollection();
        assertEquals(expResult, result);
    }

    /**
     * Test of getPlaceEmploymentIdplaceEmployment method, of class Job.
     */
    @Test
    public void testGetSetPlaceEmploymentIdplaceEmployment() {
        System.out.println("getsetPlaceEmploymentIdplaceEmployment");
        Job instance = new Job();
        PlaceEmployment expResult = new PlaceEmployment(1);
        instance.setPlaceEmploymentIdplaceEmployment(expResult);
        PlaceEmployment result = instance.getPlaceEmploymentIdplaceEmployment();
        assertEquals(expResult, result);
    }

    /**
     * Test of getOrganisationalPositionIdorganisationalPosition method, of class Job.
     */
    @Test
    public void testSetGetOrganisationalPositionIdorganisationalPosition() {
        System.out.println("getsetOrganisationalPositionIdorganisationalPosition");
        Job instance = new Job();
        OrganisationalPosition expResult = new OrganisationalPosition(1);
        instance.setOrganisationalPositionIdorganisationalPosition(expResult);
        OrganisationalPosition result = instance.getOrganisationalPositionIdorganisationalPosition();
        assertEquals(expResult, result);
    }

    /**
     * Test of getReportingToIdemployee method, of class Job.
     */
    @Test
    public void testGetSetReportingToIdemployee() {
        System.out.println("getsetReportingToIdemployee");
        Job instance = new Job();
        Employee expResult = new Employee(1);
        instance.setReportingToIdemployee(expResult);
        Employee result = instance.getReportingToIdemployee();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getBusinessAreaIdbusinessArea method, of class Job.
     */
    @Test
    public void testGetSetBusinessAreaIdbusinessArea() {
        System.out.println("getBusinessAreaIdbusinessArea");
        Job instance = new Job();
        Department expResult = new Department(1);
        instance.setDepartmentIddepartment(expResult);
        Department result = instance.getDepartmentIddepartment();
        assertEquals(expResult, result);
    }

    /**
     * Test of getInCompanyEmploymentCollection method, of class Job.
     */
    @Test
    public void testGetSetInCompanyEmploymentCollection() {
        System.out.println("getsetInCompanyEmploymentCollection");
        Job instance = new Job();
        InCompanyEmployment x1 = new InCompanyEmployment(1);
        InCompanyEmployment x2 = new InCompanyEmployment(2);
        Collection<InCompanyEmployment> expResult = new HashSet();
        expResult.add(x1);
        expResult.add(x2);
        instance.setInCompanyEmploymentCollection(expResult);
        Collection<InCompanyEmployment> result = instance.getInCompanyEmploymentCollection();
        assertEquals(expResult, result);
    }

    /**
     * Test of getCompetencesRequirementCollection method, of class Job.
     */
    @Test
    public void testGetCompetencesRequirementCollection() {
        System.out.println("getCompetencesRequirementCollection");
        Job instance = new Job();
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
     * Test of hashCode method, of class Job.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        Job instance = new Job();
        int expResult = 0;
        int result = instance.hashCode();
        assertEquals(expResult, result);
    }
    @Test
    public void testHashCode1() {
        System.out.println("hashCode1");
        Job instance = new Job(1);
        int expResult = 1;
        int result = instance.hashCode();
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Job.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object object = null;
        Job instance = new Job();
        boolean expResult = false;
        boolean result = instance.equals(object);
        assertEquals(expResult, result);
    }
    public void testEquals1() {
        System.out.println("equals1");
        Job object = new Job();
        Job instance = new Job(1);
        boolean expResult = false;
        boolean result = instance.equals(object);
        assertEquals(expResult, result);
    }
    public void testEquals2() {
        System.out.println("equals2");
        Job object = new Job(1);
        Job instance = new Job();
        boolean expResult = false;
        boolean result = instance.equals(object);
        assertEquals(expResult, result);
    }
    public void testEquals3() {
        System.out.println("equals3");
        Job object = new Job(1);
        Job instance = new Job(1);
        boolean expResult = true;
        boolean result = instance.equals(object);
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class Job.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Integer idResult = 1;
        Job instance = new Job(idResult);
        instance.setJobTitle("Programmer");
        String expResult = "Programmer";
        String result = instance.toString();
        assertEquals(expResult, result);
    }
    
}
