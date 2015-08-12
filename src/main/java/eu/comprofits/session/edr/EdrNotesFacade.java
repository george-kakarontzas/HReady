/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package eu.comprofits.session.edr;

import eu.comprofits.entities.edr.Edr;
import eu.comprofits.entities.edr.EdrNotes;
import eu.comprofits.session.AbstractFacade;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author alexanderhoelzemann
 */
@Stateless
public class EdrNotesFacade extends AbstractFacade<EdrNotes> {
    @PersistenceContext(unitName = "comprofitsPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EdrNotesFacade() {
        super(EdrNotes.class);
    }
    
    public List<EdrNotes> getNotesForEdr (Edr edr)
    {
        List<EdrNotes> notes = new ArrayList();
        Query q = em.createQuery("SELECT e FROM EdrNotes e WHERE e.edrIdedr=:Idedr ORDER BY e.date DESC");
        q.setParameter("Idedr", edr.getIdedr());
        notes = q.getResultList();
        return notes;
    }
    
}
