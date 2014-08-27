/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package eu.comprofits.session.assessment;

import eu.comprofits.entities.assessment.EmployeeCompetenceAssessment;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import eu.comprofits.session.AbstractFacade;

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
    
}
