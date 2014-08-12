/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package eu.comprofits.cdibeans.employee;

import eu.comprofits.entities.employee.Employee;
import eu.comprofits.entities.employee.StudyRecord;
import eu.comprofits.session.employee.StudyRecordFacade;
import java.io.Serializable;
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
 * @author george
 */
@Named(value = "updateStudiesCDIBean")
@SessionScoped
public class UpdateStudiesCDIBean implements Serializable {   
    @EJB
    private StudyRecordFacade studyRecordFacade;
    
    private Employee employee;
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
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        employee = (Employee) externalContext.getSessionMap().get("employee");
        refreshStudyRecsList();
    }

    public Employee getEmployee() {
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        employee = (Employee) externalContext.getSessionMap().get("employee");
        return employee;
    }

    public List<StudyRecord> getStudyrecs() {
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
        }
        return " ";
    }

    private void refreshStudyRecsList() {
        studyrecs = 
            studyRecordFacade.getStudyRecsForEmployee(employee);
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
        this.studyRecord =
                new StudyRecord();
        studyRecord.setEmployeeIdemployee(employee);
        return "editStudyRecord";
    }
   
    public String update() {
        try {
            if (studyRecord.getIdstudyRecord()==null) {
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
