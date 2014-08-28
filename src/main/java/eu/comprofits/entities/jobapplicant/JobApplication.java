/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package eu.comprofits.entities.jobapplicant;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author george
 */
@Entity
@Table(name = "job_application")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "JobApplication.findAll", query = "SELECT j FROM JobApplication j"),
    @NamedQuery(name = "JobApplication.findByIdjobApplication", query = "SELECT j FROM JobApplication j WHERE j.idjobApplication = :idjobApplication"),
    @NamedQuery(name = "JobApplication.findByDate", query = "SELECT j FROM JobApplication j WHERE j.date = :date")})
public class JobApplication implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idjob_application")
    private Integer idjobApplication;
    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    private Date date;
    @JoinColumn(name = "job_applicant_idjob_applicant", referencedColumnName = "idjob_applicant")
    @ManyToOne(optional = false)
    private JobApplicant jobApplicantIdjobApplicant;
    @JoinColumn(name = "job_advertisement_idjob_advertisement", referencedColumnName = "idjob_advertisement")
    @ManyToOne(optional = false)
    private JobAdvertisement jobAdvertisementIdjobAdvertisement;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "jobApplicationId")
    private Collection<ApplicantCompetenceAssessment> applicantCompetenceAssessmentCollection;

    public JobApplication() {
    }

    public JobApplication(Integer idjobApplication) {
        this.idjobApplication = idjobApplication;
    }

    public Integer getIdjobApplication() {
        return idjobApplication;
    }

    public void setIdjobApplication(Integer idjobApplication) {
        this.idjobApplication = idjobApplication;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public JobApplicant getJobApplicantIdjobApplicant() {
        return jobApplicantIdjobApplicant;
    }

    public void setJobApplicantIdjobApplicant(JobApplicant jobApplicantIdjobApplicant) {
        this.jobApplicantIdjobApplicant = jobApplicantIdjobApplicant;
    }

    public JobAdvertisement getJobAdvertisementIdjobAdvertisement() {
        return jobAdvertisementIdjobAdvertisement;
    }

    public void setJobAdvertisementIdjobAdvertisement(JobAdvertisement jobAdvertisementIdjobAdvertisement) {
        this.jobAdvertisementIdjobAdvertisement = jobAdvertisementIdjobAdvertisement;
    }

    @XmlTransient
    public Collection<ApplicantCompetenceAssessment> getApplicantCompetenceAssessmentCollection() {
        return applicantCompetenceAssessmentCollection;
    }

    public void setApplicantCompetenceAssessmentCollection(Collection<ApplicantCompetenceAssessment> applicantCompetenceAssessmentCollection) {
        this.applicantCompetenceAssessmentCollection = applicantCompetenceAssessmentCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idjobApplication != null ? idjobApplication.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof JobApplication)) {
            return false;
        }
        JobApplication other = (JobApplication) object;
        if ((this.idjobApplication == null && other.idjobApplication != null) || (this.idjobApplication != null && !this.idjobApplication.equals(other.idjobApplication))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.mavenproject1.JobApplication[ idjobApplication=" + idjobApplication + " ]";
    }
    
}
