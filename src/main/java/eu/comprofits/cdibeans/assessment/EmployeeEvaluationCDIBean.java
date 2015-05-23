/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.comprofits.cdibeans.assessment;

import eu.comprofits.ComprofitsRRunner.ComprofitsRRunner;
import eu.comprofits.ComprofitsRRunner.ComprofitsRRunnerException;
import eu.comprofits.entities.employee.CurrentCompetenceAssessment;
import eu.comprofits.entities.employee.Employee;
import eu.comprofits.entities.jobprofile.CompetencesRequirement;
import eu.comprofits.entities.jobprofile.Job;
import eu.comprofits.entities.main.Competence;
import eu.comprofits.session.employee.CurrentCompetenceAssessmentFacade;
import eu.comprofits.session.employee.EmployeeFacade;
import eu.comprofits.session.jobprofile.CompetencesRequirementFacade;
import eu.comprofits.session.jobprofile.JobFacade;
import eu.comprofits.session.main.CompetenceFacade;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import org.apache.commons.io.IOUtils;

/**
 *
 * @author ckopanos
 */
@Named(value = "employeeEvaluation")
@RequestScoped
public class EmployeeEvaluationCDIBean {

    @EJB
    private EmployeeFacade emfacade;
    @EJB
    private CompetenceFacade comfacade;
    @EJB
    private CompetencesRequirementFacade crfacade;
    @EJB
    private JobFacade jfacade;
    @EJB
    private CurrentCompetenceAssessmentFacade cafacade;

    private List<Employee> employees;
    private List<Job> jobs;
    private Employee employee;
    private Job job;

