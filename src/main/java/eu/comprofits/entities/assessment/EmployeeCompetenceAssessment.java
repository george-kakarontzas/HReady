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
import eu.comprofits.entities.main.Competence;
import java.io.Serializable;
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
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author George Kakarontzas
 */
@Entity
@Table(name = "employee_competence_assessment")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EmployeeCompetenceAssessment.findAll", query = "SELECT e FROM EmployeeCompetenceAssessment e"),
    @NamedQuery(name = "EmployeeCompetenceAssessment.findByIdemployeeCompetenceAssessment", query = "SELECT e FROM EmployeeCompetenceAssessment e WHERE e.idemployeeCompetenceAssessment = :idemployeeCompetenceAssessment"),
    @NamedQuery(name = "EmployeeCompetenceAssessment.findByAssessment", query = "SELECT e FROM EmployeeCompetenceAssessment e WHERE e.assessment = :assessment")})
public class EmployeeCompetenceAssessment implements Serializable {
    @JoinColumn(name = "statement_idstatement", referencedColumnName = "idstatement")
    @ManyToOne(optional = false)
    private Statement statementIdstatement;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idemployee_competence_assessment")
    private Integer idemployeeCompetenceAssessment;
    @Column(name = "assessment")
    private Integer assessment;
    @JoinColumn(name = "assessor_idemployee", referencedColumnName = "idemployee")
    @ManyToOne(optional = false)
    private Employee assessorIdemployee;
    @JoinColumn(name = "competence_idcompetence", referencedColumnName = "idcompetence")
    @ManyToOne(optional = false)
    private Competence competenceIdcompetence;
    @JoinColumn(name = "assessment_idassessment", referencedColumnName = "idassessment")
    @ManyToOne(optional = false)
    private Assessment assessmentIdassessment;

    public EmployeeCompetenceAssessment() {
    }

    public EmployeeCompetenceAssessment(Integer idemployeeCompetenceAssessment) {
        this.idemployeeCompetenceAssessment = idemployeeCompetenceAssessment;
    }

    public Integer getIdemployeeCompetenceAssessment() {
        return idemployeeCompetenceAssessment;
    }

    public void setIdemployeeCompetenceAssessment(Integer idemployeeCompetenceAssessment) {
        this.idemployeeCompetenceAssessment = idemployeeCompetenceAssessment;
    }

    public Integer getAssessment() {
        return assessment;
    }

    public void setAssessment(Integer assessment) {
        this.assessment = assessment;
    }

    public Employee getAssessorIdemployee() {
        return assessorIdemployee;
    }

    public void setAssessorIdemployee(Employee assessorIdemployee) {
        this.assessorIdemployee = assessorIdemployee;
    }

    public Competence getCompetenceIdcompetence() {
        return competenceIdcompetence;
    }

    public void setCompetenceIdcompetence(Competence competenceIdcompetence) {
        this.competenceIdcompetence = competenceIdcompetence;
    }

    public Assessment getAssessmentIdassessment() {
        return assessmentIdassessment;
    }

    public void setAssessmentIdassessment(Assessment assessmentIdassessment) {
        this.assessmentIdassessment = assessmentIdassessment;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idemployeeCompetenceAssessment != null ? idemployeeCompetenceAssessment.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EmployeeCompetenceAssessment)) {
            return false;
        }
        EmployeeCompetenceAssessment other = (EmployeeCompetenceAssessment) object;
        if ((this.idemployeeCompetenceAssessment == null && other.idemployeeCompetenceAssessment != null) || (this.idemployeeCompetenceAssessment != null && !this.idemployeeCompetenceAssessment.equals(other.idemployeeCompetenceAssessment))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.mavenproject1.EmployeeCompetenceAssessment[ idemployeeCompetenceAssessment=" + idemployeeCompetenceAssessment + " ]";
    }

    public Statement getStatementIdstatement() {
        return statementIdstatement;
    }

    public void setStatementIdstatement(Statement statementIdstatement) {
        this.statementIdstatement = statementIdstatement;
    }
    
}
