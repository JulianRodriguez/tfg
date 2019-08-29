package com.proyecto.tfg.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Product {

    public static final String FIELD_INGREDIENT = "idProduct";


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProduct;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(columnDefinition="LONGTEXT")
    private String photo;

    //Aqui falta añadir la imagen

//    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    @JoinColumn(name = FIELD_INGREDIENT, referencedColumnName = FIELD_INGREDIENT)
//    private List<Ingredient> ingredients;



}
