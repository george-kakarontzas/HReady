/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package eu.comprofits.session.edr;

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
            
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public QuestionFacade() {
        super(Question.class);
    }
    
    public List<Question> getQuestionsForCategory (QuestionCategory category)
    {
        Query q = em.createQuery("SELECT c FROM Question q WHERE q.questionCategoryIdquestioncat=:category");
        q.setParameter("category",category);
        List<Question> questions = q.getResultList();
        
        return questions;
    }
    
    public TreeNode getQuestionTree ()
    {
        TreeNode questionTree = new DefaultTreeNode("Root",null);
        for (QuestionCategory category : questionCategoryFacade.getCategories())
        {
            TreeNode categoryNode = new DefaultTreeNode(category, questionTree);
            for (Question question : this.getQuestionsForCategory(category))
            {
                TreeNode questionNode = new DefaultTreeNode(question, categoryNode);
            }
        }
        return questionTree;
    }
}
