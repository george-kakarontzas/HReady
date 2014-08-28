/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
 * @author george
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
