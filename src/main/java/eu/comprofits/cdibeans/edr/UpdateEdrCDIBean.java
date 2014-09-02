/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.comprofits.cdibeans.edr;

import eu.comprofits.entities.edr.Edr;
import eu.comprofits.entities.employee.Employee;
import eu.comprofits.session.edr.EdrFacade;
import java.io.Serializable;
import java.util.ArrayList;
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
@Named(value = "UpdateEdrCDIBean")
@SessionScoped
public class UpdateEdrCDIBean implements Serializable {

    private static final long serialVersionUID = 1L;
    @EJB
    private EdrFacade edrFacade;

    private Edr edrObject;
    private Edr latestEdr;
    private List<Edr> edrList;
    private List<Employee> employeeList;

    public UpdateEdrCDIBean() {
    }

    @PostConstruct
    public void init() {
        edrList = edrFacade.findAll();
    }

    public String getEdrByEdrId(Edr edr) {
        for (Edr edrtemp : edrList) {
            if (edrtemp.getIdedr().equals(edr.getIdedr())) {
                this.edrObject = edrtemp;
                return "foundEdrById";
            }
        }
        return " edrNotFound";
    }
    
    public String getEdrsByEmployeeId(Employee employee) {
        
        ArrayList<Edr> tempList = new ArrayList<Edr>();
        for (Edr edrtemp : edrList) {
            if (edrtemp.getReviewedEmployeeIdemployee().equals(employee.getIdemployee())) {
                tempList.add(edrtemp);
                this.edrObject = edrtemp;
                
            }
        } if (tempList.isEmpty()) {
            return "edrNotFound";
        } else {
            edrList.clear();
            edrList = tempList;
            
            return "EdrsFound";
        }
    }

    public String closeEdrStatusByEmployee(Employee employee, int status) {

        if (edrObject.getReviewedEmployeeIdemployee().getIdemployee().equals(employee.getIdemployee())) {
            this.edrObject.setStatus(status);
            this.update();

            return "EdrStatusUpdated";
        } else {

            return "EdrStatusNotPossible";
        }
    }

    public String followUpOnLatestEdr(Edr edr) {
        ArrayList<Edr> temp = new ArrayList<Edr>();
        
        latestEdr = new Edr();
        
        for (Edr e : edrList) {
            if (e.getReviewedEmployeeIdemployee().getIdemployee().equals(edr.getReviewedEmployeeIdemployee())) {
                temp.add(e);
            }
        }
        if (temp.isEmpty()) {
            return "noPreviousEdrFound";
        } else {
            this.latestEdr = temp.get(temp.size() - 1);
            return "PreviousEdrFound";
        }
    }

    public Edr getEdrObject() {
        return edrObject;
    }

    public List<Edr> getEdrList() {
        return edrList;
    }

    public void setEdrObject(Edr edrObject) {
        this.edrObject = edrObject;
    }

    public void setEdrList(List<Edr> edrList) {
        this.edrList = edrList;
    }

    public String edit(Edr edr) {
        this.edrObject = edr;
        return "editEdr";
    }

    public String create() {
        this.edrObject = new Edr();
        return "createEdr";
    }

    public void remove(Edr edr) {
        try {
            edrFacade.remove(edr);
            edrList = edrFacade.findAll();

        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            e.getMessage(), null));
        }
    }

    public void update() {
        try {
            if (edrObject.getIdedr() == null) {
                edrFacade.create(edrObject);
            } else {
                edrFacade.edit(edrObject);
            }
            edrList = edrFacade.findAll();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            e.getMessage(), null));
        }
    }

}
