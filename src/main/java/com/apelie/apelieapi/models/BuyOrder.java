package com.apelie.apelieapi.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "BuyOrder")
@Getter
@Setter
@NoArgsConstructor
public class BuyOrder {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long buyOrderId;
    private String trackingCode;
    private String status;
    private String createdAt;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User owner;

    @ManyToOne
    @JoinColumn(name="store_id")
    private Store store;

    @ManyToMany
    @JoinTable(
            name = "product_buy_order",
            joinColumns = @JoinColumn(name = "buy_order_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<Product> products;
}
