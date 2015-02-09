/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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

/**
 *
 * @author george
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
    
    public void removeCompetencesRequirementsForJob (Job job) {
        Query q = em.createQuery("DELETE * FROM CompetencesRequirement WHERE jobIdjob=:job");
        q.setParameter("job",job);
    }
}
