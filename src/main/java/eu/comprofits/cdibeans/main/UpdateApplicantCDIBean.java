/* 
 * Copyright 2016 ComProFITS Project.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package eu.comprofits.cdibeans.main;

import eu.comprofits.entities.jobapplicant.JobApplicant;
import eu.comprofits.session.jobapplicant.JobApplicantFacade;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.Map;
import java.util.ResourceBundle;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import eu.comprofits.cdibeans.main.CountryList;
import eu.comprofits.cdibeans.main.CountryList.Country;
import static java.lang.System.out;
import java.util.List;
import java.util.Set;
/**
 *
 * @author George Kakarontzas
 */
@Named(value = "updateApplicantCDIBean")
@SessionScoped
public class UpdateApplicantCDIBean implements Serializable {

    @EJB
    private JobApplicantFacade jobApplicantFacade;
    private JobApplicant jobApplicant;
    private UploadedFile photograph;
    private String password;

    @PostConstruct
    public void init() {
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        jobApplicant = 
                (JobApplicant) externalContext.getSessionMap().get("applicant");
        if (jobApplicant == null)
            jobApplicant = new JobApplicant();
    }

    /**
     * Creates a new instance of UpdateOrganisationalPositionsCDIBean
     */
    public UpdateApplicantCDIBean() {
    }

    public JobApplicant getJobApplicant() {
        return jobApplicant;
    }

    public void setJobApplicant(JobApplicant jobApplicant) {
        this.jobApplicant = jobApplicant;
    }

    public UploadedFile getPhotograph() {
        return photograph;
    }

    public void setPhotograph(UploadedFile photograph) {
        this.photograph = photograph;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhotoPath() {
        if (jobApplicant != null) {
            if (jobApplicant.getPhotoPath() != null) {
                return "/images/" + jobApplicant.getPhotoPath();
            }
        }
        return "/images/user.jpg";
    }

    public List<CountryList.Country> getCountries() {
        // Present a menu with language code, languate title 
        // better store country code in db.
        // this is irrespective of the chosen language and displays correctly
        // both in english and spanish
        CountryList countriesList = new CountryList(FacesContext.getCurrentInstance().getViewRoot().getLocale());
        return countriesList.getCountries();
    }
    
    public String getCountryName(Country country) {
        return country.getName(); 
    }
    
      public String getCountryCode(Country country) {
        return country.getCode(); 
    }  

    public void upload() {
        if (photograph != null) {
            FacesContext context = FacesContext.getCurrentInstance();
            ResourceBundle bundle = context.getApplication().getResourceBundle(context, "msgs");
            String message = bundle.getString("file_uploaded_succesfully");
            FacesMessage m = new FacesMessage(message + ":" + photograph.getFileName());
            FacesContext.getCurrentInstance().addMessage(null, m);
        }
    }

    public void fileUploadListener(FileUploadEvent e) {
        FacesContext context = FacesContext.getCurrentInstance();
        ResourceBundle bundle = context.getApplication().getResourceBundle(context, "msgs");

        // Get uploaded file from the FileUploadEvent
        this.photograph = e.getFile();
        File file = null;
        OutputStream output = null;
        try {
            // Prepare filename prefix and suffix for an unique filename in upload folder.
            String prefix = FilenameUtils.getBaseName(photograph.getFileName());
            String suffix = FilenameUtils.getExtension(photograph.getFileName());

            // Create file with unique name in upload folder and write to it.
            FacesContext ctx = FacesContext.getCurrentInstance();
            String outputdir
                    = ctx.getExternalContext().getInitParameter("FILE_UPLOAD_DIR");
            file = File.createTempFile(prefix + "_", "." + suffix, new File(outputdir));
            output = new FileOutputStream(file);
            IOUtils.copy(photograph.getInputstream(), output);
            //remove the old photograph file of the employee if it exists
            String oldFile = jobApplicant.getPhotoPath();
            File f = new File(outputdir + File.separator + oldFile);
            if (f.exists()) {
                f.delete();
            }
            jobApplicant.setPhotoPath(file.getName());
        } catch (IOException ex) {
            // Cleanup.
            if (file != null) {
                file.delete();
            }
            String failedMessage=bundle.getString("file_upload_failed");
            // Show error message.
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
                    FacesMessage.SEVERITY_ERROR, failedMessage, null));

            // Always log stacktraces (with a real logger).
            ex.printStackTrace();
        } finally {
            IOUtils.closeQuietly(output);
        }
        String succeededMessage=bundle.getString("file_uploaded_succesfully");
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(succeededMessage));
    }

    public String update() {
        String whereToReturn="";
        
        FacesContext context = FacesContext.getCurrentInstance();
        ResourceBundle bundle = context.getApplication().getResourceBundle(context, "msgs");

        try {
            if (jobApplicant.getIdjobApplicant() == null) {
                whereToReturn="registrationSucceeded";
                if (password.isEmpty()) {
                    String requiredPwdMessage=
                            bundle.getString("required_password");
                    FacesContext.getCurrentInstance().addMessage(null,
                            new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                    requiredPwdMessage, null));
                    return "";
                }
                String sha256hex = DigestUtils.sha256Hex(password);
                jobApplicant.setPassword(sha256hex);
                jobApplicantFacade.create(jobApplicant);
            } else {
                whereToReturn="applicantHomePage";
                if (!password.isEmpty()) {
                    String sha256hex = DigestUtils.sha256Hex(password);
                    jobApplicant.setPassword(sha256hex);
                }
                jobApplicantFacade.edit(jobApplicant);
            }

        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            e.getLocalizedMessage(), null));
            return "";
        }

        return whereToReturn;
    }
}
