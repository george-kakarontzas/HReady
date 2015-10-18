/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
 * @author george
 */
@Named(value = "updateProExperienceCDIBean")
@SessionScoped
public class updateProExperienceCDIBean implements Serializable {

    @EJB
    private ProfessionalExperienceRecordFacade professionalExperienceRecordFacade;
    @EJB
    private EmployeeFacade employeeFacade;

    private Employee employee;
    private ProfessionalExperienceRecord professionalExperienceRec;
    private List<ProfessionalExperienceRecord> prorecs;

    /**
     * Creates a new instance of updateProExperienceCDIBean
     */
    public updateProExperienceCDIBean() {
    }

    @PostConstruct
    public void init() {
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        employee = (Employee) externalContext.getSessionMap().get("employee");
        if (employee == null) {
            employee = this.getCurrentEmployee();
        }
        refreshProRecsList();
    }

    public Employee getCurrentEmployee() {
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
        return employee;
    }

    public List<ProfessionalExperienceRecord> getProrecs() {
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
                = professionalExperienceRecordFacade.getProRecsForEmployee(employee);
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
        professionalExperienceRec.setEmployeeIdemployee(employee);
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
