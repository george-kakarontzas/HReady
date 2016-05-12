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
import eu.comprofits.entities.employee.ProfessionalExperienceRecord;
import eu.comprofits.session.employee.EmployeeFacade;
import eu.comprofits.session.employee.ProfessionalExperienceRecordFacade;
import java.io.Serializable;
import java.security.Principal;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author George Kakarontzas
 */
@Named(value = "updateProExperienceCDIBean")
@SessionScoped
public class updateProExperienceCDIBean implements Serializable {

    @EJB
    private ProfessionalExperienceRecordFacade professionalExperienceRecordFacade;
    @EJB
    private EmployeeFacade employeeFacade;
    private ProfessionalExperienceRecord professionalExperienceRec;
    private List<ProfessionalExperienceRecord> prorecs;

    /**
     * Creates a new instance of updateProExperienceCDIBean
     */
    public updateProExperienceCDIBean() {
    }

    @PostConstruct
    public void init() {
        refreshProRecsList();
    }

    private Employee getCurrentEmployee() {
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        Employee e = (Employee) externalContext.getSessionMap().get("user");
        if (e == null) {
            Principal principal
                    = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal();
            if (principal != null) {
                e = employeeFacade.getEmployeeByUsername(principal.getName()); // Find User by j_username.
            }
        }
        return e;
    }

    public Employee getEmployee() {
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        Employee employee = (Employee) externalContext.getSessionMap().get("employee");
        if (employee == null) {
            employee = this.getCurrentEmployee();
        }
        return employee;
    }

    public List<ProfessionalExperienceRecord> getProrecs() {
        refreshProRecsList();
        return prorecs;
    }

    public ProfessionalExperienceRecord getProfessionalExperienceRec() {
        return professionalExperienceRec;
    }

    public void setProfessionalExperienceRec(ProfessionalExperienceRecord professionalExperienceRec) {
        this.professionalExperienceRec = professionalExperienceRec;
    }

    private void refreshProRecsList() {
        prorecs
                = professionalExperienceRecordFacade.getProRecsForEmployee(this.getEmployee());
    }

    public void remove(ProfessionalExperienceRecord p) {
        try {
            professionalExperienceRecordFacade.remove(p);
            refreshProRecsList();
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            ex.getMessage(), null));
        }
    }

    public String edit(ProfessionalExperienceRecord p) {
        this.professionalExperienceRec = p;
        return "editProfessionalExperienceRecord";
    }

    public String create() {
        this.professionalExperienceRec
                = new ProfessionalExperienceRecord();
        professionalExperienceRec.setEmployeeIdemployee(this.getEmployee());
        return "editProfessionalExperienceRecord";
    }

    public String update() {
        try {
            if (professionalExperienceRec.getIdprofessionalExperienceRecord() == null) {
                professionalExperienceRecordFacade.create(professionalExperienceRec);
            } else {
                professionalExperienceRecordFacade.edit(professionalExperienceRec);
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            e.getMessage(), null));
        }
        refreshProRecsList();
        return "updateProExperience";
    }
}
