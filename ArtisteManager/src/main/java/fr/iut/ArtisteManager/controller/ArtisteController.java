package fr.iut.ArtisteManager.controller;

import fr.iut.ArtisteManager.domain.Album;
import fr.iut.ArtisteManager.domain.Artiste;
import fr.iut.ArtisteManager.repository.AlbumRepository;
import fr.iut.ArtisteManager.repository.ArtisteRepository;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ArtisteController {


    final ArtisteRepository repository;

    /**
     * Spring fonctionne avec de l'injection de dépendances, pas d'annotation à rajouter dans les controller,
     * pas de new, il va s'en charger pour vous grâce à l'annotation présente sur cette classe.
     */
    public ArtisteController(ArtisteRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/getAllArtistes")
    public List<Artiste> getAllArtistes() {
        //return new ArrayList<Artiste>();
        return repository.findAll();
    }

    @PostMapping("/getArtisteByPseudo")
    public Artiste getArtisteByPseudo(@RequestParam String pseudo) {
        if (pseudo == null) {
            throw new CustomException("donnes moi un pseudo !");
        }
        return repository.findArtisteByPseudo(pseudo);
    }

    @PostMapping("/addArtiste")
    public Artiste insert(@RequestBody Artiste entity) {
        if (entity == null) {
            throw new CustomException("Must be not null");
        }
        return repository.save(entity);
    }

}
