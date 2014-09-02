/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package eu.comprofits.mbs;

import eu.comprofits.ejb.UserService;
import eu.comprofits.entities.User;
import java.io.Serializable;
import java.security.Principal;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author george
 */
@Named(value = "authenticator")
@SessionScoped
public class Authenticator implements Serializable {

    private User user; // The JPA entity.

    @EJB
    private UserService userService;

    public User getUser() {
        if (user == null) {
            Principal principal = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal();
            if (principal != null) {
                user = userService.find(principal.getName()); // Find User by j_username.
            }
        }
        return user;
    }
    
}
