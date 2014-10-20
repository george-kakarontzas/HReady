/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.comprofits.cdibeans.jobprofile;

import eu.comprofits.entities.jobprofile.Job;
import eu.comprofits.entities.main.OrganisationalPosition;
import eu.comprofits.session.jobprofile.JobFacade;
import eu.comprofits.session.main.OrganisationalPositionFacade;
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
    @EJB
    private OrganisationalPositionFacade organisationalPositionFacade;

    /**
     *
     * @author alexanderhoelzemann
     */

    private static final long serialVersionUID = 1L;
    @EJB
    private JobFacade jobFacade;
    

    private Job jobObject;
    private List<Job> jobList;
    private List<Job> filteredJobList;
    private Job selectedJob;
    private List<OrganisationalPosition> positions;

    public UpdateJobCDIBean() {
    }

    @PostConstruct
    public void init() {
        jobObject = new Job();
        jobList = jobFacade.findAll();
        positions=organisationalPositionFacade.findAll();
    }

    public List<Job> getFilteredJobList() {
        return filteredJobList;
    }

    public void setFilteredJobList(List<Job> filteredJobList) {
        this.filteredJobList = filteredJobList;
    }
    
    public List<OrganisationalPosition> getPositions() {
        return positions;
    }

    public void setPositions(List<OrganisationalPosition> positions) {
        this.positions = positions;
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

    public Job getSelectedJob() {
        return selectedJob;
    }

    public void setSelectedJob(Job selectedJob) {
        this.selectedJob = selectedJob;
    }

    public String edit(Job job) {
        this.jobObject = job;
        return "editJobProfile";
    }

    public String create() {
        this.jobObject = new Job();
        return "createJobProfile";
    }

     private void refreshJobList() {
        jobList = jobFacade.findAll();
    }
    public String remove(Job e) {
        
        try {
        jobFacade.remove(e);
            if (filteredJobList != null) { 
                filteredJobList.remove(e);
            }
            refreshJobList();
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            ex.getMessage(), null));
        }
        return "updateJobProfile";
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
