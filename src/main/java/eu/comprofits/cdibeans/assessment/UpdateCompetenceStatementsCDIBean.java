/* 
 * Copyright 2016 ComProFITS Project.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package eu.comprofits.cdibeans.assessment;

import eu.comprofits.entities.assessment.Statement;
import eu.comprofits.entities.main.Competence;
import eu.comprofits.session.assessment.StatementFacade;
import eu.comprofits.session.main.CompetenceFacade;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Named;
import org.primefaces.context.RequestContext;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author George Kakarontzas
 */
@Named(value = "updateCompetenceStatementsCDIBean")
@SessionScoped 
public class UpdateCompetenceStatementsCDIBean implements Serializable {

    @EJB
    private StatementFacade statementFacade;

    @EJB
    private CompetenceFacade competenceFacade;

    private List<Competence> l3competences;
    private Competence selectedCompetence;
    private List<Statement> selectedCompetenceStatements;
    private Statement newStatement;

    /**
     * Creates a new instance of UpdateCompetenceStatementsCDIBean
     */
    public UpdateCompetenceStatementsCDIBean() {

    }

    @PostConstruct
    public void init() {
        l3competences = new ArrayList<>();
        //Initialize the Level 3 competences list
        List<Competence> allCompetences = competenceFacade.findAll();
        for (Competence c : allCompetences) {
            if (c.getLevel() == 3) {
                l3competences.add(c);
            }
        }
    }

    public List<Competence> getL3competences() {
        return l3competences;
    }

    public Competence getSelectedCompetence() {
        return selectedCompetence;
    }

    public void setSelectedCompetence(Competence selectedCompetence) {
        this.selectedCompetence = selectedCompetence;
    }

    public void competenceValueChange(AjaxBehaviorEvent event) {
        updateSelectedCompetenceStatements();

    }

    private void updateSelectedCompetenceStatements() {
        /*if the selectedCompetence is not null
         update the statements list with the statements associated to
         the selected competence */
        if (selectedCompetence != null) {
            selectedCompetenceStatements
                    = statementFacade.getCompetenceStatements(selectedCompetence);
        }
    }

    public List<Statement> getSelectedCompetenceStatements() {
        //updateSelectedCompetenceStatements();
        return selectedCompetenceStatements;
    }

    public void setSelectedCompetenceStatements(List<Statement> selectedCompetenceStatements) {
        this.selectedCompetenceStatements = selectedCompetenceStatements;
    }

    public boolean isCompetenceSelected() {
        return selectedCompetence != null;
    }

    public Statement getNewStatement() {
        return newStatement;
    }

    public void setNewStatement(Statement newStatement) {
        this.newStatement = newStatement;
    }

    public void newStatementBtnPressed(ActionEvent event) {
        newStatement = new Statement();
        newStatement.setStatementText("");
        RequestContext.getCurrentInstance().update("dialog");
    }

    public void saveNewStatement(ActionEvent event) {
        newStatement.setCompetenceId(selectedCompetence);
        try {
            statementFacade.create(newStatement);
            //update the selected competence statements
            //since we've added a new competence
            updateSelectedCompetenceStatements();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            e.getMessage(), null));
        }
    }

    public void onRowEdit(RowEditEvent event) {
        FacesContext context = FacesContext.getCurrentInstance();
        ResourceBundle bundle = context.getApplication().getResourceBundle(context, "msgs");
        try {
            Statement i = (Statement) event.getObject();
            System.out.println(i.getStatementText());
            statementFacade.edit(i);
            //since the statement may have changed category update the selected
            //competence statements again
            updateSelectedCompetenceStatements();
            FacesMessage msg = new FacesMessage(bundle.getString("statement_edited"),
                "" + ((Statement) event.getObject()).getIdstatement());
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
        catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            e.getMessage(), null));
        }
    }

    public void onRowCancel(RowEditEvent event) {
        FacesContext context = FacesContext.getCurrentInstance();
        ResourceBundle bundle = context.getApplication().getResourceBundle(context, "msgs");

        FacesMessage msg = new FacesMessage(bundle.getString("statement_edit_canceled"),
                "" + ((Statement) event.getObject()).getIdstatement());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void remove(Statement i) {
        try {
            statementFacade.remove(i);
            updateSelectedCompetenceStatements();
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            ex.getMessage(), null));
        }
    }

}
