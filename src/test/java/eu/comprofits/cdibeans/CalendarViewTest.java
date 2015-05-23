/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
