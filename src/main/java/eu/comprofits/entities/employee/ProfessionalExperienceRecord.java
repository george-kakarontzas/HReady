/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package eu.comprofits.entities.employee;

import java.io.Serializable;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author george
 */
@Entity
@Table(name = "professional_experience_record")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProfessionalExperienceRecord.findAll", query = "SELECT p FROM ProfessionalExperienceRecord p"),
    @NamedQuery(name = "ProfessionalExperienceRecord.findByIdprofessionalExperienceRecord", query = "SELECT p FROM ProfessionalExperienceRecord p WHERE p.idprofessionalExperienceRecord = :idprofessionalExperienceRecord"),
    @NamedQuery(name = "ProfessionalExperienceRecord.findByDateStarted", query = "SELECT p FROM ProfessionalExperienceRecord p WHERE p.dateStarted = :dateStarted"),
    @NamedQuery(name = "ProfessionalExperienceRecord.findByDateFinished", query = "SELECT p FROM ProfessionalExperienceRecord p WHERE p.dateFinished = :dateFinished"),
    @NamedQuery(name = "ProfessionalExperienceRecord.findByCompany", query = "SELECT p FROM ProfessionalExperienceRecord p WHERE p.company = :company"),
    @NamedQuery(name = "ProfessionalExperienceRecord.findByRole", query = "SELECT p FROM ProfessionalExperienceRecord p WHERE p.role = :role"),
    @NamedQuery(name = "ProfessionalExperienceRecord.findByJobTitle", query = "SELECT p FROM ProfessionalExperienceRecord p WHERE p.jobTitle = :jobTitle"),
    @NamedQuery(name = "ProfessionalExperienceRecord.findByFieldOfWork", query = "SELECT p FROM ProfessionalExperienceRecord p WHERE p.fieldOfWork = :fieldOfWork"),
    @NamedQuery(name = "ProfessionalExperienceRecord.findByPlaceOfEmployment", query = "SELECT p FROM ProfessionalExperienceRecord p WHERE p.placeOfEmployment = :placeOfEmployment"),
    @NamedQuery(name = "ProfessionalExperienceRecord.findByImmediateManager", query = "SELECT p FROM ProfessionalExperienceRecord p WHERE p.immediateManager = :immediateManager"),
    @NamedQuery(name = "ProfessionalExperienceRecord.findByBusinessArea", query = "SELECT p FROM ProfessionalExperienceRecord p WHERE p.businessArea = :businessArea"),
    @NamedQuery(name = "ProfessionalExperienceRecord.findByDivision", query = "SELECT p FROM ProfessionalExperienceRecord p WHERE p.division = :division")})
public class ProfessionalExperienceRecord implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idprofessional_experience_record")
    private Integer idprofessionalExperienceRecord;
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
    @Size(max = 100)
    @Column(name = "division")
    private String division;
    @JoinColumn(name = "employee_idemployee", referencedColumnName = "idemployee")
    @ManyToOne(optional = false)
    private Employee employeeIdemployee;

    public ProfessionalExperienceRecord() {
    }

    public ProfessionalExperienceRecord(Integer idprofessionalExperienceRecord) {
        this.idprofessionalExperienceRecord = idprofessionalExperienceRecord;
    }

    public ProfessionalExperienceRecord(Integer idprofessionalExperienceRecord, Date dateStarted, Date dateFinished, String company, String role) {
        this.idprofessionalExperienceRecord = idprofessionalExperienceRecord;
        this.dateStarted = dateStarted;
        this.dateFinished = dateFinished;
        this.company = company;
        this.role = role;
    }

    public Integer getIdprofessionalExperienceRecord() {
        return idprofessionalExperienceRecord;
    }

    public void setIdprofessionalExperienceRecord(Integer idprofessionalExperienceRecord) {
        this.idprofessionalExperienceRecord = idprofessionalExperienceRecord;
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

    public Employee getEmployeeIdemployee() {
        return employeeIdemployee;
    }

    public void setEmployeeIdemployee(Employee employeeIdemployee) {
        this.employeeIdemployee = employeeIdemployee;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idprofessionalExperienceRecord != null ? idprofessionalExperienceRecord.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProfessionalExperienceRecord)) {
            return false;
        }
        ProfessionalExperienceRecord other = (ProfessionalExperienceRecord) object;
        if ((this.idprofessionalExperienceRecord == null && other.idprofessionalExperienceRecord != null) || (this.idprofessionalExperienceRecord != null && !this.idprofessionalExperienceRecord.equals(other.idprofessionalExperienceRecord))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.mavenproject1.ProfessionalExperienceRecord[ idprofessionalExperienceRecord=" + idprofessionalExperienceRecord + " ]";
    }
    
}
