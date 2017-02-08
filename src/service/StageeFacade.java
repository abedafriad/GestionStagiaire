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
import bean.Tache;
import java.util.List;
import util.SearchUtil;

/**
 *
 * @author Abed
 */
public class StageeFacade extends AbstractFacade<Stagee> {

    public StageeFacade() {
        super(Stagee.class);
    }

    TacheFacade tacheFacade;
    EncadrantFacade encadrantFacade;
    StagiaireFacade stagiaireFacade;

    public void calculeAvanc(Stagee stagee) {
        tacheFacade = new TacheFacade();
        Double avancementTot = 0D;
        stagee.setAvancement(0D);
        List<Tache> taches = tacheFacade.findByStage(stagee);
        System.out.println(taches.toString());
        if (taches != null) {
            for (Tache tache : taches) {
                double pourc = tache.getImportance() / 100;
                System.out.println(pourc + "    " + tache.getAvancement());
                double avancement = tache.getAvancement() * pourc;
                System.out.println(avancement);
                avancementTot += avancement;
            }
        }
        stagee.setAvancement(avancementTot);
        edit(stagee);
    }

    public List<Stagee> recherchStage(String nom, Double budgetMax, Double budgetMin, Departement departement, Encadrant encadrant) {
        String req = "SELECT s FROM Stagee s WHERE 1=1";
        req += SearchUtil.addConstraint("s", "nom", "=", nom);
        req += SearchUtil.addConstraint("s", "budget", "<=", budgetMax);
        req += SearchUtil.addConstraint("s", "budget", ">=", budgetMin);
        if (departement != null) {
            req += SearchUtil.addConstraint("s", "departement.id", "=", departement.getId());
        }
        if (encadrant != null) {
            req += SearchUtil.addConstraint("s", "encadrant.login", "=", encadrant.getLogin());
        }
        return getEntityManager().createQuery(req).getResultList();
    }

    public void delete(Stagee stagee) {
        tacheFacade = new TacheFacade();
        encadrantFacade = new EncadrantFacade();
        stagiaireFacade = new StagiaireFacade();
        if (stagee != null) {
            List<Tache> taches = tacheFacade.findByStage(stagee);
            List<Stagiaire> stagiaires = stagiaireFacade.findByStagee(stagee);
            System.out.println(taches.toString());
            if (!taches.isEmpty()) {
                for (Tache tache : taches) {
                    tache.setStagee(null);
                    tacheFacade.remove(tache);
                }
            }
            if (!stagiaires.isEmpty()) {
                for (Stagiaire stagiaire : stagiaires) {
                    stagiaire.setStagee(null);
                    stagiaireFacade.remove(stagiaire);
                }
            }
            if (stagee.getEncadrant() != null) {
                encadrantFacade.remove(stagee.getEncadrant());
            }
            remove(stagee);
        }
    }
    public List<Stagee> findByDep(Departement d){
        return getEntityManager().createQuery("SELECT s FROM Stagee s WHERE s.departement.id ="+d.getId()).getResultList();
    }
}
