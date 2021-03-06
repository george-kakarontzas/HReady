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
package eu.comprofits.entities.edr;

import eu.comprofits.entities.employee.Employee;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.ResourceBundle;
import javax.faces.context.FacesContext;
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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author George Kakarontzas
 */

@Entity
@Table(name = "edr")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Edr.findAll", query = "SELECT e FROM Edr e"),
    @NamedQuery(name = "Edr.findByIdedr", query = "SELECT e FROM Edr e WHERE e.idedr = :idedr"),
    @NamedQuery(name = "Edr.findByYear", query = "SELECT e FROM Edr e WHERE e.year = :year"),
    @NamedQuery(name = "Edr.findByStatus", query = "SELECT e FROM Edr e WHERE e.status = :status"),
    @NamedQuery(name = "Edr.findByVerdict", query = "SELECT e FROM Edr e WHERE e.verdict = :verdict")})

public class Edr implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idedr")
    private Integer idedr;
    @Size(max = 45)
    @Column(name = "year")
    private String year;
    @Column(name = "status")
    private Integer status;
    @Size(max = 2147483647)
    @Column(name = "verdict")
    private String verdict;
    @JoinColumn(name = "immediate_manager_idemployee", referencedColumnName = "idemployee")
    @ManyToOne(optional = false)
    private Employee immediateManagerIdemployee;
    @JoinColumn(name = "reviewed_employee_idemployee", referencedColumnName = "idemployee")
    @ManyToOne(optional = false)
    private Employee reviewedEmployeeIdemployee;
    @JoinColumn(name = "head_of_department_idemployee", referencedColumnName = "idemployee")
    @ManyToOne(optional = false)
    private Employee headOfDepartmentIdemployee;
    @OneToMany(mappedBy = "previousEdrIdedr")
    private Collection<Edr> edrCollection;
    @JoinColumn(name = "previous_edr_idedr", referencedColumnName = "idedr")
    @ManyToOne
    private Edr previousEdrIdedr;
    @Column(name = "last_changed")
    @Temporal(TemporalType.DATE)
    private Date lastChanged;
    @Transient
    private int role;

    public Edr() {
    }

    public Edr(Integer idedr) {
        this.idedr = idedr;
    }

    public Integer getIdedr() {
        return idedr;
    }

    public void setIdedr(Integer idedr) {
        this.idedr = idedr;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }
    
    public String getConvertedRole(int role) {
        
        FacesContext context = FacesContext.getCurrentInstance();
        ResourceBundle bundle = context.getApplication().getResourceBundle(context, "msgs");
        
        String convertedRole = "";
        if (role == 1)
        {
            convertedRole = bundle.getString("initiator");
        }
        else if (role == 2)
        {
            convertedRole = bundle.getString("immediate_manager");
        }
        else if (role == 3)
        {
            convertedRole = bundle.getString("reviewed_employee_name");
        }
        
        return convertedRole;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public Integer getStatus() {
        return status;
    }
    
    public String getConvertedStatus (int status)
    {
        FacesContext context = FacesContext.getCurrentInstance();
        ResourceBundle bundle = context.getApplication().getResourceBundle(context, "msgs");
        
        String convertedStatus = "";
        if (status == 1)
        {
            convertedStatus = bundle.getString("status_initiated");
        }
        else if (status == 2)
        {
            convertedStatus = bundle.getString("status_ready");
        }
        else if (status == 3)
        {
            convertedStatus = bundle.getString("status_changed");
        }
        else if (status == 4)
        {
            convertedStatus = bundle.getString("status_rejected");
        }
        else if (status == 5)
        {
            convertedStatus = bundle.getString("status_accepted");
        }
        
        return convertedStatus;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getVerdict() {
        return verdict;
    }

    public void setVerdict(String verdict) {
        this.verdict = verdict;
    }

    public Employee getHeadOfDepartmentIdemployee() {
        return headOfDepartmentIdemployee;
    }

    public void setHeadOfDepartmentIdemployee(Employee headOfDepartmentIdemployee) {
        this.headOfDepartmentIdemployee = headOfDepartmentIdemployee;
    }

    public Employee getImmediateManagerIdemployee() {
        return immediateManagerIdemployee;
    }

    public void setImmediateManagerIdemployee(Employee immediateManagerIdemployee) {
        this.immediateManagerIdemployee = immediateManagerIdemployee;
    }

    public Employee getReviewedEmployeeIdemployee() {
        return reviewedEmployeeIdemployee;
    }

    public void setReviewedEmployeeIdemployee(Employee reviewedEmployeeIdemployee) {
        this.reviewedEmployeeIdemployee = reviewedEmployeeIdemployee;
    }

    @XmlTransient
    public Collection<Edr> getEdrCollection() {
        return edrCollection;
    }

    public void setEdrCollection(Collection<Edr> edrCollection) {
        this.edrCollection = edrCollection;
    }

    public Edr getPreviousEdrIdedr() {
        return previousEdrIdedr;
    }

    public void setPreviousEdrIdedr(Edr previousEdrIdedr) {
        this.previousEdrIdedr = previousEdrIdedr;
    }

    public Date getLastChanged() {
        return lastChanged;
    }

    public void setLastChanged(Date lastChanged) {
        this.lastChanged = lastChanged;
    }
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idedr != null ? idedr.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Edr)) {
            return false;
        }
        Edr other = (Edr) object;
        if ((this.idedr == null && other.idedr != null) || (this.idedr != null && !this.idedr.equals(other.idedr))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.mavenproject1.Edr[ idedr=" + idedr + " ]";
    }

    public Edr followUpOnLatestEdr(Edr edr, Employee employee) {
        return null;
    }

    public boolean closeEdrStatusByEmployee(Edr edr, Employee employee) {

        if (employee == this.reviewedEmployeeIdemployee) {
            this.status = edr.getStatus();
            return true;
        } else {
            return false;
        }
    }

 

