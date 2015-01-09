/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.comprofits.cdibeans.main;

import eu.comprofits.entities.main.Competence;
import eu.comprofits.session.main.CompetenceFacade;
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
 * @author george
 */
@Named(value = "updateCompetencePyramidCDIBean")
@SessionScoped
public class updateCompetencePyramidCDIBean implements Serializable {

    private static final long serialVersionUID = 1L;
    @EJB
    private CompetenceFacade competenceFacade;
    private Competence competence;  
    private List<Competence> competences;

    /**
     * Creates a new instance of updateCompetencePyramidCDIBean
     */
    public updateCompetencePyramidCDIBean() {
    }

    @PostConstruct
    public void init() {
        refreshCompetences();
    }
    
    public void refreshCompetences() {
        competences = competenceFacade.getOrderedCompetences();
    }

    public Competence getCompetence() {
        return competence;
    }

    public void setCompetence(Competence competence) {
        this.competence = competence;
    }

    public List<Competence> getCompetences() {
        return competences;
    }

    public void setCompetences(List<Competence> competences) {
        this.competences = competences;
    }
    
    public void remove(Competence c) {
        try {
            competenceFacade.remove(c);
            refreshCompetences();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            e.getMessage(), null));
        }
    }

    public String edit(Competence competence) {
        this.competence = competence;
        return "editCompetence";
    }

    public String create() {
        this.competence = new Competence();
        return "editCompetence";
    }

    public String update() {
        try {
            if (competence.getIdcompetence() == null) {
                competenceFacade.create(competence);
            } else {
                competenceFacade.edit(competence);
            }
            refreshCompetences();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            e.getMessage(), null));
        }
        return "updateCompetencesPyramid";
    }
}
