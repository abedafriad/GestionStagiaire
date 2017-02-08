/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helper;

import bean.Tache;
import java.util.List;
import javafx.scene.control.TableView;

/**
 *
 * @author Abed
 */
public class TacheFxHForStg extends AbstractFxHelper<Tache> {

    private static AbstractFxHelperItem[] titres;

    static {

        titres = new AbstractFxHelperItem[]{
            new AbstractFxHelperItem("Nom", "nom"),
            new AbstractFxHelperItem("Avancement", "avancement"),
            new AbstractFxHelperItem("Importance", "importance")
        };
    }

    public TacheFxHForStg(TableView<Tache> table, List<Tache> list) {
        super(titres, table, list);
    }

    public TacheFxHForStg(TableView<Tache> table) {
        super(titres, table);
    }

}
