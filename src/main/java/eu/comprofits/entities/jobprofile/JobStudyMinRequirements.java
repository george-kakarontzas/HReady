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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author George Kakarontzas
 */
@Entity
@Table(name = "job_study_min_requirements")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "JobStudyMinRequirements.findAll", query = "SELECT j FROM JobStudyMinRequirements j"),
    @NamedQuery(name = "JobStudyMinRequirements.findByIdjobStudyMinRequirements", query = "SELECT j FROM JobStudyMinRequirements j WHERE j.idjobStudyMinRequirements = :idjobStudyMinRequirements"),
    @NamedQuery(name = "JobStudyMinRequirements.findByRequiredTitleType", query = "SELECT j FROM JobStudyMinRequirements j WHERE j.requiredTitleType = :requiredTitleType")})
public class JobStudyMinRequirements implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idjob_study_min_requirements")
    private Integer idjobStudyMinRequirements;
    @Column(name = "required_title_type")
    private Integer requiredTitleType;
    @JoinColumn(name = "job_idjob", referencedColumnName = "idjob")
    @ManyToOne(optional = false)
    private Job jobIdjob;

    public JobStudyMinRequirements() {
    }

    public JobStudyMinRequirements(Integer idjobStudyMinRequirements) {
        this.idjobStudyMinRequirements = idjobStudyMinRequirements;
    }

    public Integer getIdjobStudyMinRequirements() {
        return idjobStudyMinRequirements;
    }

    public void setIdjobStudyMinRequirements(Integer idjobStudyMinRequirements) {
        this.idjobStudyMinRequirements = idjobStudyMinRequirements;
    }

    public Integer getRequiredTitleType() {
        return requiredTitleType;
    }

    public void setRequiredTitleType(Integer requiredTitleType) {
        this.requiredTitleType = requiredTitleType;
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
        hash += (idjobStudyMinRequirements != null ? idjobStudyMinRequirements.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof JobStudyMinRequirements)) {
            return false;
        }
        JobStudyMinRequirements other = (JobStudyMinRequirements) object;
        if ((this.idjobStudyMinRequirements == null && other.idjobStudyMinRequirements != null) || (this.idjobStudyMinRequirements != null && !this.idjobStudyMinRequirements.equals(other.idjobStudyMinRequirements))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.mavenproject1.JobStudyMinRequirements[ idjobStudyMinRequirements=" + idjobStudyMinRequirements + " ]";
    }
    
}
