package com.github.rguliamov.PP223.model;

import lombok.Data;

import javax.persistence.*;

/**
 * @author Guliamov Rustam
 */
@Entity
@Data
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", length = 32)
    private String name;

    public String getRoleWithoutPrefix() {
        return name.split("_")[1];
    }
}
