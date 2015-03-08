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
    private TreeNode availableQuestionsTree;
    private List questionCategories;
    private List selectedQuestions;
    private TreeNode answersTree;
    private Employee currentUser;
   
    public UpdateEdrCDIBean() {
    }

    @PostConstruct
    public void init() {

        refreshEdrList();
    }

    public String createEdrStep1() {
        
        this.edrObject = new Edr();
        this.employeeList = employeeFacade.getDepartmentEmployees(currentUser.getDepartmentIddepartment());

        return "createEdr1";
    }
    
    public String createEdrStep2() {
        
        this.availableQuestionsTree = questionFacade.getQuestionTree();
        this.selectedQuestions = new ArrayList();
        
        
        return "createEdr2";
    }
    
    public String createQuestion() {
        
        this.questionCategories = questionCategoryFacade.getCategories();
        this.questionObject = new Question();
        return "createQuestion";
    }
    
    public String createQuestionCategory() {
        
        this.questionCategories = questionCategoryFacade.getCategories();
        this.questionCategoryObject = new QuestionCategory();
        return "createQuestionCategory";
    }

    public String saveEdr() throws InterruptedException {
        
        try {
            
        this.edrObject.setLastChanged(new java.sql.Date(System.currentTimeMillis()));

        if (edrObject.getIdedr() == null) {
            edrFacade.create(edrObject);
            this.competenceGoalFacade.updateCompetenceGoals(competenceGoalTree, edrObject);
            
            questionAnswer1.setEdrIdedr(edrObject);
            questionAnswer1.setQuestionCategory(1);
            questionAnswer1.setQuestion("Have goals and other agreements from the latest EDR been reached and carried through?");
               
            questionAnswer2.setEdrIdedr(edrObject);
            questionAnswer2.setQuestionCategory(2);
            questionAnswer2.setQuestion("Are both parties content with the period's effort and results?");

            questionAnswer3.setEdrIdedr(edrObject);
            questionAnswer3.setQuestionCategory(3);
            questionAnswer3.setQuestion("Was the feedback that was given during the latest EDR usable for both parties?");

            questionAnswer4.setEdrIdedr(edrObject);
            questionAnswer4.setQuestionCategory(4);
            questionAnswer4.setQuestion("Any remarks?");

            questionAnswer5.setEdrIdedr(edrObject);
            questionAnswer5.setQuestionCategory(5);
            questionAnswer5.setQuestion("Does the job description match your expectations? Which tasks do you like most in your job?");

            questionAnswer6.setEdrIdedr(edrObject);
            questionAnswer6.setQuestionCategory(6);
            questionAnswer6.setQuestion("How do you find that the tasks have been carried through? Have you had any influence on the planning/organisation of your work? Deadlines, rate of work etc.?");

            questionAnswer7.setEdrIdedr(edrObject);
            questionAnswer7.setQuestionCategory(7);
            questionAnswer7.setQuestion("Do you get sufficient professional challenges to make optional use of your string sides? Do your tasks correspond to your competences?");

            questionAnswer8.setEdrIdedr(edrObject);
            questionAnswer8.setQuestionCategory(8);
            questionAnswer8.setQuestion("Have you had the possibility of using your competences in the following fields? Solving tasks - Professional fields - Do your tasks correspond to your competences? Do you have any suggestions for changes in your tasks?");

            questionAnswer9.setEdrIdedr(edrObject);
            questionAnswer9.setQuestionCategory(9);
            questionAnswer9.setQuestion("How is the collaboration with your immediate manager in these fields? Information / communication? Feedback? Support / supervision? Do you find that there is a professional and personal comfort betwenn you and your manager?");

            questionAnswer10.setEdrIdedr(edrObject);
            questionAnswer10.setQuestionCategory(10);
            questionAnswer10.setQuestion("How is the collaboration between you and the employee in the department? Open/closed/uncomfortable? How do you consider the tone and the working climate? In the department? In Tecnalia?");

            questionAnswer11.setEdrIdedr(edrObject);
            questionAnswer11.setQuestionCategory(11);
            questionAnswer11.setQuestion("Have you got any special needs for flexibility and comfort-creating initiatives in the planning of your job? Are there things in your private situation that should be considered in relation to your job?");

            questionAnswer12.setEdrIdedr(edrObject);
            questionAnswer12.setQuestionCategory(12);
            questionAnswer12.setQuestion("How do you in general evaluate the consideration for your private affairs? Do you have any suggestions for changing your working conditions?");

            questionAnswer13.setEdrIdedr(edrObject);
            questionAnswer13.setQuestionCategory(13);
            questionAnswer13.setQuestion("General discussion about your view on the department's tasks and the execution of these? Prioritisation of tasks? Allocation of resources?");

            questionAnswer14.setEdrIdedr(edrObject);
            questionAnswer14.setQuestionCategory(14);
            questionAnswer14.setQuestion("Have you got any suggestions for changes in the department's goals and methods for the achievement of goals?");

            questionAnswer15.setEdrIdedr(edrObject);
            questionAnswer15.setQuestionCategory(15);
            questionAnswer15.setQuestion("Discussion about and setting up your tasks/goals and your role in the solution of tasks.");

            questionAnswer16.setEdrIdedr(edrObject);
            questionAnswer16.setQuestionCategory(16);
            questionAnswer16.setQuestion("Other matters");

            questionAnswer17.setEdrIdedr(edrObject);
            questionAnswer17.setQuestionCategory(17);
            questionAnswer17.setQuestion("General discussion about your need for competences, which are necessary for maintaining/improving your professional level?");

            questionAnswer18.setEdrIdedr(edrObject);
            questionAnswer18.setQuestionCategory(18);
            questionAnswer18.setQuestion("Do you find that you have the necessary competences for solving the agreed tasks in the period towards the next EDR?");

            questionAnswer19.setEdrIdedr(edrObject);
            questionAnswer19.setQuestionCategory(19);
            questionAnswer19.setQuestion("Do you have special whishes/needs for: Other tasks, On the job training, Peertopeer training, Job rotation, Further education/training, Other things");

            questionAnswer20.setEdrIdedr(edrObject);
            questionAnswer20.setQuestionCategory(20);
            questionAnswer20.setQuestion("Which plans do you have for your future working life? Career development? Change of career?");

            questionAnswer21.setEdrIdedr(edrObject);
            questionAnswer21.setQuestionCategory(21);
            questionAnswer21.setQuestion("Are you satisfied with the procedure of this EDR?");

            questionAnswer22.setEdrIdedr(edrObject);
            questionAnswer22.setQuestionCategory(22);
            questionAnswer22.setQuestion("Are there things that should be different? Content, the length of the EDR, the atmosphere, Did you get an influence on the agreements? did you say what you intended to say?");

            questionAnswer23.setEdrIdedr(edrObject);
            questionAnswer23.setQuestionCategory(23);
            questionAnswer23.setQuestion("Did you prepare well enough for this EDR?");

            questionAnswer24.setEdrIdedr(edrObject);
            questionAnswer24.setQuestionCategory(24);
            questionAnswer24.setQuestion("Other things to be followed up/remembered for the next EDR?");
                
            //edrNotes.setDate(date);
            //edrNotes.setEdrIdedr(edrObject);
            
            questionAnswerFacade.create(questionAnswer1);
            questionAnswerFacade.create(questionAnswer2);
            questionAnswerFacade.create(questionAnswer3);
            questionAnswerFacade.create(questionAnswer4);
            questionAnswerFacade.create(questionAnswer5);
            questionAnswerFacade.create(questionAnswer6);
            questionAnswerFacade.create(questionAnswer7);
            questionAnswerFacade.create(questionAnswer8);
            questionAnswerFacade.create(questionAnswer9);
            questionAnswerFacade.create(questionAnswer10);
            questionAnswerFacade.create(questionAnswer11);
            questionAnswerFacade.create(questionAnswer12);
            questionAnswerFacade.create(questionAnswer13);
            questionAnswerFacade.create(questionAnswer14);
            questionAnswerFacade.create(questionAnswer15);
            questionAnswerFacade.create(questionAnswer16);
            questionAnswerFacade.create(questionAnswer17);
            questionAnswerFacade.create(questionAnswer18);
            questionAnswerFacade.create(questionAnswer19);
            questionAnswerFacade.create(questionAnswer20);
            questionAnswerFacade.create(questionAnswer21);
            questionAnswerFacade.create(questionAnswer22);
            questionAnswerFacade.create(questionAnswer23);
            questionAnswerFacade.create(questionAnswer24);            
            //edrNotesFacade.create(edrNotes);
            
        } else {

            edrFacade.edit(edrObject);
            this.competenceGoalFacade.updateCompetenceGoals(competenceGoalTree, edrObject);
            
            questionAnswerFacade.edit(questionAnswer1);
            questionAnswerFacade.edit(questionAnswer2);
            questionAnswerFacade.edit(questionAnswer3);
            questionAnswerFacade.edit(questionAnswer4);
            questionAnswerFacade.edit(questionAnswer5);
            questionAnswerFacade.edit(questionAnswer6);
            questionAnswerFacade.edit(questionAnswer7);
            questionAnswerFacade.edit(questionAnswer8);
            questionAnswerFacade.edit(questionAnswer9);
            questionAnswerFacade.edit(questionAnswer10);
            questionAnswerFacade.edit(questionAnswer11);
            questionAnswerFacade.edit(questionAnswer12);
            questionAnswerFacade.edit(questionAnswer13);
            questionAnswerFacade.edit(questionAnswer14);
            questionAnswerFacade.edit(questionAnswer15);
            questionAnswerFacade.edit(questionAnswer16);
            questionAnswerFacade.edit(questionAnswer17);
            questionAnswerFacade.edit(questionAnswer18);
            questionAnswerFacade.edit(questionAnswer19);
            questionAnswerFacade.edit(questionAnswer20);
            questionAnswerFacade.edit(questionAnswer21);
            questionAnswerFacade.edit(questionAnswer22);
            questionAnswerFacade.edit(questionAnswer23);
            questionAnswerFacade.edit(questionAnswer24);
            
            //if (!date.equals(lastDate)){
            //    edrNotes.setDate(date);
            //} else {
            //    edrNotes.setDate(lastDate);
            //}
            
            //edrNotesFacade.edit(edrNotes);
        }
        FacesContext context = FacesContext.getCurrentInstance();
        ResourceBundle text = ResourceBundle.getBundle("messages", context.getViewRoot().getLocale());
        String message = text.getString("succesful_save_message");

        context.addMessage(
                null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, message, message));

        edrList = edrFacade.findAll();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            e.getMessage(), null));
        }

        return "updateEdr";
    }

    public String edit(Edr edr) {
        
        this.edrObject = edr;
        int questionCategory = 0;
        this.qAList = questionAnswerFacade.findAll();
        this.competenceGoalList = competenceGoalFacade.findAll();
        //this.edrNotesList = edrNotesFacade.findAll();

        // Load Question Objects
        
        for (Question entry : qAList) {
            questionCategory = entry.getQuestionCategory();
            if (entry.getEdrIdedr().equals(edr) && questionCategory == 1) {
                this.questionAnswer1 = entry;
                break;
            }
        }
        for (Question entry : qAList) {
            questionCategory = entry.getQuestionCategory();
            if (entry.getEdrIdedr().equals(edr) && questionCategory == 2) {
                this.questionAnswer2 = entry;
                break;
            }
        }
        for (Question entry : qAList) {
            questionCategory = entry.getQuestionCategory();
            if (entry.getEdrIdedr().equals(edr) && questionCategory == 3) {
                this.questionAnswer3 = entry;
                break;
            }
        }
        for (Question entry : qAList) {
            questionCategory = entry.getQuestionCategory();
            if (entry.getEdrIdedr().equals(edr) && questionCategory == 4) {
                this.questionAnswer4 = entry;
                break;
            }
        }
        for (Question entry : qAList) {
            questionCategory = entry.getQuestionCategory();
            if (entry.getEdrIdedr().equals(edr) && questionCategory == 5) {
                this.questionAnswer5 = entry;
                break;
            }
        }
        for (Question entry : qAList) {
            questionCategory = entry.getQuestionCategory();
            if (entry.getEdrIdedr().equals(edr) && questionCategory == 6) {
                this.questionAnswer6 = entry;
                break;
            }
        }
        for (Question entry : qAList) {
            questionCategory = entry.getQuestionCategory();
            if (entry.getEdrIdedr().equals(edr) && questionCategory == 7) {
                this.questionAnswer7 = entry;
                break;
            }
        }
        for (Question entry : qAList) {
            questionCategory = entry.getQuestionCategory();
            if (entry.getEdrIdedr().equals(edr) && questionCategory == 8) {
                this.questionAnswer8 = entry;
                break;
            }
        }
        for (Question entry : qAList) {
            questionCategory = entry.getQuestionCategory();
            if (entry.getEdrIdedr().equals(edr) && questionCategory == 9) {
                this.questionAnswer9 = entry;
                break;
            }
        }
        for (Question entry : qAList) {
            questionCategory = entry.getQuestionCategory();
            if (entry.getEdrIdedr().equals(edr) && questionCategory == 10) {
                this.questionAnswer10 = entry;
                break;
            }
        }
        for (Question entry : qAList) {
            questionCategory = entry.getQuestionCategory();
            if (entry.getEdrIdedr().equals(edr) && questionCategory == 11) {
                this.questionAnswer11 = entry;
                break;
            }
        }
        for (Question entry : qAList) {
            questionCategory = entry.getQuestionCategory();
            if (entry.getEdrIdedr().equals(edr) && questionCategory == 12) {
                this.questionAnswer12 = entry;
                break;
            }
        }
        for (Question entry : qAList) {
            questionCategory = entry.getQuestionCategory();
            if (entry.getEdrIdedr().equals(edr) && questionCategory == 13) {
                this.questionAnswer13 = entry;
                break;
            }
        }
        for (Question entry : qAList) {
            questionCategory = entry.getQuestionCategory();
            if (entry.getEdrIdedr().equals(edr) && questionCategory == 14) {
                this.questionAnswer14 = entry;
                break;
            }
           
        }
        for (Question entry : qAList) {
            questionCategory = entry.getQuestionCategory();
            if (entry.getEdrIdedr().equals(edr) && questionCategory == 15) {
                this.questionAnswer15 = entry;
                break;
            }
        }
        for (Question entry : qAList) {
            questionCategory = entry.getQuestionCategory();
            if (entry.getEdrIdedr().equals(edr) && questionCategory == 16) {
                this.questionAnswer16 = entry;
                break;
            }
        }
        for (Question entry : qAList) {
            questionCategory = entry.getQuestionCategory();
            if (entry.getEdrIdedr().equals(edr) && questionCategory == 17) {
                this.questionAnswer17 = entry;
                break;
            }
        }
        for (Question entry : qAList) {
            questionCategory = entry.getQuestionCategory();
            if (entry.getEdrIdedr().equals(edr) && questionCategory == 18) {
                this.questionAnswer18 = entry;
                break;
            }
        }
        for (Question entry : qAList) {
            questionCategory = entry.getQuestionCategory();
            if (entry.getEdrIdedr().equals(edr) && questionCategory == 19) {
                this.questionAnswer19 = entry;
                break;
            }
        }
        for (Question entry : qAList) {
            questionCategory = entry.getQuestionCategory();
            if (entry.getEdrIdedr().equals(edr) && questionCategory == 20) {
                this.questionAnswer20 = entry;
                break;
            }
        }
        for (Question entry : qAList) {
            questionCategory = entry.getQuestionCategory();
            if (entry.getEdrIdedr().equals(edr) && questionCategory == 21) {
                this.questionAnswer21 = entry;
                break;
            }
        }
        for (Question entry : qAList) {
            questionCategory = entry.getQuestionCategory();
            if (entry.getEdrIdedr().equals(edr) && questionCategory == 22) {
                this.questionAnswer22 = entry;
                break;
            }
        }
        for (Question entry : qAList) {
            questionCategory = entry.getQuestionCategory();
            if (entry.getEdrIdedr().equals(edr) && questionCategory == 23) {
                this.questionAnswer23 = entry;
                break;
            }
        }
        for (Question entry : qAList) {
            questionCategory = entry.getQuestionCategory();
            if (entry.getEdrIdedr().equals(edr) && questionCategory == 24) {
                this.questionAnswer24 = entry;
                break;
            }
        }
        
        for (Question entry : qAList) {
            questionCategory = entry.getQuestionCategory();
            if (entry.getEdrIdedr().equals(edr) && questionCategory == 1) {
                this.questionAnswer1 = entry;
                break;
            }
        }
        for (Question entry : qAList) {
            questionCategory = entry.getQuestionCategory();
            if (entry.getEdrIdedr().equals(edr) && questionCategory == 2) {
                this.questionAnswer2 = entry;
                break;
            }
        }
        for (Question entry : qAList) {
            questionCategory = entry.getQuestionCategory();
            if (entry.getEdrIdedr().equals(edr) && questionCategory == 3) {
                this.questionAnswer3 = entry;
                break;
            }
        }
        for (Question entry : qAList) {
            questionCategory = entry.getQuestionCategory();
            if (entry.getEdrIdedr().equals(edr) && questionCategory == 4) {
                this.questionAnswer4 = entry;
                break;
            }
        }
        for (Question entry : qAList) {
            questionCategory = entry.getQuestionCategory();
            if (entry.getEdrIdedr().equals(edr) && questionCategory == 5) {
                this.questionAnswer5 = entry;
                break;
            }
        }
        for (Question entry : qAList) {
            questionCategory = entry.getQuestionCategory();
            if (entry.getEdrIdedr().equals(edr) && questionCategory == 6) {
                this.questionAnswer6 = entry;
                break;
            }
        }
        
        //load CompetenceGoals Objects
        
        this.competencesTree = competenceFacade.getCompetencesTree();
        this.competenceGoalTree = competenceGoalFacade.getCompetenceGoalsTree(competencesTree, this.edrObject);
        
        // load EdrNotes Objects
        
        //for (EdrNotes note : edrNotesList) {
        //    if (note.getEdrIdedr().equals(edr)) {
        //        this.edrNotes = note;
        //        lastDate = edrNotes.getDate();
        //        break;
        //    } 
        //}
        
        return "createEdr";
    }

    /*public void remove(Edr edr) {
        
        try {

            qAList = questionAnswerFacade.findAll();
            competenceGoalList = competenceGoalFacade.findAll();
            edrNotesList = edrNotesFacade.findAll();
            
            for (Question q : qAList) {
                if (q.getEdrIdedr().equals(edr)) {
                    questionAnswerFacade.remove(q);
                }
            }
            for (CompetenceGoal c : competenceGoalList) {
                if (c.getEdrIdedr().equals(edr)) {
                    competenceGoalFacade.remove(c);
                }
            }
            for (EdrNotes e : edrNotesList) {
                if (e.getEdrIdedr().equals(edr)) {
                    edrNotesFacade.remove(e);
                }
            }
            
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            e.getMessage(), null));
        }
        
        edrFacade.remove(edr);
        edrList = edrFacade.findAll();
    }*/
    
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
            html = html.replace ("{{edr_questionnaire_title}}",bundle.getString("questionnaire_statements"));
            String questions_answers = "<ol>";
            
            for (Question qa : qAList)
            {
                questions_answers = questions_answers + "<li><b>" + qa.getQuestion() +"</b><br/><br/>" + qa.getAnswer() + "</li><br/><br/><br/>";
            }
            questions_answers = questions_answers + "</ul>";
            
            html = html.replace("{{edr_questionnaire_content}}",questions_answers);
            
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
