/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package eu.comprofits.cdibeans.assessment;

import eu.comprofits.entities.assessment.Assessment;
import eu.comprofits.entities.assessment.EmployeeCompetenceAssessment;
import eu.comprofits.entities.assessment.Statement;
import eu.comprofits.entities.employee.Employee;
import eu.comprofits.entities.main.Competence;
import eu.comprofits.session.assessment.AssessmentFacade;
import eu.comprofits.session.assessment.EmployeeCompetenceAssessmentFacade;
import eu.comprofits.session.employee.EmployeeFacade;
import java.io.Serializable;
import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Named;

/**
 *
 * @author george
 */
@Named(value = "assessmentCDIBean")
@SessionScoped
public class AssessmentCDIBean implements Serializable {
    @EJB
    private EmployeeCompetenceAssessmentFacade employeeCompetenceAssessmentFacade;
    @EJB
    private EmployeeFacade employeeFacade;
    @EJB
    private AssessmentFacade assessmentFacade;
    private Assessment selectedAssessment;
    private Competence selectedCompetence;
    private Employee e;
    private List<Assessment> employeeAssessments;
    /**
     * Creates a new instance of AssessmentCDIBean
     */
    
    private Map<Competence, List<Statement>> statementsMap;
    
    public AssessmentCDIBean() {
    }
    
    @PostConstruct
    public void init() {
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        e = (Employee) externalContext.getSessionMap().get("user");
        if (e == null) {
            Principal principal
                    = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal();
            if (principal != null) {
                e = employeeFacade.getEmployeeByUsername(principal.getName()); // Find User by j_username.
            }
        }
        employeeAssessments=assessmentFacade.getEmployeeAssessments(e);
        statementsMap = new HashMap<>();
    }
    
    public boolean doAssessementsExist() {
        return !employeeAssessments.isEmpty();
    }

    public List<Assessment> getEmployeeAssessments() {
        return employeeAssessments;
    }

    public Assessment getSelectedAssessment() {
        return selectedAssessment;
    }

    public void setSelectedAssessment(Assessment selectedAssessment) {
        this.selectedAssessment = selectedAssessment;
    }

    public Competence getSelectedCompetence() {
        return selectedCompetence;
    }

    public void setSelectedCompetence(Competence selectedCompetence) {
        this.selectedCompetence = selectedCompetence;
    }  
    
    public List<Competence> getCompetences() {
        return new ArrayList<>(statementsMap.keySet());
    }
    
    public void assessmentValueChange(AjaxBehaviorEvent event) {
        statementsMap = new HashMap<>();
        List<EmployeeCompetenceAssessment> ecas = 
                employeeCompetenceAssessmentFacade.findAllForAssessmentAndEmployee(selectedAssessment, e);
        for (EmployeeCompetenceAssessment eca : ecas) {
            System.out.println(eca.toString());
            List<Statement> cStatements = statementsMap.get(eca.getCompetenceIdcompetence());
            if (cStatements==null) {
                cStatements = new ArrayList<>();
            }
            cStatements.add(eca.getStatementIdstatement());
            System.out.println("\t"+eca.getStatementIdstatement().toString());
            statementsMap.put(eca.getCompetenceIdcompetence(), cStatements);
        }
    }
    
    
}
