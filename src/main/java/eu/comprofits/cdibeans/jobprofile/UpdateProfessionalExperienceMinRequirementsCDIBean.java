/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.comprofits.cdibeans.jobprofile;

import eu.comprofits.entities.jobprofile.ProfessionalExperienceMinRequirements;
import eu.comprofits.entities.jobprofile.Job;
import eu.comprofits.session.jobprofile.ProfessionalExperienceMinRequirementsFacade;
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
public class UpdateProfessionalExperienceMinRequirementsCDIBean implements Serializable {

    /**
     *
     * @author alexanderhoelzemann
     */
    @Named(value = "UpdateProfessionalExperienceMinRequirementsCDIBean")
    @SessionScoped

    private static final long serialVersionUID = 1L;
    @EJB
    private ProfessionalExperienceMinRequirementsFacade professionalExperienceMinRequirementsFacade;

    @EJB
    private JobFacade jobFacade;

    private ProfessionalExperienceMinRequirements professionalExperienceMinRequirementsObject;
    private List<ProfessionalExperienceMinRequirements> professionalExperienceMinRequirementsList;
    private List<Job> jobList;

    public UpdateProfessionalExperienceMinRequirementsCDIBean() {
    }

    @PostConstruct
    public void init() {
        professionalExperienceMinRequirementsList = professionalExperienceMinRequirementsFacade.findAll();
        jobList = jobFacade.findAll();
    }

    public ProfessionalExperienceMinRequirementsFacade getProfessionalExperienceMinRequirementsFacade() {
        return professionalExperienceMinRequirementsFacade;
    }

    public void setProfessionalExperienceMinRequirementsFacade(ProfessionalExperienceMinRequirementsFacade professionalExperienceMinRequirementsFacade) {
        this.professionalExperienceMinRequirementsFacade = professionalExperienceMinRequirementsFacade;
    }

    public JobFacade getJobFacade() {
        return jobFacade;
    }

    public void setJobFacade(JobFacade jobFacade) {
        this.jobFacade = jobFacade;
    }

    public ProfessionalExperienceMinRequirements getProfessionalExperienceMinRequirementsObject() {
        return professionalExperienceMinRequirementsObject;
    }

    public void setProfessionalExperienceMinRequirementsObject(ProfessionalExperienceMinRequirements professionalExperienceMinRequirementsObject) {
        this.professionalExperienceMinRequirementsObject = professionalExperienceMinRequirementsObject;
    }

    public List<ProfessionalExperienceMinRequirements> getProfessionalExperienceMinRequirementsList() {
        return professionalExperienceMinRequirementsList;
    }

    public void setProfessionalExperienceMinRequirementsList(List<ProfessionalExperienceMinRequirements> professionalExperienceMinRequirementsList) {
        this.professionalExperienceMinRequirementsList = professionalExperienceMinRequirementsList;
    }

    public List<Job> getJobList() {
        return jobList;
    }

    public void setJobList(List<Job> jobList) {
        this.jobList = jobList;
    }

    public String edit(ProfessionalExperienceMinRequirements professionalExperienceMinRequirements) {
        this.professionalExperienceMinRequirementsObject = professionalExperienceMinRequirements;
        return "editProfessionalExperienceMinRequirements";
    }

    public String create() {
        this.professionalExperienceMinRequirementsObject = new ProfessionalExperienceMinRequirements();
        return "createProfessionalExperienceMinRequirements";
    }

    public void remove(ProfessionalExperienceMinRequirements professionalExperienceMinRequirements) {
        try {
            professionalExperienceMinRequirementsFacade.remove(professionalExperienceMinRequirements);
            professionalExperienceMinRequirementsList = professionalExperienceMinRequirementsFacade.findAll();

        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            e.getMessage(), null));
        }
    }

    public void update() {
        try {
            if (professionalExperienceMinRequirementsObject.getIdprofessionalExperienceMinRequirements() == null) {
                professionalExperienceMinRequirementsFacade.create(professionalExperienceMinRequirementsObject);
            } else {
                professionalExperienceMinRequirementsFacade.edit(professionalExperienceMinRequirementsObject);
            }
            professionalExperienceMinRequirementsList = professionalExperienceMinRequirementsFacade.findAll();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            e.getMessage(), null));
        }
    }

}
