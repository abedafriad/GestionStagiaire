/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helper;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/*
 *
 * @author AIMAD
 */
public abstract class AbstractFxHelper<T> {

    protected AbstractFxHelperItem[] abstractFxHelperItem;
    protected List<T> list = new ArrayList<T>();
    protected TableView<T> table;
    private ObservableList<T> data;

    public TableView<T> getTable() {
        return table;
    }

    public void setTable(TableView<T> table) {
        this.table = table;
    }

    public AbstractFxHelper(AbstractFxHelperItem[] abstractFxHelperItem, TableView<T> table) {
        this.abstractFxHelperItem = abstractFxHelperItem;
        this.table = table;
        setupTable();
    }

    public AbstractFxHelper(AbstractFxHelperItem[] abstractFxHelperItem, TableView<T> table, List<T> list) {
        this.abstractFxHelperItem = abstractFxHelperItem;
        this.table = table;
        this.list = list;
        setupTable();
    }

    public void select(T t) {
        table.getSelectionModel().select(t);
    }

    private void setupTable() {
        for (int i = 0; i < abstractFxHelperItem.length; i++) {
            if (abstractFxHelperItem[i].getAttributeName().equals("dateOperation")) {

            }
            TableColumn<T, ?> column = new TableColumn<>(abstractFxHelperItem[i].getColumnName());
            column.setCellValueFactory(new PropertyValueFactory<>(abstractFxHelperItem[i].getAttributeName()));
            table.getColumns().add(column);
        }
        data = FXCollections.observableArrayList(list);
        table.setItems(data);
    }

    public void save(T t) {
        list.add(t);
        data = FXCollections.observableArrayList(list);
        table.setItems(data);
    }

    public void remove(T t) {
        list.remove(t);
        data = FXCollections.observableArrayList(list);
        table.setItems(data);
    }

    public T getSelected() {
        return table.getSelectionModel().getSelectedItem();
    }

    public void setList(List<T> list) {
        data = FXCollections.observableArrayList(list);
        table.setItems(data);
    }

    public ObservableList<T> getData() {
        return data;
    }

    public void setData(ObservableList<T> data) {
        this.data = data;
    }

}
