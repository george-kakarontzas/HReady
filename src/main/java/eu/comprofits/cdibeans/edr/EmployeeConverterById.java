/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.comprofits.cdibeans.edr;

import eu.comprofits.entities.edr.Edr;
import eu.comprofits.entities.employee.Employee;
import eu.comprofits.session.edr.EdrFacade;
import eu.comprofits.session.employee.EmployeeFacade;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Named;

/**
 *
 * @author alexanderhoelzemann
 */
@Named(value = "emplyeeConverterById")
@RequestScoped
public class EmployeeConverterById implements Converter {

    @EJB
    private EmployeeFacade employeeFacade;

    /**
     * Creates a new instance of Converter
     */
    public EmployeeConverterById() {
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.isEmpty() || value.equals("---")) {
            return null;
        }
        return employeeFacade.find(Integer.valueOf(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (!(value instanceof Employee)) {
            return null;
        }
        return ((Employee) value).getIdemployee().toString();
    }

}