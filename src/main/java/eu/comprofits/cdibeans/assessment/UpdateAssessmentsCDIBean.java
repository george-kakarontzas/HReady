/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.comprofits.cdibeans.assessment;

import eu.comprofits.entities.assessment.Assessment;
import eu.comprofits.entities.assessment.EmployeeCompetenceAssessment;
import eu.comprofits.entities.assessment.Statement;
import eu.comprofits.entities.employee.Employee;
import eu.comprofits.entities.main.Competence;
import eu.comprofits.entities.main.Department;
import eu.comprofits.session.assessment.AssessmentFacade;
import eu.comprofits.session.assessment.EmployeeCompetenceAssessmentFacade;
import eu.comprofits.session.assessment.StatementFacade;
import eu.comprofits.session.employee.EmployeeFacade;
import eu.comprofits.session.main.CompetenceFacade;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Named;
import org.apache.commons.io.IOUtils;
import org.primefaces.event.FlowEvent;
import org.primefaces.event.TransferEvent;
import org.primefaces.model.DualListModel;

/**
 *
 * @author george
 */
@Named(value = "updateAssessmentsCDIBean")
@SessionScoped
public class UpdateAssessmentsCDIBean implements Serializable {

    @EJB
    private EmployeeCompetenceAssessmentFacade employeeCompetenceAssessmentFacade;

    @EJB
    private StatementFacade statementFacade;

    @EJB
    private CompetenceFacade competenceFacade;
    @EJB
    private EmployeeFacade employeeFacade;
    @EJB
    private AssessmentFacade assessmentFacade;

    private Assessment assessment;
    private Department department;
    private List<Assessment> assessments;
    private List<Employee> departmentEmployees;
    private Competence selectedCompetence;
    private DualListModel<Competence> competences;
    private DualListModel<Statement> statements;
    private Map<Competence, DualListModel<Statement>> selectedStatementsPerCompetence;
    private boolean statementSelected;
    private double totalScore;
    private double noOfCompetedAssessments;

    public Map<Competence, DualListModel<Statement>> getSelectedStatementsPerCompetence() {
        return selectedStatementsPerCompetence;
    }

    public List<Statement> getStatementsForCompetence(Object c) {
        if (c instanceof Competence) {
            Competence c1 = (Competence) c;
            DualListModel d = selectedStatementsPerCompetence.get(c1);
            List<Statement> result = d.getTarget();
            return result;
        }
        return null;
    }

    /**
     * Creates a new instance of UpdateOrganisationalPositionsCDIBean
     */
    public UpdateAssessmentsCDIBean() {
    }

    @PostConstruct
    public void init() {
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        Employee e = (Employee) externalContext.getSessionMap().get("user");
        if (e == null) {
            Principal principal
                    = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal();
            if (principal != null) {
                e = employeeFacade.getEmployeeByUsername(principal.getName()); // Find User by j_username.
            }
        }
        if (e != null) {
            department = e.getDepartmentIddepartment();
            departmentEmployees = employeeFacade.getDepartmentEmployees(department);
            refreshAssessmentsList();
        }
    }

    private void refreshAssessmentsList() {
        assessments = assessmentFacade.getDepartmentAssessments(department);
    }

    public Department getDepartment() {
        return department;
    }

    public List<Assessment> getAssessments() {
        return assessments;
    }

    public Assessment getAssessment() {
        return assessment;
    }

    public void setAssessment(Assessment assessment) {
        this.assessment = assessment;
    }

    public Competence getSelectedCompetence() {
        return selectedCompetence;
    }

    public void setSelectedCompetence(Competence selectedCompetence) {
        this.selectedCompetence = selectedCompetence;
    }

    public List<Employee> getDepartmentEmployees() {
        return departmentEmployees;
    }

    public DualListModel<Competence> getCompetences() {
        return competences;
    }

    public void setCompetences(DualListModel<Competence> competences) {
        this.competences = competences;
    }

    public DualListModel<Statement> getStatements() {
        return statements;
    }

    public void setStatements(DualListModel<Statement> statements) {
        this.statements = statements;
    }

    public List<Competence> getSelectedCompetences() {
        return competences.getTarget();
    }

    public boolean isSelectedCompetencesNotEmpty() {
        return !competences.getTarget().isEmpty();
    }

    public boolean isStatementSelected() {
        return statementSelected;
    }

