/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.comprofits.cdibeans.edr;

import eu.comprofits.entities.edr.Edr;
import eu.comprofits.entities.edr.CompetenceGoal;
import eu.comprofits.session.edr.EdrFacade;
import eu.comprofits.session.edr.CompetenceGoalFacade;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author alexanderhoelzemann
 */
@Named(value = "UpdateCompetenceGoalCDIBean")
@SessionScoped
public class UpdateCompetenceGoalCDIBean implements Serializable {

    private static final long serialVersionUID = 1L;
    @EJB
    private CompetenceGoalFacade competenceGoalFacade;

    @EJB
    private EdrFacade edrFacade;

    private CompetenceGoal competenceGoalObject;
    private List<CompetenceGoal> competenceGoalList;
    private List<Edr> edrList;

    public UpdateCompetenceGoalCDIBean() {
    }

    @PostConstruct
    public void init() {
        competenceGoalList = competenceGoalFacade.findAll();
        edrList = edrFacade.findAll();
    }

    public CompetenceGoal getCompetenceGoalObject() {
        return competenceGoalObject;
    }

    public List<CompetenceGoal> getCompetenceGoalList() {
        return competenceGoalList;
    }

    public List<Edr> getEdrList() {
        return edrList;
    }

    public void setCompetenceGoalObject(CompetenceGoal competenceGoalObject) {
        this.competenceGoalObject = competenceGoalObject;
    }

    public void setCompetenceGoalList(List<CompetenceGoal> competenceGoalList) {
        this.competenceGoalList = competenceGoalList;
    }

    public void setEdrList(List<Edr> edrList) {
        this.edrList = edrList;
    }

    public String edit(CompetenceGoal competenceGoal) {
        this.competenceGoalObject = competenceGoal;
        return "editCompetenceGoal";
    }

    public String create() {
        this.competenceGoalObject = new CompetenceGoal();
        return "createCompetenceGoal";
    }

    public void remove(CompetenceGoal competenceGoal) {
        try {
            competenceGoalFacade.remove(competenceGoal);
            competenceGoalList = competenceGoalFacade.findAll();

        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            e.getMessage(), null));
        }
    }

    public void update() {
        try {
            if (competenceGoalObject.getIdcompetenceGoal() == null) {
                competenceGoalFacade.create(competenceGoalObject);
            } else {
                competenceGoalFacade.edit(competenceGoalObject);
            }
            competenceGoalList = competenceGoalFacade.findAll();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            e.getMessage(), null));
        }
    }
   
}
