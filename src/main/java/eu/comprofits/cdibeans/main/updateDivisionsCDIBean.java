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
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author george
 */
@Named(value = "updateDivisionsCDIBean")
@SessionScoped
public class updateDivisionsCDIBean implements Serializable {

    private static final long serialVersionUID = 1L;
    @EJB
    private DivisionFacade divisionFacade;
    @EJB
    private CompanyFacade companyFacade;
    @EJB
    private DepartmentFacade departmentFacade;

    private Division division;
    private List<Division> divisions;
    private List<Company> companies;
    /**
     * Creates a new instance of UpdateDepartmentsCDIBean
     */
    public updateDivisionsCDIBean() {
    }

    @PostConstruct
    public void init() {
        divisions = divisionFacade.findAll();
        companies = companyFacade.findAll();
    }

    public Division getDivision() {
        return division;
    }

    public void setDivision(Division division) {
        this.division = division;
    }

    public List<Division> getDivisions() {
        return divisions;
    }

    public void setDivisions(List<Division> divisions) {
        this.divisions = divisions;
    }
    public List<Company> getCompanies() {
        return companies;
    }

    public void setCompanies(List<Company> companies) {
        this.companies = companies;
    }
    public void remove(Division division) {
        try {
            divisionFacade.remove(division);
            divisions = divisionFacade.findAll();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            e.getMessage(), null));
        }
    }

    public String edit(Division division) {
        this.division = division;
        return "editDivision";
    }

    public String create() {
        this.division = new Division();
        return "editDivision";
    }

    public List<Department> getDivisionDepartments() {
        return departmentFacade.findDepartmenstForDivision(division);
    }

    public String update() {
        try {
            if (division.getIddivision() == null) {
                divisionFacade.create(division);
            } else {
                divisionFacade.edit(division);
            }
            divisions = divisionFacade.findAll();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            e.getMessage(), null));
        }
        return "updateDivisions";
    }

}
