/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.comprofits.cdibeans;

import java.util.List;
import javax.faces.model.SelectItem;
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
public class MultiSelectViewTest {
    
    public MultiSelectViewTest() {
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
     * Test of init method, of class MultiSelectView.
     */
    @Test
    public void testInit() {
        System.out.println("init");
        MultiSelectView instance = new MultiSelectView();
        instance.init();
        List<SelectItem> result = instance.getCategories();
        assertEquals("error",result.size(),4);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of getCategories method, of class MultiSelectView.
     */
    @Test
    public void testGetCategories() {
        System.out.println("getCategories");
        MultiSelectView instance = new MultiSelectView();
        List<SelectItem> expResult = null;
        List<SelectItem> result = instance.getCategories();
        assertEquals("error",expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of getSelection method, of class MultiSelectView.
     */
    @Test
    public void testGetSelection() {
        System.out.println("getSelection");
        MultiSelectView instance = new MultiSelectView();
        instance.setSelection("se");
        String expResult = "se";
        String result = instance.getSelection();
        assertEquals("error",expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of setSelection method, of class MultiSelectView.
     */
    @Test
    public void testSetSelection() {
        System.out.println("setSelection");
        String selection = "se";
        MultiSelectView instance = new MultiSelectView();
        instance.setSelection(selection);
        assertTrue("error",instance.getSelection()=="se");
        // TODO review the generated test code and remove the default call to fail.
    }
    
}
