package fr.iut.ArtisteManager.repository;

import fr.iut.ArtisteManager.domain.Album;
import fr.iut.ArtisteManager.domain.Musique;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Interface représentant le repository pour la gestion des albums
 */
public interface AlbumRepository extends MongoRepository<Album, ObjectId> {

    Album findByTitre(String titre);
    void deleteAlbumByTitre(String titre);

}
