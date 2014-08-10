/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package eu.comprofits.entities.main;

import eu.comprofits.entities.jobprofile.Job;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author george
 */
@Entity
@Table(name = "organisational_position")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OrganisationalPosition.findAll", query = "SELECT o FROM OrganisationalPosition o"),
    @NamedQuery(name = "OrganisationalPosition.findByIdorganisationalPosition", query = "SELECT o FROM OrganisationalPosition o WHERE o.idorganisationalPosition = :idorganisationalPosition"),
    @NamedQuery(name = "OrganisationalPosition.findByOrganisationalPositionName", query = "SELECT o FROM OrganisationalPosition o WHERE o.organisationalPositionName = :organisationalPositionName"),
    @NamedQuery(name = "OrganisationalPosition.findByOrganisationalPositionDescription", query = "SELECT o FROM OrganisationalPosition o WHERE o.organisationalPositionDescription = :organisationalPositionDescription")})
public class OrganisationalPosition implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idorganisational_position")
    private Integer idorganisationalPosition;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "organisational_position_name")
    private String organisationalPositionName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "organisational_position_description")
    private String organisationalPositionDescription;
    @JoinColumn(name = "company_idcompany", referencedColumnName = "idcompany")
    @ManyToOne(optional = false)
    private Company companyIdcompany;
    @OneToMany(mappedBy = "organisationalPositionIdorganisationalPosition")
    private Collection<Job> jobCollection;

    public OrganisationalPosition() {
    }

    public OrganisationalPosition(Integer idorganisationalPosition) {
        this.idorganisationalPosition = idorganisationalPosition;
    }

    public OrganisationalPosition(Integer idorganisationalPosition, String organisationalPositionName, String organisationalPositionDescription) {
        this.idorganisationalPosition = idorganisationalPosition;
        this.organisationalPositionName = organisationalPositionName;
        this.organisationalPositionDescription = organisationalPositionDescription;
    }

    public Integer getIdorganisationalPosition() {
        return idorganisationalPosition;
    }

    public void setIdorganisationalPosition(Integer idorganisationalPosition) {
        this.idorganisationalPosition = idorganisationalPosition;
    }

    public String getOrganisationalPositionName() {
        return organisationalPositionName;
    }

    public void setOrganisationalPositionName(String organisationalPositionName) {
        this.organisationalPositionName = organisationalPositionName;
    }

    public String getOrganisationalPositionDescription() {
        return organisationalPositionDescription;
    }

    public void setOrganisationalPositionDescription(String organisationalPositionDescription) {
        this.organisationalPositionDescription = organisationalPositionDescription;
    }

    public Company getCompanyIdcompany() {
        return companyIdcompany;
    }

    public void setCompanyIdcompany(Company companyIdcompany) {
        this.companyIdcompany = companyIdcompany;
    }

    @XmlTransient
    public Collection<Job> getJobCollection() {
        return jobCollection;
    }

    public void setJobCollection(Collection<Job> jobCollection) {
        this.jobCollection = jobCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idorganisationalPosition != null ? idorganisationalPosition.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OrganisationalPosition)) {
            return false;
        }
        OrganisationalPosition other = (OrganisationalPosition) object;
        if ((this.idorganisationalPosition == null && other.idorganisationalPosition != null) || (this.idorganisationalPosition != null && !this.idorganisationalPosition.equals(other.idorganisationalPosition))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.mavenproject1.OrganisationalPosition[ idorganisationalPosition=" + idorganisationalPosition + " ]";
    }
    
}
