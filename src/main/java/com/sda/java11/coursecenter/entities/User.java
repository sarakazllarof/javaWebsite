package com.sda.java11.coursecenter.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "user")
public class User extends BaseEntity {
    @Column(name = "username", unique = true)
    protected String username;

    @Column(name = "password")
    protected String password;

    @Column(name = "roles")
    protected String roles;
}
