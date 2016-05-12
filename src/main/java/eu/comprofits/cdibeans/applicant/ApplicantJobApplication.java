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
package eu.comprofits.cdibeans.applicant;

import eu.comprofits.entities.jobapplicant.JobAdvertisement;
import eu.comprofits.entities.jobapplicant.JobApplicant;
import eu.comprofits.entities.jobapplicant.JobApplication;
import eu.comprofits.session.jobapplicant.JobAdvertisementFacade;
import eu.comprofits.session.jobapplicant.JobApplicationFacade;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author ckopanos
 */
@Named(value = "applicantJobApplication")
@SessionScoped
public class ApplicantJobApplication implements Serializable {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method"
    
    @EJB JobApplicationFacade jobApplicationFacade;
    @EJB JobAdvertisementFacade jobAdvertisementFacade;
    private JobApplicant jobApplicant;
    private List<JobAdvertisement> availableJobs; 
    private JobAdvertisement selectedJob;

    public ApplicantJobApplication() {
    }
    
    @PostConstruct
    public void init() {
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        jobApplicant =  (JobApplicant) externalContext.getSessionMap().get("applicant");
    }

    private void refreshJobsList() {
        availableJobs = jobAdvertisementFacade.getAvailableJobs();
    }
    public String getCompanyName(JobAdvertisement item) {
        String name = "";
        try {
        name = item.getJobIdjob().getOrganisationalPositionIdorganisationalPosition().getCompanyIdcompany().getCompanyName1();
        } catch (NullPointerException e) {}
        return name;    
    }
    public String getDepartmentName(JobAdvertisement item) {
        String name = "";
        try {
        name = item.getJobIdjob().getDepartmentIddepartment().getDepartmentName();
        } catch (NullPointerException e) {}
        return name;    
    }
    
    public String view(JobAdvertisement item) {
        selectedJob = item;
        if (this.hasAppliedForJob(selectedJob)) {
            FacesContext context = FacesContext.getCurrentInstance();
        ResourceBundle bundle = context.getApplication().getResourceBundle(context, "msgs");
        context.addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, bundle.getString("already_applied_for_job"),bundle.getString("already_applied_for_job")));
        }
        return "viewJobDetails.xhtml";
    }

    public JobApplicant getJobApplicant() {
        return jobApplicant;
    }

    public List<JobAdvertisement> getAvailableJobs() {
        // always fetch available jobs, here otherwise, changes
        // that may occur while the user is logged in will not be e.g. a new job
        // shown in his interface
        refreshJobsList();
        return availableJobs;
    }

    public JobAdvertisement getSelectedJob() {
        return selectedJob;
    }
    
    public String applyForJob() {
        // just to be certain with double attempts
        // e.g. user presses button twice
        if (this.hasAppliedForJob(selectedJob)) {
            return "viewJobDetails.xhtml";
        }
        JobApplication jobApplication = new JobApplication();
        jobApplication.setDate(new Date());
        jobApplication.setJobAdvertisementIdjobAdvertisement(selectedJob);
        jobApplication.setJobApplicantIdjobApplicant(jobApplicant);
        this.jobApplicationFacade.create(jobApplication);
        FacesContext context = FacesContext.getCurrentInstance();
        ResourceBundle bundle = context.getApplication().getResourceBundle(context, "msgs");
        context.addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, bundle.getString("succesfully_applied_for_job"),bundle.getString("succesfully_applied_for_job")));
        return "viewJobDetails.xhtml";
    }
    
    public Boolean hasAppliedForJob(JobAdvertisement job) {
        return this.jobApplicationFacade.getApplicationByJobAndApplication(job, jobApplicant) != null;
    }
    
    
    
    
}
