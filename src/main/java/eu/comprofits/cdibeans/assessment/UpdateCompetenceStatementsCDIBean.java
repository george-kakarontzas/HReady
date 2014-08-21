/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.comprofits.cdibeans.assessment;

import eu.comprofits.entities.assessment.Statement;
import eu.comprofits.entities.main.Competence;
import eu.comprofits.session.assessment.StatementFacade;
import eu.comprofits.session.main.CompetenceFacade;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Named;

/**
 *
 * @author george
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
        updateSelectedCompetenceStatements();
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
    }

    public void saveNewStatement(ActionEvent event) {
        newStatement.setCompetenceId(selectedCompetence);
        try {
            statementFacade.create(newStatement);   
        }
        catch (Exception e) {
             FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            e.getMessage(), null));
        }
    }

}
