package com.example.day4.entities;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Data
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cid;


    @NotEmpty
    @Size(min = 3, max = 100)
    @NotNull
    @Column(length = 100)
    private String name;
    @Email
    @Size(min = 5, max = 100)
    @NotEmpty
    @NotNull
    @Column(unique = true, length = 150)
    private String email;
    @Column(length = 500)
    private String password;
    @Min(15)
    @Max(99)
    private Integer age;

}
