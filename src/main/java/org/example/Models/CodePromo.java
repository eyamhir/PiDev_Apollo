package org.example.Models;

import java.time.LocalDate;
import java.util.Date;

public class CodePromo {
    private int id_CodePromo;
    private String code;
    private LocalDate dateExpiration;
    private int id_utilisteur;



    public CodePromo(String code, LocalDate dateExpiration, int id_utilisteur) {
        this.code = code;
        this.dateExpiration = dateExpiration;
        this.id_utilisteur = id_utilisteur;
    }

    public int getId_utilisteur() {
        return id_utilisteur;
    }

    public void setId_utilisteur(int id_utilisteur) {
        this.id_utilisteur = id_utilisteur;
    }

    public CodePromo(int id_CodePromo, String code, LocalDate dateExpiration, int id_utilisteur) {
        this.id_CodePromo = id_CodePromo;
        this.code = code;
        this.dateExpiration = dateExpiration;
        this.id_utilisteur = id_utilisteur;
    }

    public CodePromo() {
    }

    public CodePromo(int id_CodePromo, String code, LocalDate dateExpiration) {
        this.id_CodePromo = id_CodePromo;
        this.code = code;
        this.dateExpiration = dateExpiration;
    }

    public CodePromo(String code, LocalDate dateExpiration) {
        this.code = code;
        this.dateExpiration = dateExpiration;
    }

    public int getId_CodePromo() {
        return id_CodePromo;
    }

    public void setId_CodePromo(int id_CodePromo) {
        this.id_CodePromo = id_CodePromo;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LocalDate getDateExpiration() {
        return dateExpiration;
    }

    public void setDateExpiration(LocalDate dateExpiration) {
        this.dateExpiration = dateExpiration;
    }

    @Override
    public String toString() {
        return "CodePromo{" +
                "id_CodePromo=" + id_CodePromo +
                ", code='" + code + '\'' +
                ", dateExpiration=" + dateExpiration +
                ", id_utilisteur=" + id_utilisteur +
                '}';
    }
}

