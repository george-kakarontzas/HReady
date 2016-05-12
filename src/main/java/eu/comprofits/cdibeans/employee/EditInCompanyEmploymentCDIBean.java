/* 
 * Copyright 2016 ComProFITS Project.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
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
import java.util.ResourceBundle;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author George Kakarontzas
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
    private List<Job> jobs;
    private InCompanyEmployment employmentBeforeChanges;
    private boolean savePreviousEmployment;
    private boolean renderPrevious;

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
                renderPrevious=false;
            } else {
                inCompanyEmployment = employee.getCurrentInCompanyEmploymentId();
                //keep old fields in a new object
                employmentBeforeChanges = new InCompanyEmployment();
                employmentBeforeChanges.setEmployeeIdemployee(inCompanyEmployment.getEmployeeIdemployee());
                employmentBeforeChanges.setEndDate(new Date());
                employmentBeforeChanges.setJobIdjob(inCompanyEmployment.getJobIdjob());
                employmentBeforeChanges.setStartDate(inCompanyEmployment.getStartDate());
                renderPrevious=true;
            }
        }
        jobs = jobFacade.findAll();
    }

    public InCompanyEmployment getEmploymentBeforeChanges() {
        return employmentBeforeChanges;
    }
    
    public InCompanyEmployment getInCompanyEmployment() {
        return inCompanyEmployment;
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

    public boolean isSavePreviousEmployment() {
        return savePreviousEmployment;
    }

    public void setSavePreviousEmployment(boolean savePreviousEmployment) {
        this.savePreviousEmployment = savePreviousEmployment;
    }

    public boolean isRenderPrevious() {
        return renderPrevious;
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
                if (savePreviousEmployment) {
                    employmentBeforeChanges.setDepartmentName(employee.getDepartmentIddepartment().getDepartmentName());
                    inCompanyEmploymentFacade.create(employmentBeforeChanges);
                    
                    FacesContext context = FacesContext.getCurrentInstance();
                    ResourceBundle bundle = context.getApplication().getResourceBundle(context, "msgs");
                    String str = bundle.getString("old_employment_was_saved");
                    FacesMessage message = new FacesMessage(str);
                    context.addMessage(null, message);
                }
                //update the employee in the database
                employeeFacade.edit(employee);
            }

        }
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        externalContext.getSessionMap().remove("employee");
        return "updateEmployeeProfile";
    }
}
