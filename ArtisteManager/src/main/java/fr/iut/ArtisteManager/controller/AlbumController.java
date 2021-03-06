package fr.iut.ArtisteManager.controller;

import fr.iut.ArtisteManager.domain.Album;
import fr.iut.ArtisteManager.domain.Artiste;
import fr.iut.ArtisteManager.domain.Identite;
import fr.iut.ArtisteManager.exception.AlbumNotFoundException;
import fr.iut.ArtisteManager.exception.ArtisteNotFoundException;
import fr.iut.ArtisteManager.domain.AlbumAggregate;
import fr.iut.ArtisteManager.exception.EmptyOrNullIdException;
import fr.iut.ArtisteManager.exception.UnknownRestException;
import fr.iut.ArtisteManager.repository.AlbumRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.Optional;


/**
 * Controller permettant de gérer les albums
 */
@RestController
public class AlbumController {

    /**
     * Le repository pour gérer les albums
     */
    final AlbumRepository repository;

    private final static int albumSchemaVersion = 1;

    /**
     * Constructeur du AlbumController, injecte le repository
      * @param repository = repository à injetcer
     */
    public AlbumController(AlbumRepository repository) {
        this.repository = repository;
    }

    /**
     * Méthode pour récupérer un album à partir de son id
     * @param id : titre de l'album
     * @return l'album qui possède l'identifiant "id"
     */
    @GetMapping("/getAlbumById/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Album getAlbumById(@PathVariable String id) {
        try{
            ObjectId objectId;
            if (id.equals("") || id == null) {
                throw new EmptyOrNullIdException();
            }
            try{
                objectId = new ObjectId(id);
            }catch (IllegalArgumentException ex) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "le format de l'identifiant n'est pas bon chacal");
            }

            if (!repository.existsById(objectId)){
                throw new ArtisteNotFoundException();
            }

