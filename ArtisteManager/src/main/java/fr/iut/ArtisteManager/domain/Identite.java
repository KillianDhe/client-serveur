package fr.iut.ArtisteManager.domain;

/**
 * Classe représentant l'identité d'une personne, d'un artiste en l'occurence
 */
public class Identite {

    /**
     * Le nom composant l'identité
     */
    private String nom;

    /**
     * Le prénom composant l'identité
     */
    private String prenom;

    /**
     * Obtient le nom de l'identité
     * @return le nom de l'identité
     */
    public String getNom() {
        return nom;
    }

    /**
     * Définit le nom de l'identité
     * @param nom le nom à définir
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Obtient le prénom de l'identité
     * @return le prénom de l'identité
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     * Définit le nom de l'identité
     * @param nom le nom à définir
     */
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
}
