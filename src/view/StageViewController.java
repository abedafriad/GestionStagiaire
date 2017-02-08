/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import bean.Departement;
import bean.Encadrant;
import bean.Stagee;
import bean.Stagiaire;
import bean.Tache;

import helper.StageeFxHelper;
import helper.StagiaireFxHForDep;
import helper.TacheFxHelper;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import service.DepartementFacade;
import service.EncadrantFacade;
import service.StageeFacade;
import service.StagiaireFacade;
import service.TacheFacade;

import util.DateUtil;
import util.Session;

/**
 * FXML Controller class
 *
 * @author Abed
 */
public class StageViewController implements Initializable {

    /*
     * Declaration of class attributes
     */
    @FXML
    private TextField nom;

    @FXML
    private TextField budget;

    @FXML
    private DatePicker dateDeb;

    @FXML
    private DatePicker dateFin;

    @FXML
    private ComboBox<Encadrant> encadrantComboBox;

    @FXML
    private ComboBox<Departement> departementComboBox;

    StageeFacade stageeFacade = new StageeFacade();
    DepartementFacade departementFacade = new DepartementFacade();
    EncadrantFacade encadrantFacade = new EncadrantFacade();
    TacheFacade tacheFacade = new TacheFacade();
    StagiaireFacade stagiaireFacade = new StagiaireFacade();
    StageeFxHelper stageeFxHelper;
    TacheFxHelper tacheFxHelper;
    StagiaireFxHForDep stagiaireFxHForDep;

    /*
    ******
    ******** Recherche View Attributes
    *****
     */
    @FXML
    private TextField nomRech;
    @FXML
    private TextField budgetMax;
    @FXML
    private TextField budgetMin;

    @FXML
    private ComboBox<Encadrant> encadrantComboBoxRech;

    @FXML
    private ComboBox<Departement> departementComboBoxRech;

    @FXML
    private TableView stageTableView;
    @FXML
    private TableView tacheTableView;
    @FXML
    private TableView stagiaireTableView;

    /*
    ******
    ******** Edit View Attributes
    *****
     */
    @FXML
    private TextField nomEd;

    @FXML
    private DatePicker dateDebEd;

    @FXML
    private DatePicker dateFinEd;

    @FXML
    private ComboBox<Encadrant> encadrantComboBoxEd;

    @FXML
    private ComboBox<Departement> departementComboBoxEd;

    @FXML
    private TextField budgetEd;

    /*
     * Functions
     */
    @FXML
    private void ajout(ActionEvent event) {
        Stagee stagee = getParam(event);
        if (stagee != null) {
            stageeFacade.calculeAvanc(stagee);
            stageeFacade.create(stagee);
            actualiser();
        }
    }

    @FXML
    public void edit(ActionEvent event) {
        Stagee s = stageeFxHelper.getSelected();
        if (s != null) {
            Stagee stagee = stageeFacade.find(s.getId());
            if (stagee != null && !nomEd.getText().equals("") && !budgetEd.getText().equals("") && !dateDebEd.getEditor().getText().equals("") && !dateFinEd.getEditor().getText().equals("") && !encadrantComboBoxEd.getValue().equals("") && !departementComboBoxEd.getValue().equals("")) {
                stagee.setNom(nomEd.getText());
                stagee.setBudget(new Double(budgetEd.getText()));
                stagee.setDateDeb(DateUtil.convert(dateDebEd.getValue().toString()));
                stagee.setDateFin(DateUtil.convert(dateFinEd.getValue().toString()));
                stagee.setDepartement(departementComboBoxEd.getValue());
                stagee.setEncadrant(encadrantComboBoxEd.getValue());
                stageeFacade.calculeAvanc(stagee);
                stageeFacade.edit(stagee);
                int i = stageeFxHelper.getTable().getItems().indexOf(stageeFxHelper.getSelected());
                stageeFxHelper.getTable().getItems().set(i, stagee);
                actualiser();
                stagePane.getSelectionModel().selectPrevious();
            } else {
                alert(event);
            }
        } else {
            alertedit(event);
        }
    }

    @FXML
    public void delete(ActionEvent actionEvent) {
        Stagee stagee = stageeFxHelper.getSelected();
        if (stagee != null) {
            stageeFacade.delete(stagee);
            stageeFxHelper.setList(stageeFacade.findAll());
            tacheFxHelper.setList(null);
            stagiaireFxHForDep.setList(null);
        }
    }

    @FXML
    public void getForEdit(ActionEvent event) {
        Stagee stagee = stageeFxHelper.getSelected();
        if (stagee != null) {
            stagePane.getSelectionModel().select(3);
            nomEd.setText(stagee.getNom());
            dateDebEd.getEditor().setText(stagee.getDateDeb().toString());
            dateFinEd.getEditor().setText(stagee.getDateFin().toString());
            encadrantComboBoxEd.setValue(stagee.getEncadrant());
            departementComboBoxEd.setValue(stagee.getDepartement());
            budgetEd.setText(stagee.getBudget() + "");
        }
    }

