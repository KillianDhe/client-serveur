package fr.iut.ArtisteManager.exception;

import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class AlbumNotFoundException extends RuntimeException {

    public AlbumNotFoundException() {
        super("Aucun album trouvé");
    }

    public AlbumNotFoundException(ObjectId id) {
        super("Album non trouvé pour l'id : " + id);
    }

    public AlbumNotFoundException(String message) {
        super(message);
    }
}
