/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package eu.comprofits.entities.employee;

import eu.comprofits.entities.assessment.Assessment;
import eu.comprofits.entities.edr.Edr;
import eu.comprofits.entities.assessment.EmployeeCompetenceAssessment;
import eu.comprofits.entities.main.Department;
import eu.comprofits.entities.jobprofile.Job;
import eu.comprofits.entities.jobprofile.Division;
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
 * @author george
 */
@Entity
@Table(name = "employee")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Employee.findAll", query = "SELECT e FROM Employee e"),
    @NamedQuery(name = "Employee.findByIdemployee", query = "SELECT e FROM Employee e WHERE e.idemployee = :idemployee"),
    @NamedQuery(name = "Employee.findByIdentityCardNumber", query = "SELECT e FROM Employee e WHERE e.identityCardNumber = :identityCardNumber"),
    @NamedQuery(name = "Employee.findBySocialSecurityNumber", query = "SELECT e FROM Employee e WHERE e.socialSecurityNumber = :socialSecurityNumber"),
    @NamedQuery(name = "Employee.findByFirstName", query = "SELECT e FROM Employee e WHERE e.firstName = :firstName"),
    @NamedQuery(name = "Employee.findByLastName", query = "SELECT e FROM Employee e WHERE e.lastName = :lastName"),
    @NamedQuery(name = "Employee.findByGender", query = "SELECT e FROM Employee e WHERE e.gender = :gender"),
    @NamedQuery(name = "Employee.findByProvince", query = "SELECT e FROM Employee e WHERE e.province = :province"),
    @NamedQuery(name = "Employee.findByAddress", query = "SELECT e FROM Employee e WHERE e.address = :address"),
    @NamedQuery(name = "Employee.findByPostalCode", query = "SELECT e FROM Employee e WHERE e.postalCode = :postalCode"),
    @NamedQuery(name = "Employee.findByCity", query = "SELECT e FROM Employee e WHERE e.city = :city"),
    @NamedQuery(name = "Employee.findByCountry", query = "SELECT e FROM Employee e WHERE e.country = :country"),
    @NamedQuery(name = "Employee.findByDateOfBirth", query = "SELECT e FROM Employee e WHERE e.dateOfBirth = :dateOfBirth"),
    @NamedQuery(name = "Employee.findByPhonePrivate", query = "SELECT e FROM Employee e WHERE e.phonePrivate = :phonePrivate"),
    @NamedQuery(name = "Employee.findByPhoneMobile", query = "SELECT e FROM Employee e WHERE e.phoneMobile = :phoneMobile"),
    @NamedQuery(name = "Employee.findByEmail", query = "SELECT e FROM Employee e WHERE e.email = :email"),
    @NamedQuery(name = "Employee.findByPhotoPath", query = "SELECT e FROM Employee e WHERE e.photoPath = :photoPath"),
    @NamedQuery(name = "Employee.findByCvPath", query = "SELECT e FROM Employee e WHERE e.cvPath = :cvPath"),
    @NamedQuery(name = "Employee.findByUsername", query = "SELECT e FROM Employee e WHERE e.username = :username"),
    @NamedQuery(name = "Employee.findByPassword", query = "SELECT e FROM Employee e WHERE e.password = :password"),
    @NamedQuery(name = "Employee.findByMaritalStatus", query = "SELECT e FROM Employee e WHERE e.maritalStatus = :maritalStatus"),
    @NamedQuery(name = "Employee.findByNumberOfChildren", query = "SELECT e FROM Employee e WHERE e.numberOfChildren = :numberOfChildren"),
    @NamedQuery(name = "Employee.findByRole", query = "SELECT e FROM Employee e WHERE e.role = :role"),
    @NamedQuery(name = "Employee.findByIsActive", query = "SELECT e FROM Employee e WHERE e.isActive = :isActive")})
