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

import eu.comprofits.entities.assessment.Assessment;
import eu.comprofits.session.assessment.AssessmentFacade;
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
@Named(value = "assessmentsConverter")
@RequestScoped
public class AssessmentsConverter implements Converter {

    @EJB
    private AssessmentFacade assessmentFacade;

    /**
     * Creates a new instance of CompetenceConverter
     */
    public AssessmentsConverter() {
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.isEmpty() || value.equals("---")) {
            return null;
        }
        /*
        sparts[0] should be date that assessment was created
        sparts[1] should be assessee last name
        sparts[3] should be assessee first name
        */
        String[] sparts = value.split("\\s+");
        return assessmentFacade.getAssessmentByDateAndAssessee(sparts[0], sparts[1], sparts[2]);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (!(value instanceof Assessment)) {
            return null;
        }
        Assessment a = (Assessment) value;
        return a.getDateCreated()+" "+a.getAssesseeIdemployee().getLastName()+" "+
                a.getAssesseeIdemployee().getFirstName();
    }

}
