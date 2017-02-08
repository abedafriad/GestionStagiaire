package helper;

import bean.Encadrant;
import java.util.List;

import javafx.scene.control.TableView;

public class EncadrantFxHelper extends AbstractFxHelper<Encadrant> {

    private static AbstractFxHelperItem[] titres;

    static {

        titres = new AbstractFxHelperItem[]{
            new AbstractFxHelperItem("Login", "login"),
            new AbstractFxHelperItem("Nom", "nom"),
            new AbstractFxHelperItem("Prenom", "prenom"),
            new AbstractFxHelperItem("Gender", "gender"),
            new AbstractFxHelperItem("Stage", "stagee"),
            new AbstractFxHelperItem("Departement", "departement")
        };
    }

    public EncadrantFxHelper(TableView<Encadrant> table, List<Encadrant> list) {
        super(titres, table, list);
    }

    public EncadrantFxHelper(TableView<Encadrant> table) {
        super(titres, table);
    }

}
