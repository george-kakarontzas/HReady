/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.comprofits.cdibeans.edr;

import eu.comprofits.entities.edr.Edr;
import eu.comprofits.entities.edr.QuestionAnswer;
import eu.comprofits.session.edr.EdrFacade;
import eu.comprofits.session.edr.QuestionAnswerFacade;
import java.io.Serializable;
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
@Named(value = "updateQuestionAnswerCDIBean")
@SessionScoped
public class UpdateQuestionAnswerCDIBean implements Serializable {

    private static final long serialVersionUID = 1L;
    @EJB
    private QuestionAnswerFacade questionAnswerFacade;

    @EJB
    private EdrFacade edrFacade;

    private QuestionAnswer questionAnswerObject1;
    private QuestionAnswer questionAnswerObject2;
    private QuestionAnswer questionAnswerObject3;
    private QuestionAnswer questionAnswerObject4;

    private boolean firsttime;
    private Edr edrObject;
    private List<QuestionAnswer> questionAnswerList;
    private List<Edr> edrList;

    public UpdateQuestionAnswerCDIBean() {
    }

    @PostConstruct
    public void init() {
        questionAnswerObject1 = new QuestionAnswer();
        questionAnswerObject2 = new QuestionAnswer();
        questionAnswerObject3 = new QuestionAnswer();
        questionAnswerObject4 = new QuestionAnswer();
        questionAnswerList = questionAnswerFacade.findAll();
        edrList = edrFacade.findAll();
        firsttime = true;
    }

    public boolean isFirsttime() {
        return firsttime;
    }

    public void setFirsttime(boolean firsttime) {
        this.firsttime = firsttime;
    }

    public QuestionAnswer getQuestionAnswerObject() {
        return questionAnswerObject1;
    }

    public List<QuestionAnswer> getQuestionAnswerList() {
        return questionAnswerList;
    }

    public QuestionAnswer getQuestionAnswerObject1() {
        return questionAnswerObject1;
    }

    public void setQuestionAnswerObject1(QuestionAnswer questionAnswerObject1) {
        this.questionAnswerObject1 = questionAnswerObject1;
    }

    public QuestionAnswer getQuestionAnswerObject2() {
        return questionAnswerObject2;
    }

    public void setQuestionAnswerObject2(QuestionAnswer questionAnswerObject2) {
        this.questionAnswerObject2 = questionAnswerObject2;
    }

    public QuestionAnswer getQuestionAnswerObject3() {
        return questionAnswerObject3;
    }

    public void setQuestionAnswerObject3(QuestionAnswer questionAnswerObject3) {
        this.questionAnswerObject3 = questionAnswerObject3;
    }

    public QuestionAnswer getQuestionAnswerObject4() {
        return questionAnswerObject4;
    }

    public void setQuestionAnswerObject4(QuestionAnswer questionAnswerObject4) {
        this.questionAnswerObject4 = questionAnswerObject4;
    }

    public List<Edr> getEdrList() {
        return edrList;
    }

    public void setQuestionAnswerObject(QuestionAnswer questionAnswerObject) {
        this.questionAnswerObject1 = questionAnswerObject;
    }

    public void setQuestionAnswerList(List<QuestionAnswer> questionAnswerList) {
        this.questionAnswerList = questionAnswerList;
    }

    public void setEdrList(List<Edr> edrList) {
        this.edrList = edrList;
    }

    public Edr getEdrObject() {
        return edrObject;
    }

    public void setEdrObject(Edr edrObject) {
        this.edrObject = edrObject;
    }

    public String edit(QuestionAnswer questionAnswer) {
        this.questionAnswerObject1 = questionAnswer;
        return "editQuestionAnswer";
    }

    public String create1() {
        this.questionAnswerObject1 = new QuestionAnswer();
        return "createQuestionAnswer";
    }

    public String create2() {
        this.questionAnswerObject2 = new QuestionAnswer();
        return "createQuestionAnswer";
    }

    public String create3() {
        this.questionAnswerObject3 = new QuestionAnswer();
        return "createQuestionAnswer";
    }

    public String create4() {
        this.questionAnswerObject4 = new QuestionAnswer();
        return "createQuestionAnswer";
    }

    public void remove(QuestionAnswer questionAnswer) {
        try {
            questionAnswerFacade.remove(questionAnswer);
            questionAnswerList = questionAnswerFacade.findAll();

        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            e.getMessage(), null));
        }
    }

    public void update1() {
        try {
            if (questionAnswerObject1.getIdquestion() == null) {
                questionAnswerFacade.create(questionAnswerObject1);
            } else {
                questionAnswerFacade.edit(questionAnswerObject1);
            }
            questionAnswerList = questionAnswerFacade.findAll();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            e.getMessage(), null));
        }
    }

    public void update2() {
        try {
            if (questionAnswerObject2.getIdquestion() == null) {
                questionAnswerFacade.create(questionAnswerObject2);
            } else {
                questionAnswerFacade.edit(questionAnswerObject2);
            }
            questionAnswerList = questionAnswerFacade.findAll();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            e.getMessage(), null));
        }
    }

    public void update3() {
        try {
            if (questionAnswerObject3.getIdquestion() == null) {
                questionAnswerFacade.create(questionAnswerObject3);
            } else {
                questionAnswerFacade.edit(questionAnswerObject3);
            }
            questionAnswerList = questionAnswerFacade.findAll();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            e.getMessage(), null));
        }
    }

    public void update4() {
        try {
            if (questionAnswerObject4.getIdquestion() == null) {
                questionAnswerFacade.create(questionAnswerObject4);
            } else {
                questionAnswerFacade.edit(questionAnswerObject4);
            }
            questionAnswerList = questionAnswerFacade.findAll();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            e.getMessage(), null));
        }
    }

    public String save() throws InterruptedException {
        //if (questionAnswerObject1 != null ) {

            //if (firsttime) {
        Edr edr1= new Edr();
        edr1.setIdedr(0);
        questionAnswerObject1.setQuestion("question1");
        questionAnswerObject2.setQuestion("question2");
        questionAnswerObject3.setQuestion("question3");
        questionAnswerObject4.setQuestion("question4");
        questionAnswerObject1.setEdrIdedr(edr1);
        questionAnswerObject2.setEdrIdedr(edr1);
        questionAnswerObject3.setEdrIdedr(edr1);
        questionAnswerObject4.setEdrIdedr(edr1);
        questionAnswerObject1.setQuestionCategory(0);
        questionAnswerObject2.setQuestionCategory(0);
        questionAnswerObject3.setQuestionCategory(0);
        questionAnswerObject4.setQuestionCategory(0);

        questionAnswerFacade.create(questionAnswerObject1);
        questionAnswerFacade.create(questionAnswerObject2);
        questionAnswerFacade.create(questionAnswerObject3);
        questionAnswerFacade.create(questionAnswerObject4);
            //} else {
//                questionAnswerFacade.edit(questionAnswerObject1);
//                questionAnswerFacade.edit(questionAnswerObject2);
//                questionAnswerFacade.edit(questionAnswerObject3);
//                questionAnswerFacade.edit(questionAnswerObject4);
        //}
        // }
        FacesContext context = FacesContext.getCurrentInstance();
        ResourceBundle text = ResourceBundle.getBundle("messages", context.getViewRoot().getLocale());
        String message = text.getString("succesful_save_message");
        context.addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, message, message));
        return "";
    }

}
