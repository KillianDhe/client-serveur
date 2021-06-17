package fr.iut.ArtisteManager.domain;

public class Contact {

    private String numeroTelephoneFixe;

    private String numeroTelephonePortable;

    public Contact() {
    }

    public Contact(String numeroTelephoneFixe, String numeroTelephonePortable) {
        this.numeroTelephoneFixe = numeroTelephoneFixe;
        this.numeroTelephonePortable = numeroTelephonePortable;
    }

    public String getNumeroTelephoneFixe() {
        return numeroTelephoneFixe;
    }

    public void setNumeroTelephoneFixe(String numeroTelephoneFixe) {
        this.numeroTelephoneFixe = numeroTelephoneFixe;
    }

    public String getNumeroTelephonePortable() {
        return numeroTelephonePortable;
    }

    public void setNumeroTelephonePortable(String numeroTelephonePortable) {
        this.numeroTelephonePortable = numeroTelephonePortable;
    }
}
