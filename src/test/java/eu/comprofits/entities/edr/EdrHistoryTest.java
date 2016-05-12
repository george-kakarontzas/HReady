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
package eu.comprofits.entities.edr;

import eu.comprofits.entities.employee.Employee;
import java.sql.Date;
import java.sql.Timestamp;
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
public class EdrHistoryTest {
    
    public EdrHistoryTest() {
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
     * Test of getNote method, of class EdrHistory.
     */
    @Test
    public void testGetSetNote() {
        System.out.println("getsetNote");
        EdrHistory instance = new EdrHistory();
        String expResult = "note";
        instance.setNote(expResult);
        String result = instance.getNote();
        assertEquals(expResult, result);
    }


    /**
     * Test of getDate method, of class EdrHistory.
     */
    @Test
    public void testGetSetDate() {
        System.out.println("getsetDate");
        EdrHistory instance = new EdrHistory();
        Date expResult = new Date(93,11,11);
        instance.setDate(expResult);
        Date result = instance.getDate();
        assertEquals(expResult, result);

    }

    /**
     * Test of getTimestamp method, of class EdrHistory.
     */
    @Test
    public void testGetSetTimestamp() {
        System.out.println("getsetTimestamp");
        EdrHistory instance = new EdrHistory();
        Timestamp expResult = new Timestamp(93,10,11,0,1,1,1);
        instance.setTimestamp(expResult);
        Timestamp result = instance.getTimestamp();
        assertEquals(expResult, result);
    }

    /**
     * Test of getIdemployee method, of class EdrHistory.
     */
    @Test
    public void testGetSetIdemployee() {
        System.out.println("getsetIdemployee");
        EdrHistory instance = new EdrHistory();
        Employee expResult = new Employee(1);
        instance.setIdemployee(expResult);
        Employee result = instance.getIdemployee();
        assertEquals(expResult, result);
    }


    /**
     * Test of getIdedr method, of class EdrHistory.
     */
    @Test
    public void testGetSetIdedr() {
        System.out.println("getsetIdedr");
        EdrHistory instance = new EdrHistory();
        Edr expResult = new Edr(1);
        instance.setIdedr(expResult);
        Edr result = instance.getIdedr();
        assertEquals(expResult, result);
    }

    /**
     * Test of getIdedrHistory method, of class EdrHistory.
     */
    @Test
    public void testGetSetIdedrHistory() {
        System.out.println("getsetIdedrHistory");
        EdrHistory instance = new EdrHistory();
        Integer expResult = 1;
        instance.setIdedrHistory(expResult);
        Integer result = instance.getIdedrHistory();
        assertEquals(expResult, result);
    }


    /**
     * Test of hashCode method, of class EdrHistory.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        EdrHistory instance = new EdrHistory();
        int expResult = 0;
        int result = instance.hashCode();
        assertEquals(expResult, result);
    }
    @Test
    public void testHashCode1() {
        System.out.println("hashCode1");
        EdrHistory instance = new EdrHistory();
        instance.setIdedrHistory(1);
        int expResult = 1;
        int result = instance.hashCode();
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class EdrHistory.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object object = null;
        EdrHistory instance = new EdrHistory();
        boolean expResult = false;
        boolean result = instance.equals(object);
        assertEquals(expResult, result);
    }
    @Test
    public void testEquals1() {
        System.out.println("equals1");
        EdrHistory object = new EdrHistory();
        object.setIdedrHistory(1);
        EdrHistory instance = new EdrHistory();
        boolean expResult = false;
        boolean result = instance.equals(object);
        assertEquals(expResult, result);
    }
    @Test
    public void testEquals2() {
        System.out.println("equals2");
        EdrHistory object = new EdrHistory();
        EdrHistory instance = new EdrHistory();
        instance.setIdedrHistory(1);
        boolean expResult = false;
        boolean result = instance.equals(object);
        assertEquals(expResult, result);
    }
    @Test
    public void testEquals3() {
        System.out.println("equals3");
        EdrHistory object = new EdrHistory();
        EdrHistory instance = new EdrHistory();
        object.setIdedrHistory(1);
        instance.setIdedrHistory(1);
        boolean expResult = true;
        boolean result = instance.equals(object);
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class EdrHistory.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        int idResult = 1;
        EdrHistory instance = new EdrHistory();
        instance.setIdedrHistory(idResult);
        String expResult = "eu.comprofits.entities.edr.EdrHistory[ idedrHistory=" + idResult + " ]";
        String result = instance.toString();
        assertEquals(expResult, result);
    }
    
}
