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
import eu.comprofits.entities.jobprofile.Job;
import eu.comprofits.session.AbstractFacade;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TemporalType;

/**
 *
 * @author George Kakarontzas
 */
@Stateless
public class JobAdvertisementFacade extends AbstractFacade<JobAdvertisement> {
    @PersistenceContext(unitName = "comprofitsPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public JobAdvertisementFacade() {
        super(JobAdvertisement.class);
    }
    
    
    public List<JobAdvertisement> getAvailableJobs() {
        /*
        Query q = em.createQuery(
                "SELECT ja FROM JobAdvertisement ja, Job jb WHERE ja.jobIdjob=jb AND  jb.status=:status");
                q.setParameter("status", true);
                */
        
        Query q = em.createQuery(
            "Select ja FROM JobAdvertisement ja WHERE ja.startDate <= :date AND ja.endDate >= :date").
                setParameter("date", new Date(),  TemporalType.DATE);
        return q.getResultList();
    }
    
    public List<JobAdvertisement> getJobAdvertisementsForJob (Job job)
    {
        Query q = em.createQuery("Select j from JobAdvertisement j where j.jobIdjob=:job");
        q.setParameter("job", job);
        return q.getResultList();
    }
    
}
