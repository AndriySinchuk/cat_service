package com.cat.service.entity;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "cat_breeds")
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class CatBreed implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "breed_id")
    private int breed_id;

    @Column(name = "cat_breed")
    private String catBreed;

    @Override
    public String toString() {
        return "Cat breed [id = " + breed_id + ", cat breed = " + catBreed;
    }

}
