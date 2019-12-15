package com.cat.service.entity;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

import java.io.Serializable;

import javax.persistence.*;

@Data
@Entity
@Table(name = "cats")
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Cat implements Serializable{

    private static final Integer serialVersionUID = 1;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cat_id")
    private Integer catId;

    @Column(name = "cat_name")
    private String catName;

//    @JoinColumn(name = "breed_id")
    @Column(name = "breed_id")
    private int breedId;

//    @JoinColumn(name = "owner_Id")
    @Column(name = "owner_id")
    private int owner_Id;

    @Override
    public String toString() {
        return "Cat [id = " + catId + ", cat_name = " + catName + ", breed_id = " + breedId + ", ownerId = " + owner_Id + "]";
    }

}
