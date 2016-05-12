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
