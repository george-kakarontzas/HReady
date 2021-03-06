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
import eu.comprofits.entities.jobprofile.Job;
import eu.comprofits.entities.main.Department;
import eu.comprofits.session.jobapplicant.JobAdvertisementFacade;
import eu.comprofits.session.jobprofile.JobFacade;
import eu.comprofits.session.main.DepartmentFacade;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Named;

/**
 *
 * @author George Kakarontzas
 */
@Named(value = "updatePublishedAvailableJobsCDIBean")
@SessionScoped
              
public class updatePublishedAvailableJobsCDIBean implements Serializable {

    @EJB
    private JobAdvertisementFacade jobAdvertisementFacade;
    @EJB
    private DepartmentFacade departmentFacade;
    @EJB
    private JobFacade jobFacade;
    private JobAdvertisement jobAdvertisement;
    private List<JobAdvertisement> jobAdds;
    private Department selectedDepartment;
    private Job selectedJob;
    private List<Job> jobs;
    private List<Department> departments;
    /**
     * Creates a new instance of updateProExperienceCDIBean
     */
    public updatePublishedAvailableJobsCDIBean() {
    }

    @PostConstruct
    public void init() {
        refreshDepartmentsList();
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

    public Department getSelectedDepartment() {
        return selectedDepartment;
    }

    public void setSelectedDepartment(Department selectedDepartment) {
        this.selectedDepartment = selectedDepartment;
    }

    public List<Job> getJobs() {
        return jobs;
    }

    public void setJobs(List<Job> jobs) {
        this.jobs = jobs;
    }

    public Job getSelectedJob() {
        return selectedJob;
    }

    public void setSelectedJob(Job selectedJob) {
        this.selectedJob = selectedJob;
    }

    public List<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(List<Department> departments) {
        this.departments = departments;
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
        this.selectedJob = add.getJobIdjob();
        this.selectedDepartment = selectedJob.getDepartmentIddepartment();
        this.departments=departmentFacade.findAll();
        this.jobs=jobFacade.findByDepartment(selectedDepartment);
        return "editJobAdvertisement";
    }

    public String create() {
        this.jobAdvertisement
                = new JobAdvertisement();
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
    
    public void departmentValueChange(AjaxBehaviorEvent event) {
        jobs = jobFacade.findByDepartment(selectedDepartment);
        jobAdvertisement.setJobTitle("");
        jobAdvertisement.setJobDescription("");
        jobAdvertisement.setJobIdjob(null);
    }
    
    public void jobValueChange(AjaxBehaviorEvent event) {
        if (selectedJob!=null) {
            jobAdvertisement.setJobTitle(selectedJob.getJobTitle());
            jobAdvertisement.setJobDescription(selectedJob.getJobDescription());
            jobAdvertisement.setJobIdjob(selectedJob);
        }
        else {
            jobAdvertisement.setJobTitle("");
            jobAdvertisement.setJobDescription("");
            jobAdvertisement.setJobIdjob(null);
        }
        
    }

    private void refreshDepartmentsList() {
        departments=departmentFacade.findAll();
    }
}
