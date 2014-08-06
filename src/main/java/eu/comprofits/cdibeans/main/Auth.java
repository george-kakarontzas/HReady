/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.comprofits.cdibeans.main;

import eu.comprofits.entities.employee.Employee;
import eu.comprofits.session.employee.EmployeeFacade;
import java.io.IOException;
import java.io.Serializable;
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
    private EmployeeFacade employeeFacade;
    private Employee employee;
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
            }
            else if (e !=null) {
                employee = e;
            }
        }
        return employee;
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

    public String progLogin() throws IOException {
        String gotoPage = "/index.xtml";
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
        try {
            request.login(username, password);
            Employee e =  employeeFacade.getEmployeeByUsername(username);
            externalContext.getSessionMap().put("user", e);
            String role = e.getRole();
            switch (role) {
                case "depthead":
                    gotoPage="./faces/depthead/deptheadHomePage.xhtml";
                    break;
                case "administrator":
                    gotoPage="./faces/administrator/adminHomePage.xhtml";
                    break;
                case "hrrecruiter":
                    gotoPage="./faces/hrm/hrmHomePage.xhtml";
                    break;
                default:
                    gotoPage="./faces/index.xhtml";
            }
            externalContext.redirect(gotoPage);
        } catch (ServletException e) {
            // Handle unknown username/password in request.login().
            context.addMessage(null, new FacesMessage("Unknown login"));
        }
        return "index";
    }
}
