/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import bean.Departement;
import bean.Encadrant;
import bean.Stagee;
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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import service.DepartementFacade;
import service.EncadrantFacade;
import service.StageeFacade;
import service.StagiaireFacade;
import util.Session;

/**
 * FXML Controller class
 *
 * @author Abed
 */
public class EditProfilController implements Initializable {

    @FXML
    private AnchorPane root;
    
    @FXML
    private ImageView rootView;
    

    @FXML
    private TextField password;

    @FXML
    private TextField nomEd;

    @FXML
    private TextField prenomEd;

    @FXML
    private TextField telephoneEd;

    @FXML
    private TextField emailEd;

    @FXML
    private RadioButton rbMEd;

    @FXML
    private RadioButton rbFEd;

    @FXML
    private TextArea adresseEd;

    @FXML
    private ComboBox<Departement> departementComboBoxEd;

    @FXML
    private ComboBox<Stagee> stageeComboBoxEd;

    StagiaireFacade stagiaireFacade = new StagiaireFacade();
    DepartementFacade departementFacade = new DepartementFacade();
    StageeFacade stageFacade = new StageeFacade();
    EncadrantFacade encadrantFacade = new EncadrantFacade();

    Encadrant encadrant = (Encadrant) Session.getAttribut("encaConnect");

    @FXML
    public void edit(ActionEvent ae) throws IOException {

        String gender = "";
        if (rbMEd.isSelected()) {
            gender = "M";
        } else if (rbFEd.isSelected()) {
            gender = "F";
        }
        if (!password.getText().equals("") && !nomEd.getText().equals("") && !prenomEd.getText().equals("") && !adresseEd.getText().equals("") && !telephoneEd.getText().equals("") && !emailEd.getText().equals("") && !gender.equals("") && !departementComboBoxEd.getValue().equals("") && !stageeComboBoxEd.getValue().equals("")) {
            encadrant.setPassword(password.getText());
            encadrant.setNom(nomEd.getText());
            encadrant.setPrenom(prenomEd.getText());
            encadrant.setAdresse(adresseEd.getText());
            encadrant.setTelephone(telephoneEd.getText());
            encadrant.setEmail(emailEd.getText());
            encadrant.setGender(gender);
            encadrant.setDepartement(departementComboBoxEd.getValue());
            encadrant.setStagee(stageeComboBoxEd.getValue());
            encadrantFacade.edit(encadrant);
            Launcher.forward(ae, "Menu.fxml", this.getClass());
        } else {
            alert(ae);
        }

    }

    public void quit(ActionEvent actionEvent) throws IOException {
        Launcher.forward(actionEvent, "Menu.fxml", this.getClass());
    }

    public void getForEdit() {
        if (encadrant != null) {
            password.setText(encadrant.getPassword());
            nomEd.setText(encadrant.getNom());
            prenomEd.setText(encadrant.getPrenom());
            adresseEd.setText(encadrant.getAdresse());
            if (encadrant.getGender().equals("M")) {
                rbMEd.setSelected(true);
            }
            if (encadrant.getGender().equals("F")) {
                rbFEd.setSelected(true);
            }
            emailEd.setText(encadrant.getEmail());
            telephoneEd.setText(encadrant.getTelephone());
            stageeComboBoxEd.getSelectionModel().select(encadrant.getStagee());
            departementComboBoxEd.getSelectionModel().select(encadrant.getDepartement());
        }
    }

    private void alert(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur insertion");
        alert.setContentText("Assurez-vouz qu'aucun champs est vide!! ");
        alert.setHeaderText("ATTENTION!");
        alert.showAndWait();
    }

    @FXML
    public void actuComboBox(ActionEvent actionEvent) {
        stageeComboBoxEd.setItems(FXCollections.observableArrayList(stageFacade.findByDep(departementComboBoxEd.getValue())));
    }

    private void initComboBox() {
        System.out.println("com");
        departementComboBoxEd.setItems(FXCollections.observableArrayList(departementFacade.findAll()));
    }

    private void init() {
        System.out.println("init");
        if (encadrant.getRoot() == 1) {
            root.setVisible(true);
            rootView.setVisible(true);
        } else {
            root.setVisible(false);
            rootView.setVisible(false);
        }
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        initComboBox();
        init();
        getForEdit();
    }

}
