package com.apelie.apelieapi.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "Order")
@Getter
@Setter
@NoArgsConstructor
public class BuyOrder {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long orderId;
    private String trackingCode;
    private OrderStatus status;
    private PaymentMethod paymentMethod;
    private Date createdAt;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name="store_id")
    private Store store;

    @OneToMany
    @JoinColumn(name="order_item_id")
    private List<OrderItem> orderItemList;
}
