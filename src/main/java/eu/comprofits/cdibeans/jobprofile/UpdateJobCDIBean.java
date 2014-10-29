/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.comprofits.cdibeans.jobprofile;

import eu.comprofits.entities.jobapplicant.JobAdvertisement;
import eu.comprofits.entities.jobprofile.Job;
import eu.comprofits.entities.main.OrganisationalPosition;
import eu.comprofits.session.jobapplicant.JobAdvertisementFacade;
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

    @EJB
    private JobAdvertisementFacade jobAdvertisementFacade;

    private JobAdvertisement jobAdvertisementObject;
    private List<JobAdvertisement> jobAdvertisementList;
    private Job jobObject;
    private List<Job> jobList;
    private List<Job> filteredJobList;
    private Job selectedJob;
    private List<OrganisationalPosition> positions;
    private OrganisationalPosition position;

    public UpdateJobCDIBean() {
    }

    @PostConstruct
    public void init() {
        jobObject = new Job();
        
        jobList = jobFacade.findAll();
        positions = organisationalPositionFacade.findAll();
       
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

    public JobAdvertisement getJobAdvertisementObject() {
        return jobAdvertisementObject;
    }

    public void setJobAdvertisementObject(JobAdvertisement jobAdvertisementObject) {
        this.jobAdvertisementObject = jobAdvertisementObject;
    }

    public List<JobAdvertisement> getJobAdvertisementList() {
        return jobAdvertisementList;
    }

    public void setJobAdvertisementList(List<JobAdvertisement> jobAdvertisementList) {
        this.jobAdvertisementList = jobAdvertisementList;
    }

    public String edit(Job job) {
        this.jobObject = job;
        return "editJobProfile";
    }

    public String create() {
        this.jobObject = new Job();
        return "createJobProfile";
    }

    public String saveAsNew() {
        this.jobObject = new Job();
        return "updateJobProfile";
    }

    private void refreshJobList() {
        jobList = jobFacade.findAll();
    }

    public JobAdvertisement getSpecificJobAdvertisement(Integer jobId) {

        JobAdvertisement jobAdvertisement = new JobAdvertisement();
        for (JobAdvertisement jobAd : jobAdvertisementList) {
            if (jobId.equals(jobAd.getJobIdjob().getIdjob())) {
                jobAdvertisement = jobAd;
            } else {
                jobAdvertisement = null;
            }
        }
        return jobAdvertisement;
    }

    public String getFieldOfResponsibilityForAJob(Integer jobId) {

        JobAdvertisement jobAdvertisement = new JobAdvertisement();
        for (JobAdvertisement jobAd : jobAdvertisementList) {
            if (jobId.equals(jobAd.getJobIdjob().getIdjob())) {
                jobAdvertisement = jobAd;
            }
        }
        return jobAdvertisement.getFieldsOfResponsibility();
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

                jobAdvertisementFacade.create(jobAdvertisementObject);
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
