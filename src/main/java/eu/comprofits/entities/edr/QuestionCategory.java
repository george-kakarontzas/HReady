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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author George Kakarontzas
 */
@Entity
@Table(name = "question_category")
@XmlRootElement

public class QuestionCategory implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idquestioncat")
    private Integer idquestioncat;
    @Column(name = "category")
    @Size(max = 2147483647)
    private String questionCategory;

    public QuestionCategory() {
    }

    public QuestionCategory(Integer idquestioncat) {
        this.idquestioncat = idquestioncat;
    }

    public Integer getIdquestioncat() {
        return idquestioncat;
    }

    public void setIdquestioncat(Integer idquestioncat) {
        this.idquestioncat = idquestioncat;
    }

    public String getQuestionCategory() {
        return questionCategory;
    }

    public void setQuestionCategory(String questionCategory) {
        this.questionCategory = questionCategory;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idquestioncat != null ? idquestioncat.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof QuestionCategory)) {
            return false;
        }
        QuestionCategory other = (QuestionCategory) object;
        if ((this.idquestioncat == null && other.idquestioncat != null) || (this.idquestioncat != null && !this.idquestioncat.equals(other.idquestioncat))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.mavenproject1.QuestionCategory[ idquestioncat=" + idquestioncat + " ]";
    }
    
}
