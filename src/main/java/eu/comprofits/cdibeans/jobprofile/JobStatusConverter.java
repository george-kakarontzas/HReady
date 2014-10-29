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


import eu.comprofits.entities.jobprofile.Job;
import eu.comprofits.entities.main.OrganisationalPosition;
import eu.comprofits.session.jobprofile.JobFacade;
import eu.comprofits.session.main.OrganisationalPositionFacade;
import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Named;

/**
 *
 * @author alexanderhoelzemann
 */
@Named(value = "jobStatusConverter")

public class JobStatusConverter implements Converter {

    @EJB
    private JobFacade jobFacade;

    
    public JobStatusConverter() {
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.isEmpty() || value.equals("---")) {
            return null;
        }
        return jobFacade.findByJobTitle(value);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (!(value instanceof OrganisationalPosition)) {
            return null;
        }
        return ((Job) value).toString();
    }

}
