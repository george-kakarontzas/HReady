/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.comprofits.cdibeans.edr;

import eu.comprofits.cdibeans.assessment.EmployeeEvaluationCDIBean;
import eu.comprofits.entities.edr.Answer;
import eu.comprofits.entities.edr.CompetenceGoal;
import eu.comprofits.entities.edr.Edr;
import eu.comprofits.entities.edr.EdrHistory;
import eu.comprofits.entities.edr.EdrNotes;
import eu.comprofits.entities.edr.Question;
import eu.comprofits.entities.edr.QuestionCategory;
import eu.comprofits.entities.employee.Employee;
import eu.comprofits.entities.jobprofile.BusinessArea;
import eu.comprofits.entities.jobprofile.CompetencesRequirement;
import eu.comprofits.entities.jobprofile.Job;
import eu.comprofits.session.edr.CompetenceGoalFacade;
import eu.comprofits.session.edr.EdrFacade;
import eu.comprofits.session.edr.EdrHistoryFacade;
import eu.comprofits.session.edr.EdrNotesFacade;
import eu.comprofits.session.edr.QuestionFacade;
import eu.comprofits.session.edr.AnswerFacade;
import eu.comprofits.session.edr.QuestionCategoryFacade;
import eu.comprofits.session.employee.EmployeeFacade;
import eu.comprofits.session.employee.InCompanyEmploymentFacade;
import eu.comprofits.session.jobprofile.BusinessAreaFacade;
import eu.comprofits.session.jobprofile.CompetencesRequirementFacade;
import eu.comprofits.session.main.CompetenceFacade;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
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
@Named(value = "updateEdrCDIBean")
@SessionScoped
public class UpdateEdrCDIBean implements Serializable {

    private static final long serialVersionUID = 1L;
    @EJB
    private EdrFacade edrFacade;
    @EJB
    private EmployeeFacade employeeFacade;
    @EJB
    private QuestionFacade questionFacade;
    @EJB
    private QuestionCategoryFacade questionCategoryFacade;
    @EJB
    private AnswerFacade answerFacade;
    @EJB
    private CompetencesRequirementFacade competencesrequirementFacade;
    @EJB
    private CompetenceGoalFacade competenceGoalFacade;
    @EJB
    private CompetenceFacade competenceFacade;
    @EJB
    private InCompanyEmploymentFacade iceFacade;

    private Edr edrObject;
    private Question questionObject;
    private QuestionCategory questionCategoryObject;
    private List<Edr> edrList;
    private List<Edr> filteredEdrList;
    private List<Employee> employeeList;
    private java.sql.Date lastDate;
    private TreeNode competencesTree;
    private TreeNode competenceGoalTree;
    private List<QuestionCategory> questionCategories;
    private List<List<Question>> questions;
    private List<List<Answer>> answers;
    private List<Question> selectedQuestions;
    private Employee currentUser;

    public EdrFacade getEdrFacade() {
        return edrFacade;
    }

    public void setEdrFacade(EdrFacade edrFacade) {
        this.edrFacade = edrFacade;
    }

    public EmployeeFacade getEmployeeFacade() {
        return employeeFacade;
    }

    public void setEmployeeFacade(EmployeeFacade employeeFacade) {
        this.employeeFacade = employeeFacade;
    }

    public QuestionFacade getQuestionFacade() {
        return questionFacade;
    }

    public void setQuestionFacade(QuestionFacade questionFacade) {
        this.questionFacade = questionFacade;
    }

    public QuestionCategoryFacade getQuestionCategoryFacade() {
        return questionCategoryFacade;
    }

    public void setQuestionCategoryFacade(QuestionCategoryFacade questionCategoryFacade) {
        this.questionCategoryFacade = questionCategoryFacade;
    }

    public AnswerFacade getAnswerFacade() {
        return answerFacade;
    }

    public void setAnswerFacade(AnswerFacade answerFacade) {
        this.answerFacade = answerFacade;
    }

    public CompetencesRequirementFacade getCompetencesrequirementFacade() {
        return competencesrequirementFacade;
    }

    public void setCompetencesrequirementFacade(CompetencesRequirementFacade competencesrequirementFacade) {
        this.competencesrequirementFacade = competencesrequirementFacade;
    }

    public CompetenceGoalFacade getCompetenceGoalFacade() {
        return competenceGoalFacade;
    }

