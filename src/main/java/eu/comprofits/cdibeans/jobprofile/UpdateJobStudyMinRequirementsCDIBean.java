/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.comprofits.cdibeans.jobprofile;

import eu.comprofits.entities.jobprofile.JobStudyMinRequirements;
import eu.comprofits.entities.jobprofile.Job;
import eu.comprofits.session.jobprofile.JobStudyMinRequirementsFacade;
import eu.comprofits.session.jobprofile.JobFacade;
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
 * @author alexanderhoelzemann
 */
@Named(value = "UpdateJobStudyMinRequirementsCDIBean")
@SessionScoped
public class UpdateJobStudyMinRequirementsCDIBean implements Serializable {

    private static final long serialVersionUID = 1L;
    @EJB
    private JobStudyMinRequirementsFacade jobStudyMinRequirementsFacade;

    @EJB
    private JobFacade jobFacade;

    private JobStudyMinRequirements jobStudyMinRequirementsObject;
    private List<JobStudyMinRequirements> jobStudyMinRequirementsList;
    private List<Job> jobList;

    public UpdateJobStudyMinRequirementsCDIBean() {
    }

    @PostConstruct
    public void init() {
        jobStudyMinRequirementsList = jobStudyMinRequirementsFacade.findAll();
        jobList = jobFacade.findAll();
    }

    public JobStudyMinRequirementsFacade getJobStudyMinRequirementsFacade() {
        return jobStudyMinRequirementsFacade;
    }

    public void setJobStudyMinRequirementsFacade(JobStudyMinRequirementsFacade jobStudyMinRequirementsFacade) {
        this.jobStudyMinRequirementsFacade = jobStudyMinRequirementsFacade;
    }

    public JobFacade getJobFacade() {
        return jobFacade;
    }

    public void setJobFacade(JobFacade jobFacade) {
        this.jobFacade = jobFacade;
    }

    public JobStudyMinRequirements getJobStudyMinRequirementsObject() {
        return jobStudyMinRequirementsObject;
    }

    public void setJobStudyMinRequirementsObject(JobStudyMinRequirements jobStudyMinRequirementsObject) {
        this.jobStudyMinRequirementsObject = jobStudyMinRequirementsObject;
    }

    public List<JobStudyMinRequirements> getJobStudyMinRequirementsList() {
        return jobStudyMinRequirementsList;
    }

    public void setJobStudyMinRequirementsList(List<JobStudyMinRequirements> jobStudyMinRequirementsList) {
        this.jobStudyMinRequirementsList = jobStudyMinRequirementsList;
    }

    public List<Job> getJobList() {
        return jobList;
    }

    public void setJobList(List<Job> jobList) {
        this.jobList = jobList;
    }

    public String edit(JobStudyMinRequirements jobStudyMinRequirements) {
        this.jobStudyMinRequirementsObject = jobStudyMinRequirements;
        return "editJobStudyMinRequirements";
    }

    public String create() {
        this.jobStudyMinRequirementsObject = new JobStudyMinRequirements();
        return "createJobStudyMinRequirements";
    }

    public void remove(JobStudyMinRequirements jobStudyMinRequirements) {
        try {
            jobStudyMinRequirementsFacade.remove(jobStudyMinRequirements);
            jobStudyMinRequirementsList = jobStudyMinRequirementsFacade.findAll();

        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            e.getMessage(), null));
        }
    }

    public void update() {
        try {
            if (jobStudyMinRequirementsObject.getIdjobStudyMinRequirements() == null) {
                jobStudyMinRequirementsFacade.create(jobStudyMinRequirementsObject);
            } else {
                jobStudyMinRequirementsFacade.edit(jobStudyMinRequirementsObject);
            }
            jobStudyMinRequirementsList = jobStudyMinRequirementsFacade.findAll();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            e.getMessage(), null));
        }
    }

}
