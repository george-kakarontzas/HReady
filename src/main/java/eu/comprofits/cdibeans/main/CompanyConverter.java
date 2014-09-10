/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.comprofits.cdibeans.main;

import eu.comprofits.entities.main.Company;
import eu.comprofits.session.main.CompanyFacade;
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
@Named(value = "companyConverter")
@RequestScoped
public class CompanyConverter implements Converter {

    @EJB
    private CompanyFacade companyFacade;

    /**
     * Creates a new instance of CompetenceConverter
     */
    public CompanyConverter() {
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.isEmpty() || value.equals("---")) {
            return null;
        }
        return companyFacade.find(Integer.valueOf(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (!(value instanceof Company)) {
            return null;
        }
        return ((Company) value).getIdcompany().toString();
    }

}
