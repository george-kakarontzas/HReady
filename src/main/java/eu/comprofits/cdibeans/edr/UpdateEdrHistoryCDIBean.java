/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.comprofits.cdibeans.edr;

import eu.comprofits.entities.edr.Edr;
import eu.comprofits.entities.edr.EdrHistory;
import eu.comprofits.session.edr.EdrFacade;
import eu.comprofits.session.edr.EdrHistoryFacade;
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
@Named(value = "UpdateEdrHistoryCDIBean")
@SessionScoped
public class UpdateEdrHistoryCDIBean implements Serializable {

    private static final long serialVersionUID = 1L;
    @EJB
    private EdrHistoryFacade edrHistoryFacade;

    @EJB
    private EdrFacade edrFacade;

    private EdrHistory edrHistoryObject;
    private List<EdrHistory> edrHistoryList;
    private List<Edr> edrList;

    public UpdateEdrHistoryCDIBean() {
    }

    @PostConstruct
    public void init() {
        edrHistoryList = edrHistoryFacade.findAll();
        edrList = edrFacade.findAll();
    }

    public EdrHistory getEdrHistoryObject() {
        return edrHistoryObject;
    }

    public List<EdrHistory> getEdrHistoryList() {
        return edrHistoryList;
    }

    public List<Edr> getEdrList() {
        return edrList;
    }

    public void setEdrHistoryObject(EdrHistory edrHistoryObject) {
        this.edrHistoryObject = edrHistoryObject;
    }

    public void setEdrHistoryList(List<EdrHistory> edrHistoryList) {
        this.edrHistoryList = edrHistoryList;
    }

    public void setEdrList(List<Edr> edrList) {
        this.edrList = edrList;
    }

    public String edit(EdrHistory edrHistory) {
        this.edrHistoryObject = edrHistory;
        return "editEdrHistory";
    }

    public String create() {
        this.edrHistoryObject = new EdrHistory();
        return "createImiportHistory";
    }

    public void remove(EdrHistory edrHistory) {
        try {
            edrHistoryFacade.remove(edrHistory);
            edrHistoryList = edrHistoryFacade.findAll();

        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            e.getMessage(), null));
        }
    }

    public void update() {
        try {
            if (edrHistoryObject.getId() == null) {
                edrHistoryFacade.create(edrHistoryObject);
            } else {
                edrHistoryFacade.edit(edrHistoryObject);
            }
            edrHistoryList = edrHistoryFacade.findAll();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            e.getMessage(), null));
        }
    }
   
}
