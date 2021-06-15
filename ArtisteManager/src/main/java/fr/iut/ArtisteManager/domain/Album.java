package fr.iut.ArtisteManager.domain;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

/**
 * Classe qui représente un album dans la base de données
 */
@Document(collection = "albums")
public class Album {

    /**
     * Identifiant d'un album
     */
    @Id
    private ObjectId _id;

    /**
     * Titre d'un album
     */
    private String titre;

    /**
     * Couverture d'un album
     */
    private String couverture;

    /**
     * Date de parution d'un album
     */
    private Date dateDeParution;

    /**
     * Identifiant de l'artiste qui a fait l'album
     */
    private ObjectId artiste_id;

    /**
     * Artiste qui a fait l'album
     */
    private Artiste artiste;

    /**
     * Liste des musiques de l'album
     */
    private List<Musique> musiques;

    /**
     * Constructeur vide d'un album
     */
    public Album() {
    }

    /**
     * Constructeur d'un album
     * @param titre = titre de l'album
     * @param couverture = couverture de l'album
     * @param dateDeParution = date de parution de l'album
     * @param artiste_id = identifiant de l'artiste de l'album
     * @param artiste = artiste de l'album
     * @param musiques = liste des musiques de l'album
     */
    public Album(String titre, String couverture, Date dateDeParution, ObjectId artiste_id, Artiste artiste, List<Musique> musiques) {
        this.titre = titre;
        this.couverture = couverture;
        this.dateDeParution = dateDeParution;
        this.artiste_id = artiste_id;
        this.artiste = artiste;
        this.musiques = musiques;
    }

    /**
     * Getter de l'identifiant de l'album
     * @return _id
     */
    public ObjectId get_id() {
        return _id;
    }

    /**
     * Setter de l'identifiant de l'album
     * @param id = identifiant à affecter
     */
    public void set_id(ObjectId id) {
        this._id = id;
    }

    /**
     * Getter du titre de l'album
     * @return titre
     */
    public String getTitre() {
        return titre;
    }

    /**
     * Setter du titre de l'album
     * @param titre = titre à affecter
     */
    public void setTitre(String titre) {
        this.titre = titre;
    }

    /**
     * Getter de la couverture d'un album
     * @return couverture
     */
    public String getCouverture() {
        return couverture;
    }

    /**
     * Setter de la couverture d'un album
     * @param couverture = couverture à affecter
     */
    public void setCouverture(String couverture) {
        this.couverture = couverture;
    }

    /**
     * Getter de la date de parution de l'album
     * @return dateDeParution
     */
    public Date getDateParution() {
        return dateDeParution;
    }

    /**
     * Setter de la date de parution de l'album
     * @param dateDeParution = date de parution à affecter
     */
    public void setDateParution(Date dateDeParution) {
        this.dateDeParution = dateDeParution;
    }

    /**
     * Getter de l'identifiant de l'artiste de l'album
     * @return artiste_id
     */
    public ObjectId getArtiste_id() {
        return artiste_id;
    }

    /**
     * Setter de l'identifiant de l'artiste de l'album
     * @param artiste_id = identifiant de l'artiste de l'album à affecter
     */
    public void setArtiste_id(ObjectId artiste_id) {
        this.artiste_id = artiste_id;
    }

    /**
     * Getter de l'artiste de l'album
     * @return artiste
     */
    public Artiste getArtiste() {
        return artiste;
    }

    /**
     * Setter de l'artiste de l'album
     * @param artiste = artiste à affecter
     */
    public void setArtiste(Artiste artiste) {
        this.artiste = artiste;
    }

    /**
     * Getter de la liste des musiques de l'album
     * @return musiques
     */
    public List<Musique> getMusiques() {
        return musiques;
    }

    /**
     * Setter de la liste de musiques de l'album
     * @param musiques = liste de musiques de l'album à affecter
     */
    public void setMusiques(List<Musique> musiques) {
        this.musiques = musiques;
    }
}

