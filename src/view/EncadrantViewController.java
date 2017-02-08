/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import bean.Departement;
import bean.Encadrant;
import bean.Stagee;
import helper.EncadrantFxHelper;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import service.DepartementFacade;
import service.EncadrantFacade;
import service.StageeFacade;
import service.StagiaireFacade;

/**
 * FXML Controller class
 *
 * @author Abed
 */
public class EncadrantViewController implements Initializable {

    /**
     * Declaration of class attributes
     */
    @FXML
    private TableView<Encadrant> encaAttTableView = new TableView<>();

    StagiaireFacade stagiaireFacade = new StagiaireFacade();
    DepartementFacade departementFacade = new DepartementFacade();
    StageeFacade stageFacade = new StageeFacade();
    EncadrantFacade encadrantFacade = new EncadrantFacade();
    EncadrantFxHelper encadrantFxHelper;
    EncadrantFxHelper encadrantFxHelperAtt;

    @FXML
    private TextField nomRec;
    @FXML
    private TextField loginRec;

    @FXML
    private TextField prenomRec;

    @FXML
    private RadioButton rbMRech = new RadioButton();

    @FXML
    private RadioButton rbFRech = new RadioButton();

    @FXML
    private ComboBox<Departement> departementRechComboBox;

    @FXML
    private ComboBox<Stagee> stageeRechComboBox;
    @FXML
    private TableView encaTableView;

    /*
     * Functions
     */
//    @FXML
//    public void ajout(ActionEvent ae) {
//        Encadrant encadrant = getParam(ae);
//        if (encadrant != null) {
//            encadrantFacade.create(encadrant);
//            actualiser();
//        }
//    }
    @FXML
    public void delete(ActionEvent ae) {
        Encadrant encadrant = encadrantFxHelper.getSelected();
        if (encadrant != null) {
            if (encadrant.getRoot() != 1) {
                encadrantFacade.remove(encadrant);
                encadrantFxHelper.setList(encadrantFacade.findAll());
            }
        } else {
            alertSupp(ae);
        }
    }

    @FXML
    public void decline(ActionEvent ae) {
        Encadrant encadrant = encadrantFxHelperAtt.getSelected();
        if (encadrant != null) {
            encadrantFacade.remove(encadrant);
            encadrantFxHelper.setList(encadrantFacade.findByBlock());
        }
    }

    @FXML
    public void accept(ActionEvent actionEvent) {
        Encadrant encadrant = encadrantFxHelperAtt.getSelected();
        if (encadrant != null) {
            encadrant.setBlocked(0);
            encadrantFacade.edit(encadrant);
            encadrantFxHelperAtt.setList(encadrantFacade.findByBlock());
        }
    }

    @FXML
    public void block(ActionEvent actionEvent) {
        Encadrant encadrant = encadrantFxHelper.getSelected();
        if (encadrant != null) {
            if (encadrant.getRoot() == 0) {
                if (encadrant.getBlocked() == 0) {
                    encadrant.setBlocked(1);
                    encadrantFacade.edit(encadrant);
                } else {
                    alertblock(actionEvent);
                }
            } else {
                alertblockRoot(actionEvent);
            }
        }
    }

    @FXML
    public void setRoot(ActionEvent actionEvent) {
        Encadrant encadrant = encadrantFxHelper.getSelected();
        if (encadrant != null) {
            if (encadrant.getBlocked() == 0) {
                if(encadrant.getRoot()==0){
                encadrant.setRoot(1);
                encadrantFacade.edit(encadrant);
                }else {
                    alertSetRoot(actionEvent);
                }
            } else {
                alertSetRootblock(actionEvent);
            }
        }
    }

    @FXML
    public void recherch(ActionEvent ae) {
        String gender = "";
        if (rbMRech.isSelected()) {
            gender = "M";
        } else if (rbFRech.isSelected()) {
            gender = "F";
        }
        encadrantFxHelper.setList(encadrantFacade.recherchEncadrants(loginRec.getText(), nomRec.getText(), prenomRec.getText(), gender, stageeRechComboBox.getValue(), departementRechComboBox.getValue()));
        actualiser();
    }

