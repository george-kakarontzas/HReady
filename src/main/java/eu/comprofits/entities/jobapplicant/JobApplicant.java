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
package eu.comprofits.entities.jobapplicant;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author George Kakarontzas
 */
@Entity
@Table(name = "job_applicant")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "JobApplicant.findAll", query = "SELECT j FROM JobApplicant j"),
    @NamedQuery(name = "JobApplicant.findByIdjobApplicant", query = "SELECT j FROM JobApplicant j WHERE j.idjobApplicant = :idjobApplicant"),
    @NamedQuery(name = "JobApplicant.findByDateOfBirth", query = "SELECT j FROM JobApplicant j WHERE j.dateOfBirth = :dateOfBirth"),
    @NamedQuery(name = "JobApplicant.findByFirstName", query = "SELECT j FROM JobApplicant j WHERE j.firstName = :firstName"),
    @NamedQuery(name = "JobApplicant.findByLastName", query = "SELECT j FROM JobApplicant j WHERE j.lastName = :lastName"),
    @NamedQuery(name = "JobApplicant.findByGender", query = "SELECT j FROM JobApplicant j WHERE j.gender = :gender"),
    @NamedQuery(name = "JobApplicant.findByAddress", query = "SELECT j FROM JobApplicant j WHERE j.address = :address"),
    @NamedQuery(name = "JobApplicant.findByPostalCode", query = "SELECT j FROM JobApplicant j WHERE j.postalCode = :postalCode"),
    @NamedQuery(name = "JobApplicant.findByCity", query = "SELECT j FROM JobApplicant j WHERE j.city = :city"),
    @NamedQuery(name = "JobApplicant.findByCountry", query = "SELECT j FROM JobApplicant j WHERE j.country = :country"),
    @NamedQuery(name = "JobApplicant.findByProvince", query = "SELECT j FROM JobApplicant j WHERE j.province = :province"),
    @NamedQuery(name = "JobApplicant.findByPhonePrivate", query = "SELECT j FROM JobApplicant j WHERE j.phonePrivate = :phonePrivate"),
    @NamedQuery(name = "JobApplicant.findByPhoneMobile", query = "SELECT j FROM JobApplicant j WHERE j.phoneMobile = :phoneMobile"),
    @NamedQuery(name = "JobApplicant.findByEmail", query = "SELECT j FROM JobApplicant j WHERE j.email = :email"),
    @NamedQuery(name = "JobApplicant.findByPhotoPath", query = "SELECT j FROM JobApplicant j WHERE j.photoPath = :photoPath"),
    @NamedQuery(name = "JobApplicant.findByUsername", query = "SELECT j FROM JobApplicant j WHERE j.username = :username"),
    @NamedQuery(name = "JobApplicant.findByPassword", query = "SELECT j FROM JobApplicant j WHERE j.password = :password"),
    @NamedQuery(name = "JobApplicant.findByMaritalStatus", query = "SELECT j FROM JobApplicant j WHERE j.maritalStatus = :maritalStatus"),
    @NamedQuery(name = "JobApplicant.findByNumberOfChildren", query = "SELECT j FROM JobApplicant j WHERE j.numberOfChildren = :numberOfChildren")})
