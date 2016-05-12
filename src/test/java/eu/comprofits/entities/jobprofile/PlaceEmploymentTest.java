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
package eu.comprofits.entities.jobprofile;

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
public class PlaceEmploymentTest {
    
    public PlaceEmploymentTest() {
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
     * Test of getIdplaceEmployment method, of class PlaceEmployment.
     */
    @Test
    public void testGetSetIdplaceEmployment() {
        System.out.println("getsetIdplaceEmployment");
        PlaceEmployment instance = new PlaceEmployment();
        Integer expResult = 1;
        instance.setIdplaceEmployment(expResult);
        Integer result = instance.getIdplaceEmployment();
        assertEquals(expResult, result);
    }

    /**
     * Test of getName method, of class PlaceEmployment.
     */
    @Test
    public void testGetSetName() {
        System.out.println("getsetName");
        PlaceEmployment instance = new PlaceEmployment();
        String expResult = "Chris";
        instance.setName(expResult);
        String result = instance.getName();
        assertEquals(expResult, result);
    }

    /**
     * Test of getAddress method, of class PlaceEmployment.
     */
    @Test
    public void testGetSetAddress() {
        System.out.println("getsetAddress");
        PlaceEmployment instance = new PlaceEmployment();
        String expResult = "Nialas";
        instance.setAddress(expResult);
        String result = instance.getAddress();
        assertEquals(expResult, result);
    }

    /**
     * Test of getPostalCode method, of class PlaceEmployment.
     */
    @Test
    public void testGetSetPostalCode() {
        System.out.println("getsetPostalCode");
        PlaceEmployment instance = new PlaceEmployment();
        String expResult = "43100";
        instance.setPostalCode(expResult);
        String result = instance.getPostalCode();
        assertEquals(expResult, result);
    }

    /**
     * Test of getCity method, of class PlaceEmployment.
     */
    @Test
    public void testGetSetCity() {
        System.out.println("getsetCity");
        PlaceEmployment instance = new PlaceEmployment();
        String expResult = "Karditsa";
        instance.setCity(expResult);
        String result = instance.getCity();
        assertEquals(expResult, result);
    }

    /**
     * Test of getProvince method, of class PlaceEmployment.
     */
    @Test
    public void testGetSetProvince() {
        System.out.println("getsetProvince");
        PlaceEmployment instance = new PlaceEmployment();
        String expResult = "province";
        instance.setProvince(expResult);
        String result = instance.getProvince();
        assertEquals(expResult, result);
    }

    /**
     * Test of getCountry method, of class PlaceEmployment.
     */
    @Test
    public void testGetSetCountry() {
        System.out.println("getsetCountry");
        PlaceEmployment instance = new PlaceEmployment();
        String expResult = "Greece";
        instance.setCountry(expResult);
        String result = instance.getCountry();
        assertEquals(expResult, result);
    }

    /**
     * Test of getJobCollection method, of class PlaceEmployment.
     */
    @Test
    public void testGetSetJobCollection() {
        System.out.println("getsetJobCollection");
        PlaceEmployment instance = new PlaceEmployment();
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
     * Test of hashCode method, of class PlaceEmployment.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        PlaceEmployment instance = new PlaceEmployment();
        int expResult = 0;
        int result = instance.hashCode();
        assertEquals(expResult, result);
    }
    @Test
    public void testHashCode1() {
        System.out.println("hashCode1");
        PlaceEmployment instance = new PlaceEmployment(1);
        int expResult = 1;
        int result = instance.hashCode();
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class PlaceEmployment.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object object = null;
        PlaceEmployment instance = new PlaceEmployment();
        boolean expResult = false;
        boolean result = instance.equals(object);
        assertEquals(expResult, result);
    }
    @Test
    public void testEquals1() {
        System.out.println("equals1");
        PlaceEmployment object = new PlaceEmployment();
        PlaceEmployment instance = new PlaceEmployment(1);
        boolean expResult = false;
        boolean result = instance.equals(object);
        assertEquals(expResult, result);
    }
    @Test
    public void testEquals2() {
        System.out.println("equals2");
        PlaceEmployment object = new PlaceEmployment(1);
        PlaceEmployment instance = new PlaceEmployment();
        boolean expResult = false;
        boolean result = instance.equals(object);
        assertEquals(expResult, result);
    }
    @Test
    public void testEquals3() {
        System.out.println("equals3");
        PlaceEmployment object = new PlaceEmployment(1);
        PlaceEmployment instance = new PlaceEmployment(1);
        boolean expResult = true;
        boolean result = instance.equals(object);
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class PlaceEmployment.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Integer idResult = 1;
        PlaceEmployment instance = new PlaceEmployment(idResult);
        String expResult = "com.mycompany.mavenproject1.PlaceEmployment[ idplaceEmployment=" + idResult + " ]";
        String result = instance.toString();
        assertEquals(expResult, result);
    }
    
}
