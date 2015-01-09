/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package eu.comprofits.session.jobapplicant;

import eu.comprofits.entities.jobapplicant.ApplicantStudyRecord;
import eu.comprofits.entities.jobapplicant.JobApplicant;
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
public class ApplicantStudyRecordFacade extends AbstractFacade<ApplicantStudyRecord> {
    @PersistenceContext(unitName = "comprofitsPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ApplicantStudyRecordFacade() {
        super(ApplicantStudyRecord.class);
    }
    
    public List<ApplicantStudyRecord> findByApplicant(JobApplicant applicant) {
        Query q = em.createQuery("SELECT a FROM ApplicantStudyRecord a WHERE a.jobApplicantIdjobApplicant = :applicant ORDER by a.dateStarted DESC, a.dateAcquired ASC");
        q.setParameter("applicant", applicant);
        return q.getResultList();
    }
    
}