    public void setStatementSelected(boolean noStatementSelected) {
        this.statementSelected = noStatementSelected;
    }
    
    

    public String onFlowProcess(FlowEvent event) {
        FacesContext context = FacesContext.getCurrentInstance();
        ResourceBundle bundle = context.getApplication().getResourceBundle(context, "msgs");
        if (event.getOldStep().equals("assessmentTeam")) {
            Employee assessee = assessment.getAssesseeIdemployee();
            Employee immediateManager = assessment.getImmediateManagerIdemployee();
            Employee colleague1 = assessment.getColleague1Idemployee();
            Employee colleague2 = assessment.getColleague2Idemployee();
            Employee colleague3 = assessment.getColleague3Idemployee();
            if (assessee == null || immediateManager == null || colleague1 == null
                    || colleague2 == null || colleague3 == null) {

                FacesMessage msg = new FacesMessage();
                msg.setSeverity(FacesMessage.SEVERITY_ERROR);
                String message = bundle.getString("all_assessment_team_members_must_be_selected");
                msg.setSummary(message);
                context.addMessage(null, msg);
                return event.getOldStep();
            }
            boolean sameExists = false;
            if (assessee.equals(immediateManager)
                    || assessee.equals(colleague1) || assessee.equals(colleague2)
                    || assessee.equals(colleague3)) {
                sameExists = true;
            } else if (immediateManager.equals(colleague1) || immediateManager.equals(colleague2)
                    || immediateManager.equals(colleague3)) {
                sameExists = true;
            } else if (colleague1.equals(colleague2) || colleague1.equals(colleague3)) {
                sameExists = true;
            } else if (colleague2.equals(colleague3)) {
                sameExists = true;
            }
            if (sameExists) {
                FacesMessage msg = new FacesMessage();
                msg.setSeverity(FacesMessage.SEVERITY_ERROR);
                String message = bundle.getString("assessment_team_members_must_be_distinct");
                msg.setSummary(message);
                context.addMessage(null, msg);
                return event.getOldStep();
            }
        } 
        else if (event.getOldStep().equals("competenceSelection") && !this.isSelectedCompetencesNotEmpty()) {
            FacesMessage msg = new FacesMessage();
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            String message = bundle.getString("select_competences_msg");
            msg.setSummary(message);
            //msg.setDetail(builder.toString());
            context.addMessage(null, msg);
            return event.getOldStep();
        } 
        else if (event.getOldStep().equals("statementSelection")) {
            List<Competence> selectedCompetences = this.getSelectedCompetences();
            String missing_competence = "";
            boolean allOK = true;
            for (Competence c : selectedCompetences) {
                if (!selectedStatementsPerCompetence.containsKey(c)) {
                    allOK = false;
                    missing_competence = c.getCompetenceName();
                    break;
                }
                DualListModel<Statement> m
                        = selectedStatementsPerCompetence.get(c);
                if (m.getTarget().isEmpty()) {
                    allOK = false;
                    missing_competence = c.getCompetenceName();
                    break;
                }
            }
            if (!allOK) {
                FacesMessage msg = new FacesMessage();
                msg.setSeverity(FacesMessage.SEVERITY_ERROR);
                String message = bundle.getString("missing_statements");
                msg.setSummary(message + ": " + missing_competence);
                //msg.setDetail(builder.toString());
                FacesContext.getCurrentInstance().addMessage(null, msg);
                return event.getOldStep();
            }
        } 
        return event.getNewStep();
    }

