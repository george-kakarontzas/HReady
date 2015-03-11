/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
 * @author george
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
