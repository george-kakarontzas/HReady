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
 * @author George Kakarontzas
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
      
     public List<JobApplication> getOrderedApplications() {
        Query q = em.createQuery("SELECT ja FROM JobApplication ja ORDER BY ja.date DESC");
        return q.getResultList();
    }
     
     public List<JobApplication> getOrderedApplications(JobAdvertisement job) {
        Query q = em.createQuery("SELECT ja FROM JobApplication ja WHERE ja.jobAdvertisementIdjobAdvertisement=:job ORDER BY ja.date DESC");
        q.setParameter("job", job);
        return q.getResultList();
    }
    
}
