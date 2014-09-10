/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.comprofits.cdibeans.edr;

import eu.comprofits.entities.edr.Edr;
import eu.comprofits.session.edr.EdrFacade;
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
@Named(value = "edrConverter")
@RequestScoped
public class EdrConverter implements Converter {

    @EJB
    private EdrFacade edrFacade;

    /**
     * Creates a new instance of CompetenceConverter
     */
    public EdrConverter() {
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.isEmpty() || value.equals("---")) {
            return null;
        }
        return edrFacade.find(Integer.valueOf(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (!(value instanceof Edr)) {
            return null;
        }
        return ((Edr) value).getIdedr().toString();
    }

}