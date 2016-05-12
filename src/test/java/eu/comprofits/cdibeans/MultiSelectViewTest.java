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
