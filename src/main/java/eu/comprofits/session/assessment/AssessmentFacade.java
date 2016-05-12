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
package eu.comprofits.session.assessment;

import eu.comprofits.entities.assessment.Assessment;
import eu.comprofits.entities.employee.Employee;
import eu.comprofits.entities.main.Department;
import eu.comprofits.session.AbstractFacade;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author George Kakarontzas
 */
@Stateless
public class AssessmentFacade extends AbstractFacade<Assessment> {

    @PersistenceContext(unitName = "comprofitsPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AssessmentFacade() {
        super(Assessment.class);
    }

    public List<Assessment> getDepartmentAssessments(Department department) {
        try {
            List results = em.createNamedQuery("Assessment.findByAssesseeDepartment")
                    .setParameter("department", department)
                    .getResultList();
            return results;
        }
        catch (Exception e) {
            return null;
        }
    }
    
    public List<Assessment> getEmployeeAssessments(Employee employee) {
        try {
            List results = em.createQuery("SELECT a FROM Assessment a WHERE a.assesseeIdemployee=:e OR "+
                    "a.immediateManagerIdemployee=:e OR a.colleague1Idemployee=:e OR a.colleague2Idemployee=:e OR "+
                    "a.colleague3Idemployee=:e")
                    .setParameter("e", employee)
                    .getResultList();
            return results;
        }
        catch (Exception e) {
            return null;
        }
    }
    
    
    
    public List<Assessment> getOngoingEmployeeAssessments(Employee employee) {
        try {
            List results = em.createQuery("SELECT a FROM Assessment a WHERE (a.assesseeIdemployee=:e OR "+
                    "a.immediateManagerIdemployee=:e OR a.colleague1Idemployee=:e OR a.colleague2Idemployee=:e OR "+
                    "a.colleague3Idemployee=:e) AND NOT a.completed")
                    .setParameter("e", employee)
                    .getResultList();
            return results;
        }
        catch (Exception e) {
            return null;
        }
    }
    
     public List<Assessment> getAssesseeEmployeeAssessments(Employee employee) {
        try {
            List results;
            results = em.createQuery("SELECT a FROM Assessment a WHERE a.assesseeIdemployee=:e")
                    .setParameter("e", employee)
                    .getResultList();
            return results;
        }
        catch (Exception e) {
            return null;
        }
    }
    
    public Assessment getAssessmentByDateAndAssessee(String date, String lname, String fname) {
        try {
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            java.util.Date d = df.parse(date);
            java.sql.Date sd = new java.sql.Date(d.getTime());
            Assessment result = (Assessment)
                    em.createQuery("SELECT a FROM Assessment a WHERE a.assesseeIdemployee.lastName=:lname AND a.assesseeIdemployee.firstName=:fname AND a.dateCreated=:date")
                    .setParameter("fname",fname).setParameter("lname",lname).setParameter("date",sd).getSingleResult();
            return result;   
        }
        
        catch (Exception e) {
            return null;
        }
    }
}
