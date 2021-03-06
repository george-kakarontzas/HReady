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

import eu.comprofits.cdibeans.main.CountryList;
import eu.comprofits.entities.main.Competence;
import eu.comprofits.session.AbstractFacade;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

/**
 *
 * @author George Kakarontzas
 */
@Stateless
public class CompetenceFacade extends AbstractFacade<Competence> {

    @PersistenceContext(unitName = "comprofitsPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CompetenceFacade() {
        super(Competence.class);
    }

    public Competence findByName(String cName) {
        Object result = em.createNamedQuery("Competence.findByCompetenceName")
                .setParameter("competenceName", cName)
                .getSingleResult();
        return ((Competence) result);
    }
    
    public TreeNode getCompetencesTree()
    {
        TreeNode competencesTree = new DefaultTreeNode("Root",null);
        Query q = em.createQuery("SELECT c FROM Competence c WHERE c.parentId IS NULL");
        List<Competence> competences = q.getResultList();
        for (Competence c: competences)
        {
            getCompetencesTree(c,competencesTree);
        }
        return competencesTree;
    }
    
    public void getCompetencesTree(Competence childCompetence, TreeNode parentNode)
    {       
        
        TreeNode childTree = new DefaultTreeNode(childCompetence, parentNode);
        Query q = em.createQuery("SELECT c FROM Competence c WHERE c.parentId=:parent");
        q.setParameter("parent",childCompetence);
        List<Competence> childCompetences = q.getResultList();
        for (Competence c: childCompetences)
        {
            getCompetencesTree(c, childTree);
        }
    }
    
    public List<Competence> getOrderedCompetences() {
        Query q = em.createQuery("SELECT c FROM Competence c WHERE c.parentId IS NULL");
        List<Competence> competences = new ArrayList();
        List<Competence> parents = q.getResultList();
        for (Competence p: parents) {
            competences.add(p);
            competences.addAll(this.getDescendants(p, new ArrayList()));
        }
        return competences;
    }
    
    public List<Competence> getDescendants(Competence competence,List<Competence> descendants) {
        Query q = em.createQuery("SELECT c FROM Competence c WHERE c.parentId=:parent");
        q.setParameter("parent",competence);
        List<Competence> children = q.getResultList();
        for (Competence c: children) {
            descendants.add(c);
            descendants = getDescendants(c,descendants);
        }
        return descendants;
    }

}
