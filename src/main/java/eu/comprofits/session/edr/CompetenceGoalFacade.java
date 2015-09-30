/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package eu.comprofits.session.edr;

import eu.comprofits.entities.edr.CompetenceGoal;
import eu.comprofits.entities.edr.Edr;
import eu.comprofits.entities.jobprofile.CompetencesRequirement;
import eu.comprofits.entities.main.Competence;
import eu.comprofits.session.AbstractFacade;
import eu.comprofits.session.jobprofile.CompetencesRequirementFacade;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

/**
 *
 * @author george
 */
@Stateless
public class CompetenceGoalFacade extends AbstractFacade<CompetenceGoal> {
    @PersistenceContext(unitName = "comprofitsPU")
    private EntityManager em;
    @EJB
    private CompetencesRequirementFacade crFacade;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CompetenceGoalFacade() {
        super(CompetenceGoal.class);
    }
    
    public List<CompetenceGoal> getGoals() {
        Query q = em.createQuery("SELECT c FROM CompetenceGoal c ORDER BY c.edrIdedr");
        return q.getResultList();
    }
    
    public List<CompetenceGoal> getGoalsForEdr(Edr edr) {
        Query q = em.createQuery("SELECT c FROM CompetenceGoal c WHERE c.edrIdedr=:edr");
        q.setParameter("edr",edr);
        return q.getResultList();
    }
    
    public CompetenceGoal getGoalForEdrAndCompetence(Edr edr,Competence com) {
        Query q = em.createQuery("SELECT c FROM CompetenceGoal c WHERE c.edrIdedr=:edr AND c.competenceIdcompetence=:com");
        q.setParameter("edr",edr);
        q.setParameter("com",com);
        CompetenceGoal c = null;
        try {
            c = (CompetenceGoal) q.getSingleResult();
        } catch (NoResultException e) {}
        return c;
    }
    
    public TreeNode getCompetenceGoalsTree(TreeNode competencesTree, Edr edr)
    {
        TreeNode competenceGoalsTree = new DefaultTreeNode("Root",null);
        
        for (TreeNode c: competencesTree.getChildren())
        {
            getCompetenceGoalsTree(c,competenceGoalsTree, edr);
        }
        return competenceGoalsTree;
    }
    
    public void getCompetenceGoalsTree(TreeNode childNode, TreeNode parentNode, Edr edr)
    {     
            if (this.getGoalForEdrAndCompetence(edr, (Competence)childNode.getData()) == null)
            {
                CompetenceGoal newCg = new CompetenceGoal();
                newCg.setCompetenceIdcompetence((Competence)childNode.getData());
                newCg.setEdrIdedr(edr);
                newCg.setComments("");
                newCg.setNextYearGoalValue(0);
                TreeNode childTree = new DefaultTreeNode(newCg, parentNode);
            
                for (TreeNode c: childNode.getChildren())
                {
                    getCompetenceGoalsTree(c, childTree, edr);
                }
            }
            else
            {
                TreeNode childTree = new DefaultTreeNode(getGoalForEdrAndCompetence(edr, (Competence)childNode.getData()), parentNode);
                for (TreeNode c: childNode.getChildren())
                {
                    getCompetenceGoalsTree(c, childTree, edr);
                }
            } 
        
    }
    
    public void updateCompetenceGoals (TreeNode competencesGoalsTree, Edr edr)
    {
        for (TreeNode cgNode : competencesGoalsTree.getChildren())
        {
            CompetenceGoal competenceGoal = (CompetenceGoal) cgNode.getData();
            Competence competence = competenceGoal.getCompetenceIdcompetence();
            if (getGoalForEdrAndCompetence(edr, competence) == null && (!competenceGoal.getComments().isEmpty() || competenceGoal.getNextYearGoalValue() != 0))
            {
                create(competenceGoal);
            }
            else
            {
                if (getGoalForEdrAndCompetence(edr, competence) != null && (!competenceGoal.getComments().isEmpty() || competenceGoal.getNextYearGoalValue() != 0))
                {
                    edit(competenceGoal);
                }
                else
                {
                    if (getGoalForEdrAndCompetence(edr, competence) != null && competenceGoal.getComments().isEmpty() && competenceGoal.getNextYearGoalValue() == 0)
                    {
                        remove(competenceGoal);
                    }
                }
            }
            updateCompetenceGoals(cgNode, edr);
        }    
    }
    
    public void removeCompetenceGoalsForEdr (Edr edr) {
        Query q = em.createQuery("DELETE FROM CompetenceGoal WHERE edrIdedr=:edr");
        q.setParameter("edr",edr);
    }
    
    public void convertTreeToList (TreeNode competencesGoalsTree, List<CompetenceGoal> resultList) {
        
        for (TreeNode cgNode : competencesGoalsTree.getChildren())
        {
            CompetenceGoal cg = (CompetenceGoal)cgNode.getData();
            resultList.add(cg);
            convertTreeToList (cgNode, resultList);
        }
    }
}
