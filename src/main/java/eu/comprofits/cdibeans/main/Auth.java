/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.comprofits.cdibeans.main;

import eu.comprofits.entities.employee.Employee;
import eu.comprofits.entities.jobapplicant.JobApplicant;
import eu.comprofits.session.employee.EmployeeFacade;
import eu.comprofits.session.jobapplicant.JobApplicantFacade;
import eu.comprofits.session.jobapplicant.JobApplicationFacade;
import java.io.IOException;
import java.io.Serializable;
import static java.lang.System.out;
import java.security.Principal;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author george
 */
@Named(value = "auth")
@SessionScoped
public class Auth implements Serializable {

    @EJB
    private JobApplicantFacade jobApplicantFacade;

    @EJB
    private EmployeeFacade employeeFacade;

    private Employee employee;
    private JobApplicant applicant;
    private String username;
    private String password;

    /**
     * Creates a new instance of Auth
     */
    public Auth() {
    }

    public Employee getEmployee() {
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        Employee e = (Employee) externalContext.getSessionMap().get("user");
        if (employee == null) {
            Principal principal
                    = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal();
            if (principal != null) {
                employee = employeeFacade.getEmployeeByUsername(principal.getName()); // Find User by j_username.
            } else if (e != null) {
                employee = e;
            }
        }
        return employee;
    }
    
     public JobApplicant getApplicant() {
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        JobApplicant a = (JobApplicant) externalContext.getSessionMap().get("applicant");
        if (applicant == null) {
            Principal principal
                    = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal();
            if (principal != null) {
                applicant = jobApplicantFacade.getJobApplicantByUsername(principal.getName()); // Find User by j_username.
            } else if (a != null) {
                applicant = a;
            }
        }
        return applicant;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/index?faces-redirect=true";
    }

    public void setApplicant(JobApplicant applicant) {
        this.applicant = applicant;
    }
    
    public String progLogin() throws IOException {
        String gotoPage = "/index.xhtml";
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
        try {
            request.login(username, password);
            Employee e = employeeFacade.getEmployeeByUsername(username);
            // update gotoPage to handle absolute instead of relative redirects
            // this way when the user is logged out inside a page e.g. http://localhost:8080/comprofitsapp/applicant/
            // he will be properly redirected
            if (e != null) {
                externalContext.getSessionMap().put("user", e);
                String role = e.getRole();
                switch (role) {
                    case "depthead":
                        gotoPage = "/depthead/deptheadHomePage.xhtml";
                        break;
                    case "administrator":
                        gotoPage = "/administrator/adminHomePage.xhtml";
                        break;
                    case "hrrecruiter":
                        gotoPage = "/hrm/hrmHomePage.xhtml";
                        break;
                    case "employee":
                        gotoPage = "/employee/employeeHomePage.xhtml";
                        break;
                    default:
                        gotoPage = "/index.xhtml";
                }
            } else {
                JobApplicant j = jobApplicantFacade.getJobApplicantByUsername(username);
                if (j != null) {                         
                    externalContext.getSessionMap().put("applicant", j);
                    gotoPage = "/applicant/applicantHomePage.xhtml";
                }
            }
            // Handle the case of the beta version where the app is
            // not deployed under a directory
            if (request.getContextPath().startsWith("/comprofitsapp")) {
                gotoPage = "/comprofitsapp"+gotoPage;
            };
            externalContext.redirect(gotoPage);
        } catch (ServletException e) {
            // Handle unknown username/password in request.login().
            context.addMessage(null, new FacesMessage("Unknown login"));
        }
        return "index";
    }
}
