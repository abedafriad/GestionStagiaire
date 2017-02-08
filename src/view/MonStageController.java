/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import bean.Encadrant;
import bean.Stagee;
import bean.Stagiaire;
import bean.Tache;
import helper.StagiaireFxHelper;
import helper.TacheFxHelper;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
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
public class MonStageController implements Initializable {

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
    private RadioButton rb1Rech = new RadioButton();

    @FXML
    private RadioButton rb2Rech = new RadioButton();

    @FXML
    private TableView stagiareTableView = new TableView();
    @FXML
    private TableView stagiareTableView1 = new TableView();
    @FXML
    private TableView tacheTableView = new TableView();

    StageeFacade stageeFacade = new StageeFacade();
    DepartementFacade departementFacade = new DepartementFacade();
    EncadrantFacade encadrantFacade = new EncadrantFacade();
    TacheFacade tacheFacade = new TacheFacade();
    StagiaireFacade stagiaireFacade = new StagiaireFacade();
    TacheFxHelper tacheFxHelper;
    StagiaireFxHelper stagiaireFxHelper;
    StagiaireFxHelper stagiaireFxHelper1;

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

    Encadrant encadrant = (Encadrant) Session.getAttribut("encaConnect");

    /*
     * Functions
     */
    @FXML
    public void ajoute(ActionEvent actionEventAdd) {
        Stagiaire stagiaire = getParam(actionEventAdd);
        if (stagiaire != null) {
            stagiaireFacade.create(stagiaire);
            actualiser();
            stagiaireFxHelper1.setList(stagiaireFacade.findByEnca(encadrant));
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

        stagiareTableView.setVisible(true);
        stagiaireFxHelper.setList(stagiaireFacade.recherchStagiaire(nomRech.getText(), prenomRech.getText(), gender, encadrant.getStagee(), encadrant.getDepartement(), this.encadrant));
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
            if (!nomEd.getText().equals("") && !prenomEd.getText().equals("") && !dateNaissanceEd.getValue().toString().equals("") && !gender.equals("") && !etablissementEd.getText().equals("") && !niveauEd.getText().equals("") && !filiereEd.getText().equals("") && !telephoneEd.getText().equals("") && !adresseEd.getText().equals("") && !emailEd.getText().equals("")) {
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
                stagiaire.setStagee(this.encadrant.getStagee());
                stagiaire.setDepartement(this.encadrant.getDepartement());
                stagiaire.setEncadrant(this.encadrant);
                stagiaireFacade.edit(stagiaire);
                int i = stagiaireFxHelper.getTable().getItems().indexOf(stagiaireFxHelper.getSelected());
                stagiaireFxHelper.getTable().getItems().set(i, stagiaire);
                actualiser();
                stagiairePane.getSelectionModel().selectPrevious();
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
            stagiaireFxHelper.setList(stagiaireFacade.findByEnca(encadrant));
            stagiaireFxHelper1.setList(stagiaireFacade.findByEnca(encadrant));
            //stagiaireFxHelper.getTable().getItems().remove(stagiaireFxHelper.getSelected());
        }
    }

    @FXML
    public void delete1(ActionEvent actionEventAdd) {
        Stagiaire stagiaire = stagiaireFxHelper1.getSelected();
        if (stagiaire != null) {
            stagiaireFacade.remove(stagiaire);
            stagiaireFxHelper1.setList(stagiaireFacade.findByEnca(encadrant));
            //stagiaireFxHelper.getTable().getItems().remove(stagiaireFxHelper.getSelected());
        }
    }

    @FXML
    public void getForEdit(ActionEvent actionEvent) {
        Stagiaire stagiaire = stagiaireFxHelper.getSelected();
        fillField(stagiaire);
    }

    public void getForEdit1(ActionEvent actionEvent) {
        Stagiaire stagiaire = stagiaireFxHelper1.getSelected();
        fillField(stagiaire);
    }

    public void fillField(Stagiaire stagiaire) {
        if (stagiaire != null) {
            stagiairePane.getSelectionModel().select(4);
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
        }
    }

    /*
    **********************************
    *******************************
    *****************************
     */
    @FXML
    private AnchorPane tacheEditAvanc;
    @FXML
    private Label tacheInfo;
    @FXML
    private TextField tacheAvanc;
    @FXML
    private Button editAvancButton;

    @FXML
    public void geteditAvanc(ActionEvent actionEvent) {
        Tache tache = tacheFxHelper.getSelected();
        if (tache != null) {
            tacheTableView.setVisible(false);
            editAvancButton.setDisable(true);
            tacheEditAvanc.setVisible(true);
            tacheInfo.setText(tache.getNom() + " Avanc.");
            tacheAvanc.setText(tache.getAvancement().toString());
        }
    }

    @FXML
    public void editAvanc(ActionEvent actionEvent) {
        Tache tache = tacheFxHelper.getSelected();
        tache.setAvancement(new Double(tacheAvanc.getText()));
        tacheFacade.edit(tache);
        stageeFacade.calculeAvanc(this.encadrant.getStagee());

        tacheEditAvanc.setVisible(false);
        editAvancButton.setDisable(false);
        tacheTableView.setVisible(true);
        tacheFxHelper.getTable().refresh();
        tacheFxHelper.setList(tacheFacade.findByStage(this.encadrant.getStagee()));
        initStageData();
    }

    @FXML
    public void setComplet(ActionEvent actionEvent) {
        Tache tache = tacheFxHelper.getSelected();
        tache.setAvancement(100D);
        if (tacheEditAvanc.isVisible()) {
            tacheEditAvanc.setVisible(false);
            editAvancButton.setDisable(false);
            tacheTableView.setVisible(true);
        }
        tacheFacade.edit(tache);
        stageeFacade.calculeAvanc(this.encadrant.getStagee());
        tacheFxHelper.getTable().refresh();
        tacheFxHelper.setList(tacheFacade.findByStage(this.encadrant.getStagee()));
        initStageData();

    }

    /*
    //////////////////////************************
    *******************************************
    ***********************************
     */
    @FXML
    private Label stageLabel;
    @FXML
    private Label encaLabel;
    @FXML
    private Label deptLabel;
    @FXML
    private Label avancLabel;
    @FXML
    private Label budgetLabel;

    public void initStageData() {
        Stagee stagee = encadrant.getStagee();
        stageLabel.setText(stagee.toString());
        encaLabel.setText(encadrant.toString());
        deptLabel.setText(stagee.getDepartement().toString());
        avancLabel.setText(stagee.getAvancement().toString());
        budgetLabel.setText(stagee.getBudget().toString());
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
        if (!nom.getText().equals("") && !prenom.getText().equals("") && !dateNaissance.getValue().toString().equals("") && !gender.equals("") && !etablissement.getText().equals("") && !niveau.getText().equals("") && !filiere.getText().equals("") && !telephone.getText().equals("") && !adresse.getText().equals("") && !email.getText().equals("")) {
            Stagiaire stagiaire = new Stagiaire(nom.getText(), prenom.getText(), DateUtil.convert(dateNaissance.getValue().toString()), gender, etablissement.getText(), new Integer(niveau.getText()), filiere.getText(), telephone.getText(), adresse.getText(), email.getText(), encadrant.getStagee(), encadrant.getDepartement(), encadrant);
            return stagiaire;
        }
        alert(actionEvent);
        return null;
    }

    private void initHelper() {
        stagiaireFxHelper = new StagiaireFxHelper(stagiareTableView);
        stagiaireFxHelper1 = new StagiaireFxHelper(stagiareTableView1, stagiaireFacade.findByEnca(encadrant));
        tacheFxHelper = new TacheFxHelper(tacheTableView, tacheFacade.findByStage(this.encadrant.getStagee()));
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

        nomRech.setText("");
        prenomRech.setText("");
        rb1Rech.setSelected(false);
        rb2Rech.setSelected(false);

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
    private TabPane stagiairePane;

    @FXML
    public void edittab(ActionEvent actionEvent) {
        stagiairePane.getSelectionModel().select(4);
    }

    @FXML
    public void ajouttab(ActionEvent actionEvent) {
        stagiairePane.getSelectionModel().select(2);
    }

    @FXML
    public void recherchtab(ActionEvent actionEvent) {
        stagiairePane.getSelectionModel().select(3);
    }

    @FXML
    public void menuStagtab(ActionEvent actionEvent) {
        stagiairePane.getSelectionModel().select(1);
    }

    @FXML
    public void menuTachetab(ActionEvent actionEvent) {
        stagiairePane.getSelectionModel().select(5);
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
//
//    @FXML
//    public void stage(ActionEvent actionEvent) throws IOException {
//        Launcher.forward(actionEvent, "StageView.fxml", this.getClass());
//    }
//
//    @FXML
//    public void departement(ActionEvent actionEvent) throws IOException {
//        Launcher.forward(actionEvent, "DepartementView.fxml", this.getClass());
//    }
//
//    @FXML
//    public void encadrant(ActionEvent actionEvent) throws IOException {
//        Launcher.forward(actionEvent, "EncadrantView.fxml", this.getClass());
//    }
//
//    @FXML
//    public void tache(ActionEvent actionEvent) throws IOException {
//        ViewLauncher.forward(actionEvent, "TacheView.fxml", this.getClass());
//    }

    /*
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        initHelper();
        initStageData();
    }

}
