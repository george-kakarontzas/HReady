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
package eu.comprofits.session.main;

import eu.comprofits.entities.main.OrganisationalPosition;
import eu.comprofits.session.AbstractFacade;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author George Kakarontzas
 */
@Stateless
public class OrganisationalPositionFacade extends AbstractFacade<OrganisationalPosition> {

    @PersistenceContext(unitName = "comprofitsPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public OrganisationalPositionFacade() {
        super(OrganisationalPosition.class);
    }
    
    public OrganisationalPosition findByName(String name) {
        Object result = em.createNamedQuery("OrganisationalPosition.findByOrganisationalPositionName")
                .setParameter("organisationalPositionName", name)
                .getSingleResult();
        return ((OrganisationalPosition) result);
    }
}
