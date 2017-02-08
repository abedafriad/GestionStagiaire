/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import bean.Encadrant;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import service.EncadrantFacade;
import util.Session;

/**
 * FXML Controller class
 *
 * @author Abed
 */
public class LoginController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TextField login;

    @FXML
    private PasswordField password;

    EncadrantFacade encadrantFacade = new EncadrantFacade();

    public void seConnect(ActionEvent actionEvent) throws IOException {
        if (!login.getText().equals("") && !password.getText().equals("")) {
            Object res[] = encadrantFacade.seConnecter(login.getText(), password.getText());
            int resInt = (int) res[0];
            if (resInt == 1) {
                Encadrant e = (Encadrant) res[1];
                Session.clear();
                Session.setAttribut(e, "encaConnect");
                Launcher.forward(actionEvent, "Menu.fxml", this.getClass());
            } else if (resInt == -1) {
                alertLogin(actionEvent);
            } else if (resInt == -2) {
                alertPasswd(actionEvent);
                password.setText("");

            } else if (resInt == -3) {
                alertBlock(actionEvent);
                password.setText("");
            }
        } else {
            alertChamps(actionEvent);
        }
    }

    private void alertChamps(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Champs Vide!");
        alert.setContentText("Il est necessaire de remplire tout les champs pour se connecter!");
        alert.setHeaderText("ATTENTION!");
        alert.showAndWait();
    }

    private void alertPasswd(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Mot de passe incorrect!");
        alert.setContentText("");
        alert.setHeaderText("MOT DE PASSE INCORRECT!");
        alert.showAndWait();
    }
    private void alertBlock(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Compte Bloquée!");
        alert.setContentText("Votre Compte est Bloquee!!\n");
        alert.setHeaderText("BLOQUEE!");
        alert.showAndWait();
    }

    private void alertLogin(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Compte non Trouvée! ");
        alert.setContentText("Aucun compte a ce Login!!.. \nSi vous avez pas de compte vous pouvez le crée en appuiant le boutton en bas");
        alert.setHeaderText("DESOLEE!");
        alert.showAndWait();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private TabPane pane;

    public void toLogin(ActionEvent actionEvent) {
        pane.getSelectionModel().selectNext();
    }

    public void creeCompte(ActionEvent actionEvent) throws IOException {
        Launcher.forward(actionEvent, "CreeCompte.fxml", this.getClass());
    }

}
