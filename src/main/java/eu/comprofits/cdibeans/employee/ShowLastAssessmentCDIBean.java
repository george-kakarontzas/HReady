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
package eu.comprofits.cdibeans.employee;

import eu.comprofits.entities.assessment.Assessment;
import eu.comprofits.entities.employee.Employee;
import eu.comprofits.session.assessment.AssessmentFacade;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author George Kakarontzas
 */
@Named(value = "showLastAssessmentCDIBean")
@RequestScoped
public class ShowLastAssessmentCDIBean {

    @EJB
    private AssessmentFacade assessmentFacade;
    private Assessment lastAssessment;

    private Employee employee;

    /**
     * Creates a new instance of EditPastCompanyEmploymentsCDIBean
     */
    public ShowLastAssessmentCDIBean() {
    }

    @PostConstruct
    public void init() {
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        employee = (Employee) externalContext.getSessionMap().get("employee");
        List<Assessment> allCompletedAssessments
                = assessmentFacade.getAssesseeEmployeeAssessments(employee);
        lastAssessment = null;
        if (allCompletedAssessments != null) {
            for (Assessment a : allCompletedAssessments) {
                if (lastAssessment == null && a.isCompleted()) {
                    lastAssessment = a;
                } else if ((lastAssessment.getDeadline().before(a.getDeadline())) && a.isCompleted()) {
                    lastAssessment = a;
                }
            }
        }
    }

    public boolean isLastAssessmentNotNull() {
        return lastAssessment != null;
    }

    public Assessment getLastAssessment() {
        return lastAssessment;
    }

    public Employee getEmployee() {
        return employee;
    }
    
}
