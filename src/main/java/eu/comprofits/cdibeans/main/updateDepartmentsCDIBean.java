/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.comprofits.cdibeans.main;

import eu.comprofits.entities.employee.Employee;
import eu.comprofits.entities.jobprofile.Division;
import eu.comprofits.entities.main.Company;
import eu.comprofits.entities.main.Department;
import eu.comprofits.session.employee.EmployeeFacade;
import eu.comprofits.session.jobprofile.DivisionFacade;
import eu.comprofits.session.main.CompanyFacade;
import eu.comprofits.session.main.DepartmentFacade;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author george
 */
@Named(value = "updateDepartmentsCDIBean")
@SessionScoped
public class updateDepartmentsCDIBean implements Serializable {

    private static final long serialVersionUID = 1L;
    @EJB
    private DivisionFacade divisionFacade;

    @EJB
    private DepartmentFacade departmentFacade;

    @EJB
    private EmployeeFacade employeeFacade;

    private Department department;
    private List<Department> departments;
    private List<Division> divisions;

    /**
     * Creates a new instance of UpdateDepartmentsCDIBean
     */
    public updateDepartmentsCDIBean() {
    }

    @PostConstruct
    public void init() {
        departments = departmentFacade.findAll();
        divisions = divisionFacade.findAll();
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public List<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(List<Department> departments) {
        this.departments = departments;
    }

    public List<Division> getDivisions() {
        //return divisions;
        divisions = divisionFacade.findAll();
        return divisions;
    }

    public void setDivisions(List<Division> divisions) {
        this.divisions = divisions;
    }

    public void remove(Department d) {
        try {
            departmentFacade.remove(d);
            departments = departmentFacade.findAll();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            e.getMessage(), null));
        }
    }

    public String edit(Department department) {
        this.department = department;
        return "editDepartment";
    }

    public String create() {
        this.department = new Department();
        return "editDepartment";
    }

    public List<Employee> getDepartmentEmployees() {
        return employeeFacade.getDepartmentEmployees(department);
    }

    public String update() {
        try {
            if (department.getIddepartment() == null) {
                departmentFacade.create(department);
            } else {
                departmentFacade.edit(department);
            }
            departments = departmentFacade.findAll();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            e.getMessage(), null));
        }
        return "updateDepartments";
    }

}
