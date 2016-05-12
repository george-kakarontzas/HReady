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

import eu.comprofits.entities.employee.Employee;
import eu.comprofits.entities.employee.StudyRecord;
import eu.comprofits.session.employee.EmployeeFacade;
import eu.comprofits.session.employee.StudyRecordFacade;
import java.io.Serializable;
import java.security.Principal;
import java.util.List;
import java.util.ResourceBundle;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author George Kakarontzas
 */
@Named(value = "updateStudiesCDIBean")
@SessionScoped
public class UpdateStudiesCDIBean implements Serializable {

    @EJB
    private StudyRecordFacade studyRecordFacade;
    @EJB
    private EmployeeFacade employeeFacade;
    private StudyRecord studyRecord;
    private List<StudyRecord> studyrecs;
    private String titleName;

    /**
     * Creates a new instance of UpdateStudiesCDIBean
     */
    public UpdateStudiesCDIBean() {
    }

    @PostConstruct
    public void init() {
        refreshStudyRecsList();
    }

    private Employee getCurrentEmployee() {
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        Employee e = (Employee) externalContext.getSessionMap().get("user");
        if (e == null) {
            Principal principal
                    = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal();
            if (principal != null) {
                e = employeeFacade.getEmployeeByUsername(principal.getName()); // Find User by j_username.
            }
        }
        return e;
    }

    public Employee getEmployee() {
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        Employee employee = (Employee) externalContext.getSessionMap().get("employee");
        if (employee == null) {
            employee = this.getCurrentEmployee();
        }
        return employee;
    }

    public List<StudyRecord> getStudyrecs() {
        refreshStudyRecsList();
        return studyrecs;
    }

    public StudyRecord getStudyRecord() {
        return studyRecord;
    }

    public void setStudyRecord(StudyRecord studyRecord) {
        this.studyRecord = studyRecord;
    }

    public String getTitleName(Integer titleType) {
        FacesContext context = FacesContext.getCurrentInstance();
        ResourceBundle bundle = context.getApplication().getResourceBundle(context, "msgs");
        switch (titleType) {
            case 1:
                return bundle.getString("university_degree");
            case 2:
                return bundle.getString("master_degree");
            case 3:
                return bundle.getString("phd");
            case 4:
                return bundle.getString("postdoc");
            case 5:
                return bundle.getString("professional_education");
            case 6:
                return bundle.getString("continuing_training");
            case 7:
                return bundle.getString("other_training");
        }
        return " ";
    }

    private void refreshStudyRecsList() {
        studyrecs
                = studyRecordFacade.getStudyRecsForEmployee(this.getEmployee());
    }

    public void remove(StudyRecord s) {
        try {
            studyRecordFacade.remove(s);
            refreshStudyRecsList();
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            ex.getMessage(), null));
        }
    }

    public String edit(StudyRecord s) {
        this.studyRecord = s;
        return "editStudyRecord";
    }

    public String create() {
        this.studyRecord
                = new StudyRecord();
        studyRecord.setEmployeeIdemployee(this.getEmployee());
        return "editStudyRecord";
    }

    public String update() {
        try {
            if (studyRecord.getIdstudyRecord() == null) {
                studyRecordFacade.create(studyRecord);
            } else {
                studyRecordFacade.edit(studyRecord);
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            e.getMessage(), null));
        }
        refreshStudyRecsList();
        return "updateStudies";
    }
}
