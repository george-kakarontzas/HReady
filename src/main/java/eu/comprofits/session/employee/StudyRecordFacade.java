/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.comprofits.session.employee;

import eu.comprofits.entities.employee.Employee;
import eu.comprofits.entities.employee.StudyRecord;
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
public class StudyRecordFacade extends AbstractFacade<StudyRecord> {

    @PersistenceContext(unitName = "comprofitsPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public StudyRecordFacade() {
        super(StudyRecord.class);
    }

    public List<StudyRecord> getStudyRecsForEmployee(Employee e) {
        List<StudyRecord> all = em.createQuery(
                "Select s From StudyRecord s WHERE s.employeeIdemployee=:emp").
                setParameter("emp", e).getResultList();
        return all;
    }
}
