/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package eu.comprofits.entities.employee;

import eu.comprofits.entities.jobprofile.Job;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author george
 */
@Entity
@Table(name = "in_company_employment")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "InCompanyEmployment.findAll", query = "SELECT i FROM InCompanyEmployment i"),
    @NamedQuery(name = "InCompanyEmployment.findByIdinCompanyEmployment", query = "SELECT i FROM InCompanyEmployment i WHERE i.idinCompanyEmployment = :idinCompanyEmployment"),
    @NamedQuery(name = "InCompanyEmployment.findByStartDate", query = "SELECT i FROM InCompanyEmployment i WHERE i.startDate = :startDate"),
    @NamedQuery(name = "InCompanyEmployment.findByEndDate", query = "SELECT i FROM InCompanyEmployment i WHERE i.endDate = :endDate")})
public class InCompanyEmployment implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idin_company_employment")
    private Integer idinCompanyEmployment;
    @Column(name = "start_date")
    @Temporal(TemporalType.DATE)
    private Date startDate;
    @Column(name = "end_date")
    @Temporal(TemporalType.DATE)
    private Date endDate;
    @OneToMany(mappedBy = "currentInCompanyEmploymentId")
    private Collection<Employee> employeeCollection;
    @JoinColumn(name = "job_idjob", referencedColumnName = "idjob")
    @ManyToOne(optional = false)
    private Job jobIdjob;
    @JoinColumn(name = "employee_idemployee", referencedColumnName = "idemployee")
    @ManyToOne(optional = false)
    private Employee employeeIdemployee;

    public InCompanyEmployment() {
    }

    public InCompanyEmployment(Integer idinCompanyEmployment) {
        this.idinCompanyEmployment = idinCompanyEmployment;
    }

    public Integer getIdinCompanyEmployment() {
        return idinCompanyEmployment;
    }

    public void setIdinCompanyEmployment(Integer idinCompanyEmployment) {
        this.idinCompanyEmployment = idinCompanyEmployment;
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
    public Collection<Employee> getEmployeeCollection() {
        return employeeCollection;
    }

    public void setEmployeeCollection(Collection<Employee> employeeCollection) {
        this.employeeCollection = employeeCollection;
    }

    public Job getJobIdjob() {
        return jobIdjob;
    }

    public void setJobIdjob(Job jobIdjob) {
        this.jobIdjob = jobIdjob;
    }

    public Employee getEmployeeIdemployee() {
        return employeeIdemployee;
    }

    public void setEmployeeIdemployee(Employee employeeIdemployee) {
        this.employeeIdemployee = employeeIdemployee;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idinCompanyEmployment != null ? idinCompanyEmployment.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InCompanyEmployment)) {
            return false;
        }
        InCompanyEmployment other = (InCompanyEmployment) object;
        if ((this.idinCompanyEmployment == null && other.idinCompanyEmployment != null) || (this.idinCompanyEmployment != null && !this.idinCompanyEmployment.equals(other.idinCompanyEmployment))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.mavenproject1.InCompanyEmployment[ idinCompanyEmployment=" + idinCompanyEmployment + " ]";
    }
    
}
