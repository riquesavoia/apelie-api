package com.apelie.apelieapi.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "product")
@Getter
@Setter
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long productId;
    private float price;
    @Column(length = 50)
    private String name;
    private String description;
    private int quantity;
    @Column(length = 40)
    private String category;

    @ManyToOne
    @JoinColumn(name="store_id")
    private Store store;

    @OneToMany
    @JoinColumn(name="product_id")
    private List<ProductImage> images;

    @OneToMany(mappedBy = "product")
    private List<ProductRating> ratings;
}
