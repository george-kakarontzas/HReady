/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package eu.comprofits.session.jobprofile;

import eu.comprofits.entities.jobprofile.Job;
import eu.comprofits.entities.jobprofile.JobStudyMinRequirements;
import eu.comprofits.entities.jobprofile.ProfessionalExperienceMinRequirements;
import eu.comprofits.session.AbstractFacade;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author george
 */
@Stateless
public class JobStudyMinRequirementsFacade extends AbstractFacade<JobStudyMinRequirements> {
    @PersistenceContext(unitName = "comprofitsPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public JobStudyMinRequirementsFacade() {
        super(JobStudyMinRequirements.class);
    }
    
    public List<JobStudyMinRequirements> getJobStudyMinRequirementsForJob (Job job)
    {
        Query q = em.createQuery("Select j from JobStudyMinRequirements j where j.jobIdjob=:job");
        q.setParameter("job", job);
        return q.getResultList();
    }
    
}
