/* 
 * Copyright 2016 ComProFITS Project.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package eu.comprofits.entities.main;

import eu.comprofits.entities.jobprofile.Division;
import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
 * @author George Kakarontzas
 */
@Entity
@Table(name = "company")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Company.findAll", query = "SELECT c FROM Company c"),
    @NamedQuery(name = "Company.findByIdcompany", query = "SELECT c FROM Company c WHERE c.idcompany = :idcompany"),
    @NamedQuery(name = "Company.findByCompanyName1", query = "SELECT c FROM Company c WHERE c.companyName1 = :companyName1"),
    @NamedQuery(name = "Company.findByCompanyName2", query = "SELECT c FROM Company c WHERE c.companyName2 = :companyName2"),
    @NamedQuery(name = "Company.findByCompanyAddress1", query = "SELECT c FROM Company c WHERE c.companyAddress1 = :companyAddress1"),
    @NamedQuery(name = "Company.findByCompanyAddress2", query = "SELECT c FROM Company c WHERE c.companyAddress2 = :companyAddress2"),
    @NamedQuery(name = "Company.findByPostalCode", query = "SELECT c FROM Company c WHERE c.postalCode = :postalCode"),
    @NamedQuery(name = "Company.findByProvince", query = "SELECT c FROM Company c WHERE c.province = :province"),
    @NamedQuery(name = "Company.findByCountry", query = "SELECT c FROM Company c WHERE c.country = :country"),
    @NamedQuery(name = "Company.findByPhoneNumber", query = "SELECT c FROM Company c WHERE c.phoneNumber = :phoneNumber"),
    @NamedQuery(name = "Company.findByEMail", query = "SELECT c FROM Company c WHERE c.eMail = :eMail"),
    @NamedQuery(name = "Company.findByWebsite", query = "SELECT c FROM Company c WHERE c.website = :website")})
public class Company implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idcompany")
    private Integer idcompany;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "company_name1")
    private String companyName1;
    @Size(max = 100)
    @Column(name = "company_name2")
    private String companyName2;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "company_address1")
    private String companyAddress1;
    @Size(max = 100)
    @Column(name = "company_address2")
    private String companyAddress2;
    @Size(max = 30)
    @Column(name = "postal_code")
    private String postalCode;
    @Size(max = 45)
    @Column(name = "province")
    private String province;
    @Size(max = 45)
    @Column(name = "country")
    private String country;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "phone_number")
    private String phoneNumber;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "e_mail")
    private String eMail;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "website")
    private String website;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "companyIdcompany")
    private Collection<Division> divisionCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "companyIdcompany")
    private Collection<OrganisationalPosition> organisationalPositionCollection;

    public Company() {
    }

    public Company(Integer idcompany) {
        this.idcompany = idcompany;
    }

    public Company(Integer idcompany, String companyName1, String companyAddress1, String phoneNumber, String eMail, String website) {
        this.idcompany = idcompany;
        this.companyName1 = companyName1;
        this.companyAddress1 = companyAddress1;
        this.phoneNumber = phoneNumber;
        this.eMail = eMail;
        this.website = website;
    }

    public Integer getIdcompany() {
        return idcompany;
    }

    public void setIdcompany(Integer idcompany) {
        this.idcompany = idcompany;
    }

    public String getCompanyName1() {
        return companyName1;
    }

    public void setCompanyName1(String companyName1) {
        this.companyName1 = companyName1;
    }

    public String getCompanyName2() {
        return companyName2;
    }

    public void setCompanyName2(String companyName2) {
        this.companyName2 = companyName2;
    }

    public String getCompanyAddress1() {
        return companyAddress1;
    }

    public void setCompanyAddress1(String companyAddress1) {
        this.companyAddress1 = companyAddress1;
    }

    public String getCompanyAddress2() {
        return companyAddress2;
    }

    public void setCompanyAddress2(String companyAddress2) {
        this.companyAddress2 = companyAddress2;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEMail() {
        return eMail;
    }

    public void setEMail(String eMail) {
        this.eMail = eMail;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    @XmlTransient
    public Collection<Division> getDivisionCollection() {
        return divisionCollection;
    }

    public void setDivisionCollection(Collection<Division> divisionCollection) {
        this.divisionCollection = divisionCollection;
    }

    @XmlTransient
    public Collection<OrganisationalPosition> getOrganisationalPositionCollection() {
        return organisationalPositionCollection;
    }

    public void setOrganisationalPositionCollection(Collection<OrganisationalPosition> organisationalPositionCollection) {
        this.organisationalPositionCollection = organisationalPositionCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcompany != null ? idcompany.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Company)) {
            return false;
        }
        Company other = (Company) object;
        if ((this.idcompany == null && other.idcompany != null) || (this.idcompany != null && !this.idcompany.equals(other.idcompany))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.mavenproject1.Company[ idcompany=" + idcompany + " ]";
    }
    
}
