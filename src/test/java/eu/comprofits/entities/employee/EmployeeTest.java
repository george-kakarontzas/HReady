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
package eu.comprofits.entities.employee;

import eu.comprofits.entities.assessment.Assessment;
import eu.comprofits.entities.assessment.EmployeeCompetenceAssessment;
import eu.comprofits.entities.edr.Edr;
import eu.comprofits.entities.jobprofile.Division;
import eu.comprofits.entities.jobprofile.Job;
import eu.comprofits.entities.main.Department;
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
public class EmployeeTest {
    
    public EmployeeTest() {
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
     * Test of Employee constructor, of class Employee.
     */
    @Test
    public void testEmployee() {
        System.out.println("employee");
        Date date = new Date();
        Employee instance = new Employee(1,"2","chris","papantonis","Nialas","43100","Karditsa","Greece",date,"69898","pap@yahoo.gr","asd","asd",true);
        Integer expResult = 1;
        Integer result = instance.getIdemployee();
        assertEquals("error id",expResult, result);
        assertEquals("error socialsecuritynumber",instance.getSocialSecurityNumber(),"2");
        assertEquals("error fname",instance.getFirstName(),"chris");
        assertEquals("error lname",instance.getLastName(),"papantonis");
        assertEquals("error address",instance.getAddress(),"Nialas");
        assertEquals("error postalcode",instance.getPostalCode(),"43100");
        assertEquals("error city",instance.getCity(),"Karditsa");
        assertEquals("error country",instance.getCountry(),"Greece");
        assertEquals("error birthdate",instance.getDateOfBirth(),date);
        assertEquals("error mobile",instance.getPhoneMobile(),"69898");
        assertEquals("error e-mail",instance.getEmail(),"pap@yahoo.gr");
        assertEquals("error username",instance.getUsername(),"asd");
        assertEquals("error password",instance.getPassword(),"asd");
        assertEquals("error active",instance.getIsActive(),true);
    }
    
    /**
     * Test of getIdemployee method, of class Employee.
     */
    @Test
    public void testGetIdemployee() {
        System.out.println("getIdemployee");
        Employee instance = new Employee();
        Integer expResult = null;
        Integer result = instance.getIdemployee();
        assertEquals(expResult, result);
    }

    /**
     * Test of setIdemployee method, of class Employee.
     */
    @Test
    public void testGetSetIdemployee() {
        System.out.println("getsetIdemployee");
        Integer idemployee = 13;
        Employee instance = new Employee(12);
        instance.setIdemployee(idemployee);
        assertEquals("error",instance.getIdemployee(),idemployee);
    }

    /**
     * Test of getIdentityCardNumber method, of class Employee.
     */
    @Test
    public void testGetSetIdentityCardNumber() {
        System.out.println("getsetIdentityCardNumber");
        Employee instance = new Employee();
        String expResult = "132asd";
        instance.setIdentityCardNumber(expResult);
        String result = instance.getIdentityCardNumber();
        assertEquals(expResult, result);
    }

    /**
     * Test of setIdentityCardNumber method, of class Employee.
     */

    @Test
    public void testGetSetSocialSecurityNumber() {
        System.out.println("getsetSocialSecurityNumber");
        Employee instance = new Employee();
        String expResult = "1553asd";
        instance.setSocialSecurityNumber(expResult);
        String result = instance.getSocialSecurityNumber();
        assertEquals(expResult, result);
    }

    /**
     * Test of setSocialSecurityNumber method, of class Employee.
     */
    
    @Test
    public void testGetSetFirstName() {
        System.out.println("getsetFirstName");
        Employee instance = new Employee();
        String expResult = "Chris";
        instance.setFirstName(expResult);
        String result = instance.getFirstName();
        assertEquals(expResult, result);
    }

    /**
     * Test of getFullName method, of class Employee.
     */
    @Test
    public void testGetFullName() {
        System.out.println("getFullName");
        Employee instance = new Employee();
        instance.setFirstName("Chris");
        instance.setLastName("Papantonis");
        String expResult = ("Chris Papantonis");
        String result = instance.getFullName();
        assertEquals(expResult, result);
    }


    /**
     * Test of getLastName method, of class Employee.
     */
    @Test
    public void testGetSetLastName() {
        System.out.println("getsetLastName");
        Employee instance = new Employee();
        String expResult = "Papantonis";
        instance.setLastName(expResult);
        String result = instance.getLastName();
        assertEquals(expResult, result);
    }

    /**
     * Test of getGender method, of class Employee.
     */
    @Test
    public void testGetSetGender() {
        System.out.println("getsetGender");
        Employee instance = new Employee();
        Integer expResult = 1;
        instance.setGender(expResult);
        Integer result = instance.getGender();
        assertEquals(expResult, result);
    }

    /**
     * Test of getProvince method, of class Employee.
     */
    @Test
    public void testGetSetProvince() {
        System.out.println("getsetProvince");
        Employee instance = new Employee();
        String expResult = "Larisa";
        instance.setProvince(expResult);
        String result = instance.getProvince();
        assertEquals(expResult, result);
    }

    /**
     * Test of getAddress method, of class Employee.
     */
    @Test
    public void testGetSetAddress() {
        System.out.println("getsetAddress");
        Employee instance = new Employee();
        String expResult = "Palama";
        instance.setAddress(expResult);
        String result = instance.getAddress();
        assertEquals(expResult, result);
    }

    /**
     * Test of getPostalCode method, of class Employee.
     */
    @Test
    public void testGetPostalCode() {
        System.out.println("getPostalCode");
        Employee instance = new Employee();
        String expResult = "43100";
        instance.setPostalCode(expResult);
        String result = instance.getPostalCode();
        assertEquals(expResult, result);
    }

    /**
     * Test of getCity method, of class Employee.
     */
    @Test
    public void testGetSetCity() {
        System.out.println("getsetCity");
        Employee instance = new Employee();
        String expResult = "Larisa";
        instance.setCity(expResult);
        String result = instance.getCity();
        assertEquals(expResult, result);
    }

    /**
     * Test of getCountry method, of class Employee.
     */
    @Test
    public void testGetSetCountry() {
        System.out.println("getsetCountry");
        Employee instance = new Employee();
        String expResult = "Greece";
        instance.setCountry(expResult);
        String result = instance.getCountry();
        assertEquals(expResult, result);
    }


    /**
     * Test of getDateOfBirth method, of class Employee.
     */
    @Test
    public void testGetDateOfBirth() {
        System.out.println("getDateOfBirth");
        Employee instance = new Employee();
        Date expResult = new Date();
        instance.setDateOfBirth(expResult);
        Date result = instance.getDateOfBirth();
        assertEquals("getDateOfBirth",expResult, result);
    }

    /**
     * Test of getPhonePrivate method, of class Employee.
     */
    @Test
    public void testGetSetPhonePrivate() {
        System.out.println("getSetPhonePrivate");
        Employee instance = new Employee();
        String expResult = "699989";
        instance.setPhonePrivate(expResult);
        String result = instance.getPhonePrivate();
        assertEquals(expResult, result);
    }

    /**
     * Test of getPhoneMobile method, of class Employee.
     */
    @Test
    public void testGetSetPhoneMobile() {
        System.out.println("getSetPhoneMobile");
        Employee instance = new Employee();
        String expResult = "6988";
        instance.setPhoneMobile(expResult);
        String result = instance.getPhoneMobile();
        assertEquals(expResult, result);
    }

    /**
     * Test of getEmail method, of class Employee.
     */
    @Test
    public void testGetSetEmail() {
        System.out.println("getsetEmail");
        Employee instance = new Employee();
        String expResult = "papantonisc@hotmail.com";
        instance.setEmail(expResult);
        String result = instance.getEmail();
        assertEquals(expResult, result);
    }

    /**
     * Test of getPhotoPath method, of class Employee.
     */
    @Test
    public void testGetSetPhotoPath() {
        System.out.println("getsetPhotoPath");
        Employee instance = new Employee();
        String expResult = "C:asdfasdf";
        instance.setPhotoPath(expResult);
        String result = instance.getPhotoPath();
        assertEquals(expResult, result);
    }


    /**
     * Test of getUsername method, of class Employee.
     */
    @Test
    public void testGetSetUsername() {
        System.out.println("getsetUsername");
        Employee instance = new Employee();
        String expResult = "Chris";
        instance.setUsername(expResult);
        String result = instance.getUsername();
        assertEquals(expResult, result);
    }


    /**
     * Test of getPassword method, of class Employee.
     */
    @Test
    public void testGetSetPassword() {
        System.out.println("getsetPassword");
        Employee instance = new Employee();
        String expResult = "22622626asf";
        instance.setPassword(expResult);
        String result = instance.getPassword();
        assertEquals(expResult, result);
    }

    /**
     * Test of getMaritalStatus method, of class Employee.
     */
    @Test
    public void testGetSetMaritalStatus() {
        System.out.println("getsetMaritalStatus");
        Employee instance = new Employee();
        Character expResult = 'a';
        instance.setMaritalStatus(expResult);
        Character result = instance.getMaritalStatus();
        assertEquals(expResult, result);
    }

    /**
     * Test of getNumberOfChildren method, of class Employee.
     */
    @Test
    public void testGetSetNumberOfChildren() {
        System.out.println("getsetNumberOfChildren");
        Employee instance = new Employee();
        Short expResult = 3;
        instance.setNumberOfChildren(expResult);
        Short result = instance.getNumberOfChildren();
        assertEquals(expResult, result);
    }

    /**
     * Test of getRole method, of class Employee.
     */
    @Test
    public void testGetSetRole() {
        System.out.println("getsetRole");
        Employee instance = new Employee();
        String expResult = "manager";
        instance.setRole(expResult);
        String result = instance.getRole();
        assertEquals(expResult, result);
    }

    /**
     * Test of getDepartmentCollection method, of class Employee.
     */
    @Test
    public void testGetSetDepartmentCollection() {
        System.out.println("getsetDepartmentCollection");
        Employee instance = new Employee();
        Department x1 = new Department(1);
        Department x2 = new Department(2);
        Collection<Department> expResult = new HashSet();
        expResult.add(x1);
        expResult.add(x2);
        instance.setDepartmentCollection(expResult);
        Collection<Department> result = instance.getDepartmentCollection();
        assertEquals(expResult, result);
    }

    /**
     * Test of getStudyRecordCollection method, of class Employee.
     */
    @Test
    public void testGetSetStudyRecordCollection() {
        System.out.println("getsetStudyRecordCollection");
        Employee instance = new Employee();
        StudyRecord x1 = new StudyRecord(1);
        StudyRecord x2 = new StudyRecord(2);
        Collection<StudyRecord> expResult = new HashSet();
        expResult.add(x1);
        expResult.add(x2);
        instance.setStudyRecordCollection(expResult);
        Collection<StudyRecord> result = instance.getStudyRecordCollection();
        assertEquals(expResult, result);
    }


    /**
     * Test of getProfessionalExperienceRecordCollection method, of class Employee.
     */
    @Test
    public void testGetsetProfessionalExperienceRecordCollection() {
        System.out.println("getsetProfessionalExperienceRecordCollection");
        Employee instance = new Employee();
        ProfessionalExperienceRecord x1 = new ProfessionalExperienceRecord(1);
        ProfessionalExperienceRecord x2 = new ProfessionalExperienceRecord(1);
        Collection<ProfessionalExperienceRecord> expResult = new HashSet();
        expResult.add(x1);
        expResult.add(x2);
        instance.setProfessionalExperienceRecordCollection(expResult);
        Collection<ProfessionalExperienceRecord> result = instance.getProfessionalExperienceRecordCollection();
        assertEquals(expResult, result);
    }

    /**
     * Test of getDivisionCollection method, of class Employee.
     */
    @Test
    public void testGetSetDivisionCollection() {
        System.out.println("getsetDivisionCollection");
        Employee instance = new Employee();
        Division x1 = new Division(1);
        Division x2 = new Division(2);
        Collection<Division> expResult = new HashSet();
        expResult.add(x1);
        expResult.add(x2);
        instance.setDivisionCollection(expResult);
        Collection<Division> result = instance.getDivisionCollection();
        assertEquals(expResult, result);
    }

    /**
     * Test of getCurrentCompetenceAssessmentCollection method, of class Employee.
     */
    @Test
    public void testGetSetCurrentCompetenceAssessmentCollection() {
        System.out.println("getsetCurrentCompetenceAssessmentCollection");
        Employee instance = new Employee();
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
     * Test of getCurrentInCompanyEmploymentId method, of class Employee.
     */
    @Test
    public void testGetSetCurrentInCompanyEmploymentId() {
        System.out.println("getsetCurrentInCompanyEmploymentId");
        Employee instance = new Employee();
        InCompanyEmployment expResult = new InCompanyEmployment(1);
        instance.setCurrentInCompanyEmploymentId(expResult);
        InCompanyEmployment result = instance.getCurrentInCompanyEmploymentId();
        assertEquals(expResult, result);
    }

    /**
     * Test of getDepartmentIddepartment method, of class Employee.
     */
    @Test
    public void testGetSetDepartmentIddepartment() {
        System.out.println("getsetDepartmentIddepartment");
        Employee instance = new Employee();
        Department expResult = new Department(1);
        instance.setDepartmentIddepartment(expResult);
        Department result = instance.getDepartmentIddepartment();
        assertEquals(expResult, result);

    }


    /**
     * Test of getEdrCollection method, of class Employee.
     */
    @Test
    public void testGetSetEdrCollection() {
        System.out.println("getsetEdrCollection");
        Employee instance = new Employee();
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
     * Test of getEdrCollection1 method, of class Employee.
     */
    @Test
    public void testGetSetEdrCollection1() {
        System.out.println("getsetEdrCollection1");
        Employee instance = new Employee();
        Edr x1 = new Edr(1);
        Edr x2 = new Edr(2);
        Collection<Edr> expResult = new HashSet();
        expResult.add(x1);
        expResult.add(x2);
        instance.setEdrCollection1(expResult);
        Collection<Edr> result = instance.getEdrCollection1();
        assertEquals(expResult, result);
    }

    /**
     * Test of getEmployeeCompetenceAssessmentCollection method, of class Employee.
     */
    @Test
    public void testGetSetEmployeeCompetenceAssessmentCollection() {
        System.out.println("getsetEmployeeCompetenceAssessmentCollection");
        Employee instance = new Employee();
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
     * Test of getJobCollection method, of class Employee.
     */
    @Test
    public void testGetSetJobCollection() {
        System.out.println("getsetJobCollection");
        Employee instance = new Employee();
        Job x1 = new Job(1);
        Job x2 = new Job(2);
        Collection<Job> expResult = new HashSet();
        expResult.add(x1);
        expResult.add(x2);
        instance.setJobCollection(expResult);
        Collection<Job> result = instance.getJobCollection();
        assertEquals(expResult, result);
    }

    /**
     * Test of getInCompanyEmploymentCollection method, of class Employee.
     */
    @Test
    public void testGetSetInCompanyEmploymentCollection() {
        System.out.println("getsetInCompanyEmploymentCollection");
        Employee instance = new Employee();
        InCompanyEmployment x1 = new  InCompanyEmployment(1);
        InCompanyEmployment x2 = new  InCompanyEmployment(2);
        Collection<InCompanyEmployment> expResult = new HashSet();
        expResult.add(x1);
        expResult.add(x2);
        instance.setInCompanyEmploymentCollection(expResult);
        Collection<InCompanyEmployment> result = instance.getInCompanyEmploymentCollection();
        assertEquals(expResult, result);
    }


    /**
     * Test of getAssessmentCollection method, of class Employee.
     */
    @Test
    public void testGetSetAssessmentCollection() {
        System.out.println("getsetAssessmentCollection");
        Employee instance = new Employee();
        Assessment x1 = new Assessment(1);
        Assessment x2 = new Assessment(2);
        Collection<Assessment> expResult = new HashSet();
        expResult.add(x1);
        expResult.add(x2);
        instance.setAssessmentCollection(expResult);
        Collection<Assessment> result = instance.getAssessmentCollection();
        assertEquals(expResult, result);
    }


    /**
     * Test of getAssessmentCollection1 method, of class Employee.
     */
    @Test
    public void testGetSetAssessmentCollection1() {
        System.out.println("getsetAssessmentCollection1");
        Employee instance = new Employee();
        Assessment x1 = new Assessment(1);
        Assessment x2 = new Assessment(2);
        Collection<Assessment> expResult = new HashSet();
        expResult.add(x1);
        expResult.add(x2);
        instance.setAssessmentCollection1(expResult);
        Collection<Assessment> result = instance.getAssessmentCollection1();
        assertEquals(expResult, result);

    }

    /**
     * Test of getAssessmentCollection2 method, of class Employee.
     */
    @Test
    public void testGetSetAssessmentCollection2() {
        System.out.println("getsetAssessmentCollection2");
        Employee instance = new Employee();
        Assessment x1 = new Assessment(1);
        Assessment x2 = new Assessment(2);
        Collection<Assessment> expResult = new HashSet();
        expResult.add(x1);
        expResult.add(x2);
        instance.setAssessmentCollection2(expResult);
        Collection<Assessment> result = instance.getAssessmentCollection2();
        assertEquals(expResult, result);
    }


    /**
     * Test of getAssessmentCollection3 method, of class Employee.
     */
    @Test
    public void testGetAssessmentSetCollection3() {
        System.out.println("getsetAssessmentCollection3");
        Employee instance = new Employee();
        Assessment x1 = new Assessment(1);
        Assessment x2 = new Assessment(2);
        Collection<Assessment> expResult = new HashSet();
        expResult.add(x1);
        expResult.add(x2);
        instance.setAssessmentCollection3(expResult);
        Collection<Assessment> result = instance.getAssessmentCollection3();
        assertEquals(expResult, result);
    }

    /**
     * Test of getAssessmentCollection4 method, of class Employee.
     */
    @Test
    public void testGetSetAssessmentCollection4() {
        System.out.println("getsetAssessmentCollection4");
        Employee instance = new Employee();
        Assessment x1 = new Assessment(1);
        Assessment x2 = new Assessment(2);
        Collection<Assessment> expResult = new HashSet();
        expResult.add(x1);
        expResult.add(x2);
        instance.setAssessmentCollection4(expResult);
        Collection<Assessment> result = instance.getAssessmentCollection4();
        assertEquals(expResult, result);
    }

    /**
     * Test of getIsActive method, of class Employee.
     */
    @Test
    public void testGetSetIsActive() {
        System.out.println("getsetIsActive");
        Employee instance = new Employee();
        Boolean expResult = true;
        instance.setIsActive(expResult);
        Boolean result = instance.getIsActive();
        assertEquals(expResult, result);
    }

    /**
     * Test of hashCode method, of class Employee.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        Employee instance = new Employee(1);
        int expResult = instance.getIdemployee();
        int result = instance.hashCode();
        assertEquals(expResult, result);
    }
    @Test
    public void testHashCode1() {
        System.out.println("hash1Code");
        Employee instance = new Employee();
        int expResult = 0;
        int result = instance.hashCode();
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Employee.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object object = null;
        Employee instance = new Employee();
        boolean expResult = false;
        boolean result = instance.equals(object);
        assertEquals(expResult, result);
    }
    @Test
    public void testEquals1() {
        System.out.println("equals1");
        Employee instance = new Employee(1);
        Employee object = new Employee(1);
        boolean expResult = true;
        boolean result = instance.equals(object);
        assertEquals(expResult, result);
    }
    @Test
    public void testEquals2() {
        System.out.println("equals2");
        Employee instance = new Employee();
        Employee object = new Employee(1);
        boolean expResult = false;
        boolean result = instance.equals(object);
        assertEquals(expResult, result);
    }
    @Test
    public void testEquals3() {
        System.out.println("equals3");
        Employee instance = new Employee(1);
        Employee object = new Employee();
        boolean expResult = false;
        boolean result = instance.equals(object);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of toString method, of class Employee.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Integer idResult = 1;
        Employee instance = new Employee(idResult);
        String expResult = "com.mycompany.mavenproject1.Employee[ idemployee=" + idResult + " ]";
        String result = instance.toString();
        assertEquals(expResult, result);
    }
    
}
