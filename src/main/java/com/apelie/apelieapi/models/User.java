package com.apelie.apelieapi.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "user")
@Getter
@Setter
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long userId;
    private String username;
    private String password;
    private String city;
    private String email;
    private String street;
    private String state;
    private String cep;
    private String address_number;
    private String neighbourhood;
    private String gender;
    private String cpf;
    private Date birthDate;
}
