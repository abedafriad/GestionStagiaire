package helper;

import bean.Stagee;
import java.util.List;

import javafx.scene.control.TableView;

public class StageeFxHelper extends AbstractFxHelper<Stagee> {

    private static AbstractFxHelperItem[] titres;

    static {

        titres = new AbstractFxHelperItem[]{
            new AbstractFxHelperItem("ID", "id"),
            new AbstractFxHelperItem("Nom", "nom"),
            new AbstractFxHelperItem("Budget", "budget"),
            new AbstractFxHelperItem("Avancement", "avancement"),
            new AbstractFxHelperItem("Date Debut", "dateDeb"),
            new AbstractFxHelperItem("Date Fin", "dateFin"),
            new AbstractFxHelperItem("Encadrant", "encadrant"),
            new AbstractFxHelperItem("Departement", "departement")
        };
    }

    public StageeFxHelper(TableView<Stagee> table, List<Stagee> list) {
        super(titres, table, list);
    }

    public StageeFxHelper(TableView<Stagee> table) {
        super(titres, table);
    }

}
