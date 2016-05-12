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
 * @author George Kakarontzas
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
