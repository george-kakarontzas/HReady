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
package eu.comprofits.entities.jobapplicant;

import java.io.Serializable;
import java.util.Date;
import java.util.ResourceBundle;
import javax.faces.context.FacesContext;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author George Kakarontzas
 */
@Entity
@Table(name = "applicant_study_record")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ApplicantStudyRecord.findAll", query = "SELECT a FROM ApplicantStudyRecord a"),
    @NamedQuery(name = "ApplicantStudyRecord.findByIdstudyRecord", query = "SELECT a FROM ApplicantStudyRecord a WHERE a.idstudyRecord = :idstudyRecord"),
    @NamedQuery(name = "ApplicantStudyRecord.findByTitle", query = "SELECT a FROM ApplicantStudyRecord a WHERE a.title = :title"),
    @NamedQuery(name = "ApplicantStudyRecord.findByTitleType", query = "SELECT a FROM ApplicantStudyRecord a WHERE a.titleType = :titleType"),
    @NamedQuery(name = "ApplicantStudyRecord.findByInstitution", query = "SELECT a FROM ApplicantStudyRecord a WHERE a.institution = :institution"),
    @NamedQuery(name = "ApplicantStudyRecord.findByDateStarted", query = "SELECT a FROM ApplicantStudyRecord a WHERE a.dateStarted = :dateStarted"),
    @NamedQuery(name = "ApplicantStudyRecord.findByDateAcquired", query = "SELECT a FROM ApplicantStudyRecord a WHERE a.dateAcquired = :dateAcquired"),
    @NamedQuery(name = "ApplicantStudyRecord.findByApplicant", query = "SELECT a FROM ApplicantStudyRecord a WHERE a.jobApplicantIdjobApplicant = :applicant")})
public class ApplicantStudyRecord implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idstudy_record")
    private Integer idstudyRecord;
    @Size(max = 45)
    @Column(name = "title")
    private String title;
    @Column(name = "title_type")
    private Integer titleType;
    @Size(max = 45)
    @Column(name = "institution")
    private String institution;
    @Column(name = "date_started")
    @Temporal(TemporalType.DATE)
    private Date dateStarted;
    @Column(name = "date_acquired")
    @Temporal(TemporalType.DATE)
    private Date dateAcquired;
    @JoinColumn(name = "job_applicant_idjob_applicant", referencedColumnName = "idjob_applicant")
    @ManyToOne(optional = false)
    private JobApplicant jobApplicantIdjobApplicant;

    public ApplicantStudyRecord() {
    }

    public ApplicantStudyRecord(Integer idstudyRecord) {
        this.idstudyRecord = idstudyRecord;
    }

    public Integer getIdstudyRecord() {
        return idstudyRecord;
    }

    public void setIdstudyRecord(Integer idstudyRecord) {
        this.idstudyRecord = idstudyRecord;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getTitleType() {
        return titleType;
    }

    public void setTitleType(Integer titleType) {
        this.titleType = titleType;
    }

    public String getInstitution() {
        return institution;
    }

    public void setInstitution(String institution) {
        this.institution = institution;
    }

    public Date getDateStarted() {
        return dateStarted;
    }

    public void setDateStarted(Date dateStarted) {
        this.dateStarted = dateStarted;
    }

    public Date getDateAcquired() {
        return dateAcquired;
    }

    public void setDateAcquired(Date dateAcquired) {
        this.dateAcquired = dateAcquired;
    }

    public JobApplicant getJobApplicantIdjobApplicant() {
        return jobApplicantIdjobApplicant;
    }

    public void setJobApplicantIdjobApplicant(JobApplicant jobApplicantIdjobApplicant) {
        this.jobApplicantIdjobApplicant = jobApplicantIdjobApplicant;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idstudyRecord != null ? idstudyRecord.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ApplicantStudyRecord)) {
            return false;
        }
        ApplicantStudyRecord other = (ApplicantStudyRecord) object;
        if ((this.idstudyRecord == null && other.idstudyRecord != null) || (this.idstudyRecord != null && !this.idstudyRecord.equals(other.idstudyRecord))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.mavenproject1.ApplicantStudyRecord[ idstudyRecord=" + idstudyRecord + " ]";
    }
    
    public String getTitleTypeName() {
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
    
}
