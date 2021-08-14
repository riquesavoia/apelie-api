package com.apelie.apelieapi.models;

import com.apelie.apelieapi.models.enums.Category;
import com.apelie.apelieapi.models.enums.PaymentMethod;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "store")
@Getter
@Setter
@NoArgsConstructor
public class Store {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long storeId;

    @OneToOne
    @JoinColumn(name="user_id")
    private User owner;

    @Column(length = 50)
    private String twitterLink;

    @Enumerated(EnumType.STRING)
    private Category category;

    @ElementCollection(targetClass = PaymentMethod.class)
    @CollectionTable(name = "store_payment", joinColumns = @JoinColumn(name = "store_id"))
    @Column(name = "payment_method", nullable = false)
    @Enumerated(EnumType.STRING)
    private List<PaymentMethod> paymentMethods;

    @Column(length = 50)
    private String instagramLink;
    @Column(length = 30)
    private String state;
    @Column(length = 50)
    private String facebookLink;
    @Column(length = 50)
    private String youtubeLink;
    private String bannerUrl;
    private String logoUrl;
    @Column(length = 20)
    private String theme;
    @Column(length = 60)
    private String street;
    @Column(length = 25)
    private String city;
    @Column(length = 20)
    private String cep;
    @Column(length = 50)
    private String name;
    @Column(length = 40)
    private String email;
    @Column(length = 15)
    private String phone;
    @Column(length = 10)
    private String addressNumber;
    @Column(length = 10)
    private String neighbourhood;
    private Float rating;
    private String description;
}
