/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.comprofits.cdibeans.edr;

import eu.comprofits.entities.edr.Edr;
import eu.comprofits.entities.edr.EdrHistory;
import eu.comprofits.entities.edr.QuestionAnswer;
import eu.comprofits.entities.employee.Employee;
import eu.comprofits.entities.jobprofile.BusinessArea;
import eu.comprofits.entities.jobprofile.Job;
import eu.comprofits.session.edr.EdrFacade;
import eu.comprofits.session.edr.EdrHistoryFacade;
import eu.comprofits.session.edr.QuestionAnswerFacade;
import eu.comprofits.session.employee.EmployeeFacade;
import eu.comprofits.session.jobprofile.BusinessAreaFacade;
import eu.comprofits.session.jobprofile.JobFacade;
import java.io.Serializable;
import java.sql.Timestamp;
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
    @EJB
    private QuestionAnswerFacade questionAnswerFacade;
    @EJB
    private EdrHistoryFacade edrHistoryFacade;

    private EdrHistory edrHistory;
    private BusinessArea selectedBusinessArea;
    private Edr edrObject;
    private Edr latestEdr;
    private Job selectedJob;
    private List<BusinessArea> businessAreaList;
    private List<Edr> edrList;
    private List<Edr> filteredEdrList;
    private List<Employee> employeeList;
    private boolean firstTime;

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

        edrObject = new Edr();
        edrList = edrFacade.findAll();
        employeeList = employeeFacade.findAll();
        businessAreaList = businessAreaFacade.findAll();

        questionAnswer1 = new QuestionAnswer();
        questionAnswer2 = new QuestionAnswer();
        questionAnswer3 = new QuestionAnswer();
        questionAnswer4 = new QuestionAnswer();
        questionAnswer5 = new QuestionAnswer();
        questionAnswer6 = new QuestionAnswer();
        questionAnswer7 = new QuestionAnswer();
        questionAnswer8 = new QuestionAnswer();
        questionAnswer9 = new QuestionAnswer();
        questionAnswer10 = new QuestionAnswer();
        questionAnswer11 = new QuestionAnswer();
        questionAnswer12 = new QuestionAnswer();
        questionAnswer13 = new QuestionAnswer();
        questionAnswer14 = new QuestionAnswer();
        questionAnswer15 = new QuestionAnswer();
        questionAnswer16 = new QuestionAnswer();
        questionAnswer17 = new QuestionAnswer();
        questionAnswer18 = new QuestionAnswer();
        questionAnswer19 = new QuestionAnswer();
        questionAnswer20 = new QuestionAnswer();
        questionAnswer21 = new QuestionAnswer();
        questionAnswer22 = new QuestionAnswer();
        questionAnswer23 = new QuestionAnswer();
        questionAnswer24 = new QuestionAnswer();

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
        }
        if (tempList.isEmpty()) {
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
        
        
        return "createEdrPage1";
    }

    public String create() {
        this.edrObject = new Edr();
        
        this.questionAnswer1 = new QuestionAnswer();
        this.questionAnswer2 = new QuestionAnswer();
        this.questionAnswer3 = new QuestionAnswer();
        this.questionAnswer4 = new QuestionAnswer();
        this.questionAnswer5 = new QuestionAnswer();
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
        
        
        edrList = edrFacade.findAll();
        employeeList = employeeFacade.findAll();
        businessAreaList = businessAreaFacade.findAll();
       
        return "createEdrPage1";
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

    public String save(String note) throws InterruptedException {
        questionAnswer1.setEdrIdedr(edrObject);
        questionAnswer2.setEdrIdedr(edrObject);
        questionAnswer3.setEdrIdedr(edrObject);
        questionAnswer4.setEdrIdedr(edrObject);
        questionAnswer5.setEdrIdedr(edrObject);
        questionAnswer6.setEdrIdedr(edrObject);
        questionAnswer7.setEdrIdedr(edrObject);
        questionAnswer8.setEdrIdedr(edrObject);
        questionAnswer9.setEdrIdedr(edrObject);
        questionAnswer10.setEdrIdedr(edrObject);
        questionAnswer11.setEdrIdedr(edrObject);
        questionAnswer12.setEdrIdedr(edrObject);
        questionAnswer13.setEdrIdedr(edrObject);
        questionAnswer14.setEdrIdedr(edrObject);
        questionAnswer15.setEdrIdedr(edrObject);
        questionAnswer16.setEdrIdedr(edrObject);
        questionAnswer17.setEdrIdedr(edrObject);
        questionAnswer18.setEdrIdedr(edrObject);
        questionAnswer19.setEdrIdedr(edrObject);
        questionAnswer20.setEdrIdedr(edrObject);
        questionAnswer21.setEdrIdedr(edrObject);
        questionAnswer22.setEdrIdedr(edrObject);
        questionAnswer23.setEdrIdedr(edrObject);
        questionAnswer24.setEdrIdedr(edrObject);

        if (edrObject != null) {
            if (firstTime) {
                edrFacade.create(edrObject);
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

            } else {
                edrFacade.edit(edrObject);
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
            }
        }

        
        
        edrList = edrFacade.findAll();
        edrObject = new Edr();
        edrHistory = new EdrHistory();
        
        
        edrHistory.setIdedr(edrObject);
        edrHistory.setIdemployee(edrObject.getImmediateManagerIdemployee());
        edrHistory.setNote(note);
       
       //edrHistory.setTimestamp(new Timestamp());
        questionAnswer1 = new QuestionAnswer();
        questionAnswer2 = new QuestionAnswer();
        questionAnswer3 = new QuestionAnswer();
        questionAnswer4 = new QuestionAnswer();
        questionAnswer5 = new QuestionAnswer();
        questionAnswer6 = new QuestionAnswer();
        questionAnswer7 = new QuestionAnswer();
        questionAnswer8 = new QuestionAnswer();
        questionAnswer9 = new QuestionAnswer();
        questionAnswer10 = new QuestionAnswer();
        questionAnswer11 = new QuestionAnswer();
        questionAnswer12 = new QuestionAnswer();
        questionAnswer13 = new QuestionAnswer();
        questionAnswer14 = new QuestionAnswer();
        questionAnswer15 = new QuestionAnswer();
        questionAnswer16 = new QuestionAnswer();
        questionAnswer17 = new QuestionAnswer();
        questionAnswer18 = new QuestionAnswer();
        questionAnswer19 = new QuestionAnswer();
        questionAnswer20 = new QuestionAnswer();
        questionAnswer21 = new QuestionAnswer();
        questionAnswer22 = new QuestionAnswer();
        questionAnswer23 = new QuestionAnswer();
        questionAnswer24 = new QuestionAnswer();

        FacesContext context = FacesContext.getCurrentInstance();
        ResourceBundle text = ResourceBundle.getBundle("messages", context.getViewRoot().getLocale());
        String message = text.getString("succesful_save_message");
        context.addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, message, message));

        return "updateEdr";
    }

    public String print() throws InterruptedException {

        return "createEdrPage2";

    }

    public String help() throws InterruptedException {

        return "createEdrPage2";

    }
    
    public String createNewEdr() throws InterruptedException {
        
        edrObject = new Edr();
        edrList = edrFacade.findAll();
        employeeList = employeeFacade.findAll();
        businessAreaList = businessAreaFacade.findAll();

        questionAnswer1 = new QuestionAnswer();
        questionAnswer2 = new QuestionAnswer();
        questionAnswer3 = new QuestionAnswer();
        questionAnswer4 = new QuestionAnswer();
        questionAnswer5 = new QuestionAnswer();
        questionAnswer6 = new QuestionAnswer();
        questionAnswer7 = new QuestionAnswer();
        questionAnswer8 = new QuestionAnswer();
        questionAnswer9 = new QuestionAnswer();
        questionAnswer10 = new QuestionAnswer();
        questionAnswer11 = new QuestionAnswer();
        questionAnswer12 = new QuestionAnswer();
        questionAnswer13 = new QuestionAnswer();
        questionAnswer14 = new QuestionAnswer();
        questionAnswer15 = new QuestionAnswer();
        questionAnswer16 = new QuestionAnswer();
        questionAnswer17 = new QuestionAnswer();
        questionAnswer18 = new QuestionAnswer();
        questionAnswer19 = new QuestionAnswer();
        questionAnswer20 = new QuestionAnswer();
        questionAnswer21 = new QuestionAnswer();
        questionAnswer22 = new QuestionAnswer();
        questionAnswer23 = new QuestionAnswer();
        questionAnswer24 = new QuestionAnswer();
        
        return "./createEdrPage1.xhtml";
    }
}
