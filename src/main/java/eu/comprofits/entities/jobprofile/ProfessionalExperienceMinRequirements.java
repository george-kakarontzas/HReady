/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package eu.comprofits.entities.jobprofile;

import java.io.Serializable;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author george
 */
@Entity
@Table(name = "professional_experience_min_requirements")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProfessionalExperienceMinRequirements.findAll", query = "SELECT p FROM ProfessionalExperienceMinRequirements p"),
    @NamedQuery(name = "ProfessionalExperienceMinRequirements.findByIdprofessionalExperienceMinRequirements", query = "SELECT p FROM ProfessionalExperienceMinRequirements p WHERE p.idprofessionalExperienceMinRequirements = :idprofessionalExperienceMinRequirements"),
    @NamedQuery(name = "ProfessionalExperienceMinRequirements.findByRequiredExperienceYears", query = "SELECT p FROM ProfessionalExperienceMinRequirements p WHERE p.requiredExperienceYears = :requiredExperienceYears"),
    @NamedQuery(name = "ProfessionalExperienceMinRequirements.findByRequiredProfExperienceDescription", query = "SELECT p FROM ProfessionalExperienceMinRequirements p WHERE p.requiredProfExperienceDescription = :requiredProfExperienceDescription")})
public class ProfessionalExperienceMinRequirements implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idprofessional_experience_min_requirements")
    private Integer idprofessionalExperienceMinRequirements;
    @Basic(optional = false)
    @NotNull
    @Column(name = "required_experience_years")
    private int requiredExperienceYears;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "required_prof_experience_description")
    private String requiredProfExperienceDescription;
    @JoinColumn(name = "job_idjob", referencedColumnName = "idjob")
    @ManyToOne(optional = false)
    private Job jobIdjob;

    public ProfessionalExperienceMinRequirements() {
    }

    public ProfessionalExperienceMinRequirements(Integer idprofessionalExperienceMinRequirements) {
        this.idprofessionalExperienceMinRequirements = idprofessionalExperienceMinRequirements;
    }

    public ProfessionalExperienceMinRequirements(Integer idprofessionalExperienceMinRequirements, int requiredExperienceYears, String requiredProfExperienceDescription) {
        this.idprofessionalExperienceMinRequirements = idprofessionalExperienceMinRequirements;
        this.requiredExperienceYears = requiredExperienceYears;
        this.requiredProfExperienceDescription = requiredProfExperienceDescription;
    }

    public Integer getIdprofessionalExperienceMinRequirements() {
        return idprofessionalExperienceMinRequirements;
    }

    public void setIdprofessionalExperienceMinRequirements(Integer idprofessionalExperienceMinRequirements) {
        this.idprofessionalExperienceMinRequirements = idprofessionalExperienceMinRequirements;
    }

    public int getRequiredExperienceYears() {
        return requiredExperienceYears;
    }

    public void setRequiredExperienceYears(int requiredExperienceYears) {
        this.requiredExperienceYears = requiredExperienceYears;
    }

    public String getRequiredProfExperienceDescription() {
        return requiredProfExperienceDescription;
    }

    public void setRequiredProfExperienceDescription(String requiredProfExperienceDescription) {
        this.requiredProfExperienceDescription = requiredProfExperienceDescription;
    }

    public Job getJobIdjob() {
        return jobIdjob;
    }

    public void setJobIdjob(Job jobIdjob) {
        this.jobIdjob = jobIdjob;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idprofessionalExperienceMinRequirements != null ? idprofessionalExperienceMinRequirements.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProfessionalExperienceMinRequirements)) {
            return false;
        }
        ProfessionalExperienceMinRequirements other = (ProfessionalExperienceMinRequirements) object;
        if ((this.idprofessionalExperienceMinRequirements == null && other.idprofessionalExperienceMinRequirements != null) || (this.idprofessionalExperienceMinRequirements != null && !this.idprofessionalExperienceMinRequirements.equals(other.idprofessionalExperienceMinRequirements))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.mavenproject1.ProfessionalExperienceMinRequirements[ idprofessionalExperienceMinRequirements=" + idprofessionalExperienceMinRequirements + " ]";
    }
    
}
