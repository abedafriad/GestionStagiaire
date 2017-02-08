/*9
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
public class DepartementFacade extends AbstractFacade<Departement> {

    EncadrantFacade encadrantFacade;
    StagiaireFacade stagiaireFacade;
    StageeFacade stageeFacade;

    public DepartementFacade() {
        super(Departement.class);
    }

    public List<Departement> recherch(String nom) {
        String req = "select d from Departement d where 1=1";
        req += SearchUtil.addConstraint("d", "nom", "=", nom);
        return getEntityManager().createQuery(req).getResultList();

    }

    public void delete(Departement d) {
        if (d != null) {
            stageeFacade = new StageeFacade();
            stagiaireFacade = new StagiaireFacade();
            encadrantFacade = new EncadrantFacade();
            List<Stagee> stagees = stageeFacade.findByDep(d);
            List<Encadrant> encadrants = encadrantFacade.findByDep(d);
            List<Stagiaire> stagiaires = stagiaireFacade.findByDep(d);
            if (!stagiaires.isEmpty()) {
                for (Stagiaire stagiaire : stagiaires) {
                    stagiaireFacade.remove(stagiaire);
                }
            }
            if (!stagees.isEmpty()) {
                for (Stagee stagee : stagees) {
                    stageeFacade.delete(stagee);
                }
            }
            if (!encadrants.isEmpty()) {
                for (Encadrant encadrant : encadrants) {
                    encadrantFacade.delete(encadrant);
                }
            }
            remove(d);
        }
    }

}
