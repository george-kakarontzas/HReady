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
import eu.comprofits.session.main.CompanyFacade;
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
@Named(value = "updateCompanyProfileCDIBean")
@SessionScoped
public class updateCompanyProfileCDIBean implements Serializable {
   private static final long serialVersionUID = 1L;
    @EJB
    private CompanyFacade companyFacade;


    private Company company;
    private List<Company> companies;

    /**
     * Creates a new instance of UpdateDepartmentsCDIBean
     */
    public updateCompanyProfileCDIBean() {
    }

    @PostConstruct
    public void init() {
        refreshCompanies();
    }

    public void refreshCompanies() {
        companies = companyFacade.findAll();
    }
    
    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }


    public List<Company> getCompanies() {
        return companies;
    }

    public void setCompanies(List<Company> companies) {
        this.companies = companies;
    }

    public void remove(Company c) {
        try {
            companyFacade.remove(c);
            refreshCompanies();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            e.getMessage(), null));
        }
    }

    public String edit(Company company) {
        this.company = company;
        return "editCompanyProfile";
    }

    public String create() {
        this.company = new Company();
        return "editCompanyProfile";
    }


    public String update() {
        try {
            if (company.getIdcompany() == null) {
                companyFacade.create(company);
            } else {
                companyFacade.edit(company);
            }
            refreshCompanies();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            e.getMessage(), null));
        }
        return "updateCompanyProfile";
    }
    
     public List<CountryList.Country> getCountries() {
        // Present a menu with language code, languate title 
        // better store country code in db.
        // this is irrespective of the chosen language and displays correctly
        // both in english and spanish
        CountryList countriesList = new CountryList(FacesContext.getCurrentInstance().getViewRoot().getLocale());
        return countriesList.getCountries();
    }
    
    public String getCountryName(CountryList.Country country) {
        return country.getName(); 
    }
    
      public String getCountryCode(CountryList.Country country) {
        return country.getCode(); 
    }  

}