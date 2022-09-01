package com.proyecto.tfg.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
 public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idIngredient;

    @Column(unique= true, nullable = false)
    private String name;

    @Column(nullable = false)
    private String type;
}
