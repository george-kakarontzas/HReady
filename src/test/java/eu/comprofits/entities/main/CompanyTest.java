/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.comprofits.entities.main;

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
public class CompanyTest {
    
    public CompanyTest() {
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
     * Test of Company constructor, of class Company.
     */
    @Test
    public void testCompany() {
        System.out.println("Company");
        Integer idResult = 1;
        Company instance = new Company(idResult,"comp","address","6989","papa@yahoo.gr","website.gr");
        Integer result = instance.getIdcompany();
        assertEquals("error id",idResult, result);
        assertEquals("error company",instance.getCompanyName1(),"comp");
        assertEquals("error company",instance.getCompanyAddress1(),"address");
        assertEquals("error phone number",instance.getPhoneNumber(),"6989");
        assertEquals("error e-mail",instance.getEMail(),"papa@yahoo.gr");
        assertEquals("error website",instance.getWebsite(),"website.gr");
    }

    /**
     * Test of getIdcompany method, of class Company.
     */
    @Test
    public void testGetSetIdcompany() {
        System.out.println("getsetIdcompany");
        Company instance = new Company();
        Integer expResult = 1;
        instance.setIdcompany(expResult);
        Integer result = instance.getIdcompany();
        assertEquals(expResult, result);
    }


    /**
     * Test of getCompanyName1 method, of class Company.
     */
    @Test
    public void testGetSetCompanyName1() {
        System.out.println("getsetCompanyName1");
        Company instance = new Company();
        String expResult = "comp1";
        instance.setCompanyName1(expResult);
        String result = instance.getCompanyName1();
        assertEquals(expResult, result);
    }

    /**
     * Test of getCompanyName2 method, of class Company.
     */
    @Test
    public void testGetSetCompanyName2() {
        System.out.println("getsetCompanyName2");
        Company instance = new Company();
        String expResult = "comp2";
        instance.setCompanyName2(expResult);
        String result = instance.getCompanyName2();
        assertEquals(expResult, result);
    }

    /**
     * Test of getCompanyAddress1 method, of class Company.
     */
    @Test
    public void testGetSetCompanyAddress1() {
        System.out.println("getsetCompanyAddress1");
        Company instance = new Company();
        String expResult = "address1";
        instance.setCompanyAddress1(expResult);
        String result = instance.getCompanyAddress1();
        assertEquals(expResult, result);
    }

    /**
     * Test of getCompanyAddress2 method, of class Company.
     */
    @Test
    public void testGetSetCompanyAddress2() {
        System.out.println("getsetCompanyAddress2");
        Company instance = new Company();
        String expResult = "address2";
        instance.setCompanyAddress2(expResult);
        String result = instance.getCompanyAddress2();
        assertEquals(expResult, result);
    }

    /**
     * Test of getPostalCode method, of class Company.
     */
    @Test
    public void testGetSetPostalCode() {
        System.out.println("getsetPostalCode");
        Company instance = new Company();
        String expResult = "43100";
        instance.setPostalCode(expResult);
        String result = instance.getPostalCode();
        assertEquals(expResult, result);
    }

    /**
     * Test of getProvince method, of class Company.
     */
    @Test
    public void testGetSetProvince() {
        System.out.println("getsetProvince");
        Company instance = new Company();
        String expResult = "Karditsa";
        instance.setProvince(expResult);
        String result = instance.getProvince();
        assertEquals(expResult, result);
    }

    /**
     * Test of getCountry method, of class Company.
     */
    @Test
    public void testGetSetCountry() {
        System.out.println("getsetCountry");
        Company instance = new Company();
        String expResult = "Greece";
        instance.setCountry(expResult);
        String result = instance.getCountry();
        assertEquals(expResult, result);
    }


    /**
     * Test of getPhoneNumber method, of class Company.
     */
    @Test
    public void testGetSetPhoneNumber() {
        System.out.println("getsetPhoneNumber");
        Company instance = new Company();
        String expResult = "6988";
        instance.setPhoneNumber(expResult);
        String result = instance.getPhoneNumber();
        assertEquals(expResult, result);
    }

    /**
     * Test of getEMail method, of class Company.
     */
    @Test
    public void testGetSetEMail() {
        System.out.println("getsetEMail");
        Company instance = new Company();
        String expResult = "papa@yahoo.gr";
        instance.setEMail(expResult);
        String result = instance.getEMail();
        assertEquals(expResult, result);
    }

    /**
     * Test of getWebsite method, of class Company.
     */
    @Test
    public void testGetSetWebsite() {
        System.out.println("getsetWebsite");
        Company instance = new Company();
        String expResult = "website.gr";
        instance.setWebsite(expResult);
        String result = instance.getWebsite();
        assertEquals(expResult, result);
    }

    /**
     * Test of getDepartmentCollection method, of class Company.
     */
    @Test
    public void testGetSetDepartmentCollection() {
        System.out.println("getsetDepartmentCollection");
        Company instance = new Company();
        Department x1 = new Department(1);
        Department x2 = new Department(2);
        Collection<Department> expResult = new HashSet();
        expResult.add(x1);
        expResult.add(x2);
        instance.setDepartmentCollection(expResult);
        Collection<Department> result = instance.getDepartmentCollection();
        assertEquals(expResult, result);
    }


    /**
     * Test of getOrganisationalPositionCollection method, of class Company.
     */
    @Test
    public void testGetOrganisationalPositionCollection() {
        System.out.println("getOrganisationalPositionCollection");
        Company instance = new Company();
        OrganisationalPosition x1 = new OrganisationalPosition(1);
        OrganisationalPosition x2 = new OrganisationalPosition(2);
        Collection<OrganisationalPosition> expResult = new HashSet();
        expResult.add(x1);
        expResult.add(x2);
        instance.setOrganisationalPositionCollection(expResult);
        Collection<OrganisationalPosition> result = instance.getOrganisationalPositionCollection();
        assertEquals(expResult, result);
    }

    /**
     * Test of hashCode method, of class Company.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        Company instance = new Company();
        int expResult = 0;
        int result = instance.hashCode();
        assertEquals(expResult, result);
    }
    @Test
    public void testHashCode1() {
        System.out.println("hashCode1");
        Company instance = new Company(1);
        int expResult = 1;
        int result = instance.hashCode();
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Company.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object object = null;
        Company instance = new Company();
        boolean expResult = false;
        boolean result = instance.equals(object);
        assertEquals(expResult, result);
    }
    @Test
    public void testEquals1() {
        System.out.println("equals1");
        Company object = new Company(1);
        Company instance = new Company();
        boolean expResult = false;
        boolean result = instance.equals(object);
        assertEquals(expResult, result);
    }
    @Test
    public void testEquals2() {
        System.out.println("equals2");
        Company object = new Company();
        Company instance = new Company(1);
        boolean expResult = false;
        boolean result = instance.equals(object);
        assertEquals(expResult, result);
    }
    @Test
    public void testEquals3() {
        System.out.println("equals3");
        Company object = new Company(1);
        Company instance = new Company(1);
        boolean expResult = true;
        boolean result = instance.equals(object);
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class Company.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Integer idResult = 1;
        Company instance = new Company(idResult);
        String expResult = "com.mycompany.mavenproject1.Company[ idcompany=" + idResult +" ]";
        String result = instance.toString();
        assertEquals(expResult, result);
    }
    
}
