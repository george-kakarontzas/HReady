/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package eu.comprofits.session.assessment;

import eu.comprofits.entities.assessment.Assessment;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import eu.comprofits.session.AbstractFacade;

/**
 *
 * @author george
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
    
}
