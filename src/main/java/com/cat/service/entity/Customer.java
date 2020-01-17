package com.cat.service.entity;


import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@Entity
@Table(name = "customers", uniqueConstraints = {@UniqueConstraint(columnNames = {"customer_phone_number"})})
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Customer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Long customer_id;

    @NotNull
    @Column(name = "customer_name")
    private String customerName;

    @Column(name = "customer_second_name")
    private String customerSecondName;

    @NotNull
    @Column(name = "customer_phone_number")
    private Long customerPhoneNumber;

    @Override
    public String toString() {
        return "Owner [id = " + customer_id + "," +
                " owner_name = " + customerName + "," +
                " owner_second_name = " + customerSecondName + "," +
                " customer_phone_number" + customerPhoneNumber;
    }

}
