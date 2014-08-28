/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package eu.comprofits.entities.jobapplicant;

import eu.comprofits.entities.jobprofile.Job;
import java.io.Serializable;
import java.util.Collection;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author george
 */
@Entity
@Table(name = "job_advertisement")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "JobAdvertisement.findAll", query = "SELECT j FROM JobAdvertisement j"),
    @NamedQuery(name = "JobAdvertisement.findByIdjobAdvertisement", query = "SELECT j FROM JobAdvertisement j WHERE j.idjobAdvertisement = :idjobAdvertisement"),
    @NamedQuery(name = "JobAdvertisement.findByJobTitle", query = "SELECT j FROM JobAdvertisement j WHERE j.jobTitle = :jobTitle"),
    @NamedQuery(name = "JobAdvertisement.findByFieldsOfResponsibility", query = "SELECT j FROM JobAdvertisement j WHERE j.fieldsOfResponsibility = :fieldsOfResponsibility"),
    @NamedQuery(name = "JobAdvertisement.findByJobDescription", query = "SELECT j FROM JobAdvertisement j WHERE j.jobDescription = :jobDescription")})
public class JobAdvertisement implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idjob_advertisement")
    private Integer idjobAdvertisement;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "job_title")
    private String jobTitle;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "fields_of_responsibility")
    private String fieldsOfResponsibility;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "job_description")
    private String jobDescription;
    @JoinColumn(name = "job_idjob", referencedColumnName = "idjob")
    @ManyToOne(optional = false)
    private Job jobIdjob;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "jobAdvertisementIdjobAdvertisement")
    private Collection<JobApplication> jobApplicationCollection;

    public JobAdvertisement() {
    }

    public JobAdvertisement(Integer idjobAdvertisement) {
        this.idjobAdvertisement = idjobAdvertisement;
    }

    public JobAdvertisement(Integer idjobAdvertisement, String jobTitle, String fieldsOfResponsibility, String jobDescription) {
        this.idjobAdvertisement = idjobAdvertisement;
        this.jobTitle = jobTitle;
        this.fieldsOfResponsibility = fieldsOfResponsibility;
        this.jobDescription = jobDescription;
    }

    public Integer getIdjobAdvertisement() {
        return idjobAdvertisement;
    }

    public void setIdjobAdvertisement(Integer idjobAdvertisement) {
        this.idjobAdvertisement = idjobAdvertisement;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getFieldsOfResponsibility() {
        return fieldsOfResponsibility;
    }

    public void setFieldsOfResponsibility(String fieldsOfResponsibility) {
        this.fieldsOfResponsibility = fieldsOfResponsibility;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    public Job getJobIdjob() {
        return jobIdjob;
    }

    public void setJobIdjob(Job jobIdjob) {
        this.jobIdjob = jobIdjob;
    }

    @XmlTransient
    public Collection<JobApplication> getJobApplicationCollection() {
        return jobApplicationCollection;
    }

    public void setJobApplicationCollection(Collection<JobApplication> jobApplicationCollection) {
        this.jobApplicationCollection = jobApplicationCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idjobAdvertisement != null ? idjobAdvertisement.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof JobAdvertisement)) {
            return false;
        }
        JobAdvertisement other = (JobAdvertisement) object;
        if ((this.idjobAdvertisement == null && other.idjobAdvertisement != null) || (this.idjobAdvertisement != null && !this.idjobAdvertisement.equals(other.idjobAdvertisement))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.mavenproject1.JobAdvertisement[ idjobAdvertisement=" + idjobAdvertisement + " ]";
    }
    
}
