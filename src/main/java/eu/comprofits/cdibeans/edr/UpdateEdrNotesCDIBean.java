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
import eu.comprofits.entities.edr.EdrNotes;
import eu.comprofits.session.edr.EdrFacade;
import eu.comprofits.session.edr.EdrNotesFacade;
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
@Named(value = "UpdateEdrNotesCDIBean")
@SessionScoped
public class UpdateEdrNotesCDIBean implements Serializable {

    private static final long serialVersionUID = 1L;
    @EJB
    private EdrNotesFacade edrNotesFacade;

    @EJB
    private EdrFacade edrFacade;

    private EdrNotes edrNotesObject;
    private List<EdrNotes> edrNotesList;
    private List<Edr> edrList;

    public UpdateEdrNotesCDIBean() {
    }

    @PostConstruct
    public void init() {
        edrNotesList = edrNotesFacade.findAll();
        edrList = edrFacade.findAll();
    }

    public EdrNotes getEdrNotesObject() {
        return edrNotesObject;
    }

    public List<EdrNotes> getEdrNotesList() {
        return edrNotesList;
    }

    public List<Edr> getEdrList() {
        return edrList;
    }

    public void setEdrNotesObject(EdrNotes edrNotesObject) {
        this.edrNotesObject = edrNotesObject;
    }

    public void setEdrNotesList(List<EdrNotes> edrNotesList) {
        this.edrNotesList = edrNotesList;
    }

    public void setEdrList(List<Edr> edrList) {
        this.edrList = edrList;
    }

    public String edit(EdrNotes edrNotes) {
        this.edrNotesObject = edrNotes;
        return "editEdrNotes";
    }

    public String create() {
        this.edrNotesObject = new EdrNotes();
        return "createEdrNotes";
    }

    public void remove(EdrNotes edrNotes) {
        try {
            edrNotesFacade.remove(edrNotes);
            edrNotesList = edrNotesFacade.findAll();

        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            e.getMessage(), null));
        }
    }

    public void update() {
        try {
            if (edrNotesObject.getIdnote()== null) {
                edrNotesFacade.create(edrNotesObject);
            } else {
                edrNotesFacade.edit(edrNotesObject);
            }
            edrNotesList = edrNotesFacade.findAll();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            e.getMessage(), null));
        }
    }
   
}
