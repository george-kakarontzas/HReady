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
package eu.comprofits.session.employee;

import eu.comprofits.entities.employee.CurrentCompetenceAssessment;
import eu.comprofits.entities.employee.Employee;
import eu.comprofits.entities.main.Competence;
import eu.comprofits.session.AbstractFacade;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author George Kakarontzas
 */
@Stateless
public class CurrentCompetenceAssessmentFacade extends AbstractFacade<CurrentCompetenceAssessment> {
    @PersistenceContext(unitName = "comprofitsPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CurrentCompetenceAssessmentFacade() {
        super(CurrentCompetenceAssessment.class);
    }
    
    public CurrentCompetenceAssessment getAssessmentForEmployeeAndCompetence(Employee emp,Competence com) {
        Query q = em.createQuery("SELECT c FROM CurrentCompetenceAssessment c WHERE c.employeeIdemployee=:emp AND c.competenceIdcompetence=:com");
        q.setParameter("emp",emp);
        q.setParameter("com",com);
        CurrentCompetenceAssessment c = null;
        try {
            c = (CurrentCompetenceAssessment) q.getSingleResult();
        } catch (NoResultException e) {}
        return c;
    }
    
}
