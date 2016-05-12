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
package eu.comprofits.entities.main;

import eu.comprofits.entities.jobprofile.Job;
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
public class OrganisationalPositionTest {
    
    public OrganisationalPositionTest() {
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
     * Test of OrganisationalPosition constructor, of class Competence.
     */
    @Test
    public void testOrganisationalPosition() {
        System.out.println("OrganisationalPosition");
        Integer idResult = 1;
        OrganisationalPosition instance = new OrganisationalPosition(idResult,"posname","desc");        
        Integer result = instance.getIdorganisationalPosition();
        assertEquals("error id",idResult, result);
        assertEquals("error position name",instance.getOrganisationalPositionName(),"posname");
        assertEquals("error position desc",instance.getOrganisationalPositionDescription(),"desc");
    }


    /**
     * Test of getIdorganisationalPosition method, of class OrganisationalPosition.
     */
    @Test
    public void testGetSetIdorganisationalPosition() {
        System.out.println("getsetIdorganisationalPosition");
        OrganisationalPosition instance = new OrganisationalPosition();
        Integer expResult = 1;
        instance.setIdorganisationalPosition(expResult);
        Integer result = instance.getIdorganisationalPosition();
        assertEquals(expResult, result);
    }

    /**
     * Test of getOrganisationalPositionName method, of class OrganisationalPosition.
     */
    @Test
    public void testGetSetOrganisationalPositionName() {
        System.out.println("getsetOrganisationalPositionName");
        OrganisationalPosition instance = new OrganisationalPosition();
        String expResult = "name";
        instance.setOrganisationalPositionName(expResult);
        String result = instance.getOrganisationalPositionName();
        assertEquals(expResult, result);

    }
    
    /**
     * Test of getOrganisationalPositionDescription method, of class OrganisationalPosition.
     */
    @Test
    public void testGetSetOrganisationalPositionDescription() {
        System.out.println("getsetOrganisationalPositionDescription");
        OrganisationalPosition instance = new OrganisationalPosition();
        String expResult = "desc";
        instance.setOrganisationalPositionDescription(expResult);
        String result = instance.getOrganisationalPositionDescription();
        assertEquals(expResult, result);
    }

    /**
     * Test of getCompanyIdcompany method, of class OrganisationalPosition.
     */
    @Test
    public void testGetSetCompanyIdcompany() {
        System.out.println("getsetCompanyIdcompany");
        OrganisationalPosition instance = new OrganisationalPosition();
        Company expResult = new Company(1);
        instance.setCompanyIdcompany(expResult);
        Company result = instance.getCompanyIdcompany();
        assertEquals(expResult, result);
    }

    /**
     * Test of getJobCollection method, of class OrganisationalPosition.
     */
    @Test
    public void testGetSetJobCollection() {
        System.out.println("getsetJobCollection");
        OrganisationalPosition instance = new OrganisationalPosition();
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
     * Test of hashCode method, of class OrganisationalPosition.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        OrganisationalPosition instance = new OrganisationalPosition();
        int expResult = 0;
        int result = instance.hashCode();
        assertEquals(expResult, result);
    }
    @Test
    public void testHashCode1() {
        System.out.println("hashCode1");
        OrganisationalPosition instance = new OrganisationalPosition(1);
        int expResult = 1;
        int result = instance.hashCode();
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class OrganisationalPosition.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object object = null;
        OrganisationalPosition instance = new OrganisationalPosition();
        boolean expResult = false;
        boolean result = instance.equals(object);
        assertEquals(expResult, result);
    }
    @Test
    public void testEquals1() {
        System.out.println("equals1");
        OrganisationalPosition object = new OrganisationalPosition(1);
        OrganisationalPosition instance = new OrganisationalPosition();
        boolean expResult = false;
        boolean result = instance.equals(object);
        assertEquals(expResult, result);
    }
    @Test
    public void testEquals2() {
        System.out.println("equals2");
        OrganisationalPosition object = new OrganisationalPosition();
        OrganisationalPosition instance = new OrganisationalPosition(1);
        boolean expResult = false;
        boolean result = instance.equals(object);
        assertEquals(expResult, result);
    }
    @Test
    public void testEquals3() {
        System.out.println("equals3");
        OrganisationalPosition object = new OrganisationalPosition(1);
        OrganisationalPosition instance = new OrganisationalPosition(1);
        boolean expResult = true;
        boolean result = instance.equals(object);
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class OrganisationalPosition.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Integer idResult = 1;
        OrganisationalPosition instance = new OrganisationalPosition(idResult);
        String expResult = "com.mycompany.mavenproject1.OrganisationalPosition[ idorganisationalPosition=" + idResult +" ]";
        String result = instance.toString();
        assertEquals(expResult, result);
    }
    
}
