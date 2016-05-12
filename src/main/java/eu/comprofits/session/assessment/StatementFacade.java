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
package eu.comprofits.session.assessment;

import eu.comprofits.entities.assessment.Statement;
import eu.comprofits.entities.main.Competence;
import eu.comprofits.session.AbstractFacade;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author George Kakarontzas
 */
@Stateless
public class StatementFacade extends AbstractFacade<Statement> {
    @PersistenceContext(unitName = "comprofitsPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public StatementFacade() {
        super(Statement.class);
    }
    
    //select all statements for a given competence
    public List<Statement> getCompetenceStatements(Competence c) {
        return em.createQuery(
                "Select s From Statement s WHERE s.competenceId=:competence").
                setParameter("competence", c).getResultList();
    }
    
    public Statement getStatementFromText(String text) {
        try {
            Statement result = (Statement) em.createNamedQuery("Statement.findByStatementText")
                    .setParameter("statementText", text)
                    .getSingleResult();
            return result;
        }
        catch (Exception e) {
            return null;
        }
    }
    
}
