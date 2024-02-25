package tn.esprit.test;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import tn.esprit.controllers.AjouterCommande;

import java.io.IOException;

public class MainFX extends Application {
    FXMLLoader myloader = null; Scene myScene = null; Stage prevStage = null; static Stage stg= new Stage();
    /*public void start(Stage stage) throws IOException {
       this.stg =stage;
        try{
            myloader = new FXMLLoader();
            myloader = new FXMLLoader(MainFX.class.getResource("/tn.esprit/AjouterPayment.fxml"));
            Parent root = myloader.load();
            AjouterPayment ajouterPayment = myloader.getController();
            ajouterPayment.setPaymentStage(stage);
            myScene = new Scene(root);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setTitle("Liste");
            stage.setScene(myScene);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }*/


    public void start(Stage stage) throws IOException {
        this.stg =stage;
        try{
            myloader = new FXMLLoader();
            myloader = new FXMLLoader(MainFX.class.getResource("/tn.esprit/AjouterCommande.fxml"));
            Parent root = myloader.load();
            AjouterCommande ajouterCommande = myloader.getController();
            ajouterCommande.setCommandeStage(stage);
            myScene = new Scene(root);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setTitle("Liste");
            stage.setScene(myScene);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public static void main(String[] args) {
        launch(args);
    }
    public void changeStage(String path) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(path));
        stg.getScene().setRoot(root);
    }





   /* @Override
    public void start(Stage primaryStage) throws IOException {
        //Parent root = FXMLLoader.load(getClass().getResource("/tn.esprit/AjouterPayment.fxml"));
        Parent root = FXMLLoader.load(getClass().getResource("/tn.esprit/AjouterCommande.fxml"));
        primaryStage.setTitle("Liste des Commandes");
        primaryStage.setScene(new Scene(root,1000,700));
        primaryStage.show();
    }*/
}
