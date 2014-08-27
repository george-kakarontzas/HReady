/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.comprofits.cdibeans.jobprofile;

import eu.comprofits.entities.jobprofile.BusinessArea;
import eu.comprofits.entities.jobprofile.Job;
import eu.comprofits.session.jobprofile.BusinessAreaFacade;
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
public class UpdateBusinessAreaCDIBean implements Serializable {

    /**
     *
     * @author alexanderhoelzemann
     */
    @Named(value = "UpdateBusinessAreaCDIBean")
    @SessionScoped

    private static final long serialVersionUID = 1L;
    @EJB
    private BusinessAreaFacade businessAreaFacade;

    @EJB
    private JobFacade jobFacade;

    private BusinessArea businessAreaObject;
    private List<BusinessArea> businessAreaList;
    private List<Job> jobList;

    public UpdateBusinessAreaCDIBean() {
    }

    @PostConstruct
    public void init() {
        businessAreaList = businessAreaFacade.findAll();
        jobList = jobFacade.findAll();
    }

    public BusinessAreaFacade getBusinessAreaFacade() {
        return businessAreaFacade;
    }

    public void setBusinessAreaFacade(BusinessAreaFacade businessAreaFacade) {
        this.businessAreaFacade = businessAreaFacade;
    }

    public JobFacade getJobFacade() {
        return jobFacade;
    }

    public void setJobFacade(JobFacade jobFacade) {
        this.jobFacade = jobFacade;
    }

    public BusinessArea getBusinessAreaObject() {
        return businessAreaObject;
    }

    public void setBusinessAreaObject(BusinessArea businessAreaObject) {
        this.businessAreaObject = businessAreaObject;
    }

    public List<BusinessArea> getBusinessAreaList() {
        return businessAreaList;
    }

    public void setBusinessAreaList(List<BusinessArea> businessAreaList) {
        this.businessAreaList = businessAreaList;
    }

    public List<Job> getJobList() {
        return jobList;
    }

    public void setJobList(List<Job> jobList) {
        this.jobList = jobList;
    }

    public String edit(BusinessArea businessArea) {
        this.businessAreaObject = businessArea;
        return "editBusinessArea";
    }

    public String create() {
        this.businessAreaObject = new BusinessArea();
        return "createBusinessArea";
    }

    public void remove(BusinessArea businessArea) {
        try {
            businessAreaFacade.remove(businessArea);
            businessAreaList = businessAreaFacade.findAll();

        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            e.getMessage(), null));
        }
    }

    public void update() {
        try {
            if (businessAreaObject.getIdbusinessArea() == null) {
                businessAreaFacade.create(businessAreaObject);
            } else {
                businessAreaFacade.edit(businessAreaObject);
            }
            businessAreaList = businessAreaFacade.findAll();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            e.getMessage(), null));
        }
    }

}
