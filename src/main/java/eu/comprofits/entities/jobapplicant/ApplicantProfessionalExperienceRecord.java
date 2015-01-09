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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author george
 */
@Entity
@Table(name = "applicant_professional_experience_record")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ApplicantProfessionalExperienceRecord.findAll", query = "SELECT a FROM ApplicantProfessionalExperienceRecord a"),
    @NamedQuery(name = "ApplicantProfessionalExperienceRecord.findByIdapplicantProfessionalExperienceRecord", query = "SELECT a FROM ApplicantProfessionalExperienceRecord a WHERE a.idapplicantProfessionalExperienceRecord = :idapplicantProfessionalExperienceRecord"),
    @NamedQuery(name = "ApplicantProfessionalExperienceRecord.findByDateStarted", query = "SELECT a FROM ApplicantProfessionalExperienceRecord a WHERE a.dateStarted = :dateStarted"),
    @NamedQuery(name = "ApplicantProfessionalExperienceRecord.findByDateFinished", query = "SELECT a FROM ApplicantProfessionalExperienceRecord a WHERE a.dateFinished = :dateFinished"),
    @NamedQuery(name = "ApplicantProfessionalExperienceRecord.findByCompany", query = "SELECT a FROM ApplicantProfessionalExperienceRecord a WHERE a.company = :company"),
    @NamedQuery(name = "ApplicantProfessionalExperienceRecord.findByRole", query = "SELECT a FROM ApplicantProfessionalExperienceRecord a WHERE a.role = :role"),
    @NamedQuery(name = "ApplicantProfessionalExperienceRecord.findByJobTitle", query = "SELECT a FROM ApplicantProfessionalExperienceRecord a WHERE a.jobTitle = :jobTitle"),
    @NamedQuery(name = "ApplicantProfessionalExperienceRecord.findByFieldOfWork", query = "SELECT a FROM ApplicantProfessionalExperienceRecord a WHERE a.fieldOfWork = :fieldOfWork"),
    @NamedQuery(name = "ApplicantProfessionalExperienceRecord.findByPlaceOfEmployment", query = "SELECT a FROM ApplicantProfessionalExperienceRecord a WHERE a.placeOfEmployment = :placeOfEmployment"),
    @NamedQuery(name = "ApplicantProfessionalExperienceRecord.findByImmediateManager", query = "SELECT a FROM ApplicantProfessionalExperienceRecord a WHERE a.immediateManager = :immediateManager"),
    @NamedQuery(name = "ApplicantProfessionalExperienceRecord.findByBusinessArea", query = "SELECT a FROM ApplicantProfessionalExperienceRecord a WHERE a.businessArea = :businessArea"),
    @NamedQuery(name = "ApplicantProfessionalExperienceRecord.findByDivision", query = "SELECT a FROM ApplicantProfessionalExperienceRecord a WHERE a.division = :division")})
public class ApplicantProfessionalExperienceRecord implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idapplicant_professional_experience_record")
    private Integer idapplicantProfessionalExperienceRecord;
    @Basic(optional = false)
    @NotNull
    @Column(name = "date_started")
    @Temporal(TemporalType.DATE)
    private Date dateStarted;
    @Basic(optional = false)
    @NotNull
    @Column(name = "date_finished")
    @Temporal(TemporalType.DATE)
    private Date dateFinished;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "company")
    private String company;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "role")
    private String role;
    @Size(max = 45)
    @Column(name = "job_title")
    private String jobTitle;
    @Size(max = 45)
    @Column(name = "field_of_work")
    private String fieldOfWork;
    @Size(max = 45)
    @Column(name = "place_of_employment")
    private String placeOfEmployment;
    @Size(max = 100)
    @Column(name = "immediate_manager")
    private String immediateManager;
    @Size(max = 45)
    @Column(name = "business_area")
    private String businessArea;
    @Size(max = 45)
    @Column(name = "division")
    private String division;
    @JoinColumn(name = "job_applicant_idjob_applicant", referencedColumnName = "idjob_applicant")
    @ManyToOne(optional = false)
    private JobApplicant jobApplicantIdjobApplicant;
    @OneToMany(mappedBy = "presentIdapplicantProfessionalExperienceRecord")
    private Collection<JobApplicant> jobApplicantCollection;

    public ApplicantProfessionalExperienceRecord() {
    }

    public ApplicantProfessionalExperienceRecord(Integer idapplicantProfessionalExperienceRecord) {
        this.idapplicantProfessionalExperienceRecord = idapplicantProfessionalExperienceRecord;
    }

    public ApplicantProfessionalExperienceRecord(Integer idapplicantProfessionalExperienceRecord, Date dateStarted, Date dateFinished, String company, String role) {
        this.idapplicantProfessionalExperienceRecord = idapplicantProfessionalExperienceRecord;
        this.dateStarted = dateStarted;
        this.dateFinished = dateFinished;
        this.company = company;
        this.role = role;
    }

    public Integer getIdapplicantProfessionalExperienceRecord() {
        return idapplicantProfessionalExperienceRecord;
    }

    public void setIdapplicantProfessionalExperienceRecord(Integer idapplicantProfessionalExperienceRecord) {
        this.idapplicantProfessionalExperienceRecord = idapplicantProfessionalExperienceRecord;
    }

    public Date getDateStarted() {
        return dateStarted;
    }

    public void setDateStarted(Date dateStarted) {
        this.dateStarted = dateStarted;
    }

    public Date getDateFinished() {
        return dateFinished;
    }

    public void setDateFinished(Date dateFinished) {
        this.dateFinished = dateFinished;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getFieldOfWork() {
        return fieldOfWork;
    }

    public void setFieldOfWork(String fieldOfWork) {
        this.fieldOfWork = fieldOfWork;
    }

    public String getPlaceOfEmployment() {
        return placeOfEmployment;
    }

    public void setPlaceOfEmployment(String placeOfEmployment) {
        this.placeOfEmployment = placeOfEmployment;
    }

    public String getImmediateManager() {
        return immediateManager;
    }

    public void setImmediateManager(String immediateManager) {
        this.immediateManager = immediateManager;
    }

    public String getBusinessArea() {
        return businessArea;
    }

    public void setBusinessArea(String businessArea) {
        this.businessArea = businessArea;
    }

    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    public JobApplicant getJobApplicantIdjobApplicant() {
        return jobApplicantIdjobApplicant;
    }

    public void setJobApplicantIdjobApplicant(JobApplicant jobApplicantIdjobApplicant) {
        this.jobApplicantIdjobApplicant = jobApplicantIdjobApplicant;
    }

    @XmlTransient
    public Collection<JobApplicant> getJobApplicantCollection() {
        return jobApplicantCollection;
    }

    public void setJobApplicantCollection(Collection<JobApplicant> jobApplicantCollection) {
        this.jobApplicantCollection = jobApplicantCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idapplicantProfessionalExperienceRecord != null ? idapplicantProfessionalExperienceRecord.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ApplicantProfessionalExperienceRecord)) {
            return false;
        }
        ApplicantProfessionalExperienceRecord other = (ApplicantProfessionalExperienceRecord) object;
        if ((this.idapplicantProfessionalExperienceRecord == null && other.idapplicantProfessionalExperienceRecord != null) || (this.idapplicantProfessionalExperienceRecord != null && !this.idapplicantProfessionalExperienceRecord.equals(other.idapplicantProfessionalExperienceRecord))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.mavenproject1.ApplicantProfessionalExperienceRecord[ idapplicantProfessionalExperienceRecord=" + idapplicantProfessionalExperienceRecord + " ]";
    }
    
}
