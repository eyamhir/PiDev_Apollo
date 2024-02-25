package org.example.interfaces;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.net.Socket;
import java.nio.Buffer;

public interface Interface_Serveur_Socket  {
    void demarrerServeur() throws IOException;
    void arreterServeur(Socket socket, BufferedWriter bufferedWriter, BufferedReader bufferedReader) throws IOException;
    void gererConnexions() throws IOException;
}
