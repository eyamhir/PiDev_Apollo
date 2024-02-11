package org.example.interfaces;

import java.io.IOException;
import java.net.Socket;

public interface Interface_Serveur_Socket_Conversation extends  Interface_Serveur_Socket{
    void gererConversation(Socket socketConversation) throws IOException;
}

