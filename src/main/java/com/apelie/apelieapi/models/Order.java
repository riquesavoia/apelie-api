package com.apelie.apelieapi.models;

import com.apelie.apelieapi.models.enums.OrderStatus;
import com.apelie.apelieapi.models.enums.PaymentMethod;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "buy_order")
@Getter
@Setter
@NoArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long orderId;
    private String trackingCode;
    @Enumerated(EnumType.STRING)
    private OrderStatus status;
    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    @Column(name = "", columnDefinition = "TIMESTAMP")
    private LocalDateTime createdAt;
    private float totalValue;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name="store_id")
    private Store store;

    @OneToOne
    @JoinColumn(name="address_id")
    private Address address;

    @OneToMany
    @JoinColumn(name="order_id")
    @Cascade(value={org.hibernate.annotations.CascadeType.ALL})
    private Set<OrderItem> itemList;

    @OneToOne
    @JoinColumn(name="review_id")
    private StoreReview storeReview;
}
