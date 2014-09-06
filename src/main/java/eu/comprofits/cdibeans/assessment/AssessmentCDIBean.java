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
import java.util.ResourceBundle;
import java.util.Set;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Named;
import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;

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
    private Employee employee;
    private List<Assessment> employeeAssessments;
    private List<EmployeeCompetenceAssessment> employeeCompetenceAssessments;
    private List<EmployeeCompetenceAssessment> filteredEmployeeCompetenceAssessments;

    public AssessmentCDIBean() {
    }

    @PostConstruct
    public void init() {
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        employee = (Employee) externalContext.getSessionMap().get("user");
        if (employee == null) {
            Principal principal
                    = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal();
            if (principal != null) {
                employee = employeeFacade.getEmployeeByUsername(principal.getName()); // Find User by j_username.
            }
        }
        employeeAssessments = assessmentFacade.getEmployeeAssessments(employee);
        employeeCompetenceAssessments = new ArrayList<>();
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

    public List<EmployeeCompetenceAssessment> getEmployeeCompetenceAssessments() {
        return employeeCompetenceAssessments;
    }

    public void setEmployeeCompetenceAssessments(List<EmployeeCompetenceAssessment> employeeCompetenceAssessments) {
        this.employeeCompetenceAssessments = employeeCompetenceAssessments;
    }

    public List<EmployeeCompetenceAssessment> getFilteredEmployeeCompetenceAssessments() {
        return filteredEmployeeCompetenceAssessments;
    }

    public void setFilteredEmployeeCompetenceAssessments(List<EmployeeCompetenceAssessment> filteredEmployeeCompetenceAssessments) {
        this.filteredEmployeeCompetenceAssessments = filteredEmployeeCompetenceAssessments;
    }

    public List<Competence> getAssessmentCompetences() {
        List<Competence> competences = new ArrayList<>();
        for (EmployeeCompetenceAssessment eca : employeeCompetenceAssessments) {
            Competence c = eca.getCompetenceIdcompetence();
            if (!competences.contains(c)) {
                competences.add(c);
            }
        }
        return competences;
    }

    public void onRowEdit(RowEditEvent event) {
        EmployeeCompetenceAssessment modified
                = (EmployeeCompetenceAssessment) event.getObject();
        FacesContext context = FacesContext.getCurrentInstance();
        ResourceBundle bundle = context.getApplication().getResourceBundle(context, "msgs");
        try {
            employeeCompetenceAssessmentFacade.edit(modified);
        } catch (Exception ex) {
            FacesMessage msg = new FacesMessage();
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            msg.setSummary(ex.getLocalizedMessage());
            context.addMessage(null, msg);
            return;
        }
        FacesMessage msg = new FacesMessage();
        String message = bundle.getString("assessment_updated_in_database");
        msg.setSummary(message);
        context.addMessage(null, msg);
    }

    public void onRowCancel(RowEditEvent event) {
        FacesContext context = FacesContext.getCurrentInstance();
        ResourceBundle bundle = context.getApplication().getResourceBundle(context, "msgs");
        FacesMessage msg = new FacesMessage();
        String message = bundle.getString("assessment_was_canceled");
        msg.setSummary(message);
        context.addMessage(null, msg);
    }
    /*
     public String getAssessmentText() {
     FacesContext context = FacesContext.getCurrentInstance();
     ResourceBundle bundle = context.getApplication().getResourceBundle(context, "msgs");
     switch (assessment) {
     case 1 :
     return bundle.getString("strongly_disagree");
     case 2:
     return bundle.getString("disagree");
     case 3:
     return bundle.getString("neither_agree");
     case 4:
     return bundle.getString("agree");
     case 5:
     return bundle.getString("strongly_agree");
     }
     return "not_assessed_yet";                   
     }
     */

    public String getAssessmentStatus() {
        FacesContext context = FacesContext.getCurrentInstance();
        ResourceBundle bundle = context.getApplication().getResourceBundle(context, "msgs");
        //fill map with competences
        Map<Competence, List<EmployeeCompetenceAssessment>> map
                = new HashMap<>();
        if (employeeCompetenceAssessments.isEmpty()) {
            return " ";
        }
        for (EmployeeCompetenceAssessment eca : employeeCompetenceAssessments) {
            Competence c = eca.getCompetenceIdcompetence();
            List<EmployeeCompetenceAssessment> ecas;
            if (map.containsKey(c)) {
                ecas = map.get(c);

            } else {
                ecas = new ArrayList<>();
            }
            ecas.add(eca);
            map.put(c, ecas);
        }

        Set<Competence> competences = map.keySet();
        StringBuilder sbuf = new StringBuilder();
        int count = 0;
        double total = 0.0;
        boolean notCompletedYet = false;
        for (Competence c : competences) {
            List<EmployeeCompetenceAssessment> ecas = map.get(c);
            double average = this.getAverage(ecas);
            total += average;
            count++;
            String competenceText = bundle.getString("competence");
            String averageText = bundle.getString("average");
            sbuf.append(competenceText);
            sbuf.append(" ");
            sbuf.append(c.getCompetenceName());
            sbuf.append(" ");
            sbuf.append(averageText);
            sbuf.append(": ");
            if (average == 0.0) {
                String notCompletedYetText = bundle.getString("not_completed_yet");
                sbuf.append(notCompletedYetText);
                notCompletedYet = true;
            } else {
                sbuf.append(average);
            }
            sbuf.append("<br />");
        }
        String assessmentStatusText = bundle.getString("assessment_status");
        sbuf.append(assessmentStatusText);
        if (notCompletedYet) {
            String notCompletedYetText = bundle.getString("not_completed_yet");
            sbuf.append(notCompletedYetText);
        } else {
            String completedText = bundle.getString("completed");
            sbuf.append(completedText);
            sbuf.append("<br />");
            String averageText = bundle.getString("average");
            sbuf.append(averageText);
            sbuf.append(": ");
            double avg = total / count;
            sbuf.append(avg);
        }
        
        return sbuf.toString();
    }

    private double getAverage(List<EmployeeCompetenceAssessment> ecas) {
        double total = 0.0;
        double undefined = 0;
        double count = 0;
        for (EmployeeCompetenceAssessment eca : ecas) {
            double mark = eca.getAssessment();
            if (mark >= 1 && mark <= 5) {
                total += mark;
                count++;
            } else {
                undefined++;
            }
        }
        if (undefined > 0) {
            return 0.0;
        } else {
            return total / count;
        }
    }

    public void assessmentValueChange(AjaxBehaviorEvent event) {
        employeeCompetenceAssessments
                = employeeCompetenceAssessmentFacade.findAllForAssessmentAndEmployee(selectedAssessment, employee);
        filteredEmployeeCompetenceAssessments = null;

    }
}
