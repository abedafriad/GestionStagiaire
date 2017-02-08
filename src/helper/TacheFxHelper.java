package helper;

import bean.Stagee;
import bean.Tache;
import java.util.List;

import javafx.scene.control.TableView;

public class TacheFxHelper extends AbstractFxHelper<Tache> {

    private static AbstractFxHelperItem[] titres;

    static {

        titres = new AbstractFxHelperItem[]{
            new AbstractFxHelperItem("ID", "id"),
            new AbstractFxHelperItem("Nom", "nom"),
            new AbstractFxHelperItem("Avancement", "avancement"),
            new AbstractFxHelperItem("Importance", "importance"),
            new AbstractFxHelperItem("Stage", "stagee")
        };
    }

    public TacheFxHelper(TableView<Tache> table, List<Tache> list) {
        super(titres, table, list);
    }

    public TacheFxHelper(TableView<Tache> table) {
        super(titres, table);
    }

}
