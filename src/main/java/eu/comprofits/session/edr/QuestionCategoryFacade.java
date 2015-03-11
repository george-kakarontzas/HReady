/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
 * @author george
 */
@Stateless
public class QuestionCategoryFacade extends AbstractFacade<QuestionCategory> {
    @PersistenceContext(unitName = "comprofitsPU")
    private EntityManager em;
    @EJB
    private QuestionFacade questionFacade;

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