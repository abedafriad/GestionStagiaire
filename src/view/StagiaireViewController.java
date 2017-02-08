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
import helper.StagiaireFxHelper;
import java.io.IOException;

import java.net.URL;
import util.DateUtil;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import service.DepartementFacade;
import service.EncadrantFacade;
import service.StageeFacade;
import service.StagiaireFacade;
import service.TacheFacade;
import util.Session;

/**
 * FXML Controller class
 *
 * @author Abed
 */
public class StagiaireViewController implements Initializable {

    /*
     * Declaration of class attributes
     */
 /*
    *****
    *******
    ********   Ajoute Attributes *****
     */
    @FXML
    private TextField nom;

    @FXML
    private TextField prenom;

    @FXML
    private RadioButton rb1 = new RadioButton();

    @FXML
    private RadioButton rb2 = new RadioButton();

    @FXML
    private DatePicker dateNaissance = new DatePicker();

    @FXML
    private TextField email;

    @FXML
    private TextField niveau;

    @FXML
    private TextField filiere;

    @FXML
    private TextField telephone;

    @FXML
    private TextArea adresse;

    @FXML
    private TextField etablissement;
    @FXML
    private ComboBox<Stagee> stageeComboBox = new ComboBox<>();
    @FXML
    private ComboBox<Departement> departementComboBox = new ComboBox<>();
    @FXML
    private ComboBox<Encadrant> encadrantComboBox = new ComboBox<>();

    /*
    *****
    *******
    ********   RECHERCHE Attributes *****
     */
    @FXML
    private TextField nomRech;

    @FXML
    private TextField prenomRech;

    @FXML
    private Label message;
    @FXML
    private RadioButton rb1Rech = new RadioButton();

    @FXML
    private RadioButton rb2Rech = new RadioButton();

    @FXML
    private TableView stagiareTableView = new TableView();

    @FXML
    private ComboBox<Stagee> stageeComboBoxRech = new ComboBox<>();
    @FXML
    private ComboBox<Departement> departementComboBoxRech = new ComboBox<>();
    @FXML
    private ComboBox<Encadrant> encadrantComboBoxRech = new ComboBox<>();

    StageeFacade stageeFacade = new StageeFacade();
    DepartementFacade departementFacade = new DepartementFacade();
    EncadrantFacade encadrantFacade = new EncadrantFacade();
    TacheFacade sujetFacade = new TacheFacade();
    StagiaireFacade stagiaireFacade = new StagiaireFacade();
    StagiaireFxHelper stagiaireFxHelper;

    @FXML
    private TextField nomEd;

    @FXML
    private TextField prenomEd;

    @FXML
    private RadioButton rb1Ed = new RadioButton();

    @FXML
    private RadioButton rb2Ed = new RadioButton();

    @FXML
    private DatePicker dateNaissanceEd = new DatePicker();

    @FXML
    private TextField emailEd;

    @FXML
    private TextField niveauEd;

    @FXML
    private TextField filiereEd;

    @FXML
    private TextField telephoneEd;

    @FXML
    private TextArea adresseEd;

    @FXML
    private TextField etablissementEd;
    @FXML
    private ComboBox<Stagee> stageeComboBoxEd = new ComboBox<>();
    @FXML
    private ComboBox<Departement> departementComboBoxEd = new ComboBox<>();
    @FXML
    private ComboBox<Encadrant> encadrantComboBoxEd = new ComboBox<>();

    /*
     * Functions
     */
    @FXML
    public void ajoute(ActionEvent actionEventAdd) {
        Stagiaire stagiaire = getParam(actionEventAdd);
        if (stagiaire != null) {
            stagiaireFacade.create(stagiaire);
            actualiser();
        }
    }

    @FXML
    public void recherche(ActionEvent actionEventRech) {
        String gender = "";
        if (rb1Rech.isSelected()) {
            gender = "M";
        } else if (rb2Rech.isSelected()) {
            gender = "F";
        }
        message.setVisible(true);
        stagiareTableView.setVisible(true);
        stagiaireFxHelper.setList(stagiaireFacade.recherchStagiaire(nomRech.getText(), prenomRech.getText(), gender, stageeComboBoxRech.getValue(), departementComboBoxRech.getValue(), encadrantComboBoxRech.getValue()));
        actualiser();
    }

