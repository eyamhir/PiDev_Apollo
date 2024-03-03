package  org.example.model;
import org.example.services.Service_Conversation;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Server {


    private ServerSocket serverSocket;
    private Socket socket;
    private static Server server;
    public BufferedWriter writer;
    public BufferedReader reader;
    private volatile boolean running = true;
    private Service_Conversation serviceConversation = new Service_Conversation();
    private List<UtilisateurHandler> clients = new ArrayList<>();

    public Server() throws IOException {
        serverSocket = new ServerSocket(3003);
        System.out.println("Le serveur démarre sur le port " + serverSocket.getLocalPort());
    }
    public boolean isRunning() {
        return running;
    }

    public  void accepterConnexions() {
        while (running) {
            try {
                socket = serverSocket.accept();
                System.out.println("Client connecté : " + socket.getInetAddress() + ":" + socket.getPort());
                writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                System.out.println("writer ok");
                reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                System.out.println("Flux d'entrée/sortie initialisés avec succès.");

                UtilisateurHandler clientHandler = new UtilisateurHandler(socket, clients);
                clients.add(clientHandler);
                clientHandler.recevoirMessages(); // Lancer la réception de messages pour ce client
            } catch (IOException e) {
                System.out.println("Erreur lors de l'acceptation de la connexion : " + e.getMessage());
            }
        }
    }
  /*  public void arreterServeur() throws SQLException {
        running = false;
        Conversation conversationEnCours = serviceConversation.recupererConversationEnCours();

        if (conversationEnCours != null) {
            // Définir la date actuelle comme date de fin de la conversation en cours
            java.sql.Date dateFin = new java.sql.Date(new java.util.Date().getTime()); // Utilise la date actuelle
            serviceConversation.mettreAJourDateFinConversationEnCours(dateFin);
        }
        try {
            if (serverSocket != null && !serverSocket.isClosed()) {
                writer.close();
                reader.close();
                serverSocket.close();
                System.out.println("Le serveur a été arrêté.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/
  public void arreterServeur() throws SQLException {
      running = false;
      Conversation conversationEnCours = serviceConversation.recupererConversationEnCours();

      if (conversationEnCours != null) {
          // Définir la date actuelle comme date de fin de la conversation en cours
          java.util.Date dateFin = new java.util.Date(); // Utilise la date actuelle
          java.sql.Date sqlDateFin = new java.sql.Date(dateFin.getTime()); // Convertir en java.sql.Date
          serviceConversation.mettreAJourDateFinConversationEnCours(sqlDateFin);
      }
      try {
          if (serverSocket != null && !serverSocket.isClosed()) {
              writer.close();
              reader.close();
              serverSocket.close();
              System.out.println("Le serveur a été arrêté.");
          }
      } catch (IOException e) {
          e.printStackTrace();
      }
  }

}
