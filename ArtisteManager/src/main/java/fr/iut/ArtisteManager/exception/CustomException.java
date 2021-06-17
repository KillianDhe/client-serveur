package fr.iut.ArtisteManager.exception;

/**
 * Classe représentant une exception métier de l'application.
 */
public class CustomException extends RuntimeException {

    public CustomException(String message) {
        super(message);
    }
}
