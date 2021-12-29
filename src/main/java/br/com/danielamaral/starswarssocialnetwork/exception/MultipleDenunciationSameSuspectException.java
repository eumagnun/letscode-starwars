package br.com.danielamaral.starswarssocialnetwork.exception;

public class MultipleDenunciationSameSuspectException extends Exception {

	private static final long serialVersionUID = -7419231201007025654L;

	public MultipleDenunciationSameSuspectException(String errorMessage) {
        super(errorMessage);
    }
}
