package com.project.userManagement.data.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "userTable")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name ="user_profile", nullable = false)
    private String userName;

    @Column(name ="user_password", nullable = false)
    private String password;

    @Column(name ="user_email", nullable = false)
    private String email;

    @Column(name = "user_name", nullable = false)
    private String name;
}