    @FXML
    public void editTache(ActionEvent ae) throws IOException {
        Tache tache = tacheFxHelper.getSelected();
        if (tache != null) {
            Session.setAttribut(tache, "tacheEdit");
            Launcher.forward(ae, "TacheView.fxml", this.getClass());
        }
    }

    @FXML
    public void supprTache(ActionEvent ae) {
        Tache tache = tacheFxHelper.getSelected();
        if (tache != null) {
            Stagee s = tache.getStagee();
            tacheFacade.remove(tache);
            tacheFxHelper.setList(tacheFacade.findByStage(s));
            stageeFacade.calculeAvanc(s);
            stageeFxHelper.getTable().refresh();
        }
    }

    @FXML
    public void editStagiaire(ActionEvent ae) throws IOException {
        Stagiaire stagiaire = stagiaireFxHForDep.getSelected();
        if (stagiaire != null) {
            Session.setAttribut(stagiaire, "stagiaireEdit");
            Launcher.forward(ae, "StagiaireView.fxml", this.getClass());
        }
    }

    @FXML
    public void supprStagiaire(ActionEvent ae) throws IOException {
        Stagiaire stagiaire = stagiaireFxHForDep.getSelected();
        if (stagiaire != null) {
            stagiaireFacade.remove(stagiaire);
            stagiaireFxHForDep.setList(stagiaireFacade.findAll());
        }
    }

    @FXML
    public void details(MouseEvent event) {
        tacheFxHelper.setList(stageeFxHelper.getSelected().getTaches());
        stagiaireFxHForDep.setList(stageeFxHelper.getSelected().getStagiaires());
    }

    @FXML
    public void recherch(ActionEvent event) {
        Double bmx;
        Double bmn;
        if (budgetMax.getText().equals("")) {
            bmx = null;
        } else {
            bmx = new Double(budgetMax.getText());
        }
        if (budgetMin.getText().equals("")) {
            bmn = null;
        } else {
            bmn = new Double(budgetMin.getText());
        }

        stageeFxHelper.setList(stageeFacade.recherchStage(nomRech.getText(), bmx, bmn, departementComboBoxRech.getValue(), encadrantComboBoxRech.getValue()));
    }

