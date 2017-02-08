/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import bean.Departement;

import helper.DepartementFxHelper;
import helper.EncadrantFxHForDep;
import helper.StageeFxHForDep;
import helper.StagiaireFxHForDep;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import service.DepartementFacade;

/**
 * FXML Controller class
 *
 * @author Abed
 */
public class DepartementViewController implements Initializable {

    @FXML
    private TextField nom;

    @FXML
    private TextField nomRech;
    @FXML
    private TableView depTableView = new TableView();

    @FXML
    private TableView stagiareView;

    @FXML
    private TableView stageeView;

    @FXML
    private TableView encadrantView;

    @FXML
    private TextField nomEd;

    DepartementFacade departementFacade = new DepartementFacade();
    DepartementFxHelper departementFxHelper;
    StageeFxHForDep stageeFxHelper;
    EncadrantFxHForDep encadrantFxHelper;
    StagiaireFxHForDep stagiaireFxHelper;

    @FXML
    public void ajouter(ActionEvent actionEvent) {
        Departement departement = getParam(actionEvent);
        if (departement != null) {
            departementFacade.create(departement);
            actualiser();
        }
    }

    @FXML
    public void edit(ActionEvent actionEvent) {
        Departement departement = departementFxHelper.getSelected();
        if (departement != null) {
            if (!nomEd.getText().equals("")) {
                departement.setNom(nomEd.getText());
                departementFacade.edit(departement);
                int i = departementFxHelper.getTable().getItems().indexOf(departementFxHelper.getSelected());
                departementFxHelper.getTable().getItems().set(i, departement);
                actualiser();
                departementPane.getSelectionModel().selectPrevious();
            } else {
                alert(actionEvent);
            }
        } else {
            alertedit(actionEvent);
        }
    }

    @FXML
    public void recherch() {
        depTableView.setVisible(true);
        departementFxHelper.setList(departementFacade.recherch(nomRech.getText()));
        actualiser();
    }

    @FXML
    public void delete() {
        Departement d = departementFxHelper.getSelected();
        if(d!=null){
        departementFacade.delete(d);
        departementFxHelper.setList(departementFacade.findAll());
        }
    }

    @FXML
    public void getForEdit() {
        Departement d = departementFxHelper.getSelected();
        if(d!=null){
        departementPane.getSelectionModel().select(3);
        nomEd.setText(d.getNom());
        }
    }

    @FXML
    public void details(MouseEvent mouseEvent) {
        Departement d = departementFxHelper.getSelected();
        stageeFxHelper.setList(d.getStagees());
        encadrantFxHelper.setList(d.getEncadrants());
        stagiaireFxHelper.setList(d.getStagiaires());

    }

    private Departement getParam(ActionEvent actionEvent) {
        if (!nom.getText().equals("")) {
            Departement d = new Departement(nom.getText());
            return d;
        } else {
            alert(actionEvent);
            return null;
        }
    }

    private void initHelper() {
        departementFxHelper = new DepartementFxHelper(depTableView);
        stageeFxHelper = new StageeFxHForDep(stageeView);
        stagiaireFxHelper = new StagiaireFxHForDep(stagiareView);
        encadrantFxHelper = new EncadrantFxHForDep(encadrantView);
    }

    private void actualiser() {
        nom.setText("");
        nomEd.setText("");
        nomRech.setText("");

    }

    private void alert(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur insertion");
        alert.setContentText("Assurez-vouz qu'aucun champs est vide!! ");
        alert.setHeaderText("ATTENTION!");
        alert.showAndWait();
    }

    private void alertedit(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("ERREUR");
        alert.setContentText("Selectionner un element a modifier dans le volet de Recherche!! ");
        alert.setHeaderText("ATTENTION! Aucun element a modifier!!");
        alert.showAndWait();
    }

    /*
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        initHelper();
    }
    @FXML
    private TabPane departementPane;

    @FXML
    public void edittab(ActionEvent actionEvent) {
        departementPane.getSelectionModel().select(3);
    }

    @FXML
    public void ajouttab(ActionEvent actionEvent) {
        departementPane.getSelectionModel().select(1);
    }

    @FXML
    public void recherchtab(ActionEvent actionEvent) {
        departementPane.getSelectionModel().select(2);
    }

    @FXML
    public void menutab(ActionEvent actionEvent) {
        departementPane.getSelectionModel().select(0);
    }

    /*
    ****
    ****** Main Menu Items
     */
    @FXML
    public void mainmenu(ActionEvent actionEvent) throws IOException {
        Launcher.forward(actionEvent, "Menu.fxml", this.getClass());
    }

    @FXML
    public void stage(ActionEvent actionEvent) throws IOException {
        Launcher.forward(actionEvent, "StageView.fxml", this.getClass());
    }

    @FXML
    public void stagiaire(ActionEvent actionEvent) throws IOException {
        Launcher.forward(actionEvent, "StagiaireView.fxml", this.getClass());
    }

    @FXML
    public void encadrant(ActionEvent actionEvent) throws IOException {
        Launcher.forward(actionEvent, "EncadrantView.fxml", this.getClass());
    }

    @FXML
    public void tache(ActionEvent actionEvent) throws IOException {
        ViewLauncher.forward(actionEvent, "TacheView.fxml", this.getClass());
    }

    public TextField getNom() {
        return nom;
    }

    public void setNom(TextField nom) {
        this.nom = nom;
    }

    public TextField getNomRech() {
        return nomRech;
    }

    public void setNomRech(TextField nomRech) {
        this.nomRech = nomRech;
    }

    public TableView getDepTableView() {
        return depTableView;
    }

    public void setDepTableView(TableView depTableView) {
        this.depTableView = depTableView;
    }

    public DepartementFacade getDepartementFacade() {
        return departementFacade;
    }

    public void setDepartementFacade(DepartementFacade departementFacade) {
        this.departementFacade = departementFacade;
    }

    public DepartementFxHelper getDepartementFxHelper() {
        return departementFxHelper;
    }

    public void setDepartementFxHelper(DepartementFxHelper departementFxHelper) {
        this.departementFxHelper = departementFxHelper;
    }

    public TabPane getDepartementPane() {
        return departementPane;
    }

    public void setDepartementPane(TabPane departementPane) {
        this.departementPane = departementPane;
    }

}
