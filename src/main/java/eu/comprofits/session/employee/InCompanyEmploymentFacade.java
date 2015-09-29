/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package eu.comprofits.session.employee;

import eu.comprofits.entities.employee.Employee;
import eu.comprofits.entities.employee.InCompanyEmployment;
import eu.comprofits.entities.jobprofile.Job;
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
public class InCompanyEmploymentFacade extends AbstractFacade<InCompanyEmployment> {
    @PersistenceContext(unitName = "comprofitsPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public InCompanyEmploymentFacade() {
        super(InCompanyEmployment.class);
    }
    
    public List<InCompanyEmployment> getPastPositions(Employee e) {
        List<InCompanyEmployment> all = em.createQuery(
                "Select i From InCompanyEmployment i WHERE i.employeeIdemployee=:emp").
                setParameter("emp", e).getResultList();
        InCompanyEmployment current = e.getCurrentInCompanyEmploymentId();
        all.remove(current);
        return all;
    }   
    
    public Job getCurrentJobOfEmployee (Employee e){
        Query q = em.createQuery("SELECT i FROM InCompanyEmployment i WHERE i.employeeIdemployee=:emp");
        q.setParameter("emp",e);
        List<InCompanyEmployment> ice = q.getResultList();
        Job jobIdJob = ice.get(ice.size()).getJobIdjob();
        return jobIdJob;
    }
    
    public List<InCompanyEmployment> getInCompanyEmploymentsForJob (Job job)
    {
        Query q = em.createQuery("Select i from InCompanyEmployment i where i.jobIdjob=:job");
        q.setParameter("job", job);
        return q.getResultList();
    }
}
