package fr.iut.ArtisteManager.exception;

import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ArtisteNotFoundException extends RuntimeException {

    public ArtisteNotFoundException() {
        super("Aucun artiste trouvé");
    }

    public ArtisteNotFoundException(ObjectId id) {
        super("Artiste non trouvé pour l'id : " + id);
    }

    public ArtisteNotFoundException(String message) {
        super(message);
    }
}
