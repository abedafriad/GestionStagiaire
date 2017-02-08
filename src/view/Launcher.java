/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.scene.Node;

/**
 *
 * @author pc&
 */
public class Launcher extends Application {

    private static Parent root;
    private static Stage stage;

    @Override
    public void start(Stage stage) throws Exception {
        Launcher.stage = stage;
        showView("Login.fxml", this.getClass());
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    public static void forward(ActionEvent actionEvent, String pageName, Class myClass) throws IOException {
        Parent parent = FXMLLoader.load(myClass.getResource(pageName));
        Scene scene = new Scene(parent);
        Stage app_stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        app_stage.hide();
        app_stage.setScene(scene);
        app_stage.show();
        
    }
    
    public static void showView(String pathView, Class myClass) throws IOException {
        if (!pathView.endsWith("fxml")) {
            pathView += ".fxml";
        }
        root = FXMLLoader.load(myClass.getResource(pathView));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public static Parent getRoot() {
        return root;
    }

    public static void setRoot(Parent root) {
        Launcher.root = root;
    }

    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Stage stage) {
        Launcher.stage = stage;
    }

}
