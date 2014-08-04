/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.comprofits.cdibeans.edr;

import eu.comprofits.entities.edr.CompetenceGoal;
import eu.comprofits.session.edr.CompetenceGoalFacade;
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
@Named(value = "competenceGoalConverter")
@RequestScoped
public class CompetenceGoalConverter implements Converter {

    @EJB
    private CompetenceGoalFacade competenceGoalFacade;

    /**
     * Creates a new instance of CompetenceConverter
     */
    public CompetenceGoalConverter() {
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.isEmpty() || value.equals("---")) {
            return null;
        }
        return competenceGoalFacade.find(Integer.valueOf(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (!(value instanceof CompetenceGoal)) {
            return null;
        }
        return ((CompetenceGoal) value).getIdcompetenceGoal().toString();
    }

}
