package Esprit.Controller;

import Esprit.Models.Exposition;
import Esprit.Service.ExpositionService;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class Afficherexpo implements Initializable {


        @FXML
        private ListView<Exposition> listView; // Assurez-vous que l'attribut fx:id correspond à celui dans votre fichier FXML

//        @FXML
//        public void modifierService(ActionEvent actionEvent) {
//            // Code pour modifier l'utilisateur sélectionné dans la liste
//            Service selectedService = listView.getSelectionModel().getSelectedItem();
//            if (selectedService != null) {
//                try {
//                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/ServiceFX/Modifier.fxml"));
//                    Parent root = loader.load();
//                    UpdateService controller = loader.getController();
//                    controller.initData(selectedService); // Passer l'utilisateur sélectionné au contrôleur de l'interface de modification
//                    Stage window = (Stage) listView.getScene().getWindow();
//                    window.setScene(new Scene(root));
//                    window.show();
//
//                } catch (IOException e) {
//                    throw new RuntimeException(e);
//                }
//
//            }
//        }


//        @FXML
//        void supprimerService(ActionEvent event) {
//            Service selectedService = listView.getSelectionModel().getSelectedItem();
//            if (selectedService != null) {
//                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
//                alert.setTitle("Confirmation de suppression");
//                alert.setHeaderText(null);
//                alert.setContentText("Êtes-vous sûr de vouloir supprimer cette conversation ?");
//                Optional<ButtonType> result = alert.showAndWait();
//                if (result.isPresent() && result.get() == ButtonType.OK) {
//                    try {
//                        int id_service = selectedService.getId_service();
//                        ServiceService ServiceService = new ServiceService();
//                        ServiceService.supprimer(id_service);
//                        listView.getItems().remove(selectedService);
//                    } catch (SQLException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//
//
//        }


        @Override
        public void initialize(URL location, ResourceBundle resources) {
            ExpositionService expositionService = new ExpositionService();
            List<Exposition> services = null;
            try {
                services = expositionService.reupere_tout();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            listView.setItems(FXCollections.observableArrayList(services));
        }



}
