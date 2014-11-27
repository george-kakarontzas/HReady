/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package eu.comprofits.session.jobapplicant;

import eu.comprofits.entities.jobapplicant.JobAdvertisement;
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
        Query q = em.createQuery("SELECT ja FROM JobAdvertisement ja, Job jb WHERE ja.jobIdjob=jb AND  jb.status=:status");
                q.setParameter("status", true);
        return q.getResultList();
    }
    
}
