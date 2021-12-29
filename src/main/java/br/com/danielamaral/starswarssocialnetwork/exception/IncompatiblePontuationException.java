package br.com.danielamaral.starswarssocialnetwork.exception;

public class IncompatiblePontuationException extends Exception {

	private static final long serialVersionUID = -3856530014504143016L;

	public IncompatiblePontuationException(String errorMessage) {
        super(errorMessage);
    }
}
