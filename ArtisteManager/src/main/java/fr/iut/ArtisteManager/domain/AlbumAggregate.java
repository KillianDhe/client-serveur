package fr.iut.ArtisteManager.domain;

import org.springframework.data.annotation.Id;

import java.util.List;

/**
 * Classe représentant un Album particulier (utilisé pour le pipeline d'aggregation du repository : méthode groupByTitreAndMusiques)
 */
public class AlbumAggregate {
    /**
     * Identifiant d'un AlbumAggregate
     */
    @Id
    private String titre;

    /**
     * Liste des musiques d'un AlbumAggregate
     */
    private List<Musique> musiques;

    /**
     * Nombre de musiques d'un AlbumAggregate
     */
    private int count;

    /**
     * Constructeur d'un AlbumAggregate
     * @param titre = titre de l'AlbumAggregate
     * @param musiques = liste de musiques de l'AlbumAggregate
     * @param count = nombre de musiques de l'AlbumAggregate
     */
    public AlbumAggregate(String titre, List<Musique> musiques, int count) {
        this.titre = titre;
        this.musiques = musiques;
        this.count = count;
    }

    /**
     * Getter du titre de AlbumAggregate
     * @return titre
     */
    public String getTitre() {
        return titre;
    }

    /**
     * Setter du titre de AlbumAggregate
     * @param titre = titre à affecter
     */
    public void setTitre(String titre) {
        this.titre = titre;
    }

    /**
     * Getter de la liste de musiques de AlbumAggregate
     * @return musiques
     */
    public List<Musique> getMusiques() {
        return musiques;
    }

    /**
     * Setter de la liste de musiques de AlbumAggregate
     * @param musiques = musiques à affecter
     */
    public void setMusiques(List<Musique> musiques) {
        this.musiques = musiques;
    }

    /**
     * Getter du nombre de musiques de AlbumAggregate
     * @return count
     */
    public int getCount() {
        return count;
    }

    /**
     * Setter du nombre de musiques de AlbumAggregate
     * @param count = count à affecter
     */
    public void setCount(int count) {
        this.count = count;
    }
}
