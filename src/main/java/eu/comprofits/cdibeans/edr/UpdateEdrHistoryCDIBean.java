/* 
 * Copyright 2016 ComProFITS Project.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
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
            if (edrHistoryObject.getIdedrHistory()== null) {
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