    /**
     * Functions get & Initialize parametres
     */
    private void alertSupp(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setContentText("Vous Ne Pouvez Pas Supprimer Ce Compte");
        alert.setHeaderText("ATTENTION!");
        alert.showAndWait();
    }

    private void alertblockRoot(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("ERREUR");
        alert.setContentText("Vous Ne Pouvez Pas Bloquer Ce Compte");
        alert.setHeaderText("ATTENTION!");
        alert.showAndWait();
    }

    private void alertblock(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("ERREUR");
        alert.setContentText("Ce Compte Est Deja Bloquer");
        alert.setHeaderText("ATTENTION!");
        alert.showAndWait();
    }

    private void alertSetRootblock(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("ERREUR");
        alert.setContentText("Ce Compte Est Bloquer..\nDebloquer le avant de le Administrer!");
        alert.setHeaderText("ATTENTION!");
        alert.showAndWait();
    }
    private void alertSetRoot(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("ERREUR");
        alert.setContentText("Ce Compte Est Deja Administrateur!");
        alert.setHeaderText("ATTENTION!");
        alert.showAndWait();
    }

//    private Encadrant getParam(ActionEvent ae) {
//        String gender = "";
//        if (rbM.isSelected()) {
//            gender = "M";
//        } else if (rbF.isSelected()) {
//            gender = "F";
//        }
//        if (!nom.getText().equals("") && !prenom.getText().equals("") && !adresse.getText().equals("") && !telephone.getText().equals("") && !email.getText().equals("") && !gender.equals("") && !departementComboBox.getValue().equals("") && !stageeComboBox.getValue().equals("")) {
//            Encadrant encadrant = new Encadrant(nom.getText(), prenom.getText(), adresse.getText(), telephone.getText(), email.getText(), gender, departementComboBox.getValue(), stageeComboBox.getValue());
//            return encadrant;
//        } else {
//            alert(ae);
//            return null;
//        }
//
//    }
    private void initComboBox() {
//        departementComboBox.setItems(FXCollections.observableArrayList(departementFacade.findAll()));
//        stageeComboBox.setItems(FXCollections.observableArrayList(stageFacade.findAll()));
//        departementComboBoxEd.setItems(FXCollections.observableArrayList(departementFacade.findAll()));
//        stageeComboBoxEd.setItems(FXCollections.observableArrayList(stageFacade.findAll()));
        departementRechComboBox.setItems(FXCollections.observableArrayList(departementFacade.findAll()));
        stageeRechComboBox.setItems(FXCollections.observableArrayList(stageFacade.findAll()));
    }

    private void initHelper() {
        encadrantFxHelper = new EncadrantFxHelper(encaTableView);
        encadrantFxHelperAtt = new EncadrantFxHelper(encaAttTableView);
        encadrantFxHelperAtt.setList(encadrantFacade.findByBlock());
    }

    private void actualiser() {
//        nom.setText("");
//        prenom.setText("");
//        adresse.setText("");
//        telephone.setText("");
//        email.setText("");
//        rbM.setSelected(false);
//        rbF.setSelected(false);
//        departementComboBox.setValue(null);
//        stageeComboBox.setValue(null);
        nomRec.setText("");
        loginRec.setText("");
        prenomRec.setText("");
        rbMRech.setSelected(false);
        rbFRech.setSelected(false);
        departementRechComboBox.setValue(null);
        stageeRechComboBox.setValue(null);

    }

    /*
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        initHelper();
        initComboBox();
    }

    @FXML
    private TabPane encadrantPane;

    /*
    ****
    ****** Main Menu Items
     */

    @FXML
    public void mainmenu(ActionEvent actionEvent) throws IOException {
        Launcher.forward(actionEvent, "Menu.fxml", this.getClass());
    }


}
