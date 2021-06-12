package fr.iut.ArtisteManager.controller;

import fr.iut.ArtisteManager.domain.Album;
import fr.iut.ArtisteManager.repository.AlbumRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * Controller permettant de gérer les albums
 */
@RestController
public class AlbumController {

    /**
     * Le repository pour gérer les albums
     */
    final AlbumRepository repository;

    /**
     * Constructeur du AlbumController, injecte le repository
      * @param repository = repository à injetcer
     */
    public AlbumController(AlbumRepository repository) {
        this.repository = repository;
    }

    /**
     * Méthode pour récupérer tous les albums de la base de données
     * @return la liste des albums
     */
    @GetMapping("/getAllAlbums")
    public List<Album> getAllAlbums() {
        return repository.findAll();
    }

    /**
     * Méthode pour récupérer un album à partir de son titre
     * @param name : titre de l'album
     * @return l'album qui possède le titre "name"
     */
    @GetMapping("/getAlbumByTitre")
    public Album getAlbumByTitre(@RequestParam(required = true) String name) {
        return repository.findByTitre(name);
    }

    /**
     * Méthode pour supprimer un album selon son titre
     * @param name : titre de l'album à supprimer
     */
    @DeleteMapping("/deleteAlbumByTitre")
    public void deleteAlbumByTitre(@RequestParam(required = true) String name) {
        if (name.equals("")) {
            //exception
            return;
        }
        repository.deleteAlbumByTitre(name);
    }

    /**
     * Méthode pour supprimer un album selon son id
     * @param id : id de l'album à supprimer
     */
    @DeleteMapping("/deleteAlbum/{id}")
    public void deleteAlbum(@PathVariable String id) {
        ObjectId objectId = new ObjectId(id);
        if (id.equals("")) {
            throw new CustomException("Il faut renseigner un id");
        }
        if(!repository.existsById(objectId)){
            throw new CustomException("Cet album n'existe pas");
        }
        repository.deleteById(objectId);
    }

    /**
     * Méthode pour ajouter un album en base de données
     * @param entity : l'album à ajouter
     * @return l'album ajouté
     */
    @PostMapping("/addAlbum")
    public Album addAlbum(@RequestBody Album entity) {
        if (entity == null) {
            throw new CustomException("Must be not null");
        }
        if(repository.existsById(entity.get_id())){
            throw new CustomException("Cet album existe déjà");
        }
        return repository.insert(entity);
    }

    /**
     * Méthode pour modifier un album, doit contenir l'id de l'album à modifier, tous les autres champs seront modifiés
     * @param entity : album à modifier
     * @return l'album modifié
     */
    @PutMapping("/updateAlbum")
    public Album updateAlbum(@RequestBody Album entity) {
        if (entity == null) {
            throw new CustomException("Must be not null");
        }
        if(!repository.existsById(entity.get_id())){
            throw new CustomException("Cet album n'existe pas");
        }
        return repository.save(entity);
    }
}
