package com.apelie.apelieapi.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "cart_item")
@Getter
@Setter
@NoArgsConstructor
public class CartItem {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long cartItemId;

    @OneToOne
    @JoinColumn(name="user_id")
    private User owner;

    @OneToOne
    @JoinColumn(name="product_id")
    private Product product;

    private String description;

    private int quantity;
}