    @FXML
    public void edit(ActionEvent actionEvent) throws IOException {
        Stagiaire stagiaire = stagiaireFxHelper.getSelected();
        if (stagiaire != null || Session.getAttribut("stagiaireEdit") != null) {
            String gender = "";
            if (rb1Ed.isSelected()) {
                gender = "M";
            } else if (rb2Ed.isSelected()) {
                gender = "F";
            }
            if (!nomEd.getText().equals("") && !prenomEd.getText().equals("") && !dateNaissanceEd.getValue().toString().equals("") && !gender.equals("") && !etablissementEd.getText().equals("") && !niveauEd.getText().equals("") && !filiereEd.getText().equals("") && !telephoneEd.getText().equals("") && !adresseEd.getText().equals("") && !emailEd.getText().equals("") && !stageeComboBoxEd.getValue().equals(null) && !departementComboBoxEd.getValue().equals(null) && !encadrantComboBoxEd.getValue().equals(null)) {
                stagiaire.setNom(nomEd.getText());
                stagiaire.setPrenom(prenomEd.getText());
                stagiaire.setDateNaissance(DateUtil.convert(dateNaissanceEd.getValue().toString()));
                stagiaire.setGender(gender);
                stagiaire.setEtablissement(etablissementEd.getText());
                stagiaire.setNiveau(new Integer(niveauEd.getText()));
                stagiaire.setFiliere(filiereEd.getText());
                stagiaire.setTelephone(telephoneEd.getText());
                stagiaire.setAdresse(adresseEd.getText());
                stagiaire.setEmail(emailEd.getText());
                stagiaire.setStagee(stageeComboBoxEd.getValue());
                stagiaire.setDepartement(departementComboBoxEd.getValue());
                stagiaire.setEncadrant(encadrantComboBoxEd.getValue());
                stagiaireFacade.edit(stagiaire);
                int i = stagiaireFxHelper.getTable().getItems().indexOf(stagiaireFxHelper.getSelected());
                stagiaireFxHelper.getTable().getItems().set(i, stagiaire);
                actualiser();
                if (stagiaire != null) {
                    stagiairePane.getSelectionModel().selectPrevious();
                } else if (Session.getAttribut("stagiaireEdit") != null) {
                    Session.setAttribut(-1D, "stagiaireEdit");
                    Launcher.forward(actionEvent, "StageView.fxml", this.getClass());
                }
            } else {
                alert(actionEvent);
            }
        } else {
            alertedit(actionEvent);
        }
    }

    @FXML
    public void delete(ActionEvent actionEventAdd) {
        Stagiaire stagiaire = stagiaireFxHelper.getSelected();
        if (stagiaire != null) {
            stagiaireFacade.remove(stagiaire);
            stagiaireFxHelper.setList(stagiaireFacade.findAll());
            //stagiaireFxHelper.getTable().getItems().remove(stagiaireFxHelper.getSelected());
        }
    }

    @FXML
    public void getForEdit() {
        Stagiaire stagiaire = stagiaireFxHelper.getSelected();
        fillField(stagiaire);
    }

    public void fillField(Stagiaire stagiaire) {
        if (stagiaire != null) {
            stagiairePane.getSelectionModel().select(3);
            nomEd.setText(stagiaire.getNom());
            prenomEd.setText(stagiaire.getPrenom());
            adresseEd.setText(stagiaire.getAdresse());
            if (stagiaire.getGender().equals("M")) {
                rb1Ed.setSelected(true);
            }
            if (stagiaire.getGender().equals("F")) {
                rb2Ed.setSelected(true);
            }
            emailEd.setText(stagiaire.getEmail());
            dateNaissanceEd.getEditor().setText(stagiaire.getDateNaissance().toString());
            niveauEd.setText(stagiaire.getNiveau() + "");
            filiereEd.setText(stagiaire.getFiliere());
            telephoneEd.setText(stagiaire.getTelephone());
            etablissementEd.setText(stagiaire.getEtablissement());
            stageeComboBoxEd.getSelectionModel().select(stagiaire.getStagee());
            departementComboBoxEd.getSelectionModel().select(stagiaire.getDepartement());
            encadrantComboBoxEd.getSelectionModel().select(stagiaire.getEncadrant());
        }
    }


    /*
     * Functions get & Initialize parametres
     */
    private Stagiaire getParam(ActionEvent actionEvent) {
        String gender = "";
        if (rb1.isSelected()) {
            gender = "M";
        } else if (rb2.isSelected()) {
            gender = "F";
        }
        if (!nom.getText().equals("") && !prenom.getText().equals("") && !dateNaissance.getValue().toString().equals("") && !gender.equals("") && !etablissement.getText().equals("") && !niveau.getText().equals("") && !filiere.getText().equals("") && !telephone.getText().equals("") && !adresse.getText().equals("") && !email.getText().equals("") && !stageeComboBox.getValue().equals(null) && !departementComboBox.getValue().equals(null) && !encadrantComboBox.getValue().equals(null)) {
            Stagiaire stagiaire = new Stagiaire(nom.getText(), prenom.getText(), DateUtil.convert(dateNaissance.getValue().toString()), gender, etablissement.getText(), new Integer(niveau.getText()), filiere.getText(), telephone.getText(), adresse.getText(), email.getText(), stageeComboBox.getValue(), departementComboBox.getValue(), encadrantComboBox.getValue());
            return stagiaire;
        }
        alert(actionEvent);
        return null;
    }

