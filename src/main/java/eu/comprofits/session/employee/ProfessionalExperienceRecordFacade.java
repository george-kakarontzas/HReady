/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package eu.comprofits.session.employee;

import eu.comprofits.entities.employee.Employee;
import eu.comprofits.entities.employee.ProfessionalExperienceRecord;
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
public class ProfessionalExperienceRecordFacade extends AbstractFacade<ProfessionalExperienceRecord> {
    @PersistenceContext(unitName = "comprofitsPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProfessionalExperienceRecordFacade() {
        super(ProfessionalExperienceRecord.class);
    }
    
    public List<ProfessionalExperienceRecord> getProRecsForEmployee(Employee e) {
        List<ProfessionalExperienceRecord> all = em.createQuery(
                "Select p From ProfessionalExperienceRecord p WHERE p.employeeIdemployee=:emp").
                setParameter("emp", e).getResultList();
        return all;
    }
    
}
