package com.cat.service.entity;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "pets")
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Pet implements Serializable {

    private static final Integer serialVersionUID = 1;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "pet_name", unique = true)
    private String petName;

    @Column(name = "breed_id")
    private Long breedId;

    @Column(name = "customer_id")
    private Long customerId;

    @Column(name = "doctor_id")
    private Long doctorId;

    @Column(name = "date_in")
    @ApiModelProperty(hidden = true)
    @CreatedDate
    @Temporal(TemporalType.DATE)
    private Date passedIn = new Date();

    @Column(name = "date_out")
    @CreatedDate
    @Temporal(TemporalType.DATE)
    @Nullable
    private Date passedOut = new Date();

    @Column(name = "diagnosis")
    private String diagnosis;

    @Override
    public String toString() {
        return "Pet [id = " + id + ", " +
                "pet_name = " + petName + ", " +
                "breed_id = " + breedId + ", " +
                "ownerId = " + customerId + ", " +
                "doctorId = " + doctorId + " ," +
                "date_in = " + passedIn + "," +
                "date_out = " + passedOut + "," +
                "diagnosis = " + diagnosis + "]";
    }

}
