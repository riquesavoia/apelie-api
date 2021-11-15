package com.apelie.apelieapi.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "store_review")
@Getter
@Setter
@NoArgsConstructor
public class StoreReview {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long reviewId;

    private String description;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    private float rating;

    @ManyToOne
    @JoinColumn(name="store_id")
    private Store store;
}
