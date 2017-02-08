package helper;

import bean.Stagiaire;
import java.util.List;

import javafx.scene.control.TableView;

public class StagiaireFxHelper extends AbstractFxHelper<Stagiaire> {

    private static AbstractFxHelperItem[] titres;

    static {

        titres = new AbstractFxHelperItem[]{
            new AbstractFxHelperItem("N°.", "id"),
            new AbstractFxHelperItem("Prenom", "prenom"),
            new AbstractFxHelperItem("Nom", "nom"),
            new AbstractFxHelperItem("DateNiss", "dateNaissance"),
            new AbstractFxHelperItem("Stage", "stagee"),
            new AbstractFxHelperItem("Departement", "departement"),
            new AbstractFxHelperItem("Encadrant", "encadrant")
            };
    }

    public StagiaireFxHelper(TableView<Stagiaire> table, List<Stagiaire> list) {
        super(titres, table, list);
    }

    public StagiaireFxHelper(TableView<Stagiaire> table) {
        super(titres, table);
    }

}
