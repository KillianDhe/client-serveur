package fr.iut.ArtisteManager.controller;

import fr.iut.ArtisteManager.domain.Album;
import fr.iut.ArtisteManager.domain.Artiste;
import fr.iut.ArtisteManager.domain.Contact;
import fr.iut.ArtisteManager.domain.Identite;
import fr.iut.ArtisteManager.exception.CustomException;
import fr.iut.ArtisteManager.repository.AlbumRepository;
import org.bson.types.ObjectId;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * Les Controller vont vous permettre une exposition rapide vos {@link org.springframework.data.mongodb.repository.MongoRepository}
 * et une mise en situation de leurs cas d'usages.
 */
@RestController
public class AlbumController {

    final AlbumRepository repository;

    private final static int albumSchemaVersion = 1;

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

    private boolean IsArtisteLastVersion(Album album){
        return  album.getSchema_version() == albumSchemaVersion;
    }

    private Album convertSchemaToLastVersionIfNedded(Album album){
        if ( album.getSchema_version() == 0 ){
            return convertAlbumV0ToV1(album);
        }
        return null;
    }

    private Album convertAlbumV0ToV1(Album album) {
        Contact contact = new Contact();
        contact.setNumeroTelephoneFixe(album.getNumeroTelephone());
        contact.setNumeroTelephonePortable(album.getNumeroTelephone());
        album.setContact(contact);
        album.setSchema_version(1);
        repository.save(album);
        return album;
    }
}
