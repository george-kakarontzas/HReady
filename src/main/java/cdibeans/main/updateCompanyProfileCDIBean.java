/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cdibeans.main;

import eu.comprofits.entities.main.Company;
import eu.comprofits.session.main.CompanyFacade;
import java.util.List;
import java.util.ResourceBundle;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author george
 */
@Named(value = "updateCompanyProfileCDIBean")
@RequestScoped
public class updateCompanyProfileCDIBean {

    @EJB
    private CompanyFacade companyFacade;
    private Company company;
    private boolean firstTime;

    /**
     * Creates a new instance of updateCompanyProfileCDIBean
     */
    public updateCompanyProfileCDIBean() {
    }

    @PostConstruct
    public void init() {
        if (company == null) {
            List<Company> allCompanies = companyFacade.findAll();
            //if there is no company in the database then
            //create a new object. Otherwise we use the first company in DB
            //as the current company
            if (!allCompanies.isEmpty()) {
                this.company = allCompanies.get(0);
            } else {
                firstTime = true;
                company = new Company();
            }
        }
    }

    public String save() throws InterruptedException {
        if (company != null) {
            if (firstTime) {
                companyFacade.create(company);
            } else {
                companyFacade.edit(company);
            }
        }
        FacesContext context = FacesContext.getCurrentInstance();
        ResourceBundle text = ResourceBundle.getBundle("messages", context.getViewRoot().getLocale());
        String message = text.getString("succesful_save_message");
        context.addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, message, message));
        return "";
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

}
