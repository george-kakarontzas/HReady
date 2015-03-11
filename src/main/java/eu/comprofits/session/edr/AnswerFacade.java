/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package eu.comprofits.session.edr;

import eu.comprofits.entities.edr.Answer;
import eu.comprofits.entities.edr.Edr;
import eu.comprofits.session.edr.*;
import eu.comprofits.entities.edr.Question;
import eu.comprofits.entities.edr.QuestionCategory;
import eu.comprofits.session.AbstractFacade;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author george
 */
@Stateless
public class AnswerFacade extends AbstractFacade<Answer> {
    @PersistenceContext(unitName = "comprofitsPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AnswerFacade() {
        super(Answer.class);
    }
    
    public List<Answer> getAnswersForEdr (Edr edr)
    {
        Query q = em.createQuery("Select a from answer a where edr=:edr;");
        q.setParameter("edr",edr);
        List<Answer> answers = q.getResultList();
        return answers;
    }
    
    public List<Answer> getAnswerForQuestionAndEdr (Question question, Edr edr)
    {
        Query q = em.createQuery("Select a from answer a where question_idquestion=:question and edr=:edr;");
        q.setParameter("question", question);
        q.setParameter("edr",edr);
        List<Answer> answer = q.getResultList();
        return answer;
    }
    
}
