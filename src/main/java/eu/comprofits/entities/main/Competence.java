/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package eu.comprofits.entities.main;

import eu.comprofits.entities.assessment.EmployeeCompetenceAssessment;
import eu.comprofits.entities.assessment.Statement;
import eu.comprofits.entities.edr.CompetenceGoal;
import eu.comprofits.entities.employee.CurrentCompetenceAssessment;
import eu.comprofits.entities.jobapplicant.ApplicantCompetenceAssessment;
import eu.comprofits.entities.jobprofile.CompetencesRequirement;
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
import javax.persistence.Transient;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author george
 */
@Entity
@Table(name = "competence")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Competence.findAll", query = "SELECT c FROM Competence c"),
    @NamedQuery(name = "Competence.findByIdcompetence", query = "SELECT c FROM Competence c WHERE c.idcompetence = :idcompetence"),
    @NamedQuery(name = "Competence.findByCompetenceName", query = "SELECT c FROM Competence c WHERE c.competenceName = :competenceName")})
public class Competence implements Serializable {
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "competenceId")
    private Collection<Statement> statementCollection;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idcompetence")
    private Integer idcompetence;
    @Size(max = 100)
    @Column(name = "competence_name")
    private String competenceName;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "competenceIdcompetence")
    private Collection<CurrentCompetenceAssessment> currentCompetenceAssessmentCollection;
    @OneToMany(mappedBy = "parentId")
    private Collection<Competence> competenceCollection;
    @JoinColumn(name = "parent_id", referencedColumnName = "idcompetence")
    @ManyToOne
    private Competence parentId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "competenceIdcompetence")
    private Collection<EmployeeCompetenceAssessment> employeeCompetenceAssessmentCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "competenceIdcompetence")
    private Collection<CompetenceGoal> competenceGoalCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "competenceId")
    private Collection<ApplicantCompetenceAssessment> applicantCompetenceAssessmentCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "competenceIdcompetence")
    private Collection<CompetencesRequirement> competencesRequirementCollection;

    public Competence() {
    }

    public Competence(Integer idcompetence) {
        this.idcompetence = idcompetence;
    }

    public Integer getIdcompetence() {
        return idcompetence;
    }

    public void setIdcompetence(Integer idcompetence) {
        this.idcompetence = idcompetence;
    }

    public String getCompetenceName() {
        return competenceName;
    }

    public void setCompetenceName(String competenceName) {
        this.competenceName = competenceName;
    }

    @XmlTransient
    public Collection<CurrentCompetenceAssessment> getCurrentCompetenceAssessmentCollection() {
        return currentCompetenceAssessmentCollection;
    }

    public void setCurrentCompetenceAssessmentCollection(Collection<CurrentCompetenceAssessment> currentCompetenceAssessmentCollection) {
        this.currentCompetenceAssessmentCollection = currentCompetenceAssessmentCollection;
    }

    @XmlTransient
    public Collection<Competence> getCompetenceCollection() {
        return competenceCollection;
    }

    public void setCompetenceCollection(Collection<Competence> competenceCollection) {
        this.competenceCollection = competenceCollection;
    }

    public Competence getParentId() {
        return parentId;
    }

    public void setParentId(Competence parentId) {
        this.parentId = parentId;
    }
    
    @Transient 
    public int getLevel() {
        int level=1;
        Competence p = this.getParentId();
        while (p!=null) {
            p=p.getParentId();
            level++;
        }
        return level;
    }

    @XmlTransient
    public Collection<EmployeeCompetenceAssessment> getEmployeeCompetenceAssessmentCollection() {
        return employeeCompetenceAssessmentCollection;
    }

    public void setEmployeeCompetenceAssessmentCollection(Collection<EmployeeCompetenceAssessment> employeeCompetenceAssessmentCollection) {
        this.employeeCompetenceAssessmentCollection = employeeCompetenceAssessmentCollection;
    }

    @XmlTransient
    public Collection<CompetenceGoal> getCompetenceGoalCollection() {
        return competenceGoalCollection;
    }

    public void setCompetenceGoalCollection(Collection<CompetenceGoal> competenceGoalCollection) {
        this.competenceGoalCollection = competenceGoalCollection;
    }

    @XmlTransient
    public Collection<ApplicantCompetenceAssessment> getApplicantCompetenceAssessmentCollection() {
        return applicantCompetenceAssessmentCollection;
    }

    public void setApplicantCompetenceAssessmentCollection(Collection<ApplicantCompetenceAssessment> applicantCompetenceAssessmentCollection) {
        this.applicantCompetenceAssessmentCollection = applicantCompetenceAssessmentCollection;
    }

    @XmlTransient
    public Collection<CompetencesRequirement> getCompetencesRequirementCollection() {
        return competencesRequirementCollection;
    }

    public void setCompetencesRequirementCollection(Collection<CompetencesRequirement> competencesRequirementCollection) {
        this.competencesRequirementCollection = competencesRequirementCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcompetence != null ? idcompetence.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Competence)) {
            return false;
        }
        Competence other = (Competence) object;
        if ((this.idcompetence == null && other.idcompetence != null) || (this.idcompetence != null && !this.idcompetence.equals(other.idcompetence))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.mavenproject1.Competence[ idcompetence=" + idcompetence + " ]";
    }

    @XmlTransient
    public Collection<Statement> getStatementCollection() {
        return statementCollection;
    }

    public void setStatementCollection(Collection<Statement> statementCollection) {
        this.statementCollection = statementCollection;
    }
    
}
