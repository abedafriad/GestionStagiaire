package helper;

import bean.Departement;
import bean.Stagee;
import java.util.List;

import javafx.scene.control.TableView;

public class DepartementFxHelper extends AbstractFxHelper<Departement> {

    private static AbstractFxHelperItem[] titres;

    static {

        titres = new AbstractFxHelperItem[]{
            new AbstractFxHelperItem("ID", "id"),
            new AbstractFxHelperItem("Nom", "nom")
        };
    }

    public DepartementFxHelper(TableView<Departement> table, List<Departement> list) {
        super(titres, table, list);
    }

    public DepartementFxHelper(TableView<Departement> table) {
        super(titres, table);
    }

}
