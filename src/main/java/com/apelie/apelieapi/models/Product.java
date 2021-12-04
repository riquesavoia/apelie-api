package com.apelie.apelieapi.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "product")
@Getter
@Setter
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long productId;
    private float price;

    @Column(length = 50)
    private String name;

    @Lob
    private String description;

    private int quantity;

    @Column(length = 40)
    private String category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="store_id")
    private Store store;

    @OneToMany
    @JoinColumn(name="product_id")
    @Fetch(FetchMode.JOIN)
    @OrderBy("product_image_id")
    private Set<ProductImage> images;

    @OneToMany(mappedBy = "product")
    private List<ProductRating> ratings;

    private boolean deleted = false;
}
