/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.comprofits.cdibeans.assessment;

import eu.comprofits.entities.employee.CurrentCompetenceAssessment;
import eu.comprofits.entities.employee.Employee;
import eu.comprofits.entities.main.Competence;
import eu.comprofits.session.employee.CurrentCompetenceAssessmentFacade;
import eu.comprofits.session.employee.EmployeeFacade;
import eu.comprofits.session.main.CompetenceFacade;
import java.io.Serializable;
import java.util.ArrayList;
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
@Named(value = "currentCompetenceAssessment")
@SessionScoped 
public class CurrentCompetenceAssessmentCDIBean implements Serializable {

    @EJB
    private CurrentCompetenceAssessmentFacade ccfacade;
    @EJB
    private EmployeeFacade emfacade;
    @EJB
    private CompetenceFacade comfacade;
    
    private List<CurrentCompetenceAssessment> assessments;
    private CurrentCompetenceAssessment assessment;
    private List<Employee> employees;
    private List<Competence> l3competences;
    
    public void refreshAssessments() {
        this.assessments = this.ccfacade.findAll();
    }
    
    @PostConstruct
    public void init() {
      this.refreshAssessments();
    }

    public List<CurrentCompetenceAssessment> getAssessments() {
        return assessments;
    }

    public void setAssessments(List<CurrentCompetenceAssessment> assessments) {
        this.assessments = assessments;
    }

    public CurrentCompetenceAssessment getAssessment() {
        return assessment;
    }

    public void setAssessment(CurrentCompetenceAssessment assessment) {
        this.assessment = assessment;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public List<Competence> getL3competences() {
        l3competences = new ArrayList<>();
        //Initialize the Level 3 competences list
        List<Competence> allCompetences = this.comfacade.findAll();
        for (Competence c : allCompetences) {
            if (c.getLevel() == 3) {
                l3competences.add(c);
            }
        }
        return l3competences;
    }

    
        
    public void remove(CurrentCompetenceAssessment d) {
        try {
            this.ccfacade.remove(d);
            this.refreshAssessments();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            e.getMessage(), null));
        }
    }

    public String edit(CurrentCompetenceAssessment assessment) {
        this.assessment = assessment;
        this.l3competences = this.getL3competences();
        this.employees = this.emfacade.findAll();
        return "editAssessment";
    }

    public String create() {
        this.assessment = new CurrentCompetenceAssessment();
        this.l3competences = this.getL3competences();
        this.employees = this.emfacade.findAll();
        return "editAssessment";
    }



    public String update() {
        try {
            if (this.assessment.getIdcurrentCompetenceAssessment() == null) {
                // check that this requirement does not already exist
                CurrentCompetenceAssessment cr = this.ccfacade.getAssessmentForEmployeeAndCompetence(this.assessment.getEmployeeIdemployee(),this.assessment.getCompetenceIdcompetence());
                if (cr != null) {
                 FacesContext context = FacesContext.getCurrentInstance();
                 ResourceBundle bundle = context.getApplication().getResourceBundle(context, "msgs");
                 String requirement_exists = 
                            bundle.getString("assessement_exists");
                 context.addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                           requirement_exists , null));
                 this.refreshAssessments();;
                 return "editAssessment";
                }
                this.ccfacade.create(this.assessment);

            } else {
                CurrentCompetenceAssessment cr = this.ccfacade.getAssessmentForEmployeeAndCompetence(this.assessment.getEmployeeIdemployee(),this.assessment.getCompetenceIdcompetence());
                if (cr != null && cr.getIdcurrentCompetenceAssessment() != this.assessment.getIdcurrentCompetenceAssessment()) {
                 FacesContext context = FacesContext.getCurrentInstance();
                 ResourceBundle bundle = context.getApplication().getResourceBundle(context, "msgs");
                 String requirement_exists = 
                            bundle.getString("assessement_exists");
                 context.addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                           requirement_exists , null));
                 this.refreshAssessments();
                 return "editAssessment";
                }
                this.ccfacade.edit(this.assessment);
            }
            this.refreshAssessments();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            e.getMessage(), null));
        }
        return "updateCurrentCompetenceAssessment";
    }
    
    
    
}
