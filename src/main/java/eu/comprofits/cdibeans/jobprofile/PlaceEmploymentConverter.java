/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package eu.comprofits.cdibeans.jobprofile;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import eu.comprofits.entities.jobprofile.PlaceEmployment;
import eu.comprofits.session.jobprofile.PlaceEmploymentFacade;
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
@Named(value = "placeEmploymentConverter")
@RequestScoped
public class PlaceEmploymentConverter implements Converter {

    @EJB
    private PlaceEmploymentFacade placeEmploymentFacade;

    /**
     * Creates a new instance of CompetenceConverter
     */
    public PlaceEmploymentConverter() {
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.isEmpty() || value.equals("---")) {
            return null;
        }
        return placeEmploymentFacade.find(Integer.valueOf(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (!(value instanceof PlaceEmployment)) {
            return null;
        }
        return ((PlaceEmployment) value).getIdplaceEmployment().toString();
    }

}
