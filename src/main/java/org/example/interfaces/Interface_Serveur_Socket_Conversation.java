package org.example.interfaces;

import java.io.IOException;
import java.net.Socket;
import java.sql.SQLException;

public interface Interface_Serveur_Socket_Conversation extends  Interface_Serveur_Socket{
    void gererConversation(Socket socketConversation) throws IOException;
    void gererMessage(Socket socketClient)throws IOException, SQLException;
    void envoyerMessageAUnAutreServeur(String contenu, String adresseServeur, int portServeur) ;
}

