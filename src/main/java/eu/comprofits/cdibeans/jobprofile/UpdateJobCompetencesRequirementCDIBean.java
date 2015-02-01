/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.comprofits.cdibeans.jobprofile;

import eu.comprofits.cdibeans.main.*;
import eu.comprofits.entities.jobprofile.CompetencesRequirement;
import eu.comprofits.entities.jobprofile.Job;
import eu.comprofits.entities.main.Competence;
import eu.comprofits.session.jobprofile.CompetencesRequirementFacade;
import eu.comprofits.session.jobprofile.JobFacade;
import eu.comprofits.session.main.CompetenceFacade;
import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author ckopanos
 */
@Named(value = "updateJobCompetencesRequirementCDIBean")
@SessionScoped
public class UpdateJobCompetencesRequirementCDIBean implements Serializable {

   @EJB
   private CompetencesRequirementFacade crfacade;
   @EJB
   private JobFacade jobfacade;
   @EJB
   private CompetenceFacade comfacade;
   private CompetencesRequirement requirement;
   private List<CompetencesRequirement> requirements;
   private List<Competence> competences;
   private List<Job> jobs;
   private Job jobObject;

    public void setCrfacade(CompetencesRequirementFacade crfacade) {
        this.crfacade = crfacade;
    }

    public void setJobfacade(JobFacade jobfacade) {
        this.jobfacade = jobfacade;
    }

    public void setComfacade(CompetenceFacade comfacade) {
        this.comfacade = comfacade;
    }

    public void setJobObject(Job jobObject) {
        this.jobObject = jobObject;
    }
    
    public Job getJobObject() {
        return jobObject;
    }
   
    @PostConstruct
    public void init() {
        refreshRequirements();
    }
   
   public void refreshRequirements() {
      this.requirements = this.crfacade.getRequirementsForJob(jobObject);
   }
   
    public CompetencesRequirement getRequirement() {
        return requirement;
    }
    
    public void setRequirement(CompetencesRequirement requirement) {
        this.requirement = requirement;
    }

    public List<CompetencesRequirement> getRequirements() {
        return requirements;
    }

    public void setRequirements(List<CompetencesRequirement> requirements) {
        this.requirements = requirements;
    }

    public List<Competence> getCompetences() {
        return competences;
    }

    public void setCompetences(List<Competence> competences) {
        this.competences = competences;
    }

    public List<Job> getJobs() {
        return jobs;
    }

    public void setJobs(List<Job> jobs) {
        this.jobs = jobs;
    }
    
    
    public void remove(CompetencesRequirement d) {
        try {
            this.crfacade.remove(d);
            this.refreshRequirements();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            e.getMessage(), null));
        }
    }

    public String edit(CompetencesRequirement requirement) {
        this.requirement = requirement;
        this.competences = this.comfacade.getOrderedCompetences();
        this.jobs = this.jobfacade.findAll();
        return "editJobRequirement";
    }

    public String create() {
        this.requirement = new CompetencesRequirement();
        this.requirement.setJobIdjob(jobObject);
        this.competences = this.comfacade.getOrderedCompetences();
        this.jobs = this.jobfacade.findAll();
        return "editJobRequirement";
    }

    public String update() {
        try {
            if (this.requirement.getIdcompetencesRequirement() == null) {
                // check that this requirement does not already exist
                CompetencesRequirement cr = this.crfacade.getRequirementForJobAndCompetence(this.requirement.getJobIdjob(), this.requirement.getCompetenceIdcompetence());
                if (cr != null) {
                 FacesContext context = FacesContext.getCurrentInstance();
                 ResourceBundle bundle = context.getApplication().getResourceBundle(context, "msgs");
                 String requirement_exists = 
                            bundle.getString("requirement_exists");
                 context.addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                           requirement_exists , null));
                 this.refreshRequirements();
                 return "editJobRequirement";
                }
                this.crfacade.create(this.requirement);

            } else {
                CompetencesRequirement cr = this.crfacade.getRequirementForJobAndCompetence(this.requirement.getJobIdjob(), this.requirement.getCompetenceIdcompetence());
                if (cr != null && cr.getIdcompetencesRequirement() != this.requirement.getIdcompetencesRequirement()) {
                 FacesContext context = FacesContext.getCurrentInstance();
                 ResourceBundle bundle = context.getApplication().getResourceBundle(context, "msgs");
                 String requirement_exists = 
                            bundle.getString("requirement_exists");
                 context.addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                           requirement_exists , null));
                 this.refreshRequirements();
                 return "editJobRequirement";
                }
                this.crfacade.edit(this.requirement);
            }
            this.refreshRequirements();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            e.getMessage(), null));
        }
        return "updateJobCompetencesRequirement";
    }
    
    public String linkIt(Job jobObject)
    {
        this.jobObject=jobObject;
        refreshRequirements();
        return "updateJobCompetencesRequirement";
    }
}
