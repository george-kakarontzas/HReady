/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.comprofits.session.employee;

import eu.comprofits.entities.employee.Employee;
import eu.comprofits.entities.main.Department;
import eu.comprofits.session.AbstractFacade;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

/**
 *
 * @author george
 */
@Stateless
public class EmployeeFacade extends AbstractFacade<Employee> {

    @PersistenceContext(unitName = "comprofitsPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EmployeeFacade() {
        super(Employee.class);
    }

    public Employee getEmployeeByUsername(String username) {
        try {
            Employee result = (Employee) em.createNamedQuery("Employee.findByUsername")
                    .setParameter("username", username)
                    .getSingleResult();
            return result;
        }
        catch (NoResultException nre) {
            return null;
        }    
    }

    public List<Employee> getDepartmentEmployees(Department d) {
        return em.createQuery(
                "Select e From Employee e WHERE e.departmentIddepartment=:department").
                setParameter("department", d).getResultList();
    }

    public Employee getEmployeeByLnameFnameAndEmail(String l, String f, String e) {
        return (Employee) em.createQuery(
                "Select e From Employee e WHERE e.lastName=:lname AND e.firstName=:fname AND e.email=:email").
                setParameter("lname", l).
                setParameter("fname", f).
                setParameter("email", e).getSingleResult();
    }
}
