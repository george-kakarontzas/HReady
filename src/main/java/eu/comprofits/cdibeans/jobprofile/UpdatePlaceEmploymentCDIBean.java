/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.comprofits.cdibeans.jobprofile;

import eu.comprofits.entities.jobprofile.PlaceEmployment;
import eu.comprofits.entities.jobprofile.Job;
import eu.comprofits.session.jobprofile.PlaceEmploymentFacade;
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
public class UpdatePlaceEmploymentCDIBean implements Serializable {

    /**
     *
     * @author alexanderhoelzemann
     */
    @Named(value = "UpdatePlaceEmploymentCDIBean")
    @SessionScoped

    private static final long serialVersionUID = 1L;
    @EJB
    private PlaceEmploymentFacade placeEmploymentFacade;

    @EJB
    private JobFacade jobFacade;

    private PlaceEmployment placeEmploymentObject;
    private List<PlaceEmployment> placeEmploymentList;
    private List<Job> jobList;

    public UpdatePlaceEmploymentCDIBean() {
    }

    @PostConstruct
    public void init() {
        placeEmploymentList = placeEmploymentFacade.findAll();
        jobList = jobFacade.findAll();
    }

    public PlaceEmploymentFacade getPlaceEmploymentFacade() {
        return placeEmploymentFacade;
    }

    public void setPlaceEmploymentFacade(PlaceEmploymentFacade placeEmploymentFacade) {
        this.placeEmploymentFacade = placeEmploymentFacade;
    }

    public JobFacade getJobFacade() {
        return jobFacade;
    }

    public void setJobFacade(JobFacade jobFacade) {
        this.jobFacade = jobFacade;
    }

    public PlaceEmployment getPlaceEmploymentObject() {
        return placeEmploymentObject;
    }

    public void setPlaceEmploymentObject(PlaceEmployment placeEmploymentObject) {
        this.placeEmploymentObject = placeEmploymentObject;
    }

    public List<PlaceEmployment> getPlaceEmploymentList() {
        return placeEmploymentList;
    }

    public void setPlaceEmploymentList(List<PlaceEmployment> placeEmploymentList) {
        this.placeEmploymentList = placeEmploymentList;
    }

    public List<Job> getJobList() {
        return jobList;
    }

    public void setJobList(List<Job> jobList) {
        this.jobList = jobList;
    }

    public String edit(PlaceEmployment placeEmployment) {
        this.placeEmploymentObject = placeEmployment;
        return "editPlaceEmployment";
    }

    public String create() {
        this.placeEmploymentObject = new PlaceEmployment();
        return "createPlaceEmployment";
    }

    public void remove(PlaceEmployment placeEmployment) {
        try {
            placeEmploymentFacade.remove(placeEmployment);
            placeEmploymentList = placeEmploymentFacade.findAll();

        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            e.getMessage(), null));
        }
    }

    public void update() {
        try {
            if (placeEmploymentObject.getIdplaceEmployment() == null) {
                placeEmploymentFacade.create(placeEmploymentObject);
            } else {
                placeEmploymentFacade.edit(placeEmploymentObject);
            }
            placeEmploymentList = placeEmploymentFacade.findAll();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            e.getMessage(), null));
        }
    }

}
