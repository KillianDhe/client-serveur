package fr.iut.ArtisteManager.domain;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

public class Distributeur {

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
