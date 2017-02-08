/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Stagee;
import bean.Tache;
import java.util.List;
import util.SearchUtil;

/**
 *
 * @author Abed
 */
public class TacheFacade extends AbstractFacade<Tache> {

    public TacheFacade() {
        super(Tache.class);
    }

    
    public List<Tache> findByStage(Stagee stagee){
        return getEntityManager().createQuery("SELECT t FROM Tache t WHERE t.stagee.id="+stagee.getId()).getResultList();
    }
    public List<Tache> recherchTache(String nom, Double avancementMax,Double avancementMin, Double importanceMax, Double importanceMin, Stagee stagee) {
        String req = "SELECT t FROM Tache t WHERE 1=1";
        req += SearchUtil.addConstraint("t", "nom", "=", nom);
        req += SearchUtil.addConstraint("t", "avancement", "<=", avancementMax);
        req += SearchUtil.addConstraint("t", "avancement", ">=", avancementMin);
        req += SearchUtil.addConstraint("t", "importance", "<=", importanceMax);
        req += SearchUtil.addConstraint("t", "importance", ">=", importanceMin);
        if (stagee != null) {
            req += SearchUtil.addConstraint("t", "stagee.id", "=", stagee.getId());
        }
        return getEntityManager().createQuery(req).getResultList();
    }

}
