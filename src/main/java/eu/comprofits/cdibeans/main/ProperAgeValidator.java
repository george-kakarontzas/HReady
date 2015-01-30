/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
