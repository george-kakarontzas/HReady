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
package eu.comprofits.cdibeans.assessment;

import eu.comprofits.entities.employee.Employee;
import eu.comprofits.session.employee.EmployeeFacade;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Named;

/**
 *
 * @author George Kakarontzas
 */
@Named(value = "employeeConverter1")
@RequestScoped
public class EmployeeConverter1 implements Converter {

    @EJB
    private EmployeeFacade employeeFacade;

    /**
     * Creates a new instance of CompetenceConverter
     */
    public EmployeeConverter1() {
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.isEmpty() || value.equals("---")) {
            return null;
        }
        /*
        strParts[0] should be last name
        strParts[1] should be first name
        strParts[3] should be email
        */
        String[] strParts = value.split("\\s+");
        
        return employeeFacade.getEmployeeByLnameFnameAndEmail(strParts[0], strParts[1], strParts[2]);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (!(value instanceof Employee)) {
            return null;
        }
        Employee e = (Employee) value;
        return e.getLastName()+" "+e.getFirstName()+" "+e.getEmail();
    }

}
