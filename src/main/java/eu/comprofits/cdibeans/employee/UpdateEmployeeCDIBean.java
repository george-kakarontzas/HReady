/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.comprofits.cdibeans.employee;

import eu.comprofits.cdibeans.main.CountryList;
import eu.comprofits.entities.employee.Employee;
import eu.comprofits.entities.jobprofile.Division;
import eu.comprofits.entities.main.Department;
import eu.comprofits.session.employee.EmployeeFacade;
import eu.comprofits.session.jobprofile.DivisionFacade;
import eu.comprofits.session.main.DepartmentFacade;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.security.Principal;
import java.util.ArrayList;
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
@Named(value = "updateEmployeeCDIBean")
@SessionScoped
public class UpdateEmployeeCDIBean implements Serializable {

    @EJB
    private EmployeeFacade employeeFacade;
    @EJB
    private DepartmentFacade departmentFacade;
    @EJB
    private DivisionFacade divisionFacade;
    private Employee employee;
    private List<Employee> employees;
    private List<Employee> filteredEmployees;
    private UploadedFile photograph;
    private String password;
    private List<Department> departments;
    private List<Division> divisions;
    /**
     * Creates a new instance of UpdateOrganisationalPositionsCDIBean
     */
    public UpdateEmployeeCDIBean() {
    }

    @PostConstruct
    public void init() {
        refreshEmployeesList();
        departments = departmentFacade.findAll();
        divisions = divisionFacade.findAll();
    }

    private void refreshEmployeesList() {
        employees = employeeFacade.findAll();
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

            case "hrassistant":
                return bundle.getString("hr_assistant");
                
            case "hrteamdevelopment":
                return bundle.getString("hr_team_development");
                
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

    public List<Employee> getFilteredEmployees() {
        return filteredEmployees;
    }

    public void setFilteredEmployees(List<Employee> filteredEmployees) {
        this.filteredEmployees = filteredEmployees;
    }

    public List<Department> getDepartments() {
        return departments;
    }

     public List<Department> getDepartmentsByDivision(Division d) {
        if (d!=null) {
            return departmentFacade.findDepartmenstForDivision(d);
        }
        else {
            return departmentFacade.findAll();
        }
    }
    
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Division> getDivisions() {
        return divisions;
    }

    public void setDivisions(List<Division> divisions) {
        this.divisions = divisions;
    }

    public void upload() {
        if (photograph != null) {
            FacesMessage message = new FacesMessage("Succesful", photograph.getFileName() + " is uploaded.");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void changeActiveStatus(Employee e) {
        e.setIsActive(!e.getIsActive());
        employeeFacade.edit(e);
        this.refreshEmployeesList();
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
            if (filteredEmployees != null) { 
                filteredEmployees.remove(e);
            }
            refreshEmployeesList();
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            ex.getMessage(), null));
        }
    }

    public Employee getCurrentEmployee() {
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
        return e;
    }
    
    public String edit(Employee e) {
        this.employee = e;
        return "editEmployee";
    }

    public String create() {
        this.employee = new Employee();
        this.employee.setIsActive(true);
        return "editEmployee";
    }

    public String update() {
        ResourceBundle bundle = FacesContext.getCurrentInstance().getApplication().getResourceBundle(FacesContext.getCurrentInstance(), "msgs");
                if (!employeeFacade.hasUniqueIdentityCard(employee)) {
                    FacesContext.getCurrentInstance().addMessage(null,
                            new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                    bundle.getString("non_unique_identity_card"), null));
                    return "";
                }
                if (!employeeFacade.hasUniqueSocialNumber(employee)) {
                    FacesContext.getCurrentInstance().addMessage(null,
                            new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                    bundle.getString("non_unique_social_number"), null));
                    return "";
                }
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
                //if new add also in the filtered list
                if (filteredEmployees != null) {
                    boolean exists = false;
                    for (Employee e : filteredEmployees) {
                        if (e.equals(employee)) {
                            exists = true;
                            break;
                        }
                    }
                    if (!exists) {
                        filteredEmployees.add(employee);
                    }
                }

            } else {
                if (!password.isEmpty()) {
                    String sha256hex = DigestUtils.sha256Hex(password);
                    employee.setPassword(sha256hex);
                }
                employeeFacade.edit(employee);
            }
            refreshEmployeesList();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            e.getMessage(), null));
        }
        refreshEmployeesList();
        return "updateEmployee";
    }
    
    
    public List<CountryList.Country> getCountries() {
        // Present a menu with language code, languate title 
        // better store country code in db.
        // this is irrespective of the chosen language and displays correctly
        // both in english and spanish
        CountryList countriesList = new CountryList(FacesContext.getCurrentInstance().getViewRoot().getLocale());
        return countriesList.getCountries();
    }
    
    public String getCountryName(CountryList.Country country) {
        return country.getName(); 
    }
    
      public String getCountryCode(CountryList.Country country) {
        return country.getCode(); 
    }  

}
