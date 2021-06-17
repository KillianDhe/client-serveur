package fr.iut.ArtisteManager.exception;

import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class EmptyOrNullIdException extends RuntimeException {

    public EmptyOrNullIdException() {
        super("Vous avez donné un id vide, veuillez entrer un id valdie");
    }

    public EmptyOrNullIdException(String message) {
        super(message);
    }
}
