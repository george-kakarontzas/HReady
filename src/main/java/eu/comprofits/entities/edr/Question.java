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
import javax.persistence.Transient;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author George Kakarontzas
 */
@Entity
@Table(name = "question")
@XmlRootElement


public class Question implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idquestion")
    private Integer idquestion;
    @JoinColumn(name = "question_category_idquestioncat", referencedColumnName = "idquestioncat")
    @ManyToOne(optional = false)
    private QuestionCategory questionCategoryIdquestioncat;
    @Column(name = "question")
    @Size(max = 2147483647)
    private String question;
    @Transient
    private boolean checked;

    public Question() {
    }

    public Question(Integer idquestion) {
        this.idquestion = idquestion;
    }

    public Integer getIdquestion() {
        return idquestion;
    }

    public void setIdquestion(Integer idquestion) {
        this.idquestion = idquestion;
    }

    public QuestionCategory getQuestionCategoryIdquestioncat() {
        return questionCategoryIdquestioncat;
    }

    public void setQuestionCategoryIdquestioncat(QuestionCategory questionCategoryIdquestioncat) {
        this.questionCategoryIdquestioncat = questionCategoryIdquestioncat;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idquestion != null ? idquestion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Question)) {
            return false;
        }
        Question other = (Question) object;
        if ((this.idquestion == null && other.idquestion != null) || (this.idquestion != null && !this.idquestion.equals(other.idquestion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.mavenproject1.Question[ idquestion=" + idquestion + " ]";
    }
    
}
