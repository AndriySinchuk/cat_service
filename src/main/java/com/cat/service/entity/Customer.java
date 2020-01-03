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
@Table(name = "customers")
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Customer implements Serializable {

    @Id
    @ApiModelProperty(hidden = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Long customer_id;

    @Column(name = "customer_name")
    private String customerName;

    @Column(name = "customer_second_name")
    private String customerSecondName;

    @Override
    public String toString() {
        return "Owner [id = " + customer_id + ", owner_name = " + customerName + ", owner_second_name = " + customerSecondName;
    }

}
