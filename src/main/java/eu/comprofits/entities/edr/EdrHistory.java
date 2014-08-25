/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.comprofits.entities.edr;

import eu.comprofits.entities.employee.Employee;
import eu.comprofits.session.edr.EdrHistoryFacade;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import javax.ejb.EJB;
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
 * @author alexanderhoelzemann
 */
@Entity
@Table(name = "edrHistory")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EdrHistory.findAll", query = "SELECT e FROM EdrHistory e"),
    @NamedQuery(name = "EdrHistory.findByIdEdrHistory", query = "SELECT e FROM EdrHistory e WHERE e.idedrHistory = :idedrHistory"),
    @NamedQuery(name = "EdrHistory.findByDate", query = "SELECT e FROM EdrHistory e WHERE e.date = :date"),
    @NamedQuery(name = "EdrHistory.findByIdEdr", query = "SELECT e FROM EdrHistory e WHERE e.idedr = :idedr")})

public class EdrHistory implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Basic(optional = false)
    @Column
    private Integer idedrHistory;
    @Column(name = "note")
    private String note;
    @Column(name = "date")
    private Date date;
    @Column(name = "time")
    private Timestamp timestamp;
    @JoinColumn(name = "employee_idemployee", referencedColumnName = "idemployee")
    @ManyToOne(optional = false)
    private Employee idemployee;
    @JoinColumn(name = "edr_idedr", referencedColumnName = "idedr")
    @ManyToOne(optional = false)
    private Edr idedr;

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

    public Employee getIdemployee() {
        return idemployee;
    }

    public void setIdemployee(Employee idemployee) {
        this.idemployee = idemployee;
    }

    public Edr getIdedr() {
        return idedr;
    }

    public void setIdedr(Edr idedr) {
        this.idedr = idedr;
    }

    public Integer getIdedrHistory() {
        return idedrHistory;
    }

    public void setIdedrHistory(Integer idedrHistory) {
        this.idedrHistory = idedrHistory;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idedrHistory != null ? idedrHistory.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EdrHistory)) {
            return false;
        }
        EdrHistory other = (EdrHistory) object;
        if ((this.idedrHistory == null && other.idedrHistory != null) || (this.idedrHistory != null && !this.idedrHistory.equals(other.idedrHistory))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "eu.comprofits.entities.edr.EdrHistory[ idedrHistory=" + idedrHistory + " ]";
    }

}
