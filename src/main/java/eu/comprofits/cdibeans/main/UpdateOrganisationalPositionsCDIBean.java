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
 * @author George Kakarontzas
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
