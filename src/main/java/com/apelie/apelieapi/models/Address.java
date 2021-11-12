package com.apelie.apelieapi.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.*;

@Entity
@Table(name = "address")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SQLDelete(sql = "UPDATE address SET deleted = true WHERE address_id=?")
public class Address {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long addressId;
    private String city;
    private String complement;
    private String district;
    private String number;
    private String state;
    private String street;
    private String zipCode;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    private boolean deleted = false;
}
