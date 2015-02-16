/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.comprofits.cdibeans.jobprofile;

import eu.comprofits.entities.employee.Employee;
import eu.comprofits.entities.jobapplicant.JobAdvertisement;
import eu.comprofits.entities.jobprofile.CompetencesRequirement;
import eu.comprofits.entities.jobprofile.Job;
import eu.comprofits.entities.main.Competence;
import eu.comprofits.entities.main.OrganisationalPosition;
import eu.comprofits.session.employee.EmployeeFacade;
import eu.comprofits.session.jobapplicant.JobAdvertisementFacade;
import eu.comprofits.session.jobprofile.CompetencesRequirementFacade;
import eu.comprofits.session.jobprofile.JobFacade;
import eu.comprofits.session.main.CompetenceFacade;
import eu.comprofits.session.main.OrganisationalPositionFacade;
import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

/**
 *
 * @author alexanderhoelzemann
 */
@Named(value = "updateJobCDIBean")
@SessionScoped
public class UpdateJobCDIBean implements Serializable {

    @EJB
    private OrganisationalPositionFacade organisationalPositionFacade;

    /**
     *
     * @author alexanderhoelzemann
     */
    private static final long serialVersionUID = 1L;
    @EJB
    private JobFacade jobFacade;

    @EJB
    private JobAdvertisementFacade jobAdvertisementFacade;
    @EJB
    private EmployeeFacade employeeFacade;
    @EJB
    private CompetenceFacade competenceFacade;
    @EJB
    private CompetencesRequirementFacade competencesRequirementFacade;

    private JobAdvertisement jobAdvertisementObject;
    private List<JobAdvertisement> jobAdvertisementList;
    private Job jobObject;
    private List<Job> jobList;
    private List<Job> filteredJobList;
    //private Job selectedJob;
    private List<OrganisationalPosition> positions;
    private OrganisationalPosition position;
    private Employee employeeObject;
    private List<Employee> employeeList;
    private List<Employee> employeeReportingToList;
    private TreeNode competencesTree;
    private TreeNode competencesRequirementsTree;

    public UpdateJobCDIBean() {
    }

    @PostConstruct
    public void init() {
        refreshJobList();
    }

    public List<Job> getFilteredJobList() {
        return filteredJobList;
    }

    public void setFilteredJobList(List<Job> filteredJobList) {
        this.filteredJobList = filteredJobList;
    }

    public List<OrganisationalPosition> getPositions() {
        return positions;
    }

    public void setPositions(List<OrganisationalPosition> positions) {
        this.positions = positions;
    }

    public Job getJobObject() {
        return jobObject;
    }

    public void setJobObject(Job jobObject) {
        this.jobObject = jobObject;
    }

    public OrganisationalPosition getPosition() {
        return position;
    }

    public void setPosition(OrganisationalPosition position) {
        this.position = position;
    }

    public Employee getEmployeeObject() {
        return employeeObject;
    }

    public void setEmployeeObject(Employee employeeObject) {
        this.employeeObject = employeeObject;
    }

    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }

    public List<Job> getJobList() {
        return jobList;
    }

    public void setJobList(List<Job> jobList) {
        this.jobList = jobList;
    }

    public JobAdvertisement getJobAdvertisementObject() {
        return jobAdvertisementObject;
    }

    public void setJobAdvertisementObject(JobAdvertisement jobAdvertisementObject) {
        this.jobAdvertisementObject = jobAdvertisementObject;
    }

    public List<JobAdvertisement> getJobAdvertisementList() {
        return jobAdvertisementList;
    }

    public void setJobAdvertisementList(List<JobAdvertisement> jobAdvertisementList) {
        this.jobAdvertisementList = jobAdvertisementList;
    }

    public List<Employee> getEmployeeReportingToList() {
        return employeeReportingToList;
    }

    public void setEmployeeReportingToList(List<Employee> employeeReportingToList) {
        this.employeeReportingToList = employeeReportingToList;
    }
    
    public TreeNode getCompetencesTree() {
        return competencesTree;
    }

    public void setCompetencesTree(TreeNode competencesTree) {
        this.competencesTree = competencesTree;
    }
    
    public TreeNode getCompetencesRequirementsTree() {
        return competencesRequirementsTree;
    }

    public void setCompetencesRequirementsTree(TreeNode competencesRequirementsTree) {
        this.competencesRequirementsTree = competencesRequirementsTree;
    }
    
    public String edit(Job job) {
        this.jobObject = job;
        this.employeeList = this.employeeFacade.findAll();
        this.positions = this.organisationalPositionFacade.findAll();
        this.competencesTree = competenceFacade.getCompetencesTree();
        this.competencesRequirementsTree = competencesRequirementFacade.getCompetencesRequirementsTree(competencesTree, this.jobObject);
        return "editJobProfile";
    }

    public String create() {
        this.jobObject = new Job();
        this.employeeList = this.employeeFacade.findAll();
        this.positions = this.organisationalPositionFacade.findAll();
        this.competencesTree = competenceFacade.getCompetencesTree();
        this.competencesRequirementsTree = competencesRequirementFacade.getCompetencesRequirementsTree(competencesTree, this.jobObject);
        return "editJobProfile";
    }

    public void refreshJobList() {
        jobList = jobFacade.findAll();
    }

    public JobAdvertisement getSpecificJobAdvertisement(Integer jobId) {

        JobAdvertisement jobAdvertisement = new JobAdvertisement();
        for (JobAdvertisement jobAd : jobAdvertisementList) {
            if (jobId.equals(jobAd.getJobIdjob().getIdjob())) {
                jobAdvertisement = jobAd;
            } else {
                jobAdvertisement = null;
            }
        }
        return jobAdvertisement;
    }

    public String getFieldOfResponsibilityForAJob(Integer jobId) {

        JobAdvertisement jobAdvertisement = new JobAdvertisement();
        for (JobAdvertisement jobAd : jobAdvertisementList) {
            if (jobId.equals(jobAd.getJobIdjob().getIdjob())) {
                jobAdvertisement = jobAd;
            }
        }
        return jobAdvertisement.getFieldsOfResponsibility();
    }

    public String remove(Job e) {

        try {
            for (CompetencesRequirement cr : competencesRequirementFacade.getRequirementsForJob(this.jobObject))
            {
                competencesRequirementFacade.remove(cr);
            }
            /*for (CompetencesRequirement cr : competenceRequirements) {
                if (cr.getJobIdjob().getIdjob().equals(e.getIdjob())) {
                    competencesRequirementFacade.remove(cr);
                }
            }*/
            jobFacade.remove(e);
            if (filteredJobList != null) {
                filteredJobList.remove(e);
            }
            refreshJobList();
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            ex.getMessage(), null));
        }
        jobList = jobFacade.findAll();

        return "updateJobProfile";
    }

    public String update() {
        try {
            if (jobObject.getIdjob() == null) {
                jobFacade.create(jobObject);
                this.competencesRequirementFacade.updateCompetencesRequirements(competencesRequirementsTree, jobObject);
                //jobAdvertisementFacade.create(jobAdvertisementObject);
            } else {
                jobFacade.edit(jobObject);
                this.competencesRequirementFacade.updateCompetencesRequirements(competencesRequirementsTree, jobObject);
            }
            jobList = jobFacade.findAll();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            e.getMessage(), null));
        }
        return "updateJobProfile";
    }
}
