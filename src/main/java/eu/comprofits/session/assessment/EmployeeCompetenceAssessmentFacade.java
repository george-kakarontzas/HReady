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
import eu.comprofits.entities.assessment.EmployeeCompetenceAssessment;
import eu.comprofits.entities.assessment.Statement;
import eu.comprofits.entities.employee.Employee;
import eu.comprofits.session.AbstractFacade;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

/**
 *
 * @author George Kakarontzas
 */
@Stateless
public class EmployeeCompetenceAssessmentFacade extends AbstractFacade<EmployeeCompetenceAssessment> {

    @PersistenceContext(unitName = "comprofitsPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EmployeeCompetenceAssessmentFacade() {
        super(EmployeeCompetenceAssessment.class);
    }

    public EmployeeCompetenceAssessment matchEmployeeCompetenceAssessment(Assessment a, Employee e, Statement s) {
        EmployeeCompetenceAssessment eca = null;
        try {
            eca = (EmployeeCompetenceAssessment) em.createQuery(
                    "Select e From EmployeeCompetenceAssessment e WHERE e.assessmentIdassessment=:assessmentId AND"
                    + "  e.assessorIdemployee=:employeeId AND e.statementIdstatement=:statementId").
                    setParameter("assessmentId", a).
                    setParameter("employeeId", e).
                    setParameter("statementId", s).
                    getSingleResult();
        } catch (NoResultException ignored) {
        }
        return eca;
    }

    public List<EmployeeCompetenceAssessment> findAllForAssessment(Assessment a) {
        List<EmployeeCompetenceAssessment> ecas = em.createQuery(
                "Select e From EmployeeCompetenceAssessment e WHERE e.assessmentIdassessment=:assessmentId").
                setParameter("assessmentId", a).
                getResultList();
        return ecas;
    }
    
    public List<EmployeeCompetenceAssessment> findAllForAssessmentAndEmployee(Assessment a, Employee e) {
        List<EmployeeCompetenceAssessment> ecas = em.createQuery(
                "Select e From EmployeeCompetenceAssessment e WHERE e.assessmentIdassessment=:a AND e.assessorIdemployee=:e").
                setParameter("a", a).setParameter("e",e).
                getResultList();
        return ecas;
    }

}
