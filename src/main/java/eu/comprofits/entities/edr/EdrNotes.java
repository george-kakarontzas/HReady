/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.comprofits.entities.edr;

import java.io.Serializable;
import java.sql.Date;
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
 * @author alexanderhoelzemann
 */
@Entity
@Table(name = "edrNotes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EdrNotes.findAll", query = "SELECT e FROM EdrNotes e"),
    @NamedQuery(name = "EdrNotes.findByIdNotes", query = "SELECT e FROM EdrNotes e WHERE e.idnote = :idnote"),
    @NamedQuery(name = "EdrNotes.findByDate", query = "SELECT e FROM EdrNotes e WHERE e.date = :date"),
    @NamedQuery(name = "EdrNotes.findByIdEdr", query = "SELECT e FROM EdrNotes e WHERE e.edrIdedr = :idedr")})
public class EdrNotes implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private static final long serialVersionUID = 1L;
    @Column(name = "idnote")
    private Integer idnote;
    @JoinColumn(name = "edr_idEdr", referencedColumnName = "idedr")
    @ManyToOne(optional = false)
    private Edr edrIdedr;
    @Column(name = "note")
    private String note;
    @Column(name = "date")
    private Date date;

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

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EdrNotes)) {
            return false;
        }
        EdrNotes other = (EdrNotes) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "eu.comprofits.entities.edr.EdrNotes[ id=" + id + " ]";
    }

}
