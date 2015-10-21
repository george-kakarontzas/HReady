/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.comprofits.cdibeans.applicant;

import eu.comprofits.cdibeans.employee.*;
import eu.comprofits.entities.employee.Employee;
import eu.comprofits.entities.employee.ProfessionalExperienceRecord;
import eu.comprofits.entities.jobapplicant.JobAdvertisement;
import eu.comprofits.session.employee.EmployeeFacade;
import eu.comprofits.session.employee.ProfessionalExperienceRecordFacade;
import eu.comprofits.session.jobapplicant.JobAdvertisementFacade;
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
@Named(value = "updatePublishedAvailableJobsCDIBean")
@SessionScoped
              
public class UpdatePublishedAvailableJobsCDIBean implements Serializable {

    @EJB
    private JobAdvertisementFacade jobAdvertisementFacade;
    private JobAdvertisement jobAdvertisement;
    private List<JobAdvertisement> jobAdds;

    /**
     * Creates a new instance of updateProExperienceCDIBean
     */
    public UpdatePublishedAvailableJobsCDIBean() {
    }

    @PostConstruct
    public void init() {
        refreshJobAddsList();
    }

    public List<JobAdvertisement> getJobAdds() {
        refreshJobAddsList();
        return jobAdds;
    }

    public JobAdvertisement getJobAdvertisement() {
        return jobAdvertisement;
    }

    public void setJobAdvertisement(JobAdvertisement jobAdvertisement) {
        this.jobAdvertisement = jobAdvertisement;
    }
    
    private void refreshJobAddsList() {
        jobAdds
                = jobAdvertisementFacade.findAll();
    }

    public void remove(JobAdvertisement add) {
        try {
            jobAdvertisementFacade.remove(add);
            refreshJobAddsList();
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            ex.getMessage(), null));
        }
    }

    public String edit(JobAdvertisement add) {
        this.jobAdvertisement = add;
        return "editJobAdvertisement";
    }

    public String create() {
        this.jobAdvertisement
                = new JobAdvertisement();
        //TO-DO: SET JOB of JOB ADVERTISEMENT HERE
        return "editJobAdvertisement";
    }

    public String update() {
        try {
            if (jobAdvertisement.getIdjobAdvertisement() == null) {
                jobAdvertisementFacade.create(jobAdvertisement);
            } else {
                jobAdvertisementFacade.edit(jobAdvertisement);
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            e.getMessage(), null));
        }
        refreshJobAddsList();
        return "upadatePubishedAvailableJobs";
    }
}
