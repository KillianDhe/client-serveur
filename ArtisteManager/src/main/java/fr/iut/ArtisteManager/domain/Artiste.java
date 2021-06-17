package fr.iut.ArtisteManager.domain;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Document(collection = "artistes")
public class Artiste {

    /**
     * Identifiant de l'artiste
     */
    @Id
    private ObjectId _id;

    /**
     * Le pseudo (unique me semble it) de l'artiste
     */
    private String pseudo;

    /**
     * L'identité de l'artiste, conteint des infos telles que nom et prénom.
     */
    private Identite identite;

    /**
     * LE nom de famille de l'artiste
     */
    private String nom;

    /**
     * Le prénom de l'artiste
     */
    private String prenom;

    /**
     * Version du schema
     */
    private int schema_version;


    /**
     * La date de naissance de l'artiste
     */
    private Date dateDeNaissance;

    /**
     * Le distributeur utilisé par l'artiste
     */
    private Distributeur distributeur;

    /**
     * Constructeur par défaut
     */
    public Artiste() {
    }

    /**
     * Constructeur d'un artiste
     * @param pseudo le pseudo de l'artiste
     * @param nom le nbom de l'artiste
     * @param prenom le prenom de l'artiste
     * @param dateDeNaissance la ddate de nbaissance de l'artiste
     * @param distributeur le distributeur de l'artiste
     */
    public Artiste(String pseudo, String nom, String prenom, Date dateDeNaissance,Distributeur distributeur, Identite identite) {
        this.pseudo = pseudo;
        this.nom = nom;
        this.prenom = prenom;
        this.dateDeNaissance = dateDeNaissance;
        this.distributeur = distributeur;
        this.identite = identite;
    }

    /**
     * Définit l'identifiant de l'artiste
     * @param id
     */
    public void setId(ObjectId id) {
        this._id = id;
    }

    /**
     * Définit le pseudo de l'artiste.
     * @param pseudo
     */
    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    /**
     * Définit le nom de l'artiste
     * @param nom
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Définit le prénom de l'artiste
     * @param prenom
     */
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    /**
     * Définit la date de naissance de l'artiste
     * @param dateDeNaissance
     */
    public void setDateDeNaissance(Date dateDeNaissance) {
        this.dateDeNaissance = dateDeNaissance;
    }

    /**
     * Obtient l'identifiant de l'artiste
     * @return l'identifiant de l'artiste
     */
    public ObjectId get_id() {
        return _id;
    }

    /**
     * Obtient le pseudo de l'artiste
     * @return le pseudo de l'artiste
     */
    public String getPseudo() {
        return pseudo;
    }

    /**
     * Obtient le nom de l'artiste
     * @return le nom de l'artiste
     */
    public String getNom() {
        return nom;
    }

    /**
     * Obtient le prénom de l'artiste
     * @return le prénom de l'artiste
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     * Obtient la date de naissance de l'artiste
     * @return la date de naissance de l'artiste
     */
    public Date getDateDeNaissance() {
        return dateDeNaissance;
    }

    /**
     * Obtient l'identifiant de l'artiste
     * @param _id l'identifiant de l'artiste
     */
    public void set_id(ObjectId _id) {
        this._id = _id;
    }

    /**
     * Obtient le distribteur de l'artiste
     * @return le distributeur de l'artiste
     */
    public Distributeur getDistributeur() {
        return distributeur;
    }

    /**
     * Définit le distributeur de l'artiste
     * @param distributeur
     */
    public void setDistributeur(Distributeur distributeur) {
        this.distributeur = distributeur;
    }

    public int getSchema_version() {
        return schema_version;
    }

    public void setSchema_version(int schema_version) {
        this.schema_version = schema_version;
    }

    public Identite getIdentite() {
        return identite;
    }

    public void setIdentite(Identite identite) {
        this.identite = identite;
    }
}
