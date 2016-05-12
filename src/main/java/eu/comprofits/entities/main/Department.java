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

import eu.comprofits.entities.employee.Employee;
import eu.comprofits.entities.jobprofile.Division;
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
 * @author George Kakarontzas
 */
@Entity
@Table(name = "department")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Department.findAll", query = "SELECT d FROM Department d"),
    @NamedQuery(name = "Department.findByIddepartment", query = "SELECT d FROM Department d WHERE d.iddepartment = :iddepartment"),
    @NamedQuery(name = "Department.findByDepartmentName", query = "SELECT d FROM Department d WHERE d.departmentName = :departmentName"),
    @NamedQuery(name = "Department.findByDivision", query="SELECT d FROM Department d WHERE d.divisionIdDivision = :divisionIdDivision")})
public class Department implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "iddepartment")
    private Integer iddepartment;
    @Size(max = 255)
    @Column(name = "department_name")
    private String departmentName;
    @JoinColumn(name = "head_of_department_idemployee", referencedColumnName = "idemployee")
    @ManyToOne
    private Employee headOfDepartmentIdemployee;
    @JoinColumn(name = "division_iddivision", referencedColumnName = "iddivision")
    @ManyToOne(optional = false)
    private Division divisionIdDivision;
    @OneToMany(mappedBy = "departmentIddepartment")
    private Collection<Employee> employeeCollection;

    public Department() {
    }

    public Department(Integer iddepartment) {
        this.iddepartment = iddepartment;
    }

    public Integer getIddepartment() {
        return iddepartment;
    }

    public void setIddepartment(Integer iddepartment) {
        this.iddepartment = iddepartment;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public Employee getHeadOfDepartmentIdemployee() {
        return headOfDepartmentIdemployee;
    }

    public void setHeadOfDepartmentIdemployee(Employee headOfDepartmentIdemployee) {
        this.headOfDepartmentIdemployee = headOfDepartmentIdemployee;
    }

    public Division getDivisionIddivision() {
        return divisionIdDivision;
    }

    public void setDivisionIddivision(Division divisionIddivision) {
        this.divisionIdDivision = divisionIddivision;
    }

    @XmlTransient
    public Collection<Employee> getEmployeeCollection() {
        return employeeCollection;
    }

    public void setEmployeeCollection(Collection<Employee> employeeCollection) {
        this.employeeCollection = employeeCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iddepartment != null ? iddepartment.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Department)) {
            return false;
        }
        Department other = (Department) object;
        if ((this.iddepartment == null && other.iddepartment != null) || (this.iddepartment != null && !this.iddepartment.equals(other.iddepartment))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.departmentName;
    }
    
}
