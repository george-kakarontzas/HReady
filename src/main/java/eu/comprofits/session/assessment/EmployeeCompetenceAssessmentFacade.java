/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
 * @author george
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

}