    public void setCompetenceGoalFacade(CompetenceGoalFacade competenceGoalFacade) {
        this.competenceGoalFacade = competenceGoalFacade;
    }

    public CompetenceFacade getCompetenceFacade() {
        return competenceFacade;
    }

    public void setCompetenceFacade(CompetenceFacade competenceFacade) {
        this.competenceFacade = competenceFacade;
    }

    public InCompanyEmploymentFacade getIceFacade() {
        return iceFacade;
    }

    public void setIceFacade(InCompanyEmploymentFacade iceFacade) {
        this.iceFacade = iceFacade;
    }

    public Edr getEdrObject() {
        return edrObject;
    }

    public void setEdrObject(Edr edrObject) {
        this.edrObject = edrObject;
    }

    public Question getQuestionObject() {
        return questionObject;
    }

    public void setQuestionObject(Question questionObject) {
        this.questionObject = questionObject;
    }

    public QuestionCategory getQuestionCategoryObject() {
        return questionCategoryObject;
    }

    public void setQuestionCategoryObject(QuestionCategory questionCategoryObject) {
        this.questionCategoryObject = questionCategoryObject;
    }

    public List<Edr> getEdrList() {
        return edrList;
    }

    public void setEdrList(List<Edr> edrList) {
        this.edrList = edrList;
    }

    public List<Edr> getFilteredEdrList() {
        return filteredEdrList;
    }