//    public String edrToFormattedString() {
//
//       
//        Integer localEdrId = this.idedr;
//        String localYear = this.year;
//        String localVerdict = this.verdict;
//        Integer localStatus = this.status;
//        Collection<QuestionAnswer> localQuestionAnswerCollection = this.questionAnswerCollection;
//        Employee localImmediateManagerEmployee = this.immediateManagerIdemployee;
//        Employee localReviewedEmployee = this.reviewedEmployeeIdemployee;
//        Edr localPreviousEdr = this.previousEdrIdedr;
//        Collection<Edr> localEdrCollection = this.edrCollection;
//
//        //String output = "";
//        String output = "Employee Development Review \n\n"
//                + "Edr-Number: " + localEdrId + "\n"
//                + "Reviewed Year: " + localYear + "\n"
//                + "Verdict: " + localVerdict + "\n"
//                + "Actual status: " + localStatus + "\n"
//                + "Evaluator: " + localImmediateManagerEmployee.getIdemployee() + "\n"
//                + "Surveyed: " + localReviewedEmployee.getIdemployee() + "\n"
//                + "Previous-Edr: " + localPreviousEdr.getIdedr() + "\n\n"
//                + "Related Questions: \n\n"; //needs to be changed -> better subject
//
//        Iterator<QuestionAnswer> itQa = localQuestionAnswerCollection.iterator();
//
//        int i = 0;
//        int x = 1;
//
//        if (localQuestionAnswerCollection != null) {
//
//            while (i < localQuestionAnswerCollection.size()) {
//
//                Question questionAnswerObject = itQa.next();
//                String localQuestion = questionAnswerObject.getQuestion();
//                String localAnswer = questionAnswerObject.getAnswer();
//                Integer localIdQuestion = questionAnswerObject.getIdquestion();
//                Integer localQuestionCategory = questionAnswerObject.getQuestionCategory();
//
//                output = output + "Question " + x + ":\n"
//                        + "Question Id: " + localIdQuestion + "\n"
//                        + "Question Category: " + localQuestionCategory + "\n"
//                        + "Question: " + localQuestion + "\n"
//                        + "Answer: " + localAnswer + "\n\n";
//
//                i++;
//                if (i == localQuestionAnswerCollection.size()) {
//                    x = 1;
//                } else {
//                    x++;
//                }
//
//            }
//        } else {
//            output = output + "no related Questions \n";
//        }
//
//        output = output + "Related Edr's: \n\n";
//        i = 0;
//        if (localEdrCollection != null) {
//            Iterator<Edr> itEdr = localEdrCollection.iterator();
//
//            while (i < localEdrCollection.size()) {
//                output = output + "Edr :" + itEdr.next().getIdedr() + "\n";
//
//                i++;
//            }
//        } else {
//            output = output + "no previous Edr's\n";
//        }
//
//        return output;
//    }

}
