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


import eu.comprofits.entities.jobprofile.JobStudyMinRequirements;
import eu.comprofits.session.jobprofile.JobStudyMinRequirementsFacade;
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
@Named(value = "jobStudyMinRequirementsConverter")
@RequestScoped
public class JobStudyMinRequirementsConverter implements Converter {

    @EJB
    private JobStudyMinRequirementsFacade jobStudyMinRequirementsFacade;

    /**
     * Creates a new instance of CompetenceConverter
     */
    public JobStudyMinRequirementsConverter() {
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.isEmpty() || value.equals("---")) {
            return null;
        }
        return jobStudyMinRequirementsFacade.find(Integer.valueOf(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (!(value instanceof JobStudyMinRequirements)) {
            return null;
        }
        return ((JobStudyMinRequirements) value).getIdjobStudyMinRequirements().toString();
    }

}
