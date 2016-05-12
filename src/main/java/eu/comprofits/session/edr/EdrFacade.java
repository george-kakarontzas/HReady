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
package eu.comprofits.session.edr;

import eu.comprofits.entities.edr.Edr;
import eu.comprofits.entities.employee.Employee;
import eu.comprofits.entities.jobprofile.Job;
import eu.comprofits.entities.main.Department;
import eu.comprofits.session.AbstractFacade;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author George Kakarontzas
 */
@Stateless
public class EdrFacade extends AbstractFacade<Edr> {
    @PersistenceContext(unitName = "comprofitsPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EdrFacade() {
        super(Edr.class);
    }
    
    public List<Edr> getVisibleEdrsForEmployee (Employee employee)
    {
        List<Edr> edrList = this.findAll();
        List<Edr> filteredEdrList = new ArrayList();
        
        for (Edr edr : edrList)
        {
            if (employee.getIdemployee() == edr.getHeadOfDepartmentIdemployee().getIdemployee())
            {
                edr.setRole(1);
                filteredEdrList.add(edr);
            }
            if ((employee.getIdemployee() == edr.getImmediateManagerIdemployee().getIdemployee()) && (edr.getStatus() > 1))
            {
                edr.setRole(2);     
                filteredEdrList.add(edr);
            }
            if ((employee.getIdemployee() == edr.getReviewedEmployeeIdemployee().getIdemployee()) && (edr.getStatus() > 2))
            {
                edr.setRole(3);    
                filteredEdrList.add(edr);
            }
            
        }
        
        return filteredEdrList;
    }
        
    public List<Edr> getEdrHistory (Edr edrObject)
    {
        List<Edr> EdrHistory = new ArrayList();
        Query q = em.createQuery("SELECT e FROM Edr e WHERE e.reviewedEmployeeIdemployee=:reviewedEmployee AND e.idedr!=:idedr AND e.year<=:year AND e.status=5 ORDER BY e.year DESC");
        q.setParameter("reviewedEmployee", edrObject.getReviewedEmployeeIdemployee());
        q.setParameter("idedr",edrObject.getIdedr());
        q.setParameter("year",edrObject.getYear());
        
        EdrHistory = q.getResultList();
        return EdrHistory;
    }
}
