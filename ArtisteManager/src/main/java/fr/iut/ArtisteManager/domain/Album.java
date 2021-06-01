package fr.iut.ArtisteManager.domain;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Document(collection = "albums")
public class Album {

    @Id
    private ObjectId _id;

    private String titre;

    private String couverture;

    private Date dateDeParution;

    private ObjectId artiste_id;

    private Artiste artiste;

    private List<Musique> musiques;

    public Album() {
    }

    public Album(String titre, String couverture, Date dateDeParution, ObjectId artiste_id, Artiste artiste, List<Musique> musiques) {
        this.titre = titre;
        this.couverture = couverture;
        this.dateDeParution = dateDeParution;
        this.artiste_id = artiste_id;
        this.artiste = artiste;
        this.musiques = musiques;
    }

    public ObjectId getId() {
        return _id;
    }

    public void setId(ObjectId id) {
        this._id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getCouverture() {
        return couverture;
    }

    public void setCouverture(String couverture) {
        this.couverture = couverture;
    }

    public Date getDateParution() {
        return dateDeParution;
    }

    public void setDateParution(Date dateDeParution) {
        this.dateDeParution = dateDeParution;
    }

    public ObjectId getArtiste_id() {
        return artiste_id;
    }

    public void setArtiste_id(ObjectId artiste_id) {
        this.artiste_id = artiste_id;
    }

    public Artiste getArtiste() {
        return artiste;
    }

    public void setArtiste(Artiste artiste) {
        this.artiste = artiste;
    }

    public List<Musique> getMusiques() {
        return musiques;
    }

    public void setMusiques(List<Musique> musiques) {
        this.musiques = musiques;
    }
}
