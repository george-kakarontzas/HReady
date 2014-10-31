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


import eu.comprofits.entities.main.OrganisationalPosition;
import eu.comprofits.session.main.OrganisationalPositionFacade;
import java.util.List;
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
@Named(value = "organisationalPositionConverter")
@RequestScoped
public class OrganisationalPositionConverter implements Converter {

    @EJB
    private OrganisationalPositionFacade positionFacade;
    
    List<OrganisationalPosition> positions;
    OrganisationalPosition output;

    
    public OrganisationalPositionConverter() {
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.isEmpty() || value.equals("---")) {
            return null;
        }
       
        
        return (OrganisationalPosition) positionFacade.findByName(value);
        
        
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (!(value instanceof OrganisationalPosition)) {
            return null;
        }
        
        return ((OrganisationalPosition) value).getOrganisationalPositionName();
    }

}
