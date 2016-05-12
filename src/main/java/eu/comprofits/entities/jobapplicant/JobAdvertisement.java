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

import eu.comprofits.entities.jobprofile.Job;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author George Kakarontzas
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
    @Column(name = "fields_of_responsibility")
    private String fieldsOfResponsibility;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "job_description")
    private String jobDescription;
    @Basic(optional = false)
    @NotNull
    @Column(name = "start_date")
    @Temporal(TemporalType.DATE)
    private Date startDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "end_date")
    @Temporal(TemporalType.DATE)
    private Date endDate;
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

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
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
