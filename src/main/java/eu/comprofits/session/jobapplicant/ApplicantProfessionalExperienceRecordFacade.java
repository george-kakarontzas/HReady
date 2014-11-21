/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package eu.comprofits.session.jobapplicant;


import eu.comprofits.entities.jobapplicant.ApplicantProfessionalExperienceRecord;
import eu.comprofits.entities.jobapplicant.ApplicantStudyRecord;
import eu.comprofits.entities.jobapplicant.JobApplicant;
import eu.comprofits.session.AbstractFacade;
import static java.lang.System.out;
import java.util.ArrayList;
import java.util.Collections;
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
public class ApplicantProfessionalExperienceRecordFacade extends AbstractFacade<ApplicantProfessionalExperienceRecord> {
    @PersistenceContext(unitName = "comprofitsPU")
    private EntityManager em;
    
    @EJB JobApplicantFacade applicantFacade;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ApplicantProfessionalExperienceRecordFacade() {
        super(ApplicantProfessionalExperienceRecord.class);
    }
    
    
       public List<ApplicantProfessionalExperienceRecord> findByApplicant(JobApplicant applicant) {
        Query q = em.createQuery("SELECT a FROM ApplicantProfessionalExperienceRecord a WHERE a.jobApplicantIdjobApplicant = :applicant ORDER by a.dateStarted DESC, a.dateFinished DESC");
        q.setParameter("applicant", applicant);
        return q.getResultList();
    }

    @Override
    public void create(ApplicantProfessionalExperienceRecord entity) {
        super.create(entity);
        em.persist(entity);
        this.updateApplicantPresentPosition(entity);
    }

    @Override
    public void edit(ApplicantProfessionalExperienceRecord entity) {
        super.edit(entity); 
        this.updateApplicantPresentPosition(entity);
    }
    
    private void updateApplicantPresentPosition(ApplicantProfessionalExperienceRecord entity) {
        JobApplicant applicant = entity.getJobApplicantIdjobApplicant();
        List<ApplicantProfessionalExperienceRecord> proExperience = this.findByApplicant(applicant);
        if (!proExperience.isEmpty()) {
        applicant.setPresentIdapplicantProfessionalExperienceRecord(proExperience.get(0));
        } else {
        applicant.setPresentIdapplicantProfessionalExperienceRecord(null);    
        }
        this.applicantFacade.edit(applicant);
    }

    @Override
    public void remove(ApplicantProfessionalExperienceRecord entity) {
        // first remove present experience from applicant to avoid fk error
        JobApplicant applicant = entity.getJobApplicantIdjobApplicant();
        applicant.setPresentIdapplicantProfessionalExperienceRecord(null);
        this.applicantFacade.edit(applicant);
        super.remove(entity);
        // reapply the present position
        this.updateApplicantPresentPosition(entity);
    }
    
    
    
    
       
       
}
