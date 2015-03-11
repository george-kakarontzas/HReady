/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.comprofits.cdibeans.edr;

import eu.comprofits.entities.edr.Question;
import eu.comprofits.session.edr.AnswerFacade;
import eu.comprofits.session.edr.QuestionFacade;
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
@Named(value = "answerConverter")
@RequestScoped
public class AnswerConverter implements Converter {

    @EJB
    private AnswerFacade answerFacade;

    /**
     * Creates a new instance of CompetenceConverter
     */
    public AnswerConverter() {
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.isEmpty() || value.equals("---")) {
            return null;
        }
        return answerFacade.find(Integer.valueOf(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (!(value instanceof Question)) {
            return null;
        }
        return ((Question) value).getIdquestion().toString();
    }

}
