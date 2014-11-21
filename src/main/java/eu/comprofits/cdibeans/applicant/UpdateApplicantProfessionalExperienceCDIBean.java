/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.comprofits.cdibeans.applicant;

import eu.comprofits.entities.jobapplicant.ApplicantProfessionalExperienceRecord;
import eu.comprofits.entities.jobapplicant.JobApplicant;
import eu.comprofits.session.jobapplicant.ApplicantProfessionalExperienceRecordFacade;
import java.io.Serializable;
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
 * @author ckopanos
 */
@Named(value = "updateApplicantProfessionalExperience")
@SessionScoped
public class UpdateApplicantProfessionalExperienceCDIBean implements Serializable {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    @EJB ApplicantProfessionalExperienceRecordFacade experienceRecordFacade;
    private List<ApplicantProfessionalExperienceRecord> experienceRecords;
    private JobApplicant jobApplicant;
    private ApplicantProfessionalExperienceRecord experienceRecord;

    public UpdateApplicantProfessionalExperienceCDIBean() {
    }

    public List<ApplicantProfessionalExperienceRecord> getExperienceRecords() {
        return experienceRecords;
    }

    public void setExperienceRecords(List<ApplicantProfessionalExperienceRecord> experienceRecords) {
        this.experienceRecords = experienceRecords;
    }

    public ApplicantProfessionalExperienceRecord getExperienceRecord() {
        return experienceRecord;
    }

    public void setExperienceRecord(ApplicantProfessionalExperienceRecord experienceRecord) {
        this.experienceRecord = experienceRecord;
    }
    
    @PostConstruct
    public void init() {
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        this.jobApplicant =  (JobApplicant) externalContext.getSessionMap().get("applicant");
        refreshJobsList();
    }

    private void refreshJobsList() {
        this.experienceRecords = experienceRecordFacade.findByApplicant(jobApplicant);
    }
    
    
    public void remove(ApplicantProfessionalExperienceRecord s) {
        try {
            experienceRecordFacade.remove(s);           
            refreshJobsList();
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            ex.getMessage(), null));
        }
    }

    public String edit(ApplicantProfessionalExperienceRecord s) {
        this.experienceRecord = s;
        return "editExperienceRecord";
    }


 
    
    public String create() {
        this.experienceRecord =
                new ApplicantProfessionalExperienceRecord();
        experienceRecord.setJobApplicantIdjobApplicant(jobApplicant);
        return "editExperienceRecord";
    }
   
    public String update() {
        try {
            if (experienceRecord.getIdapplicantProfessionalExperienceRecord()==null) {
               experienceRecordFacade.create(experienceRecord);
            } else {
                experienceRecordFacade.edit(experienceRecord);
            }           
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            e.getMessage(), null));
        }
        this.refreshJobsList();
        return "updateProfessionalExperience";
    }
    
}
