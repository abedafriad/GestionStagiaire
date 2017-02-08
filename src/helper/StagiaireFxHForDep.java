/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helper;

import bean.Stagiaire;
import java.util.List;
import javafx.scene.control.TableView;

/**
 *
 * @author Abed
 */
public class StagiaireFxHForDep extends AbstractFxHelper<Stagiaire> {

    private static AbstractFxHelperItem[] titres;

    static {

        titres = new AbstractFxHelperItem[]{
            new AbstractFxHelperItem("Prenom", "prenom"),
            new AbstractFxHelperItem("Nom", "nom")
            };
    }

    public StagiaireFxHForDep(TableView<Stagiaire> table, List<Stagiaire> list) {
        super(titres, table, list);
    }

    public StagiaireFxHForDep(TableView<Stagiaire> table) {
        super(titres, table);
    }
}
