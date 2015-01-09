/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package eu.comprofits.session.jobapplicant;

import eu.comprofits.entities.jobapplicant.JobAdvertisement;
import eu.comprofits.entities.jobapplicant.JobApplicant;
import eu.comprofits.entities.jobapplicant.JobApplication;
import eu.comprofits.session.AbstractFacade;
import java.util.List;
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
public class JobApplicationFacade extends AbstractFacade<JobApplication> {
    @PersistenceContext(unitName = "comprofitsPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public JobApplicationFacade() {
        super(JobApplication.class);
    }
    
    public JobApplication getApplicationByJobAndApplication(JobAdvertisement job, JobApplicant applicant) {
        Query q = em.createQuery("SELECT ja FROM JobApplication ja WHERE ja.jobApplicantIdjobApplicant=:applicant AND ja.jobAdvertisementIdjobAdvertisement=:job");
        q.setParameter("job", job);
        q.setParameter("applicant", applicant);
        try {
            return (JobApplication) q.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
    
      public List<JobApplication> getApplicationsByApplicant(JobApplicant applicant) {
        Query q = em.createQuery("SELECT ja FROM JobApplication ja WHERE ja.jobApplicantIdjobApplicant=:applicant ORDER BY ja.date DESC");
        q.setParameter("applicant", applicant);
        return q.getResultList();
    }
    
}
