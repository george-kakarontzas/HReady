/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
 * @author george
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

    public CompetencesRequirement(Integer idcompetencesRequirement, int weight) {
        this.idcompetencesRequirement = idcompetencesRequirement;
        this.weight = weight;
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
