package br.com.danielamaral.starswarssocialnetwork.exception;

public class ParticipantNotFoundException extends Exception {
    public ParticipantNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
