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
package eu.comprofits.mbs;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

/**
 * This JSF/CDI Managed Bean provides a way for users to log out of the
 * application.
 */
@Named(value = "logoutBean")
@RequestScoped
public class LogoutBean {
    // Built in logging is used now, but will be replaced in future with
    // 3rd party solution. Logging does what System.out.println() does and
    // more!
    private static final Logger log = Logger.getLogger(LogoutBean.class.getName());

    public String logout() {
        // Notice the redirect syntax. The forward slash means start at
        // the root of the web application.
        String destination = "/index?faces-redirect=true";

        // FacesContext provides access to other container managed objects,
        // such as the HttpServletRequest object, which is needed to perform
        // the logout operation.
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = 
                (HttpServletRequest) context.getExternalContext().getRequest();

        try {
            request.logout();
        } catch (ServletException e) {
            log.log(Level.SEVERE, "Failed to logout user!", e);
            destination = "/loginerror?faces-redirect=true";
        }

        return destination; // go to destination
    }
}

