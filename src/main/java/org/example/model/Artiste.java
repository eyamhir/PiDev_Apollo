package org.example.model;

public class Artiste {
    private int artiste_Id;
    private String Specialite_Artistique;
    // Autres attributs spécifiques à un artiste

    // Constructeur
    public Artiste(int artiste_Id, String Specialite_Artistique) {
        this.artiste_Id = artiste_Id;
        this.Specialite_Artistique = Specialite_Artistique;
    }
    public Artiste(){

    }
    public Artiste(String Specialite_Artistique){
        this.Specialite_Artistique=Specialite_Artistique;
    }

    public int getArtisteId() {
        return artiste_Id;
    }
    // GETTER AND SETTER
    public void setArtisteId(int artiste_Id) {
        this.artiste_Id = artiste_Id;
    }

    public String getSpecialite_Artistique() {
        return Specialite_Artistique;
    }

    public void setSpecialite_Artistique(String specialite_Artistique) {
        Specialite_Artistique = specialite_Artistique;
    }

    @Override
    public String toString() {
        return "Artiste{" +
                "artisteId=" + artiste_Id +
                ", Specialite_Artistique='" + Specialite_Artistique + '\'' +
                '}';
    }
}
