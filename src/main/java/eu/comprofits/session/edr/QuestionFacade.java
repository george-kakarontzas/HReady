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
 * @author George Kakarontzas
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
        if (!answerFacade.getAnswerForQuestionAndEdr(question, edr).isEmpty())
        {
            return true;
        }
        else return false;
    }
}