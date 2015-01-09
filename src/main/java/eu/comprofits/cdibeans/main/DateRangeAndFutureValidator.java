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

import java.util.Date;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;

import javax.faces.validator.Validator;

import javax.faces.validator.ValidatorException;
import org.primefaces.component.calendar.Calendar;


@FacesValidator("dateRangeValidator")
public class DateRangeAndFutureValidator implements Validator {

    @Override
    public void validate(FacesContext context, UIComponent component, Object value)
            throws ValidatorException {

        if (value == null) {
            return; // Let required="true" handle.
        }

        UIInput startDateComponent = (UIInput) component.getAttributes().get("startDateComponent");

        if (!startDateComponent.isValid()) {
            return; // Already invalidated. Don't care about it then.
        }

        Date startDate = (Date) startDateComponent.getValue();

        if (startDate == null) {
            return; // Let required="true" handle.
        }
        ResourceBundle bundle = context.getApplication().getResourceBundle(context, "msgs");
        Date endDate = (Date) value;
        if (endDate.compareTo(new Date())>0) {
            throw new ValidatorException(new FacesMessage(
                FacesMessage.SEVERITY_ERROR, bundle.getString("error_date_future"), null));
        }
        if (startDate.after(endDate)) {
            startDateComponent.setValid(false);
            throw new ValidatorException(new FacesMessage(
                FacesMessage.SEVERITY_ERROR, bundle.getString("error_date_range"), null));
        }
    }
}
