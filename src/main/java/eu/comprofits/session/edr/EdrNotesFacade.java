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
        q.setParameter("Idedr", edr);
        notes = q.getResultList();
        
        for (int i=0;i<notes.size();i++)
        {
            if (i == notes.size()-1)
            {
                notes.get(i).setLastEntry(true);
            }
            else
            {
                notes.get(i).setLastEntry(false);
            }
        }
        return notes;
    }
    
}
