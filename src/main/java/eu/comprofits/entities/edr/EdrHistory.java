/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.comprofits.entities.edr;

import eu.comprofits.session.edr.EdrHistoryFacade;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import javax.ejb.EJB;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author alexanderhoelzemann
 */
@Entity
public class EdrHistory implements Serializable {

    @EJB
    EdrHistoryFacade edrHistoryFacade;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    
    private Long id;
    @Column(name = "note")
    private String note;
    @Column(name = "date")
    private Date date;
    @Column(name = "time")
    private Timestamp timestamp;
    @Column(name = "idemployee")
    private Integer idemployee;
    @Column(name = "idedr")
    private Integer idedr;
    @Column(name = "idedrHistory")
    private Integer idedrHistory;

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

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getIdemployee() {
        return idemployee;
    }

    public void setIdemployee(Integer idemployee) {
        this.idemployee = idemployee;
    }

    public Integer getIdedr() {
        return idedr;
    }

    public void setIdedr(Integer idedr) {
        this.idedr = idedr;
    }

    public Integer getIdedrHistory() {
        return idedrHistory;
    }

    public void setIdedrHistory(Integer idedrHistory) {
        this.idedrHistory = idedrHistory;
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
        if (!(object instanceof EdrHistory)) {
            return false;
        }
        EdrHistory other = (EdrHistory) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "eu.comprofits.entities.edr.EdrHistory[ id=" + id + " ]";
    }

}
