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
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long storeId;

    @OneToOne
    @JoinColumn(name="user_id")
    private User owner;

    private String twitterLink;

    @Enumerated(EnumType.STRING)
    private Category category;

    @ElementCollection(targetClass = PaymentMethod.class)
    @CollectionTable(name = "store_payment", joinColumns = @JoinColumn(name = "store_id"))
    @Column(name = "payment_method", nullable = false)
    @Enumerated(EnumType.STRING)
    private List<PaymentMethod> paymentMethods;

    private String instagramLink;
    private String state;
    private String facebookLink;
    private String youtubeLink;
    private String bannerUrl;
    private String logoUrl;
    private String theme;
    private String street;
    private String city;
    private String cep;
    private String name;
    private String email;
    private String phone;
    private String addressNumber;
    private String neighbourhood;
    private Float rating;
    private String description;
}
