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
@Table(name = "cat_owners")
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class CatOwner implements Serializable {

    @Id
    @ApiModelProperty(hidden = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "owner_id")
    private Long owner_id;

    @Column(name = "owner_name")
    private String ownerName;

    @Column(name = "owner_second_name")
    private String ownerSecondName;

    @Override
    public String toString() {
        return "Owner [id = " + owner_id + ", owner_name = " + ownerName + ", owner_second_name = " + ownerSecondName;
    }

}
