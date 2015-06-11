/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package eu.comprofits.session.edr;

import eu.comprofits.entities.edr.Edr;
import eu.comprofits.entities.employee.Employee;
import eu.comprofits.entities.jobprofile.Job;
import eu.comprofits.entities.main.Department;
import eu.comprofits.session.AbstractFacade;
import java.util.ArrayList;
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
public class EdrFacade extends AbstractFacade<Edr> {
    @PersistenceContext(unitName = "comprofitsPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EdrFacade() {
        super(Edr.class);
    }
    
    public List<Edr> getEdrsForEmployee (Employee employee)
    {
        List<Edr> edrList = this.findAll();
        List<Edr> filteredEdrList = new ArrayList();
        
        for (Edr edr : edrList)
        {
            if (employee.getIdemployee() == edr.getHeadOfDepartmentIdemployee().getIdemployee())
            {
                edr.setRole(1);
                filteredEdrList.add(edr);
            }
            if ((employee.getIdemployee() == edr.getImmediateManagerIdemployee().getIdemployee()) && (edr.getStatus() > 1))
            {
                edr.setRole(2);     
                filteredEdrList.add(edr);
            }
            if ((employee.getIdemployee() == edr.getReviewedEmployeeIdemployee().getIdemployee()) && (edr.getStatus() > 2))
            {
                edr.setRole(3);    
                filteredEdrList.add(edr);
            }
            
        }
        
        return filteredEdrList;
    }
}