    @PostConstruct
    public void init() {
        this.jobs = this.jfacade.getJobsForEvaluation();
        this.employees = this.emfacade.getEmployeesForEvaluation();
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public List<Job> getJobs() {
        return jobs;
    }

    public void setJobs(List<Job> jobs) {
        this.jobs = jobs;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public boolean isAvailable() {
        return !this.jobs.isEmpty() && !this.employees.isEmpty();
    }

    public String evaluate() {
        FacesContext context = FacesContext.getCurrentInstance();
        ResourceBundle bundle = context.getApplication().getResourceBundle(context, "msgs");
        // get competences in proper order. It is essential for weights to be properly assigned
        List<Competence> competences = this.comfacade.getOrderedCompetences();
        String missing_requirement = bundle.getString("missing_requirement");
        String missing_assessment = bundle.getString("missing_assessment");
        List<Integer> competencyPriorityL1 = new ArrayList();
        List<Integer> competencyPriorityL2 = new ArrayList();
        List<Integer> competencyPriorityL3 = new ArrayList();
        List<String> labelsL1 = new ArrayList();
        List<String> labelsL2 = new ArrayList();
        List<String> labelsL3 = new ArrayList();
        List<Integer> candidateL3 = new ArrayList();
        // we have to get weights in proper order
        for (Competence c : competences) {
            // completetely skip if there is no competence Level 1 requirement
            CompetencesRequirement cr = this.crfacade.getRequirementForJobAndCompetence(this.job, c);
            if (cr != null) {
                int level = c.getLevel();
                if (level == 1) {
                    competencyPriorityL1.add(cr.getWeight());
                    labelsL1.add(c.getCompetenceName());
                }
                if (level == 2) {
                    // make sure we have the parent requirement filled in otherwise values will be wrong in R
                    CompetencesRequirement cr_parent = this.crfacade.getRequirementForJobAndCompetence(this.job, c.getParentId());
                    if (cr_parent == null) {
                        context.addMessage(null,
                                new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                        missing_requirement + " " + c.getParentId().getCompetenceName(), null));
                        return "employeeEvaluations";
                    }
                    labelsL2.add(c.getCompetenceName());
                    competencyPriorityL2.add(cr.getWeight());
                }
                if (level == 3) {
                    // make sure we have the parent requirement filled in otherwise values will be wrong in R
                    CompetencesRequirement cr_parent = this.crfacade.getRequirementForJobAndCompetence(this.job, c.getParentId());
                    if (cr_parent == null) {
                        context.addMessage(null,
                                new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                        missing_requirement + " " + c.getParentId().getCompetenceName(), null));
                        return "employeeEvaluations";
                    }
                    labelsL3.add(c.getCompetenceName());
                    competencyPriorityL3.add(cr.getWeight());
                    // now get the employee assessment for this competency
                    CurrentCompetenceAssessment assess = this.cafacade.getAssessmentForEmployeeAndCompetence(this.employee, c);
                    if (assess == null) {
                        context.addMessage(null,
                                new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                        missing_assessment + " " + c.getCompetenceName(), null));
                        return "employeeEvaluations";
                    }
                    candidateL3.add(assess.getAssessment());
                }
            }
        }
        // first check the case that we are missing some level competeneces
        if (competencyPriorityL1.isEmpty() || competencyPriorityL2.isEmpty() || competencyPriorityL3.isEmpty()) {
            // check which one is empty in order to add an appropriate message
            String missing_competence = "";
            if (competencyPriorityL1.isEmpty()) {
                missing_competence += bundle.getString("missing_level1_competence") + " ";
            }
            if (competencyPriorityL2.isEmpty()) {
                missing_competence += bundle.getString("missing_level2_competence") + " ";
            }
            if (competencyPriorityL3.isEmpty()) {
                missing_competence += bundle.getString("missing_level3_competence");
            }
            context.addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            missing_competence, null));
            return "employeeEvaluations";
        }

        // check that the competenece levels values pyramid is consistent
        // meaning that there are the same amount of leaves for each node
        if (competencyPriorityL2.size() % competencyPriorityL1.size() != 0) {
            context.addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            bundle.getString("competences_levels2_wrong"), null));
            return "employeeEvaluations";
        }
        if (competencyPriorityL3.size() % competencyPriorityL2.size() != 0) {
            context.addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            bundle.getString("competences_levels3_wrong"), null));
            return "employeeEvaluations";
        }
        try {
            ComprofitsRRunner test = new ComprofitsRRunner();
            test.setLabelsL1(this.toStringArray(labelsL1));
            test.setLabelsL2(this.toStringArray(labelsL2));
            test.setLabelsL3(this.toStringArray(labelsL3));
            //check required packages in R
            boolean[] packagesCheck = test.packageManager();
            if (packagesCheck[0] != true) {
                context.addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                bundle.getString("missing_r_packages"), null));
                return "employeeEvaluations";
            }

            //Adjust - Normalize scores of Level 1 and 2
            test.Weights(this.toPrimitiveDouble(competencyPriorityL1), this.toPrimitiveDouble(competencyPriorityL2));
            //System.out.println("level 2 actual starts");
            //Level 2 computations
            double[] actualResultsL2 = test.ActualL2(this.toPrimitiveDouble(candidateL3));
            //System.out.println("Level 2 actual:" + Arrays.toString(actualResultsL2));
            double[] requestedResultsL2 = test.RequestedL2(this.toPrimitiveDouble(competencyPriorityL3));
        //System.out.println("Level 2 requested: " + Arrays.toString(requestedResultsL2));

            //Level 1 computations
            double[] actualResultsL1 = test.ActualL1(this.toPrimitiveDouble(candidateL3));
            //System.out.println("Level 1 actual:" + Arrays.toString(actualResultsL1));
            double[] requestedResultsL1 = test.RequestedL1(this.toPrimitiveDouble(competencyPriorityL3));
        //System.out.println("Level 1 requested: " + Arrays.toString(requestedResultsL1));

            // create a tmp dir to hold plotted images
            String FullFileName12, FullFileName22, FullFileName32, FullFileName11, FullFileName21, FullFileName31;
            Path file1, file2, file3, file4, file5, file6;
            try {
                file1 = Files.createTempFile("FullFileName12", ".png");
                FullFileName12 = file1.toString();
                if (System.getProperty("os.name").startsWith("Windows")) {
                    FullFileName12 = FullFileName12.replace('\\', '/');
                }
                file2 = Files.createTempFile("FullFileName22", ".png");
                FullFileName22 = file2.toString();
                if (System.getProperty("os.name").startsWith("Windows")) {
                    FullFileName22 = FullFileName22.replace('\\', '/');
                }
                file3 = Files.createTempFile("FullFileName32", ".png");
                FullFileName32 = file3.toString();
                if (System.getProperty("os.name").startsWith("Windows")) {
                    FullFileName32 = FullFileName32.replace('\\', '/');
                }
                file4 = Files.createTempFile("FullFileName11", ".png");
                FullFileName11 = file4.toString();
                if (System.getProperty("os.name").startsWith("Windows")) {
                    FullFileName11 = FullFileName11.replace('\\', '/');
                }
                file5 = Files.createTempFile("FullFileName21", ".png");
                FullFileName21 = file5.toString();
                if (System.getProperty("os.name").startsWith("Windows")) {
                    FullFileName21 = FullFileName21.replace('\\', '/');
                }
                file6 = Files.createTempFile("FullFileName31", ".png");
                FullFileName31 = file6.toString();
                if (System.getProperty("os.name").startsWith("Windows")) {
                    FullFileName31 = FullFileName31.replace('\\', '/');
                }
            } catch (IOException e) {
                context.addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                bundle.getString("error_creating_pdf") + " " + e.getMessage(), null));
                return "employeeEvaluations";
            }
            //Plot for Level 2 - Use Level 2 computation results from previous methods

            test.plotBarplotL2(actualResultsL2, requestedResultsL2, FullFileName12);
            test.plotRadarplotL2(actualResultsL2, requestedResultsL2, FullFileName22);
            test.plotParallelplotL2(actualResultsL2, requestedResultsL2, FullFileName32);

            //Plot for Level 1 - Use Level 1 computation results from previous methods
            test.plotBarplotL1(actualResultsL1, requestedResultsL1, FullFileName11);
            test.plotRadarplotL1(actualResultsL1, requestedResultsL1, FullFileName21);
            test.plotParallelplotL1(actualResultsL1, requestedResultsL1, FullFileName31);

            // Stop the Rcaller stream first and then the proccess
            test.stopRCaller();
            // time to sent output to pdf
            ExternalContext ec = context.getExternalContext();

            String wkhtmltopdf_ex = ec.getInitParameter("WKHTMLTOPDF_EXE");

            String html = "";
            try {
                InputStream is = ec.getResourceAsStream("/resources/pdftemplates/evaluation.html");
                html = IOUtils.toString(is, "UTF-8");
            } catch (IOException e) {
                context.addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                bundle.getString("error_creating_pdf") + " " + e.getMessage(), null));
                return "employeeEvaluations";
            }

            html = html.replace("{{evaluation_title}}", bundle.getString("evaluation_for") + " " + this.employee.getFullName());
            html = html.replace("{{evaluation_job}}", bundle.getString("job_title") + ": " + this.job.getJobTitle());
            String evaluation_images = "<img src=\"" + FullFileName12 + "\" class=\"img-responsive center-block\">"
                    + "<img src=\"" + FullFileName22 + "\" class=\"img-responsive center-block\">"
                    + "<img src=\"" + FullFileName32 + "\" class=\"img-responsive center-block\">"
                    + "<img src=\"" + FullFileName11 + "\" class=\"img-responsive center-block\">"
                    + "<img src=\"" + FullFileName21 + "\" class=\"img-responsive center-block\">"
                    + "<img src=\"" + FullFileName31 + "\" class=\"img-responsive center-block\">";
            html = html.replace("{{content}}", evaluation_images);
            Runtime rt = Runtime.getRuntime();
            Process p;
            try {
                p = rt.exec(wkhtmltopdf_ex + " - -");
                IOUtils.write(html, p.getOutputStream());
                p.getOutputStream().close();

                String fileName = this.employee.getFullName() + " Evaluation.pdf";
                ec.responseReset();
                ec.setResponseContentType("application/pdf");
                ec.setResponseHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");

                OutputStream output = ec.getResponseOutputStream();
                try {
                    IOUtils.copy(p.getInputStream(), output);
                } finally {
                    p.getInputStream().close();
                    // delete temp images
                    try {
                        Files.delete(file1);
                        Files.delete(file2);
                        Files.delete(file3);
                        Files.delete(file4);
                        Files.delete(file5);
                        Files.delete(file6);
                    } catch (IOException e) {
                        Logger.getLogger(EmployeeEvaluationCDIBean.class.getName()).log(Level.SEVERE, null, e);
                    }
                    context.responseComplete();
                }

            } catch (IOException e) {
                context.addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                bundle.getString("error_creating_pdf") + " " + e.getMessage(), null));
                return "employeeEvaluations";
            }

        } catch (ComprofitsRRunnerException e) {
            context.addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            bundle.getString("error_in_r") + " " + e.getMessage(), null));
            return "employeeEvaluations";
        }
        return "employeeEvaluations";
    }

    public double[] toPrimitiveDouble(List<Integer> IntegerArray) {

        double[] result = new double[IntegerArray.size()];
        for (int i = 0; i < IntegerArray.size(); i++) {
            result[i] = IntegerArray.get(i);
        }
        return result;
    }

    public String[] toStringArray(List<String> StringArray) {

        String[] result = new String[StringArray.size()];
        for (int i = 0; i < StringArray.size(); i++) {
            result[i] = StringArray.get(i);
        }
        return result;
    }

}
