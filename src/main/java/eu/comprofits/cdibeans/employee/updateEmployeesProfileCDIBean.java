/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.comprofits.cdibeans.employee;

import eu.comprofits.entities.employee.Employee;
import eu.comprofits.entities.main.Department;
import eu.comprofits.session.employee.EmployeeFacade;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.security.Principal;
import java.util.List;
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
import org.primefaces.model.UploadedFile;

/**
 *
 * @author george
 */
@Named(value = "updateEmployeesProfileCDIBean")
@SessionScoped
public class updateEmployeesProfileCDIBean implements Serializable {

    @EJB
    private EmployeeFacade employeeFacade;
    private Employee employee;
    private Employee loggedInUser;
    private List<Employee> employees;
    private UploadedFile photograph;
    private String password;

    /**
     * Creates a new instance of UpdateOrganisationalPositionsCDIBean
     */
    public updateEmployeesProfileCDIBean() {
    }

    @PostConstruct
    public void init() {
        refreshDepartmentEmployeesList();
    }

    private void refreshDepartmentEmployeesList() {
        Department d = this.getDepartment();
        employees = employeeFacade.getDepartmentEmployees(d);
    }

    public Department getDepartment() {
        //get department of currently logged in department head
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        loggedInUser = (Employee) externalContext.getSessionMap().get("user");
        if (loggedInUser != null) {
            Department d = loggedInUser.getDepartmentIddepartment();
            return d;
        } //get Principal
        else {
            Principal principal
                    = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal();
            if (principal != null) {
                loggedInUser = employeeFacade.getEmployeeByUsername(principal.getName()); // Find User by j_username.
                Department d = loggedInUser.getDepartmentIddepartment();
                return d;
            }
        }
        return null;
    }

    public String getRoleString(String roleAbbreviation) {
        FacesContext context = FacesContext.getCurrentInstance();
        ResourceBundle bundle = context.getApplication().getResourceBundle(context, "msgs");
        switch (roleAbbreviation) {
            case "depthead":
                return bundle.getString("depthead");

            case "administrator":
                return bundle.getString("administrator");

            case "hrrecruiter":
                return bundle.getString("hr_recruiter");

            case "employee":
                return bundle.getString("employee");

            default:
                return "";
        }
    }

    public UploadedFile getPhotograph() {
        return photograph;
    }

    public void setPhotograph(UploadedFile photograph) {
        this.photograph = photograph;
    }

    public String getPhotoPath() {
        if (employee != null) {
            if (employee.getPhotoPath() != null) {
                return "/images/" + employee.getPhotoPath();
            }
        }
        return "/images/user.jpg";
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
        this.password = employee.getPassword();
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void upload() {
        if (photograph != null) {
            FacesMessage message = new FacesMessage("Succesful", photograph.getFileName() + " is uploaded.");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void remove(Employee e) {
        try {
            //first delete employee's photograph if not empty
            FacesContext ctx = FacesContext.getCurrentInstance();
            String outputdir
                    = ctx.getExternalContext().getInitParameter("FILE_UPLOAD_DIR");
            String oldFile = e.getPhotoPath();
            if (oldFile != null) {
                if (!oldFile.isEmpty()) {
                    File f = new File(outputdir + File.separator + oldFile);
                    if (f.exists()) {
                        f.delete();
                    }
                }
            }
            employeeFacade.remove(e);
            refreshDepartmentEmployeesList();
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            ex.getMessage(), null));
        }
    }

    public String edit(Employee e) {
        this.employee = e;
        return "editEmployeeProfile";
    }

    public String create() {
        this.employee = new Employee();
        employee.setDepartmentIddepartment(this.getDepartment());
        return "editEmployeeProfile";
    }

    public String callEditInCompanyEmployment(Employee e) {
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        externalContext.getSessionMap().put("employee", e);
        return "editInCompanyEmployment";
    }

    public String callEditPastCompanyEmployments(Employee e) {
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        externalContext.getSessionMap().put("employee", e);
        return "editPastCompanyEmployments";
    }

    public String callEditProExperience(Employee e) {
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        externalContext.getSessionMap().put("employee", e);
        return "updateProExperience";
    }

    public String callEditStudies(Employee e) {
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        externalContext.getSessionMap().put("employee", e);
        return "updateStudies";
    }

    public String update() {
        try {
            //save photo of user if not null
            if (!photograph.getFileName().isEmpty()) {
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
                    String oldFile = employee.getPhotoPath();
                    File f = new File(outputdir + File.separator + oldFile);
                    if (f.exists()) {
                        f.delete();
                    }
                    employee.setPhotoPath(file.getName());
                    photograph = null;
                    // Show succes message.
                    FacesContext.getCurrentInstance().addMessage("uploadForm", new FacesMessage(
                            FacesMessage.SEVERITY_INFO, "File upload succeed!", null));
                } catch (IOException e) {
                    // Cleanup.
                    if (file != null) {
                        file.delete();
                    }

                    // Show error message.
                    FacesContext.getCurrentInstance().addMessage("uploadForm", new FacesMessage(
                            FacesMessage.SEVERITY_ERROR, "File upload failed with I/O error.", null));

                    // Always log stacktraces (with a real logger).
                    e.printStackTrace();
                } finally {
                    IOUtils.closeQuietly(output);
                }
            }

            if (employee.getIdemployee() == null) {
                if (password.isEmpty()) {
                    FacesContext.getCurrentInstance().addMessage(null,
                            new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                    "password required for new employees", null));
                    return "";
                }
                String sha256hex = DigestUtils.sha256Hex(password);
                employee.setPassword(sha256hex);
                employeeFacade.create(employee);
            } else {
                if (!password.isEmpty()) {
                    String sha256hex = DigestUtils.sha256Hex(password);
                    employee.setPassword(sha256hex);
                }
                employeeFacade.edit(employee);
            }
            refreshDepartmentEmployeesList();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            e.getMessage(), null));
        }
        refreshDepartmentEmployeesList();
        return "updateEmployeeProfile";
    }

}
