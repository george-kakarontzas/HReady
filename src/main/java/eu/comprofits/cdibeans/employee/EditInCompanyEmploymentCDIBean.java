/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.comprofits.cdibeans.employee;

import eu.comprofits.entities.employee.Employee;
import eu.comprofits.entities.employee.InCompanyEmployment;
import eu.comprofits.entities.jobprofile.Job;
import eu.comprofits.session.employee.EmployeeFacade;
import eu.comprofits.session.employee.InCompanyEmploymentFacade;
import eu.comprofits.session.jobprofile.JobFacade;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author george
 */
@Named(value = "editInCompanyEmploymentCDIBean")
@RequestScoped
public class EditInCompanyEmploymentCDIBean {

    @EJB
    private InCompanyEmploymentFacade inCompanyEmploymentFacade;
    @EJB
    private JobFacade jobFacade;
    @EJB
    private EmployeeFacade employeeFacade;
  
    private Employee employee;
    private InCompanyEmployment inCompanyEmployment;
    private InCompanyEmployment oldCompanyEmployment;
    private List<InCompanyEmployment> oldEmployments;
    private List<Job> jobs;

    /**
     * Creates a new instance of EditInCompanyEmployment
     */
    public EditInCompanyEmploymentCDIBean() {
    }

    @PostConstruct
    public void init() {
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        employee = (Employee) externalContext.getSessionMap().get("employee");

        if (employee != null) {
            if (employee.getCurrentInCompanyEmploymentId() == null) {
                inCompanyEmployment = new InCompanyEmployment();
                inCompanyEmployment.setStartDate(new Date());
                inCompanyEmployment.setEmployeeIdemployee(employee);
                employee.setCurrentInCompanyEmploymentId(inCompanyEmployment);
            } else {
                oldCompanyEmployment = employee.getCurrentInCompanyEmploymentId();
                inCompanyEmployment = oldCompanyEmployment;
            }
        }
        jobs = jobFacade.findAll();
    }

    public InCompanyEmployment getInCompanyEmployment() {
        return inCompanyEmployment;
    }

    public InCompanyEmployment getOldCompanyEmployment() {
        return oldCompanyEmployment;
    }

    public List<InCompanyEmployment> getOldEmployments() {
        
        oldEmployments = inCompanyEmploymentFacade.getPastPositions(employee);
        return oldEmployments;
    }

    public Employee getEmployee() {
        if (employee == null) {
            System.out.println("NULL");
        }
        return employee;
    }

    public List<Job> getJobs() {
        return jobs;
    }

    public String update() {
        if (employee != null) {
            inCompanyEmployment.setEmployeeIdemployee(employee);
            employee.setCurrentInCompanyEmploymentId(inCompanyEmployment);
            //if the current employment is new create it
            if (inCompanyEmployment.getIdinCompanyEmployment() == null) {
                inCompanyEmploymentFacade.create(inCompanyEmployment);
            } else {
                inCompanyEmploymentFacade.edit(inCompanyEmployment);
                //TO-DO store as a different employement the old employement if the
                //user wishes to keep it
            }
            //update the employee in the database
            employeeFacade.edit(employee);
        }
        return "updateEmployeeProfile";
    }

}
