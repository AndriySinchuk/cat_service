package com.cat.service.entity;


import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "cat_owners")
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class CatOwner implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "owner_id")
    private int owner_id;

    @Column(name = "owner_name")
    private String ownerName;

    @Column(name = "owner_second_name")
    private String ownerSecondName;

    @Override
    public String toString() {
        return "Owner [id = " + owner_id + ", owner_name = " + ownerName + ", owner_second_name = " + ownerSecondName;
    }

}
