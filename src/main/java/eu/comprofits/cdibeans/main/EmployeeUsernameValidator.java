/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.comprofits.cdibeans.main;

import eu.comprofits.entities.jobapplicant.JobApplicant;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import eu.comprofits.session.jobapplicant.JobApplicantFacade;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;

/**
 *
 * @author george This is supposed to be called when updating the employee
 * table. Employee usernames should be unique also in relation to job applicants
 * table for authentication purposes
 */
@FacesValidator("employeeUsernameValidator")
public class EmployeeUsernameValidator implements Validator {

    @EJB
    private JobApplicantFacade jobApplicantFacade;

    @Override
    public void validate(FacesContext facesContext,
            UIComponent component, Object value)
            throws ValidatorException {

        String unameValue = value.toString();
        //check if the username already exists in the job applicant table
        JobApplicant a = jobApplicantFacade.getJobApplicantByUsername(unameValue);
        //if it does then disallow the update
        if (a != null) {
            FacesContext context = FacesContext.getCurrentInstance();
            ResourceBundle bundle
                    = context.getApplication().getResourceBundle(context, "msgs");
            String errorMessage = bundle.getString("no_unique_username");
            FacesMessage msg
                    = new FacesMessage();
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            msg.setSummary(errorMessage);
            throw new ValidatorException(msg);
        }
    }
}
