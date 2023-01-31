package com.example.demo.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "nis")
    private String NIS;
    @Column(name = "email_id")
    private String emailId;

    @Column(name = "rombel")
    private String rombel;
}
