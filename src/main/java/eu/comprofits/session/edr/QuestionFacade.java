/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package eu.comprofits.session.edr;

import eu.comprofits.entities.edr.Answer;
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
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

/**
 *
 * @author george
 */
@Stateless
public class QuestionFacade extends AbstractFacade<Question> {
    @PersistenceContext(unitName = "comprofitsPU")
    private EntityManager em;
    @EJB
    private QuestionCategoryFacade questionCategoryFacade;
    @EJB
    private AnswerFacade answerFacade;
    @EJB
    private EdrFacade edrFacade;
            
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public QuestionFacade() {
        super(Question.class);
    }
    
    public List<Question> getQuestionsForCategory (QuestionCategory category)
    {
        Query q = em.createQuery("SELECT q FROM Question q WHERE q.questionCategoryIdquestioncat=:category");
        q.setParameter("category",category);
        List<Question> questions = q.getResultList();
        
        return questions;
    }   
    
    public boolean isUsed (Question question)
    {
        boolean check = false;
        for (Edr edr : edrFacade.findAll())
        {
            if (this.isUsedInEdr(question, edr))
            {
                check = true;
            }
        }
        return check;
    }
    
    public boolean isUsedInEdr (Question question, Edr edr)
    {
        if (answerFacade.getAnswerForQuestionAndEdr(question, edr) != null)
        {
            return true;
        }
        else return false;
    }
}