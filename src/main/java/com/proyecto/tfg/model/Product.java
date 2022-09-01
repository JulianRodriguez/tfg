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

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Restaurant restaurant;

    @Override
    public String toString() {
        return "Product{" +
                "idProduct=" + idProduct +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", photo='" + photo + '\'' +
                '}';
    }
}
