/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package eu.comprofits.entities.edr;

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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author george
 */
@Entity
@Table(name = "competence_goal")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CompetenceGoal.findAll", query = "SELECT c FROM CompetenceGoal c"),
    @NamedQuery(name = "CompetenceGoal.findByIdcompetenceGoal", query = "SELECT c FROM CompetenceGoal c WHERE c.idcompetenceGoal = :idcompetenceGoal"),
    @NamedQuery(name = "CompetenceGoal.findByNextYearGoalValue", query = "SELECT c FROM CompetenceGoal c WHERE c.nextYearGoalValue = :nextYearGoalValue"),
    @NamedQuery(name = "CompetenceGoal.findByComments", query = "SELECT c FROM CompetenceGoal c WHERE c.comments = :comments"),
    @NamedQuery(name = "CompetenceGoal.findByEdrIdedr", query = "SELECT c FROM CompetenceGoal c WHERE c.edrIdedr = :edrIdedr")})
public class CompetenceGoal implements Serializable {
    @JoinColumn(name = "edr_idedr", referencedColumnName = "idedr")
    @ManyToOne(optional = false)
    private Edr edrIdedr;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idcompetence_goal")
    private Integer idcompetenceGoal;
    @Column(name = "next_year_goal_value")
    private Integer nextYearGoalValue;
    @Size(max = 255)
    @Column(name = "comments")
    private String comments;
    @JoinColumn(name = "competence_idcompetence", referencedColumnName = "idcompetence")
    @ManyToOne(optional = false)
    private Competence competenceIdcompetence;

    public CompetenceGoal() {
    }

    public CompetenceGoal(Integer idcompetenceGoal) {
        this.idcompetenceGoal = idcompetenceGoal;
    }

    public CompetenceGoal(Integer idcompetenceGoal, Edr edrIdedr) {
        this.idcompetenceGoal = idcompetenceGoal;
        this.edrIdedr = edrIdedr;
    }

    public Integer getIdcompetenceGoal() {
        return idcompetenceGoal;
    }

    public void setIdcompetenceGoal(Integer idcompetenceGoal) {
        this.idcompetenceGoal = idcompetenceGoal;
    }

    public Integer getNextYearGoalValue() {
        return nextYearGoalValue;
    }

    public void setNextYearGoalValue(Integer nextYearGoalValue) {
        this.nextYearGoalValue = nextYearGoalValue;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
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
        hash += (idcompetenceGoal != null ? idcompetenceGoal.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CompetenceGoal)) {
            return false;
        }
        CompetenceGoal other = (CompetenceGoal) object;
        if ((this.idcompetenceGoal == null && other.idcompetenceGoal != null) || (this.idcompetenceGoal != null && !this.idcompetenceGoal.equals(other.idcompetenceGoal))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.mavenproject1.CompetenceGoal[ idcompetenceGoal=" + idcompetenceGoal + " ]";
    }

    public Edr getEdrIdedr() {
        return edrIdedr;
    }

    public void setEdrIdedr(Edr edrIdedr) {
        this.edrIdedr = edrIdedr;
    }
    
}
