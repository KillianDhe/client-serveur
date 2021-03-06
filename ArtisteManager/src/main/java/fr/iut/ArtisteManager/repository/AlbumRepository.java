package fr.iut.ArtisteManager.repository;

import fr.iut.ArtisteManager.domain.Album;
import fr.iut.ArtisteManager.domain.AlbumAggregate;
import fr.iut.ArtisteManager.domain.Musique;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Interface représentant le repository pour la gestion des albums
 */
public interface AlbumRepository extends MongoRepository<Album, ObjectId> {

    List<Album> findAlbumsByTitre(String titre);

    List<Album> findAlbumsByTitreContaining(String titre);

    void deleteAlbumsByTitre(String titre);

    @Aggregation({"{ $unwind : \"$musiques\" }", "{ $group: { _id: $titre, musiques: { $addToSet: $musiques } } }", "{ $project: { \"count\": { $size: \"$musiques\" }, musiques: 1 } }"})
    List<AlbumAggregate> groupByTitreAndMusiques();

    @Aggregation("{ $project: { _id: $titre } }")
    List<String> findAllTitres();

}
