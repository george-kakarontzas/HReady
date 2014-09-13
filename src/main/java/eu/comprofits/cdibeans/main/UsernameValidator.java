/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.comprofits.cdibeans.main;

import eu.comprofits.entities.employee.Employee;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import eu.comprofits.session.employee.EmployeeFacade;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;

/**
 *
 * @author george This is supposed to be called when updating job_applicant
 * table. Applicants uesrnames should be unique also in relation to employees
 * table for authentication purposes
 */
@FacesValidator("usernameValidator")
public class UsernameValidator implements Validator {

    @EJB
    private EmployeeFacade employeeFacade;

    @Override
    public void validate(FacesContext facesContext,
            UIComponent component, Object value)
            throws ValidatorException {

        String unameValue = value.toString();
        //check if the username already exists in the employee table
        Employee e = employeeFacade.getEmployeeByUsername(unameValue);
        //if it does then disallow the update
        if (e != null) {
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
