/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package eu.comprofits.session.jobapplicant;

import eu.comprofits.entities.jobapplicant.JobApplicant;
import eu.comprofits.session.AbstractFacade;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

/**
 *
 * @author george
 */
@Stateless
public class JobApplicantFacade extends AbstractFacade<JobApplicant> {

    public JobApplicant getJobApplicantByUsername(String username) {
        try {
        JobApplicant result = (JobApplicant) em.createNamedQuery("JobApplicant.findByUsername")
                .setParameter("username", username)
                .getSingleResult();
        return result;
        }
        catch (NoResultException nre) {
            return null;
        }
    }
    @PersistenceContext(unitName = "comprofitsPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public JobApplicantFacade() {
        super(JobApplicant.class);
    }
    
}
