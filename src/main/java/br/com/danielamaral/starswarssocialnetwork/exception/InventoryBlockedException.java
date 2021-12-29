package br.com.danielamaral.starswarssocialnetwork.exception;

public class InventoryBlockedException extends Exception {
	private static final long serialVersionUID = -2620423557615573392L;

	public InventoryBlockedException(String errorMessage) {
        super(errorMessage);
    }
}
