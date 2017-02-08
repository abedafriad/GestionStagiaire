/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Departement;
import bean.Encadrant;
import bean.Stagee;
import bean.Stagiaire;
import java.util.List;
import util.SearchUtil;

/**
 *
 * @author Abed
 */
public class StagiaireFacade extends AbstractFacade<Stagiaire> {

    public StagiaireFacade() {
        super(Stagiaire.class);
    }

    public List<Stagiaire> recherchStagiaire(String nom, String prenom, String gender, Stagee stagee, Departement departement, Encadrant encadrant) {
        String req = "SELECT s FROM Stagiaire s WHERE 1=1";
        req += SearchUtil.addConstraint("s", "nom", "=", nom);
        req += SearchUtil.addConstraint("s", "prenom", "=", prenom);
        req += SearchUtil.addConstraint("s", "gender", "=", gender);
        if (stagee != null) {
            req += SearchUtil.addConstraint("s", "stagee.id", "=", stagee.getId());
        }
        if (departement != null) {
            req += SearchUtil.addConstraint("s", "departement.id", "=", departement.getId());
        }
        if (encadrant != null) {
            req += SearchUtil.addConstraint("s", "encadrant.login", "=", encadrant.getLogin());
        }
        return getEntityManager().createQuery(req).getResultList();
    }
    
    public List<Stagiaire> findByStagee(Stagee stagee){
        return getEntityManager().createQuery("SELECT s FROM Stagiaire s WHERE s.stagee.id="+stagee.getId()).getResultList();
    }
    public List<Stagiaire> findByDep(Departement departement ){
        return getEntityManager().createQuery("SELECT s FROM Stagiaire s WHERE s.departement.id"+departement.getId()).getResultList();
    }

    public List<Stagiaire> findByEnca(Encadrant encadrant) {
        return getEntityManager().createQuery("SELECT s FROM Stagiaire s WHERE s.encadrant.login= '"+encadrant.getLogin()+"'").getResultList();
    }
    
}
