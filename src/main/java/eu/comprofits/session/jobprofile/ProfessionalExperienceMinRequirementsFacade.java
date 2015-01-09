/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package eu.comprofits.session.jobprofile;

import eu.comprofits.entities.jobprofile.ProfessionalExperienceMinRequirements;
import eu.comprofits.session.AbstractFacade;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author george
 */
@Stateless
public class ProfessionalExperienceMinRequirementsFacade extends AbstractFacade<ProfessionalExperienceMinRequirements> {
    @PersistenceContext(unitName = "comprofitsPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProfessionalExperienceMinRequirementsFacade() {
        super(ProfessionalExperienceMinRequirements.class);
    }
    
}
