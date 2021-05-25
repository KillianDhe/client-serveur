package fr.iut.ArtisteManager.domain;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

import java.util.Date;
import java.util.List;

public class Artiste {

    @Id
    private ObjectId _id;

    private String pseudo;

    private String nom;

    private String prenom;

    private Date dateDeNaissance;

    private List<Distributeur> distributeur;

    public Artiste() {
    }

    public Artiste(String pseudo, String nom, String prenom, Date dateDeNaissance, List<Distributeur> distributeur) {
        this.pseudo = pseudo;
        this.nom = nom;
        this.prenom = prenom;
        this.dateDeNaissance = dateDeNaissance;
        this.distributeur = distributeur;
    }

    public void setId(ObjectId id) {
        this._id = id;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setDateDeNaissance(Date dateDeNaissance) {
        this.dateDeNaissance = dateDeNaissance;
    }

    public void setDistributeur(List<Distributeur> distributeur) {
        this.distributeur = distributeur;
    }
}
