package br.com.danielamaral.starswarssocialnetwork.model;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "Gender")
public enum Gender {
    MALE(1), FEMALE(2);
    private final int option;
    Gender(int option) {
        this.option = option;
    }
    public int getOption() {
        return option;
    }
}