    public void actualiser() {
        nomEd.setText("");
        dateDebEd.getEditor().setText("");
        dateFinEd.getEditor().setText("");
        encadrantComboBoxEd.setValue(null);
        departementComboBoxEd.setValue(null);
        budgetEd.setText("");
        nom.setText("");
        dateDeb.getEditor().setText("");
        dateFin.getEditor().setText("");
        encadrantComboBox.setValue(null);
        departementComboBox.setValue(null);
        budget.setText("");
        nomRech.setText("");
        encadrantComboBoxRech.setValue(null);
        departementComboBoxRech.setValue(null);
        budgetMax.setText("");
        budgetMin.setText("");
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

    /**
     * Functions get & Initialize parametres
     */
    private Stagee getParam(ActionEvent actionEvent) {
        if (!nom.getText().equals("") && !budget.getText().equals("") && !dateDeb.getEditor().getText().equals("") && !dateFin.getEditor().getText().equals("") && !encadrantComboBox.getValue().equals("") && !departementComboBox.getValue().equals("")) {
            Stagee stagee = new Stagee(nom.getText(), DateUtil.convert(dateDeb.toString()), DateUtil.convert(dateFin.toString()), new Double(budget.getText()), departementComboBox.getValue(), encadrantComboBox.getValue());
            return stagee;
        } else {
            alert(actionEvent);
            return null;
        }
    }

    private void initHelper() {
        stageeFxHelper = new StageeFxHelper(stageTableView);
        tacheFxHelper = new TacheFxHelper(tacheTableView);
        stagiaireFxHForDep = new StagiaireFxHForDep(stagiaireTableView);
    }

    private void initComboBox() {
        departementComboBox.setItems(FXCollections.observableArrayList(departementFacade.findAll()));
        encadrantComboBox.setItems(FXCollections.observableArrayList(encadrantFacade.findAll()));
        departementComboBoxEd.setItems(FXCollections.observableArrayList(departementFacade.findAll()));
        encadrantComboBoxEd.setItems(FXCollections.observableArrayList(encadrantFacade.findAll()));
        departementComboBoxRech.setItems(FXCollections.observableArrayList(departementFacade.findAll()));
        encadrantComboBoxRech.setItems(FXCollections.observableArrayList(encadrantFacade.findAll()));
    }

    @FXML
    private TabPane stagePane;

    @FXML
    public void edittab(ActionEvent actionEvent) {
        stagePane.getSelectionModel().select(3);
    }

    @FXML
    public void ajouttab(ActionEvent actionEvent) {
        stagePane.getSelectionModel().select(1);
    }

    @FXML
    public void recherchtab(ActionEvent actionEvent) {
        stagePane.getSelectionModel().select(2);
    }

    @FXML
    public void menutab(ActionEvent actionEvent) {
        stagePane.getSelectionModel().select(0);
    }

    /*
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        initComboBox();
        initHelper();
        if (Session.getAttribut("tacheEdit") instanceof Double) {
            stagePane.getSelectionModel().select(2);
            Session.setAttribut(null, "tacheEdit");
        }
        if (Session.getAttribut("stagiaireEdit") instanceof Double) {
            stagePane.getSelectionModel().select(2);
            Session.setAttribut(null, "stagiaireEdit");
        }
    }

    /*
    ****
    ****** Main Menu Items
     */
    @FXML
    public void stagiaire(ActionEvent actionEvent) throws IOException {
        ViewLauncher.forward(actionEvent, "StagiaireView.fxml", this.getClass());
    }

    @FXML
    public void mainmenu(ActionEvent actionEvent) throws IOException {
        ViewLauncher.forward(actionEvent, "Menu.fxml", this.getClass());
    }

    @FXML
    public void departement(ActionEvent actionEvent) throws IOException {
        ViewLauncher.forward(actionEvent, "DepartementView.fxml", this.getClass());
    }

    @FXML
    public void encadrant(ActionEvent actionEvent) throws IOException {
        ViewLauncher.forward(actionEvent, "EncadrantView.fxml", this.getClass());
    }

    @FXML
    public void tache(ActionEvent actionEvent) throws IOException {
        ViewLauncher.forward(actionEvent, "TacheView.fxml", this.getClass());
    }

    /*
     * Getters & Setters
     */
    public TextField getNom() {
        return nom;
    }

    public void setNom(TextField nom) {
        this.nom = nom;
    }

    public TextField getBudget() {
        return budget;
    }

    public void setBudget(TextField budget) {
        this.budget = budget;
    }

    public DatePicker getDateDeb() {
        return dateDeb;
    }

    public void setDateDeb(DatePicker DateDeb) {
        this.dateDeb = DateDeb;
    }

    public DatePicker getDateFin() {
        return dateFin;
    }

    public void setDateFin(DatePicker DateFin) {
        this.dateFin = DateFin;
    }

    public DepartementFacade getDepartementFacade() {
        return departementFacade;
    }

    public void setDepartementFacade(DepartementFacade departementFacade) {
        this.departementFacade = departementFacade;
    }

    public EncadrantFacade getEncadrantFacade() {
        return encadrantFacade;
    }

    public void setEncadrantFacade(EncadrantFacade encadrantFacade) {
        this.encadrantFacade = encadrantFacade;
    }

    public ComboBox<Encadrant> getEncadrantComboBox() {
        return encadrantComboBox;
    }

    public void setEncadrantComboBox(ComboBox<Encadrant> encadrantComboBox) {
        this.encadrantComboBox = encadrantComboBox;
    }

    public ComboBox<Departement> getDepartementComboBox() {
        return departementComboBox;
    }

    public void setDepartementComboBox(ComboBox<Departement> departementComboBox) {
        this.departementComboBox = departementComboBox;
    }

    public StageeFacade getStageeFacade() {
        return stageeFacade;
    }

    public void setStageeFacade(StageeFacade stageeFacade) {
        this.stageeFacade = stageeFacade;
    }

    public TextField getNomRech() {
        return nomRech;
    }

    public void setNomRech(TextField nomRech) {
        this.nomRech = nomRech;
    }

    public TextField getBudgetMax() {
        return budgetMax;
    }

    public void setBudgetMax(TextField budgetMax) {
        this.budgetMax = budgetMax;
    }

    public TextField getBudgetMin() {
        return budgetMin;
    }

    public void setBudgetMin(TextField budgetMin) {
        this.budgetMin = budgetMin;
    }

    public StageeFxHelper getStageeFxHelper() {
        return stageeFxHelper;
    }

    public void setStageeFxHelper(StageeFxHelper stageeFxHelper) {
        this.stageeFxHelper = stageeFxHelper;
    }

    public ComboBox<Encadrant> getEncadrantComboBoxRech() {
        return encadrantComboBoxRech;
    }

    public void setEncadrantComboBoxRech(ComboBox<Encadrant> encadrantComboBoxRech) {
        this.encadrantComboBoxRech = encadrantComboBoxRech;
    }

    public ComboBox<Departement> getDepartementComboBoxRech() {
        return departementComboBoxRech;
    }

    public void setDepartementComboBoxRech(ComboBox<Departement> departementComboBoxRech) {
        this.departementComboBoxRech = departementComboBoxRech;
    }

    public TableView getStageTableView() {
        return stageTableView;
    }

    public void setStageTableView(TableView stageTableView) {
        this.stageTableView = stageTableView;
    }

    public TabPane getEditS() {
        return stagePane;
    }

    public void setEditS(TabPane stagePane) {
        this.stagePane = stagePane;
    }

}
