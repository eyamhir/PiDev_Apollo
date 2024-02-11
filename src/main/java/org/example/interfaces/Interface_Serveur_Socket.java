package org.example.interfaces;

import java.io.IOException;

public interface Interface_Serveur_Socket  {
    void demarrerServeur() throws IOException;
    void arreterServeur() throws IOException;
    void gererConnexions() throws IOException;
}
