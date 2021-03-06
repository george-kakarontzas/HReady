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
package eu.comprofits.session.jobprofile;

import eu.comprofits.entities.employee.Employee;
import eu.comprofits.entities.jobprofile.CompetencesRequirement;
import eu.comprofits.entities.jobprofile.Job;
import eu.comprofits.entities.main.Competence;
import eu.comprofits.session.AbstractFacade;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

/**
 *
 * @author George Kakarontzas
 */
@Stateless
public class CompetencesRequirementFacade extends AbstractFacade<CompetencesRequirement> {
    @PersistenceContext(unitName = "comprofitsPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CompetencesRequirementFacade() {
        super(CompetencesRequirement.class);
    }
    
    
     public List<CompetencesRequirement> getRequirements() {
        Query q = em.createQuery("SELECT c FROM CompetencesRequirement c ORDER BY c.jobIdjob");
        return q.getResultList();
    }
    
    public List<CompetencesRequirement> getRequirementsForJob(Job job) {
        Query q = em.createQuery("SELECT c FROM CompetencesRequirement c WHERE c.jobIdjob=:job");
        q.setParameter("job",job);
        return q.getResultList();
    }
    
    public CompetencesRequirement getRequirementForJobAndCompetence(Job job,Competence com) {
        Query q = em.createQuery("SELECT c FROM CompetencesRequirement c WHERE c.jobIdjob=:job AND c.competenceIdcompetence=:com");
        q.setParameter("job",job);
        q.setParameter("com",com);
        CompetencesRequirement c = null;
        try {
            c = (CompetencesRequirement) q.getSingleResult();
        } catch (NoResultException e) {}
        return c;
    }
    public TreeNode getCompetencesRequirementsTree(TreeNode competencesTree, Job job)
    {
        TreeNode competencesRequirementsTree = new DefaultTreeNode("Root",null);
        
        for (TreeNode c: competencesTree.getChildren())
        {
            getCompetencesRequirementsTree(c,competencesRequirementsTree, job);
        }
        return competencesRequirementsTree;
    }
    
    public void getCompetencesRequirementsTree(TreeNode childNode, TreeNode parentNode, Job job)
    {       
        if (this.getRequirementForJobAndCompetence(job, (Competence)childNode.getData()) == null)
        {
            CompetencesRequirement newCr = new CompetencesRequirement();
            newCr.setCompetenceIdcompetence((Competence)childNode.getData());
            newCr.setJobIdjob(job);
            newCr.setWeight(0);
            newCr.setImportance(0);
            TreeNode childTree = new DefaultTreeNode(newCr, parentNode);
            
            for (TreeNode c: childNode.getChildren())
            {
                getCompetencesRequirementsTree(c, childTree, job);
            }
        }
        else
        {
            TreeNode childTree = new DefaultTreeNode(getRequirementForJobAndCompetence(job, (Competence)childNode.getData()), parentNode);
            for (TreeNode c: childNode.getChildren())
            {
                getCompetencesRequirementsTree(c, childTree, job);
            }
        }    
    }
    
    public void updateCompetencesRequirements (TreeNode competencesRequirementsTree, Job job)
    {
        for (TreeNode crNode : competencesRequirementsTree.getChildren())
        {
            CompetencesRequirement competencesRequirement = (CompetencesRequirement) crNode.getData();
            Competence competence = competencesRequirement.getCompetenceIdcompetence();
            if (getRequirementForJobAndCompetence(job, competence) == null && (competencesRequirement.getWeight() != 0 || competencesRequirement.getImportance() != 0))
            {
                competencesRequirement.setJobIdjob(job);
                create(competencesRequirement);
            }
            else
            {
                if (getRequirementForJobAndCompetence(job, competence) != null && (competencesRequirement.getWeight() != 0 || competencesRequirement.getImportance() != 0))
                {
                    competencesRequirement.setJobIdjob(job);
                    edit(competencesRequirement);
                }
                else
                {
                    if (getRequirementForJobAndCompetence(job, competence) != null && competencesRequirement.getWeight() == 0 && competencesRequirement.getImportance() == 0)
                    {
                        competencesRequirement.setJobIdjob(job);
                        remove(competencesRequirement);
                    }
                }
            }
            updateCompetencesRequirements(crNode, job);
        }
    }
    
    public void removeCompetencesRequirementsForJob (Job job) {
        Query q = em.createQuery("DELETE FROM CompetencesRequirement WHERE jobIdjob=:job");
        q.setParameter("job",job);
    }
    
    public void convertTreeToList (TreeNode competencesRequirementsTree, List<CompetencesRequirement> resultList) {
        
        for (TreeNode crNode : competencesRequirementsTree.getChildren())
        {
            CompetencesRequirement cr = (CompetencesRequirement)crNode.getData();
            resultList.add(cr);
            convertTreeToList (crNode, resultList);
        }
    }
}
