package com.apelie.apelieapi.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;

@Entity
@Table(name = "user")
@Getter
@Setter
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long userId;
    @Column(length = 50)
    private String fullName;
    private String password;
    @Column(length = 25)
    private String city;
    @Column(length = 40)
    private String email;
    @Column(length = 60)
    private String street;
    @Column(length = 30)
    private String state;
    @Column(length = 20)
    private String cep;
    @Column(length = 10)
    private String addressNumber;
    @Column(length = 60)
    private String neighbourhood;
    @Column(length = 40)
    private String gender;
    @Column(length = 15)
    private String cpf;
    private LocalDate birthDate;
    private String photoUrl;
}
