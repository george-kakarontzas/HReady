/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.comprofits.cdibeans.edr;


import eu.comprofits.entities.edr.EdrHistory;
import eu.comprofits.session.edr.EdrHistoryFacade;
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
@Named(value = "edrHistoryConverter")
@RequestScoped
public class EdrHistoryConverter implements Converter {

    @EJB
    private EdrHistoryFacade edrHistoryFacade;

    /**
     * Creates a new instance of CompetenceConverter
     */
    public EdrHistoryConverter() {
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.isEmpty() || value.equals("---")) {
            return null;
        }
        return edrHistoryFacade.find(Integer.valueOf(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (!(value instanceof EdrHistory)) {
            return null;
        }
        return ((EdrHistory) value).getIdedrHistory().toString();
    }

}
