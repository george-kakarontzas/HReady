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
import eu.comprofits.session.jobprofile.PlaceEmploymentFacade;
import eu.comprofits.session.main.CompetenceFacade;
import eu.comprofits.session.main.DepartmentFacade;
import eu.comprofits.session.main.OrganisationalPositionFacade;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import org.apache.commons.io.IOUtils;
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
    @EJB
    private DepartmentFacade departmentFacade;
    @EJB
    private PlaceEmploymentFacade placeEmploymentFacade;

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

    public OrganisationalPositionFacade getOrganisationalPositionFacade() {
        return organisationalPositionFacade;
    }

    public void setOrganisationalPositionFacade(OrganisationalPositionFacade organisationalPositionFacade) {
        this.organisationalPositionFacade = organisationalPositionFacade;
    }

    public JobFacade getJobFacade() {
        return jobFacade;
    }

    public void setJobFacade(JobFacade jobFacade) {
        this.jobFacade = jobFacade;
    }

    public JobAdvertisementFacade getJobAdvertisementFacade() {
        return jobAdvertisementFacade;
    }

    public void setJobAdvertisementFacade(JobAdvertisementFacade jobAdvertisementFacade) {
        this.jobAdvertisementFacade = jobAdvertisementFacade;
    }

    public EmployeeFacade getEmployeeFacade() {
        return employeeFacade;
    }

    public void setEmployeeFacade(EmployeeFacade employeeFacade) {
        this.employeeFacade = employeeFacade;
    }

    public CompetenceFacade getCompetenceFacade() {
        return competenceFacade;
    }

    public void setCompetenceFacade(CompetenceFacade competenceFacade) {
        this.competenceFacade = competenceFacade;
    }

    public CompetencesRequirementFacade getCompetencesRequirementFacade() {
        return competencesRequirementFacade;
    }

    public void setCompetencesRequirementFacade(CompetencesRequirementFacade competencesRequirementFacade) {
        this.competencesRequirementFacade = competencesRequirementFacade;
    }

    public DepartmentFacade getDepartmentFacade() {
        return departmentFacade;
    }

    public void setDepartmentFacade(DepartmentFacade departmentFacade) {
        this.departmentFacade = departmentFacade;
    }

    public PlaceEmploymentFacade getPlaceEmploymentFacade() {
        return placeEmploymentFacade;
    }

    public void setPlaceEmploymentFacade(PlaceEmploymentFacade placeEmploymentFacade) {
        this.placeEmploymentFacade = placeEmploymentFacade;
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
        return "createJobProfile";
    }

    public void refreshJobList() {
        jobList = jobFacade.findAll();
    }
    
    public void refreshCompetencesRequirements()
    {
        this.competencesRequirementsTree = this.competencesRequirementFacade.getCompetencesRequirementsTree(this.competenceFacade.getCompetencesTree(), this.jobObject);
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

    public String remove(Job job) {

        try {
            for (CompetencesRequirement cr : competencesRequirementFacade.getRequirementsForJob(job))
            {
                competencesRequirementFacade.remove(cr);
            }
            /*for (CompetencesRequirement cr : competenceRequirements) {
                if (cr.getJobIdjob().getIdjob().equals(e.getIdjob())) {
                    competencesRequirementFacade.remove(cr);
                }
            }*/
            jobFacade.remove(job);
            if (filteredJobList != null) {
                filteredJobList.remove(job);
            }
            refreshJobList();
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            ex.getMessage(), null));
        }
        refreshJobList();

        return "updateJobProfile";
    }
    
    public String saveAsNew()
    {
        try {
                Job newJobObject = new Job();
                newJobObject.setJobTitle(this.jobObject.getJobTitle());
                newJobObject.setJobDescription(this.jobObject.getJobDescription());
                newJobObject.setReportingToIdemployee(this.jobObject.getReportingToIdemployee());
                newJobObject.setOrganisationalPositionIdorganisationalPosition(this.jobObject.getOrganisationalPositionIdorganisationalPosition());
                newJobObject.setStatus(this.jobObject.getStatus());
                jobFacade.create(newJobObject);
                this.competencesRequirementFacade.updateCompetencesRequirements(this.competencesRequirementsTree, newJobObject);
                //jobAdvertisementFacade.create(jobAdvertisementObject);
            
            jobList = jobFacade.findAll();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            e.getMessage(), null));
        }
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
    
    public String export(Job job) throws InterruptedException {
        this.jobObject = job;
        refreshCompetencesRequirements();
        
        FacesContext context = FacesContext.getCurrentInstance();
        ResourceBundle bundle = context.getApplication().getResourceBundle(context, "msgs");
        
        
        ExternalContext ec = context.getExternalContext();

            String wkhtmltopdf_ex = ec.getInitParameter("WKHTMLTOPDF_EXE");

            String html = "";
            try {
                InputStream is = ec.getResourceAsStream("/resources/pdftemplates/job.html");
                html = IOUtils.toString(is, "UTF-8");
            } catch (IOException e) {
                context.addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                bundle.getString("error_creating_pdf") + " " + e.getMessage(), null));
                return "updateJobProfile";
            }
            
            html = html.replace("{{job_title}}", bundle.getString("job_description") + ": ");
            html = html.replace("{{job_information_title}}", bundle.getString("edr_assignments") + ": ");
            html = html.replace("{{job_competencerequirements_title}}", bundle.getString("update_competence_requirement")+":");
                        
            String jobStatusString = "";
            if (jobObject.getStatus())
            {
                jobStatusString = bundle.getString("enable");
            }
            else
            {
                jobStatusString = bundle.getString("disable");
            }
            
            html = html.replace("{{job_information_content}}", "<ul>"+
                                                        "<li><b>"+bundle.getString("job_title")+": </b>"+jobObject.getJobTitle()+"</li>"+
                                                        "<li><b>"+bundle.getString("organizational_position")+": </b>"+jobObject.getOrganisationalPositionIdorganisationalPosition().getOrganisationalPositionName()+"</li>"+
                                                        //"<li><b>"+bundle.getString("business_area")+": </b>"+jobObject.getDepartmentIddepartment().getDepartmentName()+"</li>"+
                                                        //"<li><b>"+bundle.getString("place_of_employment")+": </b>"+jobObject.getPlaceEmploymentIdplaceEmployment().getName()+"</li>"+
                                                        "<li><b>"+bundle.getString("reporting_to")+": </b>"+jobObject.getReportingToIdemployee().getFullName()+"</li>"+
                                                        "<li><b>"+bundle.getString("job_description")+": </b>"+jobObject.getJobDescription()+"</li>"+
                                                        "<li><b>"+bundle.getString("job_profile_status")+": </b>"+jobStatusString+"</li>"+
                                                        "</ul>");
                  
            String competenceRequirements = "<table width=\"100%\" border=\"1\" cellpadding=\"10\"><tbody><tr><th>"+bundle.getString("competences")+
                                                    "</th><th>"+bundle.getString("competence_requirement_weight")+
                                                    "</th><th>"+bundle.getString("importance")+
                                                    "</th></tr>";
            
            List<CompetencesRequirement> crList = new ArrayList();
            this.competencesRequirementFacade.convertTreeToList(this.competencesRequirementsTree, crList);
                
            for (CompetencesRequirement cr : crList)
            {
                String importanceString = "";
                    switch (cr.getImportance())
                    {
                        case 0: importanceString = bundle.getString("cr_importancevalue_0");
                                break;
                        case 1: importanceString = bundle.getString("cr_importancevalue_1");
                                break;
                        case 2: importanceString = bundle.getString("cr_importancevalue_2");
                                break;
                        case 3: importanceString = bundle.getString("cr_importancevalue_3");
                                break;
                        case 4: importanceString = bundle.getString("cr_importancevalue_4");
                                break;
                        case 5: importanceString = bundle.getString("cr_importancevalue_5");
                                break;
                    }
                competenceRequirements = competenceRequirements + "<tr><td>" + cr.getCompetenceIdcompetence().getLeveledLabel() + 
                                                    "</td><td>" + cr.getWeight() +
                                                    "</td><td>" + importanceString +
                                                    "</td></tr>";
            }
            competenceRequirements = competenceRequirements + "</tbody></table>";
            
            html = html.replace("{{job_competencerequirements_content}}", competenceRequirements);
         
            Runtime rt = Runtime.getRuntime();
            Process p;
            try {
                p = rt.exec(wkhtmltopdf_ex + " - -");
                IOUtils.write(html, p.getOutputStream());
                p.getOutputStream().close();

                String fileName = jobObject.getJobTitle() + "-Job.pdf";
                ec.responseReset();
                ec.setResponseContentType("application/pdf");
                ec.setResponseHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");

                OutputStream output = ec.getResponseOutputStream();
                try {
                    IOUtils.copy(p.getInputStream(), output);
                } 
                catch (Exception e)
                {
                    context.addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                bundle.getString("error_creating_pdf") + " " + e.getMessage(), null));
                }
                finally {
                    p.getInputStream().close();
                    // delete temp images
                    
                    context.responseComplete();
                }

            } catch (IOException e) {
                context.addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                bundle.getString("error_creating_pdf") + " " + e.getMessage(), null));
                return "updateJobProfile";
            }
        
            return "updateJobProfile";
    }
}
