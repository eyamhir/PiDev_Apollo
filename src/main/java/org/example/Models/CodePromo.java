package org.example.Models;

import java.util.Date;

public class CodePromo {
    private int id_CodePromo;
    private String code;
    private Date dateExpiration;

    public CodePromo() {
    }

    public CodePromo(int id_CodePromo, String code, Date dateExpiration) {
        this.id_CodePromo = id_CodePromo;
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

    public Date getDateExpiration() {
        return dateExpiration;
    }

    public void setDateExpiration(Date dateExpiration) {
        this.dateExpiration = dateExpiration;
    }

    @Override
    public String toString() {
        return "CodePromo{" +
                "id_CodePromo=" + id_CodePromo +
                ", code='" + code + '\'' +
                ", dateExpiration=" + dateExpiration +
                '}';
    }
}
