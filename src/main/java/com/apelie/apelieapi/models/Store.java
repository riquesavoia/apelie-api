package com.apelie.apelieapi.models;

import com.apelie.apelieapi.models.enums.StoreCategory;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.*;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;
import java.util.Set;

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
    private String twitterAccount;

    @ElementCollection(targetClass = StoreCategory.class)
    @CollectionTable(name = "store_category", joinColumns = @JoinColumn(name = "store_id"))
    @Column(name = "category", nullable = false)
    @JoinColumn(name = "store_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @Cascade(value={CascadeType.ALL})
    @Enumerated(EnumType.STRING)
    private Set<StoreCategory> categoryList;

    @OneToMany(mappedBy = "store")
    @Fetch(FetchMode.JOIN)
    private List<Product> productList;

    @Column(length = 50)
    private String instagramAccount;

    @Column(length = 30)
    private String state;

    @Column(length = 50)
    private String facebookAccount;

    @Column(length = 50)
    private String youtubeAccount;

    private String bannerUrl;

    private String logoUrl;

    @Column(length = 20)
    private String primaryColor;

    @Column(length = 20)
    private String secondaryColor;

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

    @Column(length = 50)
    private String neighbourhood;

    private Float rating;

    @Lob
    private String description;
}
