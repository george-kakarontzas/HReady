/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.comprofits.session.main;

import eu.comprofits.entities.main.Competence;
import eu.comprofits.session.AbstractFacade;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author george
 */
@Stateless
public class CompetenceFacade extends AbstractFacade<Competence> {

    @PersistenceContext(unitName = "comprofitsPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CompetenceFacade() {
        super(Competence.class);
    }

    public Competence findByName(String cName) {
        Object result = em.createNamedQuery("Competence.findByCompetenceName")
                .setParameter("competenceName", cName)
                .getSingleResult();
        return ((Competence) result);
    }

}
