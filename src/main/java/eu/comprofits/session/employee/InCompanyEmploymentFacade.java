/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package eu.comprofits.session.employee;

import eu.comprofits.entities.employee.Employee;
import eu.comprofits.entities.employee.InCompanyEmployment;
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
        return em.createQuery(
                "Select i From InCompanyEmployment i WHERE i.employeeIdemployee=:emp").
                setParameter("emp", e).getResultList();
    }   
}
