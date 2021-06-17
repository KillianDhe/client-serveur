package fr.iut.ArtisteManager.domain;

import java.util.Date;
import java.util.List;

public class ArtisteWithAlbum extends Artiste{
    private List<Album> albums;

    public ArtisteWithAlbum() {
    }

    public ArtisteWithAlbum(List<Album> albums) {
        this.albums = albums;
    }

    public ArtisteWithAlbum(String pseudo, String nom, String prenom, Date dateDeNaissance, Distributeur distributeur, Identite identite, List<Album> albums) {
        super(pseudo, nom, prenom, dateDeNaissance, distributeur, identite);
        this.albums = albums;
    }

    public List<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(List<Album> albums) {
        this.albums = albums;
    }
}
