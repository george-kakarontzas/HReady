/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package eu.comprofits.entities.jobprofile;

import java.io.Serializable;
import java.util.Collection;
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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author george
 */
@Entity
@Table(name = "business_area")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "BusinessArea.findAll", query = "SELECT b FROM BusinessArea b"),
    @NamedQuery(name = "BusinessArea.findByIdbusinessArea", query = "SELECT b FROM BusinessArea b WHERE b.idbusinessArea = :idbusinessArea"),
    @NamedQuery(name = "BusinessArea.findByName", query = "SELECT b FROM BusinessArea b WHERE b.name = :name"),
    @NamedQuery(name = "BusinessArea.findByDescription", query = "SELECT b FROM BusinessArea b WHERE b.description = :description")})
public class BusinessArea implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idbusiness_area")
    private Integer idbusinessArea;
    @Size(max = 45)
    @Column(name = "name")
    private String name;
    @Size(max = 10)
    @Column(name = "description")
    private String description;
    @JoinColumn(name = "division_iddivision", referencedColumnName = "iddivision")
    @ManyToOne(optional = false)
    private Division divisionIddivision;
    //@OneToMany(mappedBy = "businessAreaIdbusinessArea")
    //private Collection<Job> jobCollection;

    public BusinessArea() {
    }

    public BusinessArea(Integer idbusinessArea) {
        this.idbusinessArea = idbusinessArea;
    }

    public Integer getIdbusinessArea() {
        return idbusinessArea;
    }

    public void setIdbusinessArea(Integer idbusinessArea) {
        this.idbusinessArea = idbusinessArea;
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

    public Division getDivisionIddivision() {
        return divisionIddivision;
    }

    public void setDivisionIddivision(Division divisionIddivision) {
        this.divisionIddivision = divisionIddivision;
    }

    @XmlTransient
    //public Collection<Job> getJobCollection() {
    //    return jobCollection;
    //}

    //public void setJobCollection(Collection<Job> jobCollection) {
   //     this.jobCollection = jobCollection;
    //}

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idbusinessArea != null ? idbusinessArea.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BusinessArea)) {
            return false;
        }
        BusinessArea other = (BusinessArea) object;
        if ((this.idbusinessArea == null && other.idbusinessArea != null) || (this.idbusinessArea != null && !this.idbusinessArea.equals(other.idbusinessArea))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.mavenproject1.BusinessArea[ idbusinessArea=" + idbusinessArea + " ]";
    }
    
}
