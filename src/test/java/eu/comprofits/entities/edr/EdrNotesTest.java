/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.comprofits.entities.edr;

import java.sql.Date;
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
public class EdrNotesTest {
    
    public EdrNotesTest() {
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
     * Test of getIdnote method, of class EdrNotes.
     */
    @Test
    public void testGesettIdnote() {
        System.out.println("getsetIdnote");
        EdrNotes instance = new EdrNotes();
        Integer expResult = 1;
        instance.setIdnote(expResult);
        Integer result = instance.getIdnote();
        assertEquals(expResult, result);
    }


    /**
     * Test of getEdrIdedr method, of class EdrNotes.
     */
    @Test
    public void testSetGetEdrIdedr() {
        System.out.println("getsetEdrIdedr");
        EdrNotes instance = new EdrNotes();
        Edr expResult = new Edr(1);
        instance.setEdrIdedr(expResult);
        Edr result = instance.getEdrIdedr();
        assertEquals(expResult, result);
    }

    /**
     * Test of getNote method, of class EdrNotes.
     */
    @Test
    public void testGetSetNote() {
        System.out.println("getsetNote");
        EdrNotes instance = new EdrNotes();
        String expResult = "notes";
        instance.setNote(expResult);
        String result = instance.getNote();
        assertEquals(expResult, result);
    }

    /**
     * Test of getDate method, of class EdrNotes.
     */
    @Test
    public void testGetSetDate() {
        System.out.println("getsetDate");
        EdrNotes instance = new EdrNotes();
        Date expResult = new Date(93,11,11);
        instance.setDate(expResult);
        Date result = instance.getDate();
        assertEquals(expResult, result);
    }

    /**
     * Test of hashCode method, of class EdrNotes.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        EdrNotes instance = new EdrNotes();
        int expResult = 0;
        int result = instance.hashCode();
        assertEquals(expResult, result);
    }
    @Test
    public void testHashCode1() {
        System.out.println("hashCode1");
        EdrNotes instance = new EdrNotes();
        instance.setIdnote(1);
        int expResult = 1;
        int result = instance.hashCode();
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class EdrNotes.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object object = null;
        EdrNotes instance = new EdrNotes();
        boolean expResult = false;
        boolean result = instance.equals(object);
        assertEquals(expResult, result);
    }
    @Test
    public void testEquals1() {
        System.out.println("equals1");
        EdrNotes object = new EdrNotes();
        object.setIdnote(1);
        EdrNotes instance = new EdrNotes();
        boolean expResult = false;
        boolean result = instance.equals(object);
        assertEquals(expResult, result);
    }
    @Test
    public void testEquals2() {
        System.out.println("equals2");
        EdrNotes object = new EdrNotes();
        EdrNotes instance = new EdrNotes();
        instance.setIdnote(1);
        boolean expResult = false;
        boolean result = instance.equals(object);
        assertEquals(expResult, result);
    }
    @Test
    public void testEquals3() {
        System.out.println("equals3");
        EdrNotes object = new EdrNotes();
        object.setIdnote(1);
        EdrNotes instance = new EdrNotes();
        instance.setIdnote(1);
        boolean expResult = true;
        boolean result = instance.equals(object);
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class EdrNotes.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        int idResult = 1;
        EdrNotes instance = new EdrNotes();
        instance.setIdnote(idResult);
        String expResult = "eu.comprofits.entities.edr.EdrNotes[ id=" + idResult + " ]";
        String result = instance.toString();
        assertEquals(expResult, result);
    }
    
}
