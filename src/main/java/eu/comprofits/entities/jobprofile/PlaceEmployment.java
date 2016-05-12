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
package eu.comprofits.entities.jobprofile;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author George Kakarontzas
 */
@Entity
@Table(name = "place_employment")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PlaceEmployment.findAll", query = "SELECT p FROM PlaceEmployment p"),
    @NamedQuery(name = "PlaceEmployment.findByIdplaceEmployment", query = "SELECT p FROM PlaceEmployment p WHERE p.idplaceEmployment = :idplaceEmployment"),
    @NamedQuery(name = "PlaceEmployment.findByName", query = "SELECT p FROM PlaceEmployment p WHERE p.name = :name"),
    @NamedQuery(name = "PlaceEmployment.findByAddress", query = "SELECT p FROM PlaceEmployment p WHERE p.address = :address"),
    @NamedQuery(name = "PlaceEmployment.findByPostalCode", query = "SELECT p FROM PlaceEmployment p WHERE p.postalCode = :postalCode"),
    @NamedQuery(name = "PlaceEmployment.findByCity", query = "SELECT p FROM PlaceEmployment p WHERE p.city = :city"),
    @NamedQuery(name = "PlaceEmployment.findByProvince", query = "SELECT p FROM PlaceEmployment p WHERE p.province = :province"),
    @NamedQuery(name = "PlaceEmployment.findByCountry", query = "SELECT p FROM PlaceEmployment p WHERE p.country = :country")})
public class PlaceEmployment implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idplace_employment")
    private Integer idplaceEmployment;
    @Size(max = 45)
    @Column(name = "name")
    private String name;
    @Size(max = 140)
    @Column(name = "address")
    private String address;
    @Size(max = 20)
    @Column(name = "postal_code")
    private String postalCode;
    @Size(max = 45)
    @Column(name = "city")
    private String city;
    @Size(max = 45)
    @Column(name = "province")
    private String province;
    @Size(max = 45)
    @Column(name = "country")
    private String country;
    @OneToMany(mappedBy = "placeEmploymentIdplaceEmployment")
    private Collection<Job> jobCollection;

    public PlaceEmployment() {
    }

    public PlaceEmployment(Integer idplaceEmployment) {
        this.idplaceEmployment = idplaceEmployment;
    }

    public Integer getIdplaceEmployment() {
        return idplaceEmployment;
    }

    public void setIdplaceEmployment(Integer idplaceEmployment) {
        this.idplaceEmployment = idplaceEmployment;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
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
        hash += (idplaceEmployment != null ? idplaceEmployment.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PlaceEmployment)) {
            return false;
        }
        PlaceEmployment other = (PlaceEmployment) object;
        if ((this.idplaceEmployment == null && other.idplaceEmployment != null) || (this.idplaceEmployment != null && !this.idplaceEmployment.equals(other.idplaceEmployment))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.mavenproject1.PlaceEmployment[ idplaceEmployment=" + idplaceEmployment + " ]";
    }
    
}