    public void remove(Assessment a) {
        try {

            assessmentFacade.remove(a);
            refreshAssessmentsList();
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            ex.getMessage(), null));
        }
    }

    public String edit(Assessment a) {
        this.assessment = a;
        List<EmployeeCompetenceAssessment> ecas
                = employeeCompetenceAssessmentFacade.findAllForAssessment(a);
        //find already selected competences
        List<Competence> existingCompetences = new ArrayList<>();
        List<Statement> existingStatements = new ArrayList<>();
        for (EmployeeCompetenceAssessment eca : ecas) {
            if (eca.getAssessorIdemployee().equals(a.getAssesseeIdemployee())) {
                if (!existingCompetences.contains(eca.getCompetenceIdcompetence())) {
                    existingCompetences.add(eca.getCompetenceIdcompetence());
                }
                if (!existingStatements.contains(eca.getStatementIdstatement())) {
                    existingStatements.add(eca.getStatementIdstatement());
                }
            }
        }
        List<Competence> allCompetences = competenceFacade.findAll();
        List<Competence> unselectedCompetences = new ArrayList<>();
        List<Competence> selectedCompetences = new ArrayList<>();
        for (Competence c : allCompetences) {
            if (c.getLevel() == 3) {
                if (!existingCompetences.contains(c)) {
                    unselectedCompetences.add(c);
                } else {
                    selectedCompetences.add(c);
                }
            }
        }
        competences = new DualListModel<>(unselectedCompetences, selectedCompetences);
       
        selectedStatementsPerCompetence = new HashMap<>();

        List<Statement> allStatements = statementFacade.findAll();
        for (Statement s : allStatements) {
            Competence targetCompetence = s.getCompetenceId();
            if (selectedCompetences.contains(targetCompetence)) {
                DualListModel<Statement> cmodel
                        = selectedStatementsPerCompetence.get(targetCompetence);
                if (cmodel == null) {
                    cmodel = new DualListModel<>(new ArrayList<Statement>(), new ArrayList<Statement>());
                    selectedStatementsPerCompetence.put(targetCompetence, cmodel);
                }
                DualListModel m = selectedStatementsPerCompetence.get(targetCompetence);
                if (existingStatements.contains(s)) {
                    m.getTarget().add(s);
                } else {
                    m.getSource().add(s);
                }
            }
        }
        statements = new DualListModel<>();
        return "editAssessment";
    }

    public String create() {
        this.assessment = new Assessment();
        this.assessment.setDateCreated(new Date());
        //initialize the level 3 competences array
        List<Competence> allCompetences = competenceFacade.findAll();
        List<Competence> level3Competences = new ArrayList<>();
        List<Competence> selectedCompetences = new ArrayList<>();
        for (Competence c : allCompetences) {
            if (c.getLevel() == 3) {
                level3Competences.add(c);
            }
        }
        competences = new DualListModel<>(level3Competences, selectedCompetences);
        selectedStatementsPerCompetence = new HashMap<>();
        statements = new DualListModel<>();
        return "editAssessment";
    }

    private void createIfNotExistentEmployeeCompetenceAssessmentsForAssessor(Employee e, Assessment a, boolean checkIfExists) {
        //for each competence
        for (Competence c : getSelectedCompetences()) {
            //for each statement for this competence
            List<Statement> sts = selectedStatementsPerCompetence.get(c).getTarget();
            for (Statement st : sts) {
                EmployeeCompetenceAssessment existing = null;
                if (checkIfExists) {
                    existing
                            = employeeCompetenceAssessmentFacade.matchEmployeeCompetenceAssessment(a, e, st);
                }
                if (existing == null) {
                    EmployeeCompetenceAssessment eca
                            = new EmployeeCompetenceAssessment();
                    eca.setAssessmentIdassessment(a);
                    eca.setAssessorIdemployee(e);
                    eca.setCompetenceIdcompetence(c);
                    eca.setStatementIdstatement(st);
                    employeeCompetenceAssessmentFacade.create(eca);
                }
            }
        }
    }

    public String update() {
        try {
            if (assessment.getIdassessment() == null) {
                assessmentFacade.create(assessment);
                //This is a newly cteated assessment so
                //we need to create all the records for each employee and statement
                //in the assessment team
                createIfNotExistentEmployeeCompetenceAssessmentsForAssessor(assessment.getAssesseeIdemployee(), assessment, false);
                createIfNotExistentEmployeeCompetenceAssessmentsForAssessor(assessment.getColleague1Idemployee(), assessment, false);
                createIfNotExistentEmployeeCompetenceAssessmentsForAssessor(assessment.getColleague2Idemployee(), assessment, false);
                createIfNotExistentEmployeeCompetenceAssessmentsForAssessor(assessment.getColleague3Idemployee(), assessment, false);
                createIfNotExistentEmployeeCompetenceAssessmentsForAssessor(assessment.getImmediateManagerIdemployee(), assessment, false);
            } else {
                assessmentFacade.edit(assessment);
                //Delete all orphan employeeCompetenceAssessments. i.e. orphans are those that are associated currently
                //with this assessment but their employee or their statement are not in the current set of employees or
                //statements associated to this assessment after the update
                deleteOrphanEmployeeCompetenceAssessments();
                //then create the currently valid employee competence assessments if they do not already exist
                createIfNotExistentEmployeeCompetenceAssessmentsForAssessor(assessment.getAssesseeIdemployee(), assessment, true);
                createIfNotExistentEmployeeCompetenceAssessmentsForAssessor(assessment.getColleague1Idemployee(), assessment, true);
                createIfNotExistentEmployeeCompetenceAssessmentsForAssessor(assessment.getColleague2Idemployee(), assessment, true);
                createIfNotExistentEmployeeCompetenceAssessmentsForAssessor(assessment.getColleague3Idemployee(), assessment, true);
                createIfNotExistentEmployeeCompetenceAssessmentsForAssessor(assessment.getImmediateManagerIdemployee(), assessment, true);
            }
            refreshAssessmentsList();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            e.getMessage(), null));
        }
        return "updateAssessments";
    }

    public void competenceValueChange(AjaxBehaviorEvent event) {
        if (selectedCompetence != null) {
            statementSelected=false;
            //if this is the first time we insert this competence in the hashmap
            if (!selectedStatementsPerCompetence.containsKey(selectedCompetence)) {
                List<Statement> selectedCompetenceStatements
                        = statementFacade.getCompetenceStatements(selectedCompetence);
                DualListModel<Statement> s = new DualListModel<>();
                s.setSource(selectedCompetenceStatements);
                s.setTarget(new ArrayList<Statement>());
                selectedStatementsPerCompetence.put(selectedCompetence, s);
            }
            statements = selectedStatementsPerCompetence.get(selectedCompetence);
        }
        else {
            statementSelected=true;
        }
    }

    public void statementChanged(TransferEvent event) {
        selectedStatementsPerCompetence.put(selectedCompetence, statements);
    }

    public void competenceChanged(TransferEvent event) {
        if (event.isRemove()) {
            List<Competence> c = competences.getSource();
            for (Competence c1 : c) {
                if (selectedStatementsPerCompetence.containsKey(c1)) {
                    selectedStatementsPerCompetence.remove(c1);
                }
            }
        }
    }

    private void deleteOrphanEmployeeCompetenceAssessments() {
        //Get all existing stored EmployeeCompetenceAssessments for this assessment
        List<EmployeeCompetenceAssessment> allForAssessemnt
                = employeeCompetenceAssessmentFacade.findAllForAssessment(assessment);
        //iterate over the returned assessments to see if there are leftovers from
        //previous updates that should be deleted.
        for (EmployeeCompetenceAssessment e : allForAssessemnt) {
            Employee assessor = e.getAssessorIdemployee();
            if (!assessor.equals(assessment.getAssesseeIdemployee())
                    && !assessor.equals(assessment.getImmediateManagerIdemployee())
                    && !assessor.equals(assessment.getColleague1Idemployee())
                    && !assessor.equals(assessment.getColleague2Idemployee())
                    && !assessor.equals(assessment.getColleague3Idemployee())) {
                employeeCompetenceAssessmentFacade.remove(e);
                continue;
            }
            Statement s = e.getStatementIdstatement();
            boolean found = false;
            for (Competence c : selectedStatementsPerCompetence.keySet()) {
                DualListModel d = selectedStatementsPerCompetence.get(c);
                List<Statement> selStatements = d.getTarget();
                for (Statement s1 : selStatements) {
                    if (s1.equals(s)) {
                        found = true;
                        break;
                    }
                }
                if (found) {
                    break;
                }
            }
            if (!found) {
                employeeCompetenceAssessmentFacade.remove(e);
            }
        }
    }
    
    public String toPdf(Assessment a) {
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext ec = context.getExternalContext();
        ResourceBundle bundle = context.getApplication().getResourceBundle(context, "msgs");
            String wkhtmltopdf_ex = ec.getInitParameter("WKHTMLTOPDF_EXE");

            String html = "";
            try {
                InputStream is = ec.getResourceAsStream("/resources/pdftemplates/assessment.html");
                html = IOUtils.toString(is, "UTF-8");
            } catch (IOException e) {
                context.addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                bundle.getString("error_creating_pdf") + " " + e.getMessage(), null));
                return "updateAssessments";
            }

            html = html.replace("{{assessee}}", bundle.getString("assessee") + " " + a.getAssesseeIdemployee().getFullName());
            html = html.replace("{{date_created_title}}", bundle.getString("date_created"));
            html = html.replace("{{issue_date_title}}", bundle.getString("issue_date"));
            html = html.replace("{{deadline_title}}", bundle.getString("deadline"));
            html = html.replace("{{date_created}}", a.getDateCreated().toString());
            html = html.replace("{{issue_date}}", a.getIssueDate().toString());
            html = html.replace("{{deadline}}", a.getDeadline().toString());
            html = html.replace("{{manager_title}}", bundle.getString("immediate_manager"));
            html = html.replace("{{coleague_title}}", bundle.getString("colleague"));
            html = html.replace("{{manager}}", a.getImmediateManagerIdemployee().getFullName());
            html = html.replace("{{coleague_1}}", a.getColleague1Idemployee().getFullName());
            html = html.replace("{{coleague_2}}", a.getColleague2Idemployee().getFullName());
            html = html.replace("{{coleague_3}}", a.getColleague3Idemployee().getFullName());
            html = html.replace("{{questionaire_title}}", bundle.getString("questionnaire_statements"));
            html = html.replace("{{competence_name}}", bundle.getString("competence_name"));
            html = html.replace("{{statement}}", bundle.getString("statement"));
            String questions = "";
            
            for (EmployeeCompetenceAssessment eca: employeeCompetenceAssessmentFacade.findAllForAssessmentAndEmployee(a, a.getAssesseeIdemployee())) {
                questions+="<tr><td>"+eca.getCompetenceIdcompetence().getCompetenceName()+"</td>";
                questions+="<td>"+eca.getStatementIdstatement().getStatementText()+"</td></tr>";
            }
            html = html.replace("{{questionaire_contents}}", questions);
            html = html.replace("{{selected_assessment_status}}",bundle.getString("selected_assessment_status"));
            html = html.replace("{{running_total_average}}",bundle.getString("running_total_average"));
            html = html.replace("{{selected_assessment_status_content}}",this.getAssessmentStatus(a));
            html = html.replace("{{running_total_average_content}}",this.getTotalAverage());
            
            //html = html.replace("{{evaluation_job}}", bundle.getString("job_title") + ": " + this.job.getJobTitle());
            Runtime rt = Runtime.getRuntime();
            Process p;
            try {
                p = rt.exec(wkhtmltopdf_ex + " - -");
                IOUtils.write(html, p.getOutputStream());
                p.getOutputStream().close();

                String fileName = a.getAssesseeIdemployee().getFullName() + " Assessment.pdf";
                ec.responseReset();
                ec.setResponseContentType("application/pdf");
                ec.setResponseHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");

                OutputStream output = ec.getResponseOutputStream();
                try {
                    IOUtils.copy(p.getInputStream(), output);
                } finally {
                    p.getInputStream().close();
                    context.responseComplete();
                }

            } catch (IOException e) {
                context.addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                bundle.getString("error_creating_pdf") + " " + e.getMessage(), null));
            }
            return "updateAssessments";
    }
    
    private double getAverage(List<EmployeeCompetenceAssessment> ecas) {
        double total = 0.0;
        double undefined = 0;
        int count = 0;
        for (EmployeeCompetenceAssessment eca : ecas) {
            Integer mark = eca.getAssessment();
            if (mark != null) {
                if (mark >= 1 && mark <= 5) {
                    total += mark;
                    count++;
                } else {
                    undefined++;
                }
            } else {
                undefined++;
            }
        }
        if (undefined > 0) {
            return 0.0;
        } else {
            return total / count;
        }
    }
    
    private String getStatus(List<EmployeeCompetenceAssessment> eEcas) {
        FacesContext context = FacesContext.getCurrentInstance();
        ResourceBundle bundle = context.getApplication().getResourceBundle(context, "msgs");
        //fill map with competences
        Map<Competence, List<EmployeeCompetenceAssessment>> map
                = new HashMap<>();
        if (eEcas == null) {
            return " ";
        }
        for (EmployeeCompetenceAssessment eca : eEcas) {
            Competence c = eca.getCompetenceIdcompetence();
            List<EmployeeCompetenceAssessment> ecas;
            if (map.containsKey(c)) {
                ecas = map.get(c);

            } else {
                ecas = new ArrayList<>();
            }
            ecas.add(eca);
            map.put(c, ecas);
        }
        Set<Competence> competences = map.keySet();
        StringBuilder sbuf = new StringBuilder();
        int count = 0;
        double total = 0.0;
        boolean notCompletedYet = false;
        for (Competence c : competences) {
            List<EmployeeCompetenceAssessment> ecas = map.get(c);
            double average = this.getAverage(ecas);
            total += average;
            count++;
            String competenceText = bundle.getString("competence");
            String averageText = bundle.getString("average");
            sbuf.append(competenceText);
            sbuf.append(" ");
            sbuf.append(c.getCompetenceName());
            sbuf.append(" ");
            sbuf.append(averageText);
            sbuf.append(": ");
            if (average == 0.0) {
                String notCompletedYetText = bundle.getString("not_completed_yet");
                sbuf.append(notCompletedYetText);
                notCompletedYet = true;
            } else {
                sbuf.append(average);
            }
            sbuf.append("<br />");
        }
        String assessmentStatusText = bundle.getString("assessment_status");
        sbuf.append(assessmentStatusText);
        if (notCompletedYet) {
            String notCompletedYetText = bundle.getString("not_completed_yet");
            sbuf.append(notCompletedYetText);
        } else {
            String completedText = bundle.getString("completed");
            sbuf.append(completedText);
            sbuf.append("<br />");
            String averageText = bundle.getString("average");
            sbuf.append(averageText);
            sbuf.append(": ");
            double avg = total / count;
            sbuf.append(avg);
            sbuf.append("<br />");
            totalScore += avg;
            noOfCompetedAssessments++;
        }
        return sbuf.toString();
    }
    
 
    public String getAssessmentStatus(Assessment selectedAssessment) {
        FacesContext context = FacesContext.getCurrentInstance();
        ResourceBundle bundle = context.getApplication().getResourceBundle(context, "msgs");
        //set the runnint total to 0.0
        totalScore = 0.0;
        //set the number of competed assessments to 0
        noOfCompetedAssessments = 0;
        //get this employee's status first
        String s1 = "<div class=\"lead\">";
            Employee manager = selectedAssessment.getImmediateManagerIdemployee();
              List<EmployeeCompetenceAssessment> manager_assessments
                        = employeeCompetenceAssessmentFacade.findAllForAssessmentAndEmployee(
                                selectedAssessment, manager);
                s1 += "<strong>"+ manager.getFullName()+"</strong><br/>";
                s1 += this.getStatus(manager_assessments);
                //get the status also for the employee under assessment
                List<EmployeeCompetenceAssessment> assessee_assessments
                        = employeeCompetenceAssessmentFacade.findAllForAssessmentAndEmployee(
                                selectedAssessment, selectedAssessment.getAssesseeIdemployee());
                s1 += "<hr>";
                s1 += "<strong>" + selectedAssessment.getAssesseeIdemployee().getFullName() + "</strong><br/>";
                s1 += this.getStatus(assessee_assessments);
                //get the status for 1st colleague
                List<EmployeeCompetenceAssessment> col1_assessments
                        = employeeCompetenceAssessmentFacade.findAllForAssessmentAndEmployee(
                                selectedAssessment, selectedAssessment.getColleague1Idemployee());
                s1 += "<hr>";
                s1 += "<strong>" + selectedAssessment.getColleague1Idemployee().getFullName() +"</strong><br/>";
                s1 += this.getStatus(col1_assessments);
                //get the status for 2nd colleague
                List<EmployeeCompetenceAssessment> col2_assessments
                        = employeeCompetenceAssessmentFacade.findAllForAssessmentAndEmployee(
                                selectedAssessment, selectedAssessment.getColleague2Idemployee());
                s1 += "<hr>";
                s1 += "<strong>" + selectedAssessment.getColleague2Idemployee().getFullName()+"</strong><br/>";
                s1 += this.getStatus(col2_assessments);
                //get the status for 1st colleague
                List<EmployeeCompetenceAssessment> col3_assessments
                        = employeeCompetenceAssessmentFacade.findAllForAssessmentAndEmployee(
                                selectedAssessment, selectedAssessment.getColleague3Idemployee());
                s1 += "<hr>";
                s1 += "<strong>" + selectedAssessment.getColleague3Idemployee().getFullName()+"</strong><br/>";
                s1 += this.getStatus(col3_assessments);
        return s1+"</div>";
    }
 
     public String getTotalAverage() {

        if (noOfCompetedAssessments == 0) {
            FacesContext context = FacesContext.getCurrentInstance();
            ResourceBundle bundle = context.getApplication().getResourceBundle(context, "msgs");
            String message = bundle.getString("no_competed_assessments_yet");
            return message;
        }
        return "" + (totalScore / noOfCompetedAssessments);
    }
    
}
