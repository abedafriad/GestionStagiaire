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
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import service.DepartementFacade;
import service.EncadrantFacade;
import service.StageeFacade;
import service.StagiaireFacade;

/**
 * FXML Controller class
 *
 * @author Abed
 */
public class CreeCompteController implements Initializable {

    /**
     * Declaration of class attributes
     */
    @FXML
    private TextField login;

    @FXML
    private PasswordField passwd;

    @FXML
    private TextField nom;

    @FXML
    private TextField prenom;

    @FXML
    private TextArea adresse;

    @FXML
    private TextField telephone;

    @FXML
    private TextField email;

    @FXML
    private RadioButton rbM = new RadioButton();

    @FXML
    private RadioButton rbF = new RadioButton();

    @FXML
    private ComboBox<Departement> departementComboBox;

    @FXML
    private ComboBox<Stagee> stageeComboBox;

    StagiaireFacade stagiaireFacade = new StagiaireFacade();
    DepartementFacade departementFacade = new DepartementFacade();
    StageeFacade stageFacade = new StageeFacade();
    EncadrantFacade encadrantFacade = new EncadrantFacade();
    EncadrantFxHelper encadrantFxHelper;

    @FXML
    public void ajout(ActionEvent ae) throws IOException {
        Encadrant encadrant = getParam(ae);
        if (encadrant != null) {
            encadrantFacade.create(encadrant);
            Launcher.forward(ae, "Login.fxml", this.getClass());
        }
    }

    @FXML
    private TabPane pane;

    @FXML
    public void next(ActionEvent actionEvent) {
        if (!login.getText().equals("") && !passwd.getText().equals("")) {
            int res = encadrantFacade.findByLogin(login.getText());
            if (res < 0) {
                pane.getSelectionModel().selectNext();
            } else {
                alertLoginExist(actionEvent);
            }
        } else {
            alertChampsVide(actionEvent);
        }

    }

    @FXML
    public void back(ActionEvent actionEvent) {
        pane.getSelectionModel().selectPrevious();
    }

    @FXML
    public void quit(ActionEvent actionEvent) throws IOException {
        Launcher.forward(actionEvent, "Login.fxml", this.getClass());
    }

    @FXML
    public void actuComboBox( ActionEvent actionEvent) {
        stageeComboBox.setItems(FXCollections.observableArrayList(stageFacade.findByDep(departementComboBox.getValue())));
    }

    private Encadrant getParam(ActionEvent ae) {
        String gender = "";
        if (rbM.isSelected()) {
            gender = "M";
        } else if (rbF.isSelected()) {
            gender = "F";
        }

        if (!nom.getText().equals("") && !prenom.getText().equals("") && !adresse.getText().equals("") && !telephone.getText().equals("") && !email.getText().equals("") && !gender.equals("") && !departementComboBox.getValue().equals("") && !stageeComboBox.getValue().equals("")) {
            Encadrant encadrant = new Encadrant(login.getText(), passwd.getText(), nom.getText(), prenom.getText(), adresse.getText(), telephone.getText(), email.getText(), gender, departementComboBox.getValue(), stageeComboBox.getValue());
            encadrant.setBlocked(1);
            encadrant.setRoot(0);
            return encadrant;
        } else {
            alertChampsVide(ae);
            return null;
        }
    }

    private void alertChampsVide(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Quelque Champs sont Vide!");
        alert.setContentText("Assurez-vouz qu'aucun champs est vide!! ");
        alert.setHeaderText("ATTENTION!");
        alert.showAndWait();
    }

    private void alertLoginExist(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Erreur de Login");
        alert.setContentText("Ce Login existe Déja... Essayer un autre!");
        alert.setHeaderText("CHANGER CE LOGIN!");
        alert.showAndWait();
    }

    private void initComboBox() {
        departementComboBox.setItems(FXCollections.observableArrayList(departementFacade.findAll()));

    }

    private void actualiser() {
        nom.setText("");
        prenom.setText("");
        adresse.setText("");
        telephone.setText("");
        email.setText("");
        rbM.setSelected(false);
        rbF.setSelected(false);
        departementComboBox.setValue(null);
        stageeComboBox.setValue(null);

    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initComboBox();
        // TODO
    }

}
