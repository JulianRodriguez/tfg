package com.proyecto.tfg.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Restaurant {

    public static final String FIELD_PRODUCT = "idRestaurant";
    public static final String FIELD_USER = "idRestaurant";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRestaurant;

    @Column(unique= true,nullable = false)
    private String nameRestaurant;

    @Column(nullable = false)
    private String descriptionRestaurant;

    //Falta guardar la foto

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = FIELD_PRODUCT, referencedColumnName = FIELD_PRODUCT)
    private List<Product> product;



}
