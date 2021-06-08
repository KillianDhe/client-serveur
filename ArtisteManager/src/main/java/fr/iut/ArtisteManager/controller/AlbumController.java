package fr.iut.ArtisteManager.controller;

import fr.iut.ArtisteManager.domain.Album;
import fr.iut.ArtisteManager.repository.AlbumRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * Les Controller vont vous permettre une exposition rapide vos {@link org.springframework.data.mongodb.repository.MongoRepository}
 * et une mise en situation de leurs cas d'usages.
 */
@RestController
public class AlbumController {

    final AlbumRepository repository;

    /**
     * Spring fonctionne avec de l'injection de dépendances, pas d'annotation à rajouter dans les controller,
     * pas de new, il va s'en charger pour vous grâce à l'annotation présente sur cette classe.
     */
    public AlbumController(AlbumRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/getAllAlbums")
    public List<Album> getAllAlbums() {
        return repository.findAll();
    }

    @GetMapping("/getAlbumByTitre")
    public Album getAlbumByTitre(@RequestParam(required = true) String name) {
        return repository.findByTitre(name);
    }

    @DeleteMapping("/deleteAlbumByTitre")
    public void deleteAlbumByTitre(@RequestParam(required = true) String name) {
        if (name.equals("")) {
            //exception
            return;
        }
        repository.deleteAlbumByTitre(name);
    }

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
