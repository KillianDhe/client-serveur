package fr.iut.ArtisteManager.repository;

import fr.iut.ArtisteManager.domain.Artiste;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ArtisteRepository extends MongoRepository<Artiste, ObjectId> {
        Artiste findArtistesByPseudo(String pseudo);
}