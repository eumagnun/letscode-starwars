package br.com.danielamaral.starswarssocialnetwork.exception;

public class ParticipantNotFoundException extends Exception {
	private static final long serialVersionUID = -4610241539708950888L;

	public ParticipantNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
