/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.comprofits.session.employee;

import eu.comprofits.entities.employee.CurrentCompetenceAssessment;
import eu.comprofits.entities.employee.Employee;
import eu.comprofits.entities.main.Competence;
import eu.comprofits.entities.main.Department;
import eu.comprofits.session.AbstractFacade;
import eu.comprofits.session.main.CompetenceFacade;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
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
public class EmployeeFacade extends AbstractFacade<Employee> {

    @PersistenceContext(unitName = "comprofitsPU")
    private EntityManager em;

    @EJB
    private CurrentCompetenceAssessmentFacade ccfacade;
    @EJB
    private CompetenceFacade comfacade;

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
        } catch (NoResultException nre) {
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

    public Employee getEmployeeById(Integer id) {
        return (Employee) em.createQuery("Select e From Employee e WHERE e.idemployee=:lid").setParameter("lid", id).getSingleResult();
    }

    public Employee getEmployeeByFullName(String name) {
        String[] names = name.split("\\s+");
        return (Employee) em.createQuery(
                "Select e From Employee e WHERE e.lastName=:lname AND e.firstName=:fname").
                setParameter("lname", names[1]).
                setParameter("fname", names[0]).getSingleResult();
    }

    public List<Employee> getEmployeesForEvaluation() {
        Query q = em.createQuery(
                "Select e From Employee e WHERE e.idemployee IN (SELECT c.employeeIdemployee.idemployee FROM CurrentCompetenceAssessment c)");
        return q.getResultList();
    }

    @Override
    public void create(Employee entity) {
        if (entity.getIsActive() == null) {
            entity.setIsActive(Boolean.TRUE);
        }
        super.create(entity); //To change body of generated methods, choose Tools | Templates.
    }

    public Boolean hasUniqueIdentityCard(Employee emp) {

        Query q = em.createQuery("Select e From Employee e WHERE e.identityCardNumber IS NOT NULL AND e.identityCardNumber=:cardnumber");
        if (emp.getIdemployee() != null) {
            q = em.createQuery("Select e From Employee e WHERE e.identityCardNumber IS NOT NULL AND e.identityCardNumber=:cardnumber AND e.idemployee!=:employee_id");
            q.setParameter("employee_id", emp.getIdemployee());
        }
        q.setParameter("cardnumber", emp.getIdentityCardNumber());
        return q.getResultList().isEmpty() ? true : false;

    }

    public Boolean hasUniqueSocialNumber(Employee emp) {

        Query q = em.createQuery("Select e From Employee e WHERE e.socialSecurityNumber=:socialnumber");
        if (emp.getIdemployee() != null) {
            q = em.createQuery("Select e From Employee e WHERE e.socialSecurityNumber=:socialnumber AND e.idemployee!=:employee_id");
            q.setParameter("employee_id", emp.getIdemployee());
        }
        q.setParameter("socialnumber", emp.getSocialSecurityNumber());
        return q.getResultList().isEmpty() ? true : false;

    }

    public List<Employee> getDepartmentEmployeesForEvaluation(Department d) {
        List<Employee> demp = em.createQuery(
                "Select e From Employee e WHERE e.departmentIddepartment=:department").
                setParameter("department", d).getResultList();
        //for each one of the employees see if she/he has a complete assessment profile
        List<Competence> l3competences = new ArrayList<>();
        //Initialize the Level 3 competences list
        List<Competence> allCompetences = comfacade.findAll();
        for (Competence c : allCompetences) {
            if (c.getLevel() == 3) {
                l3competences.add(c);
            }
        }
        List<Employee> readyEmployees = new ArrayList<>();
        for (Employee e : demp) {
            boolean complete=true;
            for (Competence c : l3competences) {
                CurrentCompetenceAssessment cca = ccfacade.getAssessmentForEmployeeAndCompetence(e, c);
                if (cca==null) { 
                    complete=false;
                    break;
                }
            }
            if (complete) readyEmployees.add(e);
        }
        return readyEmployees;
    }

}
