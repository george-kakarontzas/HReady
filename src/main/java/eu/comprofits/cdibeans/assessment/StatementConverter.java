/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.comprofits.cdibeans.assessment;

import eu.comprofits.entities.assessment.Statement;
import eu.comprofits.session.assessment.StatementFacade;
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
@Named(value = "statementConverter")
@RequestScoped
public class StatementConverter implements Converter {

    @EJB
    private StatementFacade statementFacade;

    /**
     * Creates a new instance of StatementConverter
     */
    public StatementConverter() {
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.isEmpty() || value.equals("---")) {
            return null;
        }
                
        return statementFacade.getStatementFromText(value);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (!(value instanceof Statement)) {
            return null;
        }
        Statement s = (Statement) value;
        return s.getStatementText();
    }

}
