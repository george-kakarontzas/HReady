/* 
 * Copyright 2016 ComProFITS Project.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
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
 * @author George Kakarontzas This is supposed to be called when updating job_applicant
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
