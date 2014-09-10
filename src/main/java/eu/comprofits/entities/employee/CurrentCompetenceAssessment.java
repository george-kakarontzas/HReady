/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package eu.comprofits.entities.employee;

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
@Table(name = "current_competence_assessment")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CurrentCompetenceAssessment.findAll", query = "SELECT c FROM CurrentCompetenceAssessment c"),
    @NamedQuery(name = "CurrentCompetenceAssessment.findByIdcurrentCompetenceAssessment", query = "SELECT c FROM CurrentCompetenceAssessment c WHERE c.idcurrentCompetenceAssessment = :idcurrentCompetenceAssessment"),
    @NamedQuery(name = "CurrentCompetenceAssessment.findByAssessment", query = "SELECT c FROM CurrentCompetenceAssessment c WHERE c.assessment = :assessment")})
public class CurrentCompetenceAssessment implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idcurrent_competence_assessment")
    private Integer idcurrentCompetenceAssessment;
    @Basic(optional = false)
    @NotNull
    @Column(name = "assessment")
    private int assessment;
    @JoinColumn(name = "employee_idemployee", referencedColumnName = "idemployee")
    @ManyToOne(optional = false)
    private Employee employeeIdemployee;
    @JoinColumn(name = "competence_idcompetence", referencedColumnName = "idcompetence")
    @ManyToOne(optional = false)
    private Competence competenceIdcompetence;

    public CurrentCompetenceAssessment() {
    }

    public CurrentCompetenceAssessment(Integer idcurrentCompetenceAssessment) {
        this.idcurrentCompetenceAssessment = idcurrentCompetenceAssessment;
    }

    public CurrentCompetenceAssessment(Integer idcurrentCompetenceAssessment, int assessment) {
        this.idcurrentCompetenceAssessment = idcurrentCompetenceAssessment;
        this.assessment = assessment;
    }

    public Integer getIdcurrentCompetenceAssessment() {
        return idcurrentCompetenceAssessment;
    }

    public void setIdcurrentCompetenceAssessment(Integer idcurrentCompetenceAssessment) {
        this.idcurrentCompetenceAssessment = idcurrentCompetenceAssessment;
    }

    public int getAssessment() {
        return assessment;
    }

    public void setAssessment(int assessment) {
        this.assessment = assessment;
    }

    public Employee getEmployeeIdemployee() {
        return employeeIdemployee;
    }

    public void setEmployeeIdemployee(Employee employeeIdemployee) {
        this.employeeIdemployee = employeeIdemployee;
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
        hash += (idcurrentCompetenceAssessment != null ? idcurrentCompetenceAssessment.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CurrentCompetenceAssessment)) {
            return false;
        }
        CurrentCompetenceAssessment other = (CurrentCompetenceAssessment) object;
        if ((this.idcurrentCompetenceAssessment == null && other.idcurrentCompetenceAssessment != null) || (this.idcurrentCompetenceAssessment != null && !this.idcurrentCompetenceAssessment.equals(other.idcurrentCompetenceAssessment))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.mavenproject1.CurrentCompetenceAssessment[ idcurrentCompetenceAssessment=" + idcurrentCompetenceAssessment + " ]";
    }
    
}