public class Employee implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idemployee")
    private Integer idemployee;
    @Size(max = 20)
    @Column(name = "identity_card_number")
    private String identityCardNumber;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "social_security_number")
    private String socialSecurityNumber;
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
    @Size(max = 45)
    @Column(name = "province")
    private String province;
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
    @Basic(optional = false)
    @NotNull
    @Column(name = "date_of_birth")
    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;
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
    @Column(name = "is_active")
    private Boolean isActive;
    @Size(max = 50)
    @Column(name = "role")
    private String role;
    @Size(max = 255)
    @Column(name = "cv_path")
    private String cvPath;
    @OneToMany(mappedBy = "headOfDepartmentIdemployee")
    private Collection<Department> departmentCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "employeeIdemployee")
    private Collection<StudyRecord> studyRecordCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "employeeIdemployee")
    private Collection<ProfessionalExperienceRecord> professionalExperienceRecordCollection;
    @OneToMany(mappedBy = "headOfDivisionEmployee")
    private Collection<Division> divisionCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "employeeIdemployee")
    private Collection<CurrentCompetenceAssessment> currentCompetenceAssessmentCollection;
    @JoinColumn(name = "current_in_company_employment_id", referencedColumnName = "idin_company_employment")
    @ManyToOne
    private InCompanyEmployment currentInCompanyEmploymentId;
    @JoinColumn(name = "department_iddepartment", referencedColumnName = "iddepartment")
    @ManyToOne
    private Department departmentIddepartment;
    @JoinColumn(name = "division_iddivision", referencedColumnName = "iddivision")
    @ManyToOne
    private Division divisionIddivision;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "immediateManagerIdemployee")
    private Collection<Edr> edrCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "reviewedEmployeeIdemployee")
    private Collection<Edr> edrCollection1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "assessorIdemployee")
    private Collection<EmployeeCompetenceAssessment> employeeCompetenceAssessmentCollection;
    @OneToMany(mappedBy = "reportingToIdemployee")
    private Collection<Job> jobCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "employeeIdemployee")
    private Collection<InCompanyEmployment> inCompanyEmploymentCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "immediateManagerIdemployee")
    private Collection<Assessment> assessmentCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "colleague3Idemployee")
    private Collection<Assessment> assessmentCollection1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "colleague2Idemployee")
    private Collection<Assessment> assessmentCollection2;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "colleague1Idemployee")
    private Collection<Assessment> assessmentCollection3;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "assesseeIdemployee")
    private Collection<Assessment> assessmentCollection4;

    public Employee() {
    }

    public Employee(Integer idemployee) {
        this.idemployee = idemployee;
    }

    public Employee(Integer idemployee, String socialSecurityNumber, String firstName, String lastName, String address, String postalCode, String city, String country, Date dateOfBirth, String phoneMobile, String email, String username, String password, Boolean isActive) {
        this.idemployee = idemployee;
        this.socialSecurityNumber = socialSecurityNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.postalCode = postalCode;
        this.city = city;
        this.country = country;
        this.dateOfBirth = dateOfBirth;
        this.phoneMobile = phoneMobile;
        this.email = email;
        this.username = username;
        this.password = password;
        this.isActive = isActive;
    }

    public Integer getIdemployee() {
        return idemployee;
    }

    public void setIdemployee(Integer idemployee) {
        this.idemployee = idemployee;
    }

    public String getIdentityCardNumber() {
        return identityCardNumber;
    }

    public void setIdentityCardNumber(String identityCardNumber) {
        this.identityCardNumber = identityCardNumber;
    }

    public String getSocialSecurityNumber() {
        return socialSecurityNumber;
    }

    public void setSocialSecurityNumber(String socialSecurityNumber) {
        this.socialSecurityNumber = socialSecurityNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getFullName() {
        return firstName+" "+lastName;
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

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
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

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getCvPath() {
        return cvPath;
    }

    public void setCvPath(String cvPath) {
        this.cvPath = cvPath;
    }
    
    @XmlTransient
    public Collection<Department> getDepartmentCollection() {
        return departmentCollection;
    }

    public void setDepartmentCollection(Collection<Department> departmentCollection) {
        this.departmentCollection = departmentCollection;
    }

    @XmlTransient
    public Collection<StudyRecord> getStudyRecordCollection() {
        return studyRecordCollection;
    }

    public void setStudyRecordCollection(Collection<StudyRecord> studyRecordCollection) {
        this.studyRecordCollection = studyRecordCollection;
    }

    @XmlTransient
    public Collection<ProfessionalExperienceRecord> getProfessionalExperienceRecordCollection() {
        return professionalExperienceRecordCollection;
    }

    public void setProfessionalExperienceRecordCollection(Collection<ProfessionalExperienceRecord> professionalExperienceRecordCollection) {
        this.professionalExperienceRecordCollection = professionalExperienceRecordCollection;
    }

    @XmlTransient
    public Collection<Division> getDivisionCollection() {
        return divisionCollection;
    }

    public void setDivisionCollection(Collection<Division> divisionCollection) {
        this.divisionCollection = divisionCollection;
    }

    @XmlTransient
    public Collection<CurrentCompetenceAssessment> getCurrentCompetenceAssessmentCollection() {
        return currentCompetenceAssessmentCollection;
    }

    public void setCurrentCompetenceAssessmentCollection(Collection<CurrentCompetenceAssessment> currentCompetenceAssessmentCollection) {
        this.currentCompetenceAssessmentCollection = currentCompetenceAssessmentCollection;
    }

    public InCompanyEmployment getCurrentInCompanyEmploymentId() {
        return currentInCompanyEmploymentId;
    }

    public void setCurrentInCompanyEmploymentId(InCompanyEmployment currentInCompanyEmploymentId) {
        this.currentInCompanyEmploymentId = currentInCompanyEmploymentId;
    }

    public Department getDepartmentIddepartment() {
        return departmentIddepartment;
    }

    public void setDepartmentIddepartment(Department departmentIddepartment) {
        this.departmentIddepartment = departmentIddepartment;
    }

    public Division getDivisionIddivision() {
        return divisionIddivision;
    }

    public void setDivisionIddivision(Division divisionIddivision) {
        this.divisionIddivision = divisionIddivision;
    }

    @XmlTransient
    public Collection<Edr> getEdrCollection() {
        return edrCollection;
    }

    public void setEdrCollection(Collection<Edr> edrCollection) {
        this.edrCollection = edrCollection;
    }

    @XmlTransient
    public Collection<Edr> getEdrCollection1() {
        return edrCollection1;
    }

    public void setEdrCollection1(Collection<Edr> edrCollection1) {
        this.edrCollection1 = edrCollection1;
    }

    @XmlTransient
    public Collection<EmployeeCompetenceAssessment> getEmployeeCompetenceAssessmentCollection() {
        return employeeCompetenceAssessmentCollection;
    }

    public void setEmployeeCompetenceAssessmentCollection(Collection<EmployeeCompetenceAssessment> employeeCompetenceAssessmentCollection) {
        this.employeeCompetenceAssessmentCollection = employeeCompetenceAssessmentCollection;
    }

    @XmlTransient
    public Collection<Job> getJobCollection() {
        return jobCollection;
    }

    public void setJobCollection(Collection<Job> jobCollection) {
        this.jobCollection = jobCollection;
    }

    @XmlTransient
    public Collection<InCompanyEmployment> getInCompanyEmploymentCollection() {
        return inCompanyEmploymentCollection;
    }

    public void setInCompanyEmploymentCollection(Collection<InCompanyEmployment> inCompanyEmploymentCollection) {
        this.inCompanyEmploymentCollection = inCompanyEmploymentCollection;
    }

    @XmlTransient
    public Collection<Assessment> getAssessmentCollection() {
        return assessmentCollection;
    }

    public void setAssessmentCollection(Collection<Assessment> assessmentCollection) {
        this.assessmentCollection = assessmentCollection;
    }

    @XmlTransient
    public Collection<Assessment> getAssessmentCollection1() {
        return assessmentCollection1;
    }

    public void setAssessmentCollection1(Collection<Assessment> assessmentCollection1) {
        this.assessmentCollection1 = assessmentCollection1;
    }

    @XmlTransient
    public Collection<Assessment> getAssessmentCollection2() {
        return assessmentCollection2;
    }

    public void setAssessmentCollection2(Collection<Assessment> assessmentCollection2) {
        this.assessmentCollection2 = assessmentCollection2;
    }

    @XmlTransient
    public Collection<Assessment> getAssessmentCollection3() {
        return assessmentCollection3;
    }

    public void setAssessmentCollection3(Collection<Assessment> assessmentCollection3) {
        this.assessmentCollection3 = assessmentCollection3;
    }

    @XmlTransient
    public Collection<Assessment> getAssessmentCollection4() {
        return assessmentCollection4;
    }

    public void setAssessmentCollection4(Collection<Assessment> assessmentCollection4) {
        this.assessmentCollection4 = assessmentCollection4;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idemployee != null ? idemployee.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Employee)) {
            return false;
        }
        Employee other = (Employee) object;
        if ((this.idemployee == null && other.idemployee != null) || (this.idemployee != null && !this.idemployee.equals(other.idemployee))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.mavenproject1.Employee[ idemployee=" + idemployee + " ]";
    }
    
}
