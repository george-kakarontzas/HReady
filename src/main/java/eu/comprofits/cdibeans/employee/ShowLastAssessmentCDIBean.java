/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.comprofits.cdibeans.employee;

import eu.comprofits.entities.assessment.Assessment;
import eu.comprofits.entities.employee.Employee;
import eu.comprofits.session.assessment.AssessmentFacade;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author george
 */
@Named(value = "showLastAssessmentCDIBean")
@RequestScoped
public class ShowLastAssessmentCDIBean {

    @EJB
    private AssessmentFacade assessmentFacade;
    private Assessment lastAssessment;

    private Employee employee;

    /**
     * Creates a new instance of EditPastCompanyEmploymentsCDIBean
     */
    public ShowLastAssessmentCDIBean() {
    }

    @PostConstruct
    public void init() {
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        employee = (Employee) externalContext.getSessionMap().get("employee");
        List<Assessment> allCompletedAssessments
                = assessmentFacade.getAssesseeEmployeeAssessments(employee);
        lastAssessment = null;
        if (allCompletedAssessments != null) {
            for (Assessment a : allCompletedAssessments) {
                if (lastAssessment == null && a.isCompleted()) {
                    lastAssessment = a;
                } else if ((lastAssessment.getDeadline().before(a.getDeadline())) && a.isCompleted()) {
                    lastAssessment = a;
                }
            }
        }
    }

    public boolean isLastAssessmentNotNull() {
        return lastAssessment != null;
    }

    public Assessment getLastAssessment() {
        return lastAssessment;
    }

    public Employee getEmployee() {
        return employee;
    }
    
}
