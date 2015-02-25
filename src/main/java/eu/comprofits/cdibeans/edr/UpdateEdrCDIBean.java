/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.comprofits.cdibeans.edr;

import eu.comprofits.cdibeans.assessment.EmployeeEvaluationCDIBean;
import eu.comprofits.entities.edr.CompetenceGoal;
import eu.comprofits.entities.edr.Edr;
import eu.comprofits.entities.edr.EdrHistory;
import eu.comprofits.entities.edr.EdrNotes;
import eu.comprofits.entities.edr.QuestionAnswer;
import eu.comprofits.entities.employee.Employee;
import eu.comprofits.entities.jobprofile.BusinessArea;
import eu.comprofits.entities.jobprofile.Job;
import eu.comprofits.session.edr.CompetenceGoalFacade;
import eu.comprofits.session.edr.EdrFacade;
import eu.comprofits.session.edr.EdrHistoryFacade;
import eu.comprofits.session.edr.EdrNotesFacade;
import eu.comprofits.session.edr.QuestionAnswerFacade;
import eu.comprofits.session.employee.EmployeeFacade;
import eu.comprofits.session.employee.InCompanyEmploymentFacade;
import eu.comprofits.session.jobprofile.BusinessAreaFacade;
import eu.comprofits.session.main.CompetenceFacade;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.nio.file.Files;
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
    private BusinessAreaFacade businessAreaFacade;
    @EJB
    private QuestionAnswerFacade questionAnswerFacade;
    @EJB
    private EdrHistoryFacade edrHistoryFacade;
    @EJB
    private EdrNotesFacade edrNotesFacade;
    @EJB
    private CompetenceGoalFacade competenceGoalFacade;
    @EJB
    private CompetenceFacade competenceFacade;
    @EJB
    private InCompanyEmploymentFacade iceFacade;

    private EdrNotes edrNotes;
    private EdrHistory edrHistory;
    private BusinessArea selectedBusinessArea;
    private Edr edrObject;
    private Edr latestEdr;
    private Job selectedJob;
    private List<BusinessArea> businessAreaList;
    private List<Edr> edrList;
    private List<EdrNotes> edrNotesList;
    private List<Edr> filteredEdrList;
    private List<Employee> employeeList;
    private List<CompetenceGoal> competenceGoalList;
    private boolean firstTime;
    private String stringHelper;
    private java.sql.Date lastDate;
    private TreeNode competencesTree;
    private TreeNode competenceGoalTree;

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

    private List<QuestionAnswer> qAList;
    private QuestionAnswer questionAnswer1;
    private QuestionAnswer questionAnswer2;
    private QuestionAnswer questionAnswer3;
    private QuestionAnswer questionAnswer4;
    private QuestionAnswer questionAnswer5;
    private QuestionAnswer questionAnswer6;
    private QuestionAnswer questionAnswer7;
    private QuestionAnswer questionAnswer8;
    private QuestionAnswer questionAnswer9;
    private QuestionAnswer questionAnswer10;
    private QuestionAnswer questionAnswer11;
    private QuestionAnswer questionAnswer12;
    private QuestionAnswer questionAnswer13;
    private QuestionAnswer questionAnswer14;
    private QuestionAnswer questionAnswer15;
    private QuestionAnswer questionAnswer16;
    private QuestionAnswer questionAnswer17;
    private QuestionAnswer questionAnswer18;
    private QuestionAnswer questionAnswer19;
    private QuestionAnswer questionAnswer20;
    private QuestionAnswer questionAnswer21;
    private QuestionAnswer questionAnswer22;
    private QuestionAnswer questionAnswer23;
    private QuestionAnswer questionAnswer24;

    public UpdateEdrCDIBean() {
    }

    @PostConstruct
    public void init() {

        edrList = edrFacade.findAll();
        employeeList = employeeFacade.findAll();
        businessAreaList = businessAreaFacade.findAll();
        qAList = questionAnswerFacade.findAll();
    }

    public EdrNotes getEdrNotes() {
        return edrNotes;
    }

    public void setEdrNotes(EdrNotes edrNotes) {
        this.edrNotes = edrNotes;
    }

    public EdrHistory getEdrHistory() {
        return edrHistory;
    }

    public void setEdrHistory(EdrHistory edrHistory) {
        this.edrHistory = edrHistory;
    }

    public String getStringHelper() {
        return stringHelper;
    }

    public void setStringHelper(String stringHelper) {
        this.stringHelper = stringHelper;
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

    public List<Edr> getFilteredEdrList() {
        return filteredEdrList;
    }

    public void setFilteredEdrList(List<Edr> filteredEdrList) {
        this.filteredEdrList = filteredEdrList;
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

    public QuestionAnswer getQuestionAnswer1() {
        return questionAnswer1;
    }

    public void setQuestionAnswer1(QuestionAnswer questionAnswer1) {
        this.questionAnswer1 = questionAnswer1;
    }

    public QuestionAnswer getQuestionAnswer2() {
        return questionAnswer2;
    }

    public void setQuestionAnswer2(QuestionAnswer questionAnswer2) {
        this.questionAnswer2 = questionAnswer2;
    }

    public QuestionAnswer getQuestionAnswer3() {
        return questionAnswer3;
    }

    public void setQuestionAnswer3(QuestionAnswer questionAnswer3) {
        this.questionAnswer3 = questionAnswer3;
    }

    public QuestionAnswer getQuestionAnswer4() {
        return questionAnswer4;
    }

    public void setQuestionAnswer4(QuestionAnswer questionAnswer4) {
        this.questionAnswer4 = questionAnswer4;
    }

    public QuestionAnswer getQuestionAnswer5() {
        return questionAnswer5;
    }

    public void setQuestionAnswer5(QuestionAnswer questionAnswer5) {
        this.questionAnswer5 = questionAnswer5;
    }

    public QuestionAnswer getQuestionAnswer6() {
        return questionAnswer6;
    }

    public void setQuestionAnswer6(QuestionAnswer questionAnswer6) {
        this.questionAnswer6 = questionAnswer6;
    }

    public QuestionAnswer getQuestionAnswer7() {
        return questionAnswer7;
    }

    public void setQuestionAnswer7(QuestionAnswer questionAnswer7) {
        this.questionAnswer7 = questionAnswer7;
    }

    public QuestionAnswer getQuestionAnswer8() {
        return questionAnswer8;
    }

    public void setQuestionAnswer8(QuestionAnswer questionAnswer8) {
        this.questionAnswer8 = questionAnswer8;
    }

    public QuestionAnswer getQuestionAnswer9() {
        return questionAnswer9;
    }

    public void setQuestionAnswer9(QuestionAnswer questionAnswer9) {
        this.questionAnswer9 = questionAnswer9;
    }

    public QuestionAnswer getQuestionAnswer10() {
        return questionAnswer10;
    }

    public void setQuestionAnswer10(QuestionAnswer questionAnswer10) {
        this.questionAnswer10 = questionAnswer10;
    }

    public QuestionAnswer getQuestionAnswer11() {
        return questionAnswer11;
    }

    public void setQuestionAnswer11(QuestionAnswer questionAnswer11) {
        this.questionAnswer11 = questionAnswer11;
    }

    public QuestionAnswer getQuestionAnswer12() {
        return questionAnswer12;
    }

    public void setQuestionAnswer12(QuestionAnswer questionAnswer12) {
        this.questionAnswer12 = questionAnswer12;
    }

    public QuestionAnswer getQuestionAnswer13() {
        return questionAnswer13;
    }

    public void setQuestionAnswer13(QuestionAnswer questionAnswer13) {
        this.questionAnswer13 = questionAnswer13;
    }

    public QuestionAnswer getQuestionAnswer14() {
        return questionAnswer14;
    }

    public void setQuestionAnswer14(QuestionAnswer questionAnswer14) {
        this.questionAnswer14 = questionAnswer14;
    }

    public QuestionAnswer getQuestionAnswer15() {
        return questionAnswer15;
    }

    public void setQuestionAnswer15(QuestionAnswer questionAnswer15) {
        this.questionAnswer15 = questionAnswer15;
    }

    public QuestionAnswer getQuestionAnswer16() {
        return questionAnswer16;
    }

    public void setQuestionAnswer16(QuestionAnswer questionAnswer16) {
        this.questionAnswer16 = questionAnswer16;
    }

    public QuestionAnswer getQuestionAnswer17() {
        return questionAnswer17;
    }

    public void setQuestionAnswer17(QuestionAnswer questionAnswer17) {
        this.questionAnswer17 = questionAnswer17;
    }

    public QuestionAnswer getQuestionAnswer18() {
        return questionAnswer18;
    }

    public void setQuestionAnswer18(QuestionAnswer questionAnswer18) {
        this.questionAnswer18 = questionAnswer18;
    }

    public QuestionAnswer getQuestionAnswer19() {
        return questionAnswer19;
    }

    public void setQuestionAnswer19(QuestionAnswer questionAnswer19) {
        this.questionAnswer19 = questionAnswer19;
    }

    public QuestionAnswer getQuestionAnswer20() {
        return questionAnswer20;
    }

    public void setQuestionAnswer20(QuestionAnswer questionAnswer20) {
        this.questionAnswer20 = questionAnswer20;
    }

    public QuestionAnswer getQuestionAnswer21() {
        return questionAnswer21;
    }

    public void setQuestionAnswer21(QuestionAnswer questionAnswer21) {
        this.questionAnswer21 = questionAnswer21;
    }

    public QuestionAnswer getQuestionAnswer22() {
        return questionAnswer22;
    }

    public void setQuestionAnswer22(QuestionAnswer questionAnswer22) {
        this.questionAnswer22 = questionAnswer22;
    }

    public QuestionAnswer getQuestionAnswer23() {
        return questionAnswer23;
    }

    public void setQuestionAnswer23(QuestionAnswer questionAnswer23) {
        this.questionAnswer23 = questionAnswer23;
    }

    public QuestionAnswer getQuestionAnswer24() {
        return questionAnswer24;
    }

    public void setQuestionAnswer24(QuestionAnswer questionAnswer24) {
        this.questionAnswer24 = questionAnswer24;
    }

    public EdrHistory getEdrhistory() {
        return edrHistory;
    }

    public void setEdrhistory(EdrHistory edrhistory) {
        this.edrHistory = edrhistory;
    }

    public String create() {
        firstTime = true;
        this.edrObject = new Edr();
        this.edrHistory = new EdrHistory();
        this.edrNotes = new EdrNotes();

        this.questionAnswer1 = new QuestionAnswer();
        this.questionAnswer2 = new QuestionAnswer();
        this.questionAnswer3 = new QuestionAnswer();
        this.questionAnswer4 = new QuestionAnswer();
        this.questionAnswer5 = new QuestionAnswer();
        this.questionAnswer6 = new QuestionAnswer();
        this.questionAnswer7 = new QuestionAnswer();
        this.questionAnswer8 = new QuestionAnswer();
        this.questionAnswer9 = new QuestionAnswer();
        this.questionAnswer10 = new QuestionAnswer();
        this.questionAnswer11 = new QuestionAnswer();
        this.questionAnswer12 = new QuestionAnswer();
        this.questionAnswer13 = new QuestionAnswer();
        this.questionAnswer14 = new QuestionAnswer();
        this.questionAnswer15 = new QuestionAnswer();
        this.questionAnswer16 = new QuestionAnswer();
        this.questionAnswer17 = new QuestionAnswer();
        this.questionAnswer18 = new QuestionAnswer();
        this.questionAnswer19 = new QuestionAnswer();
        this.questionAnswer20 = new QuestionAnswer();
        this.questionAnswer21 = new QuestionAnswer();
        this.questionAnswer22 = new QuestionAnswer();
        this.questionAnswer23 = new QuestionAnswer();
        this.questionAnswer24 = new QuestionAnswer();
        
        this.competencesTree = competenceFacade.getCompetencesTree();
        this.competenceGoalTree = competenceGoalFacade.getCompetenceGoalsTree(competencesTree, this.edrObject);

        edrList = edrFacade.findAll();
        employeeList = employeeFacade.findAll();
        businessAreaList = businessAreaFacade.findAll();

        return "createEdr";
    }

    public String save() throws InterruptedException {
        try {
            
        
        java.sql.Date date = new java.sql.Date(System.currentTimeMillis());

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

        // Load QuestionAnswer Objects
        
        for (QuestionAnswer entry : qAList) {
            questionCategory = entry.getQuestionCategory();
            if (entry.getEdrIdedr().equals(edr) && questionCategory == 1) {
                this.questionAnswer1 = entry;
                break;
            }
        }
        for (QuestionAnswer entry : qAList) {
            questionCategory = entry.getQuestionCategory();
            if (entry.getEdrIdedr().equals(edr) && questionCategory == 2) {
                this.questionAnswer2 = entry;
                break;
            }
        }
        for (QuestionAnswer entry : qAList) {
            questionCategory = entry.getQuestionCategory();
            if (entry.getEdrIdedr().equals(edr) && questionCategory == 3) {
                this.questionAnswer3 = entry;
                break;
            }
        }
        for (QuestionAnswer entry : qAList) {
            questionCategory = entry.getQuestionCategory();
            if (entry.getEdrIdedr().equals(edr) && questionCategory == 4) {
                this.questionAnswer4 = entry;
                break;
            }
        }
        for (QuestionAnswer entry : qAList) {
            questionCategory = entry.getQuestionCategory();
            if (entry.getEdrIdedr().equals(edr) && questionCategory == 5) {
                this.questionAnswer5 = entry;
                break;
            }
        }
        for (QuestionAnswer entry : qAList) {
            questionCategory = entry.getQuestionCategory();
            if (entry.getEdrIdedr().equals(edr) && questionCategory == 6) {
                this.questionAnswer6 = entry;
                break;
            }
        }
        for (QuestionAnswer entry : qAList) {
            questionCategory = entry.getQuestionCategory();
            if (entry.getEdrIdedr().equals(edr) && questionCategory == 7) {
                this.questionAnswer7 = entry;
                break;
            }
        }
        for (QuestionAnswer entry : qAList) {
            questionCategory = entry.getQuestionCategory();
            if (entry.getEdrIdedr().equals(edr) && questionCategory == 8) {
                this.questionAnswer8 = entry;
                break;
            }
        }
        for (QuestionAnswer entry : qAList) {
            questionCategory = entry.getQuestionCategory();
            if (entry.getEdrIdedr().equals(edr) && questionCategory == 9) {
                this.questionAnswer9 = entry;
                break;
            }
        }
        for (QuestionAnswer entry : qAList) {
            questionCategory = entry.getQuestionCategory();
            if (entry.getEdrIdedr().equals(edr) && questionCategory == 10) {
                this.questionAnswer10 = entry;
                break;
            }
        }
        for (QuestionAnswer entry : qAList) {
            questionCategory = entry.getQuestionCategory();
            if (entry.getEdrIdedr().equals(edr) && questionCategory == 11) {
                this.questionAnswer11 = entry;
                break;
            }
        }
        for (QuestionAnswer entry : qAList) {
            questionCategory = entry.getQuestionCategory();
            if (entry.getEdrIdedr().equals(edr) && questionCategory == 12) {
                this.questionAnswer12 = entry;
                break;
            }
        }
        for (QuestionAnswer entry : qAList) {
            questionCategory = entry.getQuestionCategory();
            if (entry.getEdrIdedr().equals(edr) && questionCategory == 13) {
                this.questionAnswer13 = entry;
                break;
            }
        }
        for (QuestionAnswer entry : qAList) {
            questionCategory = entry.getQuestionCategory();
            if (entry.getEdrIdedr().equals(edr) && questionCategory == 14) {
                this.questionAnswer14 = entry;
                break;
            }
           
        }
        for (QuestionAnswer entry : qAList) {
            questionCategory = entry.getQuestionCategory();
            if (entry.getEdrIdedr().equals(edr) && questionCategory == 15) {
                this.questionAnswer15 = entry;
                break;
            }
        }
        for (QuestionAnswer entry : qAList) {
            questionCategory = entry.getQuestionCategory();
            if (entry.getEdrIdedr().equals(edr) && questionCategory == 16) {
                this.questionAnswer16 = entry;
                break;
            }
        }
        for (QuestionAnswer entry : qAList) {
            questionCategory = entry.getQuestionCategory();
            if (entry.getEdrIdedr().equals(edr) && questionCategory == 17) {
                this.questionAnswer17 = entry;
                break;
            }
        }
        for (QuestionAnswer entry : qAList) {
            questionCategory = entry.getQuestionCategory();
            if (entry.getEdrIdedr().equals(edr) && questionCategory == 18) {
                this.questionAnswer18 = entry;
                break;
            }
        }
        for (QuestionAnswer entry : qAList) {
            questionCategory = entry.getQuestionCategory();
            if (entry.getEdrIdedr().equals(edr) && questionCategory == 19) {
                this.questionAnswer19 = entry;
                break;
            }
        }
        for (QuestionAnswer entry : qAList) {
            questionCategory = entry.getQuestionCategory();
            if (entry.getEdrIdedr().equals(edr) && questionCategory == 20) {
                this.questionAnswer20 = entry;
                break;
            }
        }
        for (QuestionAnswer entry : qAList) {
            questionCategory = entry.getQuestionCategory();
            if (entry.getEdrIdedr().equals(edr) && questionCategory == 21) {
                this.questionAnswer21 = entry;
                break;
            }
        }
        for (QuestionAnswer entry : qAList) {
            questionCategory = entry.getQuestionCategory();
            if (entry.getEdrIdedr().equals(edr) && questionCategory == 22) {
                this.questionAnswer22 = entry;
                break;
            }
        }
        for (QuestionAnswer entry : qAList) {
            questionCategory = entry.getQuestionCategory();
            if (entry.getEdrIdedr().equals(edr) && questionCategory == 23) {
                this.questionAnswer23 = entry;
                break;
            }
        }
        for (QuestionAnswer entry : qAList) {
            questionCategory = entry.getQuestionCategory();
            if (entry.getEdrIdedr().equals(edr) && questionCategory == 24) {
                this.questionAnswer24 = entry;
                break;
            }
        }
        
        for (QuestionAnswer entry : qAList) {
            questionCategory = entry.getQuestionCategory();
            if (entry.getEdrIdedr().equals(edr) && questionCategory == 1) {
                this.questionAnswer1 = entry;
                break;
            }
        }
        for (QuestionAnswer entry : qAList) {
            questionCategory = entry.getQuestionCategory();
            if (entry.getEdrIdedr().equals(edr) && questionCategory == 2) {
                this.questionAnswer2 = entry;
                break;
            }
        }
        for (QuestionAnswer entry : qAList) {
            questionCategory = entry.getQuestionCategory();
            if (entry.getEdrIdedr().equals(edr) && questionCategory == 3) {
                this.questionAnswer3 = entry;
                break;
            }
        }
        for (QuestionAnswer entry : qAList) {
            questionCategory = entry.getQuestionCategory();
            if (entry.getEdrIdedr().equals(edr) && questionCategory == 4) {
                this.questionAnswer4 = entry;
                break;
            }
        }
        for (QuestionAnswer entry : qAList) {
            questionCategory = entry.getQuestionCategory();
            if (entry.getEdrIdedr().equals(edr) && questionCategory == 5) {
                this.questionAnswer5 = entry;
                break;
            }
        }
        for (QuestionAnswer entry : qAList) {
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
            
            for (QuestionAnswer q : qAList) {
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
            for (CompetenceGoal cg : competenceGoalFacade.getGoalsForEdr(this.edrObject))
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
        edrList = edrFacade.findAll();
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
            
            for (QuestionAnswer qa : qAList)
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
