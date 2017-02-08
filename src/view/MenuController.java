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
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import util.Session;

/**
 * FXML Controller class
 *
 * @author Abed
 */
public class MenuController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private AnchorPane user;
    @FXML
    private AnchorPane root;
    @FXML
    private Label encadrant;
    @FXML
    private Label encadrantRoot;

    Encadrant e = (Encadrant) Session.getAttribut("encaConnect");
    
    
    public void init(){
        
        if (e.getRoot()==1){
            encadrantRoot.setText(e.getNom()+" "+e.getPrenom()+" SuperUser");
            user.setVisible(false);
            root.setVisible(true);
        }else{
            encadrant.setText(e.getNom()+" "+e.getPrenom());
            user.setVisible(true);
            root.setVisible(false);
        }
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
    public void departement(ActionEvent actionEvent) throws IOException {
        Launcher.forward(actionEvent, "DepartementView.fxml", this.getClass());
    }
    @FXML
    public void home(ActionEvent actionEvent) throws IOException {
        Launcher.forward(actionEvent, "Login.fxml", this.getClass());
    }

    @FXML
    public void encadrant(ActionEvent actionEvent) throws IOException {
        Launcher.forward(actionEvent, "EncadrantView.fxml", this.getClass());
    }

    @FXML
    public void tache(ActionEvent actionEvent) throws IOException {
        Launcher.forward(actionEvent, "TacheView.fxml", this.getClass());
    }
    @FXML
    public void edit(ActionEvent actionEvent) throws IOException {
        Launcher.forward(actionEvent, "EditProfil.fxml", this.getClass());
    }
    
    @FXML
    public void monStage(ActionEvent actionEvent) throws IOException {
        Launcher.forward(actionEvent, "MonStage.fxml", this.getClass());
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        init();
    }

}
