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
@Named(value = "UpdateQuestionAnswerCDIBean")
@SessionScoped
public class UpdateQuestionAnswerCDIBean implements Serializable {

    private static final long serialVersionUID = 1L;
    @EJB
    private QuestionAnswerFacade questionAnswerFacade;

    @EJB
    private EdrFacade edrFacade;

    private QuestionAnswer questionAnswerObject;
    private List<QuestionAnswer> questionAnswerList;
    private List<Edr> edrList;

    public UpdateQuestionAnswerCDIBean() {
    }

    @PostConstruct
    public void init() {
        questionAnswerList = questionAnswerFacade.findAll();
        edrList = edrFacade.findAll();
    }

    public QuestionAnswer getQuestionAnswerObject() {
        return questionAnswerObject;
    }

    public List<QuestionAnswer> getQuestionAnswerList() {
        return questionAnswerList;
    }

    public List<Edr> getEdrList() {
        return edrList;
    }

    public void setQuestionAnswerObject(QuestionAnswer questionAnswerObject) {
        this.questionAnswerObject = questionAnswerObject;
    }

    public void setQuestionAnswerList(List<QuestionAnswer> questionAnswerList) {
        this.questionAnswerList = questionAnswerList;
    }

    public void setEdrList(List<Edr> edrList) {
        this.edrList = edrList;
    }

    public String edit(QuestionAnswer questionAnswer) {
        this.questionAnswerObject = questionAnswer;
        return "editQuestionAnswer";
    }

    public String create() {
        this.questionAnswerObject = new QuestionAnswer();
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

    public void update() {
        try {
            if (questionAnswerObject.getIdquestion() == null) {
                questionAnswerFacade.create(questionAnswerObject);
            } else {
                questionAnswerFacade.edit(questionAnswerObject);
            }
            questionAnswerList = questionAnswerFacade.findAll();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            e.getMessage(), null));
        }
    }
   
}
