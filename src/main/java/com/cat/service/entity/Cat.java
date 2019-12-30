package com.cat.service.entity;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "cats")
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Cat implements Serializable {

    private static final Integer serialVersionUID = 1;

    @Id
    @ApiModelProperty(hidden = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long catId;

    @Column(name = "cat_name", unique = true)
    private String catName;

    @Column(name = "breed_id")
    private Long breedId;

    @Column(name = "owner_id")
    private Long owner_Id;

    @Override
    public String toString() {
        return "Cat [id = " + catId + ", cat_name = " + catName + ", breed_id = " + breedId + ", ownerId = " + owner_Id + "]";
    }

}
