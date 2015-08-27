/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.comprofits.entities.edr;

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
public class ImportHistoryTest {
    
    public ImportHistoryTest() {
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
     * Test of getId method, of class ImportHistory.
     */
    @Test
    public void testGetSetId() {
        System.out.println("getsetId");
        ImportHistory instance = new ImportHistory();
        Long expResult = 1231231231L;
        instance.setId(expResult);
        Long result = instance.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of getIdImportHistory method, of class ImportHistory.
     */
    @Test
    public void testGetSetIdImportHistory() {
        System.out.println("getsetIdImportHistory");
        ImportHistory instance = new ImportHistory();
        Integer expResult = 1;
        instance.setIdImportHistory(expResult);
        Integer result = instance.getIdImportHistory();
        assertEquals(expResult, result);
    }

    /**
     * Test of getFile method, of class ImportHistory.
     */
    @Test
    public void testGetSetFile() {
        System.out.println("getsetFile");
        ImportHistory instance = new ImportHistory();
        String expResult = "file.";
        instance.setFile(expResult);
        String result = instance.getFile();
        assertEquals(expResult, result);
    }

    /**
     * Test of getDate method, of class ImportHistory.
     */
    @Test
    public void testGetSetDate() {
        System.out.println("getsetDate");
        ImportHistory instance = new ImportHistory();
        Date expResult = new Date(93,11,11);
        instance.setDate(expResult);
        Date result = instance.getDate();
        assertEquals(expResult, result);
    }

    /**
     * Test of getTimestamp method, of class ImportHistory.
     */
    @Test
    public void testGetSetTimestamp() {
        System.out.println("getsetTimestamp");
        ImportHistory instance = new ImportHistory();
        Timestamp expResult = new Timestamp(93,11,11,0,1,1,0);
        instance.setTimestamp(expResult);
        Timestamp result = instance.getTimestamp();
        assertEquals(expResult, result);
    }

    /**
     * Test of getIdEmployee method, of class ImportHistory.
     */
    @Test
    public void testGetSetIdEmployee() {
        System.out.println("getsetIdEmployee");
        ImportHistory instance = new ImportHistory();
        Integer expResult = 1;
        instance.setIdEmployee(expResult);
        Integer result = instance.getIdEmployee();
        assertEquals(expResult, result);
    }

    /**
     * Test of getIdEdr method, of class ImportHistory.
     */
    @Test
    public void testGetSetIdEdr() {
        System.out.println("getsetIdEdr");
        ImportHistory instance = new ImportHistory();
        Edr expResult = new Edr(1);
        instance.setIdEdr(expResult);
        Edr result = instance.getIdEdr();
        assertEquals(expResult, result);
    }

    /**
     * Test of getComment method, of class ImportHistory.
     */
    @Test
    public void testGetSetComment() {
        System.out.println("getsetComment");
        ImportHistory instance = new ImportHistory();
        String expResult = "comment";
        instance.setComment(expResult);
        String result = instance.getComment();
        assertEquals(expResult, result);
    }

    /**
     * Test of hashCode method, of class ImportHistory.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        ImportHistory instance = new ImportHistory();
        int expResult = 0;
        int result = instance.hashCode();
        assertEquals(expResult, result);
    }
    @Test
    public void testHashCode1() {
        System.out.println("hashCode1");
        ImportHistory instance = new ImportHistory();
        instance.setId(12312312L);
        long expResult = 12312312L;
        int result = instance.hashCode();
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class ImportHistory.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object object = null;
        ImportHistory instance = new ImportHistory();
        boolean expResult = false;
        boolean result = instance.equals(object);
        assertEquals(expResult, result);
    }
    @Test
    public void testEquals1() {
        System.out.println("equals1");
        ImportHistory object = new ImportHistory();
        object.setId(1231212312L);
        ImportHistory instance = new ImportHistory();
        boolean expResult = false;
        boolean result = instance.equals(object);
        assertEquals(expResult, result);
    }
    @Test
    public void testEquals2() {
        System.out.println("equals2");
        ImportHistory object = new ImportHistory();
        ImportHistory instance = new ImportHistory();
        instance.setId(1231212312L);
        boolean expResult = false;
        boolean result = instance.equals(object);
        assertEquals(expResult, result);
    }
    @Test
    public void testEquals3() {
        System.out.println("equals3");
        ImportHistory object = new ImportHistory();
        object.setId(1231212312L);
        ImportHistory instance = new ImportHistory();
        instance.setId(1231212312L);
        boolean expResult = true;
        boolean result = instance.equals(object);
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class ImportHistory.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        ImportHistory instance = new ImportHistory();
        Long Result = 123123123L;
        instance.setId(Result);
        String expResult = "eu.comprofits.entities.edr.ImportHistory[ id=" + Result + " ]";
        String result = instance.toString();
        assertEquals(expResult, result);
    }

    /**
     * Test of toFormattedString method, of class ImportHistory.
     */
    @Test
    public void testToFormattedString() {
        System.out.println("toFormattedString");
        ImportHistory instance = new ImportHistory();
        instance.setIdImportHistory(1);
        Date date = new Date(93,11,11);
        instance.setDate(date);
        Timestamp timestamp = new Timestamp(93,11,11,0,1,1,0);
        instance.setTimestamp(timestamp);
        instance.setIdEmployee(2);
        Edr edr = new Edr(1);
        instance.setIdEdr(edr);
        instance.setComment("3");
        String expResult = "ImportHistory{" + "idImportHistory=1, \ndate=" + date + ", \ntimestamp=" + timestamp + ", \nidemployee=2, \nidedr=1, \ncomment=3}";
        String result = instance.toFormattedString();
        assertEquals(expResult, result);
    }
    
}
