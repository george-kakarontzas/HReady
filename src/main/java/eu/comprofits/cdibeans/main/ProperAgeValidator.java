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

/**
 *
 * @author ckopanos
 */

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;

import javax.faces.validator.Validator;

import javax.faces.validator.ValidatorException;


@FacesValidator("properAgeValidator")
public class ProperAgeValidator implements Validator {

    protected int properAge = 16;
    
    @Override
    public void validate(FacesContext context, UIComponent component, Object value)
            throws ValidatorException {

        if (value == null) {
            return; // Let required="true" handle.
        }

        if (value == null) {
            return; // Let required="true" handle.
        }
        Date birthDate = (Date) value;

        ResourceBundle bundle = context.getApplication().getResourceBundle(context, "msgs");
        Calendar cal = new GregorianCalendar();
        cal.setTime(birthDate);
        Calendar now = new GregorianCalendar();
        int age = now.get(Calendar.YEAR) - cal.get(Calendar.YEAR);
         if ((cal.get(Calendar.MONTH) > now.get(Calendar.MONTH)) 
                 || (cal.get(Calendar.MONTH) == now.get(Calendar.MONTH) && cal.get(Calendar.DAY_OF_MONTH) > now.get(Calendar.DAY_OF_MONTH))) {
              age--;
          }
           
        if (age < this.properAge) {
            throw new ValidatorException(new FacesMessage(
                FacesMessage.SEVERITY_ERROR, bundle.getString("error_age"), null));
        }
    }
}
