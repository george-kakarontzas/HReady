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

import eu.comprofits.entities.employee.Employee;
import eu.comprofits.entities.employee.InCompanyEmployment;
import eu.comprofits.entities.main.OrganisationalPosition;
import eu.comprofits.entities.jobapplicant.JobAdvertisement;
import eu.comprofits.entities.main.Department;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author George Kakarontzas
 */
@Entity
@Table(name = "job")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Job.findAll", query = "SELECT j FROM Job j"),
    @NamedQuery(name = "Job.findByIdjob", query = "SELECT j FROM Job j WHERE j.idjob = :idjob"),
    @NamedQuery(name = "Job.findByJobTitle", query = "SELECT j FROM Job j WHERE j.jobTitle = :jobTitle"),
    @NamedQuery(name = "Job.findByJobDescription", query = "SELECT j FROM Job j WHERE j.jobDescription = :jobDescription")})
    @NamedQuery(name = "Job.findByDepartment", query = "SELECT j FROM Job j WHERE j.departmentIddepartment = :departmentIddepartment")
public class Job implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idjob")
    private Integer idjob;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "job_title")
    private String jobTitle;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "job_description")
    private String jobDescription;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "jobIdjob")
    private Collection<ProfessionalExperienceMinRequirements> professionalExperienceMinRequirementsCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "jobIdjob")
    private Collection<JobAdvertisement> jobAdvertisementCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "jobIdjob")
    private Collection<JobStudyMinRequirements> jobStudyMinRequirementsCollection;
    @JoinColumn(name = "place_employment_idplace_employment", referencedColumnName = "idplace_employment")
    @ManyToOne
    private PlaceEmployment placeEmploymentIdplaceEmployment;
    @JoinColumn(name = "organisational_position_idorganisational_position", referencedColumnName = "idorganisational_position")
    @ManyToOne
    private OrganisationalPosition organisationalPositionIdorganisationalPosition;
    @JoinColumn(name = "reporting_to_idemployee", referencedColumnName = "idemployee")
    @ManyToOne
    private Employee reportingToIdemployee;
    @JoinColumn(name = "business_area_idbusiness_area", referencedColumnName = "iddepartment")
    @ManyToOne
    private Department departmentIddepartment;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "jobIdjob")
    private Collection<InCompanyEmployment> inCompanyEmploymentCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "jobIdjob")
    private Collection<CompetencesRequirement> competencesRequirementCollection;
    @Column(name = "job_status")
    private Boolean status;
    
    public Job() {
    }

    public Job(Integer idjob) {
        this.idjob = idjob;
    }

    public Job(Integer idjob, String jobTitle, String jobDescription) {
        this.idjob = idjob;
        this.jobTitle = jobTitle;
        this.jobDescription = jobDescription;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
    
    public Integer getIdjob() {
        return idjob;
    }

    public void setIdjob(Integer idjob) {
        this.idjob = idjob;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    @XmlTransient
    public Collection<ProfessionalExperienceMinRequirements> getProfessionalExperienceMinRequirementsCollection() {
        return professionalExperienceMinRequirementsCollection;
    }

    public void setProfessionalExperienceMinRequirementsCollection(Collection<ProfessionalExperienceMinRequirements> professionalExperienceMinRequirementsCollection) {
        this.professionalExperienceMinRequirementsCollection = professionalExperienceMinRequirementsCollection;
    }

    @XmlTransient
    public Collection<JobAdvertisement> getJobAdvertisementCollection() {
        return jobAdvertisementCollection;
    }

    public void setJobAdvertisementCollection(Collection<JobAdvertisement> jobAdvertisementCollection) {
        this.jobAdvertisementCollection = jobAdvertisementCollection;
    }

    
    @XmlTransient
    public Collection<JobStudyMinRequirements> getJobStudyMinRequirementsCollection() {
        return jobStudyMinRequirementsCollection;
    }

    public void setJobStudyMinRequirementsCollection(Collection<JobStudyMinRequirements> jobStudyMinRequirementsCollection) {
        this.jobStudyMinRequirementsCollection = jobStudyMinRequirementsCollection;
    }

    public PlaceEmployment getPlaceEmploymentIdplaceEmployment() {
        return placeEmploymentIdplaceEmployment;
    }

    public void setPlaceEmploymentIdplaceEmployment(PlaceEmployment placeEmploymentIdplaceEmployment) {
        this.placeEmploymentIdplaceEmployment = placeEmploymentIdplaceEmployment;
    }

    public OrganisationalPosition getOrganisationalPositionIdorganisationalPosition() {
        return organisationalPositionIdorganisationalPosition;
    }

    public void setOrganisationalPositionIdorganisationalPosition(OrganisationalPosition organisationalPositionIdorganisationalPosition) {
        this.organisationalPositionIdorganisationalPosition = organisationalPositionIdorganisationalPosition;
    }

    public Employee getReportingToIdemployee() {
        return reportingToIdemployee;
    }

    public void setReportingToIdemployee(Employee reportingToIdemployee) {
        this.reportingToIdemployee = reportingToIdemployee;
    }

    public Department getDepartmentIddepartment() {
        return departmentIddepartment;
    }

    public void setDepartmentIddepartment(Department departmentIddepartment) {
        this.departmentIddepartment = departmentIddepartment;
    }

    @XmlTransient
    public Collection<InCompanyEmployment> getInCompanyEmploymentCollection() {
        return inCompanyEmploymentCollection;
    }

    public void setInCompanyEmploymentCollection(Collection<InCompanyEmployment> inCompanyEmploymentCollection) {
        this.inCompanyEmploymentCollection = inCompanyEmploymentCollection;
    }

    @XmlTransient
    public Collection<CompetencesRequirement> getCompetencesRequirementCollection() {
        return competencesRequirementCollection;
    }

    public void setCompetencesRequirementCollection(Collection<CompetencesRequirement> competencesRequirementCollection) {
        this.competencesRequirementCollection = competencesRequirementCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idjob != null ? idjob.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Job)) {
            return false;
        }
        Job other = (Job) object;
        if ((this.idjob == null && other.idjob != null) || (this.idjob != null && !this.idjob.equals(other.idjob))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        //return "com.mycompany.mavenproject1.Job[ idjob=" + idjob + " ]";
        return "" + jobTitle;
    }
    
}
