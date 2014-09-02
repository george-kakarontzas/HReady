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
@Table(name = "question_answer")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "QuestionAnswer.findAll", query = "SELECT q FROM QuestionAnswer q"),
    @NamedQuery(name = "QuestionAnswer.findByIdquestion", query = "SELECT q FROM QuestionAnswer q WHERE q.idquestion = :idquestion"),
    @NamedQuery(name = "QuestionAnswer.findByQuestionCategory", query = "SELECT q FROM QuestionAnswer q WHERE q.questionCategory = :questionCategory"),
    @NamedQuery(name = "QuestionAnswer.findByQuestion", query = "SELECT q FROM QuestionAnswer q WHERE q.question = :question"),
    @NamedQuery(name = "QuestionAnswer.findByAnswer", query = "SELECT q FROM QuestionAnswer q WHERE q.answer = :answer")})
public class QuestionAnswer implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idquestion")
    private Integer idquestion;
    @Column(name = "question_category")
    private Integer questionCategory;
    @Size(max = 2147483647)
    @Column(name = "question")
    private String question;
    @Size(max = 2147483647)
    @Column(name = "answer")
    private String answer;
    @JoinColumn(name = "edr_idedr", referencedColumnName = "idedr")
    @ManyToOne(optional = false)
    private Edr edrIdedr;

    public QuestionAnswer() {
    }

    public QuestionAnswer(Integer idquestion) {
        this.idquestion = idquestion;
    }

    public Integer getIdquestion() {
        return idquestion;
    }

    public void setIdquestion(Integer idquestion) {
        this.idquestion = idquestion;
    }

    public Integer getQuestionCategory() {
        return questionCategory;
    }

    public void setQuestionCategory(Integer questionCategory) {
        this.questionCategory = questionCategory;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Edr getEdrIdedr() {
        return edrIdedr;
    }

    public void setEdrIdedr(Edr edrIdedr) {
        this.edrIdedr = edrIdedr;
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
        if (!(object instanceof QuestionAnswer)) {
            return false;
        }
        QuestionAnswer other = (QuestionAnswer) object;
        if ((this.idquestion == null && other.idquestion != null) || (this.idquestion != null && !this.idquestion.equals(other.idquestion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.mavenproject1.QuestionAnswer[ idquestion=" + idquestion + " ]";
    }
    
}
