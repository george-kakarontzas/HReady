/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.comprofits.cdibeans.edr;

import eu.comprofits.entities.edr.Edr;
import eu.comprofits.entities.edr.ImportHistory;
import eu.comprofits.session.edr.EdrFacade;
import eu.comprofits.session.edr.ImportHistoryFacade;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author alexanderhoelzemann
 */
@Named(value = "UpdateImportHistoryCDIBean")
@SessionScoped
public class UpdateImportHistoryCDIBean implements Serializable {

    private static final long serialVersionUID = 1L;
    @EJB
    private ImportHistoryFacade importHistoryFacade;

    @EJB
    private EdrFacade edrFacade;

    private ImportHistory importHistoryObject;
    private List<ImportHistory> importHistoryList;
    private List<Edr> edrList;

    public UpdateImportHistoryCDIBean() {
    }

    @PostConstruct
    public void init() {
        importHistoryList = importHistoryFacade.findAll();
        edrList = edrFacade.findAll();
    }

    public ImportHistory getImportHistoryObject() {
        return importHistoryObject;
    }

    public List<ImportHistory> getImportHistoryList() {
        return importHistoryList;
    }

    public List<Edr> getEdrList() {
        return edrList;
    }

    public void setImportHistoryObject(ImportHistory importHistoryObject) {
        this.importHistoryObject = importHistoryObject;
    }

    public void setImportHistoryList(List<ImportHistory> importHistoryList) {
        this.importHistoryList = importHistoryList;
    }

    public void setEdrList(List<Edr> edrList) {
        this.edrList = edrList;
    }

    public String edit(ImportHistory importHistory) {
        this.importHistoryObject = importHistory;
        return "editImportHistory";
    }

    public String create() {
        this.importHistoryObject = new ImportHistory();
        return "createImportHistory";
    }

    public void remove(ImportHistory importHistory) {
        try {
            importHistoryFacade.remove(importHistory);
            importHistoryList = importHistoryFacade.findAll();

        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            e.getMessage(), null));
        }
    }

    public void update() {
        try {
            if (importHistoryObject.getIdImportHistory() == null) {
                importHistoryFacade.create(importHistoryObject);
            } else {
                importHistoryFacade.edit(importHistoryObject);
            }
            importHistoryList = importHistoryFacade.findAll();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            e.getMessage(), null));
        }
    }
   
}
