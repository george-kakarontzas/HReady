/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.comprofits.cdibeans.jobprofile;

import eu.comprofits.entities.jobprofile.CompetencesRequirement;
import eu.comprofits.entities.jobprofile.Job;
import eu.comprofits.session.jobprofile.CompetencesRequirementFacade;
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
public class UpdateCompetencesRequirementCDIBean implements Serializable {

    /**
     *
     * @author alexanderhoelzemann
     */
    @Named(value = "UpdateCompetencesRequirementCDIBean")
    @SessionScoped

    private static final long serialVersionUID = 1L;
    @EJB
    private CompetencesRequirementFacade competencesRequirementFacade;

    @EJB
    private JobFacade jobFacade;

    private CompetencesRequirement competencesRequirementObject;
    private List<CompetencesRequirement> competencesRequirementList;
    private List<Job> jobList;

    public UpdateCompetencesRequirementCDIBean() {
    }

    @PostConstruct
    public void init() {
        competencesRequirementList = competencesRequirementFacade.findAll();
        jobList = jobFacade.findAll();
    }

    public CompetencesRequirementFacade getCompetencesRequirementFacade() {
        return competencesRequirementFacade;
    }

    public void setCompetencesRequirementFacade(CompetencesRequirementFacade competencesRequirementFacade) {
        this.competencesRequirementFacade = competencesRequirementFacade;
    }

    public JobFacade getJobFacade() {
        return jobFacade;
    }

    public void setJobFacade(JobFacade jobFacade) {
        this.jobFacade = jobFacade;
    }

    public CompetencesRequirement getCompetencesRequirementObject() {
        return competencesRequirementObject;
    }

    public void setCompetencesRequirementObject(CompetencesRequirement competencesRequirementObject) {
        this.competencesRequirementObject = competencesRequirementObject;
    }

    public List<CompetencesRequirement> getCompetencesRequirementList() {
        return competencesRequirementList;
    }

    public void setCompetencesRequirementList(List<CompetencesRequirement> competencesRequirementList) {
        this.competencesRequirementList = competencesRequirementList;
    }

    public List<Job> getJobList() {
        return jobList;
    }

    public void setJobList(List<Job> jobList) {
        this.jobList = jobList;
    }

    public String edit(CompetencesRequirement competencesRequirement) {
        this.competencesRequirementObject = competencesRequirement;
        return "editCompetencesRequirement";
    }

    public String create() {
        this.competencesRequirementObject = new CompetencesRequirement();
        return "createCompetencesRequirement";
    }

    public void remove(CompetencesRequirement competencesRequirement) {
        try {
            competencesRequirementFacade.remove(competencesRequirement);
            competencesRequirementList = competencesRequirementFacade.findAll();

        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            e.getMessage(), null));
        }
    }

    public void update() {
        try {
            if (competencesRequirementObject.getIdcompetencesRequirement() == null) {
                competencesRequirementFacade.create(competencesRequirementObject);
            } else {
                competencesRequirementFacade.edit(competencesRequirementObject);
            }
            competencesRequirementList = competencesRequirementFacade.findAll();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            e.getMessage(), null));
        }
    }

}
