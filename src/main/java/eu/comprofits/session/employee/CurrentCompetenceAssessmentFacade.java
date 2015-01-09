/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
 * @author george
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
