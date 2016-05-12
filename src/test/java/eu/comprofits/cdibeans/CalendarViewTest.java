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
package eu.comprofits.cdibeans;

import java.util.Date;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author Christos
 */
public class CalendarViewTest {
    
    public CalendarViewTest() {
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

//    /**
//     * Test of onDateSelect method, of class CalendarView.
//     */
//    @Test
//    public void testOnDateSelect() {
//        System.out.println("onDateSelect");
//        SelectEvent event = null;
//        CalendarView instance = new CalendarView();
//        instance.onDateSelect(event);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("This test is a prototype");
//    }
//
//    /**
//     * Test of click method, of class CalendarView.
//     */
//    @Test
//    public void testClick() {
//        System.out.println("click");
//        CalendarView instance = new CalendarView();
//        instance.click();
//        fail("This test is a prototype");
//    }

    /**
     * Test of getDate method, of class CalendarView.
     */
    @Test
    public void testGetDate() {
        System.out.println("getDate");
        CalendarView instance = new CalendarView();
        Date expResult = new Date();
        instance.setDate(expResult);
        Date result = instance.getDate();
        assertEquals("error",expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of setDate method, of class CalendarView.
     */
    @Test
    public void testSetDate() {
        System.out.println("setDate");
        Date date = new Date();
        CalendarView instance = new CalendarView();
        instance.setDate(date);
        // TODO review the generated test code and remove the default call to fail.
        assertEquals("error",instance.getDate(),date);
    }
    
}
