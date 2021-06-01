package fr.iut.ArtisteManager.domain;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Document(collection = "artistes")
public class Artiste {

    @Id
    private ObjectId _id;

    private String pseudo;

    private String nom;

    private String prenom;

    private Date dateDeNaissance;

    private Distributeur distributeur;

    public Artiste() {
    }

    public Artiste(String pseudo, String nom, String prenom, Date dateDeNaissance,Distributeur distributeur) {
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


    public ObjectId get_id() {
        return _id;
    }

    public String getPseudo() {
        return pseudo;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public Date getDateDeNaissance() {
        return dateDeNaissance;
    }

    public void set_id(ObjectId _id) {
        this._id = _id;
    }

    public Distributeur getDistributeur() {
        return distributeur;
    }

    public void setDistributeur(Distributeur distributeur) {
        this.distributeur = distributeur;
    }
}
