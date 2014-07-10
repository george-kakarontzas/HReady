/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package eu.comprofits.entities.edr;

import eu.comprofits.entities.employee.Employee;
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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author george
 */
@Entity
@Table(name = "edr")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Edr.findAll", query = "SELECT e FROM Edr e"),
    @NamedQuery(name = "Edr.findByIdedr", query = "SELECT e FROM Edr e WHERE e.idedr = :idedr"),
    @NamedQuery(name = "Edr.findByYear", query = "SELECT e FROM Edr e WHERE e.year = :year"),
    @NamedQuery(name = "Edr.findByStatus", query = "SELECT e FROM Edr e WHERE e.status = :status"),
    @NamedQuery(name = "Edr.findByVerdict", query = "SELECT e FROM Edr e WHERE e.verdict = :verdict")})
public class Edr implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idedr")
    private Integer idedr;
    @Size(max = 45)
    @Column(name = "year")
    private String year;
    @Column(name = "status")
    private Integer status;
    @Size(max = 45)
    @Column(name = "verdict")
    private String verdict;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "edrIdedr")
    private Collection<QuestionAnswer> questionAnswerCollection;
    @JoinColumn(name = "immediate_manager_idemployee", referencedColumnName = "idemployee")
    @ManyToOne(optional = false)
    private Employee immediateManagerIdemployee;
    @JoinColumn(name = "reviewed_employee_idemployee", referencedColumnName = "idemployee")
    @ManyToOne(optional = false)
    private Employee reviewedEmployeeIdemployee;
    @OneToMany(mappedBy = "previousEdrIdedr")
    private Collection<Edr> edrCollection;
    @JoinColumn(name = "previous_edr_idedr", referencedColumnName = "idedr")
    @ManyToOne
    private Edr previousEdrIdedr;

    public Edr() {
    }

    public Edr(Integer idedr) {
        this.idedr = idedr;
    }

    public Integer getIdedr() {
        return idedr;
    }

    public void setIdedr(Integer idedr) {
        this.idedr = idedr;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getVerdict() {
        return verdict;
    }

    public void setVerdict(String verdict) {
        this.verdict = verdict;
    }

    @XmlTransient
    public Collection<QuestionAnswer> getQuestionAnswerCollection() {
        return questionAnswerCollection;
    }

    public void setQuestionAnswerCollection(Collection<QuestionAnswer> questionAnswerCollection) {
        this.questionAnswerCollection = questionAnswerCollection;
    }

    public Employee getImmediateManagerIdemployee() {
        return immediateManagerIdemployee;
    }

    public void setImmediateManagerIdemployee(Employee immediateManagerIdemployee) {
        this.immediateManagerIdemployee = immediateManagerIdemployee;
    }

    public Employee getReviewedEmployeeIdemployee() {
        return reviewedEmployeeIdemployee;
    }

    public void setReviewedEmployeeIdemployee(Employee reviewedEmployeeIdemployee) {
        this.reviewedEmployeeIdemployee = reviewedEmployeeIdemployee;
    }

    @XmlTransient
    public Collection<Edr> getEdrCollection() {
        return edrCollection;
    }

    public void setEdrCollection(Collection<Edr> edrCollection) {
        this.edrCollection = edrCollection;
    }

    public Edr getPreviousEdrIdedr() {
        return previousEdrIdedr;
    }

    public void setPreviousEdrIdedr(Edr previousEdrIdedr) {
        this.previousEdrIdedr = previousEdrIdedr;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idedr != null ? idedr.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Edr)) {
            return false;
        }
        Edr other = (Edr) object;
        if ((this.idedr == null && other.idedr != null) || (this.idedr != null && !this.idedr.equals(other.idedr))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.mavenproject1.Edr[ idedr=" + idedr + " ]";
    }
    
}
