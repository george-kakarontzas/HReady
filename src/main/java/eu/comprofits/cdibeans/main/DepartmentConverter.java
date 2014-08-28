/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.comprofits.cdibeans.main;

import eu.comprofits.entities.main.Department;
import eu.comprofits.session.main.DepartmentFacade;
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
@Named(value = "departmentConverter")
@RequestScoped
public class DepartmentConverter implements Converter {

    @EJB
    private DepartmentFacade departmentFacade;

    /**
     * Creates a new instance of CompetenceConverter
     */
    public DepartmentConverter() {
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.isEmpty() || value.equals("---")) {
            return null;
        }
        return departmentFacade.find(Integer.valueOf(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (!(value instanceof Department)) {
            return null;
        }
        return ((Department) value).getIddepartment().toString();
    }

}
