/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.comprofits.cdibeans.edr;

import eu.comprofits.entities.edr.Question;
import eu.comprofits.entities.edr.QuestionCategory;
import eu.comprofits.session.edr.QuestionCategoryFacade;
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
@Named(value = "questionCategoryConverter")
@RequestScoped
public class QuestionCategoryConverter implements Converter {

    @EJB
    private QuestionCategoryFacade questionCategoryFacade;

    /**
     * Creates a new instance of CompetenceConverter
     */
    public QuestionCategoryConverter() {
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.isEmpty() || value.equals("---")) {
            return null;
        }
        return questionCategoryFacade.find(Integer.valueOf(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (!(value instanceof QuestionCategory)) {
            return null;
        }
        return ((QuestionCategory) value).getIdquestioncat().toString();
    }

}
