/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.comprofits.cdibeans.jobprofile;

import eu.comprofits.entities.jobprofile.CompetencesRequirement;
import eu.comprofits.session.jobprofile.CompetencesRequirementFacade;
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
@Named(value = "competenceRequirementConverter")
@RequestScoped
public class CompetencesRequirementConverter implements Converter {

    @EJB
    private CompetencesRequirementFacade competenceRequirementFacade;

    /**
     * Creates a new instance of CompetenceConverter
     */
    public CompetencesRequirementConverter() {
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.isEmpty() || value.equals("---")) {
            return null;
        }
        
        return competenceRequirementFacade.find(Integer.valueOf(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (!(value instanceof CompetencesRequirement)) {
            return null;
        }
        return ((CompetencesRequirement) value).getIdcompetencesRequirement().toString();
    }

}
