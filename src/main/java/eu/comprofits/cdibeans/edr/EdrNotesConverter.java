/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.comprofits.cdibeans.edr;


import eu.comprofits.entities.edr.EdrNotes;
import eu.comprofits.session.edr.EdrNotesFacade;
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
@Named(value = "edrNotesConverter")
@RequestScoped
public class EdrNotesConverter implements Converter {

    @EJB
    private EdrNotesFacade edrNotesFacade;

    /**
     * Creates a new instance of CompetenceConverter
     */
    public EdrNotesConverter() {
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.isEmpty() || value.equals("---")) {
            return null;
        }
        return edrNotesFacade.find(Integer.valueOf(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (!(value instanceof EdrNotes)) {
            return null;
        }
        return ((EdrNotes) value).getIdnote().toString();
    }

}
