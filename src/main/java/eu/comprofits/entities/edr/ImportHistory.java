/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.comprofits.entities.edr;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
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
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author alexanderhoelzemann
 */
@Entity
@Table(name = "importHistory")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ImportHistory.findAll", query = "SELECT i FROM ImportHistory i"),
    @NamedQuery(name = "ImportHistory.findByIdImportHistory", query = "SELECT i FROM ImportHistory i WHERE i.idimportHistory = :idimportHistory"),
    //@NamedQuery(name = "ImportHistory.findByIdedr", query = "SELECT i FROM ImportHistory i WHERE i.idedr = :idedr"),
    @NamedQuery(name = "ImportHistory.findByDate", query = "SELECT i FROM ImportHistory i WHERE i.date = :date"),
    @NamedQuery(name = "ImportHistory.findByIdEmployee", query = "SELECT i FROM ImportHistory i WHERE i.idemployee = :idemployee")})

public class ImportHistory implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Basic(optional = false)
    private Long id;
    @Column(name = "idimportHistory")
    @NotNull
    private Integer idimportHistory;
    @Column(name = "file")
    private String importedfile;
    @Column(name = "date")
    private Date date;
    @Column(name = "time")
    private Timestamp timestamp;
    @Column(name = "idemployee")
    private Integer idemployee;
    @JoinColumn(name = "edr_idedr", referencedColumnName = "idedr")
    @ManyToOne(optional = false)
    private Edr edrIdedr;
    @Column(name = "comment")
    private String comment;

    public ImportHistory() {
    }

    public Long getId() {
        return id;
    }

    public Integer getIdImportHistory() {
        return idimportHistory;
    }

    public String getFile() {
        return importedfile;
    }

    public Date getDate() {
        return date;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public Integer getIdEmployee() {
        return idemployee;
    }

    public Edr getIdEdr() {
        return edrIdedr;
    }

    public String getComment() {
        return comment;
    }

    public void setIdImportHistory(Integer idImportHistory) {
        this.idimportHistory = idImportHistory;
    }

    public void setFile(String file) {
        this.importedfile = file;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public void setIdEmployee(Integer idemployee) {
        this.idemployee = idemployee;
    }

    public void setIdEdr(Edr edrIdedr) {
        this.edrIdedr = edrIdedr;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        Integer hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ImportHistory)) {
            return false;
        }
        ImportHistory other = (ImportHistory) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "eu.comprofits.entities.edr.ImportHistory[ id=" + id + " ]";
    }

    public String toFormattedString() {
        return "ImportHistory{" + "idImportHistory=" + idimportHistory + ", \ndate=" + date + ", \ntimestamp=" + timestamp + ", \nidemployee=" + idemployee + ", \nidedr=" + edrIdedr.getIdedr() + ", \ncomment=" + comment + '}';
    }

}
