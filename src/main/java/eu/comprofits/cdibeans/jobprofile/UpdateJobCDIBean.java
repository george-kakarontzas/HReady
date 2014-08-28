/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.comprofits.cdibeans.jobprofile;

import eu.comprofits.entities.jobprofile.Job;
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
@Named(value = "updateJobCDIBean")
@SessionScoped

public class UpdateJobCDIBean implements Serializable {

    /**
     *
     * @author alexanderhoelzemann
     */

    private static final long serialVersionUID = 1L;
    @EJB
    private JobFacade jobFacade;

    private Job jobObject;
    private List<Job> jobList;

    public UpdateJobCDIBean() {
    }

    @PostConstruct
    public void init() {
        jobList = jobFacade.findAll();
    }

    public JobFacade getJobFacade() {
        return jobFacade;
    }

    public void setJobFacade(JobFacade jobFacade) {
        this.jobFacade = jobFacade;
    }

    public Job getJobObject() {
        return jobObject;
    }

    public void setJobObject(Job jobObject) {
        this.jobObject = jobObject;
    }

    public List<Job> getJobList() {
        return jobList;
    }

    public void setJobList(List<Job> jobList) {
        this.jobList = jobList;
    }

    public String edit(Job job) {
        this.jobObject = job;
        return "editJob";
    }

    public String create() {
        this.jobObject = new Job();
        return "createJob";
    }

    public void remove(Job job) {
        try {
            jobFacade.remove(job);
            jobList = jobFacade.findAll();

        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            e.getMessage(), null));
        }
    }

    public void update() {
        try {
            if (jobObject.getIdjob() == null) {
                jobFacade.create(jobObject);
            } else {
                jobFacade.edit(jobObject);
            }
            jobList = jobFacade.findAll();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            e.getMessage(), null));
        }
    }

}
