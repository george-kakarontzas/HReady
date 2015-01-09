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
import eu.comprofits.entities.main.OrganisationalPosition;
import eu.comprofits.session.employee.EmployeeFacade;
import eu.comprofits.session.jobapplicant.JobAdvertisementFacade;
import eu.comprofits.session.jobprofile.CompetencesRequirementFacade;
import eu.comprofits.session.jobprofile.JobFacade;
import eu.comprofits.session.main.CompetenceFacade;
import eu.comprofits.session.main.OrganisationalPositionFacade;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

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
    private Job selectedJob;
    private List<OrganisationalPosition> positions;
    private OrganisationalPosition position;

    private List<CompetencesRequirement> competenceRequirements;
    private CompetencesRequirement c1;
    private CompetencesRequirement c2;
    private CompetencesRequirement c3;
    private CompetencesRequirement c4;
    private CompetencesRequirement c5;
    private CompetencesRequirement c6;
    private CompetencesRequirement c7;
    private CompetencesRequirement c8;
    private CompetencesRequirement c9;
    private CompetencesRequirement c10;
    private CompetencesRequirement c11;
    private CompetencesRequirement c12;

    private Employee employeeObject;
    private List<Employee> employeeList;
    private List<Employee> employeeReportingToList;

    public UpdateJobCDIBean() {
    }

    @PostConstruct
    public void init() {
        jobObject = new Job();
        employeeObject = new Employee();
        employeeReportingToList = employeeFacade.findAll();
        employeeReportingToList.clear();
        jobList = jobFacade.findAll();
        employeeList = employeeFacade.findAll();
        positions = organisationalPositionFacade.findAll();
        competenceRequirements = competencesRequirementFacade.findAll();
        
        for (Employee e :employeeList) {
            if (e.getRole().equals("depthead")) {
                employeeReportingToList.add(e);
            }
        }
        
        c1 = new CompetencesRequirement();
        c2 = new CompetencesRequirement();
        c3 = new CompetencesRequirement();
        c4 = new CompetencesRequirement();
        c5 = new CompetencesRequirement();
        c6 = new CompetencesRequirement();
        c7 = new CompetencesRequirement();
        c8 = new CompetencesRequirement();
        c9 = new CompetencesRequirement();
        c10 = new CompetencesRequirement();
        c11 = new CompetencesRequirement();
        c12 = new CompetencesRequirement();
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

    public Job getSelectedJob() {
        return selectedJob;
    }

    public void setSelectedJob(Job selectedJob) {
        this.selectedJob = selectedJob;
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
    
    
    public CompetencesRequirement getC1() {
        return c1;
    }

    public void setC1(CompetencesRequirement c1) {
        this.c1 = c1;
    }

    public CompetencesRequirement getC2() {
        return c2;
    }

    public void setC2(CompetencesRequirement c2) {
        this.c2 = c2;
    }

    public CompetencesRequirement getC3() {
        return c3;
    }

    public void setC3(CompetencesRequirement c3) {
        this.c3 = c3;
    }

    public CompetencesRequirement getC4() {
        return c4;
    }

    public void setC4(CompetencesRequirement c4) {
        this.c4 = c4;
    }

    public CompetencesRequirement getC5() {
        return c5;
    }

    public void setC5(CompetencesRequirement c5) {
        this.c5 = c5;
    }

    public CompetencesRequirement getC6() {
        return c6;
    }

    public void setC6(CompetencesRequirement c6) {
        this.c6 = c6;
    }

    public CompetencesRequirement getC7() {
        return c7;
    }

    public void setC7(CompetencesRequirement c7) {
        this.c7 = c7;
    }

    public CompetencesRequirement getC8() {
        return c8;
    }

    public void setC8(CompetencesRequirement c8) {
        this.c8 = c8;
    }

    public CompetencesRequirement getC9() {
        return c9;
    }

    public void setC9(CompetencesRequirement c9) {
        this.c9 = c9;
    }

    public CompetencesRequirement getC10() {
        return c10;
    }

    public void setC10(CompetencesRequirement c10) {
        this.c10 = c10;
    }

    public CompetencesRequirement getC11() {
        return c11;
    }

    public void setC11(CompetencesRequirement c11) {
        this.c11 = c11;
    }

    public CompetencesRequirement getC12() {
        return c12;
    }

    public void setC12(CompetencesRequirement c12) {
        this.c12 = c12;
    }
    
    public String edit(Job job) {
        this.jobObject = job;

        for (CompetencesRequirement cr : competenceRequirements) {
            if (cr.getJobIdjob().getIdjob().equals(jobObject.getIdjob()) && cr.getCompetenceIdcompetence().getCompetenceName().equalsIgnoreCase("Managerial competences")) {
                this.c1 = cr;
                break;
            } else {
                this.c1 = new CompetencesRequirement();
            }
        }

        for (CompetencesRequirement cr : competenceRequirements) {
            if (cr.getJobIdjob().getIdjob().equals(jobObject.getIdjob()) && cr.getCompetenceIdcompetence().getCompetenceName().equalsIgnoreCase("Business orientation")) {
                this.c2 = cr;
                break;
            } else {
                this.c2 = new CompetencesRequirement();
            }
        }

        for (CompetencesRequirement cr : competenceRequirements) {
            if (cr.getJobIdjob().getIdjob().equals(jobObject.getIdjob()) && cr.getCompetenceIdcompetence().getCompetenceName().equalsIgnoreCase("Job related skills")) {
                this.c3 = cr;
                break;
            } else {
                this.c3 = new CompetencesRequirement();
            }
        }

        for (CompetencesRequirement cr : competenceRequirements) {
            if (cr.getJobIdjob().getIdjob().equals(jobObject.getIdjob()) && cr.getCompetenceIdcompetence().getCompetenceName().equalsIgnoreCase("Oral and written communication / languages")) {
                this.c4 = cr;
                break;
            } else {
                this.c4 = new CompetencesRequirement();
            }
        }

        for (CompetencesRequirement cr : competenceRequirements) {
            if (cr.getJobIdjob().getIdjob().equals(jobObject.getIdjob()) && cr.getCompetenceIdcompetence().getCompetenceName().equalsIgnoreCase("Creativity and holistic thinking")) {
                this.c5 = cr;
                break;
            } else {
                this.c5 = new CompetencesRequirement();
            }
        }

        for (CompetencesRequirement cr : competenceRequirements) {
            if (cr.getJobIdjob().getIdjob().equals(jobObject.getIdjob()) && cr.getCompetenceIdcompetence().getCompetenceName().equalsIgnoreCase("Entrepreneurship")) {
                this.c6 = cr;
                break;
            } else {
                this.c6 = new CompetencesRequirement();
            }
        }

        for (CompetencesRequirement cr : competenceRequirements) {
            if (cr.getJobIdjob().getIdjob().equals(jobObject.getIdjob()) && cr.getCompetenceIdcompetence().getCompetenceName().equalsIgnoreCase("Proactivity")) {
                this.c7 = cr;
                break;
            } else {
                this.c7 = new CompetencesRequirement();
            }
        }

        for (CompetencesRequirement cr : competenceRequirements) {
            if (cr.getJobIdjob().getIdjob().equals(jobObject.getIdjob()) && cr.getCompetenceIdcompetence().getCompetenceName().equalsIgnoreCase("Readiness for changes")) {
                this.c8 = cr;
                break;
            } else {
                this.c8 = new CompetencesRequirement();
            }
        }

        for (CompetencesRequirement cr : competenceRequirements) {
            if (cr.getJobIdjob().getIdjob().equals(jobObject.getIdjob()) && cr.getCompetenceIdcompetence().getCompetenceName().equalsIgnoreCase("Teamwork")) {
                this.c9 = cr;
                break;
            } else {
                this.c9 = new CompetencesRequirement();
            }
        }

        for (CompetencesRequirement cr : competenceRequirements) {
            if (cr.getJobIdjob().getIdjob().equals(jobObject.getIdjob()) && cr.getCompetenceIdcompetence().getCompetenceName().equalsIgnoreCase("Professionalism")) {
                this.c10 = cr;
                break;
            } else {
                this.c10 = new CompetencesRequirement();
            }
        }

        for (CompetencesRequirement cr : competenceRequirements) {
            if (cr.getJobIdjob().getIdjob().equals(jobObject.getIdjob()) && cr.getCompetenceIdcompetence().getCompetenceName().equalsIgnoreCase("Interpersonal skills")) {
                this.c11 = cr;
                break;
            } else {
                this.c11 = new CompetencesRequirement();
            }
        }

        for (CompetencesRequirement cr : competenceRequirements) {
            if (cr.getJobIdjob().getIdjob().equals(jobObject.getIdjob()) && cr.getCompetenceIdcompetence().getCompetenceName().equalsIgnoreCase("Motivation for learning")) {
                this.c12 = cr;
                break;
            } else {
                this.c12 = new CompetencesRequirement();
            }
        }

        employeeList = employeeFacade.findAll();
        positions = organisationalPositionFacade.findAll();
        competenceRequirements = competencesRequirementFacade.findAll();

        return "editJobProfile";
    }

    public String create() {
        this.jobObject = new Job();

        this.c1 = new CompetencesRequirement();
        this.c2 = new CompetencesRequirement();
        this.c3 = new CompetencesRequirement();
        this.c4 = new CompetencesRequirement();
        this.c5 = new CompetencesRequirement();
        this.c6 = new CompetencesRequirement();
        this.c7 = new CompetencesRequirement();
        this.c8 = new CompetencesRequirement();
        this.c9 = new CompetencesRequirement();
        this.c10 = new CompetencesRequirement();
        this.c11 = new CompetencesRequirement();
        this.c12 = new CompetencesRequirement();

        employeeList = employeeFacade.findAll();
        positions = organisationalPositionFacade.findAll();
        jobList = jobFacade.findAll();
        competenceRequirements = competencesRequirementFacade.findAll();

        return "createJobProfile";
    }

    public String saveAsNew() {

        c1.setJobIdjob(jobObject);
        c1.setCompetenceIdcompetence(competenceFacade.findByName("Managerial competences"));
        c2.setJobIdjob(jobObject);
        c2.setCompetenceIdcompetence(competenceFacade.findByName("Business orientation"));
        c3.setJobIdjob(jobObject);
        c3.setCompetenceIdcompetence(competenceFacade.findByName("Job related skills"));
        c4.setJobIdjob(jobObject);
        c4.setCompetenceIdcompetence(competenceFacade.findByName("Oral and written communication / languages"));
        c5.setJobIdjob(jobObject);
        c5.setCompetenceIdcompetence(competenceFacade.findByName("Creativity and holistic thinking"));
        c6.setJobIdjob(jobObject);
        c6.setCompetenceIdcompetence(competenceFacade.findByName("Entrepreneurship"));
        c7.setJobIdjob(jobObject);
        c7.setCompetenceIdcompetence(competenceFacade.findByName("Proactivity"));
        c8.setJobIdjob(jobObject);
        c8.setCompetenceIdcompetence(competenceFacade.findByName("Readiness for changes"));
        c9.setJobIdjob(jobObject);
        c9.setCompetenceIdcompetence(competenceFacade.findByName("Teamwork"));
        c10.setJobIdjob(jobObject);
        c10.setCompetenceIdcompetence(competenceFacade.findByName("Professionalism"));
        c11.setJobIdjob(jobObject);
        c11.setCompetenceIdcompetence(competenceFacade.findByName("Interpersonal skills"));
        c12.setJobIdjob(jobObject);
        c12.setCompetenceIdcompetence(competenceFacade.findByName("Motivation for learning"));

        jobFacade.create(jobObject);
        // jobAdvertisementFacade.create(jobAdvertisementObject);
        competencesRequirementFacade.create(c1);
        competencesRequirementFacade.create(c2);
        competencesRequirementFacade.create(c3);
        competencesRequirementFacade.create(c4);

        competencesRequirementFacade.create(c5);
        competencesRequirementFacade.create(c6);
        competencesRequirementFacade.create(c7);
        competencesRequirementFacade.create(c8);

        competencesRequirementFacade.create(c9);
        competencesRequirementFacade.create(c10);
        competencesRequirementFacade.create(c11);
        competencesRequirementFacade.create(c12);

        employeeList = employeeFacade.findAll();
        positions = organisationalPositionFacade.findAll();
        competenceRequirements = competencesRequirementFacade.findAll();
        jobList = jobFacade.findAll();

        return "updateJobProfile";
    }

    public String save() {

        c1.setJobIdjob(jobObject);
        c1.setCompetenceIdcompetence(competenceFacade.findByName("Managerial competences"));
        c2.setJobIdjob(jobObject);
        c2.setCompetenceIdcompetence(competenceFacade.findByName("Business orientation"));
        c3.setJobIdjob(jobObject);
        c3.setCompetenceIdcompetence(competenceFacade.findByName("Job related skills"));
        c4.setJobIdjob(jobObject);
        c4.setCompetenceIdcompetence(competenceFacade.findByName("Oral and written communication / languages"));
        c5.setJobIdjob(jobObject);
        c5.setCompetenceIdcompetence(competenceFacade.findByName("Creativity and holistic thinking"));
        c6.setJobIdjob(jobObject);
        c6.setCompetenceIdcompetence(competenceFacade.findByName("Entrepreneurship"));
        c7.setJobIdjob(jobObject);
        c7.setCompetenceIdcompetence(competenceFacade.findByName("Proactivity"));
        c8.setJobIdjob(jobObject);
        c8.setCompetenceIdcompetence(competenceFacade.findByName("Readiness for changes"));
        c9.setJobIdjob(jobObject);
        c9.setCompetenceIdcompetence(competenceFacade.findByName("Teamwork"));
        c10.setJobIdjob(jobObject);
        c10.setCompetenceIdcompetence(competenceFacade.findByName("Professionalism"));
        c11.setJobIdjob(jobObject);
        c11.setCompetenceIdcompetence(competenceFacade.findByName("Interpersonal skills"));
        c12.setJobIdjob(jobObject);
        c12.setCompetenceIdcompetence(competenceFacade.findByName("Motivation for learning"));

        jobFacade.create(jobObject);
        //jobAdvertisementFacade.create(jobAdvertisementObject);
        competencesRequirementFacade.create(c1);
        competencesRequirementFacade.create(c2);
        competencesRequirementFacade.create(c3);
        competencesRequirementFacade.create(c4);

        competencesRequirementFacade.create(c5);
        competencesRequirementFacade.create(c6);
        competencesRequirementFacade.create(c7);
        competencesRequirementFacade.create(c8);

        competencesRequirementFacade.create(c9);
        competencesRequirementFacade.create(c10);
        competencesRequirementFacade.create(c11);
        competencesRequirementFacade.create(c12);

        employeeList = employeeFacade.findAll();
        positions = organisationalPositionFacade.findAll();
        competenceRequirements = competencesRequirementFacade.findAll();
        jobList = jobFacade.findAll();

        return "updateJobProfile";
    }

    private void refreshJobList() {
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

            for (CompetencesRequirement cr : competenceRequirements) {
                if (cr.getJobIdjob().getIdjob().equals(e.getIdjob())) {
                    competencesRequirementFacade.remove(cr);
                }
            }
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
        competenceRequirements = competencesRequirementFacade.findAll();

        return "updateJobProfile";
    }

    public void update() {
        try {
            if (jobObject.getIdjob() == null) {
                jobFacade.create(jobObject);

                //jobAdvertisementFacade.create(jobAdvertisementObject);
            } else {
                jobFacade.edit(jobObject);
            }
            jobList = jobFacade.findAll();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            e.getMessage(), null));
        }
    }

    public void updateWithCompetenceWeights() {

        c1.setJobIdjob(jobObject);
        c1.setCompetenceIdcompetence(competenceFacade.findByName("Managerial competences"));
        c2.setJobIdjob(jobObject);
        c2.setCompetenceIdcompetence(competenceFacade.findByName("Business orientation"));
        c3.setJobIdjob(jobObject);
        c3.setCompetenceIdcompetence(competenceFacade.findByName("Job related skills"));
        c4.setJobIdjob(jobObject);
        c4.setCompetenceIdcompetence(competenceFacade.findByName("Oral and written communication / languages"));
        c5.setJobIdjob(jobObject);
        c5.setCompetenceIdcompetence(competenceFacade.findByName("Creativity and holistic thinking"));
        c6.setJobIdjob(jobObject);
        c6.setCompetenceIdcompetence(competenceFacade.findByName("Entrepreneurship"));
        c7.setJobIdjob(jobObject);
        c7.setCompetenceIdcompetence(competenceFacade.findByName("Proactivity"));
        c8.setJobIdjob(jobObject);
        c8.setCompetenceIdcompetence(competenceFacade.findByName("Readiness for changes"));
        c9.setJobIdjob(jobObject);
        c9.setCompetenceIdcompetence(competenceFacade.findByName("Teamwork"));
        c10.setJobIdjob(jobObject);
        c10.setCompetenceIdcompetence(competenceFacade.findByName("Professionalism"));
        c11.setJobIdjob(jobObject);
        c11.setCompetenceIdcompetence(competenceFacade.findByName("Interpersonal skills"));
        c12.setJobIdjob(jobObject);
        c12.setCompetenceIdcompetence(competenceFacade.findByName("Motivation for learning"));

        try {
            if (jobObject.getIdjob() == null) {
                jobFacade.create(jobObject);
                competencesRequirementFacade.create(c1);
                competencesRequirementFacade.create(c2);
                competencesRequirementFacade.create(c3);
                competencesRequirementFacade.create(c4);

                competencesRequirementFacade.create(c5);
                competencesRequirementFacade.create(c6);
                competencesRequirementFacade.create(c7);
                competencesRequirementFacade.create(c8);

                competencesRequirementFacade.create(c9);
                competencesRequirementFacade.create(c10);
                competencesRequirementFacade.create(c11);
                competencesRequirementFacade.create(c12);

                // jobAdvertisementFacade.create(jobAdvertisementObject);
            } else {
                jobFacade.edit(jobObject);
                competencesRequirementFacade.edit(c1);
                competencesRequirementFacade.edit(c2);
                competencesRequirementFacade.edit(c3);
                competencesRequirementFacade.edit(c4);

                competencesRequirementFacade.edit(c5);
                competencesRequirementFacade.edit(c6);
                competencesRequirementFacade.edit(c7);
                competencesRequirementFacade.edit(c8);

                competencesRequirementFacade.edit(c9);
                competencesRequirementFacade.edit(c10);
                competencesRequirementFacade.edit(c11);
                competencesRequirementFacade.edit(c12);

            }

            employeeList = employeeFacade.findAll();
            positions = organisationalPositionFacade.findAll();
            competenceRequirements = competencesRequirementFacade.findAll();
            jobList = jobFacade.findAll();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            e.getMessage(), null));
        }
    }
}
