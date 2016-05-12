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

import eu.comprofits.entities.jobapplicant.JobAdvertisement;
import eu.comprofits.session.jobapplicant.JobAdvertisementFacade;
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
@Named(value = "jobAdverisementConverter")
@RequestScoped
public class JobAdvertisementConverter implements Converter {

    @EJB
    private JobAdvertisementFacade advFacade;

    /**
     * Creates a new instance of CompetenceConverter
     */
    public JobAdvertisementConverter() {
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.isEmpty() || value.equals("---")) {
            return null;
        }
        return advFacade.find(Integer.valueOf(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (!(value instanceof JobAdvertisement)) {
            return null;
        }
        return ((JobAdvertisement) value).getIdjobAdvertisement().toString();
    }

}
