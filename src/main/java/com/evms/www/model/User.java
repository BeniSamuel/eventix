package com.evms.www.model;

import com.evms.www.enums.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true)
    private String email;

    private String password;

    @Column(unique = true)
    private int phone;

    private String location;

    @Enumerated(EnumType.STRING)
    private Role role;

    public User (String name, String email, String password, int phone, String location, Role role) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.location = location;
        this.phone = phone;
        this.role = role;
    }
}
