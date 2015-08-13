/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.comprofits.entities.edr;

import eu.comprofits.entities.employee.Employee;
import java.io.Serializable;
import java.sql.Date;
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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author alexanderhoelzemann
 */
@Entity
@Table(name = "edrnotes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EdrNotes.findAll", query = "SELECT e FROM EdrNotes e"),
    @NamedQuery(name = "EdrNotes.findByIdNotes", query = "SELECT e FROM EdrNotes e WHERE e.idnote = :idnote"),
    @NamedQuery(name = "EdrNotes.findByDate", query = "SELECT e FROM EdrNotes e WHERE e.date = :date"),
    @NamedQuery(name = "EdrNotes.findByIdEdr", query = "SELECT e FROM EdrNotes e WHERE e.edrIdedr = :edr_idedr")})

public class EdrNotes implements Serializable {
   
    private static final long serialVersionUID = 1L;
    @Column(name = "idnote")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer idnote;
    @JoinColumn(name = "idedr", referencedColumnName = "idedr")
    @ManyToOne(optional = false)
    private Edr edrIdedr;
    @Column(name = "subject")
    private String subject;
    @Column(name = "message")
    private String message;
    @Column(name = "date")
    private Date date;
    @JoinColumn(name = "author_idemployee", referencedColumnName = "idemployee")
    @ManyToOne(optional = false)
    private Employee authorIdemployee;
    @Transient
    private boolean lastEntry;

    public Integer getIdnote() {
        return idnote;
    }

    public void setIdnote(Integer idnote) {
        this.idnote = idnote;
    }

    public Edr getEdrIdedr() {
        return edrIdedr;
    }

    public void setEdrIdedr(Edr edrIdedr) {
        this.edrIdedr = edrIdedr;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Employee getAuthorIdemployee() {
        return authorIdemployee;
    }

    public void setAuthorIdemployee(Employee authorIdemployee) {
        this.authorIdemployee = authorIdemployee;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public boolean isLastEntry() {
        return lastEntry;
    }

    public void setLastEntry(boolean lastEntry) {
        this.lastEntry = lastEntry;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idnote != null ? idnote.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EdrNotes)) {
            return false;
        }
        EdrNotes other = (EdrNotes) object;
        if ((this.idnote == null && other.idnote != null) || (this.idnote != null && !this.idnote.equals(other.idnote))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "eu.comprofits.entities.edr.EdrNotes[ id=" + idnote + " ]";
    }

}
