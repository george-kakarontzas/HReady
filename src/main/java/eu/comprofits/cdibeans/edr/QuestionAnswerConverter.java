/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.comprofits.cdibeans.edr;

import eu.comprofits.entities.edr.QuestionAnswer;
import eu.comprofits.session.edr.QuestionAnswerFacade;
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
@Named(value = "questionAnswerConverter")
@RequestScoped
public class QuestionAnswerConverter implements Converter {

    @EJB
    private QuestionAnswerFacade questionAnswerFacade;

    /**
     * Creates a new instance of CompetenceConverter
     */
    public QuestionAnswerConverter() {
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.isEmpty() || value.equals("---")) {
            return null;
        }
        return questionAnswerFacade.find(Integer.valueOf(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (!(value instanceof QuestionAnswer)) {
            return null;
        }
        return ((QuestionAnswer) value).getIdquestion().toString();
    }

}
