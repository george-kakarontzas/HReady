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
@Table(name = "question_category")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "QuestionCategory.findAll", query = "SELECT q FROM Question q"),
    @NamedQuery(name = "QuestionCategory.findByIdquestioncat", query = "SELECT q FROM Question q WHERE q.idquestion = :idquestion"),
    @NamedQuery(name = "QuestionCategory.findByCategory", query = "SELECT q FROM Question q WHERE q.questionCategory = :questionCategory")
    })

public class QuestionCategory implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idquestioncat")
    private Integer idquestioncat;
    @Column(name = "question")
    @Size(max = 2147483647)
    private String question;

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

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
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
