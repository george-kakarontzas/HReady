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


import eu.comprofits.entities.jobprofile.BusinessArea;
import eu.comprofits.session.jobprofile.BusinessAreaFacade;
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
@Named(value = "businessAreaConverter")
@RequestScoped
public class BusinessAreaConverter implements Converter {

    @EJB
    private BusinessAreaFacade businessAreaFacade;

    /**
     * Creates a new instance of CompetenceConverter
     */
    public BusinessAreaConverter() {
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.isEmpty() || value.equals("---")) {
            return null;
        }
        return businessAreaFacade.find(Integer.valueOf(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (!(value instanceof BusinessArea)) {
            return null;
        }
        return ((BusinessArea) value).getIdbusinessArea().toString();
    }

}
