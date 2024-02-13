package tn.esprit.apollogui.services;

public interface IService {
    void ajouter(T t);

    void modifier(T t);

    void supprimer(int id);

    List<T> recuperer();

}
