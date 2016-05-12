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
package eu.comprofits.entities.jobapplicant;

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
public class JobApplicantTest {
    
    public JobApplicantTest() {
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
     * Test of getIdjobApplicant method, of class JobApplicant.
     */
    @Test
    public void testGetSetIdjobApplicant() {
        System.out.println("getIdjobApplicant");
        JobApplicant instance = new JobApplicant();
        Integer expResult = 1;
        instance.setIdjobApplicant(expResult);
        Integer result = instance.getIdjobApplicant();
        assertEquals(expResult, result);
    }


    /**
     * Test of getDateOfBirth method, of class JobApplicant.
     */
    @Test
    public void testGetSetDateOfBirth() {
        System.out.println("getsetDateOfBirth");
        JobApplicant instance = new JobApplicant();
        Date expResult = new Date();
        instance.setDateOfBirth(expResult);
        Date result = instance.getDateOfBirth();
        assertEquals(expResult, result);
    }


    /**
     * Test of getFirstName method, of class JobApplicant.
     */
    @Test
    public void testGetSetFirstName() {
        System.out.println("getsetFirstName");
        JobApplicant instance = new JobApplicant();
        String expResult = "Chris";
        instance.setFirstName(expResult);
        String result = instance.getFirstName();
        assertEquals(expResult, result);
    }


    /**
     * Test of getLastName method, of class JobApplicant.
     */
    @Test
    public void testGetSetLastName() {
        System.out.println("getSetLastName");
        JobApplicant instance = new JobApplicant();
        String expResult = "Papantonis";
        instance.setLastName(expResult);
        String result = instance.getLastName();
        assertEquals(expResult, result);
    }


    /**
     * Test of getGender method, of class JobApplicant.
     */
    @Test
    public void testGetSetGender() {
        System.out.println("getsetGender");
        JobApplicant instance = new JobApplicant();
        Integer expResult = 1;
        instance.setGender(expResult);
        Integer result = instance.getGender();
        assertEquals(expResult, result);
    }


    /**
     * Test of getAddress method, of class JobApplicant.
     */
    @Test
    public void testGetSetAddress() {
        System.out.println("getsetAddress");
        JobApplicant instance = new JobApplicant();
        String expResult = "Nials";
        instance.setAddress(expResult);
        String result = instance.getAddress();
        assertEquals(expResult, result);
    }


    /**
     * Test of getPostalCode method, of class JobApplicant.
     */
    @Test
    public void testGetSetPostalCode() {
        System.out.println("getsetPostalCode");
        JobApplicant instance = new JobApplicant();
        String expResult = "43100";
        instance.setPostalCode(expResult);
        String result = instance.getPostalCode();
        assertEquals(expResult, result);
    }

    /**
     * Test of getCity method, of class JobApplicant.
     */
    @Test
    public void testGetSetCity() {
        System.out.println("getsetCity");
        JobApplicant instance = new JobApplicant();
        String expResult = "Karditsa";
        instance.setCity(expResult);
        String result = instance.getCity();
        assertEquals(expResult, result);
    }

    /**
     * Test of getCountry method, of class JobApplicant.
     */
    @Test
    public void testGetSetCountry() {
        System.out.println("getsetCountry");
        JobApplicant instance = new JobApplicant();
        String expResult = "Greece";
        instance.setCountry(expResult);
        String result = instance.getCountry();
        assertEquals(expResult, result);
    }


    /**
     * Test of getProvince method, of class JobApplicant.
     */
    @Test
    public void testGetSetProvince() {
        System.out.println("getsetProvince");
        JobApplicant instance = new JobApplicant();
        String expResult = "province";
        instance.setProvince(expResult);
        String result = instance.getProvince();
        assertEquals(expResult, result);
    }


    /**
     * Test of getPhonePrivate method, of class JobApplicant.
     */
    @Test
    public void testGetSetPhonePrivate() {
        System.out.println("getsetPhonePrivate");
        JobApplicant instance = new JobApplicant();
        String expResult = "6989";
        instance.setPhonePrivate(expResult);
        String result = instance.getPhonePrivate();
        assertEquals(expResult, result);
    }


    /**
     * Test of getPhoneMobile method, of class JobApplicant.
     */
    @Test
    public void testGetSetPhoneMobile() {
        System.out.println("getsetPhoneMobile");
        JobApplicant instance = new JobApplicant();
        String expResult = "6989";
        instance.setPhoneMobile(expResult);
        String result = instance.getPhoneMobile();
        assertEquals(expResult, result);
    }

    /**
     * Test of getEmail method, of class JobApplicant.
     */
    @Test
    public void testGetSetEmail() {
        System.out.println("getsetEmail");
        JobApplicant instance = new JobApplicant();
        String expResult = "papantonisc@hotmail.com";
        instance.setEmail(expResult);
        String result = instance.getEmail();
        assertEquals(expResult, result);
    }


    /**
     * Test of getPhotoPath method, of class JobApplicant.
     */
    @Test
    public void testGetSetPhotoPath() {
        System.out.println("getSetPhotoPath");
        JobApplicant instance = new JobApplicant();
        String expResult = "users.jpg";
        instance.setPhotoPath(expResult);
        String result = instance.getPhotoPath();
        assertEquals(expResult, result);
    }


    /**
     * Test of getUsername method, of class JobApplicant.
     */
    @Test
    public void testGetSetUsername() {
        System.out.println("getsetUsername");
        JobApplicant instance = new JobApplicant();
        String expResult = "chris";
        instance.setUsername(expResult);
        String result = instance.getUsername();
        assertEquals(expResult, result);
    }


    /**
     * Test of getPassword method, of class JobApplicant.
     */
    @Test
    public void testGetSetPassword() {
        System.out.println("getPassword");
        JobApplicant instance = new JobApplicant();
        String expResult = "pass";
        instance.setPassword(expResult);
        String result = instance.getPassword();
        assertEquals(expResult, result);
    }

    /**
     * Test of getMaritalStatus method, of class JobApplicant.
     */
    @Test
    public void testGetSetMaritalStatus() {
        System.out.println("getsetMaritalStatus");
        JobApplicant instance = new JobApplicant();
        Character expResult = 'c';
        instance.setMaritalStatus(expResult);
        Character result = instance.getMaritalStatus();
        assertEquals(expResult, result);
    }

    /**
     * Test of getNumberOfChildren method, of class JobApplicant.
     */
    @Test
    public void testGetSetNumberOfChildren() {
        System.out.println("getsetNumberOfChildren");
        JobApplicant instance = new JobApplicant();
        Short expResult = 3;
        instance.setNumberOfChildren(expResult);
        Short result = instance.getNumberOfChildren();
        assertEquals(expResult, result);
    }


    /**
     * Test of getJobApplicationCollection method, of class JobApplicant.
     */
    @Test
    public void testGetSetJobApplicationCollection() {
        System.out.println("getsetJobApplicationCollection");
        JobApplicant instance = new JobApplicant();
        JobApplication x1 = new JobApplication(1);
        JobApplication x2 = new JobApplication(2);
        Collection<JobApplication> expResult = new HashSet();
        expResult.add(x1);
        expResult.add(x2);
        instance.setJobApplicationCollection(expResult);
        Collection<JobApplication> result = instance.getJobApplicationCollection();
        assertEquals(expResult, result);
    }


    /**
     * Test of getApplicantProfessionalExperienceRecordCollection method, of class JobApplicant.
     */
    @Test
    public void testGetSetApplicantProfessionalExperienceRecordCollection() {
        System.out.println("getsetApplicantProfessionalExperienceRecordCollection");
        JobApplicant instance = new JobApplicant();
        ApplicantProfessionalExperienceRecord x1 = new ApplicantProfessionalExperienceRecord(1);
        ApplicantProfessionalExperienceRecord x2 = new ApplicantProfessionalExperienceRecord(2);
        Collection<ApplicantProfessionalExperienceRecord> expResult = new HashSet();
        expResult.add(x1);
        expResult.add(x2);
        instance.setApplicantProfessionalExperienceRecordCollection(expResult);
        Collection<ApplicantProfessionalExperienceRecord> result = instance.getApplicantProfessionalExperienceRecordCollection();
        assertEquals(expResult, result);
    }


    /**
     * Test of getPresentIdapplicantProfessionalExperienceRecord method, of class JobApplicant.
     */
    @Test
    public void testGetPresentIdapplicantProfessionalExperienceRecord() {
        System.out.println("getPresentIdapplicantProfessionalExperienceRecord");
        JobApplicant instance = new JobApplicant();
        ApplicantProfessionalExperienceRecord expResult = new ApplicantProfessionalExperienceRecord(1);
        instance.setPresentIdapplicantProfessionalExperienceRecord(expResult);
        ApplicantProfessionalExperienceRecord result = instance.getPresentIdapplicantProfessionalExperienceRecord();
        assertEquals(expResult, result);
    }

    /**
     * Test of getApplicantStudyRecordCollection method, of class JobApplicant.
     */
    @Test
    public void testGetSetApplicantStudyRecordCollection() {
        System.out.println("getsetApplicantStudyRecordCollection");
        JobApplicant instance = new JobApplicant();
        ApplicantStudyRecord x1 = new ApplicantStudyRecord(1);
        ApplicantStudyRecord x2 = new ApplicantStudyRecord(2);
        Collection<ApplicantStudyRecord> expResult = new HashSet();
        expResult.add(x1);
        expResult.add(x2);
        instance.setApplicantStudyRecordCollection(expResult);
        Collection<ApplicantStudyRecord> result = instance.getApplicantStudyRecordCollection();
        assertEquals(expResult, result);
    }

    /**
     * Test of hashCode method, of class JobApplicant.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        JobApplicant instance = new JobApplicant();
        int expResult = 0;
        int result = instance.hashCode();
        assertEquals(expResult, result);
    }
    @Test
    public void testHashCode1() {
        System.out.println("hashCode");
        JobApplicant instance = new JobApplicant(1);
        int expResult = 1;
        int result = instance.hashCode();
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class JobApplicant.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object object = null;
        JobApplicant instance = new JobApplicant();
        boolean expResult = false;
        boolean result = instance.equals(object);
        assertEquals(expResult, result);
    }
    @Test
    public void testEquals1() {
        System.out.println("equals1");
        JobApplicant object = new JobApplicant();
        JobApplicant instance = new JobApplicant(1);
        boolean expResult = false;
        boolean result = instance.equals(object);
        assertEquals(expResult, result);
    }
    @Test
    public void testEquals2() {
        System.out.println("equals2");
        JobApplicant object = new JobApplicant(1);
        JobApplicant instance = new JobApplicant();
        boolean expResult = false;
        boolean result = instance.equals(object);
        assertEquals(expResult, result);
    }
    @Test
    public void testEquals3() {
        System.out.println("equals3");
        JobApplicant object = new JobApplicant(1);
        JobApplicant instance = new JobApplicant(1);
        boolean expResult = true;
        boolean result = instance.equals(object);
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class JobApplicant.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Integer idResult = 1;
        JobApplicant instance = new JobApplicant(idResult);
        String expResult = "com.mycompany.mavenproject1.JobApplicant[ idjobApplicant=" + idResult + " ]";
        String result = instance.toString();
        assertEquals(expResult, result);
    }

    /**
     * Test of getFullName method, of class JobApplicant.
     */
    @Test
    public void testGetFullName() {
        System.out.println("getFullName");
        JobApplicant instance = new JobApplicant();
        String Fname = "Chris";
        String Lname = "Papantonis";
        instance.setFirstName(Fname);
        instance.setLastName(Lname);
        String expResult = Fname + " " + Lname;
        String result = instance.getFullName();
        assertEquals(expResult, result);
    }
    
}
