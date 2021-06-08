package fr.iut.ArtisteManager.domain;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

public class Distributeur {

    /**
     * l'identifiant du sistributeur
     */
    private ObjectId id;

    /**
     * Le nom du distributeur
     */
    private  String nom;

    /**
     * Constructeur par défaut d'un distributeur
     */
    public Distributeur() {
    }

    /**
     * construvcteur de distributeur
     * @param nom Le nom à donner au dfistributeur
     */
    public Distributeur(String nom) {
        this.nom = nom;
    }

    /**
     * Défini l'identifiant du distributeur
     * @return l'identifiant  du distributeur
     */
    public ObjectId getId() {
        return id;
    }

    /**
     * Définit l'identifiant du distributeur
     * @param id l'identifiant du distributeur
     */
    public void setId(ObjectId id) {
        this.id = id;
    }

    /**
     * Obtient le nom du distributeur
     * @return le nom du distribteur
     */
    public String getNom() {
        return nom;
    }

    /**
     * Définit le nom du distributeur
     * @param nom le nom du distributeur
     */
    public void setNom(String nom) {
        this.nom = nom;
    }
}
