package com.sda.java11.coursecenter.entities;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "contact_request")
public class ContactRequest extends BaseEntity {
    @Email
    @NotBlank
    @Column(name = "email")
    protected String email;

    @Column(name = "subject")
    @NotBlank
    protected String subject;

    @NotBlank
    @Column(name = "message")
    protected String message;
}
