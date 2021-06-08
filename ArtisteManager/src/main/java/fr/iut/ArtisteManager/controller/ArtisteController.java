package fr.iut.ArtisteManager.controller;

import fr.iut.ArtisteManager.domain.Album;
import fr.iut.ArtisteManager.domain.Artiste;
import fr.iut.ArtisteManager.repository.AlbumRepository;
import fr.iut.ArtisteManager.repository.ArtisteRepository;
import org.bson.types.ObjectId;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Controller permettant de gérer les artistes
 */
@RestController
public class ArtisteController {


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
    public List<Artiste> getAllArtistes() {
        return repository.findAll();
    }

    /**
     * Récupère un artiste à partir de son pseudo
     * @param pseudo de l'artiste quye l'on souhaite
     * @return l'artiste trouvé
     */
    @PostMapping("/getArtisteByPseudo")
    public Artiste getArtisteByPseudo(@RequestParam String pseudo) {
        if (pseudo == null) {
            throw new CustomException("donnes moi un pseudo !");
        }
        return repository.findArtisteByPseudo(pseudo);
    }

    /**
     * Ajoute un artiste
     * @param entity l'artiste à ajouter
     * @return l'artiste ajouté
     */
    @PostMapping("/addArtiste")
    public Artiste addArtiste(@RequestBody Artiste entity) {
        if (entity == null) {
            throw new CustomException("Must be not null");
        }
        if(repository.existsById(entity.get_id())){
            throw new CustomException("Cete artiste existe déjà gogole ! mets pas d'id dans l'ajout wola pas besoin ptn !");
        }
        return repository.insert(entity);
    }

    /***
     * Mets à jour un artiste, doit contenir l'id de l'artiste à modifier, tous les autres champs seront modifier
     * @param entity l'artiste à modifier
     * @return l'artiste modifié
     */
    @PutMapping("/updateArtiste")
    public Artiste updateArtiste(@RequestBody Artiste entity) {
        if (entity == null) {
            throw new CustomException("Must be not null");
        }
        if( ! repository.existsById(entity.get_id())){
            throw new CustomException("cet artiste n'existe pas !! lszd sze !!!!");
        }
        return repository.save(entity);
    }

    /***
     * Supprimes un artiste à partir de son id
     * @param id l'id de l'artise que l'on souhaite supprimer (format string)
     */
    @DeleteMapping("/deleteArtiste/{id}")
    public void deleteArtiste(@PathVariable String id) {
        ObjectId objectId = new ObjectId(id);
        if (id.equals("")) {
            throw new CustomException("Doones un id chacal !");
        }
        if(!repository.existsById(objectId)){
            throw new CustomException("cet artiste n'existe pas !! lszd sze !!!!");
        }
        repository.deleteById(objectId);
    }

}
