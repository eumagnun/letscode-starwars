package br.com.danielamaral.starswarssocialnetwork.model;

public enum RebelStatus {
    OK(1), TRAITOR(2);
    private final int option;
    RebelStatus(int option) {
        this.option = option;
    }
    public int getOption() {
        return option;
    }
}
