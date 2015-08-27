/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package eu.comprofits.entities.jobprofile;

import eu.comprofits.entities.employee.Employee;
import eu.comprofits.entities.main.Company;
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
@Table(name = "division")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Division.findAll", query = "SELECT d FROM Division d"),
    @NamedQuery(name = "Division.findByIddivision", query = "SELECT d FROM Division d WHERE d.iddivision = :iddivision"),
    @NamedQuery(name = "Division.findByName", query = "SELECT d FROM Division d WHERE d.name = :name"),
    @NamedQuery(name = "Division.findByDescription", query = "SELECT d FROM Division d WHERE d.description = :description")})
public class Division implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "iddivision")
    private Integer iddivision;
    @Size(max = 45)
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @JoinColumn(name = "head_of_division_employee", referencedColumnName = "idemployee")
    @ManyToOne
    private Employee headOfDivisionEmployee;
    @ManyToOne(optional = false)
    @JoinColumn(name = "company_idcompany", referencedColumnName = "idcompany")
    private Company companyIdcompany;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "divisionIddivision")
    private Collection<BusinessArea> businessAreaCollection;

    public Division() {
    }

    public Division(Integer iddivision) {
        this.iddivision = iddivision;
    }

    public Integer getIddivision() {
        return iddivision;
    }

    public void setIddivision(Integer iddivision) {
        this.iddivision = iddivision;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Employee getHeadOfDivisionEmployee() {
        return headOfDivisionEmployee;
    }

    public void setHeadOfDivisionEmployee(Employee headOfDivisionEmployee) {
        this.headOfDivisionEmployee = headOfDivisionEmployee;
    }

    @XmlTransient
    public Collection<BusinessArea> getBusinessAreaCollection() {
        return businessAreaCollection;
    }

    public void setBusinessAreaCollection(Collection<BusinessArea> businessAreaCollection) {
        this.businessAreaCollection = businessAreaCollection;
    }

    public Company getCompanyIdcompany() {
        return companyIdcompany;
    }

    public void setCompanyIdcompany(Company companyIdcompany) {
        this.companyIdcompany = companyIdcompany;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iddivision != null ? iddivision.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Division)) {
            return false;
        }
        Division other = (Division) object;
        if ((this.iddivision == null && other.iddivision != null) || (this.iddivision != null && !this.iddivision.equals(other.iddivision))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.mavenproject1.Division[ iddivision=" + iddivision + " ]";
    }
    
}
