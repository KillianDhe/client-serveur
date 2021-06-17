package fr.iut.ArtisteManager.domain;

import java.time.Duration;

public class Musique {

    private String titre;

    private int duree;

    private int nbEcoutes;

    public Musique() {
    }

    public Musique(String titre, int duree, int nbEcoutes) {
        this.titre = titre;
        this.duree = duree;
        this.nbEcoutes = nbEcoutes;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public int getDuree() {
        return duree;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    public int getNbEcoutes() {
        return nbEcoutes;
    }

    public void setNbEcoutes(int nbEcoutes) {
        this.nbEcoutes = nbEcoutes;
    }
}