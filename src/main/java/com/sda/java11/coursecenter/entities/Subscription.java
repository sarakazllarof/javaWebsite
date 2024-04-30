package com.sda.java11.coursecenter.entities;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;


@Getter
@Setter
@Entity
@Table(name = "subscription")
public class Subscription extends BaseEntity {

    @Email
    @Column(name = "email")
    protected String email;

    @NotBlank
    @Column(name = "card_number")
    protected String cardNumber;

    @NotBlank
    @Column(name = "first_name")
    protected String firstName;

    @NotBlank
    @Column(name = "last_name")
    protected String lastName;

    @NotNull
    @Column(name = "course_id")
    @Type(type = "org.hibernate.type.UUIDCharType")
    protected UUID courseId;

    @ManyToOne
    @JoinColumn(insertable = false, updatable = false, name = "course_id")
    protected Course course;
}
