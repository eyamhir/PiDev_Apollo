package tn.esprit.apollogui.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import tn.esprit.apollogui.models.evenement;
import tn.esprit.apollogui.services.EvenementService;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;

public class ModifierEvenement {

    @FXML
    private DatePicker Date_debutTF;

    @FXML
    private DatePicker Date_finTF;

    @FXML
    private TextField DescriptionTF;

    @FXML
    private TextField NomTF;

    @FXML
    private TextField TypeTF;

    private final EvenementService evenementService = new EvenementService();
    private evenement evenement;

    public void setEvenement(evenement evenement) {
        this.evenement = evenement;
    }

    public void initData(evenement evenement) {
        NomTF.setText(evenement.getNom());
        TypeTF.setText(evenement.getType());
        DescriptionTF.setText(evenement.getDescription());
        // Assuming Date_debutTF and Date_finTF are DatePicker controls
        // Populate other UI elements as needed...
    }

    @FXML
    void Save(ActionEvent event) {
        try {
            if (allFieldsFilled()) {
                if (evenement != null) {
                    String Nom = NomTF.getText();
                    String Description = DescriptionTF.getText();
                    String Type = TypeTF.getText();
                    Date Date_debut = java.sql.Date.valueOf(Date_debutTF.getValue());
                    Date Date_fin = java.sql.Date.valueOf(Date_finTF.getValue());



                    if (isValidType(Type) && isValidDates(Date_debut, Date_fin)) {
                        if (isAfterToday(Date_debut)) {
                            evenement en = new evenement(
                                    evenement.getId(),
                                    Nom,
                                    Description,
                                    Type,
                                    Date_debutTF.getValue(),
                                    Date_finTF.getValue()
                            );

                            evenementService.modifier(en);

                            showAlert(Alert.AlertType.INFORMATION, "Confirmation", null, "L'encher a été modifié avec succès.");
                        } else {
                            displayErrorDialog("La date de début doit être aujourd'hui ou ultérieure.");
                        }
                    } else {
                        displayErrorDialog("Veuillez saisir des données valides.");
                    }
                } else {
                    displayErrorDialog("L'objet Encher est nul. Impossible de sauvegarder les modifications.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            displayErrorDialog("Une erreur s'est produite lors de la modification de l'encher.");
        } catch (NumberFormatException e) {
            e.printStackTrace();
            displayErrorDialog("Le montant doit être un nombre valide.");
        }
    }

    private void displayErrorDialog(String message) {
        showAlert(Alert.AlertType.ERROR, "Error", null, message);
    }

    private void showAlert(Alert.AlertType alertType, String title, String headerText, String contentText) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        alert.showAndWait();
    }

    private boolean allFieldsFilled() {
        if (Date_debutTF.getValue() == null ||
                Date_finTF.getValue() == null ||
                NomTF.getText().isEmpty() ||
                TypeTF.getText().isEmpty() ||
                DescriptionTF.getText().isEmpty()) {

            displayErrorDialog("Veuillez remplir tous les champs obligatoires.");
            return false;
        }
        return true;
    }

    private boolean isValidType(String type) {
        return type != null && !type.isEmpty() && type.matches("[a-zA-Z]+");
    }

    private boolean isValidDates(Date start, Date end) {
        return start != null && end != null && !end.before(start);
    }

    public void gobackk(ActionEvent event) throws IOException {
        try{
            FXMLLoader loader=new FXMLLoader(getClass().getResource("/tn/esprit/apollogui/AfficherEvenement.fxml"));
            Parent root=loader.load();
            Scene scene=new Scene(root);
            Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(scene);
        }catch(IOException e){
            e.printStackTrace(); // Handle the exception appropriately (e.g., log it)
        }
    }
    private boolean isAfterToday(Date date) {
        LocalDate today = LocalDate.now();
        LocalDate startDate = ((java.sql.Date) date).toLocalDate();
        return startDate.isEqual(today) || startDate.isAfter(today);
    }
}