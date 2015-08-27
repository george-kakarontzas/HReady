/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.comprofits.cdibeans.main;

import eu.comprofits.cdibeans.main.CountryList;
import eu.comprofits.entities.jobapplicant.ApplicantProfessionalExperienceRecord;
import eu.comprofits.entities.jobapplicant.ApplicantStudyRecord;
import eu.comprofits.entities.jobapplicant.JobAdvertisement;
import eu.comprofits.entities.jobapplicant.JobApplicant;
import eu.comprofits.entities.jobapplicant.JobApplication;
import eu.comprofits.session.jobapplicant.JobAdvertisementFacade;
import eu.comprofits.session.jobapplicant.JobApplicationFacade;
import java.io.Serializable;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
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
@Named(value = "jobApplications")
@SessionScoped
public class JobApplicationsCDIBean implements Serializable {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    @EJB JobApplicationFacade jobApplicationFacade;
    @EJB JobAdvertisementFacade jobAdvertisementFacade;
    private JobApplicant jobApplicant;
    private List<JobApplication> jobApplications; 
    private JobApplication jobApplication;
    private JobAdvertisement jobAdvertisement;
    private List<JobAdvertisement> jobAdvertisements;
    private List<ApplicantStudyRecord> studies;
    private List<ApplicantProfessionalExperienceRecord> experienceRecords;
    
    
    @PostConstruct
    public void init() {
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        
    }

    
    private void refreshApplicationsList() {
        jobApplications = jobApplicationFacade.getOrderedApplications();
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

    public JobApplicant getJobApplicant() {
        return jobApplicant;
    }

    public void setJobApplicant(JobApplicant jobApplicant) {
        this.jobApplicant = jobApplicant;
    }

    public JobAdvertisement getJobAdvertisement() {
        return jobAdvertisement;
    }

    public void setJobAdvertisement(JobAdvertisement jobAdvertisement) {
        this.jobAdvertisement = jobAdvertisement;
    }

    public List<JobAdvertisement> getJobAdvertisements() {
        return this.jobAdvertisementFacade.getAvailableJobs();
    }

    public void setJobAdvertisements(List<JobAdvertisement> jobAdvertisements) {
        this.jobAdvertisements = jobAdvertisements;
    }
    
    
    public String viewApplication(JobApplication j) {
        this.jobApplication=j;
        this.jobApplicant = j.getJobApplicantIdjobApplicant();
        return "viewApplication";
    }
    
   public String getPhotoPath() {
        if (jobApplicant != null) {
            if (jobApplicant.getPhotoPath() != null) {
                return "/images/" + jobApplicant.getPhotoPath();
            }
        }
        return "/images/user.jpg";
    }
    
    public void applicationsFor(JobAdvertisement ja) {
        this.jobApplications = this.jobApplicationFacade.getOrderedApplications(ja);
    }
    
    
     public String getCountryName(String country) {
        Locale baselocale = new Locale("en", country);
        return baselocale.getDisplayCountry(FacesContext.getCurrentInstance().getViewRoot().getLocale());
    }
     
     public String getGender(int gender) {
        FacesContext context = FacesContext.getCurrentInstance();
        ResourceBundle bundle = context.getApplication().getResourceBundle(context, "msgs");
        if (gender==1) {
            return bundle.getString("male");
        }
        return bundle.getString("female");
     }
     
    public String getMaritalStatus(String status) {
        FacesContext context = FacesContext.getCurrentInstance();
        ResourceBundle bundle = context.getApplication().getResourceBundle(context, "msgs");
        if (status=="m") {
            return bundle.getString("married");
        }
        return bundle.getString("single");
     }

    public List<ApplicantStudyRecord> getStudies() {
        if (this.jobApplicant == null) {
            return null;
        }
        return (List<ApplicantStudyRecord>) this.jobApplicant.getApplicantStudyRecordCollection();
    }



    public List<ApplicantProfessionalExperienceRecord> getExperienceRecords() {
        if (this.jobApplicant == null) {
            return null;
        }
        return (List<ApplicantProfessionalExperienceRecord>) this.jobApplicant.getApplicantProfessionalExperienceRecordCollection();
    }


    
    
    
    
}


