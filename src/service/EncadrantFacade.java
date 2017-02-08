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
public class EncadrantFacade extends AbstractFacade<Encadrant> {

    StagiaireFacade stagiaireFacade;

    public EncadrantFacade() {
        super(Encadrant.class);
    }

    public List<Encadrant> recherchEncadrants(String login, String nom, String prenom, String gender, Stagee stagee, Departement departement) {
        String req = "SELECT e FROM Encadrant e WHERE 1=1";
        req += SearchUtil.addConstraint("e", "login", "=", login);
        req += SearchUtil.addConstraint("e", "nom", "=", nom);
        req += SearchUtil.addConstraint("e", "prenom", "=", prenom);
        req += SearchUtil.addConstraint("e", "gender", "=", gender);
        if (stagee != null) {
            req += SearchUtil.addConstraint("e", "stagee.id", "=", stagee.getId());
        }
        if (departement != null) {
            req += SearchUtil.addConstraint("e", "departement.id", "=", departement.getId());
        }
        return getEntityManager().createQuery(req).getResultList();
    }

    public Object[] seConnecter(String login, String password) {
        Encadrant encadrant = find(login);
        if (encadrant == null) {
            return new Object[]{-1, null};
        } else if (!encadrant.getPassword().equals(password)) {
            return new Object[]{-2, null};
        } else if (encadrant.getBlocked()==1) {
            return new Object[]{-3, null};
        } else {
            return new Object[]{1, encadrant};
        }
    }

    public int findByLogin(String login) {
        String req = "SELECT e FROM Encadrant e WHERE 1=1";
        req += SearchUtil.addConstraint("e", "login", "=", login);
        List<Encadrant> encadrants = getEntityManager().createQuery(req).getResultList();
        if (encadrants.isEmpty()) {
            return -1;
        } else {
            return 1;
        }
    }

    public List<Encadrant> findByBlock() {
        String req = "SELECT e FROM Encadrant e WHERE 1=1";
        
            req += SearchUtil.addConstraint("e", "blocked", "=", 1);
        
        return getEntityManager().createQuery(req).getResultList();
    }

    public void delete(Encadrant encadrant) {
        if (encadrant != null) {
            stagiaireFacade = new StagiaireFacade();
            List<Stagiaire> stagiaires = stagiaireFacade.findByEnca(encadrant);
            if (!stagiaires.isEmpty()) {
                for (Stagiaire stagiaire : stagiaires) {
                    stagiaireFacade.remove(stagiaire);
                }
            }
            remove(encadrant);
        }
    }

    public List<Encadrant> findByDep(Departement departement) {
        return getEntityManager().createQuery("SELECT e FROM Encadrant e WHERE e.departement.id=" + departement.getId()).getResultList();
    }

}
