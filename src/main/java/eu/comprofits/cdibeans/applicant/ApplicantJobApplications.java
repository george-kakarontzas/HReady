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

import eu.comprofits.entities.jobapplicant.ApplicantStudyRecord;
import eu.comprofits.entities.jobapplicant.JobAdvertisement;
import eu.comprofits.entities.jobapplicant.JobApplicant;
import eu.comprofits.entities.jobapplicant.JobApplication;
import eu.comprofits.session.jobapplicant.JobAdvertisementFacade;
import eu.comprofits.session.jobapplicant.JobApplicationFacade;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author ckopanos
 */
@Named(value = "applicantJobApplications")
@SessionScoped
public class ApplicantJobApplications implements Serializable {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    @EJB JobApplicationFacade jobApplicationFacade;
    private JobApplicant jobApplicant;
    private List<JobApplication> jobApplications; 
    private JobApplication jobApplication;
    
    
    @PostConstruct
    public void init() {
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        jobApplicant =  (JobApplicant) externalContext.getSessionMap().get("applicant");
    }

    private void refreshApplicationsList() {
        jobApplications = jobApplicationFacade.getApplicationsByApplicant(jobApplicant);
    }

    public List<JobApplication> getJobApplications() {
        // fetch applications here to be ok with addtions
        // or removals of application while the user is still logged in
        refreshApplicationsList();
        return jobApplications;
    }

    public JobApplication getJobApplication() {
        return jobApplication;
    }
    
    public String getCompanyName(JobAdvertisement item) {
        String name = "";
        try {
        name = item.getJobIdjob().getOrganisationalPositionIdorganisationalPosition().getCompanyIdcompany().getCompanyName1();
        } catch (NullPointerException e) {}
        return name;    
    }
    
    public void remove(JobApplication s) {
        try {
            this.jobApplicationFacade.remove(s);      
            this.refreshApplicationsList();
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            ex.getMessage(), null));
        }
    }
    
}


