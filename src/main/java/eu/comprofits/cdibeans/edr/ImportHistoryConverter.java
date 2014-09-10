/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.comprofits.cdibeans.edr;

import eu.comprofits.entities.edr.ImportHistory;
import eu.comprofits.session.edr.ImportHistoryFacade;
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
@Named(value = "ImportHistoryConverter")
@RequestScoped
public class ImportHistoryConverter implements Converter {

    @EJB
    private ImportHistoryFacade importHistoryFacade;

    /**
     * Creates a new instance of CompetenceConverter
     */
    public ImportHistoryConverter() {
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.isEmpty() || value.equals("---")) {
            return null;
        }
        return importHistoryFacade.find(Integer.valueOf(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (!(value instanceof ImportHistory)) {
            return null;
        }
        return ((ImportHistory) value).getIdImportHistory().toString();
    }

}