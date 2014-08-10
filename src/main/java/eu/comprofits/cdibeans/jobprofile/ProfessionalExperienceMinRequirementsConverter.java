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


import eu.comprofits.entities.jobprofile.ProfessionalExperienceMinRequirements;
import eu.comprofits.session.jobprofile.ProfessionalExperienceMinRequirementsFacade;
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
@Named(value = "professionalExperienceMinRequirementsConverter")
@RequestScoped
public class ProfessionalExperienceMinRequirementsConverter implements Converter {

    @EJB
    private ProfessionalExperienceMinRequirementsFacade professionalExperienceMinRequirementsFacade;

    /**
     * Creates a new instance of CompetenceConverter
     */
    public ProfessionalExperienceMinRequirementsConverter() {
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.isEmpty() || value.equals("---")) {
            return null;
        }
        return professionalExperienceMinRequirementsFacade.find(Integer.valueOf(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (!(value instanceof ProfessionalExperienceMinRequirements)) {
            return null;
        }
        return ((ProfessionalExperienceMinRequirements) value).getIdprofessionalExperienceMinRequirements().toString();
    }

}
