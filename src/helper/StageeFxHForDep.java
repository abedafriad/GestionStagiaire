/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helper;

import bean.Stagee;
import java.util.List;
import javafx.scene.control.TableView;

/**
 *
 * @author Abed
 */
public class StageeFxHForDep extends AbstractFxHelper<Stagee> {

    private static AbstractFxHelperItem[] titres;

    static {

        titres = new AbstractFxHelperItem[]{
            new AbstractFxHelperItem("Nom", "nom"),
            new AbstractFxHelperItem("Budget", "budget"),
            new AbstractFxHelperItem("Avancement", "avancement"),
        };
    }

    public StageeFxHForDep(TableView<Stagee> table, List<Stagee> list) {
        super(titres, table, list);
    }

    public StageeFxHForDep(TableView<Stagee> table) {
        super(titres, table);
    }

}
