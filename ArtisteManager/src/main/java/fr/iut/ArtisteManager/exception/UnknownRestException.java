package fr.iut.ArtisteManager.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class UnknownRestException extends RuntimeException {

    public UnknownRestException() {
        super("Erreur inconnue, sorry, veuillez réessayer ultérieurement.");
    }

    public UnknownRestException(String message) {
        super(message);
    }
}
