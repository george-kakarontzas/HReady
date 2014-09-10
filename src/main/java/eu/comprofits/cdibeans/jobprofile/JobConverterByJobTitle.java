/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package eu.comprofits.cdibeans.jobprofile;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import eu.comprofits.entities.jobprofile.Job;
import eu.comprofits.session.jobprofile.JobFacade;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Named;

/**
 *
 * @author alexanderhoelzemann
 */
@Named(value = "jobConverterByJobTitle")
@RequestScoped
public class JobConverterByJobTitle implements Converter {

    @EJB
    private JobFacade jobFacade;

    /**
     * Creates a new instance of CompetenceConverter
     */
    public JobConverterByJobTitle() {
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.isEmpty() || value.equals("---")) {
            return null;
        }
        Job job = jobFacade.findByJobTitle(value);
        return  job;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (!(value instanceof Job)) {
            return null;
        }
        return ((Job) value).getJobTitle();
    }

}