    public void setFilteredEdrList(List<Edr> filteredEdrList) {
        this.filteredEdrList = filteredEdrList;
    }

    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }

    public Date getLastDate() {
        return lastDate;
    }

    public void setLastDate(Date lastDate) {
        this.lastDate = lastDate;
    }

    public TreeNode getCompetencesTree() {
        return competencesTree;
    }

    public void setCompetencesTree(TreeNode competencesTree) {
        this.competencesTree = competencesTree;
    }

    public TreeNode getCompetenceGoalTree() {
        return competenceGoalTree;
    }

    public void setCompetenceGoalTree(TreeNode competenceGoalTree) {
        this.competenceGoalTree = competenceGoalTree;
    }

    public List<QuestionCategory> getQuestionCategories() {
        return questionCategories;
    }

    public void setQuestionCategories(List<QuestionCategory> questionCategories) {
        this.questionCategories = questionCategories;
    }

    public List<List<Question>> getQuestions() {
        return questions;
    }

    public void setQuestions(List<List<Question>> questions) {
        this.questions = questions;
    }

    public List<List<Answer>> getAnswers() {
        return answers;
    }

    public void setAnswers(List<List<Answer>> answers) {
        this.answers = answers;
    }

    public List<Question> getSelectedQuestions() {
        return selectedQuestions;
    }

    public void setSelectedQuestions(List<Question> selectedQuestions) {
        this.selectedQuestions = selectedQuestions;
    }

    public Employee getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(Employee currentUser) {
        this.currentUser = currentUser;
    }
    
    public UpdateEdrCDIBean() {
    }

    @PostConstruct
    public void init() {

        refreshEdrList();
    }

    public String createEdr() {
        
        this.edrObject = new Edr();
        this.employeeList = employeeFacade.getDepartmentEmployees(currentUser.getDepartmentIddepartment());
        return "createEdr";
    }
    
    public String editEdr(Edr edr)
    {
        this.edrObject = edr;
        this.employeeList = employeeFacade.getDepartmentEmployees(currentUser.getDepartmentIddepartment());
        return "editEdr";
    }
    
    public String chooseQuestions() {
        
        refreshQuestionList();  
        return "chooseQuestions";
    }
    
    public String saveEdr() throws InterruptedException {
        
        try {
            
        this.edrObject.setLastChanged(new java.sql.Date(System.currentTimeMillis()));

        if (edrObject.getIdedr() == null) {
            edrFacade.create(edrObject);
            this.competenceGoalFacade.updateCompetenceGoals(competenceGoalTree, edrObject);
            
                for (Question q : this.selectedQuestions)
                {
                    Answer answer = new Answer();
                    answer.setQuestionIdquestion(q);
                    answer.setEdrIdedr(edrObject);
                    answer.setAnswer("");
                    answerFacade.create(answer);
                }
            
            
        } else {

            edrFacade.edit(edrObject);
            this.competenceGoalFacade.updateCompetenceGoals(competenceGoalTree, edrObject);
            for (Answer a : this.answerFacade.getAnswersForEdr(edrObject))
            {
                answerFacade.remove(a);
            }
            
                for (Question q : this.selectedQuestions)
                {
                    if (!questionFacade.isUsedInEdr(q, edrObject))
                    {
                        Answer answer = new Answer();
                        answer.setQuestionIdquestion(q);
                        answer.setEdrIdedr(edrObject);
                        answer.setAnswer("");
                        answerFacade.create(answer);
                    }
                }
            
        }
        FacesContext context = FacesContext.getCurrentInstance();
        ResourceBundle text = ResourceBundle.getBundle("messages", context.getViewRoot().getLocale());
        String message = text.getString("succesful_save_message");

        context.addMessage(
                null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, message, message));

        
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            e.getMessage(), null));
        }

        return "updateEdr";
    }
    
    public String createQuestion() {
        
        this.questionCategories = questionCategoryFacade.getCategories();
        this.questionObject = new Question();
        return "createQuestion";
    }
    
    public String editQuestion(Question question) {
        
        this.questionCategories = questionCategoryFacade.getCategories();
        this.questionObject = question;
        return "createQuestion";
    }
    
    public String saveQuestion() throws InterruptedException {
        
        try {

        if (questionObject.getIdquestion() == null) {
            questionFacade.create(questionObject);
            refreshQuestionList();
        } 
        else
        {
            questionFacade.edit(questionObject); 
            refreshQuestionList();
        }
        FacesContext context = FacesContext.getCurrentInstance();
        ResourceBundle text = ResourceBundle.getBundle("messages", context.getViewRoot().getLocale());
        String message = text.getString("succesful_save_message");

        context.addMessage(
                null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, message, message));

        
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            e.getMessage(), null));
        }

        return "chooseQuestions";
    }
    
    public String createQuestionCategory() {
       
        this.questionCategoryObject = new QuestionCategory();
        return "createQuestionCategory";
    }
    
    public String editQuestionCategory(QuestionCategory category) {

        this.questionCategoryObject = category;
        return "createQuestionCategory";
    }
    
    public String saveQuestionCategory() throws InterruptedException {
        
        try {

        if (questionCategoryObject.getIdquestioncat() == null) {
            questionCategoryFacade.create(questionCategoryObject);
            refreshQuestionList();
        } 
        else
        {
            questionCategoryFacade.edit(questionCategoryObject); 
            refreshQuestionList();
        }
        FacesContext context = FacesContext.getCurrentInstance();
        ResourceBundle text = ResourceBundle.getBundle("messages", context.getViewRoot().getLocale());
        String message = text.getString("succesful_save_message");

        context.addMessage(
                null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, message, message));

        
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            e.getMessage(), null));
        }

        return "chooseQuestions";
    }
    
    public String remove(Edr e) {

        try {
            for (CompetenceGoal cg : competenceGoalFacade.getGoalsForEdr(e))
            {
                competenceGoalFacade.remove(cg);
            }
            /*for (CompetencesRequirement cr : competenceRequirements) {
                if (cr.getJobIdjob().getIdjob().equals(e.getIdjob())) {
                    competencesRequirementFacade.remove(cr);
                }
            }*/
            edrFacade.remove(e);
            if (filteredEdrList != null) {
                filteredEdrList.remove(e);
            }
            refreshEdrList();
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            ex.getMessage(), null));
        }
        refreshEdrList();

        return "updateEdr";
    }
    
    public void refreshEdrList() {
        this.currentUser = employeeFacade.getEmployeeByUsername(FacesContext.getCurrentInstance().getExternalContext().getRemoteUser());
        this.edrList = edrFacade.getEdrsForEmployee(currentUser);
    }
    
    public void refreshQuestionList()
    {
        this.questionCategories = questionCategoryFacade.getCategories();
        this.questions = new ArrayList();
        this.selectedQuestions = new ArrayList();  
        for (QuestionCategory c : questionCategories)
        {
            List<Question> tempList = questionFacade.getQuestionsForCategory(c);
            for (Question q : tempList)
            {
                q.setChecked(false);
            }
            this.questions.add(tempList);
        }
        
        
    }
    
    public String reviewEdr() {
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        Employee e1 = (Employee) externalContext.getSessionMap().get("user");
        List <Edr> filteredList = edrFacade.findAll();
        filteredList.clear();
        
        for (Edr e: edrList) {
            if (e.getReviewedEmployeeIdemployee().getUsername().equals(e1.getUsername())) {
                filteredList.add(e);
            }
        }
        edrList.clear();
        edrList = filteredList;
        return "./updateEdr.xhtml";
    }

    public String export(Edr edr) throws InterruptedException {
        this.edrObject = edr;
        FacesContext context = FacesContext.getCurrentInstance();
        ResourceBundle bundle = context.getApplication().getResourceBundle(context, "msgs");
        
        ExternalContext ec = context.getExternalContext();

            String wkhtmltopdf_ex = ec.getInitParameter("WKHTMLTOPDF_EXE");

            String html = "";
            try {
                InputStream is = ec.getResourceAsStream("/resources/pdftemplates/edr.html");
                html = IOUtils.toString(is, "UTF-8");
            } catch (IOException e) {
                context.addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                bundle.getString("error_creating_pdf") + " " + e.getMessage(), null));
                return "createEdr";
            }
            
            html = html.replace("{{edr_employee_title}}", bundle.getString("edr_employee_title") + ": " +edrObject.getReviewedEmployeeIdemployee().getFullName());
            html = html.replace("{{edr_information_title}}", bundle.getString("edr_information_title")+":");
            html = html.replace("{{edr_verdict_title}}", bundle.getString("edr_verdict_title")+":");
            html = html.replace("{{edr_questionnaire_title}}", bundle.getString("questionnaire_statements")+":");
            
            html = html.replace("{{edr_information_content}}", "<ul>"+
                                                        "<li><b>"+bundle.getString("year")+": </b>"+edrObject.getYear()+"</li>"+
                                                        "<li><b>"+bundle.getString("immediate_manager")+": </b>"+edrObject.getImmediateManagerIdemployee().getFullName()+"</li>"+
                                                        "<li><b>"+bundle.getString("status")+": </b>"+edrObject.getStatus()+"</li>"+
                                                        "</ul>");
            
            html = html.replace("{{edr_verdict_content}}", edrObject.getVerdict());
            
            html = html.replace("{{edr_competencegoals_title}}", bundle.getString("edr_plans") + ": ");
            
            String competenceGoals = "<table width=\"100%\" border=\"1\" cellpadding=\"10\"><tbody><tr><th>"+bundle.getString("competences")+
                                                    "</th><th>"+bundle.getString("edr_goals")+
                                                    "</th><th>"+bundle.getString("comments")+
                                                    "</th></tr>";
            
            for (CompetenceGoal cg : competenceGoalFacade.getGoalsForEdr(edrObject))
            {
                competenceGoals = competenceGoals + "<tr><td>" + cg.getCompetenceIdcompetence().getCompetenceName() + 
                                                    "</td><td>" + cg.getNextYearGoalValue() +
                                                    "</td><td>" + cg.getComments() +
                                                    "</td></tr>";
            }
            competenceGoals = competenceGoals + "</tbody></table>";
            
            html = html.replace("{{edr_competencegoals_content}}",competenceGoals);
            /*html = html.replace ("{{edr_questionnaire_title}}",bundle.getString("questionnaire_statements"));
            String questions_answers = "<ol>";
            
            for (Question qa : qAList)
            {
                questions_answers = questions_answers + "<li><b>" + qa.getQuestion() +"</b><br/><br/>" + qa.getAnswer() + "</li><br/><br/><br/>";
            }
            questions_answers = questions_answers + "</ul>";
            
            html = html.replace("{{edr_questionnaire_content}}",questions_answers);
            */
            Runtime rt = Runtime.getRuntime();
            Process p;
            try {
                p = rt.exec(wkhtmltopdf_ex + " - -");
                IOUtils.write(html, p.getOutputStream());
                p.getOutputStream().close();

                String fileName = edrObject.getReviewedEmployeeIdemployee().getFullName() + "-Edr-"+edrObject.getYear()+".pdf";
                ec.responseReset();
                ec.setResponseContentType("application/pdf");
                ec.setResponseHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");

                OutputStream output = ec.getResponseOutputStream();
                try {
                    IOUtils.copy(p.getInputStream(), output);
                } finally {
                    p.getInputStream().close();
                    // delete temp images
                    
                    context.responseComplete();
                }

            } catch (IOException e) {
                context.addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                bundle.getString("error_creating_pdf") + " " + e.getMessage(), null));
                return "createEdr";
            }
        
            return "createEdr";
    }

    public String help() throws InterruptedException {

        return "createEdr";

    }
}
