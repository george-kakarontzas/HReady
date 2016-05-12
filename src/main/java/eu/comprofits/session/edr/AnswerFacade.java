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
import eu.comprofits.session.edr.*;
import eu.comprofits.entities.edr.Question;
import eu.comprofits.entities.edr.QuestionCategory;
import eu.comprofits.session.AbstractFacade;
import java.util.ArrayList;
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
public class AnswerFacade extends AbstractFacade<Answer> {
    @PersistenceContext(unitName = "comprofitsPU")
    private EntityManager em;
    
    @EJB
    private QuestionFacade questionFacade;
    @EJB
    private QuestionCategoryFacade questionCategoryFacade;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AnswerFacade() {
        super(Answer.class);
    }
    
    public List<Answer> getAnswersForEdr (Edr edr)
    {   
        Query q = em.createQuery("SELECT a FROM Answer a WHERE a.edrIdedr=:edr");
        q.setParameter("edr",edr);
        List<Answer> answers = q.getResultList();
        return answers;
    }
    
    public List<Answer> getAnswerForQuestionAndEdr (Question question, Edr edr)
    {
        List<Answer> answers = new ArrayList();
        Query q = em.createQuery("SELECT a FROM Answer a WHERE a.questionIdquestion=:question AND a.edrIdedr=:edr");
        q.setParameter("question",question);
        q.setParameter("edr",edr);
        answers = q.getResultList();
        return answers;
    }
    
    public void createAnswersForEdr (List<List<Question>> questions, Edr edr)
    {
        for (List<Question> ql : questions)
                {
                    for (Question q : ql)
                    {
                        if (q.isChecked())
                        {
                            Answer answer = new Answer();
                            answer.setQuestionIdquestion(q);
                            answer.setEdrIdedr(edr);
                            answer.setAnswer("");
                            create(answer);
                        }
                    }
                }
    }
    
    public void updateAnswersForEdr (List<List<Answer>> answers, Edr edr)
    {
        for (List<Answer> al : answers)
        {
            for (Answer a : al)
            {
                edit(a);
            }
        }
    }
    
}
