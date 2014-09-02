/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package eu.comprofits.session.jobapplicant;

import eu.comprofits.entities.jobapplicant.ApplicantProfessionalExperienceRecord;
import eu.comprofits.session.AbstractFacade;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author george
 */
@Stateless
public class ApplicantProfessionalExperienceRecordFacade extends AbstractFacade<ApplicantProfessionalExperienceRecord> {
    @PersistenceContext(unitName = "comprofitsPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ApplicantProfessionalExperienceRecordFacade() {
        super(ApplicantProfessionalExperienceRecord.class);
    }
    
}
