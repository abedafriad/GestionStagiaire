package helper;

import bean.Encadrant;
import java.util.List;

import javafx.scene.control.TableView;

public class EncadrantFxHForDep extends AbstractFxHelper<Encadrant> {

    private static AbstractFxHelperItem[] titres;

    static {

        titres = new AbstractFxHelperItem[]{
            new AbstractFxHelperItem("Nom", "nom"),
            new AbstractFxHelperItem("Prenom", "prenom")
        };
    }

    public EncadrantFxHForDep(TableView<Encadrant> table, List<Encadrant> list) {
        super(titres, table, list);
    }

    public EncadrantFxHForDep(TableView<Encadrant> table) {
        super(titres, table);
    }

}
