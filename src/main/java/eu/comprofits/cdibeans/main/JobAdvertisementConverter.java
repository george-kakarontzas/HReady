/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.comprofits.cdibeans.main;

import eu.comprofits.entities.jobapplicant.JobAdvertisement;
import eu.comprofits.session.jobapplicant.JobAdvertisementFacade;
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
@Named(value = "jobAdverisementConverter")
@RequestScoped
public class JobAdvertisementConverter implements Converter {

    @EJB
    private JobAdvertisementFacade advFacade;

    /**
     * Creates a new instance of CompetenceConverter
     */
    public JobAdvertisementConverter() {
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.isEmpty() || value.equals("---")) {
            return null;
        }
        return advFacade.find(Integer.valueOf(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (!(value instanceof JobAdvertisement)) {
            return null;
        }
        return ((JobAdvertisement) value).getIdjobAdvertisement().toString();
    }

}
