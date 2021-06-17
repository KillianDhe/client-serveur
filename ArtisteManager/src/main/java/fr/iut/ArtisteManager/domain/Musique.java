package fr.iut.ArtisteManager.domain;

import java.time.Duration;

/**
 * Classe qui réprésente une musique dans la base de données (directement à l'intérieur des albums)
 */
public class Musique {

    /**
     * Titre d'une musique
     */
    private String titre;

    /**
     * Durée d'une lusique
     */
    private int duree;

    /**
     * Nombre d'écoutes d'une musique
     */
    private int nbEcoutes;

    /**
     * Constructeur par défaut d'une musique
     */
    public Musique() {
    }

    /**
     * Constructeur d'une musique
     * @param titre = titre de la musique
     * @param duree = durée de la musique
     * @param nbEcoutes = nombre d'écoutes de la musique
     */
    public Musique(String titre, int duree, int nbEcoutes) {
        this.titre = titre;
        this.duree = duree;
        this.nbEcoutes = nbEcoutes;
    }

    /**
     * Getter du titre de la musique
     * @return titre
     */
    public String getTitre() {
        return titre;
    }

    /**
     * Setter du titre de la musique
     * @param titre = titre de musique à affecter
     */
    public void setTitre(String titre) {
        this.titre = titre;
    }

    /**
     * Getter de la durée d'une musique
     * @return duree
     */
    public int getDuree() {
        return duree;
    }

    /**
     * Setter de la durée d'une musique
     * @param duree = durée à affecter à la musique
     */
    public void setDuree(int duree) {
        this.duree = duree;
    }

    /**
     * Getter du nombre d'écoutes de la musique
     * @return nbEcoutes
     */
    public int getNbEcoutes() {
        return nbEcoutes;
    }

    /**
     * Setter du nombre d'écoutes de la musique
     * @param nbEcoutes = nombre d'écoutes à affecter à la musique
     */
    public void setNbEcoutes(int nbEcoutes) {
        this.nbEcoutes = nbEcoutes;
    }
}