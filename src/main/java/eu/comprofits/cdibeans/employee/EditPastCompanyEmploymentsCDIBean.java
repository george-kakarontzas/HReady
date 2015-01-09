/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.comprofits.cdibeans.employee;

import eu.comprofits.entities.employee.Employee;
import eu.comprofits.entities.employee.InCompanyEmployment;
import eu.comprofits.entities.jobprofile.Job;
import eu.comprofits.session.employee.InCompanyEmploymentFacade;
import eu.comprofits.session.jobprofile.JobFacade;
import java.io.File;
import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author george
 */
@Named(value = "editPastCompanyEmploymentsCDIBean")
@RequestScoped
public class EditPastCompanyEmploymentsCDIBean {

    @EJB
    private InCompanyEmploymentFacade inCompanyEmploymentFacade;
    @EJB
    private JobFacade jobFacade;

    private List<InCompanyEmployment> oldEmployments;
    private List<Job> jobs;
    private Employee employee;

    /**
     * Creates a new instance of EditPastCompanyEmploymentsCDIBean
     */
    public EditPastCompanyEmploymentsCDIBean() {
    }

    @PostConstruct
    public void init() {
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        employee = (Employee) externalContext.getSessionMap().get("employee");
        oldEmployments = inCompanyEmploymentFacade.getPastPositions(employee);
        jobs = jobFacade.findAll();
    }

    public List<InCompanyEmployment> getOldEmployments() {
        //
        return oldEmployments;
    }

    public List<Job> getJobs() {
        return jobs;
    }

    public void onRowEdit(RowEditEvent event) {
        InCompanyEmployment i = (InCompanyEmployment) event.getObject();
        inCompanyEmploymentFacade.edit(i);
        FacesContext context = FacesContext.getCurrentInstance();
        ResourceBundle bundle = context.getApplication().getResourceBundle(context, "msgs");
        FacesMessage msg = new FacesMessage(bundle.getString("past_employment_edited"),
                "" + ((InCompanyEmployment) event.getObject()).getIdinCompanyEmployment());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onRowCancel(RowEditEvent event) {
        FacesContext context = FacesContext.getCurrentInstance();
        ResourceBundle bundle = context.getApplication().getResourceBundle(context, "msgs");

        FacesMessage msg = new FacesMessage(bundle.getString("employment_edit_canceled"),
                "" + ((InCompanyEmployment) event.getObject()).getIdinCompanyEmployment());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void remove(InCompanyEmployment i) {
        try {
            inCompanyEmploymentFacade.remove(i);
            oldEmployments = inCompanyEmploymentFacade.getPastPositions(employee);
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            ex.getMessage(), null));
        }
    }

}
