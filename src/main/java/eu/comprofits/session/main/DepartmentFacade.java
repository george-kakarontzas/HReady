/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.comprofits.session.main;

import eu.comprofits.entities.jobprofile.Division;
import eu.comprofits.entities.main.Department;
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
public class DepartmentFacade extends AbstractFacade<Department> {

    @PersistenceContext(unitName = "comprofitsPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DepartmentFacade() {
        super(Department.class);
    }

    public Department findByName(String dName) {
        Object result = em.createNamedQuery("Department.findByDepartmentName")
                .setParameter("departmentName", dName)
                .getSingleResult();
        return ((Department) result);
    }
    
    //return the departments of a given division
    public List<Department> findDepartmenstForDivision(Division division) {
        return em.createNamedQuery("Department.findByDivision")
                .setParameter("divisionIdDivision", division)
                .getResultList();
       
    }

}
