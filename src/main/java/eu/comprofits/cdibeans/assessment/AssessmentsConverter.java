/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.comprofits.cdibeans.assessment;

import eu.comprofits.entities.assessment.Assessment;
import eu.comprofits.session.assessment.AssessmentFacade;
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
@Named(value = "assessmentsConverter")
@RequestScoped
public class AssessmentsConverter implements Converter {

    @EJB
    private AssessmentFacade assessmentFacade;

    /**
     * Creates a new instance of CompetenceConverter
     */
    public AssessmentsConverter() {
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.isEmpty() || value.equals("---")) {
            return null;
        }
        /*
        sparts[0] should be date that assessment was created
        sparts[1] should be assessee last name
        sparts[3] should be assessee first name
        */
        String[] sparts = value.split("\\s+");
        return assessmentFacade.getAssessmentByDateAndAssessee(sparts[0], sparts[1], sparts[2]);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (!(value instanceof Assessment)) {
            return null;
        }
        Assessment a = (Assessment) value;
        return a.getDateCreated()+" "+a.getAssesseeIdemployee().getLastName()+" "+
                a.getAssesseeIdemployee().getFirstName();
    }

}
