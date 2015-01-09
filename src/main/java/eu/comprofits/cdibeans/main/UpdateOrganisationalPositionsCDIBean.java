/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.comprofits.cdibeans.main;

import eu.comprofits.entities.main.Company;
import eu.comprofits.entities.main.OrganisationalPosition;
import eu.comprofits.session.main.CompanyFacade;
import eu.comprofits.session.main.OrganisationalPositionFacade;
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
@Named(value = "updateOrganisationalPositionsCDIBean")
@SessionScoped
public class UpdateOrganisationalPositionsCDIBean implements Serializable {

    @EJB
    private OrganisationalPositionFacade organisationalPositionFacade;
    @EJB
    private CompanyFacade companyFacade;
    private OrganisationalPosition organisationalPosition;
    private List<OrganisationalPosition> organisationalPositions;
    private List<Company> companies;

    /**
     * Creates a new instance of UpdateOrganisationalPositionsCDIBean
     */
    public UpdateOrganisationalPositionsCDIBean() {
    }

    @PostConstruct
    public void init() {
        organisationalPositions = organisationalPositionFacade.findAll();
        companies = companyFacade.findAll();
    }

    public OrganisationalPosition getOrganisationalPosition() {
        return organisationalPosition;
    }

    public void setOrganisationalPosition(OrganisationalPosition organisationalPosition) {
        this.organisationalPosition = organisationalPosition;
    }

    public List<OrganisationalPosition> getOrganisationalPositions() {
        return organisationalPositions;
    }

    public void setOrganisationalPositions(List<OrganisationalPosition> organisationalPositions) {
        this.organisationalPositions = organisationalPositions;
    }

    public List<Company> getCompanies() {
        return companies;
    }

    public void setCompanies(List<Company> companies) {
        this.companies = companies;
    }
    
    

    public void remove(OrganisationalPosition o) {
        try {
            organisationalPositionFacade.remove(o);
            organisationalPositions = organisationalPositionFacade.findAll();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            e.getMessage(), null));
        }
    }

    public String edit(OrganisationalPosition o) {
        this.organisationalPosition = o;
        return "editOrganisationalPosition";
    }

    public String create() {
        this.organisationalPosition = new OrganisationalPosition();
        return "editOrganisationalPosition";
    }

    public String update() {
        try {
            if (organisationalPosition.getIdorganisationalPosition() == null) {
                organisationalPositionFacade.create(organisationalPosition);
            } else {
                organisationalPositionFacade.edit(organisationalPosition);
            }
            organisationalPositions = organisationalPositionFacade.findAll();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            e.getMessage(), null));
        }
        return "updateOrganisationalPositions";
    }

}
