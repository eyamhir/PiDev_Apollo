package tn.esprit.apollogui.test;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import static javafx.application.Application.launch;

/* public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(mainFX.class.getResource("/tn/esprit/apollogui/AjouterEvenement.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}

 */
public class mainFX extends Application{

    public static void main(String[] args){
        launch(args);
        }


    public void start(Stage primaryStage){
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/tn/esprit/apollogui/ParticiperEvenement.fxml"));
        try{
        Parent root=loader.load();
        Scene scene=new Scene(root);
        primaryStage.setTitle("Ajouter evenement");
        primaryStage.setScene(scene);
        primaryStage.show();
        } catch(IOException e){
            throw new RuntimeException(e);

        }
        }
        }