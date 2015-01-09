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
import java.io.Serializable;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Named;
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
}
