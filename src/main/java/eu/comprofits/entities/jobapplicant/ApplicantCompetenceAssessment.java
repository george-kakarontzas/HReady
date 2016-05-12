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

import eu.comprofits.entities.main.Competence;
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
@Table(name = "applicant_competence_assessment")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ApplicantCompetenceAssessment.findAll", query = "SELECT a FROM ApplicantCompetenceAssessment a"),
    @NamedQuery(name = "ApplicantCompetenceAssessment.findByIdapplicantCompetenceAssessment", query = "SELECT a FROM ApplicantCompetenceAssessment a WHERE a.idapplicantCompetenceAssessment = :idapplicantCompetenceAssessment"),
    @NamedQuery(name = "ApplicantCompetenceAssessment.findByValue", query = "SELECT a FROM ApplicantCompetenceAssessment a WHERE a.value = :value")})
public class ApplicantCompetenceAssessment implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idapplicant_competence_assessment")
    private Integer idapplicantCompetenceAssessment;
    @Column(name = "value")
    private Integer value;
    @JoinColumn(name = "job_application_id", referencedColumnName = "idjob_application")
    @ManyToOne(optional = false)
    private JobApplication jobApplicationId;
    @JoinColumn(name = "competence_id", referencedColumnName = "idcompetence")
    @ManyToOne(optional = false)
    private Competence competenceId;

    public ApplicantCompetenceAssessment() {
    }

    public ApplicantCompetenceAssessment(Integer idapplicantCompetenceAssessment) {
        this.idapplicantCompetenceAssessment = idapplicantCompetenceAssessment;
    }

    public Integer getIdapplicantCompetenceAssessment() {
        return idapplicantCompetenceAssessment;
    }

    public void setIdapplicantCompetenceAssessment(Integer idapplicantCompetenceAssessment) {
        this.idapplicantCompetenceAssessment = idapplicantCompetenceAssessment;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public JobApplication getJobApplicationId() {
        return jobApplicationId;
    }

    public void setJobApplicationId(JobApplication jobApplicationId) {
        this.jobApplicationId = jobApplicationId;
    }

    public Competence getCompetenceId() {
        return competenceId;
    }

    public void setCompetenceId(Competence competenceId) {
        this.competenceId = competenceId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idapplicantCompetenceAssessment != null ? idapplicantCompetenceAssessment.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ApplicantCompetenceAssessment)) {
            return false;
        }
        ApplicantCompetenceAssessment other = (ApplicantCompetenceAssessment) object;
        if ((this.idapplicantCompetenceAssessment == null && other.idapplicantCompetenceAssessment != null) || (this.idapplicantCompetenceAssessment != null && !this.idapplicantCompetenceAssessment.equals(other.idapplicantCompetenceAssessment))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.mavenproject1.ApplicantCompetenceAssessment[ idapplicantCompetenceAssessment=" + idapplicantCompetenceAssessment + " ]";
    }
    
}
