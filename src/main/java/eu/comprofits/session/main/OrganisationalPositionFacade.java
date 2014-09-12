/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.comprofits.session.main;

import eu.comprofits.entities.main.OrganisationalPosition;
import eu.comprofits.session.AbstractFacade;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author george
 */
@Stateless
public class OrganisationalPositionFacade extends AbstractFacade<OrganisationalPosition> {

    @PersistenceContext(unitName = "comprofitsPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public OrganisationalPositionFacade() {
        super(OrganisationalPosition.class);
    }

    public OrganisationalPosition findByName(String name) {
        Object result = em.createNamedQuery("OrganisationalPosition.findByOrganisationalPositionName")
                .setParameter("organisationalPositionName", name)
                .getSingleResult();
        return ((OrganisationalPosition) result);
    }
}
