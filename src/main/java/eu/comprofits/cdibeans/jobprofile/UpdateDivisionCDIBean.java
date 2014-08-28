/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.comprofits.cdibeans.jobprofile;

import eu.comprofits.entities.jobprofile.Division;
import eu.comprofits.entities.jobprofile.Job;
import eu.comprofits.session.jobprofile.DivisionFacade;
import eu.comprofits.session.jobprofile.JobFacade;
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
@Named(value = "UpdateDivisionCDIBean")
@SessionScoped
public class UpdateDivisionCDIBean implements Serializable {

    private static final long serialVersionUID = 1L;
    @EJB
    private DivisionFacade divisionFacade;

    @EJB
    private JobFacade jobFacade;

    private Division divisionObject;
    private List<Division> divisionList;
    private List<Job> jobList;

    public UpdateDivisionCDIBean() {
    }

    @PostConstruct
    public void init() {
        divisionList = divisionFacade.findAll();
        jobList = jobFacade.findAll();
    }

    public DivisionFacade getDivisionFacade() {
        return divisionFacade;
    }

    public void setDivisionFacade(DivisionFacade divisionFacade) {
        this.divisionFacade = divisionFacade;
    }

    public JobFacade getJobFacade() {
        return jobFacade;
    }

    public void setJobFacade(JobFacade jobFacade) {
        this.jobFacade = jobFacade;
    }

    public Division getDivisionObject() {
        return divisionObject;
    }

    public void setDivisionObject(Division divisionObject) {
        this.divisionObject = divisionObject;
    }

    public List<Division> getDivisionList() {
        return divisionList;
    }

    public void setDivisionList(List<Division> divisionList) {
        this.divisionList = divisionList;
    }

    public List<Job> getJobList() {
        return jobList;
    }

    public void setJobList(List<Job> jobList) {
        this.jobList = jobList;
    }

    public String edit(Division division) {
        this.divisionObject = division;
        return "editDivision";
    }

    public String create() {
        this.divisionObject = new Division();
        return "createDivision";
    }

    public void remove(Division division) {
        try {
            divisionFacade.remove(division);
            divisionList = divisionFacade.findAll();

        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            e.getMessage(), null));
        }
    }

    public void update() {
        try {
            if (divisionObject.getIddivision() == null) {
                divisionFacade.create(divisionObject);
            } else {
                divisionFacade.edit(divisionObject);
            }
            divisionList = divisionFacade.findAll();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            e.getMessage(), null));
        }
    }

}
