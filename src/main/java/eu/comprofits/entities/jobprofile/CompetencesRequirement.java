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
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author George Kakarontzas
 */
@Entity
@Table(name = "competences_requirement")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CompetencesRequirement.findAll", query = "SELECT c FROM CompetencesRequirement c"),
    @NamedQuery(name = "CompetencesRequirement.findByIdcompetencesRequirement", query = "SELECT c FROM CompetencesRequirement c WHERE c.idcompetencesRequirement = :idcompetencesRequirement"),
    @NamedQuery(name = "CompetencesRequirement.findByWeight", query = "SELECT c FROM CompetencesRequirement c WHERE c.weight = :weight")})
public class CompetencesRequirement implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idcompetences_requirement")
    private Integer idcompetencesRequirement;
    @Basic(optional = false)
    @NotNull
    @Column(name = "weight")
    private int weight;
    @Basic(optional = false)
    @NotNull
    @Column(name = "importance")
    private int importance;
    @JoinColumn(name = "job_idjob", referencedColumnName = "idjob")
    @ManyToOne(optional = false)
    private Job jobIdjob;
    @JoinColumn(name = "competence_idcompetence", referencedColumnName = "idcompetence")
    @ManyToOne(optional = false)
    private Competence competenceIdcompetence;

    public CompetencesRequirement() {
    }

    public CompetencesRequirement(Integer idcompetencesRequirement) {
        this.idcompetencesRequirement = idcompetencesRequirement;
    }

    public CompetencesRequirement(Integer idcompetencesRequirement, int weight, int importance) {
        this.idcompetencesRequirement = idcompetencesRequirement;
        this.weight = weight;
        this.importance = importance;
    }

    public Integer getIdcompetencesRequirement() {
        return idcompetencesRequirement;
    }

    public void setIdcompetencesRequirement(Integer idcompetencesRequirement) {
        this.idcompetencesRequirement = idcompetencesRequirement;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
    
    public int getImportance() {
        return importance;
    }

    public void setImportance(int importance) {
        this.importance = importance;
    }

    public Job getJobIdjob() {
        return jobIdjob;
    }

    public void setJobIdjob(Job jobIdjob) {
        this.jobIdjob = jobIdjob;
    }

    public Competence getCompetenceIdcompetence() {
        return competenceIdcompetence;
    }

    public void setCompetenceIdcompetence(Competence competenceIdcompetence) {
        this.competenceIdcompetence = competenceIdcompetence;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcompetencesRequirement != null ? idcompetencesRequirement.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CompetencesRequirement)) {
            return false;
        }
        CompetencesRequirement other = (CompetencesRequirement) object;
        if ((this.idcompetencesRequirement == null && other.idcompetencesRequirement != null) || (this.idcompetencesRequirement != null && !this.idcompetencesRequirement.equals(other.idcompetencesRequirement))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.mavenproject1.CompetencesRequirement[ idcompetencesRequirement=" + idcompetencesRequirement + " ]";
    }
    
    public String getCompetenceName() {
        return this.getCompetenceIdcompetence().getCompetenceName();
    }
    
    public String getJobTitle() {
        return this.getJobIdjob().getJobTitle();
    }
    
}
