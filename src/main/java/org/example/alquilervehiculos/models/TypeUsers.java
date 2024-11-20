package org.example.alquilervehiculos.models;

import lombok.Getter;

@Getter
public class TypeUsers {

    private String code;
    private String description;



    public TypeUsers(String code, String description) {
        this.code = code;
        this.description = description;
    }

}
