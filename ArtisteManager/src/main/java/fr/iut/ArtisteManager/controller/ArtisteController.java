package fr.iut.ArtisteManager.controller;

import fr.iut.ArtisteManager.domain.Artiste;
import fr.iut.ArtisteManager.domain.ArtisteWithAlbum;
import fr.iut.ArtisteManager.domain.Identite;
import fr.iut.ArtisteManager.exception.ArtisteNotFoundException;
import fr.iut.ArtisteManager.exception.EmptyOrNullIdException;
import fr.iut.ArtisteManager.exception.UnknownRestException;
import fr.iut.ArtisteManager.repository.ArtisteRepository;
import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.Optional;

/**
 * Controller permettant de gérer les artistes
 */
@RestController
public class ArtisteController {

    private final static int artisteSchemaVersion = 1;

    /**
     * le repopothame artiste pour gérer les artistes
     */
    final ArtisteRepository repository;

    /**
     * Constructeur du ArtisteController, injecte le artiste repopo
     */
    public ArtisteController(ArtisteRepository repository) {
        this.repository = repository;
    }

    /**
     * récupère tous les artistes de la base.
     * @return La liste de tous les artistes
     */
    @GetMapping("/getAllArtistes")
    @ResponseStatus(HttpStatus.OK)
    public List<Artiste> getAllArtistes() {
        try{
            return repository.findAll();
        }
        catch (Exception ex){
            throw new UnknownRestException();
        }
    }

    /**
     * Récupère un artiste à partir de son pseudo
     * @param id l'id  de l'artiste quy l'on souhaite récuperer
     * @return l'artiste trouvé
     */
    @PostMapping("/getArtisteById")
    @ResponseStatus(HttpStatus.OK)
    public Artiste getArtisteByPseudo(@RequestParam String id) {
        try{
            ObjectId objectId;
            if (id.equals("") || id == null) {
                throw new EmptyOrNullIdException();
            }
            try{
                objectId = new ObjectId(id);
            }
            catch (IllegalArgumentException ex) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "le format de l'identifiant n'est pas bon chacal");
            }

            if (!repository.existsById(objectId)){
                throw new ArtisteNotFoundException();
            }

            Optional<Artiste> artisteFound = repository.findById(objectId);
            Artiste updated  = convertSchemaToLastVersionIfNedded(artisteFound.get());
            if(updated != null){return updated;}
            return artisteFound.get();
        }
        catch (ResponseStatusException | EmptyOrNullIdException | ArtisteNotFoundException e){
            throw e;
        }
        catch (Exception exception){
            throw new UnknownRestException();
        }

    }

    /**
     * Ajoute un artiste
     * @param entity l'artiste à ajouter
     * @return l'artiste ajouté
     */
    @PostMapping("/addArtiste")
    @ResponseStatus(HttpStatus.CREATED)
    public Artiste addArtiste(@RequestBody Artiste entity) {
        try{
            if (entity == null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"artiste Must be not null");
            }
            if (entity.get_id() != null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Cete artiste existe déjà gogole ! mets pas d'id dans l'ajout wola pas besoin ptn !");
            }

            Artiste updated = convertSchemaToLastVersionIfNedded(entity);
            if(updated != null){return updated;}
            return repository.save(entity);
        }
       catch (ResponseStatusException | EmptyOrNullIdException e){
            throw  e;
       }
        catch (Exception ex){
            throw new UnknownRestException();
        }
    }

    /***
     * Mets à jour un artiste, doit contenir l'id de l'artiste à modifier, tous les autres champs seront modifier
     * @param entity l'artiste à modifier
     * @return l'artiste modifié
     */
    @PutMapping("/updateArtiste")
    @ResponseStatus(HttpStatus.OK)
    public Artiste updateArtiste(@RequestBody Artiste entity) {
        try{
            if (entity == null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"artiste Must be not null");
            }
            if (entity.get_id() == null) {
                throw new EmptyOrNullIdException();
            }
            if( ! repository.existsById(entity.get_id())){
                throw new ArtisteNotFoundException(entity.get_id());
            }
            Artiste updated = convertSchemaToLastVersionIfNedded(entity);
            if(updated != null){return updated;}
            return repository.save(entity);
        }
        catch (ResponseStatusException | ArtisteNotFoundException | EmptyOrNullIdException e){throw e;}
        catch (Exception ex){
            throw  new UnknownRestException();
        }

    }

    /**
     *
     * @param id
     * @return
     */
    @PutMapping("/updateArtisteSchematoV1/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Artiste updateArtisteSchema(@PathVariable String id){
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
                throw new ArtisteNotFoundException(objectId);
            }
            Optional<Artiste> toPotientalliyUpdate = repository.findById(objectId);
            Artiste updated = convertSchemaToLastVersionIfNedded(toPotientalliyUpdate.get());
            if(updated != null){return updated;}
            return toPotientalliyUpdate.get();
        }
        catch (ResponseStatusException | ArtisteNotFoundException | EmptyOrNullIdException e){
            throw  e;
        }
        catch (Exception ex){
            throw new UnknownRestException();
        }
    }

    /***
     * Supprimes un artiste à partir de son id
     * @param id l'id de l'artise que l'on souhaite supprimer (format string)
     */
    @DeleteMapping("/deleteArtiste/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteArtiste(@PathVariable String id) {
        try {
            if (id.equals("") || id == null) {
                throw new EmptyOrNullIdException("Donnes un id chacal !");
            }
            ObjectId objectId;
            try {
                objectId = new ObjectId(id);
            } catch (IllegalArgumentException ex) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "le format de l'identifiant n'est pas bon chacal");
            }
            if (!repository.existsById(objectId)) {
                throw new ArtisteNotFoundException(objectId);
            }
            repository.deleteById(objectId);
        }
        catch (ArtisteNotFoundException | EmptyOrNullIdException | ResponseStatusException e){
            throw  e;
        } catch (Exception ex){
            throw new UnknownRestException();
        }
    }

    /**
     * récupère tous les artistes de la base.
     * @return La liste de tous les artistes
     */
    @GetMapping("/getArtisteByIdWithAlbums")
    @ResponseStatus(HttpStatus.OK)
    public ArtisteWithAlbum getArtisteByIdWithAlbums(@RequestParam(required = true) String id) {
        try{
            if (id.equals("") || id == null) {
                throw new EmptyOrNullIdException();
            }
            ObjectId objectId;
            try {
                objectId = new ObjectId(id);
            } catch (IllegalArgumentException ex) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "le format de l'identifiant n'est pas bon chacal");
            }
            return repository.findArtisteBy_idWithAlbums(objectId);
        }
        catch (ArtisteNotFoundException | EmptyOrNullIdException | ResponseStatusException e){
            throw  e;
        }
        catch (Exception ex){
            ex.printStackTrace();
            throw new UnknownRestException();
        }
    }

    private boolean IsArtisteLastVersion(Artiste artiste){
        return  artiste.getSchema_version() == artisteSchemaVersion;
    }

    private Artiste convertSchemaToLastVersionIfNedded(Artiste artiste){
        if ( artiste.getSchema_version() == 0 ){
            return convertArtisteV0ToV1(artiste);
        }
        return null;
    }

    private Artiste convertArtisteV0ToV1(Artiste artiste) {
        Identite identite = new Identite();
        identite.setNom(artiste.getNom());
        identite.setPrenom(artiste.getPrenom());
        artiste.setIdentite(identite);
        artiste.setSchema_version(1);
        repository.save(artiste);
        return artiste;
    }

}