public class JobApplicant implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idjob_applicant")
    private Integer idjobApplicant;
    @Basic(optional = false)
    @NotNull
    @Column(name = "date_of_birth")
    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "first_name")
    private String firstName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "gender")
    private Integer gender;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "address")
    private String address;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "postal_code")
    private String postalCode;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "city")
    private String city;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "country")
    private String country;
    @Size(max = 45)
    @Column(name = "province")
    private String province;
    @Size(max = 45)
    @Column(name = "phone_private")
    private String phonePrivate;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "phone_mobile")
    private String phoneMobile;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "email")
    private String email;
    @Size(max = 255)
    @Column(name = "photo_path")
    private String photoPath;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "username")
    private String username;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "password")
    private String password;
    @Column(name = "marital_status")
    private Character maritalStatus;
    @Column(name = "number_of_children")
    private Short numberOfChildren;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "jobApplicantIdjobApplicant")
    private Collection<JobApplication> jobApplicationCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "jobApplicantIdjobApplicant")
    private Collection<ApplicantProfessionalExperienceRecord> applicantProfessionalExperienceRecordCollection;
    @JoinColumn(name = "present_idapplicant_professional_experience_record", referencedColumnName = "idapplicant_professional_experience_record")
    @ManyToOne
    private ApplicantProfessionalExperienceRecord presentIdapplicantProfessionalExperienceRecord;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "jobApplicantIdjobApplicant")
    private Collection<ApplicantStudyRecord> applicantStudyRecordCollection;

    public JobApplicant() {
    }

    public JobApplicant(Integer idjobApplicant) {
        this.idjobApplicant = idjobApplicant;
    }

    public JobApplicant(Integer idjobApplicant, Date dateOfBirth, String firstName, String lastName, String address, String postalCode, String city, String country, String phoneMobile, String email, String username, String password) {
        this.idjobApplicant = idjobApplicant;
        this.dateOfBirth = dateOfBirth;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.postalCode = postalCode;
        this.city = city;
        this.country = country;
        this.phoneMobile = phoneMobile;
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public Integer getIdjobApplicant() {
        return idjobApplicant;
    }

    public void setIdjobApplicant(Integer idjobApplicant) {
        this.idjobApplicant = idjobApplicant;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getPhonePrivate() {
        return phonePrivate;
    }

    public void setPhonePrivate(String phonePrivate) {
        this.phonePrivate = phonePrivate;
    }

    public String getPhoneMobile() {
        return phoneMobile;
    }

    public void setPhoneMobile(String phoneMobile) {
        this.phoneMobile = phoneMobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Character getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(Character maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public Short getNumberOfChildren() {
        return numberOfChildren;
    }

    public void setNumberOfChildren(Short numberOfChildren) {
        this.numberOfChildren = numberOfChildren;
    }

    @XmlTransient
    public Collection<JobApplication> getJobApplicationCollection() {
        return jobApplicationCollection;
    }

    public void setJobApplicationCollection(Collection<JobApplication> jobApplicationCollection) {
        this.jobApplicationCollection = jobApplicationCollection;
    }

    @XmlTransient
    public Collection<ApplicantProfessionalExperienceRecord> getApplicantProfessionalExperienceRecordCollection() {
        return applicantProfessionalExperienceRecordCollection;
    }

    public void setApplicantProfessionalExperienceRecordCollection(Collection<ApplicantProfessionalExperienceRecord> applicantProfessionalExperienceRecordCollection) {
        this.applicantProfessionalExperienceRecordCollection = applicantProfessionalExperienceRecordCollection;
    }

    public ApplicantProfessionalExperienceRecord getPresentIdapplicantProfessionalExperienceRecord() {
        return presentIdapplicantProfessionalExperienceRecord;
    }

    public void setPresentIdapplicantProfessionalExperienceRecord(ApplicantProfessionalExperienceRecord presentIdapplicantProfessionalExperienceRecord) {
        this.presentIdapplicantProfessionalExperienceRecord = presentIdapplicantProfessionalExperienceRecord;
    }

    @XmlTransient
    public Collection<ApplicantStudyRecord> getApplicantStudyRecordCollection() {
        return applicantStudyRecordCollection;
    }

    public void setApplicantStudyRecordCollection(Collection<ApplicantStudyRecord> applicantStudyRecordCollection) {
        this.applicantStudyRecordCollection = applicantStudyRecordCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idjobApplicant != null ? idjobApplicant.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof JobApplicant)) {
            return false;
        }
        JobApplicant other = (JobApplicant) object;
        if ((this.idjobApplicant == null && other.idjobApplicant != null) || (this.idjobApplicant != null && !this.idjobApplicant.equals(other.idjobApplicant))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.mavenproject1.JobApplicant[ idjobApplicant=" + idjobApplicant + " ]";
    }
    
    
    public String getFullName() {
        return this.getFirstName()+" "+this.getLastName();
    }
    
}
