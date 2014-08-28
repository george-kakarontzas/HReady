/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package eu.comprofits.entities.assessment;

import eu.comprofits.entities.main.Competence;
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
@Table(name = "statement")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Statement.findAll", query = "SELECT s FROM Statement s"),
    @NamedQuery(name = "Statement.findByIdstatement", query = "SELECT s FROM Statement s WHERE s.idstatement = :idstatement"),
    @NamedQuery(name = "Statement.findByStatementText", query = "SELECT s FROM Statement s WHERE s.statementText = :statementText")})
public class Statement implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idstatement")
    private Integer idstatement;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "statement_text")
    private String statementText;
    @JoinColumn(name = "competence_id", referencedColumnName = "idcompetence")
    @ManyToOne(optional = false)
    private Competence competenceId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "statementIdstatement")
    private Collection<EmployeeCompetenceAssessment> employeeCompetenceAssessmentCollection;

    public Statement() {
    }

    public Statement(Integer idstatement) {
        this.idstatement = idstatement;
    }

    public Statement(Integer idstatement, String statementText) {
        this.idstatement = idstatement;
        this.statementText = statementText;
    }

    public Integer getIdstatement() {
        return idstatement;
    }

    public void setIdstatement(Integer idstatement) {
        this.idstatement = idstatement;
    }

    public String getStatementText() {
        return statementText;
    }

    public void setStatementText(String statementText) {
        this.statementText = statementText;
    }

    public Competence getCompetenceId() {
        return competenceId;
    }

    public void setCompetenceId(Competence competenceId) {
        this.competenceId = competenceId;
    }

    @XmlTransient
    public Collection<EmployeeCompetenceAssessment> getEmployeeCompetenceAssessmentCollection() {
        return employeeCompetenceAssessmentCollection;
    }

    public void setEmployeeCompetenceAssessmentCollection(Collection<EmployeeCompetenceAssessment> employeeCompetenceAssessmentCollection) {
        this.employeeCompetenceAssessmentCollection = employeeCompetenceAssessmentCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idstatement != null ? idstatement.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Statement)) {
            return false;
        }
        Statement other = (Statement) object;
        if ((this.idstatement == null && other.idstatement != null) || (this.idstatement != null && !this.idstatement.equals(other.idstatement))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return statementText;
    }
    
}
