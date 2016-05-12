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
package eu.comprofits.entities.assessment;

import eu.comprofits.entities.employee.Employee;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author George Kakarontzas
 */
@Entity
@Table(name = "assessment")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Assessment.findAll", query = "SELECT a FROM Assessment a"),
    @NamedQuery(name = "Assessment.findByIdassessment", query = "SELECT a FROM Assessment a WHERE a.idassessment = :idassessment"),
    @NamedQuery(name = "Assessment.findByDateCreated", query = "SELECT a FROM Assessment a WHERE a.dateCreated = :dateCreated"),
    @NamedQuery(name = "Assessment.findByAssesseeDepartment",
            query = "SELECT a FROM Assessment a WHERE a.assesseeIdemployee.departmentIddepartment = :department")})
public class Assessment implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idassessment")
    private Integer idassessment;
    @Column(name = "date_created")
    @Temporal(TemporalType.DATE)
    private Date dateCreated;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "assessmentIdassessment")
    private Collection<EmployeeCompetenceAssessment> employeeCompetenceAssessmentCollection;
    @JoinColumn(name = "immediate_manager_idemployee", referencedColumnName = "idemployee")
    @ManyToOne(optional = false)
    private Employee immediateManagerIdemployee;
    @JoinColumn(name = "colleague3_idemployee", referencedColumnName = "idemployee")
    @ManyToOne(optional = false)
    private Employee colleague3Idemployee;
    @JoinColumn(name = "colleague2_idemployee", referencedColumnName = "idemployee")
    @ManyToOne(optional = false)
    private Employee colleague2Idemployee;
    @JoinColumn(name = "colleague1_idemployee", referencedColumnName = "idemployee")
    @ManyToOne(optional = false)
    private Employee colleague1Idemployee;
    @JoinColumn(name = "assessee_idemployee", referencedColumnName = "idemployee")
    @ManyToOne(optional = false)
    private Employee assesseeIdemployee;
    @Column(name = "issue_date")
    @Temporal(TemporalType.DATE)
    private Date issueDate;
    @Column(name = "deadline")
    @Temporal(TemporalType.DATE)
    private Date deadline;
    @Column(name = "completed")
    private boolean completed;
    @Column(name="conclusion")
    private String conclusion;

    public Assessment() {
    }

    public Assessment(Integer idassessment) {
        this.idassessment = idassessment;
    }

    public Integer getIdassessment() {
        return idassessment;
    }

    public void setIdassessment(Integer idassessment) {
        this.idassessment = idassessment;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public String getConclusion() {
        return conclusion;
    }

    public void setConclusion(String conclusion) {
        this.conclusion = conclusion;
    }

    
    
    @XmlTransient
    public Collection<EmployeeCompetenceAssessment> getEmployeeCompetenceAssessmentCollection() {
        return employeeCompetenceAssessmentCollection;
    }

    public void setEmployeeCompetenceAssessmentCollection(Collection<EmployeeCompetenceAssessment> employeeCompetenceAssessmentCollection) {
        this.employeeCompetenceAssessmentCollection = employeeCompetenceAssessmentCollection;
    }

    public Employee getImmediateManagerIdemployee() {
        return immediateManagerIdemployee;
    }

    public void setImmediateManagerIdemployee(Employee immediateManagerIdemployee) {
        this.immediateManagerIdemployee = immediateManagerIdemployee;
    }

    public Employee getColleague3Idemployee() {
        return colleague3Idemployee;
    }

    public void setColleague3Idemployee(Employee colleague3Idemployee) {
        this.colleague3Idemployee = colleague3Idemployee;
    }

    public Employee getColleague2Idemployee() {
        return colleague2Idemployee;
    }

    public void setColleague2Idemployee(Employee colleague2Idemployee) {
        this.colleague2Idemployee = colleague2Idemployee;
    }

    public Employee getColleague1Idemployee() {
        return colleague1Idemployee;
    }

    public void setColleague1Idemployee(Employee colleague1Idemployee) {
        this.colleague1Idemployee = colleague1Idemployee;
    }

    public Employee getAssesseeIdemployee() {
        return assesseeIdemployee;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    
    
    public void setAssesseeIdemployee(Employee assesseeIdemployee) {
        this.assesseeIdemployee = assesseeIdemployee;
    }
    
    @Transient 
    public String getShortDate() {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        String strDateCreated = df.format(dateCreated);
        return strDateCreated;
    }
 
    @Transient 
    public String getShortIssueDate() {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        String strDeadline = df.format(issueDate);
        return strDeadline;
    }
    
    @Transient 
    public String getShortDeadlineDate() {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        String strDeadline = df.format(deadline);
        return strDeadline;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idassessment != null ? idassessment.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Assessment)) {
            return false;
        }
        Assessment other = (Assessment) object;
        if ((this.idassessment == null && other.idassessment != null) || (this.idassessment != null && !this.idassessment.equals(other.idassessment))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        String result = this.getShortDate()+" "+assesseeIdemployee.getLastName()+" "+
                assesseeIdemployee.getFirstName();
        return result;
    }

}
