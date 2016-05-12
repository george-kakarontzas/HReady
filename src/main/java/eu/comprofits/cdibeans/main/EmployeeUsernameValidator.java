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
 * @author George Kakarontzas This is supposed to be called when updating the employee
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
