/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.comprofits.session.jobprofile;

import eu.comprofits.entities.jobprofile.Job;
import eu.comprofits.session.AbstractFacade;
import eu.comprofits.session.employee.InCompanyEmploymentFacade;
import eu.comprofits.session.jobapplicant.JobAdvertisementFacade;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author george
 */
@Stateless
public class JobFacade extends AbstractFacade<Job> {

    @PersistenceContext(unitName = "comprofitsPU")
    private EntityManager em;
    
    @EJB
    private ProfessionalExperienceMinRequirementsFacade pemrFacade;
    @EJB
    private InCompanyEmploymentFacade iceFacade;
    @EJB
    private CompetencesRequirementFacade crFacade;
    @EJB
    private JobStudyMinRequirementsFacade jsmrFacade;
    @EJB
    private JobAdvertisementFacade jaFacade;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    

    public JobFacade() {
        super(Job.class);
    }

    public Job findByJobTitle(String title) {
        try {
            Job job = (Job) em.createNamedQuery("Job.findByJobTitle").
                    setParameter("jobTitle", title).
                    getSingleResult();
            return job;
        } catch (Exception e) {
            return null;
        }
    }
    
    public List<Job> getJobsForEvaluation() {
        Query q = em.createQuery("SELECT j FROM Job j WHERE j.idjob IN (SELECT c.jobIdjob.idjob FROM CompetencesRequirement c)");
        return q.getResultList();
    }
    
    public boolean isUsed (Job job)
    {
        boolean check = false;
        if (!this.pemrFacade.getProfessionalExperienceMinRequirementsForJob(job).isEmpty() || 
                !this.iceFacade.getInCompanyEmploymentsForJob(job).isEmpty() || 
                !this.crFacade.getRequirementsForJob(job).isEmpty() || 
                !this.jsmrFacade.getJobStudyMinRequirementsForJob(job).isEmpty() || 
                !this.jaFacade.getJobAdvertisementsForJob(job).isEmpty())
        {
            check = true;
        }
        return check;
    }
}
