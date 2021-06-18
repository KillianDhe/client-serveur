package fr.iut.ArtisteManager.repository;

import fr.iut.ArtisteManager.domain.Artiste;
import fr.iut.ArtisteManager.domain.ArtisteWithAlbum;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Interface représentant le repository pour la gestion des artistes
 */
public interface ArtisteRepository extends MongoRepository<Artiste, ObjectId> {

        @Aggregation({" {$match: { \"_id\": ?0 }}", "{$lookup: { from: \"albums\", localField: \"_id\", foreignField: \"artiste_id\", as: \"albums\" }} ])"})
        ArtisteWithAlbum findArtisteBy_idWithAlbums(ObjectId id);
}