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
package eu.comprofits.cdibeans.jobprofile;


import eu.comprofits.entities.jobprofile.Division;
import eu.comprofits.session.jobprofile.DivisionFacade;
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
@Named(value = "divisionConverter")
@RequestScoped
public class DivisionConverter implements Converter {

    @EJB
    private DivisionFacade divisionFacade;

    /**
     * Creates a new instance of CompetenceConverter
     */
    public DivisionConverter() {
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.isEmpty() || value.equals("---")) {
            return null;
        }
        return divisionFacade.find(Integer.valueOf(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (!(value instanceof Division)) {
            return null;
        }
        return ((Division) value).getIddivision().toString();
    }

}
