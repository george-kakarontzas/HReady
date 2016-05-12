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
package eu.comprofits.session.employee;

import eu.comprofits.entities.employee.Employee;
import eu.comprofits.entities.employee.ProfessionalExperienceRecord;
import eu.comprofits.session.AbstractFacade;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author George Kakarontzas
 */
@Stateless
public class ProfessionalExperienceRecordFacade extends AbstractFacade<ProfessionalExperienceRecord> {
    @PersistenceContext(unitName = "comprofitsPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProfessionalExperienceRecordFacade() {
        super(ProfessionalExperienceRecord.class);
    }
    
    public List<ProfessionalExperienceRecord> getProRecsForEmployee(Employee e) {
        List<ProfessionalExperienceRecord> all = em.createQuery(
                "Select p From ProfessionalExperienceRecord p WHERE p.employeeIdemployee=:emp").
                setParameter("emp", e).getResultList();
        return all;
    }
    
}
