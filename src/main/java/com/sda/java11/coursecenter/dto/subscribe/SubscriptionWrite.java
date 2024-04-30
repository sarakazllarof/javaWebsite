package com.sda.java11.coursecenter.dto.subscribe;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Getter
@Setter
public class SubscriptionWrite {
    protected UUID id;

    @NotNull
    protected UUID courseId;

    @Email
    protected String email;

    @NotBlank
    protected String firstName;

    @NotBlank
    protected String lastName;

    @NotBlank
    protected String cardNumber;
}
