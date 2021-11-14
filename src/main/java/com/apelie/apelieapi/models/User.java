package com.apelie.apelieapi.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

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
    @Column(length = 40, unique=true)
    private String email;
    private String photoUrl;
}
