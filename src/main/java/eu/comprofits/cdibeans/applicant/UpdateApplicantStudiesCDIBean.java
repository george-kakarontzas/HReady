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
package eu.comprofits.cdibeans.applicant;

import eu.comprofits.entities.jobapplicant.ApplicantStudyRecord;
import eu.comprofits.entities.jobapplicant.JobApplicant;
import eu.comprofits.session.jobapplicant.ApplicantStudyRecordFacade;
import java.io.Serializable;
import static java.lang.System.out;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author ckopanos
 */
@Named(value = "updateApplicantStudiesCDIBean")
@SessionScoped
public class UpdateApplicantStudiesCDIBean implements Serializable {

    @EJB ApplicantStudyRecordFacade studyRecordFacade;
    private List<ApplicantStudyRecord> studies;
    private JobApplicant jobApplicant;
    private ApplicantStudyRecord studyRecord;

    public ApplicantStudyRecord getStudyRecord() {
        return studyRecord;
    }

    public void setStudyRecord(ApplicantStudyRecord studyRecord) {
        this.studyRecord = studyRecord;
    }

    public JobApplicant getJobApplicant() {
        return jobApplicant;
    }

    public void setJobApplicant(JobApplicant jobApplicant) {
        this.jobApplicant = jobApplicant;
    }

    public List<ApplicantStudyRecord> getStudies() {
        return studies;
    }

    public void setStudies(List<ApplicantStudyRecord> studies) {
        this.studies = studies;
    }
    
    public UpdateApplicantStudiesCDIBean() {
    }
    
    @PostConstruct
    public void init() {
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        jobApplicant =  (JobApplicant) externalContext.getSessionMap().get("applicant");
        refreshStudiesList();
    }

    private void refreshStudiesList() {
        studies = studyRecordFacade.findByApplicant(jobApplicant);
    }
    
    
    public void remove(ApplicantStudyRecord s) {
        try {
            studyRecordFacade.remove(s);           
            refreshStudiesList();
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            ex.getMessage(), null));
        }
    }

    public String edit(ApplicantStudyRecord s) {
        this.studyRecord = s;
        return "editStudyRecord";
    }

    public String getTitleTypeName(ApplicantStudyRecord s) {
        return s.getTitleTypeName();
    }
    
    public String create() {
        this.studyRecord =
                new ApplicantStudyRecord();
        studyRecord.setJobApplicantIdjobApplicant(jobApplicant);
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
        refreshStudiesList();
        return "updateStudies";
    }
    
}
