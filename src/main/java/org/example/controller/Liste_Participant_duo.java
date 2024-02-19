package org.example.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;
import org.example.model.Participant;
import org.example.services.Service_Participant;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class Liste_Participant_duo implements Initializable {

    @FXML
    private ListView<Participant> ParticipantListView;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Initialise la ListView avec les données des participants
        Service_Participant serviceParticipant = new Service_Participant();
        try {
            List<Participant> participants = serviceParticipant.getParticipants(1); // Modifier 1 avec l'ID de la conversation appropriée
            ParticipantListView.getItems().addAll(participants);

            // Définir comment afficher chaque élément dans la ListView
            ParticipantListView.setCellFactory(new Callback<ListView<Participant>, ListCell<Participant>>() {
                @Override
                public ListCell<Participant> call(ListView<Participant> participantListView) {
                    return new ListCell<Participant>() {
                        @Override
                        protected void updateItem(Participant participant, boolean empty) {
                            super.updateItem(participant, empty);
                            if (participant != null) {
                                setText(participant.getParticipantId() + " | " + participant.getArtisteId() + " | " + participant.getClientId() + " | " + participant.getConversationId());
                            } else {
                                setText(null);
                            }
                        }
                    };
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
