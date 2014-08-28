/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.comprofits.cdibeans.assessment;

import eu.comprofits.entities.employee.Employee;
import eu.comprofits.session.employee.EmployeeFacade;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Named;

/**
 *
 * @author george
 */
@Named(value = "employeeConverter1")
@RequestScoped
public class EmployeeConverter1 implements Converter {

    @EJB
    private EmployeeFacade employeeFacade;

    /**
     * Creates a new instance of CompetenceConverter
     */
    public EmployeeConverter1() {
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.isEmpty() || value.equals("---")) {
            return null;
        }
        /*
        strParts[0] should be last name
        strParts[1] should be first name
        strParts[3] should be email
        */
        String[] strParts = value.split("\\s+");
        
        return employeeFacade.getEmployeeByLnameFnameAndEmail(strParts[0], strParts[1], strParts[2]);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (!(value instanceof Employee)) {
            return null;
        }
        Employee e = (Employee) value;
        return e.getLastName()+" "+e.getFirstName()+" "+e.getEmail();
    }

}
