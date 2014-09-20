/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.comprofits.cdibeans.edr;

import eu.comprofits.entities.edr.Edr;
import eu.comprofits.entities.employee.Employee;
import eu.comprofits.entities.jobprofile.BusinessArea;
import eu.comprofits.entities.jobprofile.Job;
import eu.comprofits.session.edr.EdrFacade;
import eu.comprofits.session.employee.EmployeeFacade;
import eu.comprofits.session.jobprofile.BusinessAreaFacade;
import eu.comprofits.session.jobprofile.JobFacade;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
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
@Named(value = "updateEdrCDIBean")
@SessionScoped
public class UpdateEdrCDIBean implements Serializable {

    private static final long serialVersionUID = 1L;
    @EJB
    private EdrFacade edrFacade;
    @EJB
    private EmployeeFacade employeeFacade;
    @EJB
    private JobFacade jobFacade;
    @EJB
    private BusinessAreaFacade businessAreaFacade;
    
    private BusinessArea selectedBusinessArea;
    private Edr edrObject;
    private Edr latestEdr;
    private Employee reviewedEmployee;
    private Employee immediateManager;
    private Job selectedJob;
    private List<BusinessArea> businessAreaList;
    private List<Job> jobList;
    private List<Edr> edrList;
    private List<Employee> employeeList;
    private boolean firstTime;

    public UpdateEdrCDIBean() {
    }

    @PostConstruct
    public void init() {
        edrObject = new Edr();
        reviewedEmployee = new Employee();
        immediateManager = new Employee();
        edrList = edrFacade.findAll();
        employeeList = employeeFacade.findAll();
        jobList = jobFacade.findAll();
        businessAreaList = businessAreaFacade.findAll();
    }

    public BusinessArea getSelectedBusinessArea() {
        return selectedBusinessArea;
    }

    public void setSelectedBusinessArea(BusinessArea selectedBusinessArea) {
        this.selectedBusinessArea = selectedBusinessArea;
    }

    public List<BusinessArea> getBusinessAreaList() {
        return businessAreaList;
    }

    public void setBusinessAreaList(List<BusinessArea> businessAreaList) {
        this.businessAreaList = businessAreaList;
    }
    
    public Job getSelectedJob() {
        return selectedJob;
    }

    public void setSelectedJob(Job selectedJob) {
        this.selectedJob = selectedJob;
    }

    public List<Job> getJobList() {
        return jobList;
    }

    public void setJobList(List<Job> jobList) {
        this.jobList = jobList;
    }
    
    public boolean isFirstTime() {
        return firstTime;
    }

    public void setFirstTime(boolean firstTime) {
        this.firstTime = firstTime;
    }

    public Edr getLatestEdr() {
        return latestEdr;
    }

    public void setLatestEdr(Edr latestEdr) {
        this.latestEdr = latestEdr;
    }

    public Employee getReviewedEmployee() {
        return reviewedEmployee;
    }

    public void setReviewedEmployee(Employee reviewedEmployee) {
        this.reviewedEmployee = reviewedEmployee;
    }

    public Employee getImmediateManager() {
        return immediateManager;
    }

    public void setImmediateManager(Employee immediateManager) {
        this.immediateManager = immediateManager;
    }


    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
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
    
    public String save() throws InterruptedException {
        if (edrObject != null) {
            if (firstTime) {
                edrFacade.create(edrObject);
            } else {
                edrFacade.edit(edrObject);
            }
        }
        FacesContext context = FacesContext.getCurrentInstance();
        ResourceBundle text = ResourceBundle.getBundle("messages", context.getViewRoot().getLocale());
        String message = text.getString("succesful_save_message");
        context.addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, message, message));
        return "";
    }

}