    private void initHelper() {
        stagiaireFxHelper = new StagiaireFxHelper(stagiareTableView);
    }

    private void initComboBox() {
        stageeComboBox.setItems(FXCollections.observableArrayList(stageeFacade.findAll()));
        departementComboBox.setItems(FXCollections.observableArrayList(departementFacade.findAll()));
        encadrantComboBox.setItems(FXCollections.observableArrayList(encadrantFacade.findAll()));
        stageeComboBoxEd.setItems(FXCollections.observableArrayList(stageeFacade.findAll()));
        departementComboBoxEd.setItems(FXCollections.observableArrayList(departementFacade.findAll()));
        encadrantComboBoxEd.setItems(FXCollections.observableArrayList(encadrantFacade.findAll()));
        stageeComboBoxRech.setItems(FXCollections.observableArrayList(stageeFacade.findAll()));
        departementComboBoxRech.setItems(FXCollections.observableArrayList(departementFacade.findAll()));
        encadrantComboBoxRech.setItems(FXCollections.observableArrayList(encadrantFacade.findAll()));
    }

    public void actualiser() {
        nom.setText("");
        prenom.setText("");
        rb1.setSelected(false);
        rb2.setSelected(false);
        dateNaissance.getEditor().setText("");
        email.setText("");
        niveau.setText("");
        filiere.setText("");
        telephone.setText("");
        adresse.setText("");
        etablissement.setText("");
        stageeComboBox.setValue(null);
        departementComboBox.setValue(null);
        encadrantComboBox.setValue(null);
        nomRech.setText("");
        prenomRech.setText("");
        rb1Rech.setSelected(false);
        rb2Rech.setSelected(false);
        stageeComboBoxRech.setValue(null);
        departementComboBoxRech.setValue(null);
        encadrantComboBoxRech.setValue(null);
        nomEd.setText("");
        prenomEd.setText("");
        rb1Ed.setSelected(false);
        rb2Ed.setSelected(false);
        dateNaissanceEd.getEditor().setText("");
        emailEd.setText("");
        niveauEd.setText("");
        filiereEd.setText("");
        telephoneEd.setText("");
        adresseEd.setText("");
        etablissementEd.setText("");
        stageeComboBoxEd.setValue(null);
        departementComboBoxEd.setValue(null);
        encadrantComboBoxEd.setValue(null);
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

    @FXML
    private void syncComboBoxS() {
//        if (stageeComboBox.isPressed()|| stageeComboBoxEd.isDisable()){
        encadrantComboBox.getSelectionModel().select(stageeComboBox.getValue().getEncadrant());
        encadrantComboBoxEd.getSelectionModel().select(stageeComboBox.getValue().getEncadrant());
    }

    @FXML
    private void syncComboBoxE() {
//        if (encadrantComboBox.isPressed()|| encadrantComboBoxEd.isDisabled()){
        stageeComboBox.getSelectionModel().select(encadrantComboBox.getValue().getStagee());
        stageeComboBoxEd.getSelectionModel().select(encadrantComboBox.getValue().getStagee());
//        }

    }

    @FXML
    private TabPane stagiairePane;

    @FXML
    public void edittab(ActionEvent actionEvent) {
        stagiairePane.getSelectionModel().select(3);
    }

    @FXML
    public void ajouttab(ActionEvent actionEvent) {
        stagiairePane.getSelectionModel().select(1);
    }

    @FXML
    public void recherchtab(ActionEvent actionEvent) {
        stagiairePane.getSelectionModel().select(2);
    }

    @FXML
    public void menutab(ActionEvent actionEvent) {
        stagiairePane.getSelectionModel().select(0);
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
    public void departement(ActionEvent actionEvent) throws IOException {
        Launcher.forward(actionEvent, "DepartementView.fxml", this.getClass());
    }

    @FXML
    public void encadrant(ActionEvent actionEvent) throws IOException {
        Launcher.forward(actionEvent, "EncadrantView.fxml", this.getClass());
    }

    @FXML
    public void tache(ActionEvent actionEvent) throws IOException {
        ViewLauncher.forward(actionEvent, "TacheView.fxml", this.getClass());
    }

    /*
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        initComboBox();
        initHelper();

        if (Session.getAttribut("stagiaireEdit") != null) {
            Stagiaire stagiaire = (Stagiaire) Session.getAttribut("stagiaireEdit");
            stagiairePane.getSelectionModel().select(3);
            fillField(stagiaire);
        }
    }

    /*
     * Getters & Setters
     */
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

    public TacheFacade getSujetFacade() {
        return sujetFacade;
    }

    public void setSujetFacade(TacheFacade sujetFacade) {
        this.sujetFacade = sujetFacade;
    }

    public TextField getNom() {
        return nom;
    }

    public void setNom(TextField nom) {
        this.nom = nom;
    }

    public TextField getPrenom() {
        return prenom;
    }

    public void setPrenom(TextField prenom) {
        this.prenom = prenom;
    }

    public DatePicker getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(DatePicker dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public TextField getEmail() {
        return email;
    }

    public void setEmail(TextField email) {
        this.email = email;
    }

    public TextField getNiveau() {
        return niveau;
    }

    public void setNiveau(TextField niveau) {
        this.niveau = niveau;
    }

    public TextField getFiliere() {
        return filiere;
    }

    public void setFiliere(TextField filiere) {
        this.filiere = filiere;
    }

    public TextField getTelephone() {
        return telephone;
    }

    public void setTelephone(TextField telephone) {
        this.telephone = telephone;
    }

    public TextArea getAdresse() {
        return adresse;
    }

    public void setAdresse(TextArea adresse) {
        this.adresse = adresse;
    }

    public TextField getEtablissement() {
        return etablissement;
    }

    public void setEtablissement(TextField etablissement) {
        this.etablissement = etablissement;
    }

    public ComboBox<Stagee> getStageeComboBox() {
        return stageeComboBox;
    }

    public void setStageeComboBox(ComboBox<Stagee> stageeComboBox) {
        this.stageeComboBox = stageeComboBox;
    }

    public ComboBox<Departement> getDepartementComboBox() {
        return departementComboBox;
    }

    public void setDepartementComboBox(ComboBox<Departement> departementComboBox) {
        this.departementComboBox = departementComboBox;
    }

    public ComboBox<Encadrant> getEncadrantComboBox() {
        return encadrantComboBox;
    }

    public void setEncadrantComboBox(ComboBox<Encadrant> encadrantComboBox) {
        this.encadrantComboBox = encadrantComboBox;
    }

    public StageeFacade getStageeFacade() {
        return stageeFacade;
    }

    public void setStageeFacade(StageeFacade stageeFacade) {
        this.stageeFacade = stageeFacade;
    }

    public StagiaireFacade getStagiaireFacade() {
        return stagiaireFacade;
    }

    public void setStagiaireFacade(StagiaireFacade stagiaireFacade) {
        this.stagiaireFacade = stagiaireFacade;
    }

    public RadioButton getRb1() {
        return rb1;
    }

    public void setRb1(RadioButton rb1) {
        this.rb1 = rb1;
    }

    public RadioButton getRb2() {
        return rb2;
    }

    public void setRb2(RadioButton rb2) {
        this.rb2 = rb2;
    }

    public TextField getNomRech() {
        return nomRech;
    }

    public Label getMessage() {
        return message;
    }

    public void setMessage(Label message) {
        this.message = message;
    }

    public StagiaireFxHelper getStagiaireFxHelper() {
        return stagiaireFxHelper;
    }

    /*
     *****
     *******
     ********   RECHERCHE Getter & Setters *****
     */
    public void setStagiaireFxHelper(StagiaireFxHelper stagiaireFxHelper) {
        this.stagiaireFxHelper = stagiaireFxHelper;
    }

    public void setNomRech(TextField nomRech) {
        this.nomRech = nomRech;
    }

    public TextField getPrenomRech() {
        return prenomRech;
    }

    public void setPrenomRech(TextField prenomRech) {
        this.prenomRech = prenomRech;
    }

    public RadioButton getRb1Rech() {
        return rb1Rech;
    }

    public void setRb1Rech(RadioButton rb1Rech) {
        this.rb1Rech = rb1Rech;
    }

    public RadioButton getRb2Rech() {
        return rb2Rech;
    }

    public void setRb2Rech(RadioButton rb2Rech) {
        this.rb2Rech = rb2Rech;
    }

    public ComboBox<Stagee> getStageeComboBoxRech() {
        return stageeComboBoxRech;
    }

    public void setStageeComboBoxRech(ComboBox<Stagee> stageeComboBoxRech) {
        this.stageeComboBoxRech = stageeComboBoxRech;
    }

    public ComboBox<Departement> getDepartementComboBoxRech() {
        return departementComboBoxRech;
    }

    public void setDepartementComboBoxRech(ComboBox<Departement> departementComboBoxRech) {
        this.departementComboBoxRech = departementComboBoxRech;
    }

    public ComboBox<Encadrant> getEncadrantComboBoxRech() {
        return encadrantComboBoxRech;
    }

    public void setEncadrantComboBoxRech(ComboBox<Encadrant> encadrantComboBoxRech) {
        this.encadrantComboBoxRech = encadrantComboBoxRech;
    }

    public TableView getStagiareTableView() {
        return stagiareTableView;
    }

    public void setStagiareTableView(TableView stagiareTableView) {
        this.stagiareTableView = stagiareTableView;
    }

}