            Optional<Album> albumFound = repository.findById(objectId);
            Album updated  = convertSchemaToLastVersionIfNedded(albumFound.get());
            if(updated != null){return updated;}
            return albumFound.get();
        }
        catch (ResponseStatusException | EmptyOrNullIdException | AlbumNotFoundException e){
            throw e;
        }
        catch (Exception exception){
            throw new UnknownRestException();
        }

    }


    /**
     * Méthode pour supprimer tous les albums avec un titre donné
     * @param name : titre des albums à supprimer
     */
    @DeleteMapping("/deleteAlbumsByTitre")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAlbumsByTitre(@RequestParam(required = true) String name) {
        try{
            repository.deleteAlbumsByTitre(name);
        }
        catch (Exception exception){
            throw new UnknownRestException();
        }
    }

    /**
     * Méthode pour supprimer un album selon son id
     * @param id : id de l'album à supprimer
     */
    @DeleteMapping("/deleteAlbum/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAlbum(@PathVariable String id) {
        try{
            ObjectId objectId;
            if (id.equals("") || id == null) {
                throw new EmptyOrNullIdException("Donnes un id chacal !");
            }

            try {
                objectId = new ObjectId(id);
            } catch (IllegalArgumentException ex) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "le format de l'identifiant n'est pas bon chacal");
            }

            if (!repository.existsById(objectId)) {
                throw new AlbumNotFoundException(objectId);
            }

            repository.deleteById(objectId);
        }
        catch (AlbumNotFoundException | EmptyOrNullIdException | ResponseStatusException e){
            throw  e;
        } catch (Exception ex){
            throw new UnknownRestException();
        }
    }

    /**
     * Méthode pour récupérer tous les albums de la base de données
     * @return la liste des albums
     */
    @GetMapping("/getAllAlbums")
    @ResponseStatus(HttpStatus.OK)
    public List<Album> getAllAlbums() {
        try{
            return repository.findAll();
        }
        catch (Exception ex){
            throw new UnknownRestException();
        }
    }

    /**
     * Méthode pour récupérer tous les albums de la base de données avec un titre contenant le titre passé en paramètre
     * @param titre le text  pour lequel on veut récuperer les albums contenant celui-ci dans le titre
     * @return la liste des albums ayant un titre contenant le titre passé en paramètre
     */
    @GetMapping("/getAllAlbumsByTitreContaining")
    @ResponseStatus(HttpStatus.OK)
    public List<Album> getAllAlbumsByTitreContaining(@RequestParam(required = true) String titre) {
        try{
            return repository.findAlbumsByTitreContaining(titre);
        }
        catch (Exception ex){
            throw new UnknownRestException();
        }
    }

    /**
     * Méthode pour récupérer tous les albums avec le titre passé en paramètre.
     * @param titre le titre pour lequel on veut récuperer les albums
     * @return la liste des albums ayant pour titre le titre passé en paramètre
     */

    @GetMapping("/getAllAlbumsByTitre")
    @ResponseStatus(HttpStatus.OK)
    public List<Album> getAllAlbumsByTitre(@RequestParam(required = true) String titre) {
        try{
            return repository.findAlbumsByTitre(titre);
        }
        catch (Exception ex){
            throw new UnknownRestException();
        }
    }

    /**
     * Méthode pour ajouter un album en base de données
     * @param entity : l'album à ajouter
     * @return l'album ajouté
     */
    @PostMapping("/addAlbum")
    @ResponseStatus(HttpStatus.CREATED)
    public Album addAlbum(@RequestBody Album entity) {
        try{
            if (entity == null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"album Must be not null");
            }

            if (entity.get_id() != null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Cete artiste existe déjà gogole ! mets pas d'id dans l'ajout wola pas besoin ptn !");
            }

            Album updated = convertSchemaToLastVersionIfNedded(entity);
            if(updated != null){return updated;}
            return repository.insert(entity);
        }
        catch (ResponseStatusException | EmptyOrNullIdException e){
            throw  e;
        }
        catch (Exception ex){
            throw new UnknownRestException();
        }
    }

    /**
     * Méthode pour modifier un album, doit contenir l'id de l'album à modifier, tous les autres champs seront modifiés
     * @param entity : album à modifier
     * @return l'album modifié
     */
    @PutMapping("/updateAlbum")
    @ResponseStatus(HttpStatus.OK)
    public Album updateAlbum(@RequestBody Album entity) {
        try{
            if (entity == null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"album Must be not null");
            }
            if (entity.get_id() == null) {
                throw new EmptyOrNullIdException();
            }
            if( ! repository.existsById(entity.get_id())){
                throw new AlbumNotFoundException(entity.get_id());
            }
            Album updated = convertSchemaToLastVersionIfNedded(entity);
            if(updated != null){return updated;}
            return repository.save(entity);
        }
        catch (ResponseStatusException | AlbumNotFoundException | EmptyOrNullIdException e){
            throw e;
        }
        catch (Exception ex){
            throw  new UnknownRestException();
        }
    }

    /**
     * Permet de mettre à jour un Album qui n'a pas de schema_version (v0 ) en version schema_version1 (couverture devient imageDeCouverture)
     * @param id l'identifiant de l'album à mettre à jour
     * @return l'album mis à jour
     */
    @PutMapping("/updateAlbumSchematoV1/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Album updateArtisteSchema(@PathVariable String id){
        try{
            if (id.equals("") || id == null) {
                throw new EmptyOrNullIdException();
            }
            ObjectId objectId;
            try{
                objectId = new ObjectId(id);
            }
            catch(IllegalArgumentException ex){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "le format de l'identifiant n'est pas bon chacal");
            }
            if(!repository.existsById(objectId)){
                throw new AlbumNotFoundException(objectId);
            }
            Optional<Album> toPotientalliyUpdate = repository.findById(objectId);
            Album updated = convertSchemaToLastVersionIfNedded(toPotientalliyUpdate.get());
            if(updated != null){return updated;}
            return toPotientalliyUpdate.get();
        }
        catch (ResponseStatusException | AlbumNotFoundException | EmptyOrNullIdException e){
            throw  e;
        }
        catch (Exception ex){
            throw new UnknownRestException();
        }
    }

    /**
     * Méthode de recherche permettant de récupérer tous les titres des albums (basé sur un pipeline d'aggregation)
     * @return la liste des titres
     */
    @GetMapping("/findAllTitresAlbum")
    @ResponseStatus(HttpStatus.OK)
    public List<String> findAllTitresAlbum() {
        try {
            return repository.findAllTitres();
        }
        catch (Exception exception){
            throw new UnknownRestException();
        }
    }

    /**
     * Méthode de recherche permettant de récupérer des AlbumAggregate (basé sur un pipeline d'aggregation)
     * @return la liste des AlbumsAggregate
     */
    @GetMapping("/groupByTitreAndMusiquesAlbum")
    @ResponseStatus(HttpStatus.OK)
    public List<AlbumAggregate> groupByTitreAndMusiquesAlbum() {
        try {
            return repository.groupByTitreAndMusiques();
        }
        catch (Exception exception){
            throw new UnknownRestException();
        }
    }

    /**
     * Vérifie si l'album est à la derniere version
     * @param album l'album pour lequel on veut vérifier la version
     * @return un booléen indiquant si oui ou non l'album utilise la derniere version disponible.
     */
    private boolean IsAlbumLastVersion(Album album){
        return  album.getSchema_version() == albumSchemaVersion;
    }

    /**
     * Mets à jour un album si il n'est pas à la derniere version.
     * @param album l'album à  mettre potentiellement à jour
     * @return l'album mis à jour si tel est le cas, null sinon
     */
    private Album convertSchemaToLastVersionIfNedded(Album album){
        if ( album.getSchema_version() == 0 ){
            return convertAlbumV0ToV1(album);
        }
        return null;
    }

    /**
     * Convertit un album qui n'a pas de schemaVersion (v0) en album v1
     * @param album l'album à convertir
     * @return l'album convertit
     */
    private Album convertAlbumV0ToV1(Album album) {
        album.setImageDeCouverture(album.getCouverture());
        album.setSchema_version(1);
        repository.save(album);
        return album;
    }
}
