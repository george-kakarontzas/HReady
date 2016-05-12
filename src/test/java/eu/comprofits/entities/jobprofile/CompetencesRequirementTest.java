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

import eu.comprofits.entities.main.Competence;
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
public class CompetencesRequirementTest {
    
    public CompetencesRequirementTest() {
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
     * Test of CompetencesRequirement constructor, of class CompetencesRequirement.
     */
    @Test
    public void testCompetencesRequirement() {
        System.out.println("getIdcompetencesRequirement");
        Integer idResult = 1;
        CompetencesRequirement instance = new CompetencesRequirement(idResult,1,2);
        Integer result = instance.getIdcompetencesRequirement();
        assertEquals("error idcompetencesRequirement",idResult, result);
        assertEquals("error weight",instance.getWeight(),1);
        assertEquals("error importance",instance.getImportance(),2);
    }

    /**
     * Test of getIdcompetencesRequirement method, of class CompetencesRequirement.
     */
    @Test
    public void testGetSetIdcompetencesRequirement() {
        System.out.println("getsetIdcompetencesRequirement");
        CompetencesRequirement instance = new CompetencesRequirement();
        Integer expResult = 1;
        instance.setIdcompetencesRequirement(expResult);
        Integer result = instance.getIdcompetencesRequirement();
        assertEquals(expResult, result);
    }

    /**
     * Test of getWeight method, of class CompetencesRequirement.
     */
    @Test
    public void testGetSetWeight() {
        System.out.println("getsetWeight");
        CompetencesRequirement instance = new CompetencesRequirement();
        int expResult = 1;
        instance.setWeight(expResult);
        int result = instance.getWeight();
        assertEquals(expResult, result);
    }

    /**
     * Test of getImportance method, of class CompetencesRequirement.
     */
    @Test
    public void testGetSetImportance() {
        System.out.println("getsetImportance");
        CompetencesRequirement instance = new CompetencesRequirement();
        int expResult = 1;
        instance.setImportance(expResult);
        int result = instance.getImportance();
        assertEquals(expResult, result);;
    }

    /**
     * Test of getJobIdjob method, of class CompetencesRequirement.
     */
    @Test
    public void testGetSetJobIdjob() {
        System.out.println("getsetJobIdjob");
        CompetencesRequirement instance = new CompetencesRequirement();
        Job expResult = new Job(1);
        instance.setJobIdjob(expResult);
        Job result = instance.getJobIdjob();
        assertEquals(expResult, result);
    }

    /**
     * Test of getCompetenceIdcompetence method, of class CompetencesRequirement.
     */
    @Test
    public void testGetSetCompetenceIdcompetence() {
        System.out.println("getsetCompetenceIdcompetence");
        CompetencesRequirement instance = new CompetencesRequirement();
        Competence expResult = new Competence(1);
        instance.setCompetenceIdcompetence(expResult);
        Competence result = instance.getCompetenceIdcompetence();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of hashCode method, of class CompetencesRequirement.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        CompetencesRequirement instance = new CompetencesRequirement();
        int expResult = 0;
        int result = instance.hashCode();
        assertEquals(expResult, result);
    }
    @Test
    public void testHashCode1() {
        System.out.println("hashCode1");
        CompetencesRequirement instance = new CompetencesRequirement(1);
        int expResult = 1;
        int result = instance.hashCode();
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class CompetencesRequirement.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object object = null;
        CompetencesRequirement instance = new CompetencesRequirement();
        boolean expResult = false;
        boolean result = instance.equals(object);
        assertEquals(expResult, result);
    }
    @Test
    public void testEquals1() {
        System.out.println("equals1");
        CompetencesRequirement object = new CompetencesRequirement();
        CompetencesRequirement instance = new CompetencesRequirement(1);
        boolean expResult = false;
        boolean result = instance.equals(object);
        assertEquals(expResult, result);
    }
    @Test
    public void testEquals2() {
        System.out.println("equals2");
        CompetencesRequirement object = new CompetencesRequirement(1);
        CompetencesRequirement instance = new CompetencesRequirement();
        boolean expResult = false;
        boolean result = instance.equals(object);
        assertEquals(expResult, result);
    }
    @Test
    public void testEquals3() {
        System.out.println("equals3");
        CompetencesRequirement object = new CompetencesRequirement(1);
        CompetencesRequirement instance = new CompetencesRequirement(1);
        boolean expResult = true;
        boolean result = instance.equals(object);
        assertEquals(expResult, result);
    }
    

    /**
     * Test of toString method, of class CompetencesRequirement.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Integer idResult = 1;
        CompetencesRequirement instance = new CompetencesRequirement(idResult);
        String expResult = "com.mycompany.mavenproject1.CompetencesRequirement[ idcompetencesRequirement=" + idResult + " ]";
        String result = instance.toString();
        assertEquals(expResult, result);
    }

    /**
     * Test of getCompetenceName method, of class CompetencesRequirement.
     */
    @Test
    public void testGetCompetenceName() {
        System.out.println("getCompetenceName");
        CompetencesRequirement instance = new CompetencesRequirement();
        Competence x1 = new Competence(1);
        String expResult = "name";
        x1.setCompetenceName(expResult);
        instance.setCompetenceIdcompetence(x1);
        String result = instance.getCompetenceName();
        assertEquals(expResult, result);
    }

    /**
     * Test of getJobTitle method, of class CompetencesRequirement.
     */
    @Test
    public void testGetJobTitle() {
        System.out.println("getJobTitle");
        CompetencesRequirement instance = new CompetencesRequirement();
        Job x1 = new Job(1);
        String expResult = "tittle";
        x1.setJobTitle(expResult);
        instance.setJobIdjob(x1);
        String result = instance.getJobTitle();
        assertEquals(expResult, result);
    }
    
}
