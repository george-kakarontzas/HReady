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
 * @author George Kakarontzas
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
