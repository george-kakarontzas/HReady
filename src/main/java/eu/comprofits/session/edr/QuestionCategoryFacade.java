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
import eu.comprofits.entities.edr.Question;
import eu.comprofits.entities.edr.QuestionCategory;
import eu.comprofits.session.AbstractFacade;
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
public class QuestionCategoryFacade extends AbstractFacade<QuestionCategory> {
    @PersistenceContext(unitName = "comprofitsPU")
    private EntityManager em;
    @EJB
    private QuestionFacade questionFacade;
    @EJB
    private EdrFacade edrFacade;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public QuestionCategoryFacade() {
        super(QuestionCategory.class);
    }
    
    public List<QuestionCategory> getCategories() {
        Query q = em.createQuery("Select c from QuestionCategory c");
        List<QuestionCategory> categoryList = q.getResultList();
        return categoryList;
    }
    
    public boolean isUsed (QuestionCategory category)
    {
        boolean check = false;
        for (Edr edr : edrFacade.findAll())
        {
            if (this.isUsedInEdr(category, edr))
            {
                check = true;
            }
        }
        return check;
    }
    
    public boolean isUsedInEdr (QuestionCategory category, Edr edr)
    {
        boolean check = false;
        for (Question q : questionFacade.getQuestionsForCategory(category))
        {
            if (questionFacade.isUsedInEdr(q, edr))
            {
                check = true;
            }
        }
        
        return check;
    }
    
}
