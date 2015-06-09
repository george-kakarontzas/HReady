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
import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;
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
import javax.inject.Named;

/**
 *
 * @author ckopanos
 * @author George Kakarontzas
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
    private Employee currentEmployee;
    private Map<Competence, Integer> l3competencesValuesMap = new HashMap<>();

    public void refreshAssessments() {
        this.assessments = this.ccfacade.findAll();
    }

    @PostConstruct
    public void init() {
        this.refreshAssessments();
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        Employee e = (Employee) externalContext.getSessionMap().get("user");
        if (e == null) {
            Principal principal
                    = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal();
            if (principal != null) {
                e = emfacade.getEmployeeByUsername(principal.getName()); // Find User by j_username.
            }
        }
        if (e != null) {
            employees = emfacade.getDepartmentEmployees(e.getDepartmentIddepartment());
            //refreshAssessmentsList();
        }
        //this.employees = emfacade.getDepartmentEmployees(null)
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

    public double getPercentageOfCompetenceCompletion(Employee e) {
        int count = 0;
        for (Competence c : getL3competences()) {
            CurrentCompetenceAssessment cca = ccfacade.getAssessmentForEmployeeAndCompetence(e, c);
            if (cca!=null) count++;
        }
        return ((double) count / getL3competences().size())*100;
    }

    public Employee getCurrentEmployee() {
        return currentEmployee;
    }

    public void setCurrentEmployee(Employee currentEmployee) {
        this.currentEmployee = currentEmployee;
    }

    public Map<Competence, Integer> getL3competencesValuesMap() {
        return l3competencesValuesMap;
    }

    public void setL3competencesValuesMap(Map<Competence, Integer> l3competencesValuesMap) {
        this.l3competencesValuesMap = l3competencesValuesMap;
    }
    
    public String edit(Employee employee) {
        //this.assessment = assessment;
        setCurrentEmployee(employee);
        this.l3competences = this.getL3competences();
        //first clear the map of competence values for this employee
        l3competencesValuesMap.clear();
        /* for each competence create an entry in the competence map
         and place there the current assessment value if there
         is an assesement for this competence and for the selected employee
         or -1 if an assessment doesn't exist
         */
        for (Competence c : l3competences) {
            CurrentCompetenceAssessment cca
                    = ccfacade.getAssessmentForEmployeeAndCompetence(employee, c);
            if (cca != null) {
                l3competencesValuesMap.put(c, cca.getAssessment());
            } else {
                l3competencesValuesMap.put(c, -1);
            }
        }
        return "editCurrentAssessment";
    }

    public List<Map.Entry<Competence, Integer>> getCompetences() {
        Set<Map.Entry<Competence, Integer>> compSet
                = l3competencesValuesMap.entrySet();
        return new ArrayList<Map.Entry<Competence, Integer>>(compSet);
    }

    public String update() {
        try {
            for (Map.Entry<Competence, Integer> e : l3competencesValuesMap.entrySet()) {
                Competence c = e.getKey();
                Integer v = e.getValue();
                Employee emp = getCurrentEmployee();
                //create or update competences only with value != -1 which is
                //considered as a "value not provided" constant
                CurrentCompetenceAssessment cr
                            = this.ccfacade.getAssessmentForEmployeeAndCompetence(
                                    emp, c);
                if (v != -1) {
                    //if assessment for this employee and this competence 
                    //already exists in the database
                    if (cr != null) {
                        //if the provided value differs from the one in the db
                        //then update the assessment in the database
                        if (cr.getAssessment() != v) {
                            cr.setAssessment(v);
                            ccfacade.edit(cr);
                        }
                    } //if assessment for this employee and this competence
                    //does not exist in the database
                    else {
                        //create a new current competence assessment object
                        // for the competence and employee with the provided value
                        cr = new CurrentCompetenceAssessment();
                        cr.setCompetenceIdcompetence(c);
                        cr.setAssessment(v);
                        cr.setEmployeeIdemployee(emp);
                        //save the new current competence assessment in database
                        ccfacade.create(cr);
                    }
                }
                else {
                    if (cr!=null) {
                        ccfacade.remove(cr);
                    }
                }
            }
            this.refreshAssessments();
            //at the end display a message that the update was succesful
            FacesContext context = FacesContext.getCurrentInstance();
            ResourceBundle bundle = context.getApplication().getResourceBundle(
                    context, "msgs");
            String competence_assessment_updated
                    = bundle.getString("competence_assessment_updated");
            context.addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO,
                            competence_assessment_updated, null));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            e.getMessage(), null));
        }
        return "updateCurrentCompetenceAssessment";
    }

}
