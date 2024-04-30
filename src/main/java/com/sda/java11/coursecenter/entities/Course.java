package com.sda.java11.coursecenter.entities;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "course")
public class Course extends BaseEntity {
    @Column(name = "name")
    protected String name;

    @Column(name = "price")
    @NotNull
    protected Double price;

    @NotNull
    @Column(name = "duration")
    protected Integer duration;

    @NotBlank
    @Column(name = "description")
    protected String description;

    @NotNull
    @Column(name = "discount")
    protected Boolean discount;

    @Column(name = "discount_price")
    protected Double discountPrice;

    @Column(name = "image")
    protected String image;

    @OneToMany(mappedBy = "course")
    protected List<Subscription> subscriptions;
}
