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

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("passwordValidator")
public class PasswordValidator implements Validator {

    // Actions ------------------------------------------------------------------------------------
    @Override
    public void validate(FacesContext context, UIComponent component, Object value)
            throws ValidatorException {
        // Cast the value of the entered password to String.
        String password = (String) value;

        // Obtain the component and submitted value of the confirm password component.
        UIInput confirmComponent = (UIInput) component.getAttributes().get("confirm");
        String confirm = (String) confirmComponent.getSubmittedValue();

        // Check if they both are filled in.
        if (password == null || password.isEmpty() || confirm == null || confirm.isEmpty()) {
            return; // Let required="true" do its job.
        }

        // Compare the password with the confirm password.
        if (!password.equals(confirm)) {
            confirmComponent.setValid(false); // So that it's marked invalid.
            throw new ValidatorException(new FacesMessage("Passwords are not equal."));
        }
    }

}
